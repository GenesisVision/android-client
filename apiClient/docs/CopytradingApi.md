# CopytradingApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getSignalAssets**](CopytradingApi.md#getSignalAssets) | **GET** v2.0/copytrading | Get GV Follow assets

<a name="getSignalAssets"></a>
# **getSignalAssets**
> ItemsViewModelCopyTradingAccountInfo getSignalAssets(authorization, tags, showIn, statisticDateFrom, statisticDateTo, chartPointsCount, facetId, mask, skip, take)

Get GV Follow assets

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.CopytradingApi;


CopytradingApi apiInstance = new CopytradingApi();
String authorization = "authorization_example"; // String | 
List<String> tags = Arrays.asList("tags_example"); // List<String> | 
String showIn = "showIn_example"; // String | 
DateTime statisticDateFrom = new DateTime(); // DateTime | 
DateTime statisticDateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String facetId = "facetId_example"; // String | 
String mask = "mask_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ItemsViewModelCopyTradingAccountInfo result = apiInstance.getSignalAssets(authorization, tags, showIn, statisticDateFrom, statisticDateTo, chartPointsCount, facetId, mask, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CopytradingApi#getSignalAssets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**|  | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **showIn** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **statisticDateFrom** | **DateTime**|  | [optional]
 **statisticDateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **facetId** | **String**|  | [optional]
 **mask** | **String**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelCopyTradingAccountInfo**](ItemsViewModelCopyTradingAccountInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

