package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;
import io.swagger.client.model.DExchangeRequest;

/**
 * API tests for DexchangeApi
 */
public class DexchangeApiTest
{

	private DexchangeApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(DexchangeApi.class);
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void v10DexchangeNodePostTest() {
		String authorization = null;
		DExchangeRequest model = null;
		// DExchangeResponse response = api.v10DexchangeNodePost(authorization, model);

		// TODO: test validations
	}
}
