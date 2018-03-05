package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.AccountCreated;
import io.swagger.client.model.NewTrade;

/**
 * API tests for TradeserverApi
 */
public class TradeserverApiTest
{

	private TradeserverApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(TradeserverApi.class);
	}

	/**
	 * Init data for trade server wrapper
	 */
	@Test
	public void apiTradeserverInitDataGetTest() {
		UUID tradeServerId = null;
		// TradeServerViewModel response = api.apiTradeserverInitDataGet(tradeServerId);

		// TODO: test validations
	}

	/**
	 * New trade event
	 */
	@Test
	public void apiTradeserverNewTradePostTest() {
		NewTrade trade = null;
		// Void response = api.apiTradeserverNewTradePost(trade);

		// TODO: test validations
	}

	/**
	 * Store trade account
	 */
	@Test
	public void apiTradeserverTradeAccountCreatedPostTest() {
		List<AccountCreated> accounts = null;
		// Void response = api.apiTradeserverTradeAccountCreatedPost(accounts);

		// TODO: test validations
	}
}
