# DashboardApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getDashboard**](DashboardApi.md#getDashboard) | **GET** v2.0/dashboard | 

<a name="getDashboard"></a>
# **getDashboard**
> Void getDashboard(authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.getDashboard(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getDashboard");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

