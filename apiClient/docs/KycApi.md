# KycApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10KycCallbackPost**](KycApi.md#v10KycCallbackPost) | **POST** v1.0/kyc/callback | 


<a name="v10KycCallbackPost"></a>
# **v10KycCallbackPost**
> String v10KycCallbackPost(model)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.KycApi;


KycApi apiInstance = new KycApi();
KycCallback model = new KycCallback(); // KycCallback | 
try {
    String result = apiInstance.v10KycCallbackPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling KycApi#v10KycCallbackPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**KycCallback**](KycCallback.md)|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

