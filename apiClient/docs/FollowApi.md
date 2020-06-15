# FollowApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addToFavorites**](FollowApi.md#addToFavorites) | **POST** v2.0/follow/{id}/favorite/add | Add to favorites
[**closeAssetTrade**](FollowApi.md#closeAssetTrade) | **POST** v2.0/follow/{id}/trades/close | Manually close trade by symbol for asset
[**getAbsoluteProfitChart**](FollowApi.md#getAbsoluteProfitChart) | **GET** v2.0/follow/{id}/charts/profit/absolute | Follow absolute profit chart
[**getAssetTrades**](FollowApi.md#getAssetTrades) | **GET** v2.0/follow/{id}/trades | Trade history
[**getBalanceChart**](FollowApi.md#getBalanceChart) | **GET** v2.0/follow/{id}/charts/balance | Follow balance chart
[**getFollowAssetDetails**](FollowApi.md#getFollowAssetDetails) | **GET** v2.0/follow/{id} | Follow asset details
[**getFollowAssets**](FollowApi.md#getFollowAssets) | **GET** v2.0/follow | Follow assets
[**getFollowSubscriptionsForAsset**](FollowApi.md#getFollowSubscriptionsForAsset) | **POST** v2.0/follow/{id}/subscriptions | Get subscriptions to current asset
[**getFollowSubscriptionsForOwnAccount**](FollowApi.md#getFollowSubscriptionsForOwnAccount) | **POST** v2.0/follow/account/own/{id}/subscriptions | Get subscriptions for my trading account
[**getProfitPercentCharts**](FollowApi.md#getProfitPercentCharts) | **GET** v2.0/follow/{id}/charts/profit/percent | Follow profit percent charts
[**removeFromFavorites**](FollowApi.md#removeFromFavorites) | **POST** v2.0/follow/{id}/favorite/remove | Remove from favorites

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
//import io.swagger.client.api.FollowApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.addToFavorites(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FollowApi#addToFavorites");
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
//import io.swagger.client.api.FollowApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
String symbol = "symbol_example"; // String | 
try {
    Void result = apiInstance.closeAssetTrade(id, symbol);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FollowApi#closeAssetTrade");
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

<a name="getAbsoluteProfitChart"></a>
# **getAbsoluteProfitChart**
> AbsoluteProfitChart getAbsoluteProfitChart(id, dateFrom, dateTo, maxPointCount, currency)

Follow absolute profit chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FollowApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
Currency currency = new Currency(); // Currency | 
try {
    AbsoluteProfitChart result = apiInstance.getAbsoluteProfitChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FollowApi#getAbsoluteProfitChart");
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
//import io.swagger.client.api.FollowApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FollowApi apiInstance = new FollowApi();
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
    System.err.println("Exception when calling FollowApi#getAssetTrades");
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

<a name="getBalanceChart"></a>
# **getBalanceChart**
> AccountBalanceChart getBalanceChart(id, dateFrom, dateTo, maxPointCount, currency)

Follow balance chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FollowApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
Currency currency = new Currency(); // Currency | 
try {
    AccountBalanceChart result = apiInstance.getBalanceChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FollowApi#getBalanceChart");
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

<a name="getFollowAssetDetails"></a>
# **getFollowAssetDetails**
> ProgramFollowDetailsFull getFollowAssetDetails(id, logoQuality)

Follow asset details

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FollowApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FollowApi apiInstance = new FollowApi();
String id = "id_example"; // String | 
ImageQuality logoQuality = new ImageQuality(); // ImageQuality | 
try {
    ProgramFollowDetailsFull result = apiInstance.getFollowAssetDetails(id, logoQuality);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FollowApi#getFollowAssetDetails");
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

<a name="getFollowAssets"></a>
# **getFollowAssets**
> FollowDetailsListItemItemsViewModel getFollowAssets(sorting, showIn, tags, subscriberId, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skipStatistic, skip, take)

Follow assets

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FollowApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FollowApi apiInstance = new FollowApi();
FollowFilterSorting sorting = new FollowFilterSorting(); // FollowFilterSorting | 
Currency showIn = new Currency(); // Currency | 
List<String> tags = Arrays.asList("tags_example"); // List<String> | 
UUID subscriberId = new UUID(); // UUID | 
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
    FollowDetailsListItemItemsViewModel result = apiInstance.getFollowAssets(sorting, showIn, tags, subscriberId, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skipStatistic, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FollowApi#getFollowAssets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sorting** | [**FollowFilterSorting**](.md)|  | [optional]
 **showIn** | [**Currency**](.md)|  | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **subscriberId** | [**UUID**](.md)|  | [optional]
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

[**FollowDetailsListItemItemsViewModel**](FollowDetailsListItemItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFollowSubscriptionsForAsset"></a>
# **getFollowSubscriptionsForAsset**
> SignalSubscriptionItemsViewModel getFollowSubscriptionsForAsset(id, onlyActive)

Get subscriptions to current asset

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FollowApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
Boolean onlyActive = true; // Boolean | 
try {
    SignalSubscriptionItemsViewModel result = apiInstance.getFollowSubscriptionsForAsset(id, onlyActive);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FollowApi#getFollowSubscriptionsForAsset");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **onlyActive** | **Boolean**|  | [optional]

### Return type

[**SignalSubscriptionItemsViewModel**](SignalSubscriptionItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFollowSubscriptionsForOwnAccount"></a>
# **getFollowSubscriptionsForOwnAccount**
> SignalSubscriptionItemsViewModel getFollowSubscriptionsForOwnAccount(id, onlyActive)

Get subscriptions for my trading account

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FollowApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
Boolean onlyActive = true; // Boolean | 
try {
    SignalSubscriptionItemsViewModel result = apiInstance.getFollowSubscriptionsForOwnAccount(id, onlyActive);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FollowApi#getFollowSubscriptionsForOwnAccount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **onlyActive** | **Boolean**|  | [optional]

### Return type

[**SignalSubscriptionItemsViewModel**](SignalSubscriptionItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProfitPercentCharts"></a>
# **getProfitPercentCharts**
> ProgramProfitPercentCharts getProfitPercentCharts(id, dateFrom, dateTo, maxPointCount, currency, currencies)

Follow profit percent charts

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FollowApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
Currency currency = new Currency(); // Currency | 
List<Currency> currencies = Arrays.asList(new Currency()); // List<Currency> | 
try {
    ProgramProfitPercentCharts result = apiInstance.getProfitPercentCharts(id, dateFrom, dateTo, maxPointCount, currency, currencies);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FollowApi#getProfitPercentCharts");
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
//import io.swagger.client.api.FollowApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.removeFromFavorites(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FollowApi#removeFromFavorites");
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

