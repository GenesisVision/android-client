package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.swagger.client.ApiClient;

/**
 * API tests for FollowApi
 */
public class FollowApiTest
{

	private FollowApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(FollowApi.class);
	}


	/**
	 * Follow asset details
	 */
	@Test
	public void getFollowAssetDetailsTest() {
		String id = null;
		String authorization = null;
		// FollowDetailsFull response = api.getFollowAssetDetails(id, authorization);

		// TODO: test validations
	}

	/**
	 * Follow assets
	 */
	@Test
	public void getFollowAssetsTest() {
		String authorization = null;
		String sorting = null;
		String showIn = null;
		List<String> tags = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		Integer chartPointsCount = null;
		String facetId = null;
		String mask = null;
		Boolean showFavorites = null;
		Integer skip = null;
		Integer take = null;
		// ItemsViewModelFollowDetailsList response = api.getFollowAssets(authorization, sorting, showIn, tags, dateFrom, dateTo, chartPointsCount, facetId, mask, showFavorites, skip, take);

		// TODO: test validations
	}
}
