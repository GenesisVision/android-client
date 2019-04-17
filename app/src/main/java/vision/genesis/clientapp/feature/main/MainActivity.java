package vision.genesis.clientapp.feature.main;

import android.animation.ValueAnimator;
import android.app.Activity;
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

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTime;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.FundFacet;
import io.swagger.client.model.ProgramFacet;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.feature.main.app_update.AppUpdateDialog;
import vision.genesis.clientapp.feature.main.facet.funds.FundsFacetActivity;
import vision.genesis.clientapp.feature.main.facet.programs.ProgramsFacetActivity;
import vision.genesis.clientapp.feature.main.fund.FundDetailsActivity;
import vision.genesis.clientapp.feature.main.manager.ManagerDetailsActivity;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsActivity;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramActivity;
import vision.genesis.clientapp.feature.main.rating.ProgramsRatingActivity;
import vision.genesis.clientapp.feature.main.wallet.specific_wallet.SpecificWalletActivity;
import vision.genesis.clientapp.feature.main.wallet.transaction_details.TransactionDetailsActivity;
import vision.genesis.clientapp.feature.pin.check.CheckPinActivity;
import vision.genesis.clientapp.feature.two_factor.disable.DisableTfaActivity;
import vision.genesis.clientapp.feature.two_factor.setup.SetupTfaActivity;
import vision.genesis.clientapp.model.AppUpdateModel;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.ManagerDetailsModel;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.WalletModel;
import vision.genesis.clientapp.model.events.ShowSetupTfaActivityEvent;
import vision.genesis.clientapp.ui.common.BackButtonListener;
import vision.genesis.clientapp.ui.common.BlockScreenHolder;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

public class MainActivity extends MvpAppCompatActivity implements MainView, BlockScreenHolder
{
	public static void startFrom(Activity activity) {
		Intent mainActivityIntent = new Intent(activity, MainActivity.class);
		mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity.startActivity(mainActivityIntent);
		activity.overridePendingTransition(R.anim.hold, R.anim.hold);
	}

	@BindView(R.id.root)
	public View root;

	@BindView(R.id.splashscreen)
	public View splashScreen;

	@BindView(R.id.group_sign_in)
	public View signInGroup;

	@BindView(R.id.block_screen)
	public View blockScreen;

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
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
		AHBottomNavigationItem dashboardItem = new AHBottomNavigationItem(null, R.drawable.icon_dashboard);
		AHBottomNavigationItem investItem = new AHBottomNavigationItem(null, R.drawable.icon_assets);
		AHBottomNavigationItem walletItem = new AHBottomNavigationItem(null, R.drawable.icon_wallet);
		AHBottomNavigationItem settingsItem = new AHBottomNavigationItem(null, R.drawable.icon_profile);

		bottomNavigationView.addItem(dashboardItem);
		bottomNavigationView.addItem(investItem);
		bottomNavigationView.addItem(walletItem);
		bottomNavigationView.addItem(settingsItem);

		bottomNavigationView.setAccentColor(ThemeUtil.getColorByAttrId(this, R.attr.colorAccent));
		bottomNavigationView.setInactiveColor(ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		bottomNavigationView.setDefaultBackgroundColor(ThemeUtil.getColorByAttrId(this, R.attr.colorBackground));

		bottomNavigationView.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

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
	public void showBottomNavigation(Boolean animate) {
		if (animate) {
			Animation signInAnimation = AnimationUtils.loadAnimation(this, R.anim.sign_in_button_slide);
			signInAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
			bottomNavigationView.startAnimation(signInAnimation);
		}
		bottomNavigationView.setVisibility(View.VISIBLE);
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
		signInGroup.setVisibility(View.GONE);
	}

	@Override
	public void showLoginActivity() {
		LoginActivity.startFrom(this);
	}

	@Override
	public void showProgramDetails(ProgramDetailsModel programDetailsModel) {
		ProgramDetailsActivity.startWith(this, programDetailsModel);
	}

	@Override
	public void showFundDetails(FundDetailsModel fundDetailsModel) {
		FundDetailsActivity.startWith(this, fundDetailsModel);
	}

	@Override
	public void showWithdrawProgram(ProgramRequest request) {
		WithdrawProgramActivity.startWith(this, request);
	}

	@Override
	public void showMessageDialog(int imageResourceId, String title, String message, boolean mustRead, MessageBottomSheetDialog.OnButtonClickListener listener) {
		MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
		dialog.show(getSupportFragmentManager(), dialog.getTag());
		dialog.setData(imageResourceId, title, message, mustRead, listener);
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

		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
		dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
	}

	@Override
	public void showSetupTwoFactorActivity() {
		SetupTfaActivity.startFrom(this);
	}

	@Override
	public void showDisableTwoFactorActivity() {
		DisableTfaActivity.startFrom(this);
	}

	@Override
	public void showLockScreen(boolean allowFingerprint) {
		CheckPinActivity.startForResult(this, CheckPinActivity.LOCK_SCREEN_REQUEST_CODE, allowFingerprint);
	}

	@Override
	public void hideSplashScreen() {
		splashScreen.setVisibility(View.GONE);
	}

	@Override
	public void changeThemeWithAnim() {
		ValueAnimator cardColorAnimation = ThemeUtil.getColorAnimator(this, bottomNavigationView.getDefaultBackgroundColor(), R.attr.colorCard);
		cardColorAnimation.addUpdateListener(animator -> bottomNavigationView.setDefaultBackgroundColor((int) animator.getAnimatedValue()));
		cardColorAnimation.start();

		ValueAnimator inactiveColorAnimation = ThemeUtil.getColorAnimator(this, bottomNavigationView.getInactiveColor(), R.attr.colorTextSecondary);
		inactiveColorAnimation.addUpdateListener(animator -> bottomNavigationView.setInactiveColor((int) animator.getAnimatedValue()));
		inactiveColorAnimation.start();

//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//			ValueAnimator statusBarColorAnimation = ThemeUtil.getColorAnimator(this, StatusBarUtil.getColor(this), android.R.attr.statusBarColor);
//			statusBarColorAnimation.addUpdateListener(animator ->
//					StatusBarUtil.setColor(this, (int) animator.getAnimatedValue()));
//			statusBarColorAnimation.start();
//		}
	}

	@Override
	public void showSpecificWallet(WalletModel walletModel) {
		SpecificWalletActivity.startWith(this, walletModel);
	}

	@Override
	public void showTransactionDetails(UUID transactionId, String transactionType, DateTime transactionDate) {
		TransactionDetailsActivity.startWith(this, transactionId, transactionType, transactionDate);
	}

	@Override
	public void showManagerDetails(ManagerDetailsModel model) {
		ManagerDetailsActivity.startWith(this, model);
	}

	@Override
	public void showProgramsRating() {
		ProgramsRatingActivity.startWith(this);
	}

	@Override
	public void showProgramFacet(ProgramFacet facet) {
		ProgramsFacetActivity.startWith(this, facet);
	}

	@Override
	public void showFundFacet(FundFacet facet) {
		FundsFacetActivity.startWith(this, facet);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Timber.d("Activity result: request code: %d result code:  %d", requestCode, resultCode);
		switch (requestCode) {
			case CheckPinActivity.LOCK_SCREEN_REQUEST_CODE:
				if (resultCode == Activity.RESULT_OK) {
					mainPresenter.onCheckPinPassed();
					hideBlock();
				}
				break;
			default:
				super.onActivityResult(requestCode, resultCode, data);
		}
	}

	@Override
	public void showBlock() {
		blockScreen.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideBlock() {
		blockScreen.setVisibility(View.GONE);
	}
}