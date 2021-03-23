# FundsApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addToFavorites**](FundsApi.md#addToFavorites) | **POST** v2.0/funds/{id}/favorite/add | Add to favorites
[**getFundAbsoluteProfitChart**](FundsApi.md#getFundAbsoluteProfitChart) | **GET** v2.0/funds/{id}/charts/profit/absolute | Fund absolute profit chart
[**getFundBalanceChart**](FundsApi.md#getFundBalanceChart) | **GET** v2.0/funds/{id}/charts/balance | Fund balance chart
[**getFundDetails**](FundsApi.md#getFundDetails) | **GET** v2.0/funds/{id} | Fund details
[**getFundProfitPercentCharts**](FundsApi.md#getFundProfitPercentCharts) | **GET** v2.0/funds/{id}/charts/profit/percent | Fund profit percent charts
[**getFunds**](FundsApi.md#getFunds) | **GET** v2.0/funds | Funds list
[**getFundsHistoryEvents**](FundsApi.md#getFundsHistoryEvents) | **GET** v2.0/funds/{id}/history | Get funds trading events
[**getLastChallengeWinner**](FundsApi.md#getLastChallengeWinner) | **GET** v2.0/funds/challenge/winner | Get last weekly funds challenge winner
[**getReallocatingHistory**](FundsApi.md#getReallocatingHistory) | **GET** v2.0/funds/{id}/reallocations | Get history of asset part update requests
[**removeFromFavorites**](FundsApi.md#removeFromFavorites) | **POST** v2.0/funds/{id}/favorite/remove | Remove from favorites

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
//import io.swagger.client.api.FundsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.addToFavorites(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#addToFavorites");
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

<a name="getFundAbsoluteProfitChart"></a>
# **getFundAbsoluteProfitChart**
> AbsoluteProfitChart getFundAbsoluteProfitChart(id, dateFrom, dateTo, maxPointCount, currency)

Fund absolute profit chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FundsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
Currency currency = new Currency(); // Currency | 
try {
    AbsoluteProfitChart result = apiInstance.getFundAbsoluteProfitChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getFundAbsoluteProfitChart");
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

<a name="getFundBalanceChart"></a>
# **getFundBalanceChart**
> FundBalanceChart getFundBalanceChart(id, dateFrom, dateTo, maxPointCount, currency)

Fund balance chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FundsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
Currency currency = new Currency(); // Currency | 
try {
    FundBalanceChart result = apiInstance.getFundBalanceChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getFundBalanceChart");
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

[**FundBalanceChart**](FundBalanceChart.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFundDetails"></a>
# **getFundDetails**
> FundDetailsFull getFundDetails(id, currency, logoQuality)

Fund details

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FundsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FundsApi apiInstance = new FundsApi();
String id = "id_example"; // String | 
Currency currency = new Currency(); // Currency | 
ImageQuality logoQuality = new ImageQuality(); // ImageQuality | 
try {
    FundDetailsFull result = apiInstance.getFundDetails(id, currency, logoQuality);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getFundDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **currency** | [**Currency**](.md)|  | [optional]
 **logoQuality** | [**ImageQuality**](.md)|  | [optional]

### Return type

[**FundDetailsFull**](FundDetailsFull.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFundProfitPercentCharts"></a>
# **getFundProfitPercentCharts**
> FundProfitPercentCharts getFundProfitPercentCharts(id, dateFrom, dateTo, maxPointCount, currency, currencies, chartAssetsCount)

Fund profit percent charts

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FundsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
Currency currency = new Currency(); // Currency | 
List<Currency> currencies = Arrays.asList(new Currency()); // List<Currency> | 
Integer chartAssetsCount = 3; // Integer | 
try {
    FundProfitPercentCharts result = apiInstance.getFundProfitPercentCharts(id, dateFrom, dateTo, maxPointCount, currency, currencies, chartAssetsCount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getFundProfitPercentCharts");
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
 **chartAssetsCount** | **Integer**|  | [optional] [default to 3]

### Return type

[**FundProfitPercentCharts**](FundProfitPercentCharts.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFunds"></a>
# **getFunds**
> FundDetailsListItemItemsViewModel getFunds(sorting, showIn, assets, investorId, includeWithInvestments, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skipStatistic, skip, take)

Funds list

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FundsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FundsApi apiInstance = new FundsApi();
FundsFilterSorting sorting = new FundsFilterSorting(); // FundsFilterSorting | 
Currency showIn = new Currency(); // Currency | 
List<String> assets = Arrays.asList("assets_example"); // List<String> | 
UUID investorId = new UUID(); // UUID | 
Boolean includeWithInvestments = true; // Boolean | 
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
    FundDetailsListItemItemsViewModel result = apiInstance.getFunds(sorting, showIn, assets, investorId, includeWithInvestments, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skipStatistic, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getFunds");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sorting** | [**FundsFilterSorting**](.md)|  | [optional]
 **showIn** | [**Currency**](.md)|  | [optional]
 **assets** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **investorId** | [**UUID**](.md)|  | [optional]
 **includeWithInvestments** | **Boolean**|  | [optional]
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

[**FundDetailsListItemItemsViewModel**](FundDetailsListItemItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFundsHistoryEvents"></a>
# **getFundsHistoryEvents**
> FundHistoryEventViewModelItemsViewModel getFundsHistoryEvents(id, dateFrom, dateTo, eventsType, skip, take)

Get funds trading events

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FundsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
FundHistoryEventFilterType eventsType = new FundHistoryEventFilterType(); // FundHistoryEventFilterType | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    FundHistoryEventViewModelItemsViewModel result = apiInstance.getFundsHistoryEvents(id, dateFrom, dateTo, eventsType, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getFundsHistoryEvents");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **eventsType** | [**FundHistoryEventFilterType**](.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**FundHistoryEventViewModelItemsViewModel**](FundHistoryEventViewModelItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getLastChallengeWinner"></a>
# **getLastChallengeWinner**
> FundDetailsListItem getLastChallengeWinner(chartPointsCount)

Get last weekly funds challenge winner

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FundsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FundsApi apiInstance = new FundsApi();
Integer chartPointsCount = 56; // Integer | 
try {
    FundDetailsListItem result = apiInstance.getLastChallengeWinner(chartPointsCount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getLastChallengeWinner");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **chartPointsCount** | **Integer**|  | [optional]

### Return type

[**FundDetailsListItem**](FundDetailsListItem.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getReallocatingHistory"></a>
# **getReallocatingHistory**
> ReallocationModelItemsViewModel getReallocatingHistory(id, dateFrom, dateTo, skip, take)

Get history of asset part update requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FundsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ReallocationModelItemsViewModel result = apiInstance.getReallocatingHistory(id, dateFrom, dateTo, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getReallocatingHistory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ReallocationModelItemsViewModel**](ReallocationModelItemsViewModel.md)

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
//import io.swagger.client.api.FundsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.removeFromFavorites(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#removeFromFavorites");
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

