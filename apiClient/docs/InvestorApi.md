# InvestorApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10InvestorDashboardChartValueGet**](InvestorApi.md#v10InvestorDashboardChartValueGet) | **GET** v1.0/investor/dashboard/chart/value | Value chart
[**v10InvestorDashboardEventsGet**](InvestorApi.md#v10InvestorDashboardEventsGet) | **GET** v1.0/investor/dashboard/events | Portfolio events
[**v10InvestorDashboardProgramListGet**](InvestorApi.md#v10InvestorDashboardProgramListGet) | **GET** v1.0/investor/dashboard/program/list | Programs list


<a name="v10InvestorDashboardChartValueGet"></a>
# **v10InvestorDashboardChartValueGet**
> DashboardChartValue v10InvestorDashboardChartValueGet(authorization, from, to, currency)

Value chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
String currency = "currency_example"; // String | 
try {
    DashboardChartValue result = apiInstance.v10InvestorDashboardChartValueGet(authorization, from, to, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorDashboardChartValueGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USD, EUR]

### Return type

[**DashboardChartValue**](DashboardChartValue.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorDashboardEventsGet"></a>
# **v10InvestorDashboardEventsGet**
> DashboardPortfolioEvents v10InvestorDashboardEventsGet(authorization, from, to, currency)

Portfolio events

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
String currency = "currency_example"; // String | 
try {
    DashboardPortfolioEvents result = apiInstance.v10InvestorDashboardEventsGet(authorization, from, to, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorDashboardEventsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USD, EUR]

### Return type

[**DashboardPortfolioEvents**](DashboardPortfolioEvents.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorDashboardProgramListGet"></a>
# **v10InvestorDashboardProgramListGet**
> DashboardProgramsList v10InvestorDashboardProgramListGet(authorization, sorting, statisticDateFrom, statisticDateTo, skip, take)

Programs list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
String sorting = "sorting_example"; // String | 
DateTime statisticDateFrom = new DateTime(); // DateTime | 
DateTime statisticDateTo = new DateTime(); // DateTime | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    DashboardProgramsList result = apiInstance.v10InvestorDashboardProgramListGet(authorization, sorting, statisticDateFrom, statisticDateTo, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorDashboardProgramListGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **sorting** | **String**|  | [optional] [enum: ByShareAsc, ByShareDesc, ByCurrAsc, ByCurrDesc, ByTimeLeftAsc, ByTimeLeftDesc, ByValueAsc, ByValueDesc, ByProfitAsc, ByProfitDesc]
 **statisticDateFrom** | **DateTime**|  | [optional]
 **statisticDateTo** | **DateTime**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**DashboardProgramsList**](DashboardProgramsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

