package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.FollowDetailsFull;
import io.swagger.client.model.TradesViewModelOld;
import retrofit2.http.GET;
import rx.Observable;

public interface TradingaccountApi
{
	/**
	 * Trading account open positions
	 *
	 * @param id              (required)
	 * @param authorization   JWT access token (required)
	 * @param sorting         (optional)
	 * @param symbol          (optional)
	 * @param accountId       (optional)
	 * @param accountCurrency (optional)
	 * @param skip            (optional)
	 * @param take            (optional)
	 * @return Call&lt;TradesViewModelOld&gt;
	 */
	@GET("v2.0/tradingaccount/{id}/trades/open")
	Observable<TradesViewModelOld> getProgramOpenTrades(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Trading account details
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;FollowDetailsFull&gt;
	 */
	@GET("v2.0/tradingaccount/{id}")
	Observable<FollowDetailsFull> getTradingAccountDetails(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Trading account trades
	 *
	 * @param id              (required)
	 * @param authorization   JWT access token (required)
	 * @param dateFrom        (optional)
	 * @param dateTo          (optional)
	 * @param symbol          (optional)
	 * @param sorting         (optional)
	 * @param accountId       (optional)
	 * @param accountCurrency (optional)
	 * @param skip            (optional)
	 * @param take            (optional)
	 * @return Call&lt;TradesViewModelOld&gt;
	 */
	@GET("v2.0/tradingaccount/{id}/trades")
	Observable<TradesViewModelOld> getTradingAccountTrades(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
