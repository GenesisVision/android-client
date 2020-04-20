# SearchApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**search**](SearchApi.md#search) | **GET** v2.0/search | Program / fund / manager search

<a name="search"></a>
# **search**
> CommonPublicAssetsViewModel search(mask, take)

Program / fund / manager search

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SearchApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SearchApi apiInstance = new SearchApi();
String mask = "mask_example"; // String | 
Integer take = 56; // Integer | 
try {
    CommonPublicAssetsViewModel result = apiInstance.search(mask, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchApi#search");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **mask** | **String**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**CommonPublicAssetsViewModel**](CommonPublicAssetsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

