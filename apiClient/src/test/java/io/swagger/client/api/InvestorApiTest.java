package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.ForgotPasswordViewModel;
import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentProgramRequestsFilter;
import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.PasswordModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.ResetPasswordViewModel;
import io.swagger.client.model.TradesFilter;
import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.TwoFactorAuthenticatorConfirm;
import io.swagger.client.model.TwoFactorCodeModel;
import io.swagger.client.model.UpdateProfileViewModel;
import io.swagger.client.model.WalletStatisticFilter;
import io.swagger.client.model.WalletWithdrawRequestModel;

/**
 * API tests for InvestorApi
 */
public class InvestorApiTest
{

	private InvestorApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(InvestorApi.class);
	}

	/**
	 * 2FA confirm
	 */
	@Test
	public void apiInvestorAuth2faConfirmPostTest() {
		String authorization = null;
		TwoFactorAuthenticatorConfirm model = null;
		// RecoveryCodesViewModel response = api.apiInvestorAuth2faConfirmPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * 2FA create
	 */
	@Test
	public void apiInvestorAuth2faCreatePostTest() {
		String authorization = null;
		// TwoFactorAuthenticator response = api.apiInvestorAuth2faCreatePost(authorization);

		// TODO: test validations
	}

	/**
	 * 2FA disable
	 */
	@Test
	public void apiInvestorAuth2faDisablePostTest() {
		String authorization = null;
		TwoFactorCodeModel model = null;
		// Void response = api.apiInvestorAuth2faDisablePost(authorization, model);

		// TODO: test validations
	}

	/**
	 * 2FA status
	 */
	@Test
	public void apiInvestorAuth2faGetTest() {
		String authorization = null;
		// TwoFactorStatus response = api.apiInvestorAuth2faGet(authorization);

		// TODO: test validations
	}

	/**
	 * 2FA generate new recovery codes
	 */
	@Test
	public void apiInvestorAuth2faRecoveryCodesNewPostTest() {
		String authorization = null;
		PasswordModel model = null;
		// RecoveryCodesViewModel response = api.apiInvestorAuth2faRecoveryCodesNewPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * 2FA recovery codes
	 */
	@Test
	public void apiInvestorAuth2faRecoveryCodesPostTest() {
		String authorization = null;
		PasswordModel model = null;
		// RecoveryCodesViewModel response = api.apiInvestorAuth2faRecoveryCodesPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Change password
	 */
	@Test
	public void apiInvestorAuthChangePasswordPostTest() {
		String authorization = null;
		ChangePasswordViewModel model = null;
		// Void response = api.apiInvestorAuthChangePasswordPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Confirm email after registration
	 */
	@Test
	public void apiInvestorAuthConfirmEmailPostTest() {
		String userId = null;
		String code = null;
		// String response = api.apiInvestorAuthConfirmEmailPost(userId, code);

		// TODO: test validations
	}

	/**
	 * Forgot password investor
	 */
	@Test
	public void apiInvestorAuthForgotPasswordPostTest() {
		ForgotPasswordViewModel model = null;
		// Void response = api.apiInvestorAuthForgotPasswordPost(model);

		// TODO: test validations
	}

	/**
	 * Reset password
	 */
	@Test
	public void apiInvestorAuthResetPasswordPostTest() {
		ResetPasswordViewModel model = null;
		// String response = api.apiInvestorAuthResetPasswordPost(model);

		// TODO: test validations
	}

	/**
	 * Authorize
	 */
	@Test
	public void apiInvestorAuthSignInPostTest() {
		LoginViewModel model = null;
		// String response = api.apiInvestorAuthSignInPost(model);

		// TODO: test validations
	}

	/**
	 * Register new investor
	 */
	@Test
	public void apiInvestorAuthSignUpPostTest() {
		RegisterInvestorViewModel model = null;
		// Void response = api.apiInvestorAuthSignUpPost(model);

		// TODO: test validations
	}

	/**
	 * Update auth token
	 */
	@Test
	public void apiInvestorAuthUpdateTokenGetTest() {
		String authorization = null;
		// String response = api.apiInvestorAuthUpdateTokenGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get investor dashboard
	 */
	@Test
	public void apiInvestorDashboardGetTest() {
		String authorization = null;
		String sorting = null;
		Integer equityChartLength = null;
		// InvestorDashboard response = api.apiInvestorDashboardGet(authorization, sorting, equityChartLength);

		// TODO: test validations
	}

	/**
	 * Get investment program buy token model
	 */
	@Test
	public void apiInvestorInvestmentProgramBuyTokensGetTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// InvestmentProgramBuyToken response = api.apiInvestorInvestmentProgramBuyTokensGet(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Get manager equity chart
	 */
	@Test
	public void apiInvestorInvestmentProgramEquityChartGetTest() {
		UUID investmentProgramId = null;
		String timeFrame = null;
		// TradesChartViewModel response = api.apiInvestorInvestmentProgramEquityChartGet(investmentProgramId, timeFrame);

		// TODO: test validations
	}

	/**
	 * Get investment program details by id
	 */
	@Test
	public void apiInvestorInvestmentProgramGetTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// InvestmentProgramViewModel response = api.apiInvestorInvestmentProgramGet(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Get manager open trades
	 */
	@Test
	public void apiInvestorInvestmentProgramOpenTradesPostTest() {
		String authorization = null;
		TradesFilter filter = null;
		// OpenTradesViewModel response = api.apiInvestorInvestmentProgramOpenTradesPost(authorization, filter);

		// TODO: test validations
	}

	/**
	 * Get investment program&#39;s requests
	 */
	@Test
	public void apiInvestorInvestmentProgramRequestsPostTest() {
		String authorization = null;
		InvestmentProgramRequestsFilter filter = null;
		// InvestmentProgramRequests response = api.apiInvestorInvestmentProgramRequestsPost(authorization, filter);

		// TODO: test validations
	}

	/**
	 * Get manager trades chart
	 */
	@Test
	public void apiInvestorInvestmentProgramTradesChartGetTest() {
		UUID investmentProgramId = null;
		// TradesChartViewModel response = api.apiInvestorInvestmentProgramTradesChartGet(investmentProgramId);

		// TODO: test validations
	}

	/**
	 * Get manager trade history
	 */
	@Test
	public void apiInvestorInvestmentProgramTradesPostTest() {
		TradesFilter filter = null;
		// TradesViewModel response = api.apiInvestorInvestmentProgramTradesPost(filter);

		// TODO: test validations
	}

	/**
	 * Cancel investment request
	 */
	@Test
	public void apiInvestorInvestmentProgramsCancelInvestmentRequestPostTest() {
		UUID requestId = null;
		String authorization = null;
		// Void response = api.apiInvestorInvestmentProgramsCancelInvestmentRequestPost(requestId, authorization);

		// TODO: test validations
	}

	/**
	 * Add to favorites
	 */
	@Test
	public void apiInvestorInvestmentProgramsFavoritesAddPostTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// Void response = api.apiInvestorInvestmentProgramsFavoritesAddPost(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Remove from favorites
	 */
	@Test
	public void apiInvestorInvestmentProgramsFavoritesRemovePostTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// Void response = api.apiInvestorInvestmentProgramsFavoritesRemovePost(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Invest in manager
	 */
	@Test
	public void apiInvestorInvestmentProgramsInvestPostTest() {
		String authorization = null;
		Invest model = null;
		// WalletsViewModel response = api.apiInvestorInvestmentProgramsInvestPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Get public investment program&#39;s list
	 */
	@Test
	public void apiInvestorInvestmentProgramsPostTest() {
		String authorization = null;
		InvestmentProgramsFilter filter = null;
		// InvestmentProgramsViewModel response = api.apiInvestorInvestmentProgramsPost(authorization, filter);

		// TODO: test validations
	}

	/**
	 * Withdraw from investment program
	 */
	@Test
	public void apiInvestorInvestmentProgramsWithdrawPostTest() {
		String authorization = null;
		Invest model = null;
		// Void response = api.apiInvestorInvestmentProgramsWithdrawPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Platform status
	 */
	@Test
	public void apiInvestorPlatformStatusGetTest() {
		// PlatformStatus response = api.apiInvestorPlatformStatusGet();

		// TODO: test validations
	}

	/**
	 * Get full profile
	 */
	@Test
	public void apiInvestorProfileFullGetTest() {
		String authorization = null;
		// ProfileFullViewModel response = api.apiInvestorProfileFullGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get public profile
	 */
	@Test
	public void apiInvestorProfilePublicGetTest() {
		UUID userId = null;
		// ProfilePublicViewModel response = api.apiInvestorProfilePublicGet(userId);

		// TODO: test validations
	}

	/**
	 * Update profile
	 */
	@Test
	public void apiInvestorProfileUpdatePostTest() {
		String authorization = null;
		UpdateProfileViewModel model = null;
		// Void response = api.apiInvestorProfileUpdatePost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Get eth address for GVT depositing
	 */
	@Test
	public void apiInvestorWalletAddressGetTest() {
		String authorization = null;
		// WalletAddressViewModel response = api.apiInvestorWalletAddressGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get user wallets
	 */
	@Test
	public void apiInvestorWalletGetTest() {
		String authorization = null;
		// WalletsViewModel response = api.apiInvestorWalletGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get user wallet statistic
	 */
	@Test
	public void apiInvestorWalletStatisticPostTest() {
		String authorization = null;
		WalletStatisticFilter filter = null;
		// WalletStatistic response = api.apiInvestorWalletStatisticPost(authorization, filter);

		// TODO: test validations
	}

	/**
	 * Get user investment programs with tx
	 */
	@Test
	public void apiInvestorWalletTransactionsInvestmentProgramsListGetTest() {
		String authorization = null;
		String mask = null;
		// WalletInvestmentPrograms response = api.apiInvestorWalletTransactionsInvestmentProgramsListGet(authorization, mask);

		// TODO: test validations
	}

	/**
	 * Get user wallet transactions
	 */
	@Test
	public void apiInvestorWalletTransactionsPostTest() {
		String authorization = null;
		TransactionsFilter filter = null;
		// WalletTransactionsViewModel response = api.apiInvestorWalletTransactionsPost(authorization, filter);

		// TODO: test validations
	}

	/**
	 * Withdraw request
	 */
	@Test
	public void apiInvestorWalletWithdrawRequestPostTest() {
		String authorization = null;
		WalletWithdrawRequestModel request = null;
		// Void response = api.apiInvestorWalletWithdrawRequestPost(authorization, request);

		// TODO: test validations
	}
}
