# BrokersApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10BrokersGet**](BrokersApi.md#v10BrokersGet) | **GET** v1.0/brokers | Get all trade servers


<a name="v10BrokersGet"></a>
# **v10BrokersGet**
> BrokersInfo v10BrokersGet()

Get all trade servers

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokersApi;


BrokersApi apiInstance = new BrokersApi();
try {
    BrokersInfo result = apiInstance.v10BrokersGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokersApi#v10BrokersGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**BrokersInfo**](BrokersInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

