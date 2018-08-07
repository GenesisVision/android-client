# RateApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10RateByFromByToGet**](RateApi.md#v10RateByFromByToGet) | **GET** v1.0/rate/{from}/{to} | Get rate


<a name="v10RateByFromByToGet"></a>
# **v10RateByFromByToGet**
> RateViewModel v10RateByFromByToGet(from, to)

Get rate

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.RateApi;


RateApi apiInstance = new RateApi();
String from = "from_example"; // String | 
String to = "to_example"; // String | 
try {
    RateViewModel result = apiInstance.v10RateByFromByToGet(from, to);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RateApi#v10RateByFromByToGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **from** | **String**|  |
 **to** | **String**|  |

### Return type

[**RateViewModel**](RateViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

