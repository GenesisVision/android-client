# PlatformApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10PlatformInfoGet**](PlatformApi.md#v10PlatformInfoGet) | **GET** v1.0/platform/info | Platform info


<a name="v10PlatformInfoGet"></a>
# **v10PlatformInfoGet**
> PlatformInfo v10PlatformInfoGet()

Platform info

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PlatformApi;


PlatformApi apiInstance = new PlatformApi();
try {
    PlatformInfo result = apiInstance.v10PlatformInfoGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#v10PlatformInfoGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**PlatformInfo**](PlatformInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

