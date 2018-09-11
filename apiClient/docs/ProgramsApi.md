# ProgramsApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10ProgramsByIdChartGet**](ProgramsApi.md#v10ProgramsByIdChartGet) | **GET** v1.0/programs/{id}/chart | Program chart
[**v10ProgramsByIdFavoriteAddPost**](ProgramsApi.md#v10ProgramsByIdFavoriteAddPost) | **POST** v1.0/programs/{id}/favorite/add | Add to favorites
[**v10ProgramsByIdFavoriteRemovePost**](ProgramsApi.md#v10ProgramsByIdFavoriteRemovePost) | **POST** v1.0/programs/{id}/favorite/remove | Remove from favorites
[**v10ProgramsByIdGet**](ProgramsApi.md#v10ProgramsByIdGet) | **GET** v1.0/programs/{id} | Program details
[**v10ProgramsByIdTradesGet**](ProgramsApi.md#v10ProgramsByIdTradesGet) | **GET** v1.0/programs/{id}/trades | Trade history
[**v10ProgramsGet**](ProgramsApi.md#v10ProgramsGet) | **GET** v1.0/programs | Programs list
[**v10ProgramsSetsGet**](ProgramsApi.md#v10ProgramsSetsGet) | **GET** v1.0/programs/sets | Programs sets


<a name="v10ProgramsByIdChartGet"></a>
# **v10ProgramsByIdChartGet**
> ProgramChart v10ProgramsByIdChartGet(id, chartDateFrom, chartDateTo)

Program chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
DateTime chartDateFrom = new DateTime(); // DateTime | 
DateTime chartDateTo = new DateTime(); // DateTime | 
try {
    ProgramChart result = apiInstance.v10ProgramsByIdChartGet(id, chartDateFrom, chartDateTo);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#v10ProgramsByIdChartGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **chartDateFrom** | **DateTime**|  | [optional]
 **chartDateTo** | **DateTime**|  | [optional]

### Return type

[**ProgramChart**](ProgramChart.md)

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
> ProgramDetailsFull v10ProgramsByIdGet(id, authorization)

Program details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | 
try {
    ProgramDetailsFull result = apiInstance.v10ProgramsByIdGet(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#v10ProgramsByIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**|  | [optional]

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
 **sorting** | **String**|  | [optional] [enum: ByDateAsk, ByDateDesc, ByTicketAsk, ByTicketDesc, BySymbolAsk, BySymbolDesc, ByDirectionAsk, ByDirectionDesc]
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
> ProgramsList v10ProgramsGet(authorization, levelMin, levelMax, profitAvgMin, profitAvgMax, statisticDateFrom, statisticDateTo, sorting, mask, facetId, isFavorite, currency, ids, skip, take)

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
DateTime statisticDateFrom = new DateTime(); // DateTime | 
DateTime statisticDateTo = new DateTime(); // DateTime | 
String sorting = "sorting_example"; // String | 
String mask = "mask_example"; // String | 
UUID facetId = new UUID(); // UUID | 
Boolean isFavorite = true; // Boolean | 
String currency = "currency_example"; // String | 
List<UUID> ids = Arrays.asList(new UUID()); // List<UUID> | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ProgramsList result = apiInstance.v10ProgramsGet(authorization, levelMin, levelMax, profitAvgMin, profitAvgMax, statisticDateFrom, statisticDateTo, sorting, mask, facetId, isFavorite, currency, ids, skip, take);
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
 **statisticDateFrom** | **DateTime**|  | [optional]
 **statisticDateTo** | **DateTime**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByLevelAsc, ByLevelDesc, ByProfitAsc, ByProfitDesc, ByDrawdownAsc, ByDrawdownDesc, ByTradesAsc, ByTradesDesc, ByInvestorsAsc, ByInvestorsDesc, ByEndOfPeriodAsc, ByEndOfPeriodDesc, ByTitleAsc, ByTitleDesc, ByBalanceAsc, ByBalanceDesc]
 **mask** | **String**|  | [optional]
 **facetId** | [**UUID**](.md)|  | [optional]
 **isFavorite** | **Boolean**|  | [optional]
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USD, EUR]
 **ids** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ProgramsList**](ProgramsList.md)

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

