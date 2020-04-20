package vision.genesis.clientapp.feature.main.manager;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.PublicProfile;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.UsersManager;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.SetManagerDetailsFollowsCountEvent;
import vision.genesis.clientapp.model.events.SetManagerDetailsFundsCountEvent;
import vision.genesis.clientapp.model.events.SetManagerDetailsProgramsCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

@InjectViewState
public class ManagerDetailsPresenter extends MvpPresenter<ManagerDetailsView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public UsersManager usersManager;

	private Subscription userSubscription;

	private Subscription managerDetailsSubscription;

	private UUID managerId;

	private PublicProfile managerDetails;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}

		if (managerDetailsSubscription != null) {
			managerDetailsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setManagerId(UUID managerId) {
		this.managerId = managerId;
	}

	void onResume() {
		getManagerDetails();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getManagerDetails();
	}

	void onTryAgainClicked() {
		getViewState().showNoInternetProgress(true);
		getManagerDetails();
	}

	private void getManagerDetails() {
		if (managerId != null && usersManager != null) {
			managerDetailsSubscription = usersManager.getUser(managerId.toString(), ImageQuality.HIGH)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleManagerDetailsSuccess,
							this::handleManagerDetailsError);
		}
	}

	private void handleManagerDetailsSuccess(PublicProfile managerDetails) {
		managerDetailsSubscription.unsubscribe();
		getViewState().showNoInternet(false);
		getViewState().showNoInternetProgress(false);
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		this.managerDetails = managerDetails;
		getViewState().setManagerDetails(managerDetails);
	}

	private void handleManagerDetailsError(Throwable throwable) {
		managerDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (managerDetails == null) {
				getViewState().showNoInternet(true);
				getViewState().showNoInternetProgress(false);
			}
			else {
				getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
			}
		}
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated, this::handleUserError);
	}

	private void userUpdated(User user) {
		if (user == null) {
			userLoggedOff();
		}
		else {
			userLoggedOn();
		}
	}

	private void userLoggedOn() {
		getViewState().showToolbarButtons(false);
	}

	private void userLoggedOff() {
		getViewState().showToolbarButtons(false);
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}

	@Subscribe
	public void onEventMainThread(SetManagerDetailsProgramsCountEvent event) {
		getViewState().setProgramsCount(event.getProgramsCount());
	}

	@Subscribe
	public void onEventMainThread(SetManagerDetailsFundsCountEvent event) {
		getViewState().setFundsCount(event.getFundsCount());
	}

	@Subscribe
	public void onEventMainThread(SetManagerDetailsFollowsCountEvent event) {
		getViewState().setFollowsCount(event.getFollowsCount());
	}
}
