# ProgramApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10ProgramByIdChartGet**](ProgramApi.md#v10ProgramByIdChartGet) | **GET** v1.0/program/{id}/chart | Get manager chart
[**v10ProgramByIdFavoriteAddPost**](ProgramApi.md#v10ProgramByIdFavoriteAddPost) | **POST** v1.0/program/{id}/favorite/add | Add to favorites
[**v10ProgramByIdFavoriteRemovePost**](ProgramApi.md#v10ProgramByIdFavoriteRemovePost) | **POST** v1.0/program/{id}/favorite/remove | Remove from favorites
[**v10ProgramByIdGet**](ProgramApi.md#v10ProgramByIdGet) | **GET** v1.0/program/{id} | Program details
[**v10ProgramByIdTradesGet**](ProgramApi.md#v10ProgramByIdTradesGet) | **GET** v1.0/program/{id}/trades | Get manager trade history
[**v10ProgramListGet**](ProgramApi.md#v10ProgramListGet) | **GET** v1.0/program/list | Programs list


<a name="v10ProgramByIdChartGet"></a>
# **v10ProgramByIdChartGet**
> ProgramChart v10ProgramByIdChartGet(id, timeFrame)

Get manager chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramApi;


ProgramApi apiInstance = new ProgramApi();
UUID id = new UUID(); // UUID | 
String timeFrame = "timeFrame_example"; // String | 
try {
    ProgramChart result = apiInstance.v10ProgramByIdChartGet(id, timeFrame);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramApi#v10ProgramByIdChartGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **timeFrame** | **String**|  | [optional] [enum: Day, Week, Month1, Month3, Month6, Year, All]

### Return type

[**ProgramChart**](ProgramChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProgramByIdFavoriteAddPost"></a>
# **v10ProgramByIdFavoriteAddPost**
> Void v10ProgramByIdFavoriteAddPost(id, authorization)

Add to favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramApi;


ProgramApi apiInstance = new ProgramApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ProgramByIdFavoriteAddPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramApi#v10ProgramByIdFavoriteAddPost");
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

<a name="v10ProgramByIdFavoriteRemovePost"></a>
# **v10ProgramByIdFavoriteRemovePost**
> Void v10ProgramByIdFavoriteRemovePost(id, authorization)

Remove from favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramApi;


ProgramApi apiInstance = new ProgramApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ProgramByIdFavoriteRemovePost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramApi#v10ProgramByIdFavoriteRemovePost");
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

<a name="v10ProgramByIdGet"></a>
# **v10ProgramByIdGet**
> ProgramDetailsFull v10ProgramByIdGet(id, authorization)

Program details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramApi;


ProgramApi apiInstance = new ProgramApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | 
try {
    ProgramDetailsFull result = apiInstance.v10ProgramByIdGet(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramApi#v10ProgramByIdGet");
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

<a name="v10ProgramByIdTradesGet"></a>
# **v10ProgramByIdTradesGet**
> TradesViewModel v10ProgramByIdTradesGet(id, dateFrom, dateTo, symbol, sorting, skip, take)

Get manager trade history

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramApi;


ProgramApi apiInstance = new ProgramApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String symbol = "symbol_example"; // String | 
String sorting = "sorting_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesViewModel result = apiInstance.v10ProgramByIdTradesGet(id, dateFrom, dateTo, symbol, sorting, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramApi#v10ProgramByIdTradesGet");
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

<a name="v10ProgramListGet"></a>
# **v10ProgramListGet**
> ProgramsList v10ProgramListGet(authorization, levelMin, levelMax, profitAvgMin, profitAvgMax, timeFrame, mask, facetId, isFavorite, currency, skip, take)

Programs list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramApi;


ProgramApi apiInstance = new ProgramApi();
String authorization = "authorization_example"; // String | 
Integer levelMin = 56; // Integer | 
Integer levelMax = 56; // Integer | 
Double profitAvgMin = 3.4D; // Double | 
Double profitAvgMax = 3.4D; // Double | 
String timeFrame = "timeFrame_example"; // String | 
String mask = "mask_example"; // String | 
UUID facetId = new UUID(); // UUID | 
Boolean isFavorite = true; // Boolean | 
String currency = "currency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ProgramsList result = apiInstance.v10ProgramListGet(authorization, levelMin, levelMax, profitAvgMin, profitAvgMax, timeFrame, mask, facetId, isFavorite, currency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramApi#v10ProgramListGet");
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
 **timeFrame** | **String**|  | [optional] [enum: Day, Week, Month1, Month3, Month6, Year, All]
 **mask** | **String**|  | [optional]
 **facetId** | [**UUID**](.md)|  | [optional]
 **isFavorite** | **Boolean**|  | [optional]
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USD, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ProgramsList**](ProgramsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

