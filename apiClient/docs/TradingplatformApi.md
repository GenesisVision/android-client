# TradingplatformApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addFavoriteSymbol**](TradingplatformApi.md#addFavoriteSymbol) | **POST** v2.0/tradingplatform/accounts/{id}/symbols/favorite/{symbol}/add | Add account favorite symbol
[**cancelAllOrders**](TradingplatformApi.md#cancelAllOrders) | **POST** v2.0/tradingplatform/binance/spot/orders/cancel/all | Cancel all orders
[**cancelOrder**](TradingplatformApi.md#cancelOrder) | **POST** v2.0/tradingplatform/binance/spot/orders/cancel | Cancel order
[**changeFuturesInitialLeverageAsync**](TradingplatformApi.md#changeFuturesInitialLeverageAsync) | **POST** v2.0/tradingplatform/binance/account/futures/leverage/initial | 
[**changeFuturesMarginType**](TradingplatformApi.md#changeFuturesMarginType) | **POST** v2.0/tradingplatform/binance/account/futures/margin/type | 
[**changeFuturesPositionMargin**](TradingplatformApi.md#changeFuturesPositionMargin) | **POST** v2.0/tradingplatform/binance/account/futures/position/margin | 
[**futuresCancelAllOpenOrders**](TradingplatformApi.md#futuresCancelAllOpenOrders) | **POST** v2.0/tradingplatform/binance/futures/orders/cancel/all | Cancel all futures orders
[**futuresCancelOrder**](TradingplatformApi.md#futuresCancelOrder) | **POST** v2.0/tradingplatform/binance/futures/orders/cancel | Cancel futures order
[**futuresKeepAliveAccountStream**](TradingplatformApi.md#futuresKeepAliveAccountStream) | **POST** v2.0/tradingplatform/binance/futures/stream/ping | Ping futures account stream
[**futuresPlaceOrder**](TradingplatformApi.md#futuresPlaceOrder) | **POST** v2.0/tradingplatform/binance/futures/orders/place | Place futures order
[**futuresStartAccountStream**](TradingplatformApi.md#futuresStartAccountStream) | **POST** v2.0/tradingplatform/binance/futures/stream/start | Start futures account stream
[**futuresStopAccountStream**](TradingplatformApi.md#futuresStopAccountStream) | **POST** v2.0/tradingplatform/binance/futures/stream/stop | Stop futures account stream
[**get24HPrice**](TradingplatformApi.md#get24HPrice) | **GET** v2.0/tradingplatform/binance/market/{symbol}/ticker/24hr | Get 24H price
[**get24HPrices**](TradingplatformApi.md#get24HPrices) | **GET** v2.0/tradingplatform/binance/market/ticker/24hr | Get 24H prices
[**getAccountInfo**](TradingplatformApi.md#getAccountInfo) | **GET** v2.0/tradingplatform/binance/account | 
[**getBlvtKlines**](TradingplatformApi.md#getBlvtKlines) | **GET** v2.0/tradingplatform/binance/blvt/market/klines | Get leveraged tokens klines
[**getExchangeInfo**](TradingplatformApi.md#getExchangeInfo) | **GET** v2.0/tradingplatform/binance/server/info | Exchange info
[**getExchangeTime**](TradingplatformApi.md#getExchangeTime) | **GET** v2.0/tradingplatform/binance/server/time | Server time
[**getFavoriteSymbols**](TradingplatformApi.md#getFavoriteSymbols) | **GET** v2.0/tradingplatform/accounts/{id}/symbols/favorite | Get account favorite symbols
[**getFutures24HPrices**](TradingplatformApi.md#getFutures24HPrices) | **GET** v2.0/tradingplatform/binance/futures/market/ticker/24hr | Get futures 24H prices
[**getFuturesAccountInfo**](TradingplatformApi.md#getFuturesAccountInfo) | **GET** v2.0/tradingplatform/binance/account/futures | 
[**getFuturesBookPrices**](TradingplatformApi.md#getFuturesBookPrices) | **GET** v2.0/tradingplatform/binance/futures/market/ticker/book | Get futures book prices
[**getFuturesBrackets**](TradingplatformApi.md#getFuturesBrackets) | **GET** v2.0/tradingplatform/binance/account/futures/symbols/brackets | Get notional and leverage brackets
[**getFuturesCompositeIndexInfo**](TradingplatformApi.md#getFuturesCompositeIndexInfo) | **GET** v2.0/tradingplatform/binance/futures/market/index/composite/info | Gets composite index info
[**getFuturesExchangeInfo**](TradingplatformApi.md#getFuturesExchangeInfo) | **GET** v2.0/tradingplatform/binance/futures/server/info | Exchange futures info
[**getFuturesForcedOrders**](TradingplatformApi.md#getFuturesForcedOrders) | **GET** v2.0/tradingplatform/binance/account/futures/orders/force | 
[**getFuturesFundingRates**](TradingplatformApi.md#getFuturesFundingRates) | **GET** v2.0/tradingplatform/binance/futures/market/rates/funding | Get futures funding rate history
[**getFuturesGlobalLongShortAccountRatio**](TradingplatformApi.md#getFuturesGlobalLongShortAccountRatio) | **GET** v2.0/tradingplatform/binance/futures/market/ratio/longshort/global/account | Get long/short ratio
[**getFuturesIncomeHistory**](TradingplatformApi.md#getFuturesIncomeHistory) | **GET** v2.0/tradingplatform/binance/account/futures/income/history | 
[**getFuturesKlines**](TradingplatformApi.md#getFuturesKlines) | **GET** v2.0/tradingplatform/binance/futures/market/klines | Get futures klines
[**getFuturesMarkPrices**](TradingplatformApi.md#getFuturesMarkPrices) | **GET** v2.0/tradingplatform/binance/futures/market/prices/mark | Get futures mark prices
[**getFuturesOpenInterest**](TradingplatformApi.md#getFuturesOpenInterest) | **GET** v2.0/tradingplatform/binance/futures/market/rates/interest | Get present open interest of a specific symbol
[**getFuturesOpenInterestHistory**](TradingplatformApi.md#getFuturesOpenInterestHistory) | **GET** v2.0/tradingplatform/binance/futures/market/rates/interest/history | Get open interest history
[**getFuturesOpenOrders**](TradingplatformApi.md#getFuturesOpenOrders) | **GET** v2.0/tradingplatform/binance/futures/usdt/orders | Futures open positions
[**getFuturesOrderBook**](TradingplatformApi.md#getFuturesOrderBook) | **GET** v2.0/tradingplatform/binance/futures/market/depth | Get futures order book
[**getFuturesPositionInformation**](TradingplatformApi.md#getFuturesPositionInformation) | **GET** v2.0/tradingplatform/binance/account/futures/position/risk | Gets position information
[**getFuturesPositionMode**](TradingplatformApi.md#getFuturesPositionMode) | **GET** v2.0/tradingplatform/binance/account/futures/position/mode | 
[**getFuturesSymbolAggregatedTrades**](TradingplatformApi.md#getFuturesSymbolAggregatedTrades) | **GET** v2.0/tradingplatform/binance/futures/market/trades/aggregated | Get futures symbol aggregated trades
[**getFuturesSymbolHistoricalTrades**](TradingplatformApi.md#getFuturesSymbolHistoricalTrades) | **GET** v2.0/tradingplatform/binance/futures/market/trades/historical | Get futures symbol historical trades
[**getFuturesSymbolRecentTrades**](TradingplatformApi.md#getFuturesSymbolRecentTrades) | **GET** v2.0/tradingplatform/binance/futures/market/trades/recent | Get futures symbol recent trades
[**getFuturesTakerBuySellVolumeRatio**](TradingplatformApi.md#getFuturesTakerBuySellVolumeRatio) | **GET** v2.0/tradingplatform/binance/futures/market/ratio/buysell/volume/taker | Get taker buy/sell volume ratio
[**getFuturesTickerPrices**](TradingplatformApi.md#getFuturesTickerPrices) | **GET** v2.0/tradingplatform/binance/futures/market/ticker/price | Get futures symbol price
[**getFuturesTopLongShortAccountRatio**](TradingplatformApi.md#getFuturesTopLongShortAccountRatio) | **GET** v2.0/tradingplatform/binance/futures/market/ratio/longshort/top/account | Get top trader long/short ratio (Accounts)
[**getFuturesTopLongShortPositionRatio**](TradingplatformApi.md#getFuturesTopLongShortPositionRatio) | **GET** v2.0/tradingplatform/binance/futures/market/ratio/longshort/top/position | Get top trader long/short ratio (Positions)
[**getFuturesTrades**](TradingplatformApi.md#getFuturesTrades) | **GET** v2.0/tradingplatform/binance/account/futures/trades | 
[**getFuturesTradesHistory**](TradingplatformApi.md#getFuturesTradesHistory) | **GET** v2.0/tradingplatform/binance/futures/trades | Account futures history
[**getKlines**](TradingplatformApi.md#getKlines) | **GET** v2.0/tradingplatform/binance/market/{symbol}/klines | Get klines
[**getOpenOrders**](TradingplatformApi.md#getOpenOrders) | **GET** v2.0/tradingplatform/binance/spot/orders | Open positions
[**getOrderBook**](TradingplatformApi.md#getOrderBook) | **GET** v2.0/tradingplatform/binance/market/{symbol}/depth | Get order book
[**getSymbolRecentTrades**](TradingplatformApi.md#getSymbolRecentTrades) | **GET** v2.0/tradingplatform/binance/market/{symbol}/trades/recent | Get symbol recent trades
[**getTradesHistory**](TradingplatformApi.md#getTradesHistory) | **GET** v2.0/tradingplatform/binance/spot/trades | Account history
[**keepAliveAccountStream**](TradingplatformApi.md#keepAliveAccountStream) | **POST** v2.0/tradingplatform/binance/spot/stream/ping | Ping account stream
[**placeOrder**](TradingplatformApi.md#placeOrder) | **POST** v2.0/tradingplatform/binance/spot/orders/place | Place order
[**removeFavoriteSymbol**](TradingplatformApi.md#removeFavoriteSymbol) | **POST** v2.0/tradingplatform/accounts/{id}/symbols/favorite/{symbol}/remove | Remove account favorite symbol
[**setFuturesPositionMode**](TradingplatformApi.md#setFuturesPositionMode) | **POST** v2.0/tradingplatform/binance/account/futures/position/mode | 
[**startAccountStream**](TradingplatformApi.md#startAccountStream) | **POST** v2.0/tradingplatform/binance/spot/stream/start | Start account stream
[**stopAccountStream**](TradingplatformApi.md#stopAccountStream) | **POST** v2.0/tradingplatform/binance/spot/stream/stop | Stop account stream

<a name="addFavoriteSymbol"></a>
# **addFavoriteSymbol**
> Void addFavoriteSymbol(id, symbol)

Add account favorite symbol

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID id = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
try {
    Void result = apiInstance.addFavoriteSymbol(id, symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#addFavoriteSymbol");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **symbol** | **String**|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="cancelAllOrders"></a>
# **cancelAllOrders**
> List&lt;BinanceRawCancelOrderId&gt; cancelAllOrders(accountId, symbol)

Cancel all orders

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
try {
    List<BinanceRawCancelOrderId> result = apiInstance.cancelAllOrders(accountId, symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#cancelAllOrders");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]

### Return type

[**List&lt;BinanceRawCancelOrderId&gt;**](BinanceRawCancelOrderId.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="cancelOrder"></a>
# **cancelOrder**
> BinanceRawCancelOrder cancelOrder(accountId, symbol, orderId)

Cancel order

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
String orderId = "orderId_example"; // String | 
try {
    BinanceRawCancelOrder result = apiInstance.cancelOrder(accountId, symbol, orderId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#cancelOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]
 **orderId** | **String**|  | [optional]

### Return type

[**BinanceRawCancelOrder**](BinanceRawCancelOrder.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="changeFuturesInitialLeverageAsync"></a>
# **changeFuturesInitialLeverageAsync**
> BinanceRawFuturesInitialLeverageChangeResult changeFuturesInitialLeverageAsync(accountId, symbol, leverage)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
Integer leverage = 56; // Integer | 
try {
    BinanceRawFuturesInitialLeverageChangeResult result = apiInstance.changeFuturesInitialLeverageAsync(accountId, symbol, leverage);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#changeFuturesInitialLeverageAsync");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]
 **leverage** | **Integer**|  | [optional]

### Return type

[**BinanceRawFuturesInitialLeverageChangeResult**](BinanceRawFuturesInitialLeverageChangeResult.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="changeFuturesMarginType"></a>
# **changeFuturesMarginType**
> BinanceRawFuturesChangeMarginTypeResult changeFuturesMarginType(accountId, symbol, marginType)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
BinanceFuturesMarginType marginType = new BinanceFuturesMarginType(); // BinanceFuturesMarginType | 
try {
    BinanceRawFuturesChangeMarginTypeResult result = apiInstance.changeFuturesMarginType(accountId, symbol, marginType);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#changeFuturesMarginType");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]
 **marginType** | [**BinanceFuturesMarginType**](.md)|  | [optional]

### Return type

[**BinanceRawFuturesChangeMarginTypeResult**](BinanceRawFuturesChangeMarginTypeResult.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="changeFuturesPositionMargin"></a>
# **changeFuturesPositionMargin**
> BinanceRawFuturesPositionMarginResult changeFuturesPositionMargin(accountId, symbol, amount, type, positionSide)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
Double amount = 3.4D; // Double | 
BinanceFuturesMarginChangeDirectionType type = new BinanceFuturesMarginChangeDirectionType(); // BinanceFuturesMarginChangeDirectionType | 
BinancePositionSide positionSide = new BinancePositionSide(); // BinancePositionSide | 
try {
    BinanceRawFuturesPositionMarginResult result = apiInstance.changeFuturesPositionMargin(accountId, symbol, amount, type, positionSide);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#changeFuturesPositionMargin");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]
 **amount** | **Double**|  | [optional]
 **type** | [**BinanceFuturesMarginChangeDirectionType**](.md)|  | [optional]
 **positionSide** | [**BinancePositionSide**](.md)|  | [optional]

### Return type

[**BinanceRawFuturesPositionMarginResult**](BinanceRawFuturesPositionMarginResult.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="futuresCancelAllOpenOrders"></a>
# **futuresCancelAllOpenOrders**
> BinanceRawFuturesCancelAllOrders futuresCancelAllOpenOrders(accountId, symbol)

Cancel all futures orders

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
try {
    BinanceRawFuturesCancelAllOrders result = apiInstance.futuresCancelAllOpenOrders(accountId, symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#futuresCancelAllOpenOrders");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]

### Return type

[**BinanceRawFuturesCancelAllOrders**](BinanceRawFuturesCancelAllOrders.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="futuresCancelOrder"></a>
# **futuresCancelOrder**
> BinanceRawFuturesCancelOrder futuresCancelOrder(accountId, symbol, orderId, origClientOrderId, newClientOrderId)

Cancel futures order

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
Long orderId = 789L; // Long | 
String origClientOrderId = "origClientOrderId_example"; // String | 
String newClientOrderId = "newClientOrderId_example"; // String | 
try {
    BinanceRawFuturesCancelOrder result = apiInstance.futuresCancelOrder(accountId, symbol, orderId, origClientOrderId, newClientOrderId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#futuresCancelOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]
 **orderId** | **Long**|  | [optional]
 **origClientOrderId** | **String**|  | [optional]
 **newClientOrderId** | **String**|  | [optional]

### Return type

[**BinanceRawFuturesCancelOrder**](BinanceRawFuturesCancelOrder.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="futuresKeepAliveAccountStream"></a>
# **futuresKeepAliveAccountStream**
> Void futuresKeepAliveAccountStream(accountId, listenKey)

Ping futures account stream

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String listenKey = "listenKey_example"; // String | 
try {
    Void result = apiInstance.futuresKeepAliveAccountStream(accountId, listenKey);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#futuresKeepAliveAccountStream");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **listenKey** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="futuresPlaceOrder"></a>
# **futuresPlaceOrder**
> BinanceRawFuturesPlacedOrder futuresPlaceOrder(body, accountId)

Place futures order

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
BinanceRawFuturesPlaceOrder body = new BinanceRawFuturesPlaceOrder(); // BinanceRawFuturesPlaceOrder | 
UUID accountId = new UUID(); // UUID | 
try {
    BinanceRawFuturesPlacedOrder result = apiInstance.futuresPlaceOrder(body, accountId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#futuresPlaceOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**BinanceRawFuturesPlaceOrder**](BinanceRawFuturesPlaceOrder.md)|  | [optional]
 **accountId** | [**UUID**](.md)|  | [optional]

### Return type

[**BinanceRawFuturesPlacedOrder**](BinanceRawFuturesPlacedOrder.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="futuresStartAccountStream"></a>
# **futuresStartAccountStream**
> String futuresStartAccountStream(accountId)

Start futures account stream

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
try {
    String result = apiInstance.futuresStartAccountStream(accountId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#futuresStartAccountStream");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]

### Return type

**String**

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="futuresStopAccountStream"></a>
# **futuresStopAccountStream**
> Void futuresStopAccountStream(accountId, listenKey)

Stop futures account stream

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String listenKey = "listenKey_example"; // String | 
try {
    Void result = apiInstance.futuresStopAccountStream(accountId, listenKey);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#futuresStopAccountStream");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **listenKey** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="get24HPrice"></a>
# **get24HPrice**
> BinanceRaw24HPrice get24HPrice(symbol)

Get 24H price

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
try {
    BinanceRaw24HPrice result = apiInstance.get24HPrice(symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#get24HPrice");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  |

### Return type

[**BinanceRaw24HPrice**](BinanceRaw24HPrice.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="get24HPrices"></a>
# **get24HPrices**
> List&lt;BinanceRaw24HPrice&gt; get24HPrices()

Get 24H prices

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
try {
    List<BinanceRaw24HPrice> result = apiInstance.get24HPrices();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#get24HPrices");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;BinanceRaw24HPrice&gt;**](BinanceRaw24HPrice.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getAccountInfo"></a>
# **getAccountInfo**
> BinanceRawAccountInfo getAccountInfo(accountId, currency)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
Currency currency = new Currency(); // Currency | 
try {
    BinanceRawAccountInfo result = apiInstance.getAccountInfo(accountId, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getAccountInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **currency** | [**Currency**](.md)|  | [optional]

### Return type

[**BinanceRawAccountInfo**](BinanceRawAccountInfo.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getBlvtKlines"></a>
# **getBlvtKlines**
> BinanceRawBlvtKlineItemsViewModel getBlvtKlines(symbol, interval, startTime, endTime, limit)

Get leveraged tokens klines

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
BinanceKlineInterval interval = new BinanceKlineInterval(); // BinanceKlineInterval | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
Integer limit = 56; // Integer | 
try {
    BinanceRawBlvtKlineItemsViewModel result = apiInstance.getBlvtKlines(symbol, interval, startTime, endTime, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getBlvtKlines");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]
 **interval** | [**BinanceKlineInterval**](.md)|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]
 **limit** | **Integer**|  | [optional]

### Return type

[**BinanceRawBlvtKlineItemsViewModel**](BinanceRawBlvtKlineItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getExchangeInfo"></a>
# **getExchangeInfo**
> BinanceRawExchangeInfo getExchangeInfo()

Exchange info

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
try {
    BinanceRawExchangeInfo result = apiInstance.getExchangeInfo();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getExchangeInfo");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**BinanceRawExchangeInfo**](BinanceRawExchangeInfo.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getExchangeTime"></a>
# **getExchangeTime**
> TimestampDate getExchangeTime()

Server time

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
try {
    TimestampDate result = apiInstance.getExchangeTime();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getExchangeTime");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**TimestampDate**](TimestampDate.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFavoriteSymbols"></a>
# **getFavoriteSymbols**
> StringItemsViewModel getFavoriteSymbols(id)

Get account favorite symbols

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID id = new UUID(); // UUID | 
try {
    StringItemsViewModel result = apiInstance.getFavoriteSymbols(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFavoriteSymbols");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**StringItemsViewModel**](StringItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFutures24HPrices"></a>
# **getFutures24HPrices**
> List&lt;BinanceRawFutures24HPrice&gt; getFutures24HPrices(symbol)

Get futures 24H prices

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
try {
    List<BinanceRawFutures24HPrice> result = apiInstance.getFutures24HPrices(symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFutures24HPrices");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]

### Return type

[**List&lt;BinanceRawFutures24HPrice&gt;**](BinanceRawFutures24HPrice.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesAccountInfo"></a>
# **getFuturesAccountInfo**
> BinanceRawFuturesAccountInfo getFuturesAccountInfo(accountId)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
try {
    BinanceRawFuturesAccountInfo result = apiInstance.getFuturesAccountInfo(accountId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesAccountInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]

### Return type

[**BinanceRawFuturesAccountInfo**](BinanceRawFuturesAccountInfo.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesBookPrices"></a>
# **getFuturesBookPrices**
> List&lt;BinanceRawBookPrice&gt; getFuturesBookPrices(symbol)

Get futures book prices

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
try {
    List<BinanceRawBookPrice> result = apiInstance.getFuturesBookPrices(symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesBookPrices");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]

### Return type

[**List&lt;BinanceRawBookPrice&gt;**](BinanceRawBookPrice.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesBrackets"></a>
# **getFuturesBrackets**
> List&lt;BinanceRawFuturesSymbolBracket&gt; getFuturesBrackets(accountId, symbol)

Get notional and leverage brackets

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
try {
    List<BinanceRawFuturesSymbolBracket> result = apiInstance.getFuturesBrackets(accountId, symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesBrackets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesSymbolBracket&gt;**](BinanceRawFuturesSymbolBracket.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesCompositeIndexInfo"></a>
# **getFuturesCompositeIndexInfo**
> List&lt;BinanceRawFuturesCompositeIndexInfo&gt; getFuturesCompositeIndexInfo(symbol)

Gets composite index info

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
try {
    List<BinanceRawFuturesCompositeIndexInfo> result = apiInstance.getFuturesCompositeIndexInfo(symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesCompositeIndexInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesCompositeIndexInfo&gt;**](BinanceRawFuturesCompositeIndexInfo.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesExchangeInfo"></a>
# **getFuturesExchangeInfo**
> BinanceRawFuturesUsdtExchangeInfo getFuturesExchangeInfo()

Exchange futures info

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
try {
    BinanceRawFuturesUsdtExchangeInfo result = apiInstance.getFuturesExchangeInfo();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesExchangeInfo");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**BinanceRawFuturesUsdtExchangeInfo**](BinanceRawFuturesUsdtExchangeInfo.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesForcedOrders"></a>
# **getFuturesForcedOrders**
> List&lt;BinanceRawFuturesOrder&gt; getFuturesForcedOrders(accountId, symbol, closeType, startTime, endTime)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
BinanceAutoCloseType closeType = new BinanceAutoCloseType(); // BinanceAutoCloseType | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
try {
    List<BinanceRawFuturesOrder> result = apiInstance.getFuturesForcedOrders(accountId, symbol, closeType, startTime, endTime);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesForcedOrders");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]
 **closeType** | [**BinanceAutoCloseType**](.md)|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesOrder&gt;**](BinanceRawFuturesOrder.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesFundingRates"></a>
# **getFuturesFundingRates**
> List&lt;BinanceRawFuturesFundingRateHistory&gt; getFuturesFundingRates(symbol, startTime, endTime, limit)

Get futures funding rate history

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
Integer limit = 56; // Integer | 
try {
    List<BinanceRawFuturesFundingRateHistory> result = apiInstance.getFuturesFundingRates(symbol, startTime, endTime, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesFundingRates");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]
 **limit** | **Integer**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesFundingRateHistory&gt;**](BinanceRawFuturesFundingRateHistory.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesGlobalLongShortAccountRatio"></a>
# **getFuturesGlobalLongShortAccountRatio**
> List&lt;BinanceRawFuturesLongShortRatio&gt; getFuturesGlobalLongShortAccountRatio(symbol, period, limit, startTime, endTime)

Get long/short ratio

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
BinancePeriodInterval period = new BinancePeriodInterval(); // BinancePeriodInterval | 
Integer limit = 56; // Integer | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
try {
    List<BinanceRawFuturesLongShortRatio> result = apiInstance.getFuturesGlobalLongShortAccountRatio(symbol, period, limit, startTime, endTime);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesGlobalLongShortAccountRatio");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]
 **period** | [**BinancePeriodInterval**](.md)|  | [optional]
 **limit** | **Integer**|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesLongShortRatio&gt;**](BinanceRawFuturesLongShortRatio.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesIncomeHistory"></a>
# **getFuturesIncomeHistory**
> List&lt;BinanceRawFuturesIncomeHistory&gt; getFuturesIncomeHistory(accountId, symbol, incomeType, startTime, endTime, limit)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
String incomeType = "incomeType_example"; // String | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
Integer limit = 56; // Integer | 
try {
    List<BinanceRawFuturesIncomeHistory> result = apiInstance.getFuturesIncomeHistory(accountId, symbol, incomeType, startTime, endTime, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesIncomeHistory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]
 **incomeType** | **String**|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]
 **limit** | **Integer**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesIncomeHistory&gt;**](BinanceRawFuturesIncomeHistory.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesKlines"></a>
# **getFuturesKlines**
> BinanceRawKlineItemsViewModel getFuturesKlines(symbol, interval, startTime, endTime, limit)

Get futures klines

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
BinanceKlineInterval interval = new BinanceKlineInterval(); // BinanceKlineInterval | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
Integer limit = 56; // Integer | 
try {
    BinanceRawKlineItemsViewModel result = apiInstance.getFuturesKlines(symbol, interval, startTime, endTime, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesKlines");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]
 **interval** | [**BinanceKlineInterval**](.md)|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]
 **limit** | **Integer**|  | [optional]

### Return type

[**BinanceRawKlineItemsViewModel**](BinanceRawKlineItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesMarkPrices"></a>
# **getFuturesMarkPrices**
> List&lt;BinanceRawFuturesMarkPrice&gt; getFuturesMarkPrices(symbol)

Get futures mark prices

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
try {
    List<BinanceRawFuturesMarkPrice> result = apiInstance.getFuturesMarkPrices(symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesMarkPrices");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesMarkPrice&gt;**](BinanceRawFuturesMarkPrice.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesOpenInterest"></a>
# **getFuturesOpenInterest**
> BinanceRawFuturesOpenInterest getFuturesOpenInterest(symbol)

Get present open interest of a specific symbol

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
try {
    BinanceRawFuturesOpenInterest result = apiInstance.getFuturesOpenInterest(symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesOpenInterest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]

### Return type

[**BinanceRawFuturesOpenInterest**](BinanceRawFuturesOpenInterest.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesOpenInterestHistory"></a>
# **getFuturesOpenInterestHistory**
> List&lt;BinanceRawFuturesOpenInterestHistory&gt; getFuturesOpenInterestHistory(symbol, period, limit, startTime, endTime)

Get open interest history

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
BinancePeriodInterval period = new BinancePeriodInterval(); // BinancePeriodInterval | 
Integer limit = 56; // Integer | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
try {
    List<BinanceRawFuturesOpenInterestHistory> result = apiInstance.getFuturesOpenInterestHistory(symbol, period, limit, startTime, endTime);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesOpenInterestHistory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]
 **period** | [**BinancePeriodInterval**](.md)|  | [optional]
 **limit** | **Integer**|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesOpenInterestHistory&gt;**](BinanceRawFuturesOpenInterestHistory.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesOpenOrders"></a>
# **getFuturesOpenOrders**
> BinanceRawFuturesOrderItemsViewModel getFuturesOpenOrders(accountId)

Futures open positions

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
try {
    BinanceRawFuturesOrderItemsViewModel result = apiInstance.getFuturesOpenOrders(accountId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesOpenOrders");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]

### Return type

[**BinanceRawFuturesOrderItemsViewModel**](BinanceRawFuturesOrderItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesOrderBook"></a>
# **getFuturesOrderBook**
> BinanceRawOrderBook getFuturesOrderBook(symbol, limit)

Get futures order book

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
Integer limit = 56; // Integer | 
try {
    BinanceRawOrderBook result = apiInstance.getFuturesOrderBook(symbol, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesOrderBook");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]
 **limit** | **Integer**|  | [optional]

### Return type

[**BinanceRawOrderBook**](BinanceRawOrderBook.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesPositionInformation"></a>
# **getFuturesPositionInformation**
> List&lt;BinanceRawFuturesPosition&gt; getFuturesPositionInformation(accountId, symbol)

Gets position information

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
try {
    List<BinanceRawFuturesPosition> result = apiInstance.getFuturesPositionInformation(accountId, symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesPositionInformation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesPosition&gt;**](BinanceRawFuturesPosition.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesPositionMode"></a>
# **getFuturesPositionMode**
> BinanceRawFuturesPositionMode getFuturesPositionMode(accountId)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
try {
    BinanceRawFuturesPositionMode result = apiInstance.getFuturesPositionMode(accountId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesPositionMode");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]

### Return type

[**BinanceRawFuturesPositionMode**](BinanceRawFuturesPositionMode.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesSymbolAggregatedTrades"></a>
# **getFuturesSymbolAggregatedTrades**
> List&lt;BinanceRawAggregatedTrade&gt; getFuturesSymbolAggregatedTrades(symbol, fromId, startTime, endTime, limit)

Get futures symbol aggregated trades

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
Long fromId = 789L; // Long | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
Integer limit = 56; // Integer | 
try {
    List<BinanceRawAggregatedTrade> result = apiInstance.getFuturesSymbolAggregatedTrades(symbol, fromId, startTime, endTime, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesSymbolAggregatedTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]
 **fromId** | **Long**|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]
 **limit** | **Integer**|  | [optional]

### Return type

[**List&lt;BinanceRawAggregatedTrade&gt;**](BinanceRawAggregatedTrade.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesSymbolHistoricalTrades"></a>
# **getFuturesSymbolHistoricalTrades**
> List&lt;BinanceRawRecentTrade&gt; getFuturesSymbolHistoricalTrades(symbol, limit, fromId)

Get futures symbol historical trades

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
Integer limit = 56; // Integer | 
Long fromId = 789L; // Long | 
try {
    List<BinanceRawRecentTrade> result = apiInstance.getFuturesSymbolHistoricalTrades(symbol, limit, fromId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesSymbolHistoricalTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]
 **limit** | **Integer**|  | [optional]
 **fromId** | **Long**|  | [optional]

### Return type

[**List&lt;BinanceRawRecentTrade&gt;**](BinanceRawRecentTrade.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesSymbolRecentTrades"></a>
# **getFuturesSymbolRecentTrades**
> List&lt;BinanceRawRecentTrade&gt; getFuturesSymbolRecentTrades(symbol, limit)

Get futures symbol recent trades

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
Integer limit = 56; // Integer | 
try {
    List<BinanceRawRecentTrade> result = apiInstance.getFuturesSymbolRecentTrades(symbol, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesSymbolRecentTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]
 **limit** | **Integer**|  | [optional]

### Return type

[**List&lt;BinanceRawRecentTrade&gt;**](BinanceRawRecentTrade.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesTakerBuySellVolumeRatio"></a>
# **getFuturesTakerBuySellVolumeRatio**
> List&lt;BinanceRawFuturesBuySellVolumeRatio&gt; getFuturesTakerBuySellVolumeRatio(symbol, period, limit, startTime, endTime)

Get taker buy/sell volume ratio

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
BinancePeriodInterval period = new BinancePeriodInterval(); // BinancePeriodInterval | 
Integer limit = 56; // Integer | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
try {
    List<BinanceRawFuturesBuySellVolumeRatio> result = apiInstance.getFuturesTakerBuySellVolumeRatio(symbol, period, limit, startTime, endTime);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesTakerBuySellVolumeRatio");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]
 **period** | [**BinancePeriodInterval**](.md)|  | [optional]
 **limit** | **Integer**|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesBuySellVolumeRatio&gt;**](BinanceRawFuturesBuySellVolumeRatio.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesTickerPrices"></a>
# **getFuturesTickerPrices**
> BinanceRawPrice getFuturesTickerPrices(symbol)

Get futures symbol price

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
try {
    BinanceRawPrice result = apiInstance.getFuturesTickerPrices(symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesTickerPrices");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]

### Return type

[**BinanceRawPrice**](BinanceRawPrice.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesTopLongShortAccountRatio"></a>
# **getFuturesTopLongShortAccountRatio**
> List&lt;BinanceRawFuturesLongShortRatio&gt; getFuturesTopLongShortAccountRatio(symbol, period, limit, startTime, endTime)

Get top trader long/short ratio (Accounts)

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
BinancePeriodInterval period = new BinancePeriodInterval(); // BinancePeriodInterval | 
Integer limit = 56; // Integer | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
try {
    List<BinanceRawFuturesLongShortRatio> result = apiInstance.getFuturesTopLongShortAccountRatio(symbol, period, limit, startTime, endTime);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesTopLongShortAccountRatio");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]
 **period** | [**BinancePeriodInterval**](.md)|  | [optional]
 **limit** | **Integer**|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesLongShortRatio&gt;**](BinanceRawFuturesLongShortRatio.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesTopLongShortPositionRatio"></a>
# **getFuturesTopLongShortPositionRatio**
> List&lt;BinanceRawFuturesLongShortRatio&gt; getFuturesTopLongShortPositionRatio(symbol, period, limit, startTime, endTime)

Get top trader long/short ratio (Positions)

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
BinancePeriodInterval period = new BinancePeriodInterval(); // BinancePeriodInterval | 
Integer limit = 56; // Integer | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
try {
    List<BinanceRawFuturesLongShortRatio> result = apiInstance.getFuturesTopLongShortPositionRatio(symbol, period, limit, startTime, endTime);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesTopLongShortPositionRatio");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  | [optional]
 **period** | [**BinancePeriodInterval**](.md)|  | [optional]
 **limit** | **Integer**|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesLongShortRatio&gt;**](BinanceRawFuturesLongShortRatio.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesTrades"></a>
# **getFuturesTrades**
> List&lt;BinanceRawFuturesUsdtTrade&gt; getFuturesTrades(accountId, symbol, startTime, endTime, limit)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
Integer limit = 56; // Integer | 
try {
    List<BinanceRawFuturesUsdtTrade> result = apiInstance.getFuturesTrades(accountId, symbol, startTime, endTime, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]
 **limit** | **Integer**|  | [optional]

### Return type

[**List&lt;BinanceRawFuturesUsdtTrade&gt;**](BinanceRawFuturesUsdtTrade.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFuturesTradesHistory"></a>
# **getFuturesTradesHistory**
> BinanceRawFuturesOrderItemsViewModel getFuturesTradesHistory(accountId, mode, dateFrom, dateTo, symbol, skip, take)

Account futures history

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
TradingPlatformBinanceOrdersMode mode = new TradingPlatformBinanceOrdersMode(); // TradingPlatformBinanceOrdersMode | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String symbol = "symbol_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    BinanceRawFuturesOrderItemsViewModel result = apiInstance.getFuturesTradesHistory(accountId, mode, dateFrom, dateTo, symbol, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getFuturesTradesHistory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **mode** | [**TradingPlatformBinanceOrdersMode**](.md)|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **symbol** | **String**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**BinanceRawFuturesOrderItemsViewModel**](BinanceRawFuturesOrderItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getKlines"></a>
# **getKlines**
> BinanceRawKlineItemsViewModel getKlines(symbol, interval, startTime, endTime, limit)

Get klines

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
BinanceKlineInterval interval = new BinanceKlineInterval(); // BinanceKlineInterval | 
DateTime startTime = new DateTime(); // DateTime | 
DateTime endTime = new DateTime(); // DateTime | 
Integer limit = 56; // Integer | 
try {
    BinanceRawKlineItemsViewModel result = apiInstance.getKlines(symbol, interval, startTime, endTime, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getKlines");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  |
 **interval** | [**BinanceKlineInterval**](.md)|  | [optional]
 **startTime** | **DateTime**|  | [optional]
 **endTime** | **DateTime**|  | [optional]
 **limit** | **Integer**|  | [optional]

### Return type

[**BinanceRawKlineItemsViewModel**](BinanceRawKlineItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getOpenOrders"></a>
# **getOpenOrders**
> BinanceRawOrderItemsViewModel getOpenOrders(accountId)

Open positions

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
try {
    BinanceRawOrderItemsViewModel result = apiInstance.getOpenOrders(accountId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getOpenOrders");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]

### Return type

[**BinanceRawOrderItemsViewModel**](BinanceRawOrderItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getOrderBook"></a>
# **getOrderBook**
> BinanceRawOrderBook getOrderBook(symbol, limit)

Get order book

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
Integer limit = 56; // Integer | 
try {
    BinanceRawOrderBook result = apiInstance.getOrderBook(symbol, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getOrderBook");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  |
 **limit** | **Integer**|  | [optional]

### Return type

[**BinanceRawOrderBook**](BinanceRawOrderBook.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getSymbolRecentTrades"></a>
# **getSymbolRecentTrades**
> List&lt;BinanceRawRecentTrade&gt; getSymbolRecentTrades(symbol, limit)

Get symbol recent trades

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
String symbol = "symbol_example"; // String | 
Integer limit = 56; // Integer | 
try {
    List<BinanceRawRecentTrade> result = apiInstance.getSymbolRecentTrades(symbol, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getSymbolRecentTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **symbol** | **String**|  |
 **limit** | **Integer**|  | [optional]

### Return type

[**List&lt;BinanceRawRecentTrade&gt;**](BinanceRawRecentTrade.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getTradesHistory"></a>
# **getTradesHistory**
> BinanceRawOrderItemsViewModel getTradesHistory(accountId, mode, dateFrom, dateTo, symbol, skip, take)

Account history

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
TradingPlatformBinanceOrdersMode mode = new TradingPlatformBinanceOrdersMode(); // TradingPlatformBinanceOrdersMode | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String symbol = "symbol_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    BinanceRawOrderItemsViewModel result = apiInstance.getTradesHistory(accountId, mode, dateFrom, dateTo, symbol, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#getTradesHistory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **mode** | [**TradingPlatformBinanceOrdersMode**](.md)|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **symbol** | **String**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**BinanceRawOrderItemsViewModel**](BinanceRawOrderItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="keepAliveAccountStream"></a>
# **keepAliveAccountStream**
> Void keepAliveAccountStream(accountId, listenKey)

Ping account stream

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String listenKey = "listenKey_example"; // String | 
try {
    Void result = apiInstance.keepAliveAccountStream(accountId, listenKey);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#keepAliveAccountStream");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **listenKey** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="placeOrder"></a>
# **placeOrder**
> BinanceRawPlacedOrder placeOrder(body, accountId)

Place order

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
BinanceRawPlaceOrder body = new BinanceRawPlaceOrder(); // BinanceRawPlaceOrder | 
UUID accountId = new UUID(); // UUID | 
try {
    BinanceRawPlacedOrder result = apiInstance.placeOrder(body, accountId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#placeOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**BinanceRawPlaceOrder**](BinanceRawPlaceOrder.md)|  | [optional]
 **accountId** | [**UUID**](.md)|  | [optional]

### Return type

[**BinanceRawPlacedOrder**](BinanceRawPlacedOrder.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="removeFavoriteSymbol"></a>
# **removeFavoriteSymbol**
> Void removeFavoriteSymbol(id, symbol)

Remove account favorite symbol

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID id = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
try {
    Void result = apiInstance.removeFavoriteSymbol(id, symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#removeFavoriteSymbol");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **symbol** | **String**|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="setFuturesPositionMode"></a>
# **setFuturesPositionMode**
> Void setFuturesPositionMode(accountId, mode)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
BinancePositionMode mode = new BinancePositionMode(); // BinancePositionMode | 
try {
    Void result = apiInstance.setFuturesPositionMode(accountId, mode);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#setFuturesPositionMode");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **mode** | [**BinancePositionMode**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="startAccountStream"></a>
# **startAccountStream**
> String startAccountStream(accountId)

Start account stream

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
try {
    String result = apiInstance.startAccountStream(accountId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#startAccountStream");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]

### Return type

**String**

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="stopAccountStream"></a>
# **stopAccountStream**
> Void stopAccountStream(accountId, listenKey)

Stop account stream

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingplatformApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingplatformApi apiInstance = new TradingplatformApi();
UUID accountId = new UUID(); // UUID | 
String listenKey = "listenKey_example"; // String | 
try {
    Void result = apiInstance.stopAccountStream(accountId, listenKey);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingplatformApi#stopAccountStream");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **listenKey** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

