package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.TradesFilter;
import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.UpdateProfileViewModel;
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
	 * Confirm email after registration
	 */
	@Test
	public void apiInvestorAuthConfirmEmailGetTest() {
		String userId = null;
		String code = null;
		// Void response = api.apiInvestorAuthConfirmEmailGet(userId, code);

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
     *
     *
     */
    @Test
    public void apiInvestorAuthUpdateTokenGetTest() {
	    String authorization = null;
	    // String response = api.apiInvestorAuthUpdateTokenGet(authorization);

	    // TODO: test validations
    }

	/**
	 * Get investor dashboard
	 *
	 *
	 */
	@Test
	public void apiInvestorDashboardGetTest() {
		String authorization = null;
		// InvestorDashboard response = api.apiInvestorDashboardGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get investment program details by id
	 *
	 *
	 */
	@Test
	public void apiInvestorInvestmentProgramGetTest() {
		UUID investmentProgramId = null;
		// InvestmentProgramViewModel response = api.apiInvestorInvestmentProgramGet(investmentProgramId);

		// TODO: test validations
	}

	/**
	 * Get manager open trades
	 *
	 *
	 */
	@Test
	public void apiInvestorInvestmentProgramOpenTradesPostTest() {
		String authorization = null;
		TradesFilter filter = null;
		// OpenTradesViewModel response = api.apiInvestorInvestmentProgramOpenTradesPost(authorization, filter);

		// TODO: test validations
	}

	/**
	 * Get manager trade history
	 */
	@Test
	public void apiInvestorInvestmentProgramTradesPostTest() {
		String authorization = null;
		TradesFilter filter = null;
		// TradesViewModel response = api.apiInvestorInvestmentProgramTradesPost(authorization, filter);

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
	 *
	 *
	 */
	@Test
	public void apiInvestorInvestmentProgramsPostTest() {
		InvestmentProgramsFilter filter = null;
		// InvestmentProgramsViewModel response = api.apiInvestorInvestmentProgramsPost(filter);

		// TODO: test validations
	}

	/**
	 * Withdraw from investment program
	 *
	 *
	 */
	@Test
	public void apiInvestorInvestmentProgramsWithdrawPostTest() {
		String authorization = null;
		Invest model = null;
		// Void response = api.apiInvestorInvestmentProgramsWithdrawPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Get full profile
	 *
	 *
	 */
	@Test
	public void apiInvestorProfileFullGetTest() {
		String authorization = null;
		// ProfileFullViewModel response = api.apiInvestorProfileFullGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get public profile
	 *
	 *
	 */
	@Test
	public void apiInvestorProfilePublicGetTest() {
		UUID userId = null;
		// ProfilePublicViewModel response = api.apiInvestorProfilePublicGet(userId);

		// TODO: test validations
	}

	/**
	 * Update profile
	 *
	 *
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
	 *
	 *
	 */
	@Test
	public void apiInvestorWalletAddressGetTest() {
		String authorization = null;
		// WalletAddressViewModel response = api.apiInvestorWalletAddressGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get user wallets
	 *
	 *
	 */
	@Test
	public void apiInvestorWalletGetTest() {
		String authorization = null;
		// WalletsViewModel response = api.apiInvestorWalletGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get user wallet transactions
	 *
	 *
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
	 *
	 *
	 */
	@Test
	public void apiInvestorWalletWithdrawRequestPostTest() {
		String authorization = null;
		WalletWithdrawRequestModel request = null;
		// Void response = api.apiInvestorWalletWithdrawRequestPost(authorization, request);

		// TODO: test validations
	}
}
