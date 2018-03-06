package vision.genesis.clientapp.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.AuthActivity;
import vision.genesis.clientapp.feature.main.program.details.ProgramDetailsActivity;
import vision.genesis.clientapp.feature.main.program.filter.ProgramsFiltersActivity;
import vision.genesis.clientapp.feature.main.wallet.deposit.DepositWalletActivity;
import vision.genesis.clientapp.feature.main.wallet.withdraw.WithdrawWalletActivity;
import vision.genesis.clientapp.ui.common.BackButtonListener;

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

	@InjectPresenter
	MainPresenter mainPresenter;

	private BaseFragment currentFragment;

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

		initBottomNavigation();
	}

	@Override
	public void onBackPressed() {
		Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content);
		if (fragment == null
				|| !(fragment instanceof BackButtonListener)
				|| !((BackButtonListener) fragment).onBackPressed()) {
			super.onBackPressed();
		}
	}

	@Override
	public void addFragmentToBackstack(BaseFragment fragment) {
		if (fragmentIsAlreadyRoot(fragment))
			return;
		if (currentFragment != null)
			hideFragment(currentFragment);
		currentFragment = fragment;
		getSupportFragmentManager()
				.beginTransaction()
				.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
				.add(R.id.content, currentFragment, Integer.toString(getFragmentCount()))
				.commit();
	}

	@Override
	public void showFragment(BaseFragment fragment) {
		if (currentFragment != null)
			hideFragment(currentFragment);
		getSupportFragmentManager()
				.beginTransaction()
				.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
				.show(fragment)
				.commit();
		currentFragment = fragment;
		currentFragment.onShow();
	}

	@Override
	public void hideFragment(BaseFragment fragment) {
		BaseFragment previousFragment = getPreviousFragment();
		if (previousFragment != null)
			currentFragment = previousFragment;
		getSupportFragmentManager()
				.beginTransaction()
				.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
				.hide(fragment)
				.commit();
		fragment.onHide();
	}

	@Override
	public void removeFragmentFromBackstack() {
		currentFragment.onHide();
		BaseFragment previousFragment = getPreviousFragment();
		if (previousFragment != null)
			currentFragment = previousFragment;
		getSupportFragmentManager().popBackStack();
	}

	private boolean fragmentIsAlreadyRoot(Fragment fragment) {
		return (currentFragment != null
				&& currentFragment.getClass().getSimpleName().equals(fragment.getClass().getSimpleName()));
	}

	private int getFragmentCount() {
		return getSupportFragmentManager().getBackStackEntryCount();
	}

	@Nullable
	private BaseFragment getFragmentAt(int index) {
		try {
			return getFragmentCount() > 0 ? (BaseFragment) getSupportFragmentManager().findFragmentByTag(Integer.toString(index)) : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private
	@Nullable
	BaseFragment getPreviousFragment() {
		return getFragmentAt(getFragmentCount() - 2);
	}

	public void initBottomNavigation() {
		AHBottomNavigationItem dashboardItem = new AHBottomNavigationItem(getString(R.string.dashboard), R.drawable.ic_dashboard_black_24dp);
		AHBottomNavigationItem investItem = new AHBottomNavigationItem(getString(R.string.programs), R.drawable.ic_attach_money_black_24dp);
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
	public void showProgramFilters() {
		ProgramsFiltersActivity.startFrom(this);
	}

	@Override
	public void showInvestmentProgramDetails(UUID programId) {
		ProgramDetailsActivity.startWith(this, programId);
	}

	@Override
	public void showWithdrawWallet() {
		WithdrawWalletActivity.startWith(this);
	}

	@Override
	public void showDepositWallet() {
		DepositWalletActivity.startWith(this);
	}
}