package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.BinanceRaw24HPrice;
import io.swagger.client.model.BinanceRawAccountInfo;
import io.swagger.client.model.BinanceRawExchangeInfo;
import io.swagger.client.model.BinanceRawKlineInterval;
import io.swagger.client.model.BinanceRawKlineItemsViewModel;
import io.swagger.client.model.BinanceRawOrderBook;
import io.swagger.client.model.BinanceRawOrderItemsViewModel;
import io.swagger.client.model.BinanceRawPlaceOrder;
import io.swagger.client.model.StringItemsViewModel;
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
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/tradingplatform/binance/spot/orders/close/all")
	Observable<Void> cancelAllOrders(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("symbol") String symbol
	);

	/**
	 * Cancel order
	 *
	 * @param accountId (optional)
	 * @param symbol    (optional)
	 * @param orderId   (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/tradingplatform/binance/spot/orders/close")
	Observable<Void> cancelOrder(
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
	 * @param accountId (optional)
	 * @return Call&lt;BinanceRawAccountInfo&gt;
	 */
	@GET("v2.0/tradingplatform/binance/account")
	Observable<BinanceRawAccountInfo> getAccountInfo(
			@retrofit2.http.Query("accountId") UUID accountId
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
	 * @return Call&lt;DateTime&gt;
	 */
	@GET("v2.0/tradingplatform/binance/server/time")
	Observable<DateTime> getExchangeTime();


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
