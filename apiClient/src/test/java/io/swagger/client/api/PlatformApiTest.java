package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;

/**
 * API tests for PlatformApi
 */
public class PlatformApiTest
{

	private PlatformApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(PlatformApi.class);
	}

	/**
	 * Platform info
	 */
	@Test
	public void v10PlatformInfoGetTest() {
		// PlatformInfo response = api.v10PlatformInfoGet();

		// TODO: test validations
	}
}
