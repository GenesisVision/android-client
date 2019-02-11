# ProgramsApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10ProgramsByIdChartsBalanceGet**](ProgramsApi.md#v10ProgramsByIdChartsBalanceGet) | **GET** v1.0/programs/{id}/charts/balance | Program balance chart
[**v10ProgramsByIdChartsProfitGet**](ProgramsApi.md#v10ProgramsByIdChartsProfitGet) | **GET** v1.0/programs/{id}/charts/profit | Program profit chart
[**v10ProgramsByIdFavoriteAddPost**](ProgramsApi.md#v10ProgramsByIdFavoriteAddPost) | **POST** v1.0/programs/{id}/favorite/add | Add to favorites
[**v10ProgramsByIdFavoriteRemovePost**](ProgramsApi.md#v10ProgramsByIdFavoriteRemovePost) | **POST** v1.0/programs/{id}/favorite/remove | Remove from favorites
[**v10ProgramsByIdGet**](ProgramsApi.md#v10ProgramsByIdGet) | **GET** v1.0/programs/{id} | Program details
[**v10ProgramsByIdTradesGet**](ProgramsApi.md#v10ProgramsByIdTradesGet) | **GET** v1.0/programs/{id}/trades | Trade history
[**v10ProgramsByIdTradesOpenGet**](ProgramsApi.md#v10ProgramsByIdTradesOpenGet) | **GET** v1.0/programs/{id}/trades/open | Open positions
[**v10ProgramsGet**](ProgramsApi.md#v10ProgramsGet) | **GET** v1.0/programs | Programs list
[**v10ProgramsLevelupSummaryGet**](ProgramsApi.md#v10ProgramsLevelupSummaryGet) | **GET** v1.0/programs/levelup/summary | Level up summary
[**v10ProgramsSetsGet**](ProgramsApi.md#v10ProgramsSetsGet) | **GET** v1.0/programs/sets | Programs sets


<a name="v10ProgramsByIdChartsBalanceGet"></a>
# **v10ProgramsByIdChartsBalanceGet**
> ProgramBalanceChart v10ProgramsByIdChartsBalanceGet(id, dateFrom, dateTo, maxPointCount)

Program balance chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
try {
    ProgramBalanceChart result = apiInstance.v10ProgramsByIdChartsBalanceGet(id, dateFrom, dateTo, maxPointCount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#v10ProgramsByIdChartsBalanceGet");
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

[**ProgramBalanceChart**](ProgramBalanceChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProgramsByIdChartsProfitGet"></a>
# **v10ProgramsByIdChartsProfitGet**
> ProgramProfitChart v10ProgramsByIdChartsProfitGet(id, dateFrom, dateTo, maxPointCount)

Program profit chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
try {
    ProgramProfitChart result = apiInstance.v10ProgramsByIdChartsProfitGet(id, dateFrom, dateTo, maxPointCount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#v10ProgramsByIdChartsProfitGet");
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

[**ProgramProfitChart**](ProgramProfitChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProgramsByIdFavoriteAddPost"></a>
# **v10ProgramsByIdFavoriteAddPost**
> Void v10ProgramsByIdFavoriteAddPost(id, authorization)

Add to favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ProgramsByIdFavoriteAddPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#v10ProgramsByIdFavoriteAddPost");
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

<a name="v10ProgramsByIdFavoriteRemovePost"></a>
# **v10ProgramsByIdFavoriteRemovePost**
> Void v10ProgramsByIdFavoriteRemovePost(id, authorization)

Remove from favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ProgramsByIdFavoriteRemovePost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#v10ProgramsByIdFavoriteRemovePost");
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

<a name="v10ProgramsByIdGet"></a>
# **v10ProgramsByIdGet**
> ProgramDetailsFull v10ProgramsByIdGet(id, authorization, currencySecondary)

Program details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
String id = "id_example"; // String | 
String authorization = "authorization_example"; // String | 
String currencySecondary = "currencySecondary_example"; // String | 
try {
    ProgramDetailsFull result = apiInstance.v10ProgramsByIdGet(id, authorization, currencySecondary);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#v10ProgramsByIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **authorization** | **String**|  | [optional]
 **currencySecondary** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**ProgramDetailsFull**](ProgramDetailsFull.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProgramsByIdTradesGet"></a>
# **v10ProgramsByIdTradesGet**
> TradesViewModel v10ProgramsByIdTradesGet(id, dateFrom, dateTo, symbol, sorting, skip, take)

Trade history

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String symbol = "symbol_example"; // String | 
String sorting = "sorting_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesViewModel result = apiInstance.v10ProgramsByIdTradesGet(id, dateFrom, dateTo, symbol, sorting, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#v10ProgramsByIdTradesGet");
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
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesViewModel**](TradesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProgramsByIdTradesOpenGet"></a>
# **v10ProgramsByIdTradesOpenGet**
> TradesViewModel v10ProgramsByIdTradesOpenGet(id, sorting, skip, take)

Open positions

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
String sorting = "sorting_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesViewModel result = apiInstance.v10ProgramsByIdTradesOpenGet(id, sorting, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#v10ProgramsByIdTradesOpenGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesViewModel**](TradesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProgramsGet"></a>
# **v10ProgramsGet**
> ProgramsList v10ProgramsGet(authorization, levelMin, levelMax, profitAvgMin, profitAvgMax, sorting, programCurrency, currencySecondary, levelUpFrom, tags, statisticDateFrom, statisticDateTo, chartPointsCount, mask, facetId, isFavorite, isEnabled, ids, managerId, programManagerId, skip, take)

Programs list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
String authorization = "authorization_example"; // String | 
Integer levelMin = 56; // Integer | 
Integer levelMax = 56; // Integer | 
Double profitAvgMin = 3.4D; // Double | 
Double profitAvgMax = 3.4D; // Double | 
String sorting = "sorting_example"; // String | 
String programCurrency = "programCurrency_example"; // String | 
String currencySecondary = "currencySecondary_example"; // String | 
Integer levelUpFrom = 56; // Integer | 
List<String> tags = Arrays.asList("tags_example"); // List<String> | 
DateTime statisticDateFrom = new DateTime(); // DateTime | 
DateTime statisticDateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String mask = "mask_example"; // String | 
String facetId = "facetId_example"; // String | 
Boolean isFavorite = true; // Boolean | 
Boolean isEnabled = true; // Boolean | 
List<UUID> ids = Arrays.asList(new UUID()); // List<UUID> | 
String managerId = "managerId_example"; // String | 
UUID programManagerId = new UUID(); // UUID | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ProgramsList result = apiInstance.v10ProgramsGet(authorization, levelMin, levelMax, profitAvgMin, profitAvgMax, sorting, programCurrency, currencySecondary, levelUpFrom, tags, statisticDateFrom, statisticDateTo, chartPointsCount, mask, facetId, isFavorite, isEnabled, ids, managerId, programManagerId, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#v10ProgramsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**|  | [optional]
 **levelMin** | **Integer**|  | [optional]
 **levelMax** | **Integer**|  | [optional]
 **profitAvgMin** | **Double**|  | [optional]
 **profitAvgMax** | **Double**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByLevelAsc, ByLevelDesc, ByProfitAsc, ByProfitDesc, ByDrawdownAsc, ByDrawdownDesc, ByTradesAsc, ByTradesDesc, ByInvestorsAsc, ByInvestorsDesc, ByNewDesc, ByNewAsc, ByEndOfPeriodAsc, ByEndOfPeriodDesc, ByTitleAsc, ByTitleDesc, ByBalanceAsc, ByBalanceDesc, ByCurrDesc, ByCurrAsc]
 **programCurrency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **currencySecondary** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **levelUpFrom** | **Integer**|  | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: Crypto, Forex]
 **statisticDateFrom** | **DateTime**|  | [optional]
 **statisticDateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **mask** | **String**|  | [optional]
 **facetId** | **String**|  | [optional]
 **isFavorite** | **Boolean**|  | [optional]
 **isEnabled** | **Boolean**|  | [optional]
 **ids** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **managerId** | **String**|  | [optional]
 **programManagerId** | [**UUID**](.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ProgramsList**](ProgramsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProgramsLevelupSummaryGet"></a>
# **v10ProgramsLevelupSummaryGet**
> LevelUpSummary v10ProgramsLevelupSummaryGet(authorization)

Level up summary

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
String authorization = "authorization_example"; // String | 
try {
    LevelUpSummary result = apiInstance.v10ProgramsLevelupSummaryGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#v10ProgramsLevelupSummaryGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**|  | [optional]

### Return type

[**LevelUpSummary**](LevelUpSummary.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProgramsSetsGet"></a>
# **v10ProgramsSetsGet**
> ProgramSets v10ProgramsSetsGet(authorization)

Programs sets

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramSets result = apiInstance.v10ProgramsSetsGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#v10ProgramsSetsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**ProgramSets**](ProgramSets.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

