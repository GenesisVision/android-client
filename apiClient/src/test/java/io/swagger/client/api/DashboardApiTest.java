package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;

/**
 * API tests for DashboardApi
 */
public class DashboardApiTest
{

	private DashboardApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(DashboardApi.class);
	}


	/**
	 *
	 */
	@Test
	public void getAssetsTest() {
		String authorization = null;
		Integer topAssetsCount = null;
		// DashboardAssets response = api.getAssets(authorization, topAssetsCount);

		// TODO: test validations
	}

	/**
	 *
	 */
	@Test
	public void getChartTest() {
		String authorization = null;
		// Void response = api.getChart(authorization);

		// TODO: test validations
	}

	/**
	 *
	 */
	@Test
	public void getInvestingDetailsTest() {
		String authorization = null;
		String currency = null;
		// DashboardInvestingDetails response = api.getInvestingDetails(authorization, currency);

		// TODO: test validations
	}

	/**
	 * Money distribution in percents. Empty list if no money at all
	 */
	@Test
	public void getPortfolioTest() {
		String authorization = null;
		// DashboardPortfolio response = api.getPortfolio(authorization);

		// TODO: test validations
	}

	/**
	 * Recommended assets to invest (programs, funds and signals). Funds in passed currency
	 */
	@Test
	public void getRecommendationsTest() {
		String authorization = null;
		String currency = null;
		// DashboardRecommendations response = api.getRecommendations(authorization, currency);

		// TODO: test validations
	}

	/**
	 *
	 */
	@Test
	public void getSummaryTest() {
		String authorization = null;
		String currency = null;
		// DashboardSummary response = api.getSummary(authorization, currency);

		// TODO: test validations
	}

	/**
	 *
	 */
	@Test
	public void getTradingDetailsTest() {
		String authorization = null;
		String currency = null;
		// DashboardTradingDetails response = api.getTradingDetails(authorization, currency);

		// TODO: test validations
	}
}
