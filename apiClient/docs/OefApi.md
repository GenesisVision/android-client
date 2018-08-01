# OefApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10OefAssetAllGet**](OefApi.md#v10OefAssetAllGet) | **GET** v1.0/oef/asset/all | Get all supported assets for OEFs


<a name="v10OefAssetAllGet"></a>
# **v10OefAssetAllGet**
> PlatformAssets v10OefAssetAllGet(authorization)

Get all supported assets for OEFs

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.OefApi;


OefApi apiInstance = new OefApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    PlatformAssets result = apiInstance.v10OefAssetAllGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling OefApi#v10OefAssetAllGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**PlatformAssets**](PlatformAssets.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

