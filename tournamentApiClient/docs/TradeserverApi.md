# TradeserverApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiTradeserverInitDataGet**](TradeserverApi.md#apiTradeserverInitDataGet) | **GET** api/tradeserver/initData | Init data for trade server wrapper
[**apiTradeserverNewTradePost**](TradeserverApi.md#apiTradeserverNewTradePost) | **POST** api/tradeserver/newTrade | New trade event
[**apiTradeserverTradeAccountCreatedPost**](TradeserverApi.md#apiTradeserverTradeAccountCreatedPost) | **POST** api/tradeserver/tradeAccountCreated | Store trade account


<a name="apiTradeserverInitDataGet"></a>
# **apiTradeserverInitDataGet**
> TradeServerViewModel apiTradeserverInitDataGet(tradeServerId)

Init data for trade server wrapper

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradeserverApi;


TradeserverApi apiInstance = new TradeserverApi();
UUID tradeServerId = new UUID(); // UUID | 
try {
    TradeServerViewModel result = apiInstance.apiTradeserverInitDataGet(tradeServerId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradeserverApi#apiTradeserverInitDataGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **tradeServerId** | [**UUID**](.md)|  |

### Return type

[**TradeServerViewModel**](TradeServerViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiTradeserverNewTradePost"></a>
# **apiTradeserverNewTradePost**
> Void apiTradeserverNewTradePost(trade)

New trade event

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradeserverApi;


TradeserverApi apiInstance = new TradeserverApi();
NewTrade trade = new NewTrade(); // NewTrade | 
try {
    Void result = apiInstance.apiTradeserverNewTradePost(trade);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradeserverApi#apiTradeserverNewTradePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **trade** | [**NewTrade**](NewTrade.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiTradeserverTradeAccountCreatedPost"></a>
# **apiTradeserverTradeAccountCreatedPost**
> Void apiTradeserverTradeAccountCreatedPost(accounts)

Store trade account

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TradeserverApi;


TradeserverApi apiInstance = new TradeserverApi();
List<AccountCreated> accounts = Arrays.asList(new AccountCreated()); // List<AccountCreated> | 
try {
    Void result = apiInstance.apiTradeserverTradeAccountCreatedPost(accounts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TradeserverApi#apiTradeserverTradeAccountCreatedPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accounts** | [**List&lt;AccountCreated&gt;**](AccountCreated.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

