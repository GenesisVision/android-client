package vision.genesis.clientapp.feature.main;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.model.InvestmentProgram;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public interface MainView extends MvpView
{
	void showBottomNavigation();

	void hideBottomNavigation();

	void setNavigationItemSelected(int position);

	void showSignInButton();

	void hideSignInButton();

	void showAuthActivity();

	void showTradersFilters();

	void showInvestmentProgramDetails(InvestmentProgram program);

	void showDepositWallet();
}
