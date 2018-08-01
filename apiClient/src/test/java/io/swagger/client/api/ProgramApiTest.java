package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;

/**
 * API tests for ProgramApi
 */
public class ProgramApiTest
{

	private ProgramApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(ProgramApi.class);
	}

	/**
	 * Get manager chart
	 */
	@Test
	public void v10ProgramByIdChartGetTest() {
		UUID id = null;
		String timeFrame = null;
		// ProgramChart response = api.v10ProgramByIdChartGet(id, timeFrame);

		// TODO: test validations
	}

	/**
	 * Add to favorites
	 */
	@Test
	public void v10ProgramByIdFavoriteAddPostTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.v10ProgramByIdFavoriteAddPost(id, authorization);

		// TODO: test validations
	}

	/**
	 * Remove from favorites
	 */
	@Test
	public void v10ProgramByIdFavoriteRemovePostTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.v10ProgramByIdFavoriteRemovePost(id, authorization);

		// TODO: test validations
	}

	/**
	 * Program details
	 */
	@Test
	public void v10ProgramByIdGetTest() {
		UUID id = null;
		String authorization = null;
		// ProgramDetailsFull response = api.v10ProgramByIdGet(id, authorization);

		// TODO: test validations
	}

	/**
	 * Get manager trade history
	 */
	@Test
	public void v10ProgramByIdTradesGetTest() {
		UUID id = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		String symbol = null;
		String sorting = null;
		Integer skip = null;
		Integer take = null;
		// TradesViewModel response = api.v10ProgramByIdTradesGet(id, dateFrom, dateTo, symbol, sorting, skip, take);

		// TODO: test validations
	}

	/**
	 * Programs list
	 */
	@Test
	public void v10ProgramListGetTest() {
		String authorization = null;
		Integer levelMin = null;
		Integer levelMax = null;
		Double profitAvgMin = null;
		Double profitAvgMax = null;
		String timeFrame = null;
		String mask = null;
		UUID facetId = null;
		Boolean isFavorite = null;
		String currency = null;
		Integer skip = null;
		Integer take = null;
		// ProgramsList response = api.v10ProgramListGet(authorization, levelMin, levelMax, profitAvgMin, profitAvgMax, timeFrame, mask, facetId, isFavorite, currency, skip, take);

		// TODO: test validations
	}
}
