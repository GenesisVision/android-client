# InvestorApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiInvestorInvestmentsInvestPost**](InvestorApi.md#apiInvestorInvestmentsInvestPost) | **POST** api/investor/investments/invest | 
[**apiInvestorInvestmentsPost**](InvestorApi.md#apiInvestorInvestmentsPost) | **POST** api/investor/investments | 


<a name="apiInvestorInvestmentsInvestPost"></a>
# **apiInvestorInvestmentsInvestPost**
> Void apiInvestorInvestmentsInvestPost(model)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
Invest model = new Invest(); // Invest | 
try {
    Void result = apiInstance.apiInvestorInvestmentsInvestPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentsInvestPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**Invest**](Invest.md)|  | [optional]

### Return type

[**Void**](.md)

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

