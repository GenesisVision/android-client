package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;
import io.swagger.client.model.KycCallback;

/**
 * API tests for KycApi
 */
public class KycApiTest
{

	private KycApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(KycApi.class);
	}

	/**
	 *
	 */
	@Test
	public void v10KycCallbackPostTest() {
		KycCallback model = null;
		// String response = api.v10KycCallbackPost(model);

		// TODO: test validations
	}
}
