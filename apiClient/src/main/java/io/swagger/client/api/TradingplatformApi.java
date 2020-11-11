package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.BinanceRaw24HPrice;
import io.swagger.client.model.BinanceRawAccountInfo;
import io.swagger.client.model.BinanceRawCancelOrder;
import io.swagger.client.model.BinanceRawCancelOrderId;
import io.swagger.client.model.BinanceRawExchangeInfo;
import io.swagger.client.model.BinanceRawKlineInterval;
import io.swagger.client.model.BinanceRawKlineItemsViewModel;
import io.swagger.client.model.BinanceRawOrderBook;
import io.swagger.client.model.BinanceRawOrderItemsViewModel;
import io.swagger.client.model.BinanceRawPlaceOrder;
import io.swagger.client.model.BinanceRawRecentTrade;
import io.swagger.client.model.Currency;
import io.swagger.client.model.StringItemsViewModel;
import io.swagger.client.model.TimestampDate;
import io.swagger.client.model.TradingPlatformBinanceOrdersMode;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface TradingplatformApi
{
	/**
	 * Add account favorite symbol
	 *
	 * @param id     (required)
	 * @param symbol (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/tradingplatform/accounts/{id}/symbols/favorite/{symbol}/add")
	Observable<Void> addFavoriteSymbol(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("symbol") String symbol
	);

	/**
	 * Cancel all orders
	 *
	 * @param accountId (optional)
	 * @param symbol    (optional)
	 * @return Call&lt;List&lt;BinanceRawCancelOrderId&gt;&gt;
	 */
	@POST("v2.0/tradingplatform/binance/spot/orders/cancel/all")
	Observable<List<BinanceRawCancelOrderId>> cancelAllOrders(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("symbol") String symbol
	);

	/**
	 * Cancel order
	 *
	 * @param accountId (optional)
	 * @param symbol    (optional)
	 * @param orderId   (optional)
	 * @return Call&lt;BinanceRawCancelOrder&gt;
	 */
	@POST("v2.0/tradingplatform/binance/spot/orders/cancel")
	Observable<BinanceRawCancelOrder> cancelOrder(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("orderId") String orderId
	);

	/**
	 * Get 24H price
	 *
	 * @param symbol (required)
	 * @return Call&lt;BinanceRaw24HPrice&gt;
	 */
	@GET("v2.0/tradingplatform/binance/market/{symbol}/ticker/24hr")
	Observable<BinanceRaw24HPrice> get24HPrice(
			@retrofit2.http.Path("symbol") String symbol
	);

	/**
	 * Get 24H prices
	 *
	 * @return Call&lt;List&lt;BinanceRaw24HPrice&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/market/ticker/24hr")
	Observable<List<BinanceRaw24HPrice>> get24HPrices();


	/**
	 * @param accountId (optional)
	 * @param currency  (optional)
	 * @return Call&lt;BinanceRawAccountInfo&gt;
	 */
	@GET("v2.0/tradingplatform/binance/account")
	Observable<BinanceRawAccountInfo> getAccountInfo(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("currency") Currency currency
	);

	/**
	 * Exchange info
	 *
	 * @return Call&lt;BinanceRawExchangeInfo&gt;
	 */
	@GET("v2.0/tradingplatform/binance/server/info")
	Observable<BinanceRawExchangeInfo> getExchangeInfo();


	/**
	 * Server time
	 *
	 * @return Call&lt;TimestampDate&gt;
	 */
	@GET("v2.0/tradingplatform/binance/server/time")
	Observable<TimestampDate> getExchangeTime();


	/**
	 * Get account favorite symbols
	 *
	 * @param id (required)
	 * @return Call&lt;StringItemsViewModel&gt;
	 */
	@GET("v2.0/tradingplatform/accounts/{id}/symbols/favorite")
	Observable<StringItemsViewModel> getFavoriteSymbols(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Get klines
	 *
	 * @param symbol    (required)
	 * @param interval  (optional)
	 * @param startTime (optional)
	 * @param endTime   (optional)
	 * @param limit     (optional)
	 * @return Call&lt;BinanceRawKlineItemsViewModel&gt;
	 */
	@GET("v2.0/tradingplatform/binance/market/{symbol}/klines")
	Observable<BinanceRawKlineItemsViewModel> getKlines(
			@retrofit2.http.Path("symbol") String symbol, @retrofit2.http.Query("interval") BinanceRawKlineInterval interval, @retrofit2.http.Query("startTime") DateTime startTime, @retrofit2.http.Query("endTime") DateTime endTime, @retrofit2.http.Query("limit") Integer limit
	);

	/**
	 * Open positions
	 *
	 * @param accountId (optional)
	 * @return Call&lt;BinanceRawOrderItemsViewModel&gt;
	 */
	@GET("v2.0/tradingplatform/binance/spot/orders")
	Observable<BinanceRawOrderItemsViewModel> getOpenOrders(
			@retrofit2.http.Query("accountId") UUID accountId
	);

	/**
	 * Get order book
	 *
	 * @param symbol (required)
	 * @param limit  (optional)
	 * @return Call&lt;BinanceRawOrderBook&gt;
	 */
	@GET("v2.0/tradingplatform/binance/market/{symbol}/depth")
	Observable<BinanceRawOrderBook> getOrderBook(
			@retrofit2.http.Path("symbol") String symbol, @retrofit2.http.Query("limit") Integer limit
	);

	/**
	 * Get symbol recent trades
	 *
	 * @param symbol (required)
	 * @param limit  (optional)
	 * @return Call&lt;List&lt;BinanceRawRecentTrade&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/market/{symbol}/trades/recent")
	Observable<List<BinanceRawRecentTrade>> getSymbolRecentTrades(
			@retrofit2.http.Path("symbol") String symbol, @retrofit2.http.Query("limit") Integer limit
	);

	/**
	 * Account history
	 *
	 * @param accountId (optional)
	 * @param mode      (optional)
	 * @param skip      (optional)
	 * @param take      (optional)
	 * @return Call&lt;BinanceRawOrderItemsViewModel&gt;
	 */
	@GET("v2.0/tradingplatform/binance/spot/trades")
	Observable<BinanceRawOrderItemsViewModel> getTradesHistory(
			@retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("Mode") TradingPlatformBinanceOrdersMode mode, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Ping account stream
	 *
	 * @param accountId (optional)
	 * @param listenKey (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/tradingplatform/binance/spot/stream/ping")
	Observable<Void> keepAliveAccountStream(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("listenKey") String listenKey
	);

	/**
	 * Place order
	 *
	 * @param body      (optional)
	 * @param accountId (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/tradingplatform/binance/spot/orders/place")
	Observable<Void> placeOrder(
			@retrofit2.http.Body BinanceRawPlaceOrder body, @retrofit2.http.Query("accountId") UUID accountId
	);

	/**
	 * Remove account favorite symbol
	 *
	 * @param id     (required)
	 * @param symbol (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/tradingplatform/accounts/{id}/symbols/favorite/{symbol}/remove")
	Observable<Void> removeFavoriteSymbol(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("symbol") String symbol
	);

	/**
	 * Start account stream
	 *
	 * @param accountId (optional)
	 * @return Call&lt;String&gt;
	 */
	@POST("v2.0/tradingplatform/binance/spot/stream/start")
	Observable<String> startAccountStream(
			@retrofit2.http.Query("accountId") UUID accountId
	);

	/**
	 * Stop account stream
	 *
	 * @param accountId (optional)
	 * @param listenKey (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/tradingplatform/binance/spot/stream/stop")
	Observable<Void> stopAccountStream(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("listenKey") String listenKey
	);

}
