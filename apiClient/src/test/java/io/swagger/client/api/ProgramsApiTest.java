package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import io.swagger.client.ApiClient;

/**
 * API tests for ProgramsApi
 */
public class ProgramsApiTest
{

	private ProgramsApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(ProgramsApi.class);
	}


	/**
	 * Program balance chart
	 */
	@Test
	public void getProgramBalanceChartTest() {
		UUID id = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		Integer maxPointCount = null;
		String currency = null;
		// ProgramBalanceChart response = api.getProgramBalanceChart(id, dateFrom, dateTo, maxPointCount, currency);

		// TODO: test validations
	}

	/**
	 * Program details
	 */
	@Test
	public void getProgramDetailsTest() {
		String id = null;
		String authorization = null;
		// ProgramDetailsFullOld response = api.getProgramDetails(id, authorization);

		// TODO: test validations
	}

	/**
	 * Program profit chart
	 */
	@Test
	public void getProgramProfitChartTest() {
		UUID id = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		Integer maxPointCount = null;
		String currency = null;
		List<String> currencies = null;
		// ProgramProfitCharts response = api.getProgramProfitChart(id, dateFrom, dateTo, maxPointCount, currency, currencies);

		// TODO: test validations
	}

	/**
	 * Programs list
	 */
	@Test
	public void getProgramsTest() {
		String authorization = null;
		Integer skip = null;
		Integer take = null;
		// ItemsViewModelProgramDetailsList response = api.getPrograms(authorization, skip, take);

		// TODO: test validations
	}
}
