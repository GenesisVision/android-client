package vision.genesis.clientapp.feature.main;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.AppUpdateModel;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.ProgramRequest;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public interface MainView extends MvpView
{
	void addFragmentToBackstack(BaseFragment fragment);

	void showFragment(BaseFragment fragment);

	void hideFragment(BaseFragment fragment);

	void removeFragmentFromBackstack();

	void showBottomNavigation(Boolean animate);

	void hideBottomNavigation();

	void setNavigationItemSelected(int position);

	void showSignInButton();

	void hideSignInButton();

	void showLoginActivity();

	void showInvestmentProgramDetails(ProgramDetailsModel programDetailsModel);

	void showWithdrawProgram(ProgramRequest request);

	void showMessageActivity(String message, int imageResourceId, boolean mustRead);

	void showWithdrawWallet();

	void showDepositWallet();

	void showAppUpdateDialog(AppUpdateModel model);

	void showEnableTwoFactor();

	void showSetupTwoFactorActivity();

	void showDisableTwoFactorActivity();

	void showSetPinActivity();

	void showLockScreen(boolean allowFingerprint);

	void hideSplashScreen();

	void changeThemeWithAnim();
}
