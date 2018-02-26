package vision.genesis.clientapp.feature.main;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.Screens;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.NewInvestmentSuccessEvent;
import vision.genesis.clientapp.model.events.OnInvestButtonClickedEvent;
import vision.genesis.clientapp.model.events.ShowDepositWalletActivityEvent;
import vision.genesis.clientapp.model.events.ShowFiltersEvent;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;

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
	public Router router;

	private Subscription userSubscription;

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
				showTraders();
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
		router.navigateTo(Screens.DASHBOARD_MAIN);
	}

	private void showTraders() {
		router.navigateTo(Screens.TRADERS);
	}

	private void showWallet() {
		router.navigateTo(Screens.WALLET);
	}

	private void showProfile() {
		router.navigateTo(Screens.PROFILE_MAIN);
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
		getViewState().showTradersFilters();
	}

	@Subscribe
	public void onEventMainThread(ShowInvestmentProgramDetailsEvent event) {
		getViewState().showInvestmentProgramDetails(event.investmentProgram);
	}

	@Subscribe
	public void onEventMainThread(NewInvestmentSuccessEvent event) {
		showDashboard();
	}

	@Subscribe
	public void onEventMainThread(ShowDepositWalletActivityEvent event) {
		getViewState().showDepositWallet();
	}
}