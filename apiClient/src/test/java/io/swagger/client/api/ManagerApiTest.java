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
import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.ManagerDashboardProgramsFilter;
import io.swagger.client.model.NewInvestmentRequest;
import io.swagger.client.model.RegisterManagerViewModel;
import io.swagger.client.model.ResetPasswordViewModel;
import io.swagger.client.model.TradesFilter;
import io.swagger.client.model.TransactionsFilter;
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
	 * Create new investment request
	 */
	@Test
	public void apiManagerAccountNewInvestmentRequestPostTest() {
		String authorization = null;
		NewInvestmentRequest request = null;
		// UUID response = api.apiManagerAccountNewInvestmentRequestPost(authorization, request);

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
	 * Forgot password
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
	 * Change password
	 */
	@Test
	public void apiManagerAuthhangePasswordPostTest() {
		String authorization = null;
		ChangePasswordViewModel model = null;
		// Void response = api.apiManagerAuthhangePasswordPost(authorization, model);

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
	public void apiManagerDashboardProgramsGetTest() {
		String authorization = null;
		ManagerDashboardProgramsFilter filter = null;
		// ManagerInvestmentPrograms response = api.apiManagerDashboardProgramsGet(authorization, filter);

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
	 * Get manager trade history
	 */
	@Test
	public void apiManagerInvestmentProgramTradesPostTest() {
		TradesFilter filter = null;
		// TradesViewModel response = api.apiManagerInvestmentProgramTradesPost(filter);

		// TODO: test validations
	}

	/**
	 * Cancel investment request
	 */
	@Test
	public void apiManagerInvestmentProgramsCancelInvestmentRequestPostTest() {
		UUID requestId = null;
		String authorization = null;
		// Void response = api.apiManagerInvestmentProgramsCancelInvestmentRequestPost(requestId, authorization);

		// TODO: test validations
	}

	/**
	 * Invest in manager
	 */
	@Test
	public void apiManagerInvestmentProgramsInvestPostTest() {
		String authorization = null;
		Invest model = null;
		// WalletsViewModel response = api.apiManagerInvestmentProgramsInvestPost(authorization, model);

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
	 * Withdraw from investment program
	 */
	@Test
	public void apiManagerInvestmentProgramsWithdrawPostTest() {
		String authorization = null;
		Invest model = null;
		// Void response = api.apiManagerInvestmentProgramsWithdrawPost(authorization, model);

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
