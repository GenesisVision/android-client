# TradingaccountApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**exportTrades**](TradingaccountApi.md#exportTrades) | **GET** v2.0/tradingaccount/{id}/trades/export | Export trade history
[**getAbsoluteProfitChart**](TradingaccountApi.md#getAbsoluteProfitChart) | **GET** v2.0/tradingaccount/{id}/charts/profit/absolute | Trading account absolute profit chart
[**getBalanceChart**](TradingaccountApi.md#getBalanceChart) | **GET** v2.0/tradingaccount/{id}/charts/balance | Trading account balance chart
[**getOpenTrades**](TradingaccountApi.md#getOpenTrades) | **GET** v2.0/tradingaccount/{id}/trades/open | Trading account open positions
[**getProfitPercentCharts**](TradingaccountApi.md#getProfitPercentCharts) | **GET** v2.0/tradingaccount/{id}/charts/profit/percent | Trading account profit percent charts
[**getTrades**](TradingaccountApi.md#getTrades) | **GET** v2.0/tradingaccount/{id}/trades | Trading account trades
[**getTradingAccountDetails**](TradingaccountApi.md#getTradingAccountDetails) | **GET** v2.0/tradingaccount/{id} | Trading account details

<a name="exportTrades"></a>
# **exportTrades**
> String exportTrades(id, authorization, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take)

Export trade history

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradingaccountApi;


TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String symbol = "symbol_example"; // String | 
String sorting = "sorting_example"; // String | 
UUID accountId = new UUID(); // UUID | 
String accountCurrency = "accountCurrency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    String result = apiInstance.exportTrades(id, authorization, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take);
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
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **symbol** | **String**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc, ByCommissionAsc, ByCommissionDesc, BySwapAsc, BySwapDesc]
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getAbsoluteProfitChart"></a>
# **getAbsoluteProfitChart**
> AbsoluteProfitChart getAbsoluteProfitChart(id, authorization, dateFrom, dateTo, maxPointCount, currency)

Trading account absolute profit chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradingaccountApi;


TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
String currency = "currency_example"; // String | 
try {
    AbsoluteProfitChart result = apiInstance.getAbsoluteProfitChart(id, authorization, dateFrom, dateTo, maxPointCount, currency);
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
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **maxPointCount** | **Integer**|  | [optional]
 **currency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**AbsoluteProfitChart**](AbsoluteProfitChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getBalanceChart"></a>
# **getBalanceChart**
> AccountBalanceChart getBalanceChart(id, authorization, dateFrom, dateTo, maxPointCount, currency)

Trading account balance chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradingaccountApi;


TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
String currency = "currency_example"; // String | 
try {
    AccountBalanceChart result = apiInstance.getBalanceChart(id, authorization, dateFrom, dateTo, maxPointCount, currency);
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
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **maxPointCount** | **Integer**|  | [optional]
 **currency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**AccountBalanceChart**](AccountBalanceChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getOpenTrades"></a>
# **getOpenTrades**
> TradesViewModel getOpenTrades(id, authorization, sorting, symbol, accountId, accountCurrency, skip, take)

Trading account open positions

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradingaccountApi;


TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
String sorting = "sorting_example"; // String | 
String symbol = "symbol_example"; // String | 
UUID accountId = new UUID(); // UUID | 
String accountCurrency = "accountCurrency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesViewModel result = apiInstance.getOpenTrades(id, authorization, sorting, symbol, accountId, accountCurrency, skip, take);
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
 **authorization** | **String**| JWT access token |
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc, ByCommissionAsc, ByCommissionDesc, BySwapAsc, BySwapDesc]
 **symbol** | **String**|  | [optional]
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesViewModel**](TradesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProfitPercentCharts"></a>
# **getProfitPercentCharts**
> AccountProfitPercentCharts getProfitPercentCharts(id, authorization, dateFrom, dateTo, maxPointCount, currency, currencies)

Trading account profit percent charts

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradingaccountApi;


TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
String currency = "currency_example"; // String | 
List<Object> currencies = null; // List<Object> | 
try {
    AccountProfitPercentCharts result = apiInstance.getProfitPercentCharts(id, authorization, dateFrom, dateTo, maxPointCount, currency, currencies);
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
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **maxPointCount** | **Integer**|  | [optional]
 **currency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
 **currencies** | [**List&lt;Object&gt;**](Object.md)|  | [optional]

### Return type

[**AccountProfitPercentCharts**](AccountProfitPercentCharts.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getTrades"></a>
# **getTrades**
> TradesSignalViewModel getTrades(id, authorization, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take)

Trading account trades

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradingaccountApi;


TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String symbol = "symbol_example"; // String | 
String sorting = "sorting_example"; // String | 
UUID accountId = new UUID(); // UUID | 
String accountCurrency = "accountCurrency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesSignalViewModel result = apiInstance.getTrades(id, authorization, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take);
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
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **symbol** | **String**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc, ByCommissionAsc, ByCommissionDesc, BySwapAsc, BySwapDesc]
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesSignalViewModel**](TradesSignalViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getTradingAccountDetails"></a>
# **getTradingAccountDetails**
> PrivateTradingAccountFull getTradingAccountDetails(id, authorization)

Trading account details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradingaccountApi;


TradingaccountApi apiInstance = new TradingaccountApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    PrivateTradingAccountFull result = apiInstance.getTradingAccountDetails(id, authorization);
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
 **authorization** | **String**| JWT access token |

### Return type

[**PrivateTradingAccountFull**](PrivateTradingAccountFull.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

