package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;

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
	 * Wallet summary
	 */
	@Test
	public void v10WalletByCurrencyGetTest() {
		String currency = null;
		String authorization = null;
		// WalletSummary response = api.v10WalletByCurrencyGet(currency, authorization);

		// TODO: test validations
	}

	/**
	 * Wallet transactions
	 */
	@Test
	public void v10WalletTransactionsGetTest() {
		String authorization = null;
		UUID assetId = null;
		DateTime from = null;
		DateTime to = null;
		String assetType = null;
		String txAction = null;
		Integer skip = null;
		Integer take = null;
		// WalletSummary response = api.v10WalletTransactionsGet(authorization, assetId, from, to, assetType, txAction, skip, take);

		// TODO: test validations
	}
}
