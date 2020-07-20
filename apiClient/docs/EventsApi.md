# EventsApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getEvents**](EventsApi.md#getEvents) | **GET** v2.0/events | Events

<a name="getEvents"></a>
# **getEvents**
> InvestmentEventViewModels getEvents(eventLocation, assetId, from, to, dateFrom, dateTo, eventType, assetType, assetsIds, forceFilterByIds, eventGroup, skip, take)

Events

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EventsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

EventsApi apiInstance = new EventsApi();
InvestmentEventLocation eventLocation = new InvestmentEventLocation(); // InvestmentEventLocation | 
UUID assetId = new UUID(); // UUID | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
InvestmentEventType eventType = new InvestmentEventType(); // InvestmentEventType | 
AssetFilterType assetType = new AssetFilterType(); // AssetFilterType | 
List<UUID> assetsIds = Arrays.asList(new UUID()); // List<UUID> | 
Boolean forceFilterByIds = true; // Boolean | 
EventGroupType eventGroup = new EventGroupType(); // EventGroupType | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    InvestmentEventViewModels result = apiInstance.getEvents(eventLocation, assetId, from, to, dateFrom, dateTo, eventType, assetType, assetsIds, forceFilterByIds, eventGroup, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EventsApi#getEvents");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **eventLocation** | [**InvestmentEventLocation**](.md)|  | [optional]
 **assetId** | [**UUID**](.md)|  | [optional]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **eventType** | [**InvestmentEventType**](.md)|  | [optional]
 **assetType** | [**AssetFilterType**](.md)|  | [optional]
 **assetsIds** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **forceFilterByIds** | **Boolean**|  | [optional]
 **eventGroup** | [**EventGroupType**](.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**InvestmentEventViewModels**](InvestmentEventViewModels.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

