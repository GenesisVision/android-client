package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.swagger.client.ApiClient;

/**
 * API tests for RateApi
 */
public class RateApiTest
{

	private RateApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(RateApi.class);
	}


	/**
	 * Get rate
	 */
	@Test
	public void getRateTest() {
		String from = null;
		String to = null;
		// RateModel response = api.getRate(from, to);

		// TODO: test validations
	}

	/**
	 * Get rate by exchange
	 */
	@Test
	public void getRateExchangeTest() {
		String exchange = null;
		String from = null;
		String to = null;
		// RateModel response = api.getRateExchange(exchange, from, to);

		// TODO: test validations
	}

	/**
	 * Get rates
	 */
	@Test
	public void getRatesTest() {
		List<String> from = null;
		List<String> to = null;
		// RatesModel response = api.getRates(from, to);

		// TODO: test validations
	}
}
