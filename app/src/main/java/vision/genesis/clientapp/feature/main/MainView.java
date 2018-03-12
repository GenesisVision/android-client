package vision.genesis.clientapp.feature.main;

import com.arellomobile.mvp.MvpView;

import java.util.UUID;

import vision.genesis.clientapp.feature.BaseFragment;
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

	void showBottomNavigation();

	void hideBottomNavigation();

	void setNavigationItemSelected(int position);

	void showSignInButton();

	void hideSignInButton();

	void showAuthActivity();

	void showProgramFilters();

	void showInvestmentProgramDetails(UUID programId);

	void showInvestProgram(UUID programId, String programName);

	void showWithdrawProgram(ProgramRequest request);

	void showWithdrawWallet();

	void showDepositWallet();
}
