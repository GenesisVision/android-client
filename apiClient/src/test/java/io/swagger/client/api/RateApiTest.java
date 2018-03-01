package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;
import io.swagger.client.model.RequestRate;

/**
 * API tests for RateApi
 */
public class RateApiTest
{

	private RateApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(RateApi.class);
	}

	/**
	 * Get rate
	 */
	@Test
	public void apiRatePostTest() {
		RequestRate model = null;
		// RateViewModel response = api.apiRatePost(model);

		// TODO: test validations
	}
}
