package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;

/**
 * API tests for BrokersApi
 */
public class BrokersApiTest
{

	private BrokersApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(BrokersApi.class);
	}

	/**
	 * Get all trade servers
	 */
	@Test
	public void v10BrokersGetTest() {
		// BrokersInfo response = api.v10BrokersGet();

		// TODO: test validations
	}
}
