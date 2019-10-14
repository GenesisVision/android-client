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
	 * Platform captcha details
	 */
	@Test
	public void getCaptchaModelTest() {
		String route = null;
		String client = null;
		String version = null;
		// CaptchaDetails response = api.getCaptchaModel(route, client, version);

		// TODO: test validations
	}

	/**
	 * Platform info
	 */
	@Test
	public void getPlatformInfoTest() {
		// PlatformInfo response = api.getPlatformInfo();

		// TODO: test validations
	}
}
