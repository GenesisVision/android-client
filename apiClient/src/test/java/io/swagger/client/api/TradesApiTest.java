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
		String ipfsHashId = null;
		// TradesViewModel response = api.apiTradesIpfsHistoryGet(ipfsHashId);

        // TODO: test validations
	}
}
