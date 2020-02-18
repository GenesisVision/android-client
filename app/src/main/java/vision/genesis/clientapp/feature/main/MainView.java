package vision.genesis.clientapp.feature.main;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.AssetFacet;
import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.TransactionViewModel;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.model.AppUpdateModel;
import vision.genesis.clientapp.model.CopytradingAccountModel;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.ManagerDetailsModel;
import vision.genesis.clientapp.model.OpenTradeModel;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.WalletModel;

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

	void showProgramDetails(ProgramDetailsModel programDetailsModel);

	void showFundDetails(FundDetailsModel fundDetailsModel);

	void showWithdrawProgram(ProgramRequest request);

	void showMessageDialog(int imageResourceId, String title, String message, boolean mustRead, MessageBottomSheetDialog.OnButtonClickListener listener);

	void showAppUpdateDialog(AppUpdateModel model);

	void showEnableTwoFactor();

	void showSetupTwoFactorActivity();

	void showDisableTwoFactorActivity();

	void showLockScreen(boolean allowFingerprint);

	void hideSplashScreen();

	void changeThemeWithAnim();

	void showSpecificWallet(WalletModel walletModel);

	void showCopytradingAccountDetails(CopytradingAccountModel model);

	void showTransactionDetails(TransactionViewModel transaction);

	void showManagerDetails(ManagerDetailsModel model);

	void showProgramsRating(AssetFacet facet);

	void showProgramFacet(AssetFacet facet);

	void showFundFacet(AssetFacet facet);

	void showFollowFacet(AssetFacet facet);

	void showOpenTradeDetails(OpenTradeModel model);

	void showEventDetails(InvestmentEventViewModel event);

	void showTradingAccountDetails(TradingAccountDetailsModel tradingAccountDetailsModel);
}
