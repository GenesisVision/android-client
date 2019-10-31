package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;

/**
 * API tests for SearchApi
 */
public class SearchApiTest
{

	private SearchApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(SearchApi.class);
	}


	/**
	 * Program / fund / manager search
	 */
	@Test
	public void searchTest() {
		String authorization = null;
		String mask = null;
		Integer take = null;
		// SearchViewModel response = api.search(authorization, mask, take);

		// TODO: test validations
	}
}
