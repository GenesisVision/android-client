package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;

/**
 * API tests for TradesApi
 */
public class TradesApiTest
{

	private TradesApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(TradesApi.class);
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiTradesIpfsGetGetTest() {
		String ipfsHashId = null;
		// TradesViewModel response = api.apiTradesIpfsGetGet(ipfsHashId);

		// TODO: test validations
	}
}
