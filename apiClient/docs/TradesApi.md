# TradesApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiTradesIpfsGetGet**](TradesApi.md#apiTradesIpfsGetGet) | **GET** api/trades/ipfsGet | 


<a name="apiTradesIpfsGetGet"></a>
# **apiTradesIpfsGetGet**
> TradesViewModel apiTradesIpfsGetGet(ipfsHashId)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradesApi;


TradesApi apiInstance = new TradesApi();
String ipfsHashId = "ipfsHashId_example"; // String | 
try {
    TradesViewModel result = apiInstance.apiTradesIpfsGetGet(ipfsHashId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradesApi#apiTradesIpfsGetGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ipfsHashId** | **String**|  | [optional]

### Return type

[**TradesViewModel**](TradesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

