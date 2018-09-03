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
	 * Program chart
	 */
	@Test
	public void v10ProgramsByIdChartGetTest() {
		UUID id = null;
		DateTime chartDateFrom = null;
		DateTime chartDateTo = null;
		// ProgramChart response = api.v10ProgramsByIdChartGet(id, chartDateFrom, chartDateTo);

		// TODO: test validations
	}

	/**
	 * Add to favorites
	 */
	@Test
	public void v10ProgramsByIdFavoriteAddPostTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.v10ProgramsByIdFavoriteAddPost(id, authorization);

		// TODO: test validations
	}

	/**
	 * Remove from favorites
	 */
	@Test
	public void v10ProgramsByIdFavoriteRemovePostTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.v10ProgramsByIdFavoriteRemovePost(id, authorization);

		// TODO: test validations
	}

	/**
	 * Program details
	 */
	@Test
	public void v10ProgramsByIdGetTest() {
		UUID id = null;
		String authorization = null;
		// ProgramDetailsFull response = api.v10ProgramsByIdGet(id, authorization);

		// TODO: test validations
	}

	/**
	 * Trade history
	 */
	@Test
	public void v10ProgramsByIdTradesGetTest() {
		UUID id = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		String symbol = null;
		String sorting = null;
		Integer skip = null;
		Integer take = null;
		// TradesViewModel response = api.v10ProgramsByIdTradesGet(id, dateFrom, dateTo, symbol, sorting, skip, take);

		// TODO: test validations
	}

	/**
	 * Programs list
	 */
	@Test
	public void v10ProgramsGetTest() {
		String authorization = null;
		Integer levelMin = null;
		Integer levelMax = null;
		Double profitAvgMin = null;
		Double profitAvgMax = null;
		DateTime statisticDateFrom = null;
		DateTime statisticDateTo = null;
		String sorting = null;
		String mask = null;
		UUID facetId = null;
		Boolean isFavorite = null;
		String currency = null;
		List<UUID> ids = null;
		Integer skip = null;
		Integer take = null;
		// ProgramsList response = api.v10ProgramsGet(authorization, levelMin, levelMax, profitAvgMin, profitAvgMax, statisticDateFrom, statisticDateTo, sorting, mask, facetId, isFavorite, currency, ids, skip, take);

		// TODO: test validations
	}

	/**
	 * Programs sets
	 */
	@Test
	public void v10ProgramsSetsGetTest() {
		// ProgramSets response = api.v10ProgramsSetsGet();

		// TODO: test validations
	}
}
