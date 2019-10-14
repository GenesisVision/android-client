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
	public void getDashboardTest() {
		String authorization = null;
		// Void response = api.getDashboard(authorization);

		// TODO: test validations
	}
}
