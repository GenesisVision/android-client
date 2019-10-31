package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.swagger.client.ApiClient;

/**
 * API tests for CopytradingApi
 */
public class CopytradingApiTest
{

	private CopytradingApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(CopytradingApi.class);
	}


	/**
	 * Get GV Follow assets
	 */
	@Test
	public void getSignalAssetsTest() {
		String authorization = null;
		List<String> tags = null;
		DateTime statisticDateFrom = null;
		DateTime statisticDateTo = null;
		Integer chartPointsCount = null;
		String chartCurrency = null;
		String facetId = null;
		String mask = null;
		Integer skip = null;
		Integer take = null;
		// ItemsViewModelCopyTradingAccountInfo response = api.getSignalAssets(authorization, tags, statisticDateFrom, statisticDateTo, chartPointsCount, chartCurrency, facetId, mask, skip, take);

		// TODO: test validations
	}
}
