package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.NewManager;

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
	 * Close investment period
	 */
	@Test
	public void apiBrokerPeriodCloseGetTest() {
		UUID investmentProgramId = null;
		String authorization = null;
		// Void response = api.apiBrokerPeriodCloseGet(investmentProgramId, authorization);

		// TODO: test validations
	}

	/**
	 * Set investment period start balance
	 */
	@Test
	public void apiBrokerPeriodSetStartBalanceGetTest() {
		UUID periodId = null;
		Double balance = null;
		String authorization = null;
		// Void response = api.apiBrokerPeriodSetStartBalanceGet(periodId, balance, authorization);

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
}
