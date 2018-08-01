# DexchangeApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10DexchangeNodePost**](DexchangeApi.md#v10DexchangeNodePost) | **POST** v1.0/dexchange/node | 


<a name="v10DexchangeNodePost"></a>
# **v10DexchangeNodePost**
> DExchangeResponse v10DexchangeNodePost(authorization, model)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DexchangeApi;


DexchangeApi apiInstance = new DexchangeApi();
String authorization = "authorization_example"; // String | JWT access token
DExchangeRequest model = new DExchangeRequest(); // DExchangeRequest | 
try {
    DExchangeResponse result = apiInstance.v10DexchangeNodePost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DexchangeApi#v10DexchangeNodePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**DExchangeRequest**](DExchangeRequest.md)|  | [optional]

### Return type

[**DExchangeResponse**](DExchangeResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

