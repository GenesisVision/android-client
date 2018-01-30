package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;
import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentsFilter;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.TransactionsFilter;

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
		// InvestorDashboard response = api.apiInvestorDashboardGet(authorization);

		// TODO: test validations
	}

	/**
	 * Invest in manager
	 */
	@Test
	public void apiInvestorInvestmentsInvestPostTest() {
		String authorization = null;
		Invest model = null;
		// ProfileShortViewModel response = api.apiInvestorInvestmentsInvestPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Get investments by filter
	 */
	@Test
	public void apiInvestorInvestmentsPostTest() {
		InvestmentsFilter filter = null;
		// InvestmentProgramsViewModel response = api.apiInvestorInvestmentsPost(filter);

		// TODO: test validations
	}

	/**
	 * Withdraw from investment program
	 */
	@Test
	public void apiInvestorInvestmentsWithdrawPostTest() {
		String authorization = null;
		Invest model = null;
		// Void response = api.apiInvestorInvestmentsWithdrawPost(authorization, model);

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
	 * Get short profile
	 */
	@Test
	public void apiInvestorProfileGetTest() {
		String authorization = null;
		// ProfileShortViewModel response = api.apiInvestorProfileGet(authorization);

		// TODO: test validations
	}

	/**
	 * Update profile
	 */
	@Test
	public void apiInvestorProfileUpdatePostTest() {
		String authorization = null;
		ProfileFullViewModel model = null;
		// Void response = api.apiInvestorProfileUpdatePost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Deposit
	 */
	@Test
	public void apiInvestorWalletDepositPostTest() {
		String authorization = null;
		// Void response = api.apiInvestorWalletDepositPost(authorization);

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
	 * Withdraw
	 */
	@Test
	public void apiInvestorWalletWithdrawPostTest() {
		String authorization = null;
		// Void response = api.apiInvestorWalletWithdrawPost(authorization);

		// TODO: test validations
	}
}
