# RateApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiRatePost**](RateApi.md#apiRatePost) | **POST** api/rate | Get rate


<a name="apiRatePost"></a>
# **apiRatePost**
> RateViewModel apiRatePost(model)

Get rate

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.RateApi;


RateApi apiInstance = new RateApi();
RequestRate model = new RequestRate(); // RequestRate | 
try {
    RateViewModel result = apiInstance.apiRatePost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RateApi#apiRatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**RequestRate**](RequestRate.md)|  | [optional]

### Return type

[**RateViewModel**](RateViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

