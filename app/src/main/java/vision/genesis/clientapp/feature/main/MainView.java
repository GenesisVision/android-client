package vision.genesis.clientapp.feature.main;

import com.arellomobile.mvp.MvpView;

import java.util.UUID;

import io.swagger.client.model.AssetFacet;
import io.swagger.client.model.ExternalKycAccessToken;
import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.Post;
import io.swagger.client.model.TransactionViewModel;
import io.swagger.client.model.UserVerificationStatus;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.managers.KycVerificationManager;
import vision.genesis.clientapp.model.AppUpdateModel;
import vision.genesis.clientapp.model.CopytradingAccountModel;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.OpenTradeModel;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.UserDetailsModel;
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

	void removeFragment(BaseFragment fragment);

	void removeFragmentFromBackstack();

//	void showBottomNavigation(Boolean animate);

//	void hideBottomNavigation();

	void setNavigationItemSelected(int position);

//	void showSignInButton();

//	void hideSignInButton();

	void showLoginActivity();

	void showProgramDetails(ProgramDetailsModel programDetailsModel);

	void showFundDetails(FundDetailsModel fundDetailsModel);

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

	void showUserDetails(UserDetailsModel model);

	void showProgramsRating(AssetFacet facet);

	void showProgramFacet(AssetFacet facet);

	void showFundsChallengeActivity(AssetFacet facet);

	void showFundFacet(AssetFacet facet);

	void showFollowFacet(AssetFacet facet);

	void showOpenTradeDetails(OpenTradeModel model);

	void showEventDetails(InvestmentEventViewModel event);

	void showTradingAccountDetails(TradingAccountDetailsModel tradingAccountDetailsModel);

	void showSocialActivity(String showPage);

	void showSocialActivityWithTag(String hashTag);

	void showMediaActivity();

	void showUsersListActivity();

	void showPostDetails(UUID postId, Post post, boolean showComments);

	void showCreatePostActivity(UUID userId);

	void showCreatePostActivityWithRepost(Post post);

	void showReportPostActivity(Post post);

	void openUrl(String url);

	void showNotificationsActivity();

	void showMediaPostDetails(UUID mediaPostId);

	void showVerificationInfoActivity(UserVerificationStatus verificationStatus);

	void showTerminal(String symbol);

	void startKycProcess(KycVerificationManager manager, ExternalKycAccessToken model);

	void showSnackbarMessage(String message);
}
