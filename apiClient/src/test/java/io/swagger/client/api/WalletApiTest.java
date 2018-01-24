package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;
import io.swagger.client.model.TransactionsFilter;

/**
 * API tests for WalletApi
 */
public class WalletApiTest
{

	private WalletApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(WalletApi.class);
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiInvestorWalletTransactionsPostTest() {
		TransactionsFilter filter = null;
		// WalletTransactionsViewModel response = api.apiInvestorWalletTransactionsPost(filter);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void apiManagerWalletTransactionsPostTest() {
		TransactionsFilter filter = null;
		// WalletTransactionsViewModel response = api.apiManagerWalletTransactionsPost(filter);

		// TODO: test validations
	}
}
