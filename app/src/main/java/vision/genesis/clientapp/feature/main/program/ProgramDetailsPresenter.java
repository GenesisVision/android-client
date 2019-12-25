package vision.genesis.clientapp.feature.main.program;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetType;
import io.swagger.client.model.ProgramFollowDetailsFull;
import rx.Observable;
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

	private Subscription programFollowDetailsSubscription;

	private Subscription setProgramFavoriteSubscription;

	private ProgramDetailsModel model;

	private ProgramFollowDetailsFull details;

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
		if (programFollowDetailsSubscription != null) {
			programFollowDetailsSubscription.unsubscribe();
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
		if (model != null && programsManager != null && followsManager != null) {
			if (model.getAssetType().equals(AssetType.PROGRAM)) {
				getProgramFollowDetails(programsManager.getProgramDetails(model.getProgramId()));
			}
			else if (model.getAssetType().equals(AssetType.FOLLOW)) {
				getProgramFollowDetails(followsManager.getFollowDetails(model.getProgramId().toString()));
			}
		}
	}

	private void getProgramFollowDetails(Observable<ProgramFollowDetailsFull> observable) {
		if (programFollowDetailsSubscription != null) {
			programFollowDetailsSubscription.unsubscribe();
		}
		programFollowDetailsSubscription = observable
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleProgramFollowDetailsSuccess,
						this::handleProgramFollowDetailsError);
	}

	private void handleProgramFollowDetailsSuccess(ProgramFollowDetailsFull details) {
		programFollowDetailsSubscription.unsubscribe();
		hideProgress();

		this.details = details;

		if (details.getPublicInfo().isIsOwnAsset()) {
			getViewState().showOwner(details);
		}
		else {
			if (model.getAssetType().equals(AssetType.PROGRAM)) {
				getViewState().showProgram(details);
			}
			else if (model.getAssetType().equals(AssetType.FOLLOW)) {
				getViewState().showFollow(details);
			}
		}
	}

	private void handleProgramFollowDetailsError(Throwable throwable) {
		programFollowDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (details == null) {
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
