package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;

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
	 * Value chart
	 */
	@Test
	public void v10InvestorDashboardChartValueGetTest() {
		String authorization = null;
		DateTime from = null;
		DateTime to = null;
		String currency = null;
		// DashboardChartValue response = api.v10InvestorDashboardChartValueGet(authorization, from, to, currency);

		// TODO: test validations
	}

	/**
	 * Portfolio events
	 */
	@Test
	public void v10InvestorDashboardEventsGetTest() {
		String authorization = null;
		DateTime from = null;
		DateTime to = null;
		String currency = null;
		// DashboardPortfolioEvents response = api.v10InvestorDashboardEventsGet(authorization, from, to, currency);

		// TODO: test validations
	}

	/**
	 * Programs list
	 */
	@Test
	public void v10InvestorDashboardProgramListGetTest() {
		String authorization = null;
		String sorting = null;
		DateTime statisticDateFrom = null;
		DateTime statisticDateTo = null;
		Integer skip = null;
		Integer take = null;
		// DashboardProgramsList response = api.v10InvestorDashboardProgramListGet(authorization, sorting, statisticDateFrom, statisticDateTo, skip, take);

		// TODO: test validations
	}
}
