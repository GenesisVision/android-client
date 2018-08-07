package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;

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
	public void v10RateByFromByToGetTest() {
		String from = null;
		String to = null;
		// RateViewModel response = api.v10RateByFromByToGet(from, to);

		// TODO: test validations
	}
}
