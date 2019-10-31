# SignalApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**attachSlaveCommonToMaster**](SignalApi.md#attachSlaveCommonToMaster) | **POST** v2.0/signal/external/attach/{id}/common | Subscribe to external signal using common account
[**attachSlaveToMaster**](SignalApi.md#attachSlaveToMaster) | **POST** v2.0/signal/attach/{id} | Subscribe to signal provider
[**attachSlaveToMaster_0**](SignalApi.md#attachSlaveToMaster_0) | **POST** v2.0/signal/external/attach/{id}/external | Subscribe to external signal account
[**closeTrade**](SignalApi.md#closeTrade) | **POST** v2.0/signal/trades/{id}/close | Close signal trade
[**createExternalSignalAccount**](SignalApi.md#createExternalSignalAccount) | **POST** v2.0/signal/external/create | Create external signal account
[**detachSlaveFromMaster**](SignalApi.md#detachSlaveFromMaster) | **POST** v2.0/signal/detach/{id} | Unsubscribe from signal provider
[**getExternalSignalTradingLog**](SignalApi.md#getExternalSignalTradingLog) | **GET** v2.0/signal/external/trades/log | Get investors signals trading log
[**getOpenSignalTrades**](SignalApi.md#getOpenSignalTrades) | **GET** v2.0/signal/trades/open | Get investors signals open trades
[**getSignalTrades**](SignalApi.md#getSignalTrades) | **GET** v2.0/signal/trades | Get investors signals trades history
[**getSignalTradingLog**](SignalApi.md#getSignalTradingLog) | **GET** v2.0/signal/trades/log | Get investors signals trading log
[**getSubscriberAccountsForAsset**](SignalApi.md#getSubscriberAccountsForAsset) | **GET** v2.0/signal/attach/{id}/accounts | Get subscriber accounts for subscribe to signal provider
[**updateSubscriptionSettings**](SignalApi.md#updateSubscriptionSettings) | **POST** v2.0/signal/{id}/update | Update signal subscription settings

<a name="attachSlaveCommonToMaster"></a>
# **attachSlaveCommonToMaster**
> Void attachSlaveCommonToMaster(authorization, id, body)

Subscribe to external signal using common account

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
AttachToExternalSignalProviderCommon body = new AttachToExternalSignalProviderCommon(); // AttachToExternalSignalProviderCommon | 
try {
    Void result = apiInstance.attachSlaveCommonToMaster(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#attachSlaveCommonToMaster");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**AttachToExternalSignalProviderCommon**](AttachToExternalSignalProviderCommon.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="attachSlaveToMaster"></a>
# **attachSlaveToMaster**
> Void attachSlaveToMaster(authorization, id, body)

Subscribe to signal provider

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
AttachToSignalProvider body = new AttachToSignalProvider(); // AttachToSignalProvider | 
try {
    Void result = apiInstance.attachSlaveToMaster(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#attachSlaveToMaster");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**AttachToSignalProvider**](AttachToSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="attachSlaveToMaster_0"></a>
# **attachSlaveToMaster_0**
> Void attachSlaveToMaster_0(authorization, id, body)

Subscribe to external signal account

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
AttachToExternalSignalProviderExt body = new AttachToExternalSignalProviderExt(); // AttachToExternalSignalProviderExt | 
try {
    Void result = apiInstance.attachSlaveToMaster_0(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#attachSlaveToMaster_0");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**AttachToExternalSignalProviderExt**](AttachToExternalSignalProviderExt.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="closeTrade"></a>
# **closeTrade**
> Void closeTrade(id, authorization, assetId)

Close signal trade

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | Trade id
String authorization = "authorization_example"; // String | JWT access token
UUID assetId = new UUID(); // UUID | Provider asset id
try {
    Void result = apiInstance.closeTrade(id, authorization, assetId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#closeTrade");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)| Trade id |
 **authorization** | **String**| JWT access token |
 **assetId** | [**UUID**](.md)| Provider asset id | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="createExternalSignalAccount"></a>
# **createExternalSignalAccount**
> Void createExternalSignalAccount(authorization, body)

Create external signal account

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
NewExternalSignalAccountRequest body = new NewExternalSignalAccountRequest(); // NewExternalSignalAccountRequest | 
try {
    Void result = apiInstance.createExternalSignalAccount(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#createExternalSignalAccount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**NewExternalSignalAccountRequest**](NewExternalSignalAccountRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="detachSlaveFromMaster"></a>
# **detachSlaveFromMaster**
> Void detachSlaveFromMaster(authorization, id, body)

Unsubscribe from signal provider

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
DetachFromSignalProvider body = new DetachFromSignalProvider(); // DetachFromSignalProvider | 
try {
    Void result = apiInstance.detachSlaveFromMaster(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#detachSlaveFromMaster");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**DetachFromSignalProvider**](DetachFromSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="getExternalSignalTradingLog"></a>
# **getExternalSignalTradingLog**
> SignalTradingEvents getExternalSignalTradingLog(authorization, accountId, accountCurrency, skip, take)

Get investors signals trading log

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
UUID accountId = new UUID(); // UUID | 
String accountCurrency = "accountCurrency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    SignalTradingEvents result = apiInstance.getExternalSignalTradingLog(authorization, accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#getExternalSignalTradingLog");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**SignalTradingEvents**](SignalTradingEvents.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getOpenSignalTrades"></a>
# **getOpenSignalTrades**
> TradesSignalViewModel getOpenSignalTrades(authorization, sorting, symbol, accountId, accountCurrency, skip, take)

Get investors signals open trades

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
String sorting = "sorting_example"; // String | 
String symbol = "symbol_example"; // String | 
UUID accountId = new UUID(); // UUID | 
String accountCurrency = "accountCurrency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesSignalViewModel result = apiInstance.getOpenSignalTrades(authorization, sorting, symbol, accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#getOpenSignalTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc, ByCommissionAsc, ByCommissionDesc, BySwapAsc, BySwapDesc]
 **symbol** | **String**|  | [optional]
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesSignalViewModel**](TradesSignalViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getSignalTrades"></a>
# **getSignalTrades**
> TradesSignalViewModel getSignalTrades(authorization, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take)

Get investors signals trades history

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String symbol = "symbol_example"; // String | 
String sorting = "sorting_example"; // String | 
UUID accountId = new UUID(); // UUID | 
String accountCurrency = "accountCurrency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesSignalViewModel result = apiInstance.getSignalTrades(authorization, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#getSignalTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **symbol** | **String**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc, ByCommissionAsc, ByCommissionDesc, BySwapAsc, BySwapDesc]
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesSignalViewModel**](TradesSignalViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getSignalTradingLog"></a>
# **getSignalTradingLog**
> SignalTradingEvents getSignalTradingLog(authorization, accountId, accountCurrency, skip, take)

Get investors signals trading log

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
UUID accountId = new UUID(); // UUID | 
String accountCurrency = "accountCurrency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    SignalTradingEvents result = apiInstance.getSignalTradingLog(authorization, accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#getSignalTradingLog");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**SignalTradingEvents**](SignalTradingEvents.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getSubscriberAccountsForAsset"></a>
# **getSubscriberAccountsForAsset**
> Void getSubscriberAccountsForAsset(id, authorization)

Get subscriber accounts for subscribe to signal provider

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.getSubscriberAccountsForAsset(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#getSubscriberAccountsForAsset");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="updateSubscriptionSettings"></a>
# **updateSubscriptionSettings**
> Void updateSubscriptionSettings(authorization, id, body)

Update signal subscription settings

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | Asset id
AttachToSignalProvider body = new AttachToSignalProvider(); // AttachToSignalProvider | Subscription settings
try {
    Void result = apiInstance.updateSubscriptionSettings(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#updateSubscriptionSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)| Asset id |
 **body** | [**AttachToSignalProvider**](AttachToSignalProvider.md)| Subscription settings | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

