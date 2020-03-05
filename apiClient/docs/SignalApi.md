# SignalApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**attachSlaveToMasterExternalCommonAccount**](SignalApi.md#attachSlaveToMasterExternalCommonAccount) | **POST** v2.0/signal/external/attach/{id}/common | Subscribe to external signal using common account
[**attachSlaveToMasterExternalPrivateAccount**](SignalApi.md#attachSlaveToMasterExternalPrivateAccount) | **POST** v2.0/signal/external/attach/{id}/private | Subscribe to external signal account
[**attachSlaveToMasterInternal**](SignalApi.md#attachSlaveToMasterInternal) | **POST** v2.0/signal/attach/{id} | Subscribe to signal provider
[**closeTradeInternal**](SignalApi.md#closeTradeInternal) | **POST** v2.0/signal/trades/{id}/close | Close signal trade
[**detachSlaveFromMasterExternal**](SignalApi.md#detachSlaveFromMasterExternal) | **POST** v2.0/signal/external/detach/{id} | 
[**detachSlaveFromMasterInternal**](SignalApi.md#detachSlaveFromMasterInternal) | **POST** v2.0/signal/detach/{id} | Unsubscribe from signal provider
[**getOpenSignalTrades**](SignalApi.md#getOpenSignalTrades) | **GET** v2.0/signal/trades/open | Get investors signals open trades
[**getSignalTradingLog**](SignalApi.md#getSignalTradingLog) | **GET** v2.0/signal/trades/log | Get investors signals trading log
[**getSubscriberAccountsForAsset**](SignalApi.md#getSubscriberAccountsForAsset) | **GET** v2.0/signal/attach/{id}/accounts | Get subscriber accounts for subscribe to signal provider (common method for all signals)
[**updateExternalSubscriptionSettings**](SignalApi.md#updateExternalSubscriptionSettings) | **POST** v2.0/signal/external/{id}/update | Update signal subscription settings
[**updateSubscriptionSettings**](SignalApi.md#updateSubscriptionSettings) | **POST** v2.0/signal/{id}/update | Update signal subscription settings

<a name="attachSlaveToMasterExternalCommonAccount"></a>
# **attachSlaveToMasterExternalCommonAccount**
> Void attachSlaveToMasterExternalCommonAccount(authorization, id, body)

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
    Void result = apiInstance.attachSlaveToMasterExternalCommonAccount(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#attachSlaveToMasterExternalCommonAccount");
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

<a name="attachSlaveToMasterExternalPrivateAccount"></a>
# **attachSlaveToMasterExternalPrivateAccount**
> Void attachSlaveToMasterExternalPrivateAccount(authorization, id, body)

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
    Void result = apiInstance.attachSlaveToMasterExternalPrivateAccount(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#attachSlaveToMasterExternalPrivateAccount");
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

<a name="attachSlaveToMasterInternal"></a>
# **attachSlaveToMasterInternal**
> Void attachSlaveToMasterInternal(authorization, id, body)

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
    Void result = apiInstance.attachSlaveToMasterInternal(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#attachSlaveToMasterInternal");
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

<a name="closeTradeInternal"></a>
# **closeTradeInternal**
> Void closeTradeInternal(id, authorization, assetId)

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
    Void result = apiInstance.closeTradeInternal(id, authorization, assetId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#closeTradeInternal");
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

<a name="detachSlaveFromMasterExternal"></a>
# **detachSlaveFromMasterExternal**
> Void detachSlaveFromMasterExternal(authorization, id, body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
DetachFromExternalSignalProvider body = new DetachFromExternalSignalProvider(); // DetachFromExternalSignalProvider | 
try {
    Void result = apiInstance.detachSlaveFromMasterExternal(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#detachSlaveFromMasterExternal");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**DetachFromExternalSignalProvider**](DetachFromExternalSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="detachSlaveFromMasterInternal"></a>
# **detachSlaveFromMasterInternal**
> Void detachSlaveFromMasterInternal(authorization, id, body)

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
    Void result = apiInstance.detachSlaveFromMasterInternal(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#detachSlaveFromMasterInternal");
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
 **accountCurrency** | **String**|  | [optional] [enum: BTC, ETH, USDT, USD, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
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
> ItemsViewModelSignalTradingEvent getSignalTradingLog(authorization, accountId, accountCurrency, skip, take)

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
    ItemsViewModelSignalTradingEvent result = apiInstance.getSignalTradingLog(authorization, accountId, accountCurrency, skip, take);
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
 **accountCurrency** | **String**|  | [optional] [enum: BTC, ETH, USDT, USD, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelSignalTradingEvent**](ItemsViewModelSignalTradingEvent.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getSubscriberAccountsForAsset"></a>
# **getSubscriberAccountsForAsset**
> ItemsViewModelTradingAccountDetails getSubscriberAccountsForAsset(id, authorization)

Get subscriber accounts for subscribe to signal provider (common method for all signals)

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ItemsViewModelTradingAccountDetails result = apiInstance.getSubscriberAccountsForAsset(id, authorization);
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

[**ItemsViewModelTradingAccountDetails**](ItemsViewModelTradingAccountDetails.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="updateExternalSubscriptionSettings"></a>
# **updateExternalSubscriptionSettings**
> Void updateExternalSubscriptionSettings(authorization, id, body)

Update signal subscription settings

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
    Void result = apiInstance.updateExternalSubscriptionSettings(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#updateExternalSubscriptionSettings");
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
UUID id = new UUID(); // UUID | 
AttachToSignalProvider body = new AttachToSignalProvider(); // AttachToSignalProvider | 
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
 **id** | [**UUID**](.md)|  |
 **body** | [**AttachToSignalProvider**](AttachToSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

