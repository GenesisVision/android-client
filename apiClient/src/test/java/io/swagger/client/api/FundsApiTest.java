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
	 * Fund details
	 */
	@Test
	public void getFundDetailsTest() {
		String id = null;
		String authorization = null;
		String currency = null;
		// FundDetailsFull response = api.getFundDetails(id, authorization, currency);

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
		List<Object> currencies = null;
		Integer chartAssetsCount = null;
		// FundProfitCharts response = api.getFundProfitChart(id, dateFrom, dateTo, maxPointCount, currency, currencies, chartAssetsCount);

		// TODO: test validations
	}

	/**
	 * Funds list
	 */
	@Test
	public void getFundsTest() {
		String authorization = null;
		List<String> assets = null;
		DateTime statisticDateFrom = null;
		DateTime statisticDateTo = null;
		Integer chartPointsCount = null;
		String chartCurrency = null;
		String facetId = null;
		String mask = null;
		Integer skip = null;
		Integer take = null;
		// ItemsViewModelFundDetailsList response = api.getFunds(authorization, assets, statisticDateFrom, statisticDateTo, chartPointsCount, chartCurrency, facetId, mask, skip, take);

		// TODO: test validations
	}

	/**
	 * Get history of asset part update requests
	 */
	@Test
	public void getReallocatingHistoryTest() {
		UUID id = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		Integer skip = null;
		Integer take = null;
		// ItemsViewModelReallocationModel response = api.getReallocatingHistory(id, dateFrom, dateTo, skip, take);

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
