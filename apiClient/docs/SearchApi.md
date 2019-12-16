# SearchApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**search**](SearchApi.md#search) | **GET** v2.0/search | Program / fund / manager search

<a name="search"></a>
# **search**
> CommonPublicAssetsViewModel search(authorization, mask, take)

Program / fund / manager search

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SearchApi;


SearchApi apiInstance = new SearchApi();
String authorization = "authorization_example"; // String | 
String mask = "mask_example"; // String | 
Integer take = 56; // Integer | 
try {
    CommonPublicAssetsViewModel result = apiInstance.search(authorization, mask, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchApi#search");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**|  | [optional]
 **mask** | **String**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**CommonPublicAssetsViewModel**](CommonPublicAssetsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

