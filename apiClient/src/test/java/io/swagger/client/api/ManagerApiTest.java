package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;

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
	 * Manager events
	 */
	@Test
	public void v10ManagerEventsGetTest() {
		String authorization = null;
		UUID assetId = null;
		DateTime from = null;
		DateTime to = null;
		String type = null;
		String assetType = null;
		// ManagerPortfolioEvents response = api.v10ManagerEventsGet(authorization, assetId, from, to, type, assetType);

		// TODO: test validations
	}

	/**
	 * Manager funds
	 */
	@Test
	public void v10ManagerFundsGetTest() {
		String authorization = null;
		String sorting = null;
		DateTime from = null;
		DateTime to = null;
		Integer pointsCount = null;
		Integer skip = null;
		Integer take = null;
		// ManagerFunds response = api.v10ManagerFundsGet(authorization, sorting, from, to, pointsCount, skip, take);

		// TODO: test validations
	}

	/**
	 * Manager dashboard
	 */
	@Test
	public void v10ManagerGetTest() {
		String authorization = null;
		Integer eventsTake = null;
		Integer requestsSkip = null;
		Integer requestsTake = null;
		// ManagerDashboard response = api.v10ManagerGet(authorization, eventsTake, requestsSkip, requestsTake);

		// TODO: test validations
	}

	/**
	 * Manager programs
	 */
	@Test
	public void v10ManagerProgramsGetTest() {
		String authorization = null;
		String sorting = null;
		DateTime from = null;
		DateTime to = null;
		Integer pointsCount = null;
		Integer skip = null;
		Integer take = null;
		// ManagerPrograms response = api.v10ManagerProgramsGet(authorization, sorting, from, to, pointsCount, skip, take);

		// TODO: test validations
	}
}
