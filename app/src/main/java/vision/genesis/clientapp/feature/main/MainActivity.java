package vision.genesis.clientapp.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.Screens;
import vision.genesis.clientapp.feature.auth.AuthActivity;
import vision.genesis.clientapp.feature.main.bottom_navigation.DashboardMainFragment;
import vision.genesis.clientapp.feature.main.bottom_navigation.ProfileMainFragment;
import vision.genesis.clientapp.feature.main.bottom_navigation.RouterProvider;
import vision.genesis.clientapp.feature.main.traders.TradersFragment;
import vision.genesis.clientapp.feature.main.traders.details.TraderDetailsActivity;
import vision.genesis.clientapp.feature.main.traders.filter.TradersFiltersActivity;
import vision.genesis.clientapp.feature.main.wallet.WalletFragment;
import vision.genesis.clientapp.feature.main.wallet.deposit.DepositWalletActivity;
import vision.genesis.clientapp.model.InvestmentProgram;
import vision.genesis.clientapp.ui.common.BackButtonListener;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

public class MainActivity extends MvpAppCompatActivity implements MainView, RouterProvider

{
	public static void startFrom(Context context) {
		Intent mainActivityIntent = new Intent(context, MainActivity.class);
		mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(mainActivityIntent);
	}

	@BindView(R.id.button_sign_in)
	public View signInButton;

	@BindView(R.id.bottom_navigation)
	public AHBottomNavigation bottomNavigationView;

	@Inject
	public NavigatorHolder navigatorHolder;

	@Inject
	Router router;

	@InjectPresenter
	MainPresenter mainPresenter;

	private DashboardMainFragment dashboardMainFragment;

	private TradersFragment tradersFragment;

	private WalletFragment walletFragment;

	private ProfileMainFragment profileMainFragment;

	private Navigator navigator = new SupportAppNavigator(this, R.id.content)
	{
		@Override
		protected Intent createActivityIntent(String screenKey, Object data) {
			return null;
		}

		@Override
		protected Fragment createFragment(String screenKey, Object data) {
			switch (screenKey) {
				case Screens.DASHBOARD_MAIN:
					if (dashboardMainFragment == null)
						dashboardMainFragment = new DashboardMainFragment();
					return dashboardMainFragment;
				case Screens.TRADERS:
					if (tradersFragment == null)
						tradersFragment = new TradersFragment();
					return tradersFragment;
				case Screens.WALLET:
					if (walletFragment == null)
						walletFragment = new WalletFragment();
					return walletFragment;
				case Screens.PROFILE_MAIN:
					if (profileMainFragment == null)
						profileMainFragment = new ProfileMainFragment();
					return profileMainFragment;
			}
			return null;
		}

		@Override
		protected void setupFragmentTransactionAnimation(
				Command command,
				Fragment currentFragment,
				Fragment nextFragment,
				FragmentTransaction fragmentTransaction) {
			fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
		}
	};

	@OnClick(R.id.button_sign_in)
	public void onSignInButtonClicked() {
		mainPresenter.onSignInButtonClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GenesisVisionApplication.getComponent().inject(this);

		ButterKnife.bind(this);

		if (savedInstanceState == null) {
			navigator.applyCommand(new Replace(Screens.TRADERS, 1));
		}

		initBottomNavigation();
	}

	@Override
	public void onResumeFragments() {
		super.onResumeFragments();
		navigatorHolder.setNavigator(navigator);
	}

	@Override
	public void onPause() {
		navigatorHolder.removeNavigator();
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content);
		if (fragment != null
				&& fragment instanceof BackButtonListener
				&& ((BackButtonListener) fragment).onBackPressed()) {
			return;
		}
		else {
			super.onBackPressed();
		}
	}

	public void initBottomNavigation() {
		AHBottomNavigationItem dashboardItem = new AHBottomNavigationItem(getString(R.string.dashboard), R.drawable.ic_dashboard_black_24dp);
		AHBottomNavigationItem investItem = new AHBottomNavigationItem(getString(R.string.traders), R.drawable.ic_attach_money_black_24dp);
		AHBottomNavigationItem walletItem = new AHBottomNavigationItem(getString(R.string.wallet), R.drawable.ic_account_balance_wallet_black_24dp);
		AHBottomNavigationItem profileItem = new AHBottomNavigationItem(getString(R.string.profile), R.drawable.ic_person_black_24dp);

		bottomNavigationView.addItem(dashboardItem);
		bottomNavigationView.addItem(investItem);
		bottomNavigationView.addItem(walletItem);
		bottomNavigationView.addItem(profileItem);

		bottomNavigationView.setAccentColor(ContextCompat.getColor(this, R.color.colorPrimary));
		bottomNavigationView.setInactiveColor(ContextCompat.getColor(this, R.color.colorAccent));
		bottomNavigationView.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.white));

		bottomNavigationView.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

		bottomNavigationView.setOnTabSelectedListener((position, wasSelected) -> {
			mainPresenter.onBottomMenuSelectionChanged(position);
			return true;
		});
	}

	private void showBottomNavigationWithAnimation() {
		Animation signInAnimation = AnimationUtils.loadAnimation(this, R.anim.sign_in_button_slide);
		signInAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		bottomNavigationView.startAnimation(signInAnimation);
		bottomNavigationView.setVisibility(View.VISIBLE);
	}


	private void showSignInButtonWithAnimation() {
		Animation signInAnimation = AnimationUtils.loadAnimation(this, R.anim.sign_in_button_slide);
		signInAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		signInButton.startAnimation(signInAnimation);
		signInButton.setVisibility(View.VISIBLE);
	}

	@Override
	public void setNavigationItemSelected(int position) {
		bottomNavigationView.setCurrentItem(position);
	}

	@Override
	public void showBottomNavigation() {
		bottomNavigationView.setCurrentItem(0);
		showBottomNavigationWithAnimation();
	}

	@Override
	public void hideBottomNavigation() {
		bottomNavigationView.setVisibility(View.GONE);
	}

	@Override
	public void showSignInButton() {
		showSignInButtonWithAnimation();
	}

	@Override
	public void hideSignInButton() {
		signInButton.setVisibility(View.GONE);
	}

	@Override
	public void showAuthActivity() {
		AuthActivity.startFrom(this);
	}

	@Override
	public void showTradersFilters() {
		TradersFiltersActivity.startFrom(this);
	}

	@Override
	public void showInvestmentProgramDetails(InvestmentProgram program) {
		TraderDetailsActivity.startWith(this, program);
	}

	@Override
	public void showDepositWallet() {
		DepositWalletActivity.startWith(this);
	}

	@Override
	public Router getRouter() {
		return router;
	}
}