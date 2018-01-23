package vision.genesis.clientapp.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;

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
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.Screens;
import vision.genesis.clientapp.feature.auth.AuthActivity;
import vision.genesis.clientapp.feature.main.dashboard.DashboardFragment;
import vision.genesis.clientapp.feature.main.invest.InvestFragment;
import vision.genesis.clientapp.feature.main.profile.ProfileFragment;
import vision.genesis.clientapp.feature.main.wallet.WalletFragment;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

public class MainActivity extends MvpAppCompatActivity implements MainView
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

	@InjectPresenter
	MainPresenter mainPresenter;

	private Navigator navigator = new SupportAppNavigator(this, R.id.content)
	{

		@Override
		protected Intent createActivityIntent(String screenKey, Object data) {
			return null;
		}

		@Override
		protected Fragment createFragment(String screenKey, Object data) {
			switch (screenKey) {
				case Screens.DASHBOARD:
					return new DashboardFragment();
				case Screens.INVEST:
					return new InvestFragment();
				case Screens.WALLET:
					return new WalletFragment();
				case Screens.PROFILE:
					return new ProfileFragment();
			}
			return null;
		}

		@Override
		protected void setupFragmentTransactionAnimation(
				Command command,
				Fragment currentFragment,
				Fragment nextFragment,
				FragmentTransaction fragmentTransaction) {
			//setup animation
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
			navigator.applyCommand(new Replace(Screens.INVEST, 1));
		}
	}

	@Override
	public void onResumeFragments() {
		super.onResumeFragments();
		navigatorHolder.setNavigator(navigator);
	}

	public void onPause() {
		navigatorHolder.removeNavigator();
		super.onPause();
	}

	@Override
	public void initBottomNavigation() {
		AHBottomNavigationItem dashboardItem = new AHBottomNavigationItem(getString(R.string.dashboard), R.drawable.ic_dashboard_black_24dp);
		AHBottomNavigationItem investItem = new AHBottomNavigationItem(getString(R.string.invest), R.drawable.ic_attach_money_black_24dp);
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

		bottomNavigationView.setVisibility(View.VISIBLE);
	}

	@Override
	public void setNavigationItemSelected(int position) {
		bottomNavigationView.setCurrentItem(position);
	}

	@Override
	public void showSignInButton() {
		signInButton.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideSignInButton() {
		signInButton.setVisibility(View.GONE);
	}

	@Override
	public void showAuthActivity() {
		AuthActivity.startFrom(this);
	}
}