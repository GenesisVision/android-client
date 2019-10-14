package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import io.swagger.client.ApiClient;

/**
 * API tests for FundsApi
 */
public class FundsApiTest
{

	private FundsApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(FundsApi.class);
	}


	/**
	 * Fund balance chart
	 */
	@Test
	public void getFundBalanceChartTest() {
		UUID id = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		Integer maxPointCount = null;
		String currency = null;
		// FundBalanceChart response = api.getFundBalanceChart(id, dateFrom, dateTo, maxPointCount, currency);

		// TODO: test validations
	}

	/**
	 * Fund profit chart
	 */
	@Test
	public void getFundProfitChartTest() {
		UUID id = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		Integer maxPointCount = null;
		String currency = null;
		List<String> currencies = null;
		Integer chartAssetsCount = null;
		// FundProfitCharts response = api.getFundProfitChart(id, dateFrom, dateTo, maxPointCount, currency, currencies, chartAssetsCount);

		// TODO: test validations
	}
}
