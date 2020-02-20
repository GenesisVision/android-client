# FollowApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addToFavorites**](FollowApi.md#addToFavorites) | **POST** v2.0/follow/{id}/favorite/add | Add to favorites
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
> Void addToFavorites(id, authorization)

Add to favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FollowApi;


FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.addToFavorites(id, authorization);
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
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

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
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FollowApi;


FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
String currency = "currency_example"; // String | 
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
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**AbsoluteProfitChart**](AbsoluteProfitChart.md)

### Authorization

No authorization required

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
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FollowApi;


FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String symbol = "symbol_example"; // String | 
String sorting = "sorting_example"; // String | 
UUID accountId = new UUID(); // UUID | 
String accountCurrency = "accountCurrency_example"; // String | 
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
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc, ByCommissionAsc, ByCommissionDesc, BySwapAsc, BySwapDesc]
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **isFollow** | **Boolean**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesSignalViewModel**](TradesSignalViewModel.md)

### Authorization

No authorization required

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
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FollowApi;


FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
String currency = "currency_example"; // String | 
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
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**AccountBalanceChart**](AccountBalanceChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFollowAssetDetails"></a>
# **getFollowAssetDetails**
> ProgramFollowDetailsFull getFollowAssetDetails(id, authorization)

Follow asset details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FollowApi;


FollowApi apiInstance = new FollowApi();
String id = "id_example"; // String | 
String authorization = "authorization_example"; // String | 
try {
    ProgramFollowDetailsFull result = apiInstance.getFollowAssetDetails(id, authorization);
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
 **authorization** | **String**|  | [optional]

### Return type

[**ProgramFollowDetailsFull**](ProgramFollowDetailsFull.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFollowAssets"></a>
# **getFollowAssets**
> ItemsViewModelFollowDetailsListItem getFollowAssets(authorization, sorting, showIn, tags, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take)

Follow assets

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FollowApi;


FollowApi apiInstance = new FollowApi();
String authorization = "authorization_example"; // String | 
String sorting = "sorting_example"; // String | 
String showIn = "showIn_example"; // String | 
List<String> tags = Arrays.asList("tags_example"); // List<String> | 
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
    ItemsViewModelFollowDetailsListItem result = apiInstance.getFollowAssets(authorization, sorting, showIn, tags, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FollowApi#getFollowAssets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByTitleAsc, ByTitleDesc, BySubscribersAsc, BySubscribersDesc, ByTradesAsc, ByTradesDesc, ByDrawdownAsc, ByDrawdownDesc, ByProfitAsc, ByProfitDesc, ByNewAsc, ByNewDesc]
 **showIn** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **tags** | [**List&lt;String&gt;**](String.md)|  | [optional]
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

[**ItemsViewModelFollowDetailsListItem**](ItemsViewModelFollowDetailsListItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFollowSubscriptionsForAsset"></a>
# **getFollowSubscriptionsForAsset**
> ItemsViewModelSignalSubscription getFollowSubscriptionsForAsset(id, authorization, onlyActive)

Get subscriptions to current asset

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FollowApi;


FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
Boolean onlyActive = true; // Boolean | 
try {
    ItemsViewModelSignalSubscription result = apiInstance.getFollowSubscriptionsForAsset(id, authorization, onlyActive);
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
 **authorization** | **String**| JWT access token |
 **onlyActive** | **Boolean**|  | [optional]

### Return type

[**ItemsViewModelSignalSubscription**](ItemsViewModelSignalSubscription.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFollowSubscriptionsForOwnAccount"></a>
# **getFollowSubscriptionsForOwnAccount**
> ItemsViewModelSignalSubscription getFollowSubscriptionsForOwnAccount(id, authorization, onlyActive)

Get subscriptions for my trading account

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FollowApi;


FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
Boolean onlyActive = true; // Boolean | 
try {
    ItemsViewModelSignalSubscription result = apiInstance.getFollowSubscriptionsForOwnAccount(id, authorization, onlyActive);
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
 **authorization** | **String**| JWT access token |
 **onlyActive** | **Boolean**|  | [optional]

### Return type

[**ItemsViewModelSignalSubscription**](ItemsViewModelSignalSubscription.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProfitPercentCharts"></a>
# **getProfitPercentCharts**
> ProgramProfitPercentCharts getProfitPercentCharts(id, authorization, dateFrom, dateTo, maxPointCount, currency, currencies)

Follow profit percent charts

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FollowApi;


FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
String currency = "currency_example"; // String | 
List<Object> currencies = null; // List<Object> | 
try {
    ProgramProfitPercentCharts result = apiInstance.getProfitPercentCharts(id, authorization, dateFrom, dateTo, maxPointCount, currency, currencies);
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
 **authorization** | **String**|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **maxPointCount** | **Integer**|  | [optional]
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **currencies** | [**List&lt;Object&gt;**](Object.md)|  | [optional]

### Return type

[**ProgramProfitPercentCharts**](ProgramProfitPercentCharts.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="removeFromFavorites"></a>
# **removeFromFavorites**
> Void removeFromFavorites(id, authorization)

Remove from favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FollowApi;


FollowApi apiInstance = new FollowApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.removeFromFavorites(id, authorization);
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
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

