package vision.genesis.clientapp.feature.main.user;

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
import vision.genesis.clientapp.managers.SocialManager;
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
public class UserDetailsPresenter extends MvpPresenter<UserDetailsView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public UsersManager usersManager;

//	@Inject
//	public ProfileManager profileManager;

	@Inject
	public SocialManager socialManager;

	private Subscription userSubscription;

//	private Subscription getProfileSubscription;

	private Subscription userDetailsSubscription;

	private Subscription followSubscription;

	private UUID userId;

	private PublicProfile userDetails;

	private User loggedUser;

//	private ProfileFullViewModel profile;

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
//		if (getProfileSubscription != null) {
//			getProfileSubscription.unsubscribe();
//		}
		if (userDetailsSubscription != null) {
			userDetailsSubscription.unsubscribe();
		}
		if (followSubscription != null) {
			followSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setUserId(UUID userId) {
		this.userId = userId;
	}

	void onResume() {
		getUserDetails();
	}

	void onFollowClicked() {
		changeFollowStatus();
	}

	void onUnfollowClicked() {
		changeFollowStatus();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getUserDetails();
	}

	void onTryAgainClicked() {
		getViewState().showNoInternetProgress(true);
		getUserDetails();
	}

	private void getUserDetails() {
		if (userId != null && usersManager != null) {
			userDetailsSubscription = usersManager.getUser(userId.toString(), ImageQuality.HIGH)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleUserDetailsSuccess,
							this::handleUserDetailsError);
		}
	}

	private void handleUserDetailsSuccess(PublicProfile userDetails) {
		userDetailsSubscription.unsubscribe();
		getViewState().showNoInternet(false);
		getViewState().showNoInternetProgress(false);
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		this.userDetails = userDetails;
//		initViewPagerMaybe();
		getViewState().initViewPager(userId, userDetails.getPersonalDetails().isCanWritePost());
		getViewState().setUserDetails(userDetails);
	}

	private void handleUserDetailsError(Throwable throwable) {
		userDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (userDetails == null) {
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
		this.loggedUser = user;
		if (user == null) {
			userLoggedOff();
		}
		else {
			userLoggedOn();
		}
	}

	private void userLoggedOn() {
		getViewState().showToolbarButtons(false);
//		getProfileInfo();
	}

	private void userLoggedOff() {
		getViewState().showToolbarButtons(false);
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}

//	private void getProfileInfo() {
//		getProfileSubscription = profileManager.getProfileFull(true)
//				.subscribeOn(Schedulers.io())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(this::handleGetProfileSuccess,
//						this::handleGetProfileError);
//	}
//
//	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
//		this.profile = profile;
//		initViewPagerMaybe();
//	}
//
//	private void initViewPagerMaybe() {
//		if (userId != null && profile != null && userDetails != null) {
//			getViewState().initViewPager(userId, profile.getId().toString().equals(userId.toString()) || userDetails.getPersonalDetails().isCanWritePost());
//		}
//	}
//
//	private void handleGetProfileError(Throwable throwable) {
//		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
//	}

	private void changeFollowStatus() {
		if (socialManager != null && userDetails != null) {
			if (followSubscription != null) {
				followSubscription.unsubscribe();
			}

			getViewState().showFollowProgress(true);
			followSubscription = (!userDetails.getPersonalDetails().isIsFollow()
					? socialManager.follow(userDetails.getId())
					: socialManager.unfollow(userDetails.getId()))
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleFollowResponse,
							this::handleFollowError);
		}
	}

	private void handleFollowResponse(Void response) {
		followSubscription.unsubscribe();
		getViewState().showFollowProgress(false);

		this.userDetails.getPersonalDetails().setIsFollow(!userDetails.getPersonalDetails().isIsFollow());
		getViewState().setUserDetails(userDetails);
	}

	private void handleFollowError(Throwable throwable) {
		followSubscription.unsubscribe();
		getViewState().showFollowProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
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
