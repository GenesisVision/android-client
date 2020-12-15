# TradingplatformApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addFavoriteSymbol**](TradingplatformApi.md#addFavoriteSymbol) | **POST** v2.0/tradingplatform/accounts/{id}/symbols/favorite/{symbol}/add | Add account favorite symbol
[**cancelAllOrders**](TradingplatformApi.md#cancelAllOrders) | **POST** v2.0/tradingplatform/binance/spot/orders/cancel/all | Cancel all orders
[**cancelOrder**](TradingplatformApi.md#cancelOrder) | **POST** v2.0/tradingplatform/binance/spot/orders/cancel | Cancel order
[**get24HPrice**](TradingplatformApi.md#get24HPrice) | **GET** v2.0/tradingplatform/binance/market/{symbol}/ticker/24hr | Get 24H price
[**get24HPrices**](TradingplatformApi.md#get24HPrices) | **GET** v2.0/tradingplatform/binance/market/ticker/24hr | Get 24H prices
[**getAccountInfo**](TradingplatformApi.md#getAccountInfo) | **GET** v2.0/tradingplatform/binance/account | 
[**getExchangeInfo**](TradingplatformApi.md#getExchangeInfo) | **GET** v2.0/tradingplatform/binance/server/info | Exchange info
[**getExchangeTime**](TradingplatformApi.md#getExchangeTime) | **GET** v2.0/tradingplatform/binance/server/time | Server time
[**getFavoriteSymbols**](TradingplatformApi.md#getFavoriteSymbols) | **GET** v2.0/tradingplatform/accounts/{id}/symbols/favorite | Get account favorite symbols
[**getKlines**](TradingplatformApi.md#getKlines) | **GET** v2.0/tradingplatform/binance/market/{symbol}/klines | Get klines
[**getOpenOrders**](TradingplatformApi.md#getOpenOrders) | **GET** v2.0/tradingplatform/binance/spot/orders | Open positions
[**getOrderBook**](TradingplatformApi.md#getOrderBook) | **GET** v2.0/tradingplatform/binance/market/{symbol}/depth | Get order book
[**getSymbolRecentTrades**](TradingplatformApi.md#getSymbolRecentTrades) | **GET** v2.0/tradingplatform/binance/market/{symbol}/trades/recent | Get symbol recent trades
[**getTradesHistory**](TradingplatformApi.md#getTradesHistory) | **GET** v2.0/tradingplatform/binance/spot/trades | Account history
[**keepAliveAccountStream**](TradingplatformApi.md#keepAliveAccountStream) | **POST** v2.0/tradingplatform/binance/spot/stream/ping | Ping account stream
[**placeOrder**](TradingplatformApi.md#placeOrder) | **POST** v2.0/tradingplatform/binance/spot/orders/place | Place order
[**removeFavoriteSymbol**](TradingplatformApi.md#removeFavoriteSymbol) | **POST** v2.0/tradingplatform/accounts/{id}/symbols/favorite/{symbol}/remove | Remove account favorite symbol
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
BinanceRawKlineInterval interval = new BinanceRawKlineInterval(); // BinanceRawKlineInterval | 
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
 **interval** | [**BinanceRawKlineInterval**](.md)|  | [optional]
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
> Void placeOrder(body, accountId)

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
    Void result = apiInstance.placeOrder(body, accountId);
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

[**Void**](.md)

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

