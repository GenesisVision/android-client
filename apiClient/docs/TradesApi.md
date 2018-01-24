# TradesApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiTradesIpfsHistoryGet**](TradesApi.md#apiTradesIpfsHistoryGet) | **GET** api/trades/ipfsHistory | 


<a name="apiTradesIpfsHistoryGet"></a>
# **apiTradesIpfsHistoryGet**
> TradesViewModel apiTradesIpfsHistoryGet(ipfsHashId)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradesApi;


TradesApi apiInstance = new TradesApi();
String ipfsHashId = "ipfsHashId_example"; // String | 
try {
    TradesViewModel result = apiInstance.apiTradesIpfsHistoryGet(ipfsHashId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradesApi#apiTradesIpfsHistoryGet");
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

