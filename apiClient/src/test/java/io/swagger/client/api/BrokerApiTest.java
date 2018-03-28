package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.InvestmentProgramAccrual;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.ManagerHistoryIpfsHash;
import io.swagger.client.model.NewManager;
import io.swagger.client.model.NewOpenTradesEvent;
import io.swagger.client.model.NewTradeEvent;
import io.swagger.client.model.ResetPasswordViewModel;
import io.swagger.client.model.StartValues;

/**
 * API tests for BrokerApi
 */
public class BrokerApiTest
{

	private BrokerApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(BrokerApi.class);
	}

	/**
	 * Create manager
	 */
	@Test
	public void apiBrokerAccountCreatePostTest() {
		String authorization = null;
		NewManager request = null;
		// UUID response = api.apiBrokerAccountCreatePost(authorization, request);

		// TODO: test validations
	}

	/**
	 * Confirm email after registration
	 */
	@Test
	public void apiBrokerAuthConfirmEmailPostTest() {
		String userId = null;
		String code = null;
		// String response = api.apiBrokerAuthConfirmEmailPost(userId, code);

		// TODO: test validations
	}

	/**
	 * Reset password
	 */
	@Test
	public void apiBrokerAuthResetPasswordPostTest() {
		ResetPasswordViewModel model = null;
		// String response = api.apiBrokerAuthResetPasswordPost(model);

		// TODO: test validations
	}

	/**
	 * Authorize
	 */
	@Test
	public void apiBrokerAuthSignInPostTest() {
		LoginViewModel model = null;
		// String response = api.apiBrokerAuthSignInPost(model);

		// TODO: test validations
	}

	/**
	 * Update auth token
	 */
	@Test
	public void apiBrokerAuthUpdateTokenGetTest() {
		String authorization = null;
		// String response = api.apiBrokerAuthUpdateTokenGet(authorization);

		// TODO: test validations
	}

	/**
	 * Change password
	 */
	@Test
	public void apiBrokerAuthhangePasswordPostTest() {
		String authorization = null;
		ChangePasswordViewModel model = null;
		// Void response = api.apiBrokerAuthhangePasswordPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Get broker initial data
	 */
	@Test
	public void apiBrokerInitDataGetTest() {
		UUID brokerTradeServerId = null;
		String authorization = null;
		// BrokerInitData response = api.apiBrokerInitDataGet(brokerTradeServerId, authorization);

		// TODO: test validations
	}

	/**
	 * Accrue investors&#39; profits
	 */
	@Test
	public void apiBrokerPeriodAccrueProfitsPostTest() {
		String authorization = null;
		InvestmentProgramAccrual accrual = null;
		// Void response = api.apiBrokerPeriodAccrueProfitsPost(authorization, accrual);

		// TODO: test validations
	}

	/**
	 * Close investment period
	 */
	@Test
	public void apiBrokerPeriodClosePostTest() {
		UUID investmentProgramId = null;
		Double currentBalance = null;
		String authorization = null;
		// Void response = api.apiBrokerPeriodClosePost(investmentProgramId, currentBalance, authorization);

		// TODO: test validations
	}

	/**
	 * Close investment program
	 */
	@Test
	public void apiBrokerPeriodProcessClosingProgramPostTest() {
		UUID investmentProgramId = null;
		Double managerBalance = null;
		String authorization = null;
		// Void response = api.apiBrokerPeriodProcessClosingProgramPost(investmentProgramId, managerBalance, authorization);

		// TODO: test validations
	}

	/**
	 * Process investment requests
	 */
	@Test
	public void apiBrokerPeriodProcessInvestmentRequestsPostTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// UUID response = api.apiBrokerPeriodProcessInvestmentRequestsPost(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Update manager token initial price/total supply after loss
	 */
	@Test
	public void apiBrokerPeriodReevaluateManagerTokenPostTest() {
		UUID investmentProgramId = null;
		Double investorLossShare = null;
		String authorization = null;
		// Void response = api.apiBrokerPeriodReevaluateManagerTokenPost(investmentProgramId, investorLossShare, authorization);

		// TODO: test validations
	}

	/**
	 * Set investment period start balance, manager share, manager balance
	 */
	@Test
	public void apiBrokerPeriodSetStartValuesPostTest() {
		String authorization = null;
		StartValues model = null;
		// Void response = api.apiBrokerPeriodSetStartValuesPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Get data for closing investment period
	 */
	@Test
	public void apiBrokerPeriodlosingDataGetTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// ClosePeriodData response = api.apiBrokerPeriodlosingDataGet(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Update manager history ipfs hash
	 */
	@Test
	public void apiBrokerTradesIpfsHashUpdatePostTest() {
		String authorization = null;
		ManagerHistoryIpfsHash data = null;
		// Void response = api.apiBrokerTradesIpfsHashUpdatePost(authorization, data);

		// TODO: test validations
	}

	/**
	 * New trade event
	 */
	@Test
	public void apiBrokerTradesNewPostTest() {
		String authorization = null;
		NewTradeEvent tradeEvent = null;
		// Void response = api.apiBrokerTradesNewPost(authorization, tradeEvent);

		// TODO: test validations
	}

	/**
	 * New open trades event
	 */
	@Test
	public void apiBrokerTradesOpenTradesNewPostTest() {
		String authorization = null;
		NewOpenTradesEvent trades = null;
		// Void response = api.apiBrokerTradesOpenTradesNewPost(authorization, trades);

		// TODO: test validations
	}
}
