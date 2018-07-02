package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.BrokersFilter;
import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.ForgotPasswordViewModel;
import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentProgramRequestsFilter;
import io.swagger.client.model.InvestmentProgramUpdate;
import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.ManagerDashboardProgramsFilter;
import io.swagger.client.model.NewInvestmentRequest;
import io.swagger.client.model.NewTournamentAccountRequest;
import io.swagger.client.model.PasswordModel;
import io.swagger.client.model.RegisterManagerViewModel;
import io.swagger.client.model.ResetPasswordViewModel;
import io.swagger.client.model.TradesFilter;
import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.TwoFactorAuthenticatorConfirm;
import io.swagger.client.model.TwoFactorCodeModel;
import io.swagger.client.model.UpdateProfileViewModel;
import io.swagger.client.model.WalletStatisticFilter;
import io.swagger.client.model.WalletWithdrawRequestModel;

/**
 * API tests for ManagerApi
 */
public class ManagerApiTest
{

	private ManagerApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(ManagerApi.class);
	}

	/**
	 * Create new investment program request
	 */
	@Test
	public void apiManagerAccountNewInvestmentRequestPostTest() {
		String authorization = null;
		NewInvestmentRequest request = null;
		// UUID response = api.apiManagerAccountNewInvestmentRequestPost(authorization, request);

		// TODO: test validations
	}

	/**
	 * Create new tournament investment program request
	 */
	@Test
	public void apiManagerAccountTournamentNewInvestmentRequestPostTest() {
		String authorization = null;
		NewTournamentAccountRequest request = null;
		// UUID response = api.apiManagerAccountTournamentNewInvestmentRequestPost(authorization, request);

		// TODO: test validations
	}

	/**
	 * 2FA confirm
	 */
	@Test
	public void apiManagerAuth2faConfirmPostTest() {
		String authorization = null;
		TwoFactorAuthenticatorConfirm model = null;
		// RecoveryCodesViewModel response = api.apiManagerAuth2faConfirmPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * 2FA create
	 */
	@Test
	public void apiManagerAuth2faCreatePostTest() {
		String authorization = null;
		// TwoFactorAuthenticator response = api.apiManagerAuth2faCreatePost(authorization);

		// TODO: test validations
	}

	/**
	 * 2FA disable
	 */
	@Test
	public void apiManagerAuth2faDisablePostTest() {
		String authorization = null;
		TwoFactorCodeModel model = null;
		// Void response = api.apiManagerAuth2faDisablePost(authorization, model);

		// TODO: test validations
	}

	/**
	 * 2FA status
	 */
	@Test
	public void apiManagerAuth2faGetTest() {
		String authorization = null;
		// TwoFactorStatus response = api.apiManagerAuth2faGet(authorization);

		// TODO: test validations
	}

	/**
	 * 2FA generate new recovery codes
	 */
	@Test
	public void apiManagerAuth2faRecoveryCodesNewPostTest() {
		String authorization = null;
		PasswordModel model = null;
		// RecoveryCodesViewModel response = api.apiManagerAuth2faRecoveryCodesNewPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * 2FA recovery codes
	 */
	@Test
	public void apiManagerAuth2faRecoveryCodesPostTest() {
		String authorization = null;
		PasswordModel model = null;
		// RecoveryCodesViewModel response = api.apiManagerAuth2faRecoveryCodesPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Change password
	 */
	@Test
	public void apiManagerAuthChangePasswordPostTest() {
		String authorization = null;
		ChangePasswordViewModel model = null;
		// Void response = api.apiManagerAuthChangePasswordPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Confirm email after registration
	 */
	@Test
	public void apiManagerAuthConfirmEmailPostTest() {
		String userId = null;
		String code = null;
		// String response = api.apiManagerAuthConfirmEmailPost(userId, code);

		// TODO: test validations
	}

	/**
	 * Forgot password manager
	 */
	@Test
	public void apiManagerAuthForgotPasswordPostTest() {
		ForgotPasswordViewModel model = null;
		// Void response = api.apiManagerAuthForgotPasswordPost(model);

		// TODO: test validations
	}

	/**
	 * Reset password
	 */
	@Test
	public void apiManagerAuthResetPasswordPostTest() {
		ResetPasswordViewModel model = null;
		// String response = api.apiManagerAuthResetPasswordPost(model);

		// TODO: test validations
	}

	/**
	 * Authorize
	 */
	@Test
	public void apiManagerAuthSignInPostTest() {
		LoginViewModel model = null;
		// String response = api.apiManagerAuthSignInPost(model);

		// TODO: test validations
	}

	/**
	 * Register new manager
	 */
	@Test
	public void apiManagerAuthSignUpPostTest() {
		RegisterManagerViewModel model = null;
		// Void response = api.apiManagerAuthSignUpPost(model);

		// TODO: test validations
	}

	/**
	 * Update auth token
	 */
	@Test
	public void apiManagerAuthUpdateTokenGetTest() {
		String authorization = null;
		// String response = api.apiManagerAuthUpdateTokenGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get all enabled trade servers
	 */
	@Test
	public void apiManagerBrokersPostTest() {
		BrokersFilter filter = null;
		// BrokersViewModel response = api.apiManagerBrokersPost(filter);

		// TODO: test validations
	}

	/**
	 * Dashboard programs
	 */
	@Test
	public void apiManagerDashboardProgramsPostTest() {
		String authorization = null;
		ManagerDashboardProgramsFilter filter = null;
		// ManagerInvestmentPrograms response = api.apiManagerDashboardProgramsPost(authorization, filter);

		// TODO: test validations
	}

	/**
	 * Dashboard statistic
	 */
	@Test
	public void apiManagerDashboardStatisticGetTest() {
		String authorization = null;
		// ManagerDashboardStatistic response = api.apiManagerDashboardStatisticGet(authorization);

		// TODO: test validations
	}

	/**
	 * Cancel investment request
	 */
	@Test
	public void apiManagerInvestmentCancelInvestmentRequestPostTest() {
		UUID requestId = null;
		String authorization = null;
		// Void response = api.apiManagerInvestmentCancelInvestmentRequestPost(requestId, authorization);

		// TODO: test validations
	}

	/**
	 * Close existing investment program
	 */
	@Test
	public void apiManagerInvestmentClosePostTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// Void response = api.apiManagerInvestmentClosePost(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Manager deposit in his own investment program
	 */
	@Test
	public void apiManagerInvestmentInvestPostTest() {
		String authorization = null;
		Invest model = null;
		// Void response = api.apiManagerInvestmentInvestPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Get investment program buy token model
	 */
	@Test
	public void apiManagerInvestmentProgramBuyTokensGetTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// InvestmentProgramBuyToken response = api.apiManagerInvestmentProgramBuyTokensGet(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Get manager equity chart
	 */
	@Test
	public void apiManagerInvestmentProgramEquityChartGetTest() {
		UUID investmentProgramId = null;
		String timeFrame = null;
		// TradesChartViewModel response = api.apiManagerInvestmentProgramEquityChartGet(investmentProgramId, timeFrame);

		// TODO: test validations
	}

	/**
	 * Get investment program details by id
	 */
	@Test
	public void apiManagerInvestmentProgramGetTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// InvestmentProgramViewModel response = api.apiManagerInvestmentProgramGet(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Level statistic
	 */
	@Test
	public void apiManagerInvestmentProgramGetlevelstatisticGetTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// ManagerLevelStatistic response = api.apiManagerInvestmentProgramGetlevelstatisticGet(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Close current period
	 */
	@Test
	public void apiManagerInvestmentProgramPeriodClosePostTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// Void response = api.apiManagerInvestmentProgramPeriodClosePost(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Get investment program&#39;s requests
	 */
	@Test
	public void apiManagerInvestmentProgramRequestsPostTest() {
		String authorization = null;
		InvestmentProgramRequestsFilter filter = null;
		// InvestmentProgramRequests response = api.apiManagerInvestmentProgramRequestsPost(authorization, filter);

		// TODO: test validations
	}

	/**
	 * Get manager trades chart
	 */
	@Test
	public void apiManagerInvestmentProgramTradesChartGetTest() {
		UUID investmentProgramId = null;
		// TradesChartViewModel response = api.apiManagerInvestmentProgramTradesChartGet(investmentProgramId);

		// TODO: test validations
	}

	/**
	 * Get manager trade history
	 */
	@Test
	public void apiManagerInvestmentProgramTradesPostTest() {
		TradesFilter filter = null;
		// TradesViewModel response = api.apiManagerInvestmentProgramTradesPost(filter);

		// TODO: test validations
	}

	/**
	 * Update investment program details
	 */
	@Test
	public void apiManagerInvestmentProgramUpdatePostTest() {
		String authorization = null;
		InvestmentProgramUpdate model = null;
		// Void response = api.apiManagerInvestmentProgramUpdatePost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Add to favorites
	 */
	@Test
	public void apiManagerInvestmentProgramsFavoritesAddPostTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// Void response = api.apiManagerInvestmentProgramsFavoritesAddPost(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Remove from favorites
	 */
	@Test
	public void apiManagerInvestmentProgramsFavoritesRemovePostTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// Void response = api.apiManagerInvestmentProgramsFavoritesRemovePost(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Get public investment program&#39;s list
	 */
	@Test
	public void apiManagerInvestmentProgramsPostTest() {
		String authorization = null;
		InvestmentProgramsFilter filter = null;
		// InvestmentProgramsViewModel response = api.apiManagerInvestmentProgramsPost(authorization, filter);

		// TODO: test validations
	}

	/**
	 * Manager withdrawal from his own investment program
	 */
	@Test
	public void apiManagerInvestmentWithdrawPostTest() {
		String authorization = null;
		Invest model = null;
		// Void response = api.apiManagerInvestmentWithdrawPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Platform status
	 */
	@Test
	public void apiManagerPlatformStatusGetTest() {
		// PlatformStatus response = api.apiManagerPlatformStatusGet();

		// TODO: test validations
	}

	/**
	 * Get full profile
	 */
	@Test
	public void apiManagerProfileFullGetTest() {
		String authorization = null;
		// ProfileFullViewModel response = api.apiManagerProfileFullGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get public profile
	 */
	@Test
	public void apiManagerProfilePublicGetTest() {
		UUID userId = null;
		// ProfilePublicViewModel response = api.apiManagerProfilePublicGet(userId);

		// TODO: test validations
	}

	/**
	 * Update profile
	 */
	@Test
	public void apiManagerProfileUpdatePostTest() {
		String authorization = null;
		UpdateProfileViewModel model = null;
		// Void response = api.apiManagerProfileUpdatePost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Get eth address for GVT depositing
	 */
	@Test
	public void apiManagerWalletAddressGetTest() {
		String authorization = null;
		// WalletAddressViewModel response = api.apiManagerWalletAddressGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get user wallets
	 */
	@Test
	public void apiManagerWalletGetTest() {
		String authorization = null;
		// WalletsViewModel response = api.apiManagerWalletGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get user wallet statistic
	 */
	@Test
	public void apiManagerWalletStatisticPostTest() {
		String authorization = null;
		WalletStatisticFilter filter = null;
		// WalletStatistic response = api.apiManagerWalletStatisticPost(authorization, filter);

		// TODO: test validations
	}

	/**
	 * Get user investment programs with tx
	 */
	@Test
	public void apiManagerWalletTransactionsInvestmentProgramsListGetTest() {
		String authorization = null;
		String mask = null;
		// WalletInvestmentPrograms response = api.apiManagerWalletTransactionsInvestmentProgramsListGet(authorization, mask);

		// TODO: test validations
	}

	/**
	 * Get user wallet transactions
	 */
	@Test
	public void apiManagerWalletTransactionsPostTest() {
		String authorization = null;
		TransactionsFilter filter = null;
		// WalletTransactionsViewModel response = api.apiManagerWalletTransactionsPost(authorization, filter);

		// TODO: test validations
	}

	/**
	 * Withdraw request
	 */
	@Test
	public void apiManagerWalletWithdrawRequestPostTest() {
		String authorization = null;
		WalletWithdrawRequestModel request = null;
		// Void response = api.apiManagerWalletWithdrawRequestPost(authorization, request);

		// TODO: test validations
	}
}
