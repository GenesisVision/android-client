# DashboardApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getChart**](DashboardApi.md#getChart) | **GET** v2.0/dashboard/chart | 
[**getChartAssets**](DashboardApi.md#getChartAssets) | **GET** v2.0/dashboard/chart/assets | Active assets for chart
[**getDashboardSummary**](DashboardApi.md#getDashboardSummary) | **GET** v2.0/dashboard/summary | 
[**getExchangeAccountsCredentials**](DashboardApi.md#getExchangeAccountsCredentials) | **GET** v2.0/dashboard/trading/exchange/credentials/all | 
[**getHoldings**](DashboardApi.md#getHoldings) | **GET** v2.0/dashboard/holdings | 
[**getInvestingDetails**](DashboardApi.md#getInvestingDetails) | **GET** v2.0/dashboard/investing | 
[**getInvestingFunds**](DashboardApi.md#getInvestingFunds) | **GET** v2.0/dashboard/investing/funds | 
[**getInvestingPrograms**](DashboardApi.md#getInvestingPrograms) | **GET** v2.0/dashboard/investing/programs | 
[**getMostProfitableAssets**](DashboardApi.md#getMostProfitableAssets) | **GET** v2.0/dashboard/trading/mostprofitable | 
[**getPortfolio**](DashboardApi.md#getPortfolio) | **GET** v2.0/dashboard/portfolio | Money distribution in percents
[**getPrivateTradingAssets**](DashboardApi.md#getPrivateTradingAssets) | **GET** v2.0/dashboard/trading/private | 
[**getPublicTradingAssets**](DashboardApi.md#getPublicTradingAssets) | **GET** v2.0/dashboard/trading/public | 
[**getRecommendations**](DashboardApi.md#getRecommendations) | **GET** v2.0/dashboard/recommendations | Recommended assets to invest (programs, funds and follows). Funds in passed currency
[**getSelfManagedFunds**](DashboardApi.md#getSelfManagedFunds) | **GET** v2.0/dashboard/trading/private/selfmanaged | 
[**getTradingDetails**](DashboardApi.md#getTradingDetails) | **GET** v2.0/dashboard/trading | 

<a name="getChart"></a>
# **getChart**
> DashboardChart getChart(assets, dateFrom, dateTo, chartPointsCount, showIn, skipStatistic)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
List<UUID> assets = Arrays.asList(new UUID()); // List<UUID> | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
Currency showIn = new Currency(); // Currency | 
Boolean skipStatistic = true; // Boolean | 
try {
    DashboardChart result = apiInstance.getChart(assets, dateFrom, dateTo, chartPointsCount, showIn, skipStatistic);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getChart");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **assets** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **showIn** | [**Currency**](.md)|  | [optional]
 **skipStatistic** | **Boolean**|  | [optional]

### Return type

[**DashboardChart**](DashboardChart.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getChartAssets"></a>
# **getChartAssets**
> DashboardChartAssets getChartAssets()

Active assets for chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
try {
    DashboardChartAssets result = apiInstance.getChartAssets();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getChartAssets");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**DashboardChartAssets**](DashboardChartAssets.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getDashboardSummary"></a>
# **getDashboardSummary**
> DashboardSummary getDashboardSummary(currency)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
Currency currency = new Currency(); // Currency | 
try {
    DashboardSummary result = apiInstance.getDashboardSummary(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getDashboardSummary");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | [**Currency**](.md)|  | [optional]

### Return type

[**DashboardSummary**](DashboardSummary.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getExchangeAccountsCredentials"></a>
# **getExchangeAccountsCredentials**
> ExchangeAssetItemsViewModel getExchangeAccountsCredentials(brokerId)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
UUID brokerId = new UUID(); // UUID | 
try {
    ExchangeAssetItemsViewModel result = apiInstance.getExchangeAccountsCredentials(brokerId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getExchangeAccountsCredentials");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **brokerId** | [**UUID**](.md)|  | [optional]

### Return type

[**ExchangeAssetItemsViewModel**](ExchangeAssetItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getHoldings"></a>
# **getHoldings**
> DashboardAssets getHoldings(topAssetsCount)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
Integer topAssetsCount = 56; // Integer | 
try {
    DashboardAssets result = apiInstance.getHoldings(topAssetsCount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getHoldings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **topAssetsCount** | **Integer**|  | [optional]

### Return type

[**DashboardAssets**](DashboardAssets.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getInvestingDetails"></a>
# **getInvestingDetails**
> DashboardInvestingDetails getInvestingDetails(currency, eventsTake)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
Currency currency = new Currency(); // Currency | 
Integer eventsTake = 56; // Integer | 
try {
    DashboardInvestingDetails result = apiInstance.getInvestingDetails(currency, eventsTake);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getInvestingDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | [**Currency**](.md)|  | [optional]
 **eventsTake** | **Integer**|  | [optional]

### Return type

[**DashboardInvestingDetails**](DashboardInvestingDetails.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getInvestingFunds"></a>
# **getInvestingFunds**
> FundInvestingDetailsListItemsViewModel getInvestingFunds(sorting, showIn, status, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skipStatistic, skip, take)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
FundsFilterSorting sorting = new FundsFilterSorting(); // FundsFilterSorting | 
Currency showIn = new Currency(); // Currency | 
DashboardAssetStatus status = new DashboardAssetStatus(); // DashboardAssetStatus | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String facetId = "facetId_example"; // String | 
String mask = "mask_example"; // String | 
UUID ownerId = new UUID(); // UUID | 
Boolean showFavorites = true; // Boolean | 
Boolean skipStatistic = true; // Boolean | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    FundInvestingDetailsListItemsViewModel result = apiInstance.getInvestingFunds(sorting, showIn, status, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skipStatistic, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getInvestingFunds");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sorting** | [**FundsFilterSorting**](.md)|  | [optional]
 **showIn** | [**Currency**](.md)|  | [optional]
 **status** | [**DashboardAssetStatus**](.md)|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **facetId** | **String**|  | [optional]
 **mask** | **String**|  | [optional]
 **ownerId** | [**UUID**](.md)|  | [optional]
 **showFavorites** | **Boolean**|  | [optional]
 **skipStatistic** | **Boolean**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**FundInvestingDetailsListItemsViewModel**](FundInvestingDetailsListItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getInvestingPrograms"></a>
# **getInvestingPrograms**
> ProgramInvestingDetailsListItemsViewModel getInvestingPrograms(sorting, showIn, status, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skipStatistic, skip, take)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
ProgramsFilterSorting sorting = new ProgramsFilterSorting(); // ProgramsFilterSorting | 
Currency showIn = new Currency(); // Currency | 
DashboardAssetStatus status = new DashboardAssetStatus(); // DashboardAssetStatus | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String facetId = "facetId_example"; // String | 
String mask = "mask_example"; // String | 
UUID ownerId = new UUID(); // UUID | 
Boolean showFavorites = true; // Boolean | 
Boolean skipStatistic = true; // Boolean | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ProgramInvestingDetailsListItemsViewModel result = apiInstance.getInvestingPrograms(sorting, showIn, status, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skipStatistic, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getInvestingPrograms");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sorting** | [**ProgramsFilterSorting**](.md)|  | [optional]
 **showIn** | [**Currency**](.md)|  | [optional]
 **status** | [**DashboardAssetStatus**](.md)|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **facetId** | **String**|  | [optional]
 **mask** | **String**|  | [optional]
 **ownerId** | [**UUID**](.md)|  | [optional]
 **showFavorites** | **Boolean**|  | [optional]
 **skipStatistic** | **Boolean**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ProgramInvestingDetailsListItemsViewModel**](ProgramInvestingDetailsListItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getMostProfitableAssets"></a>
# **getMostProfitableAssets**
> DashboardTradingAssetItemsViewModel getMostProfitableAssets(dateFrom, dateTo, chartPointsCount, showIn, skipStatistic)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
Currency showIn = new Currency(); // Currency | 
Boolean skipStatistic = true; // Boolean | 
try {
    DashboardTradingAssetItemsViewModel result = apiInstance.getMostProfitableAssets(dateFrom, dateTo, chartPointsCount, showIn, skipStatistic);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getMostProfitableAssets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **showIn** | [**Currency**](.md)|  | [optional]
 **skipStatistic** | **Boolean**|  | [optional]

### Return type

[**DashboardTradingAssetItemsViewModel**](DashboardTradingAssetItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getPortfolio"></a>
# **getPortfolio**
> DashboardPortfolio getPortfolio()

Money distribution in percents

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
try {
    DashboardPortfolio result = apiInstance.getPortfolio();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getPortfolio");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**DashboardPortfolio**](DashboardPortfolio.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getPrivateTradingAssets"></a>
# **getPrivateTradingAssets**
> DashboardTradingAssetItemsViewModel getPrivateTradingAssets(dateFrom, dateTo, chartPointsCount, showIn, status, skipStatistic, skip, take)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
Currency showIn = new Currency(); // Currency | 
DashboardAssetStatus status = new DashboardAssetStatus(); // DashboardAssetStatus | 
Boolean skipStatistic = true; // Boolean | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    DashboardTradingAssetItemsViewModel result = apiInstance.getPrivateTradingAssets(dateFrom, dateTo, chartPointsCount, showIn, status, skipStatistic, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getPrivateTradingAssets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **showIn** | [**Currency**](.md)|  | [optional]
 **status** | [**DashboardAssetStatus**](.md)|  | [optional]
 **skipStatistic** | **Boolean**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**DashboardTradingAssetItemsViewModel**](DashboardTradingAssetItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getPublicTradingAssets"></a>
# **getPublicTradingAssets**
> DashboardTradingAssetItemsViewModel getPublicTradingAssets(dateFrom, dateTo, chartPointsCount, showIn, status, skipStatistic, skip, take)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
Currency showIn = new Currency(); // Currency | 
DashboardAssetStatus status = new DashboardAssetStatus(); // DashboardAssetStatus | 
Boolean skipStatistic = true; // Boolean | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    DashboardTradingAssetItemsViewModel result = apiInstance.getPublicTradingAssets(dateFrom, dateTo, chartPointsCount, showIn, status, skipStatistic, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getPublicTradingAssets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **showIn** | [**Currency**](.md)|  | [optional]
 **status** | [**DashboardAssetStatus**](.md)|  | [optional]
 **skipStatistic** | **Boolean**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**DashboardTradingAssetItemsViewModel**](DashboardTradingAssetItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getRecommendations"></a>
# **getRecommendations**
> CommonPublicAssetsViewModel getRecommendations(currency, take, onlyFollows)

Recommended assets to invest (programs, funds and follows). Funds in passed currency

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
Currency currency = new Currency(); // Currency | 
Integer take = 56; // Integer | 
Boolean onlyFollows = true; // Boolean | 
try {
    CommonPublicAssetsViewModel result = apiInstance.getRecommendations(currency, take, onlyFollows);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getRecommendations");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | [**Currency**](.md)|  | [optional]
 **take** | **Integer**|  | [optional]
 **onlyFollows** | **Boolean**|  | [optional]

### Return type

[**CommonPublicAssetsViewModel**](CommonPublicAssetsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getSelfManagedFunds"></a>
# **getSelfManagedFunds**
> DashboardTradingAssetItemsViewModel getSelfManagedFunds(dateFrom, dateTo, chartPointsCount, showIn, status, skipStatistic, skip, take)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
Currency showIn = new Currency(); // Currency | 
DashboardAssetStatus status = new DashboardAssetStatus(); // DashboardAssetStatus | 
Boolean skipStatistic = true; // Boolean | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    DashboardTradingAssetItemsViewModel result = apiInstance.getSelfManagedFunds(dateFrom, dateTo, chartPointsCount, showIn, status, skipStatistic, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getSelfManagedFunds");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **showIn** | [**Currency**](.md)|  | [optional]
 **status** | [**DashboardAssetStatus**](.md)|  | [optional]
 **skipStatistic** | **Boolean**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**DashboardTradingAssetItemsViewModel**](DashboardTradingAssetItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getTradingDetails"></a>
# **getTradingDetails**
> DashboardTradingDetails getTradingDetails(currency, eventsTake)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.DashboardApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

DashboardApi apiInstance = new DashboardApi();
Currency currency = new Currency(); // Currency | 
Integer eventsTake = 56; // Integer | 
try {
    DashboardTradingDetails result = apiInstance.getTradingDetails(currency, eventsTake);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getTradingDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | [**Currency**](.md)|  | [optional]
 **eventsTake** | **Integer**|  | [optional]

### Return type

[**DashboardTradingDetails**](DashboardTradingDetails.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

