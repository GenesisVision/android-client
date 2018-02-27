package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.BrokersFilter;
import io.swagger.client.model.Invest;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.NewInvestmentRequest;
import io.swagger.client.model.RegisterManagerViewModel;
import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.UpdateProfileViewModel;
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
     *
     *
     */
    @Test
    public void apiManagerAuthSignUpPostTest() {
	    RegisterManagerViewModel model = null;
	    // Void response = api.apiManagerAuthSignUpPost(model);

	    // TODO: test validations
    }

	/**
	 * Update auth token
	 *
	 *
	 */
	@Test
	public void apiManagerAuthUpdateTokenGetTest() {
		String authorization = null;
		// String response = api.apiManagerAuthUpdateTokenGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get all enabled trade servers
	 *
	 *
	 */
	@Test
	public void apiManagerBrokersPostTest() {
		BrokersFilter filter = null;
		// BrokersViewModel response = api.apiManagerBrokersPost(filter);

		// TODO: test validations
	}

	/**
	 * Cancel investment request
	 *
	 *
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
	 *
	 *
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
	 *
	 *
	 */
	@Test
	public void apiManagerInvestmentInvestPostTest() {
		String authorization = null;
		Invest model = null;
		// Void response = api.apiManagerInvestmentInvestPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Manager withdrawal from his own investment program
	 *
	 *
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
	 *
	 *
	 */
	@Test
	public void apiManagerProfileFullGetTest() {
		String authorization = null;
		// ProfileFullViewModel response = api.apiManagerProfileFullGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get public profile
	 *
	 *
	 */
	@Test
	public void apiManagerProfilePublicGetTest() {
		UUID userId = null;
		// ProfilePublicViewModel response = api.apiManagerProfilePublicGet(userId);

		// TODO: test validations
	}

	/**
	 * Update profile
	 *
	 *
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
	 *
	 *
	 */
	@Test
	public void apiManagerWalletAddressGetTest() {
		String authorization = null;
		// WalletAddressViewModel response = api.apiManagerWalletAddressGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get user wallets
	 *
	 *
	 */
	@Test
	public void apiManagerWalletGetTest() {
		String authorization = null;
		// WalletsViewModel response = api.apiManagerWalletGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get user wallet transactions
	 *
	 *
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
	 *
	 *
	 */
	@Test
	public void apiManagerWalletWithdrawRequestPostTest() {
		String authorization = null;
		WalletWithdrawRequestModel request = null;
		// Void response = api.apiManagerWalletWithdrawRequestPost(authorization, request);

		// TODO: test validations
	}
}
