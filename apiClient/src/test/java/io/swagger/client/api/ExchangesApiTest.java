package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;

/**
 * API tests for ExchangesApi
 */
public class ExchangesApiTest
{

	private ExchangesApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(ExchangesApi.class);
	}


	/**
	 * Get exchanges for creating trading accounts
	 */
	@Test
	public void getExchangesTest() {
		// ExchangeInfoItemsViewModel response = api.getExchanges();

		// TODO: test validations
	}
}
