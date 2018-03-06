package vision.genesis.clientapp.feature.main;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.dashboard.DashboardFragment;
import vision.genesis.clientapp.feature.main.profile.ProfileFragment;
import vision.genesis.clientapp.feature.main.program.list.ProgramsListFragment;
import vision.genesis.clientapp.feature.main.wallet.WalletFragment;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.NewInvestmentSuccessEvent;
import vision.genesis.clientapp.model.events.OnInvestButtonClickedEvent;
import vision.genesis.clientapp.model.events.ShowDepositWalletActivityEvent;
import vision.genesis.clientapp.model.events.ShowFiltersEvent;
import vision.genesis.clientapp.model.events.ShowInvestProgramEvent;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
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

	private Subscription userSubscription;

	private DashboardFragment dashboardFragment;

	private ProgramsListFragment programsListFragment;

	private WalletFragment walletFragment;

	private ProfileFragment profileFragment;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();

		getViewState().setNavigationItemSelected(1);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (userSubscription != null)
			userSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);
	}

	void onBottomMenuSelectionChanged(int position) {
		switch (position) {
			case 0:
				showDashboard();
				break;
			case 1:
				showPrograms();
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

	private void showPrograms() {
		if (programsListFragment == null) {
			programsListFragment = new ProgramsListFragment();
			getViewState().addFragmentToBackstack(programsListFragment);
		}
		else {
			getViewState().showFragment(programsListFragment);
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
		getViewState().showAuthActivity();
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
		else
			userLoggedOn();
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
	public void onEventMainThread(ShowFiltersEvent event) {
		getViewState().showProgramFilters();
	}

	@Subscribe
	public void onEventMainThread(ShowInvestmentProgramDetailsEvent event) {
		getViewState().showInvestmentProgramDetails(event.programId);
	}

	@Subscribe
	public void onEventMainThread(ShowInvestProgramEvent event) {
		getViewState().showInvestProgram(event.programId, event.programName);
	}

	@Subscribe
	public void onEventMainThread(ShowWithdrawProgramEvent event) {
		getViewState().showWithdrawProgram(event.programId, event.programName);
	}

	@Subscribe
	public void onEventMainThread(NewInvestmentSuccessEvent event) {
		showDashboard();
	}

	@Subscribe
	public void onEventMainThread(ShowWithdrawWalletActivityEvent event) {
		getViewState().showWithdrawWallet();
	}

	@Subscribe
	public void onEventMainThread(ShowDepositWalletActivityEvent event) {
		getViewState().showDepositWallet();
	}
}