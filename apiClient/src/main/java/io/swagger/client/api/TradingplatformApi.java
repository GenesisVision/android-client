package io.swagger.client.api;//retrofit2

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.BinanceFuturesMarginType;
import io.swagger.client.model.BinanceKlineInterval;
import io.swagger.client.model.BinancePeriodInterval;
import io.swagger.client.model.BinancePositionMode;
import io.swagger.client.model.BinanceRaw24HPrice;
import io.swagger.client.model.BinanceRawAccountInfo;
import io.swagger.client.model.BinanceRawAggregatedTrade;
import io.swagger.client.model.BinanceRawBlvtKlineItemsViewModel;
import io.swagger.client.model.BinanceRawBookPrice;
import io.swagger.client.model.BinanceRawCancelOrder;
import io.swagger.client.model.BinanceRawCancelOrderId;
import io.swagger.client.model.BinanceRawExchangeInfo;
import io.swagger.client.model.BinanceRawFutures24HPrice;
import io.swagger.client.model.BinanceRawFuturesAccountInfo;
import io.swagger.client.model.BinanceRawFuturesBuySellVolumeRatio;
import io.swagger.client.model.BinanceRawFuturesCancelAllOrders;
import io.swagger.client.model.BinanceRawFuturesCancelOrder;
import io.swagger.client.model.BinanceRawFuturesChangeMarginTypeResult;
import io.swagger.client.model.BinanceRawFuturesCompositeIndexInfo;
import io.swagger.client.model.BinanceRawFuturesFundingRateHistory;
import io.swagger.client.model.BinanceRawFuturesInitialLeverageChangeResult;
import io.swagger.client.model.BinanceRawFuturesLiquidation;
import io.swagger.client.model.BinanceRawFuturesLongShortRatio;
import io.swagger.client.model.BinanceRawFuturesMarkPrice;
import io.swagger.client.model.BinanceRawFuturesOpenInterest;
import io.swagger.client.model.BinanceRawFuturesOpenInterestHistory;
import io.swagger.client.model.BinanceRawFuturesOrderItemsViewModel;
import io.swagger.client.model.BinanceRawFuturesPlaceOrder;
import io.swagger.client.model.BinanceRawFuturesPlacedOrder;
import io.swagger.client.model.BinanceRawFuturesPosition;
import io.swagger.client.model.BinanceRawFuturesPositionMode;
import io.swagger.client.model.BinanceRawFuturesSymbolBracket;
import io.swagger.client.model.BinanceRawFuturesUsdtExchangeInfo;
import io.swagger.client.model.BinanceRawKlineItemsViewModel;
import io.swagger.client.model.BinanceRawOrderBook;
import io.swagger.client.model.BinanceRawOrderItemsViewModel;
import io.swagger.client.model.BinanceRawPlaceOrder;
import io.swagger.client.model.BinanceRawPlacedOrder;
import io.swagger.client.model.BinanceRawPrice;
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
	 * @param accountId (optional)
	 * @param symbol    (optional)
	 * @param leverage  (optional)
	 * @return Call&lt;BinanceRawFuturesInitialLeverageChangeResult&gt;
	 */
	@POST("v2.0/tradingplatform/binance/account/futures/leverage/initial")
	Observable<BinanceRawFuturesInitialLeverageChangeResult> changeFuturesInitialLeverageAsync(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("leverage") Integer leverage
	);

	/**
	 * @param accountId  (optional)
	 * @param symbol     (optional)
	 * @param marginType (optional)
	 * @return Call&lt;BinanceRawFuturesChangeMarginTypeResult&gt;
	 */
	@POST("v2.0/tradingplatform/binance/account/futures/margin/type")
	Observable<BinanceRawFuturesChangeMarginTypeResult> changeFuturesMarginType(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("marginType") BinanceFuturesMarginType marginType
	);

	/**
	 * Cancel all futures orders
	 *
	 * @param accountId (optional)
	 * @param symbol    (optional)
	 * @return Call&lt;BinanceRawFuturesCancelAllOrders&gt;
	 */
	@POST("v2.0/tradingplatform/binance/futures/orders/cancel/all")
	Observable<BinanceRawFuturesCancelAllOrders> futuresCancelAllOpenOrders(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("symbol") String symbol
	);

	/**
	 * Cancel futures order
	 *
	 * @param accountId         (optional)
	 * @param symbol            (optional)
	 * @param orderId           (optional)
	 * @param origClientOrderId (optional)
	 * @param newClientOrderId  (optional)
	 * @return Call&lt;BinanceRawFuturesCancelOrder&gt;
	 */
	@POST("v2.0/tradingplatform/binance/futures/orders/cancel")
	Observable<BinanceRawFuturesCancelOrder> futuresCancelOrder(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("orderId") Long orderId, @retrofit2.http.Query("origClientOrderId") String origClientOrderId, @retrofit2.http.Query("newClientOrderId") String newClientOrderId
	);

	/**
	 * Ping futures account stream
	 *
	 * @param accountId (optional)
	 * @param listenKey (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/tradingplatform/binance/futures/stream/ping")
	Observable<Void> futuresKeepAliveAccountStream(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("listenKey") String listenKey
	);

	/**
	 * Place futures order
	 *
	 * @param body      (optional)
	 * @param accountId (optional)
	 * @return Call&lt;BinanceRawFuturesPlacedOrder&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/tradingplatform/binance/futures/orders/place")
	Observable<BinanceRawFuturesPlacedOrder> futuresPlaceOrder(
			@retrofit2.http.Body BinanceRawFuturesPlaceOrder body, @retrofit2.http.Query("accountId") UUID accountId
	);

	/**
	 * Start futures account stream
	 *
	 * @param accountId (optional)
	 * @return Call&lt;String&gt;
	 */
	@POST("v2.0/tradingplatform/binance/futures/stream/start")
	Observable<String> futuresStartAccountStream(
			@retrofit2.http.Query("accountId") UUID accountId
	);

	/**
	 * Stop futures account stream
	 *
	 * @param accountId (optional)
	 * @param listenKey (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/tradingplatform/binance/futures/stream/stop")
	Observable<Void> futuresStopAccountStream(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("listenKey") String listenKey
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
	 * Get leveraged tokens klines
	 *
	 * @param symbol    (optional)
	 * @param interval  (optional)
	 * @param startTime (optional)
	 * @param endTime   (optional)
	 * @param limit     (optional)
	 * @return Call&lt;BinanceRawBlvtKlineItemsViewModel&gt;
	 */
	@GET("v2.0/tradingplatform/binance/blvt/market/klines")
	Observable<BinanceRawBlvtKlineItemsViewModel> getBlvtKlines(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("interval") BinanceKlineInterval interval, @retrofit2.http.Query("startTime") DateTime startTime, @retrofit2.http.Query("endTime") DateTime endTime, @retrofit2.http.Query("limit") Integer limit
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
	 * Get futures 24H prices
	 *
	 * @param symbol (optional)
	 * @return Call&lt;List&lt;BinanceRawFutures24HPrice&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/ticker/24hr")
	Observable<List<BinanceRawFutures24HPrice>> getFutures24HPrices(
			@retrofit2.http.Query("symbol") String symbol
	);

	/**
	 * @param accountId (optional)
	 * @return Call&lt;BinanceRawFuturesAccountInfo&gt;
	 */
	@GET("v2.0/tradingplatform/binance/account/futures")
	Observable<BinanceRawFuturesAccountInfo> getFuturesAccountInfo(
			@retrofit2.http.Query("accountId") UUID accountId
	);

	/**
	 * Get futures book prices
	 *
	 * @param symbol (optional)
	 * @return Call&lt;List&lt;BinanceRawBookPrice&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/ticker/book")
	Observable<List<BinanceRawBookPrice>> getFuturesBookPrices(
			@retrofit2.http.Query("symbol") String symbol
	);

	/**
	 * Get notional and leverage brackets
	 *
	 * @param accountId (optional)
	 * @param symbol    (optional)
	 * @return Call&lt;List&lt;BinanceRawFuturesSymbolBracket&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/account/futures/symbols/brackets")
	Observable<List<BinanceRawFuturesSymbolBracket>> getFuturesBrackets(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("symbol") String symbol
	);

	/**
	 * Gets composite index info
	 *
	 * @param symbol (optional)
	 * @return Call&lt;List&lt;BinanceRawFuturesCompositeIndexInfo&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/index/composite/info")
	Observable<List<BinanceRawFuturesCompositeIndexInfo>> getFuturesCompositeIndexInfo(
			@retrofit2.http.Query("symbol") String symbol
	);

	/**
	 * Exchange futures info
	 *
	 * @return Call&lt;BinanceRawFuturesUsdtExchangeInfo&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/server/info")
	Observable<BinanceRawFuturesUsdtExchangeInfo> getFuturesExchangeInfo();


	/**
	 * Get futures funding rate history
	 *
	 * @param symbol    (optional)
	 * @param startTime (optional)
	 * @param endTime   (optional)
	 * @param limit     (optional)
	 * @return Call&lt;List&lt;BinanceRawFuturesFundingRateHistory&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/rates/funding")
	Observable<List<BinanceRawFuturesFundingRateHistory>> getFuturesFundingRates(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("startTime") DateTime startTime, @retrofit2.http.Query("endTime") DateTime endTime, @retrofit2.http.Query("limit") Integer limit
	);

	/**
	 * Get long/short ratio
	 *
	 * @param symbol    (optional)
	 * @param period    (optional)
	 * @param limit     (optional)
	 * @param startTime (optional)
	 * @param endTime   (optional)
	 * @return Call&lt;List&lt;BinanceRawFuturesLongShortRatio&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/ratio/longshort/global/account")
	Observable<List<BinanceRawFuturesLongShortRatio>> getFuturesGlobalLongShortAccountRatio(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("period") BinancePeriodInterval period, @retrofit2.http.Query("limit") Integer limit, @retrofit2.http.Query("startTime") DateTime startTime, @retrofit2.http.Query("endTime") DateTime endTime
	);

	/**
	 * Get futures klines
	 *
	 * @param symbol    (optional)
	 * @param interval  (optional)
	 * @param startTime (optional)
	 * @param endTime   (optional)
	 * @param limit     (optional)
	 * @return Call&lt;BinanceRawKlineItemsViewModel&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/klines")
	Observable<BinanceRawKlineItemsViewModel> getFuturesKlines(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("interval") BinanceKlineInterval interval, @retrofit2.http.Query("startTime") DateTime startTime, @retrofit2.http.Query("endTime") DateTime endTime, @retrofit2.http.Query("limit") Integer limit
	);

	/**
	 * Get futures liquidation orders
	 *
	 * @param symbol    (optional)
	 * @param startTime (optional)
	 * @param endTime   (optional)
	 * @param limit     (optional)
	 * @return Call&lt;List&lt;BinanceRawFuturesLiquidation&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/orders/liquidation")
	Observable<List<BinanceRawFuturesLiquidation>> getFuturesLiquidationOrders(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("startTime") DateTime startTime, @retrofit2.http.Query("endTime") DateTime endTime, @retrofit2.http.Query("limit") Integer limit
	);

	/**
	 * Get futures mark prices
	 *
	 * @param symbol (optional)
	 * @return Call&lt;List&lt;BinanceRawFuturesMarkPrice&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/prices/mark")
	Observable<List<BinanceRawFuturesMarkPrice>> getFuturesMarkPrices(
			@retrofit2.http.Query("symbol") String symbol
	);

	/**
	 * Get present open interest of a specific symbol
	 *
	 * @param symbol (optional)
	 * @return Call&lt;BinanceRawFuturesOpenInterest&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/rates/interest")
	Observable<BinanceRawFuturesOpenInterest> getFuturesOpenInterest(
			@retrofit2.http.Query("symbol") String symbol
	);

	/**
	 * Get open interest history
	 *
	 * @param symbol    (optional)
	 * @param period    (optional)
	 * @param limit     (optional)
	 * @param startTime (optional)
	 * @param endTime   (optional)
	 * @return Call&lt;List&lt;BinanceRawFuturesOpenInterestHistory&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/rates/interest/history")
	Observable<List<BinanceRawFuturesOpenInterestHistory>> getFuturesOpenInterestHistory(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("period") BinancePeriodInterval period, @retrofit2.http.Query("limit") Integer limit, @retrofit2.http.Query("startTime") DateTime startTime, @retrofit2.http.Query("endTime") DateTime endTime
	);

	/**
	 * Futures open positions
	 *
	 * @param accountId (optional)
	 * @return Call&lt;BinanceRawFuturesOrderItemsViewModel&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/usdt/orders")
	Observable<BinanceRawFuturesOrderItemsViewModel> getFuturesOpenOrders(
			@retrofit2.http.Query("accountId") UUID accountId
	);

	/**
	 * Get futures order book
	 *
	 * @param symbol (optional)
	 * @param limit  (optional)
	 * @return Call&lt;BinanceRawOrderBook&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/depth")
	Observable<BinanceRawOrderBook> getFuturesOrderBook(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("limit") Integer limit
	);

	/**
	 * Gets position information
	 *
	 * @param accountId (optional)
	 * @param symbol    (optional)
	 * @return Call&lt;List&lt;BinanceRawFuturesPosition&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/account/futures/position/risk")
	Observable<List<BinanceRawFuturesPosition>> getFuturesPositionInformation(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("symbol") String symbol
	);

	/**
	 * @param accountId (optional)
	 * @return Call&lt;BinanceRawFuturesPositionMode&gt;
	 */
	@GET("v2.0/tradingplatform/binance/account/futures/position/mode")
	Observable<BinanceRawFuturesPositionMode> getFuturesPositionMode(
			@retrofit2.http.Query("accountId") UUID accountId
	);

	/**
	 * Get futures symbol aggregated trades
	 *
	 * @param symbol    (optional)
	 * @param fromId    (optional)
	 * @param startTime (optional)
	 * @param endTime   (optional)
	 * @param limit     (optional)
	 * @return Call&lt;List&lt;BinanceRawAggregatedTrade&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/trades/aggregated")
	Observable<List<BinanceRawAggregatedTrade>> getFuturesSymbolAggregatedTrades(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("fromId") Long fromId, @retrofit2.http.Query("startTime") DateTime startTime, @retrofit2.http.Query("endTime") DateTime endTime, @retrofit2.http.Query("limit") Integer limit
	);

	/**
	 * Get futures symbol historical trades
	 *
	 * @param symbol (optional)
	 * @param limit  (optional)
	 * @param fromId (optional)
	 * @return Call&lt;List&lt;BinanceRawRecentTrade&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/trades/historical")
	Observable<List<BinanceRawRecentTrade>> getFuturesSymbolHistoricalTrades(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("limit") Integer limit, @retrofit2.http.Query("fromId") Long fromId
	);

	/**
	 * Get futures symbol recent trades
	 *
	 * @param symbol (optional)
	 * @param limit  (optional)
	 * @return Call&lt;List&lt;BinanceRawRecentTrade&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/trades/recent")
	Observable<List<BinanceRawRecentTrade>> getFuturesSymbolRecentTrades(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("limit") Integer limit
	);

	/**
	 * Get taker buy/sell volume ratio
	 *
	 * @param symbol    (optional)
	 * @param period    (optional)
	 * @param limit     (optional)
	 * @param startTime (optional)
	 * @param endTime   (optional)
	 * @return Call&lt;List&lt;BinanceRawFuturesBuySellVolumeRatio&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/ratio/buysell/volume/taker")
	Observable<List<BinanceRawFuturesBuySellVolumeRatio>> getFuturesTakerBuySellVolumeRatio(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("period") BinancePeriodInterval period, @retrofit2.http.Query("limit") Integer limit, @retrofit2.http.Query("startTime") DateTime startTime, @retrofit2.http.Query("endTime") DateTime endTime
	);

	/**
	 * Get futures symbol price
	 *
	 * @param symbol (optional)
	 * @return Call&lt;BinanceRawPrice&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/ticker/price")
	Observable<BinanceRawPrice> getFuturesTickerPrices(
			@retrofit2.http.Query("symbol") String symbol
	);

	/**
	 * Get top trader long/short ratio (Accounts)
	 *
	 * @param symbol    (optional)
	 * @param period    (optional)
	 * @param limit     (optional)
	 * @param startTime (optional)
	 * @param endTime   (optional)
	 * @return Call&lt;List&lt;BinanceRawFuturesLongShortRatio&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/ratio/longshort/top/account")
	Observable<List<BinanceRawFuturesLongShortRatio>> getFuturesTopLongShortAccountRatio(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("period") BinancePeriodInterval period, @retrofit2.http.Query("limit") Integer limit, @retrofit2.http.Query("startTime") DateTime startTime, @retrofit2.http.Query("endTime") DateTime endTime
	);

	/**
	 * Get top trader long/short ratio (Positions)
	 *
	 * @param symbol    (optional)
	 * @param period    (optional)
	 * @param limit     (optional)
	 * @param startTime (optional)
	 * @param endTime   (optional)
	 * @return Call&lt;List&lt;BinanceRawFuturesLongShortRatio&gt;&gt;
	 */
	@GET("v2.0/tradingplatform/binance/futures/market/ratio/longshort/top/position")
	Observable<List<BinanceRawFuturesLongShortRatio>> getFuturesTopLongShortPositionRatio(
			@retrofit2.http.Query("symbol") String symbol, @retrofit2.http.Query("period") BinancePeriodInterval period, @retrofit2.http.Query("limit") Integer limit, @retrofit2.http.Query("startTime") DateTime startTime, @retrofit2.http.Query("endTime") DateTime endTime
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
			@retrofit2.http.Path("symbol") String symbol, @retrofit2.http.Query("interval") BinanceKlineInterval interval, @retrofit2.http.Query("startTime") DateTime startTime, @retrofit2.http.Query("endTime") DateTime endTime, @retrofit2.http.Query("limit") Integer limit
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
	 * @param dateFrom  (optional)
	 * @param dateTo    (optional)
	 * @param symbol    (optional)
	 * @param skip      (optional)
	 * @param take      (optional)
	 * @return Call&lt;BinanceRawOrderItemsViewModel&gt;
	 */
	@GET("v2.0/tradingplatform/binance/spot/trades")
	Observable<BinanceRawOrderItemsViewModel> getTradesHistory(
			@retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("Mode") TradingPlatformBinanceOrdersMode mode, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
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
	 * @return Call&lt;BinanceRawPlacedOrder&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/tradingplatform/binance/spot/orders/place")
	Observable<BinanceRawPlacedOrder> placeOrder(
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
	 * @param accountId (optional)
	 * @param mode      (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/tradingplatform/binance/account/futures/position/mode")
	Observable<Void> setFuturesPositionMode(
			@retrofit2.http.Query("accountId") UUID accountId, @retrofit2.http.Query("mode") BinancePositionMode mode
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
