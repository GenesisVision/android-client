package vision.genesis.clientapp.feature.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.feature.main.app_update.AppUpdateDialog;
import vision.genesis.clientapp.feature.main.message.MessageActivity;
import vision.genesis.clientapp.feature.main.program.ProgramInfoActivity;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramActivity;
import vision.genesis.clientapp.feature.main.wallet.deposit.DepositWalletActivity;
import vision.genesis.clientapp.feature.main.wallet.withdraw.WithdrawWalletActivity;
import vision.genesis.clientapp.feature.two_factor.setup.SetupTfaActivity;
import vision.genesis.clientapp.model.AppUpdateModel;
import vision.genesis.clientapp.model.ProgramInfoModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.events.ShowSetupTfaActivityEvent;
import vision.genesis.clientapp.ui.common.BackButtonListener;
import vision.genesis.clientapp.utils.TypefaceUtil;

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

	@BindView(R.id.version)
	public TextView version;

	@BindView(R.id.group_sign_in)
	public View signInGroup;

	@BindView(R.id.bottom_shadow)
	public View bottomShadow;

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

		ButterKnife.bind(this);

		initBottomNavigation();

		version.setText(String.format(Locale.getDefault(), "%s (%d)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
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
				.setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out)
				.add(R.id.content, currentFragment, Integer.toString(getFragmentCount()))
				.commit();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//No call for super(). Bug on API Level > 11.
	}

	@Override
	public void showFragment(BaseFragment fragment) {
		if (currentFragment != null)
			hideFragment(currentFragment);
		getSupportFragmentManager()
				.beginTransaction()
				.setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out)
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
				.setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out)
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

	@Override
	public void setBottomNavigationVisibility(boolean visible) {
		bottomNavigationView.setVisibility(visible ? View.VISIBLE : View.GONE);
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
		AHBottomNavigationItem dashboardItem = new AHBottomNavigationItem(getString(R.string.dashboard).toUpperCase(), R.drawable.dashboard_icon);
		AHBottomNavigationItem investItem = new AHBottomNavigationItem(getString(R.string.programs).toUpperCase(), R.drawable.traders_icon);
		AHBottomNavigationItem walletItem = new AHBottomNavigationItem(getString(R.string.wallet).toUpperCase(), R.drawable.wallet_icon);
		AHBottomNavigationItem profileItem = new AHBottomNavigationItem(getString(R.string.profile).toUpperCase(), R.drawable.profile_icon);

		bottomNavigationView.setTitleTypeface(TypefaceUtil.bold());
		bottomNavigationView.setTitleTextSizeInSp(10, 10);

		bottomNavigationView.addItem(dashboardItem);
		bottomNavigationView.addItem(investItem);
		bottomNavigationView.addItem(walletItem);
		bottomNavigationView.addItem(profileItem);

		bottomNavigationView.setAccentColor(ContextCompat.getColor(this, R.color.white));
		bottomNavigationView.setInactiveColor(ContextCompat.getColor(this, R.color.bottomInactive));
		bottomNavigationView.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));

		bottomNavigationView.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

		bottomNavigationView.setOnTabSelectedListener((position, wasSelected) -> {
			if (!wasSelected) {
				mainPresenter.onBottomMenuSelectionChanged(position);
				return true;
			}
			else {
				return false;
			}
		});
	}

	private void showBottomNavigationWithAnimation() {
		Animation signInAnimation = AnimationUtils.loadAnimation(this, R.anim.sign_in_button_slide);
		signInAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		bottomNavigationView.startAnimation(signInAnimation);
		bottomShadow.startAnimation(signInAnimation);
		bottomNavigationView.setVisibility(View.VISIBLE);
		bottomShadow.setVisibility(View.VISIBLE);
	}


	private void showSignInButtonWithAnimation() {
		Animation signInAnimation = AnimationUtils.loadAnimation(this, R.anim.sign_in_button_slide);
		signInAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		signInGroup.startAnimation(signInAnimation);
		signInGroup.setVisibility(View.VISIBLE);
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
		bottomShadow.setVisibility(View.GONE);
	}

	@Override
	public void showSignInButton() {
		showSignInButtonWithAnimation();
	}

	@Override
	public void hideSignInButton() {
		signInGroup.setVisibility(View.GONE);
	}

	@Override
	public void showLoginActivity() {
		LoginActivity.startFrom(this);
	}

	@Override
	public void showInvestmentProgramDetails(ProgramInfoModel programInfoModel) {
		ProgramInfoActivity.startWith(this, programInfoModel);
	}

	@Override
	public void showWithdrawProgram(ProgramRequest request) {
		WithdrawProgramActivity.startWith(this, request);
	}

	@Override
	public void showMessageActivity(String message, int imageResourceId, boolean mustRead) {
		MessageActivity.startWith(this, message, imageResourceId, mustRead);
	}

	@Override
	public void showWithdrawWallet() {
		WithdrawWalletActivity.startWith(this);
	}

	@Override
	public void showDepositWallet() {
		DepositWalletActivity.startWith(this);
	}

	@Override
	public void showAppUpdateDialog(AppUpdateModel model) {
		AppUpdateDialog dialog = new AppUpdateDialog(this, model);
		dialog.show();
	}

	@Override
	public void showEnableTwoFactor() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(getString(R.string.enable_two_factor_rationale));
		builder.setPositiveButton(getString(R.string.enable), (dialogInterface, i) -> EventBus.getDefault().post(new ShowSetupTfaActivityEvent()));
		builder.setNegativeButton(getString(R.string.no_thanks), (dialogInterface, i) -> dialogInterface.cancel());

		AlertDialog dialog = builder.create();
		dialog.show();

		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
		dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
	}

	@Override
	public void showSetupTwoFactorActivity() {
		SetupTfaActivity.startFrom(this);
	}
}