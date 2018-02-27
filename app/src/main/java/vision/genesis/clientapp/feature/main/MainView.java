package vision.genesis.clientapp.feature.main;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.InvestmentProgram;

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

	void showWithdrawWallet();

	void showDepositWallet();
}
