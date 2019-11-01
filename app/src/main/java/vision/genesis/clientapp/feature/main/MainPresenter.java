package vision.genesis.clientapp.feature.main;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.ProgramsFilterSorting;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.assets.AssetsFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.InvestorDashboardFragment;
import vision.genesis.clientapp.feature.main.dashboard.manager.ManagerDashboardFragment;
import vision.genesis.clientapp.feature.main.settings.SettingsFragment;
import vision.genesis.clientapp.feature.main.wallet.WalletFragment;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.AppUpdateModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.HideBottomNavigationEvent;
import vision.genesis.clientapp.model.events.NewInvestmentSuccessEvent;
import vision.genesis.clientapp.model.events.OnBrowseFundsClickedEvent;
import vision.genesis.clientapp.model.events.OnBrowseProgramsClickedEvent;
import vision.genesis.clientapp.model.events.OnFundFacetClickedEvent;
import vision.genesis.clientapp.model.events.OnProgramFacetClickedEvent;
import vision.genesis.clientapp.model.events.OnThemeChangedEvent;
import vision.genesis.clientapp.model.events.ShowBottomNavigationEvent;
import vision.genesis.clientapp.model.events.ShowCopytradingAccountDetailsEvent;
import vision.genesis.clientapp.model.events.ShowDisableTfaActivityEvent;
import vision.genesis.clientapp.model.events.ShowEventDetailsEvent;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.model.events.ShowLockScreenEvent;
import vision.genesis.clientapp.model.events.ShowManagerDetailsEvent;
import vision.genesis.clientapp.model.events.ShowMessageActivityEvent;
import vision.genesis.clientapp.model.events.ShowOpenTradeDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.model.events.ShowSetupTfaActivityEvent;
import vision.genesis.clientapp.model.events.ShowSpecificWalletEvent;
import vision.genesis.clientapp.model.events.ShowTransactionDetailsEvent;
import vision.genesis.clientapp.model.events.ShowWithdrawProgramEvent;

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

	private Subscription userSubscription;

	private Subscription platformStatusSubscription;

	private InvestorDashboardFragment investorDashboardFragment;

	private ManagerDashboardFragment managerDashboardFragment;

	private AssetsFragment assetsFragment;

	private WalletFragment walletFragment;

	private SettingsFragment settingsFragment;

	private boolean firstCheckPin = true;

	private boolean isActive = false;

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

		getViewState().setNavigationItemSelected(1);
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}
		if (platformStatusSubscription != null) {
			platformStatusSubscription.unsubscribe();
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

	void onCheckPinPassed() {
		if (firstCheckPin) {
			firstCheckPin = false;
			subscribeToUser();
			getPlatformStatus();
		}
	}

	void onBottomMenuSelectionChanged(int position) {
		switch (position) {
			case 0:
				showDashboard();
				break;
			case 1:
				showAssets();
				break;
			case 2:
				showWallet();
				break;
			case 3:
				showSettings();
				break;
		}
	}

	private void showDashboard() {
		if (investorDashboardFragment == null) {
			investorDashboardFragment = new InvestorDashboardFragment();
			getViewState().addFragmentToBackstack(investorDashboardFragment);
		}
		else {
			getViewState().showFragment(investorDashboardFragment);
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

	private void showWallet() {
		if (walletFragment == null) {
			walletFragment = new WalletFragment();
			getViewState().addFragmentToBackstack(walletFragment);
		}
		else {
			getViewState().showFragment(walletFragment);
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

	void onSignInButtonClicked() {
		getViewState().showLoginActivity();
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
		if (user == null) {
			userLoggedOff();
		}
		else {
			userLoggedOn();
			if (authManager.isNeedShowEnableTwoFactor()) {
				getViewState().showEnableTwoFactor();
				authManager.setEnableTwoFactorAlreadyShown(true);
			}
		}
	}

	private void userLoggedOn() {
		getViewState().setNavigationItemSelected(0);
		getViewState().showBottomNavigation(true);
		getViewState().hideSignInButton();
	}

	private void userLoggedOff() {
		getViewState().setNavigationItemSelected(1);
		getViewState().showSignInButton();
		getViewState().hideBottomNavigation();

	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}

	@Subscribe
	public void onEventMainThread(OnBrowseProgramsClickedEvent event) {
		getViewState().setNavigationItemSelected(1);
	}

	@Subscribe
	public void onEventMainThread(OnBrowseFundsClickedEvent event) {
		getViewState().setNavigationItemSelected(1);
	}

	@Subscribe
	public void onEventMainThread(ShowProgramDetailsEvent event) {
		getViewState().showProgramDetails(event.programDetailsModel);
	}

	@Subscribe
	public void onEventMainThread(ShowWithdrawProgramEvent event) {
		ProgramRequest withdrawalRequest = new ProgramRequest();
//		withdrawalRequest.programId = event.programId;
//		withdrawalRequest.programName = event.programName;
//		withdrawalRequest.available = event.investedTokens;
		getViewState().showWithdrawProgram(withdrawalRequest);
	}

	@Subscribe
	public void onEventMainThread(NewInvestmentSuccessEvent event) {
		getViewState().setNavigationItemSelected(0);
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
		getViewState().showBottomNavigation(event.getAnimate());
	}

	@Subscribe
	public void onEventMainThread(HideBottomNavigationEvent event) {
		getViewState().hideBottomNavigation();
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
	public void onEventMainThread(ShowManagerDetailsEvent event) {
		getViewState().showManagerDetails(event.getModel());
	}

	@Subscribe
	public void onEventMainThread(OnProgramFacetClickedEvent event) {
		if (event.getFacet().getTitle().toLowerCase().equals(context.getString(R.string.rating).toLowerCase())) {
			event.getFacet().setSorting(ProgramsFilterSorting.BYLEVELPROGRESSDESC);
		}
		getViewState().showProgramFacet(event.getFacet());
	}

	@Subscribe
	public void onEventMainThread(OnFundFacetClickedEvent event) {
		getViewState().showFundFacet(event.getFacet());
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
}