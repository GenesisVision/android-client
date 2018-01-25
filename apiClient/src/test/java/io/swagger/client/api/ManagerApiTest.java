package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.BrokersFilter;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.NewInvestmentRequest;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.RegisterManagerViewModel;
import io.swagger.client.model.TransactionsFilter;

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
	public void apiManagerAuthConfirmEmailGetTest() {
		String userId = null;
		String code = null;
		// Void response = api.apiManagerAuthConfirmEmailGet(userId, code);

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
	 * Close existing investment program
	 */
	@Test
	public void apiManagerInvestmentCloseGetTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// Void response = api.apiManagerInvestmentCloseGet(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Get investment program with statistic by id
	 */
	@Test
	public void apiManagerInvestmentGetTest() {
		UUID investmentProgramId = null;
		// InvestmentProgramViewModel response = api.apiManagerInvestmentGet(investmentProgramId);

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
	 * Get short profile
	 */
	@Test
	public void apiManagerProfileGetTest() {
		String authorization = null;
		// ProfileShortViewModel response = api.apiManagerProfileGet(authorization);

		// TODO: test validations
	}

	/**
	 * Update profile
	 */
	@Test
	public void apiManagerProfileUpdatePostTest() {
		String authorization = null;
		ProfileFullViewModel model = null;
		// Void response = api.apiManagerProfileUpdatePost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Deposit
	 */
	@Test
	public void apiManagerWalletDepositPostTest() {
		String authorization = null;
		// Void response = api.apiManagerWalletDepositPost(authorization);

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
	 * Withdraw
	 */
	@Test
	public void apiManagerWalletWithdrawPostTest() {
		String authorization = null;
		// Void response = api.apiManagerWalletWithdrawPost(authorization);

		// TODO: test validations
	}
}
