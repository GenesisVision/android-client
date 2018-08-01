package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;

/**
 * API tests for OefApi
 */
public class OefApiTest
{

	private OefApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(OefApi.class);
	}

	/**
	 * Get all supported assets for OEFs
	 */
	@Test
	public void v10OefAssetAllGetTest() {
		String authorization = null;
		// PlatformAssets response = api.v10OefAssetAllGet(authorization);

		// TODO: test validations
	}
}
