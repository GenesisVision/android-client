package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.AccountBalanceChart;
import io.swagger.client.model.AccountProfitPercentCharts;
import io.swagger.client.model.PrivateTradingAccountFull;
import io.swagger.client.model.TradesViewModel;
import retrofit2.http.GET;
import rx.Observable;

public interface TradingaccountApi
{
	/**
	 * Trading account absolute profit chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @return Call&lt;AbsoluteProfitChart&gt;
	 */
	@GET("v2.0/tradingaccount/{id}/charts/profit/absolute")
	Observable<AbsoluteProfitChart> getAbsoluteProfitChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency
	);

	/**
	 * Trading account balance chart
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @return Call&lt;AccountBalanceChart&gt;
	 */
	@GET("v2.0/tradingaccount/{id}/charts/balance")
	Observable<AccountBalanceChart> getBalanceChart(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency
	);

	/**
	 * Trading account profit percent charts
	 *
	 * @param id            (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param maxPointCount (optional)
	 * @param currency      (optional)
	 * @param currencies    (optional)
	 * @return Call&lt;AccountProfitPercentCharts&gt;
	 */
	@GET("v2.0/tradingaccount/{id}/charts/profit/percent")
	Observable<AccountProfitPercentCharts> getProfitPercentCharts(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("MaxPointCount") Integer maxPointCount, @retrofit2.http.Query("Currency") String currency, @retrofit2.http.Query("currencies") List<Object> currencies
	);

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
	 * @return Call&lt;TradesViewModel&gt;
	 */
	@GET("v2.0/tradingaccount/{id}/trades/open")
	Observable<TradesViewModel> getProgramOpenTrades(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Trading account details
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;PrivateTradingAccountFull&gt;
	 */
	@GET("v2.0/tradingaccount/{id}")
	Observable<PrivateTradingAccountFull> getTradingAccountDetails(
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
	 * @return Call&lt;TradesViewModel&gt;
	 */
	@GET("v2.0/tradingaccount/{id}/trades")
	Observable<TradesViewModel> getTradingAccountTrades(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
