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
	 * Get trades by IPFS hash id
	 */
	@Test
	public void apiTradesIpfsHistoryGetTest() {
		String type = null;
		String ipfsHashId = null;
		// TradesViewModel response = api.apiTradesIpfsHistoryGet(type, ipfsHashId);

		// TODO: test validations
	}
}
