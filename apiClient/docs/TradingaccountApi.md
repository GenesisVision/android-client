# TradingaccountApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**closeAccountAssetTrade**](TradingaccountApi.md#closeAccountAssetTrade) | **POST** v2.0/tradingaccount/{id}/trades/close | Manually close trade by symbol for account
[**exportTrades**](TradingaccountApi.md#exportTrades) | **GET** v2.0/tradingaccount/{id}/trades/export | Export trade history
[**getAbsoluteProfitChart**](TradingaccountApi.md#getAbsoluteProfitChart) | **GET** v2.0/tradingaccount/{id}/charts/profit/absolute | Trading account absolute profit chart
[**getBalanceChart**](TradingaccountApi.md#getBalanceChart) | **GET** v2.0/tradingaccount/{id}/charts/balance | Trading account balance chart
[**getOpenTrades**](TradingaccountApi.md#getOpenTrades) | **GET** v2.0/tradingaccount/{id}/trades/open | Trading account open positions
[**getProfitPercentCharts**](TradingaccountApi.md#getProfitPercentCharts) | **GET** v2.0/tradingaccount/{id}/charts/profit/percent | Trading account profit percent charts
[**getTrades**](TradingaccountApi.md#getTrades) | **GET** v2.0/tradingaccount/{id}/trades | Trading account trades
[**getTradingAccountDetails**](TradingaccountApi.md#getTradingAccountDetails) | **GET** v2.0/tradingaccount/{id} | Trading account details

<a name="closeAccountAssetTrade"></a>
# **closeAccountAssetTrade**
> Void closeAccountAssetTrade(id, symbol)

Manually close trade by symbol for account

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingaccountApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
try {
    Void result = apiInstance.closeAccountAssetTrade(id, symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingaccountApi#closeAccountAssetTrade");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **symbol** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="exportTrades"></a>
# **exportTrades**
> byte[] exportTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, isFollow, skip, take)

Export trade history

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingaccountApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String symbol = "symbol_example"; // String | 
TradeSorting sorting = new TradeSorting(); // TradeSorting | 
UUID accountId = new UUID(); // UUID | 
Currency accountCurrency = new Currency(); // Currency | 
Boolean isFollow = true; // Boolean | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    byte[] result = apiInstance.exportTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, isFollow, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingaccountApi#exportTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **symbol** | **String**|  | [optional]
 **sorting** | [**TradeSorting**](.md)|  | [optional]
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | [**Currency**](.md)|  | [optional]
 **isFollow** | **Boolean**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

**byte[]**

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getAbsoluteProfitChart"></a>
# **getAbsoluteProfitChart**
> AbsoluteProfitChart getAbsoluteProfitChart(id, dateFrom, dateTo, maxPointCount, currency)

Trading account absolute profit chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingaccountApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
Currency currency = new Currency(); // Currency | 
try {
    AbsoluteProfitChart result = apiInstance.getAbsoluteProfitChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingaccountApi#getAbsoluteProfitChart");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **maxPointCount** | **Integer**|  | [optional]
 **currency** | [**Currency**](.md)|  | [optional]

### Return type

[**AbsoluteProfitChart**](AbsoluteProfitChart.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getBalanceChart"></a>
# **getBalanceChart**
> AccountBalanceChart getBalanceChart(id, dateFrom, dateTo, maxPointCount, currency)

Trading account balance chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingaccountApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
Currency currency = new Currency(); // Currency | 
try {
    AccountBalanceChart result = apiInstance.getBalanceChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingaccountApi#getBalanceChart");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **maxPointCount** | **Integer**|  | [optional]
 **currency** | [**Currency**](.md)|  | [optional]

### Return type

[**AccountBalanceChart**](AccountBalanceChart.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getOpenTrades"></a>
# **getOpenTrades**
> TradesViewModel getOpenTrades(id, sorting, symbol, accountId, accountCurrency, skip, take)

Trading account open positions

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingaccountApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
TradeSorting sorting = new TradeSorting(); // TradeSorting | 
String symbol = "symbol_example"; // String | 
UUID accountId = new UUID(); // UUID | 
Currency accountCurrency = new Currency(); // Currency | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesViewModel result = apiInstance.getOpenTrades(id, sorting, symbol, accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingaccountApi#getOpenTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **sorting** | [**TradeSorting**](.md)|  | [optional]
 **symbol** | **String**|  | [optional]
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | [**Currency**](.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesViewModel**](TradesViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProfitPercentCharts"></a>
# **getProfitPercentCharts**
> AccountProfitPercentCharts getProfitPercentCharts(id, dateFrom, dateTo, maxPointCount, currency, currencies)

Trading account profit percent charts

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingaccountApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
Currency currency = new Currency(); // Currency | 
List<Currency> currencies = Arrays.asList(new Currency()); // List<Currency> | 
try {
    AccountProfitPercentCharts result = apiInstance.getProfitPercentCharts(id, dateFrom, dateTo, maxPointCount, currency, currencies);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingaccountApi#getProfitPercentCharts");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **maxPointCount** | **Integer**|  | [optional]
 **currency** | [**Currency**](.md)|  | [optional]
 **currencies** | [**List&lt;Currency&gt;**](Currency.md)|  | [optional]

### Return type

[**AccountProfitPercentCharts**](AccountProfitPercentCharts.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getTrades"></a>
# **getTrades**
> TradesSignalViewModel getTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, isFollow, skip, take)

Trading account trades

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingaccountApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String symbol = "symbol_example"; // String | 
TradeSorting sorting = new TradeSorting(); // TradeSorting | 
UUID accountId = new UUID(); // UUID | 
Currency accountCurrency = new Currency(); // Currency | 
Boolean isFollow = true; // Boolean | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesSignalViewModel result = apiInstance.getTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, isFollow, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingaccountApi#getTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **symbol** | **String**|  | [optional]
 **sorting** | [**TradeSorting**](.md)|  | [optional]
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | [**Currency**](.md)|  | [optional]
 **isFollow** | **Boolean**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesSignalViewModel**](TradesSignalViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getTradingAccountDetails"></a>
# **getTradingAccountDetails**
> PrivateTradingAccountFull getTradingAccountDetails(id)

Trading account details

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TradingaccountApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
try {
    PrivateTradingAccountFull result = apiInstance.getTradingAccountDetails(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradingaccountApi#getTradingAccountDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**PrivateTradingAccountFull**](PrivateTradingAccountFull.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

