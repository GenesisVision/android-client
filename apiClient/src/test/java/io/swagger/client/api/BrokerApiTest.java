package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.BrokersFilter;

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
	 *
	 *
	 *
	 */
	@Test
	public void apiBrokerInitDataGetTest() {
		UUID brokerTradeServerId = null;
		// BrokerInitData response = api.apiBrokerInitDataGet(brokerTradeServerId);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiBrokerPeriodCloseGetTest() {
		UUID investmentProgramId = null;
		// Void response = api.apiBrokerPeriodCloseGet(investmentProgramId);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiBrokerPeriodSetStartBalanceGetTest() {
		UUID periodId = null;
		Double balance = null;
		// Void response = api.apiBrokerPeriodSetStartBalanceGet(periodId, balance);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiBrokerPeriodlosingDataGetTest() {
		UUID investmentProgramId = null;
		// ClosePeriodData response = api.apiBrokerPeriodlosingDataGet(investmentProgramId);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiManagerBrokersPostTest() {
		BrokersFilter filter = null;
		// BrokersViewModel response = api.apiManagerBrokersPost(filter);

		// TODO: test validations
	}
}
