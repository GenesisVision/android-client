# EventsApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getEvents**](EventsApi.md#getEvents) | **GET** v2.0/events | Events

<a name="getEvents"></a>
# **getEvents**
> InvestmentEventViewModels getEvents(authorization, eventLocation, assetId, from, to, eventType, assetType, assetsIds, forceFilterByIds, eventGroup, skip, take)

Events

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.EventsApi;


EventsApi apiInstance = new EventsApi();
String authorization = "authorization_example"; // String | JWT access token
String eventLocation = "eventLocation_example"; // String | 
UUID assetId = new UUID(); // UUID | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
String eventType = "eventType_example"; // String | 
String assetType = "assetType_example"; // String | 
List<UUID> assetsIds = Arrays.asList(new UUID()); // List<UUID> | 
Boolean forceFilterByIds = true; // Boolean | 
String eventGroup = "eventGroup_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    InvestmentEventViewModels result = apiInstance.getEvents(authorization, eventLocation, assetId, from, to, eventType, assetType, assetsIds, forceFilterByIds, eventGroup, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EventsApi#getEvents");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **eventLocation** | **String**|  | [optional] [enum: Asset, Dashboard, EventsAll]
 **assetId** | [**UUID**](.md)|  | [optional]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **eventType** | **String**|  | [optional] [enum: All, AssetFinished, AssetPeriodStarted, AssetPeriodEnded, AssetPeriodEndedDueToStopOut, AssetBrokerChanged, AssetEnterInvestment, AssetInvestByInvestor, AssetWithdrawalByInvestor, AssetReallocation, AssetStarted, AssetPeriodProcessed, AssetInvestByManager, AssetWithdrawalByManager, AssetSubscribeByInvestor, AssetUnsubscribeByInvestor, AssetTradeOpen, AssetTradeClosed, AssetSubscriptionEdit]
 **assetType** | **String**|  | [optional] [enum: All, Program, Fund, Signal]
 **assetsIds** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **forceFilterByIds** | **Boolean**|  | [optional]
 **eventGroup** | **String**|  | [optional] [enum: InvestmentHistory, TradingHistory]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**InvestmentEventViewModels**](InvestmentEventViewModels.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

