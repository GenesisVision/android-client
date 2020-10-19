package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.TradingPlatformBinanceOrdersMode;

/**
 * API tests for TradingplatformApi
 */
public class TradingplatformApiTest
{

	private TradingplatformApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(TradingplatformApi.class);
	}


	/**
	 * Add account favorite symbol
	 */
	@Test
	public void addTradingPlatformFavoriteSymbolTest() {
		UUID id = null;
		String symbol = null;
		// Void response = api.addTradingPlatformFavoriteSymbol(id, symbol);

		// TODO: test validations
	}

	/**
	 * Account history
	 */
	@Test
	public void getTradingPlatformBinanceOrdersTest() {
		UUID accountId = null;
		TradingPlatformBinanceOrdersMode mode = null;
		Integer skip = null;
		Integer take = null;
		// BinanceRawOrderItemsViewModel response = api.getTradingPlatformBinanceOrders(accountId, mode, skip, take);

		// TODO: test validations
	}

	/**
	 * Get account favorite symbols
	 */
	@Test
	public void getTradingPlatformFavoriteSymbolsTest() {
		UUID id = null;
		// StringItemsViewModel response = api.getTradingPlatformFavoriteSymbols(id);

		// TODO: test validations
	}

	/**
	 * Remove account favorite symbol
	 */
	@Test
	public void removeTradingPlatformFavoriteSymbolTest() {
		UUID id = null;
		String symbol = null;
		// Void response = api.removeTradingPlatformFavoriteSymbol(id, symbol);

		// TODO: test validations
	}
}
