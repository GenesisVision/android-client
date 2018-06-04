package vision.genesis.clientapp.feature.main;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.PlatformStatus;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.assets.AssetsFragment;
import vision.genesis.clientapp.feature.main.dashboard.DashboardFragment;
import vision.genesis.clientapp.feature.main.profile.ProfileFragment;
import vision.genesis.clientapp.feature.main.wallet.WalletFragment;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.AppUpdateModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.NewInvestmentSuccessEvent;
import vision.genesis.clientapp.model.events.OnInvestButtonClickedEvent;
import vision.genesis.clientapp.model.events.SetBottomMenuVisibilityEvent;
import vision.genesis.clientapp.model.events.ShowDepositWalletActivityEvent;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
import vision.genesis.clientapp.model.events.ShowMessageActivityEvent;
import vision.genesis.clientapp.model.events.ShowSetupTfaActivityEvent;
import vision.genesis.clientapp.model.events.ShowWithdrawProgramEvent;
import vision.genesis.clientapp.model.events.ShowWithdrawWalletActivityEvent;

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
	public InvestManager investManager;

	private Subscription userSubscription;

	private Subscription platformStatusSubscription;

	private DashboardFragment dashboardFragment;

	private AssetsFragment assetsFragment;

	private WalletFragment walletFragment;

	private ProfileFragment profileFragment;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
		getPlatformStatus();

		getViewState().setNavigationItemSelected(1);
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null)
			userSubscription.unsubscribe();
		if (platformStatusSubscription != null)
			platformStatusSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
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
				showProfile();
				break;
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

	private void showWallet() {
		if (walletFragment == null) {
			walletFragment = new WalletFragment();
			getViewState().addFragmentToBackstack(walletFragment);
		}
		else {
			getViewState().showFragment(walletFragment);
		}
	}

	private void showProfile() {
		if (profileFragment == null) {
			profileFragment = new ProfileFragment();
			getViewState().addFragmentToBackstack(profileFragment);
		}
		else {
			getViewState().showFragment(profileFragment);
		}
	}

	void onSignInButtonClicked() {
		getViewState().showLoginActivity();
	}

	private void getPlatformStatus() {
		platformStatusSubscription = investManager.getPlatformStatus()
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.computation())
				.map(this::checkIfNeedUpdate)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::onPlatformStatusSuccess,
						this::onPlatformStatusError);
	}

	private AppUpdateModel checkIfNeedUpdate(PlatformStatus response) {
		AppUpdateModel model = new AppUpdateModel(response.getAndroidVersion().getLastVersion().getVersionName());
		try {
			int lastVersionCode = Integer.parseInt(response.getAndroidVersion().getLastVersion().getVersionCode());
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

		if (model.needUpdate)
			getViewState().showAppUpdateDialog(model);
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
		if (user == null)
			userLoggedOff();
		else {
			userLoggedOn();
			if (authManager.isNeedShowEnableTwoFactor()) {
				getViewState().showEnableTwoFactor();
				authManager.setEnableTwoFactorAlreadyShown(true);
			}
		}
	}

	private void userLoggedOn() {
		getViewState().showBottomNavigation();
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
	public void onEventMainThread(OnInvestButtonClickedEvent event) {
		getViewState().setNavigationItemSelected(1);
	}

	@Subscribe
	public void onEventMainThread(ShowInvestmentProgramDetailsEvent event) {
		getViewState().showInvestmentProgramDetails(event.programInfoModel);
	}

	@Subscribe
	public void onEventMainThread(ShowWithdrawProgramEvent event) {
		ProgramRequest withdrawalRequest = new ProgramRequest();
		withdrawalRequest.programId = event.programId;
		withdrawalRequest.programName = event.programName;
		withdrawalRequest.available = event.investedTokens;
		getViewState().showWithdrawProgram(withdrawalRequest);
	}

	@Subscribe
	public void onEventMainThread(NewInvestmentSuccessEvent event) {
		getViewState().setNavigationItemSelected(0);
	}

	@Subscribe
	public void onEventMainThread(ShowWithdrawWalletActivityEvent event) {
		getViewState().showWithdrawWallet();
	}

	@Subscribe
	public void onEventMainThread(ShowDepositWalletActivityEvent event) {
		getViewState().showDepositWallet();
	}

	@Subscribe
	public void onEventMainThread(SetBottomMenuVisibilityEvent event) {
		getViewState().setBottomNavigationVisibility(event.visible);
	}

	@Subscribe
	public void onEventMainThread(ShowMessageActivityEvent event) {
		getViewState().showMessageActivity(event.message, event.imageResourceId, event.mustRead);
	}

	@Subscribe
	public void onEventMainThread(ShowSetupTfaActivityEvent event) {
		getViewState().showSetupTwoFactorActivity();
	}
}