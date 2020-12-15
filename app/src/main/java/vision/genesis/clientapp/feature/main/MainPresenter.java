package vision.genesis.clientapp.feature.main;

import android.content.Context;
import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;

import javax.inject.Inject;

import io.swagger.client.model.AssetType;
import io.swagger.client.model.ExternalKycAccessToken;
import io.swagger.client.model.FacetSortType;
import io.swagger.client.model.NotificationViewModel;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.PushNotificationViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.fcm.GvFirebaseMessagingService;
import vision.genesis.clientapp.feature.main.assets.AssetsFragment;
import vision.genesis.clientapp.feature.main.dashboard.DashboardFragment;
import vision.genesis.clientapp.feature.main.settings.SettingsFragment;
import vision.genesis.clientapp.feature.main.social.SocialMainFragment;
import vision.genesis.clientapp.feature.main.unregistered.dashboard.UnregisteredDashboardFragment;
import vision.genesis.clientapp.feature.main.unregistered.settings.UnregisteredSettingsFragment;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.KycVerificationManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.AppUpdateModel;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.NotificationLocation;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.UserDetailsModel;
import vision.genesis.clientapp.model.events.HandlePushEvent;
import vision.genesis.clientapp.model.events.OnAddNewPostClickedEvent;
import vision.genesis.clientapp.model.events.OnFollowFacetClickedEvent;
import vision.genesis.clientapp.model.events.OnFundFacetClickedEvent;
import vision.genesis.clientapp.model.events.OnHashTagClickedEvent;
import vision.genesis.clientapp.model.events.OnProgramFacetClickedEvent;
import vision.genesis.clientapp.model.events.OnShowMediaActivityEvent;
import vision.genesis.clientapp.model.events.OnShowRepostEvent;
import vision.genesis.clientapp.model.events.OnShowUsersListActivityEvent;
import vision.genesis.clientapp.model.events.OnStartKycClickedEvent;
import vision.genesis.clientapp.model.events.OnThemeChangedEvent;
import vision.genesis.clientapp.model.events.OpenUrlEvent;
import vision.genesis.clientapp.model.events.ShowBottomNavigationEvent;
import vision.genesis.clientapp.model.events.ShowCopytradingAccountDetailsEvent;
import vision.genesis.clientapp.model.events.ShowDisableTfaActivityEvent;
import vision.genesis.clientapp.model.events.ShowEventDetailsEvent;
import vision.genesis.clientapp.model.events.ShowFeedActivityEvent;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.model.events.ShowFundsListEvent;
import vision.genesis.clientapp.model.events.ShowLockScreenEvent;
import vision.genesis.clientapp.model.events.ShowMessageActivityEvent;
import vision.genesis.clientapp.model.events.ShowOpenTradeDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramsListEvent;
import vision.genesis.clientapp.model.events.ShowReportPostEvent;
import vision.genesis.clientapp.model.events.ShowSetupTfaActivityEvent;
import vision.genesis.clientapp.model.events.ShowSpecificWalletEvent;
import vision.genesis.clientapp.model.events.ShowTradingAccountDetailsEvent;
import vision.genesis.clientapp.model.events.ShowTransactionDetailsEvent;
import vision.genesis.clientapp.model.events.ShowUserDetailsEvent;
import vision.genesis.clientapp.model.events.ShowVerificationInfoActivityEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.OnShowPostDetailsEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public KycVerificationManager kycManager;

	private Subscription userSubscription;

	private Subscription platformStatusSubscription;

	private Subscription kycSubscription;

	private UnregisteredDashboardFragment unregisteredDashboardFragment;

	private UnregisteredSettingsFragment unregisteredSettingsFragment;

	private DashboardFragment dashboardFragment;

	private AssetsFragment assetsFragment;

	private SocialMainFragment socialMainFragment;

	private SettingsFragment settingsFragment;

	private boolean firstCheckPin = true;

	private boolean isActive = false;

	private User user;

	private Bundle pushData;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		if (settingsManager.isScreenLockEnabled()) {
			getViewState().showLockScreen(settingsManager.getFingerprintEnabled());
		}
		else {
			onCheckPinPassed();
		}

		getViewState().setNavigationItemSelected(0);
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}
		if (platformStatusSubscription != null) {
			platformStatusSubscription.unsubscribe();
		}
		if (kycSubscription != null) {
			kycSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
		isActive = true;
	}

	void onPause() {
		isActive = false;
	}

	void setData(Bundle data, String dataString) {
		pushData = data;
	}

	private void handlePushData(Bundle data) {
		if (data != null) {
			Gson gson = new Gson();
			Type typeToken = new TypeToken<NotificationViewModel>()
			{
			}.getType();

			PushNotificationViewModel push = data.getParcelable(GvFirebaseMessagingService.KEY_NOTIFICATION);
			if (push == null) {
				push = gson.fromJson(data.getString(GvFirebaseMessagingService.KEY_RESULT), typeToken);
			}

			if (push != null) {
				if (push.getLocation() != null && push.getLocation().getLocation() != null) {
					switch (push.getLocation().getLocation()) {
						case NotificationLocation.USER:
							UserDetailsModel userDetailsModel = new UserDetailsModel(push.getLocation().getId(), null, null, null);
							getViewState().showUserDetails(userDetailsModel);
							break;
						case NotificationLocation.PROGRAM:
							ProgramDetailsModel programDetailsModel = new ProgramDetailsModel();
							programDetailsModel.setProgramId(push.getLocation().getId());
							programDetailsModel.setAssetType(AssetType.PROGRAM);
							getViewState().showProgramDetails(programDetailsModel);
							break;
						case NotificationLocation.FUND:
							FundDetailsModel fundDetailsModel = new FundDetailsModel();
							fundDetailsModel.setFundId(push.getLocation().getId());
							getViewState().showFundDetails(fundDetailsModel);
							break;
						case NotificationLocation.FOLLOW:
							ProgramDetailsModel followDetailsModel = new ProgramDetailsModel();
							followDetailsModel.setProgramId(push.getLocation().getId());
							followDetailsModel.setAssetType(AssetType.FOLLOW);
							getViewState().showProgramDetails(followDetailsModel);
							break;
						case NotificationLocation.TRADING_ACCOUNT:
							TradingAccountDetailsModel tradingAccountDetailsModel = new TradingAccountDetailsModel();
							tradingAccountDetailsModel.setTradingAccountId(push.getLocation().getId());
							getViewState().showTradingAccountDetails(tradingAccountDetailsModel);
							break;
						case NotificationLocation.SOCIAL_POST:
							getViewState().showPostDetails(push.getLocation().getId(), null, false);
							break;
						case NotificationLocation.SOCIAL_MEDIA_POST:
							getViewState().showMediaPostDetails(push.getLocation().getId());
							break;
						case NotificationLocation.DASHBOARD:
							break;
						case NotificationLocation.EXTERNAL_URL:
							getViewState().openUrl(push.getLocation().getExternalUrl());
							break;
						default:
							getViewState().showNotificationsActivity();
							break;
					}
				}
				else {
					getViewState().showNotificationsActivity();
				}
			}
			pushData = null;
		}
	}

	void onCheckPinPassed() {
		if (firstCheckPin) {
			firstCheckPin = false;
			subscribeToUser();
			getPlatformStatus();
		}
	}

	boolean onBottomMenuSelectionChanged(int position, boolean wasSelected) {
		switch (position) {
			case 0:
				if (user == null) {
					if (wasSelected && unregisteredDashboardFragment != null) {
						return false;
					}
					showUnregisteredDashboard();
				}
				else {
					if (wasSelected && dashboardFragment != null) {
						return false;
					}
					showDashboard();
				}
				break;
			case 1:
				if (wasSelected && assetsFragment != null) {
					return false;
				}
				showAssets();
				break;
			case 2:
				if (wasSelected && socialMainFragment != null) {
					return false;
				}
				showSocial();
				break;
			case 3:
				if (user == null) {
					if (wasSelected && unregisteredSettingsFragment != null) {
						return false;
					}
					showUnregisteredSettings();
				}
				else {
					if (wasSelected && settingsFragment != null) {
						return false;
					}
					showSettings();
				}
				break;
		}
		return true;
	}

	private void showUnregisteredDashboard() {
		if (unregisteredDashboardFragment == null) {
			unregisteredDashboardFragment = new UnregisteredDashboardFragment();
			getViewState().addFragmentToBackstack(unregisteredDashboardFragment);
		}
		else {
			getViewState().showFragment(unregisteredDashboardFragment);
		}
	}

	private void showUnregisteredSettings() {
		if (unregisteredSettingsFragment == null) {
			unregisteredSettingsFragment = new UnregisteredSettingsFragment();
			getViewState().addFragmentToBackstack(unregisteredSettingsFragment);
		}
		else {
			getViewState().showFragment(unregisteredSettingsFragment);
		}
	}

	private void showDashboard() {
		if (dashboardFragment == null) {
			dashboardFragment = new DashboardFragment();
			getViewState().addFragmentToBackstack(dashboardFragment);
		}
		else {
			getViewState().showFragment(dashboardFragment);
		}
	}

	private void showAssets() {
		if (assetsFragment == null) {
			assetsFragment = new AssetsFragment();
			getViewState().addFragmentToBackstack(assetsFragment);
		}
		else {
			getViewState().showFragment(assetsFragment);
		}
	}

	private void showSocial() {
		if (socialMainFragment == null) {
			socialMainFragment = new SocialMainFragment();
			getViewState().addFragmentToBackstack(socialMainFragment);
		}
		else {
			getViewState().showFragment(socialMainFragment);
		}
	}

	private void showSettings() {
		if (settingsFragment == null) {
			settingsFragment = new SettingsFragment();
			getViewState().addFragmentToBackstack(settingsFragment);
		}
		else {
			getViewState().showFragment(settingsFragment);
		}
	}

	private void getPlatformStatus() {
		platformStatusSubscription = settingsManager.getPlatformInfo()
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.computation())
				.map(this::checkIfNeedUpdate)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::onPlatformStatusSuccess,
						this::onPlatformStatusError);
	}

	private AppUpdateModel checkIfNeedUpdate(PlatformInfo response) {
		AppUpdateModel model = new AppUpdateModel(response.getAppVersionInfo().getAndroid().getLastVersion().getVersionName());
		try {
			int lastVersionCode = Integer.parseInt(response.getAppVersionInfo().getAndroid().getLastVersion().getVersionCode());
			model.setVersionCode(lastVersionCode);
			if (authManager.haveUpdate(lastVersionCode)) {
				model.needUpdate = true;
			}
		} catch (NumberFormatException ignored) {
		}
		return model;
	}

	private void onPlatformStatusSuccess(AppUpdateModel model) {
		platformStatusSubscription.unsubscribe();

		if (model.needUpdate) {
			getViewState().showAppUpdateDialog(model);
		}
	}

	private void onPlatformStatusError(Throwable error) {
		platformStatusSubscription.unsubscribe();
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated, this::handleUserError);
	}

	private void userUpdated(User user) {
		getViewState().hideSplashScreen();
		if (user == null && this.user == null) {
			return;
		}
		boolean userWasLoggedOff = false;
		if (this.user == null) {
			userWasLoggedOff = true;
		}
		this.user = user;
		if (user == null) {
			userLoggedOff();
		}
		else {
			userLoggedOn(userWasLoggedOff);
			if (authManager.isNeedShowEnableTwoFactor()) {
				getViewState().showEnableTwoFactor();
				authManager.setEnableTwoFactorAlreadyShown(true);
			}
		}
	}

	private void userLoggedOn(boolean userWasLoggedOff) {
		if (unregisteredDashboardFragment != null) {
			getViewState().removeFragment(unregisteredDashboardFragment);
		}
		if (assetsFragment != null) {
			getViewState().removeFragment(assetsFragment);
		}
		if (socialMainFragment != null) {
			getViewState().removeFragment(socialMainFragment);
		}
		if (unregisteredSettingsFragment != null) {
			getViewState().removeFragment(unregisteredSettingsFragment);
		}
		unregisteredDashboardFragment = null;
		assetsFragment = null;
		socialMainFragment = null;
		unregisteredSettingsFragment = null;

		handlePushData(pushData);

		if (userWasLoggedOff) {
			getViewState().setNavigationItemSelected(0);
		}
	}

	private void userLoggedOff() {
		if (dashboardFragment != null) {
			getViewState().removeFragment(dashboardFragment);
		}
		if (assetsFragment != null) {
			getViewState().removeFragment(assetsFragment);
		}
		if (socialMainFragment != null) {
			getViewState().removeFragment(socialMainFragment);
		}
		if (settingsFragment != null) {
			getViewState().removeFragment(settingsFragment);
		}
		dashboardFragment = null;
		assetsFragment = null;
		socialMainFragment = null;
		settingsFragment = null;

		getViewState().setNavigationItemSelected(0);

	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}

	private void startKyc() {
		if (kycManager != null) {
			kycSubscription = kycManager.getKycVerificationData()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleKycDataSuccess, this::handleKycDataError);
		}
	}

	private void handleKycDataSuccess(ExternalKycAccessToken model) {
		kycSubscription.unsubscribe();

		getViewState().startKycProcess(kycManager, model);
	}

	private void handleKycDataError(Throwable throwable) {
		kycSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(ShowProgramDetailsEvent event) {
		getViewState().showProgramDetails(event.programDetailsModel);
	}

	@Subscribe
	public void onEventMainThread(ShowMessageActivityEvent event) {
		getViewState().showMessageDialog(event.imageResourceId, event.title, event.message, event.mustRead, event.listener);
	}

	@Subscribe
	public void onEventMainThread(ShowSetupTfaActivityEvent event) {
		getViewState().showSetupTwoFactorActivity();
	}

	@Subscribe
	public void onEventMainThread(ShowDisableTfaActivityEvent event) {
		getViewState().showDisableTwoFactorActivity();
	}

	@Subscribe
	public void onEventMainThread(ShowLockScreenEvent event) {
		getViewState().showLockScreen(settingsManager.getFingerprintEnabled());
	}

	@Subscribe
	public void onEventMainThread(OnThemeChangedEvent event) {
		getViewState().changeThemeWithAnim();
	}

	@Subscribe
	public void onEventMainThread(ShowBottomNavigationEvent event) {
//		getViewState().showBottomNavigation(event.getAnimate());
	}

	@Subscribe
	public void onEventMainThread(ShowFundDetailsEvent event) {
		getViewState().showFundDetails(event.getFundDetailsModel());
	}

	@Subscribe
	public void onEventMainThread(ShowSpecificWalletEvent event) {
		getViewState().showSpecificWallet(event.getWalletModel());
	}

	@Subscribe
	public void onEventMainThread(ShowCopytradingAccountDetailsEvent event) {
		getViewState().showCopytradingAccountDetails(event.getModel());
	}

	@Subscribe
	public void onEventMainThread(ShowTransactionDetailsEvent event) {
		getViewState().showTransactionDetails(event.getTransaction());
	}

	@Subscribe
	public void onEventMainThread(ShowUserDetailsEvent event) {
		getViewState().showUserDetails(event.getModel());
	}

	@Subscribe
	public void onEventMainThread(OnProgramFacetClickedEvent event) {
		if (event.getFacet().getTitle().toLowerCase().equals(context.getString(R.string.rating).toLowerCase())) {
			getViewState().showProgramsRating(event.getFacet());
		}
		else {
			getViewState().showProgramFacet(event.getFacet());
		}
	}

	@Subscribe
	public void onEventMainThread(OnFundFacetClickedEvent event) {
		if (event.getFacet().getSortType().equals(FacetSortType.FUNDSCHALLENGE)) {
			getViewState().showFundsChallengeActivity(event.getFacet());
		}
		else {
			getViewState().showFundFacet(event.getFacet());
		}
	}

	@Subscribe
	public void onEventMainThread(OnFollowFacetClickedEvent event) {
		getViewState().showFollowFacet(event.getFacet());
	}

	@Subscribe
	public void onEventMainThread(ShowOpenTradeDetailsEvent event) {
		getViewState().showOpenTradeDetails(event.getModel());
	}

	@Subscribe
	public void onEventMainThread(ShowEventDetailsEvent event) {
		if (isActive) {
			getViewState().showEventDetails(event.getEvent());
		}
	}

	@Subscribe
	public void onEventMainThread(ShowTradingAccountDetailsEvent event) {
		getViewState().showTradingAccountDetails(event.getTradingAccountDetailsModel());
	}

	@Subscribe
	public void onEventMainThread(ShowProgramsListEvent event) {
		getViewState().setNavigationItemSelected(1);
	}

	@Subscribe
	public void onEventMainThread(ShowFundsListEvent event) {
		getViewState().setNavigationItemSelected(1);
	}

	@Subscribe
	public void onEventMainThread(ShowFeedActivityEvent event) {
		getViewState().showSocialActivity(event.getType());
	}

	@Subscribe
	public void onEventMainThread(OnHashTagClickedEvent event) {
		getViewState().showSocialActivityWithTag(event.getHashTag());
	}

	@Subscribe
	public void onEventMainThread(OnShowMediaActivityEvent event) {
		getViewState().showMediaActivity();
	}

	@Subscribe
	public void onEventMainThread(OnShowUsersListActivityEvent event) {
		getViewState().showUsersListActivity();
	}

	@Subscribe
	public void onEventMainThread(OnShowPostDetailsEvent event) {
		getViewState().showPostDetails(event.getPostId(), event.getPost(), event.isShowComments());
	}

	@Subscribe
	public void onEventMainThread(OnAddNewPostClickedEvent event) {
		getViewState().showCreatePostActivity(event.getUserId());
	}

	@Subscribe
	public void onEventMainThread(OnShowRepostEvent event) {
		if (user != null) {
			getViewState().showCreatePostActivityWithRepost(event.getPost());
		}
	}

	@Subscribe
	public void onEventMainThread(ShowReportPostEvent event) {
		getViewState().showReportPostActivity(event.getPost());
	}

	@Subscribe
	public void onEventMainThread(OpenUrlEvent event) {
		getViewState().openUrl(event.getUrl());
	}

	@Subscribe
	public void onEventMainThread(HandlePushEvent event) {
		pushData = event.getPushData();
		handlePushData(pushData);
	}

	@Subscribe
	public void onEventMainThread(ShowVerificationInfoActivityEvent event) {
		getViewState().showVerificationInfoActivity(event.getVerificationStatus());
	}

	@Subscribe
	public void onEventMainThread(OnStartKycClickedEvent event) {
		startKyc();
	}
}