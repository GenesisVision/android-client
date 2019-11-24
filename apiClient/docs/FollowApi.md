# FollowApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAbsoluteProfitChart**](FollowApi.md#getAbsoluteProfitChart) | **GET** v2.0/follow/{id}/charts/profit/absolute | Follow absolute profit chart
[**getBalanceChart**](FollowApi.md#getBalanceChart) | **GET** v2.0/follow/{id}/charts/balance | Follow balance chart
[**getFollowAssetDetails**](FollowApi.md#getFollowAssetDetails) | **GET** v2.0/follow/{id} | Follow asset details
[**getFollowAssets**](FollowApi.md#getFollowAssets) | **GET** v2.0/follow | Follow assets
[**getProfitPercentCharts**](FollowApi.md#getProfitPercentCharts) | **GET** v2.0/follow/{id}/charts/profit/percent | Follow profit percent charts

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
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]

### Return type

[**AbsoluteProfitChart**](AbsoluteProfitChart.md)

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
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]

### Return type

[**AccountBalanceChart**](AccountBalanceChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFollowAssetDetails"></a>
# **getFollowAssetDetails**
> FollowDetailsFull getFollowAssetDetails(id, authorization)

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
    FollowDetailsFull result = apiInstance.getFollowAssetDetails(id, authorization);
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

[**FollowDetailsFull**](FollowDetailsFull.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFollowAssets"></a>
# **getFollowAssets**
> ItemsViewModelFollowDetailsList getFollowAssets(authorization, sorting, showIn, tags, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take)

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
    ItemsViewModelFollowDetailsList result = apiInstance.getFollowAssets(authorization, sorting, showIn, tags, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take);
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
 **showIn** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
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

[**ItemsViewModelFollowDetailsList**](ItemsViewModelFollowDetailsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProfitPercentCharts"></a>
# **getProfitPercentCharts**
> FollowProfitPercentCharts getProfitPercentCharts(id, dateFrom, dateTo, maxPointCount, currency, currencies)

Follow profit percent charts

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
List<Object> currencies = null; // List<Object> | 
try {
    FollowProfitPercentCharts result = apiInstance.getProfitPercentCharts(id, dateFrom, dateTo, maxPointCount, currency, currencies);
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
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **currencies** | [**List&lt;Object&gt;**](Object.md)|  | [optional]

### Return type

[**FollowProfitPercentCharts**](FollowProfitPercentCharts.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

