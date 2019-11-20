package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;

/**
 * API tests for TradingaccountApi
 */
public class TradingaccountApiTest
{

	private TradingaccountApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(TradingaccountApi.class);
	}


	/**
	 * Trading account open positions
	 */
	@Test
	public void getProgramOpenTradesTest() {
		UUID id = null;
		String authorization = null;
		String sorting = null;
		String symbol = null;
		UUID accountId = null;
		String accountCurrency = null;
		Integer skip = null;
		Integer take = null;
		// TradesViewModelOld response = api.getProgramOpenTrades(id, authorization, sorting, symbol, accountId, accountCurrency, skip, take);

		// TODO: test validations
	}

	/**
	 * Trading account details
	 */
	@Test
	public void getTradingAccountDetailsTest() {
		UUID id = null;
		String authorization = null;
		// FollowDetailsFull response = api.getTradingAccountDetails(id, authorization);

		// TODO: test validations
	}

	/**
	 * Trading account trades
	 */
	@Test
	public void getTradingAccountTradesTest() {
		UUID id = null;
		String authorization = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		String symbol = null;
		String sorting = null;
		UUID accountId = null;
		String accountCurrency = null;
		Integer skip = null;
		Integer take = null;
		// TradesViewModelOld response = api.getTradingAccountTrades(id, authorization, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take);

		// TODO: test validations
	}
}
