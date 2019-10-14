# ProgramsApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getProgramBalanceChart**](ProgramsApi.md#getProgramBalanceChart) | **GET** v2.0/programs/{id}/charts/balance | Program balance chart
[**getProgramDetails**](ProgramsApi.md#getProgramDetails) | **GET** v2.0/programs/{id} | Program details
[**getProgramProfitChart**](ProgramsApi.md#getProgramProfitChart) | **GET** v2.0/programs/{id}/charts/profit | Program profit chart
[**getPrograms**](ProgramsApi.md#getPrograms) | **GET** v2.0/programs | Programs list

<a name="getProgramBalanceChart"></a>
# **getProgramBalanceChart**
> ProgramBalanceChart getProgramBalanceChart(id, dateFrom, dateTo, maxPointCount, currency)

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
String currency = "currency_example"; // String | 
try {
    ProgramBalanceChart result = apiInstance.getProgramBalanceChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramBalanceChart");
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

[**ProgramBalanceChart**](ProgramBalanceChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramDetails"></a>
# **getProgramDetails**
> ProgramDetailsFullOld getProgramDetails(id, authorization)

Program details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
String id = "id_example"; // String | 
String authorization = "authorization_example"; // String | 
try {
    ProgramDetailsFullOld result = apiInstance.getProgramDetails(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **authorization** | **String**|  | [optional]

### Return type

[**ProgramDetailsFullOld**](ProgramDetailsFullOld.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramProfitChart"></a>
# **getProgramProfitChart**
> ProgramProfitCharts getProgramProfitChart(id, dateFrom, dateTo, maxPointCount, currency, currencies)

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
String currency = "currency_example"; // String | 
List<String> currencies = Arrays.asList("currencies_example"); // List<String> | 
try {
    ProgramProfitCharts result = apiInstance.getProgramProfitChart(id, dateFrom, dateTo, maxPointCount, currency, currencies);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramProfitChart");
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
 **currencies** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]

### Return type

[**ProgramProfitCharts**](ProgramProfitCharts.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getPrograms"></a>
# **getPrograms**
> ItemsViewModelProgramDetailsList getPrograms(authorization, skip, take)

Programs list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
String authorization = "authorization_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ItemsViewModelProgramDetailsList result = apiInstance.getPrograms(authorization, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getPrograms");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelProgramDetailsList**](ItemsViewModelProgramDetailsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

