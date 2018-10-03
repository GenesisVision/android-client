# FundsApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10FundsAssetsGet**](FundsApi.md#v10FundsAssetsGet) | **GET** v1.0/funds/assets | Get all supported assets for funds
[**v10FundsByIdChartsBalanceGet**](FundsApi.md#v10FundsByIdChartsBalanceGet) | **GET** v1.0/funds/{id}/charts/balance | Fund balance chart
[**v10FundsByIdChartsProfitGet**](FundsApi.md#v10FundsByIdChartsProfitGet) | **GET** v1.0/funds/{id}/charts/profit | Fund profit chart
[**v10FundsByIdFavoriteAddPost**](FundsApi.md#v10FundsByIdFavoriteAddPost) | **POST** v1.0/funds/{id}/favorite/add | Add to favorites
[**v10FundsByIdFavoriteRemovePost**](FundsApi.md#v10FundsByIdFavoriteRemovePost) | **POST** v1.0/funds/{id}/favorite/remove | Remove from favorites
[**v10FundsByIdGet**](FundsApi.md#v10FundsByIdGet) | **GET** v1.0/funds/{id} | Funds details
[**v10FundsByIdRebalancingGet**](FundsApi.md#v10FundsByIdRebalancingGet) | **GET** v1.0/funds/{id}/rebalancing | Rebalancing history
[**v10FundsGet**](FundsApi.md#v10FundsGet) | **GET** v1.0/funds | Funds list


<a name="v10FundsAssetsGet"></a>
# **v10FundsAssetsGet**
> PlatformAssets v10FundsAssetsGet(authorization)

Get all supported assets for funds

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    PlatformAssets result = apiInstance.v10FundsAssetsGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsAssetsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**PlatformAssets**](PlatformAssets.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10FundsByIdChartsBalanceGet"></a>
# **v10FundsByIdChartsBalanceGet**
> FundBalanceChart v10FundsByIdChartsBalanceGet(id, dateFrom, dateTo, maxPointCount)

Fund balance chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
try {
    FundBalanceChart result = apiInstance.v10FundsByIdChartsBalanceGet(id, dateFrom, dateTo, maxPointCount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsByIdChartsBalanceGet");
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

### Return type

[**FundBalanceChart**](FundBalanceChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10FundsByIdChartsProfitGet"></a>
# **v10FundsByIdChartsProfitGet**
> FundProfitChart v10FundsByIdChartsProfitGet(id, dateFrom, dateTo, maxPointCount)

Fund profit chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
try {
    FundProfitChart result = apiInstance.v10FundsByIdChartsProfitGet(id, dateFrom, dateTo, maxPointCount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsByIdChartsProfitGet");
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

### Return type

[**FundProfitChart**](FundProfitChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10FundsByIdFavoriteAddPost"></a>
# **v10FundsByIdFavoriteAddPost**
> Void v10FundsByIdFavoriteAddPost(id, authorization)

Add to favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10FundsByIdFavoriteAddPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsByIdFavoriteAddPost");
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

<a name="v10FundsByIdFavoriteRemovePost"></a>
# **v10FundsByIdFavoriteRemovePost**
> Void v10FundsByIdFavoriteRemovePost(id, authorization)

Remove from favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10FundsByIdFavoriteRemovePost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsByIdFavoriteRemovePost");
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

<a name="v10FundsByIdGet"></a>
# **v10FundsByIdGet**
> FundDetailsFull v10FundsByIdGet(id, authorization, currencySecondary)

Funds details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | 
String currencySecondary = "currencySecondary_example"; // String | 
try {
    FundDetailsFull result = apiInstance.v10FundsByIdGet(id, authorization, currencySecondary);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsByIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**|  | [optional]
 **currencySecondary** | **String**|  | [optional] [enum: ETH, GVT, BTC, Undefined, ADA, USD, EUR]

### Return type

[**FundDetailsFull**](FundDetailsFull.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10FundsByIdRebalancingGet"></a>
# **v10FundsByIdRebalancingGet**
> RebalancesViewModel v10FundsByIdRebalancingGet(id, dateFrom, dateTo, skip, take)

Rebalancing history

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    RebalancesViewModel result = apiInstance.v10FundsByIdRebalancingGet(id, dateFrom, dateTo, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsByIdRebalancingGet");
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

[**RebalancesViewModel**](RebalancesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10FundsGet"></a>
# **v10FundsGet**
> FundsList v10FundsGet(authorization, sorting, currencySecondary, statisticDateFrom, statisticDateTo, chartPointsCount, mask, facetId, isFavorite, ids, skip, take)

Funds list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
String authorization = "authorization_example"; // String | 
String sorting = "sorting_example"; // String | 
String currencySecondary = "currencySecondary_example"; // String | 
DateTime statisticDateFrom = new DateTime(); // DateTime | 
DateTime statisticDateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String mask = "mask_example"; // String | 
UUID facetId = new UUID(); // UUID | 
Boolean isFavorite = true; // Boolean | 
List<UUID> ids = Arrays.asList(new UUID()); // List<UUID> | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    FundsList result = apiInstance.v10FundsGet(authorization, sorting, currencySecondary, statisticDateFrom, statisticDateTo, chartPointsCount, mask, facetId, isFavorite, ids, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByProfitAsc, ByProfitDesc, ByDrawdownAsc, ByDrawdownDesc, ByInvestorsAsc, ByInvestorsDesc, ByTitleAsc, ByTitleDesc, ByBalanceAsc, ByBalanceDesc]
 **currencySecondary** | **String**|  | [optional] [enum: ETH, GVT, BTC, Undefined, ADA, USD, EUR]
 **statisticDateFrom** | **DateTime**|  | [optional]
 **statisticDateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **mask** | **String**|  | [optional]
 **facetId** | [**UUID**](.md)|  | [optional]
 **isFavorite** | **Boolean**|  | [optional]
 **ids** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**FundsList**](FundsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

