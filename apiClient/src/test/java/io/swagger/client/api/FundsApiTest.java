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
	 * Funds list
	 */
	@Test
	public void v10FundsGetTest() {
		String authorization = null;
		String sorting = null;
		DateTime statisticDateFrom = null;
		DateTime statisticDateTo = null;
		Integer chartPointsCount = null;
		String mask = null;
		UUID facetId = null;
		Boolean isFavorite = null;
		List<UUID> ids = null;
		Integer skip = null;
		Integer take = null;
		// ProgramsList response = api.v10FundsGet(authorization, sorting, statisticDateFrom, statisticDateTo, chartPointsCount, mask, facetId, isFavorite, ids, skip, take);

		// TODO: test validations
	}
}
