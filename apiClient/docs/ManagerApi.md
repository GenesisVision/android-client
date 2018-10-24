# ManagerApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10ManagerEventsGet**](ManagerApi.md#v10ManagerEventsGet) | **GET** v1.0/manager/events | Manager events
[**v10ManagerFundsGet**](ManagerApi.md#v10ManagerFundsGet) | **GET** v1.0/manager/funds | Manager funds
[**v10ManagerGet**](ManagerApi.md#v10ManagerGet) | **GET** v1.0/manager | Manager dashboard
[**v10ManagerProgramsGet**](ManagerApi.md#v10ManagerProgramsGet) | **GET** v1.0/manager/programs | Manager programs


<a name="v10ManagerEventsGet"></a>
# **v10ManagerEventsGet**
> ManagerPortfolioEvents v10ManagerEventsGet(authorization, assetId, from, to, type, assetType)

Manager events

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
UUID assetId = new UUID(); // UUID | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
String type = "type_example"; // String | 
String assetType = "assetType_example"; // String | 
try {
    ManagerPortfolioEvents result = apiInstance.v10ManagerEventsGet(authorization, assetId, from, to, type, assetType);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerEventsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **assetId** | [**UUID**](.md)|  | [optional]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **type** | **String**|  | [optional] [enum: AssetStarted, ProgramPeriodStats, ProgramPeriodEnds, InvestorInvest, InvestorWithdraw, ManagerInvest, ManagerWithdraw, AssetFinished]
 **assetType** | **String**|  | [optional] [enum: All, Program, Fund]

### Return type

[**ManagerPortfolioEvents**](ManagerPortfolioEvents.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsGet"></a>
# **v10ManagerFundsGet**
> ManagerFunds v10ManagerFundsGet(authorization, sorting, from, to, pointsCount, skip, take)

Manager funds

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
String sorting = "sorting_example"; // String | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
Integer pointsCount = 56; // Integer | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ManagerFunds result = apiInstance.v10ManagerFundsGet(authorization, sorting, from, to, pointsCount, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **sorting** | **String**|  | [optional] [enum: ByTitleAsc, ByTitleDesc, ByShareAsc, ByShareDesc, ByInvestmentsAsc, ByInvestmentsDesc, ByWithdrawalsAsc, ByWithdrawalsDesc, ByBalanceAsc, ByBalanceDesc, ByProfitAsc, ByProfitDesc]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **pointsCount** | **Integer**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ManagerFunds**](ManagerFunds.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerGet"></a>
# **v10ManagerGet**
> ManagerDashboard v10ManagerGet(authorization, eventsTake, requestsSkip, requestsTake)

Manager dashboard

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
Integer eventsTake = 56; // Integer | 
Integer requestsSkip = 56; // Integer | 
Integer requestsTake = 56; // Integer | 
try {
    ManagerDashboard result = apiInstance.v10ManagerGet(authorization, eventsTake, requestsSkip, requestsTake);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **eventsTake** | **Integer**|  | [optional]
 **requestsSkip** | **Integer**|  | [optional]
 **requestsTake** | **Integer**|  | [optional]

### Return type

[**ManagerDashboard**](ManagerDashboard.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsGet"></a>
# **v10ManagerProgramsGet**
> ManagerPrograms v10ManagerProgramsGet(authorization, sorting, from, to, pointsCount, skip, take)

Manager programs

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
String sorting = "sorting_example"; // String | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
Integer pointsCount = 56; // Integer | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ManagerPrograms result = apiInstance.v10ManagerProgramsGet(authorization, sorting, from, to, pointsCount, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **sorting** | **String**|  | [optional] [enum: ByTitleAsc, ByTitleDesc, ByShareAsc, ByShareDesc, ByInvestmentsAsc, ByInvestmentsDesc, ByWithdrawalsAsc, ByWithdrawalsDesc, ByTimeLeftAsc, ByTimeLeftDesc, ByBalanceAsc, ByBalanceDesc, ByProfitAsc, ByProfitDesc]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **pointsCount** | **Integer**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ManagerPrograms**](ManagerPrograms.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

