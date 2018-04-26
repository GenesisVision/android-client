package vision.genesis.clientapp.feature.main;

import com.arellomobile.mvp.MvpView;

import java.util.UUID;

import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.ProgramInfoModel;
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

	void setBottomNavigationVisibility(boolean visible);

	void showBottomNavigation();

	void hideBottomNavigation();

	void setNavigationItemSelected(int position);

	void showSignInButton();

	void hideSignInButton();

	void showLoginActivity();

	void showProgramFilters();

	void showInvestmentProgramDetails(ProgramInfoModel programInfoModel);

	void showInvestProgram(UUID programId, String programName);

	void showWithdrawProgram(ProgramRequest request);

	void showMessageActivity(String message, int imageResourceId, boolean mustRead);

	void showWithdrawWallet();

	void showDepositWallet();
}
