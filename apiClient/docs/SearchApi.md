# SearchApi

All URIs are relative to *https://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10SearchGet**](SearchApi.md#v10SearchGet) | **GET** v1.0/search | Program / fund / manager search


<a name="v10SearchGet"></a>
# **v10SearchGet**
> SearchViewModel v10SearchGet(authorization, mask, take)

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
    SearchViewModel result = apiInstance.v10SearchGet(authorization, mask, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchApi#v10SearchGet");
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

[**SearchViewModel**](SearchViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

