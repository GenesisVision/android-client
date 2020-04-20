# ProgramsApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addToFavorites**](ProgramsApi.md#addToFavorites) | **POST** v2.0/programs/{id}/favorite/add | Add to favorites
[**closeAssetTrade**](ProgramsApi.md#closeAssetTrade) | **POST** v2.0/programs/{id}/trades/close | Manually close trade by symbol for asset
[**exportProgramPeriods**](ProgramsApi.md#exportProgramPeriods) | **GET** v2.0/programs/{id}/periods/export | Export periods
[**exportProgramPeriodsFinStatistic**](ProgramsApi.md#exportProgramPeriodsFinStatistic) | **GET** v2.0/programs/{id}/periods/export/statistic | Export period financial statistic
[**exportProgramTrades**](ProgramsApi.md#exportProgramTrades) | **GET** v2.0/programs/{id}/trades/export | Export trade history
[**getAssetTrades**](ProgramsApi.md#getAssetTrades) | **GET** v2.0/programs/{id}/trades | Trade history
[**getProgramAbsoluteProfitChart**](ProgramsApi.md#getProgramAbsoluteProfitChart) | **GET** v2.0/programs/{id}/charts/profit/absolute | Program absolute profit chart
[**getProgramBalanceChart**](ProgramsApi.md#getProgramBalanceChart) | **GET** v2.0/programs/{id}/charts/balance | Program balance chart
[**getProgramDetails**](ProgramsApi.md#getProgramDetails) | **GET** v2.0/programs/{id} | Program details
[**getProgramOpenTrades**](ProgramsApi.md#getProgramOpenTrades) | **GET** v2.0/programs/{id}/trades/open | Open positions
[**getProgramPeriods**](ProgramsApi.md#getProgramPeriods) | **GET** v2.0/programs/{id}/periods | Program periods
[**getProgramProfitPercentCharts**](ProgramsApi.md#getProgramProfitPercentCharts) | **GET** v2.0/programs/{id}/charts/profit/percent | Program profit percent charts
[**getProgramSubscribers**](ProgramsApi.md#getProgramSubscribers) | **GET** v2.0/programs/{id}/subscribers | Signal subscribers
[**getPrograms**](ProgramsApi.md#getPrograms) | **GET** v2.0/programs | Programs list
[**removeFromFavorites**](ProgramsApi.md#removeFromFavorites) | **POST** v2.0/programs/{id}/favorite/remove | Remove from favorites

<a name="addToFavorites"></a>
# **addToFavorites**
> Void addToFavorites(id)

Add to favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.addToFavorites(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#addToFavorites");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="closeAssetTrade"></a>
# **closeAssetTrade**
> Void closeAssetTrade(id, symbol)

Manually close trade by symbol for asset

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
try {
    Void result = apiInstance.closeAssetTrade(id, symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#closeAssetTrade");
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

<a name="exportProgramPeriods"></a>
# **exportProgramPeriods**
> byte[] exportProgramPeriods(id, dateFrom, dateTo, numberMin, numberMax, status, skip, take)

Export periods

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
String id = "id_example"; // String | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer numberMin = 56; // Integer | 
Integer numberMax = 56; // Integer | 
PeriodStatus status = new PeriodStatus(); // PeriodStatus | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    byte[] result = apiInstance.exportProgramPeriods(id, dateFrom, dateTo, numberMin, numberMax, status, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#exportProgramPeriods");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **numberMin** | **Integer**|  | [optional]
 **numberMax** | **Integer**|  | [optional]
 **status** | [**PeriodStatus**](.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

**byte[]**

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="exportProgramPeriodsFinStatistic"></a>
# **exportProgramPeriodsFinStatistic**
> byte[] exportProgramPeriodsFinStatistic(id, dateFrom, dateTo, numberMin, numberMax, status, skip, take)

Export period financial statistic

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
String id = "id_example"; // String | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer numberMin = 56; // Integer | 
Integer numberMax = 56; // Integer | 
PeriodStatus status = new PeriodStatus(); // PeriodStatus | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    byte[] result = apiInstance.exportProgramPeriodsFinStatistic(id, dateFrom, dateTo, numberMin, numberMax, status, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#exportProgramPeriodsFinStatistic");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **numberMin** | **Integer**|  | [optional]
 **numberMax** | **Integer**|  | [optional]
 **status** | [**PeriodStatus**](.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

**byte[]**

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="exportProgramTrades"></a>
# **exportProgramTrades**
> byte[] exportProgramTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, isFollow, skip, take)

Export trade history

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
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
    byte[] result = apiInstance.exportProgramTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, isFollow, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#exportProgramTrades");
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

<a name="getAssetTrades"></a>
# **getAssetTrades**
> TradesSignalViewModel getAssetTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, isFollow, skip, take)

Trade history

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
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
    TradesSignalViewModel result = apiInstance.getAssetTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, isFollow, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getAssetTrades");
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

<a name="getProgramAbsoluteProfitChart"></a>
# **getProgramAbsoluteProfitChart**
> AbsoluteProfitChart getProgramAbsoluteProfitChart(id, dateFrom, dateTo, maxPointCount, currency)

Program absolute profit chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
Currency currency = new Currency(); // Currency | 
try {
    AbsoluteProfitChart result = apiInstance.getProgramAbsoluteProfitChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramAbsoluteProfitChart");
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

<a name="getProgramBalanceChart"></a>
# **getProgramBalanceChart**
> ProgramBalanceChart getProgramBalanceChart(id, dateFrom, dateTo, maxPointCount, currency)

Program balance chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
Currency currency = new Currency(); // Currency | 
try {
    ProgramBalanceChart result = apiInstance.getProgramBalanceChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramBalanceChart");
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

[**ProgramBalanceChart**](ProgramBalanceChart.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramDetails"></a>
# **getProgramDetails**
> ProgramFollowDetailsFull getProgramDetails(id, logoQuality)

Program details

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
String id = "id_example"; // String | 
ImageQuality logoQuality = new ImageQuality(); // ImageQuality | 
try {
    ProgramFollowDetailsFull result = apiInstance.getProgramDetails(id, logoQuality);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **logoQuality** | [**ImageQuality**](.md)|  | [optional]

### Return type

[**ProgramFollowDetailsFull**](ProgramFollowDetailsFull.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramOpenTrades"></a>
# **getProgramOpenTrades**
> TradesViewModel getProgramOpenTrades(id, sorting, symbol, accountId, accountCurrency, skip, take)

Open positions

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
TradeSorting sorting = new TradeSorting(); // TradeSorting | 
String symbol = "symbol_example"; // String | 
UUID accountId = new UUID(); // UUID | 
Currency accountCurrency = new Currency(); // Currency | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesViewModel result = apiInstance.getProgramOpenTrades(id, sorting, symbol, accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramOpenTrades");
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

<a name="getProgramPeriods"></a>
# **getProgramPeriods**
> ProgramPeriodsViewModel getProgramPeriods(id, dateFrom, dateTo, numberMin, numberMax, status, skip, take)

Program periods

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
String id = "id_example"; // String | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer numberMin = 56; // Integer | 
Integer numberMax = 56; // Integer | 
PeriodStatus status = new PeriodStatus(); // PeriodStatus | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ProgramPeriodsViewModel result = apiInstance.getProgramPeriods(id, dateFrom, dateTo, numberMin, numberMax, status, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramPeriods");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **numberMin** | **Integer**|  | [optional]
 **numberMax** | **Integer**|  | [optional]
 **status** | [**PeriodStatus**](.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ProgramPeriodsViewModel**](ProgramPeriodsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramProfitPercentCharts"></a>
# **getProgramProfitPercentCharts**
> ProgramProfitPercentCharts getProgramProfitPercentCharts(id, dateFrom, dateTo, maxPointCount, currency, currencies)

Program profit percent charts

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
Currency currency = new Currency(); // Currency | 
List<Currency> currencies = Arrays.asList(new Currency()); // List<Currency> | 
try {
    ProgramProfitPercentCharts result = apiInstance.getProgramProfitPercentCharts(id, dateFrom, dateTo, maxPointCount, currency, currencies);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramProfitPercentCharts");
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

[**ProgramProfitPercentCharts**](ProgramProfitPercentCharts.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramSubscribers"></a>
# **getProgramSubscribers**
> SignalProviderSubscribers getProgramSubscribers(id, status, skip, take)

Signal subscribers

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
DashboardActionStatus status = new DashboardActionStatus(); // DashboardActionStatus | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    SignalProviderSubscribers result = apiInstance.getProgramSubscribers(id, status, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramSubscribers");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **status** | [**DashboardActionStatus**](.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**SignalProviderSubscribers**](SignalProviderSubscribers.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getPrograms"></a>
# **getPrograms**
> ProgramDetailsListItemItemsViewModel getPrograms(sorting, showIn, tags, programCurrency, levelMin, levelMax, levelsSet, includeWithInvestments, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take)

Programs list

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
ProgramsFilterSorting sorting = new ProgramsFilterSorting(); // ProgramsFilterSorting | 
Currency showIn = new Currency(); // Currency | 
List<String> tags = Arrays.asList("tags_example"); // List<String> | 
Currency programCurrency = new Currency(); // Currency | 
Integer levelMin = 56; // Integer | 
Integer levelMax = 56; // Integer | 
List<Integer> levelsSet = Arrays.asList(56); // List<Integer> | 
Boolean includeWithInvestments = true; // Boolean | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String facetId = "facetId_example"; // String | 
String mask = "mask_example"; // String | 
UUID ownerId = new UUID(); // UUID | 
Boolean showFavorites = true; // Boolean | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ProgramDetailsListItemItemsViewModel result = apiInstance.getPrograms(sorting, showIn, tags, programCurrency, levelMin, levelMax, levelsSet, includeWithInvestments, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getPrograms");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sorting** | [**ProgramsFilterSorting**](.md)|  | [optional]
 **showIn** | [**Currency**](.md)|  | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **programCurrency** | [**Currency**](.md)|  | [optional]
 **levelMin** | **Integer**|  | [optional]
 **levelMax** | **Integer**|  | [optional]
 **levelsSet** | [**List&lt;Integer&gt;**](Integer.md)|  | [optional]
 **includeWithInvestments** | **Boolean**|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **facetId** | **String**|  | [optional]
 **mask** | **String**|  | [optional]
 **ownerId** | [**UUID**](.md)|  | [optional]
 **showFavorites** | **Boolean**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ProgramDetailsListItemItemsViewModel**](ProgramDetailsListItemItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="removeFromFavorites"></a>
# **removeFromFavorites**
> Void removeFromFavorites(id)

Remove from favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProgramsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.removeFromFavorites(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#removeFromFavorites");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

