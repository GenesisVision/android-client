# TradesApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiTradesIpfsHistoryGet**](TradesApi.md#apiTradesIpfsHistoryGet) | **GET** api/trades/ipfsHistory | Get trades by IPFS hash id


<a name="apiTradesIpfsHistoryGet"></a>
# **apiTradesIpfsHistoryGet**
> TradesViewModel apiTradesIpfsHistoryGet(type, ipfsHashId)

Get trades by IPFS hash id

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradesApi;


TradesApi apiInstance = new TradesApi();
String type = "type_example"; // String | 
String ipfsHashId = "ipfsHashId_example"; // String | 
try {
    TradesViewModel result = apiInstance.apiTradesIpfsHistoryGet(type, ipfsHashId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradesApi#apiTradesIpfsHistoryGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **type** | **String**|  | [enum: Undefined, MetaTrader4, MetaTrader5, NinjaTrader, cTrader, Rumus, Metastock]
 **ipfsHashId** | **String**|  | [optional]

### Return type

[**TradesViewModel**](TradesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

