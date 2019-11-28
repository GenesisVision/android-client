package vision.genesis.clientapp.feature.main.program;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetType;
import io.swagger.client.model.FollowDetailsFull;
import io.swagger.client.model.ProgramDetailsFull;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.NewInvestmentSuccessEvent;
import vision.genesis.clientapp.model.events.OnProgramFavoriteChangedEvent;
import vision.genesis.clientapp.model.events.SetProgramDetailsEventsCountEvent;
import vision.genesis.clientapp.model.events.SetProgramDetailsOpenPositionsCountEvent;
import vision.genesis.clientapp.model.events.SetProgramDetailsPeriodHistoryCountEvent;
import vision.genesis.clientapp.model.events.SetProgramDetailsTradesCountEvent;
import vision.genesis.clientapp.model.events.ShowEventDetailsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

@InjectViewState
public class ProgramDetailsPresenter extends MvpPresenter<ProgramDetailsView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public ProgramsManager programsManager;

	@Inject
	public FollowsManager followsManager;

	private Subscription userSubscription;

	private Subscription programDetailsSubscription;

	private Subscription followDetailsSubscription;

	private Subscription setProgramFavoriteSubscription;

	private ProgramDetailsModel model;

	private ProgramDetailsFull programDetails;

	private FollowDetailsFull followDetails;

	private boolean isActive = false;

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
		if (programDetailsSubscription != null) {
			programDetailsSubscription.unsubscribe();
		}
		if (followDetailsSubscription != null) {
			followDetailsSubscription.unsubscribe();
		}
		if (setProgramFavoriteSubscription != null) {
			setProgramFavoriteSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	void onResume() {
		isActive = true;
		getDetails();
	}

	void onPause() {
		isActive = false;
	}

	void setData(ProgramDetailsModel model) {
		this.model = model;
	}

	void onFavoriteButtonClicked(boolean isFavorite) {
		setProgramFavorite(isFavorite);
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getDetails();
	}

	void onTryAgainClicked() {
		getViewState().showNoInternetProgress(true);
		getDetails();
	}

	private void getDetails() {
		if (model != null) {
			if (model.getAssetType().equals(AssetType.PROGRAM)) {
				getProgramDetails();
			}
			else if (model.getAssetType().equals(AssetType.FOLLOW)) {
				getFollowDetails();
			}
		}
	}

	private void getProgramDetails() {
		if (model != null && programsManager != null) {
			programDetailsSubscription = programsManager.getProgramDetails(model.getProgramId())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleProgramDetailsSuccess,
							this::handleProgramDetailsError);
		}
	}

	private void handleProgramDetailsSuccess(ProgramDetailsFull programDetails) {
		programDetailsSubscription.unsubscribe();
		this.programDetails = programDetails;

		if (programDetails.getPersonalDetails() != null && programDetails.getPersonalDetails().isIsOwnAsset()) {
			if (programDetails.getSignalSettings() != null && programDetails.getSignalSettings().isIsActive() && followDetails == null) {
				getFollowDetails();
			}
			else {
				hideProgress();
				getViewState().showOwner(programDetails, followDetails);
			}
		}
		else {
			hideProgress();
			getViewState().showProgram(programDetails);
		}
	}

	private void handleProgramDetailsError(Throwable throwable) {
		programDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (programDetails == null) {
				getViewState().showNoInternet(true);
				getViewState().showNoInternetProgress(false);
			}
			else {
				getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
			}
		}
		else {
			ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
		}
	}

	private void hideProgress() {
		getViewState().showNoInternet(false);
		getViewState().showNoInternetProgress(false);
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);
	}

	private void getFollowDetails() {
		if (model != null && followsManager != null) {
			followDetailsSubscription = followsManager.getFollowDetails(model.getProgramId().toString())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleFollowDetailsSuccess,
							this::handleFollowDetailsError);
		}
	}

	private void handleFollowDetailsSuccess(FollowDetailsFull followDetails) {
		followDetailsSubscription.unsubscribe();
		this.followDetails = followDetails;

		if (followDetails.getPersonalDetails() != null && followDetails.getPersonalDetails().isIsOwnAsset()) {
			if (followDetails.getPersonalDetails().isIsProgram() && programDetails == null) {
				getProgramDetails();
			}
			else {
				hideProgress();
				getViewState().showOwner(programDetails, followDetails);
			}
		}
		else {
			hideProgress();
			getViewState().showFollow(followDetails);
		}
	}

	private void handleFollowDetailsError(Throwable throwable) {
		followDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (programDetails == null) {
				getViewState().showNoInternet(true);
				getViewState().showNoInternetProgress(false);
			}
			else {
				getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
			}
		}
		else {
			ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
		}
	}

	private void setProgramFavorite(boolean isFavorite) {
		setProgramFavoriteSubscription = programsManager.setProgramFavorite(model.getProgramId(), isFavorite)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(response -> handleSetProgramFavoriteSuccess(model.getProgramId(), isFavorite),
						this::handleSetProgramFavoriteError);
	}

	private void handleSetProgramFavoriteSuccess(UUID programId, boolean isFavorite) {
		setProgramFavoriteSubscription.unsubscribe();

		EventBus.getDefault().post(new OnProgramFavoriteChangedEvent(programId, isFavorite));
	}

	private void handleSetProgramFavoriteError(Throwable throwable) {
		setProgramFavoriteSubscription.unsubscribe();

		if (programDetails == null) {
			getViewState().showProgram(programDetails);
		}
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showToast(message));
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
		getViewState().showToolbarButtons(true);
	}

	private void userLoggedOff() {
		getViewState().showToolbarButtons(false);
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}

	@Subscribe
	public void onEventMainThread(NewInvestmentSuccessEvent event) {
		getViewState().finishActivity();
	}

	@Subscribe
	public void onEventMainThread(SetProgramDetailsOpenPositionsCountEvent event) {
		getViewState().setOpenPositionsCount(event.getOpenPositionsCount());
	}

	@Subscribe
	public void onEventMainThread(SetProgramDetailsTradesCountEvent event) {
		getViewState().setTradesCount(event.getTradesCount());
	}

	@Subscribe
	public void onEventMainThread(SetProgramDetailsPeriodHistoryCountEvent event) {
		getViewState().setPeriodHistoryCount(event.getPeriodHistoryCount());
	}

	@Subscribe
	public void onEventMainThread(SetProgramDetailsEventsCountEvent event) {
		getViewState().setEventsCount(event.getEventsCount());
	}

	@Subscribe
	public void onEventMainThread(ShowEventDetailsEvent event) {
		if (isActive) {
			getViewState().showEventDetails(event.getEvent());
		}
	}
}
