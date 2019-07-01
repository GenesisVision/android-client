# FundsApi

All URIs are relative to *https://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10FundsAssetsGet**](FundsApi.md#v10FundsAssetsGet) | **GET** v1.0/funds/assets | Get all supported assets for funds
[**v10FundsByIdAssetsGet**](FundsApi.md#v10FundsByIdAssetsGet) | **GET** v1.0/funds/{id}/assets | Fund assets info
[**v10FundsByIdChartsBalanceGet**](FundsApi.md#v10FundsByIdChartsBalanceGet) | **GET** v1.0/funds/{id}/charts/balance | Fund balance chart
[**v10FundsByIdChartsProfitGet**](FundsApi.md#v10FundsByIdChartsProfitGet) | **GET** v1.0/funds/{id}/charts/profit | Fund profit chart
[**v10FundsByIdFavoriteAddPost**](FundsApi.md#v10FundsByIdFavoriteAddPost) | **POST** v1.0/funds/{id}/favorite/add | Add to favorites
[**v10FundsByIdFavoriteRemovePost**](FundsApi.md#v10FundsByIdFavoriteRemovePost) | **POST** v1.0/funds/{id}/favorite/remove | Remove from favorites
[**v10FundsByIdGet**](FundsApi.md#v10FundsByIdGet) | **GET** v1.0/funds/{id} | Funds details
[**v10FundsByIdReallocationsGet**](FundsApi.md#v10FundsByIdReallocationsGet) | **GET** v1.0/funds/{id}/reallocations | Get history of asset part update requests.
[**v10FundsGet**](FundsApi.md#v10FundsGet) | **GET** v1.0/funds | Funds list
[**v10FundsSetsGet**](FundsApi.md#v10FundsSetsGet) | **GET** v1.0/funds/sets | Fund sets


<a name="v10FundsAssetsGet"></a>
# **v10FundsAssetsGet**
> PlatformAssets v10FundsAssetsGet()

Get all supported assets for funds

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
try {
    PlatformAssets result = apiInstance.v10FundsAssetsGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsAssetsGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**PlatformAssets**](PlatformAssets.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10FundsByIdAssetsGet"></a>
# **v10FundsByIdAssetsGet**
> FundAssetsListInfo v10FundsByIdAssetsGet(id)

Fund assets info

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
try {
    FundAssetsListInfo result = apiInstance.v10FundsByIdAssetsGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsByIdAssetsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**FundAssetsListInfo**](FundAssetsListInfo.md)

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
String id = "id_example"; // String | 
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
 **id** | **String**|  |
 **authorization** | **String**|  | [optional]
 **currencySecondary** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]

### Return type

[**FundDetailsFull**](FundDetailsFull.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10FundsByIdReallocationsGet"></a>
# **v10FundsByIdReallocationsGet**
> Void v10FundsByIdReallocationsGet(id, dateFrom, dateTo, skip, take)

Get history of asset part update requests.

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
    Void result = apiInstance.v10FundsByIdReallocationsGet(id, dateFrom, dateTo, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsByIdReallocationsGet");
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

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10FundsGet"></a>
# **v10FundsGet**
> FundsList v10FundsGet(authorization, sorting, currencySecondary, assets, statisticDateFrom, statisticDateTo, chartPointsCount, mask, facetId, isFavorite, isEnabled, hasInvestorsForAll, hasInvestorsForClosed, ids, managerId, programManagerId, status, skip, take)

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
List<String> assets = Arrays.asList("assets_example"); // List<String> | 
DateTime statisticDateFrom = new DateTime(); // DateTime | 
DateTime statisticDateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String mask = "mask_example"; // String | 
String facetId = "facetId_example"; // String | 
Boolean isFavorite = true; // Boolean | 
Boolean isEnabled = true; // Boolean | 
Boolean hasInvestorsForAll = true; // Boolean | 
Boolean hasInvestorsForClosed = true; // Boolean | 
List<UUID> ids = Arrays.asList(new UUID()); // List<UUID> | 
String managerId = "managerId_example"; // String | 
UUID programManagerId = new UUID(); // UUID | 
List<String> status = Arrays.asList("status_example"); // List<String> | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    FundsList result = apiInstance.v10FundsGet(authorization, sorting, currencySecondary, assets, statisticDateFrom, statisticDateTo, chartPointsCount, mask, facetId, isFavorite, isEnabled, hasInvestorsForAll, hasInvestorsForClosed, ids, managerId, programManagerId, status, skip, take);
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
 **sorting** | **String**|  | [optional] [enum: ByProfitAsc, ByProfitDesc, ByDrawdownAsc, ByDrawdownDesc, ByInvestorsAsc, ByInvestorsDesc, ByNewAsc, ByNewDesc, ByTitleAsc, ByTitleDesc, ByBalanceAsc, ByBalanceDesc]
 **currencySecondary** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **assets** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **statisticDateFrom** | **DateTime**|  | [optional]
 **statisticDateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **mask** | **String**|  | [optional]
 **facetId** | **String**|  | [optional]
 **isFavorite** | **Boolean**|  | [optional]
 **isEnabled** | **Boolean**|  | [optional]
 **hasInvestorsForAll** | **Boolean**|  | [optional]
 **hasInvestorsForClosed** | **Boolean**|  | [optional]
 **ids** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **managerId** | **String**|  | [optional]
 **programManagerId** | [**UUID**](.md)|  | [optional]
 **status** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: None, Pending, ErrorCreating, Active, Closed, Archived, ClosedDueToInactivity]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**FundsList**](FundsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10FundsSetsGet"></a>
# **v10FundsSetsGet**
> FundSets v10FundsSetsGet(authorization)

Fund sets

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    FundSets result = apiInstance.v10FundsSetsGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsSetsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**FundSets**](FundSets.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

