# InvestorApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiInvestorDashboardGet**](InvestorApi.md#apiInvestorDashboardGet) | **GET** api/investor/dashboard | 
[**apiInvestorInvestmentsInvestPost**](InvestorApi.md#apiInvestorInvestmentsInvestPost) | **POST** api/investor/investments/invest | 
[**apiInvestorInvestmentsPost**](InvestorApi.md#apiInvestorInvestmentsPost) | **POST** api/investor/investments | 
[**apiInvestorInvestmentsWithdrawPost**](InvestorApi.md#apiInvestorInvestmentsWithdrawPost) | **POST** api/investor/investments/withdraw | 


<a name="apiInvestorDashboardGet"></a>
# **apiInvestorDashboardGet**
> InvestorDashboard apiInvestorDashboardGet()



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
try {
    InvestorDashboard result = apiInstance.apiInvestorDashboardGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorDashboardGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**InvestorDashboard**](InvestorDashboard.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentsInvestPost"></a>
# **apiInvestorInvestmentsInvestPost**
> ProfileShortViewModel apiInvestorInvestmentsInvestPost(model)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
InvestViewModel model = new InvestViewModel(); // InvestViewModel | 
try {
    ProfileShortViewModel result = apiInstance.apiInvestorInvestmentsInvestPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentsInvestPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**InvestViewModel**](InvestViewModel.md)|  | [optional]

### Return type

[**ProfileShortViewModel**](ProfileShortViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentsPost"></a>
# **apiInvestorInvestmentsPost**
> InvestmentProgramsViewModel apiInvestorInvestmentsPost(filter)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
InvestmentsFilter filter = new InvestmentsFilter(); // InvestmentsFilter | 
try {
    InvestmentProgramsViewModel result = apiInstance.apiInvestorInvestmentsPost(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**InvestmentsFilter**](InvestmentsFilter.md)|  | [optional]

### Return type

[**InvestmentProgramsViewModel**](InvestmentProgramsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentsWithdrawPost"></a>
# **apiInvestorInvestmentsWithdrawPost**
> Void apiInvestorInvestmentsWithdrawPost(model)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
InvestViewModel model = new InvestViewModel(); // InvestViewModel | 
try {
    Void result = apiInstance.apiInvestorInvestmentsWithdrawPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentsWithdrawPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**InvestViewModel**](InvestViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

