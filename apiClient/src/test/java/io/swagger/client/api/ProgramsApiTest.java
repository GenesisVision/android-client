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
	 * Add to favorites
	 */
	@Test
	public void addToFavoritesTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.addToFavorites(id, authorization);

		// TODO: test validations
	}

	/**
	 * Export periods
	 */
	@Test
	public void exportProgramPeriodsTest() {
		String id = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		Integer numberMin = null;
		Integer numberMax = null;
		String status = null;
		Integer skip = null;
		Integer take = null;
		// String response = api.exportProgramPeriods(id, dateFrom, dateTo, numberMin, numberMax, status, skip, take);

		// TODO: test validations
	}

	/**
	 * Export period financial statistic
	 */
	@Test
	public void exportProgramPeriodsFinStatisticTest() {
		String id = null;
		String authorization = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		Integer numberMin = null;
		Integer numberMax = null;
		String status = null;
		Integer skip = null;
		Integer take = null;
		// String response = api.exportProgramPeriodsFinStatistic(id, authorization, dateFrom, dateTo, numberMin, numberMax, status, skip, take);

		// TODO: test validations
	}

	/**
	 * Export trade history
	 */
	@Test
	public void exportProgramTradesTest() {
		UUID id = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		String symbol = null;
		String sorting = null;
		UUID accountId = null;
		String accountCurrency = null;
		Integer skip = null;
		Integer take = null;
		// String response = api.exportProgramTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take);

		// TODO: test validations
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
		// ProgramDetailsFull response = api.getProgramDetails(id, authorization);

		// TODO: test validations
	}

	/**
	 * Open positions
	 */
	@Test
	public void getProgramOpenTradesTest() {
		UUID id = null;
		String sorting = null;
		String symbol = null;
		UUID accountId = null;
		String accountCurrency = null;
		Integer skip = null;
		Integer take = null;
		// TradesViewModel response = api.getProgramOpenTrades(id, sorting, symbol, accountId, accountCurrency, skip, take);

		// TODO: test validations
	}

	/**
	 * Program periods
	 */
	@Test
	public void getProgramPeriodsTest() {
		String id = null;
		String authorization = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		Integer numberMin = null;
		Integer numberMax = null;
		String status = null;
		Integer skip = null;
		Integer take = null;
		// ProgramPeriodsViewModel response = api.getProgramPeriods(id, authorization, dateFrom, dateTo, numberMin, numberMax, status, skip, take);

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
		List<Object> currencies = null;
		// ProgramProfitCharts response = api.getProgramProfitChart(id, dateFrom, dateTo, maxPointCount, currency, currencies);

		// TODO: test validations
	}

	/**
	 * Signal subscribers
	 */
	@Test
	public void getProgramSubscribersTest() {
		UUID id = null;
		String authorization = null;
		String status = null;
		Integer skip = null;
		Integer take = null;
		// SignalProviderSubscribers response = api.getProgramSubscribers(id, authorization, status, skip, take);

		// TODO: test validations
	}

	/**
	 * Trade history
	 */
	@Test
	public void getProgramTradesTest() {
		UUID id = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		String symbol = null;
		String sorting = null;
		UUID accountId = null;
		String accountCurrency = null;
		Integer skip = null;
		Integer take = null;
		// TradesViewModel response = api.getProgramTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take);

		// TODO: test validations
	}

	/**
	 * Programs list
	 */
	@Test
	public void getProgramsTest() {
		String authorization = null;
		List<String> tags = null;
		String programCurrency = null;
		Integer levelMin = null;
		Integer levelMax = null;
		DateTime statisticDateFrom = null;
		DateTime statisticDateTo = null;
		Integer chartPointsCount = null;
		String chartCurrency = null;
		String facetId = null;
		String mask = null;
		Integer skip = null;
		Integer take = null;
		// ItemsViewModelProgramDetailsList response = api.getPrograms(authorization, tags, programCurrency, levelMin, levelMax, statisticDateFrom, statisticDateTo, chartPointsCount, chartCurrency, facetId, mask, skip, take);

		// TODO: test validations
	}

	/**
	 * Remove from favorites
	 */
	@Test
	public void removeFromFavoritesTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.removeFromFavorites(id, authorization);

		// TODO: test validations
	}
}
