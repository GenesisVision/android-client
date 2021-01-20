# SignalApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**attachSlaveToMasterExternalPrivateAccount**](SignalApi.md#attachSlaveToMasterExternalPrivateAccount) | **POST** v2.0/signal/external/attach/{id}/private | Subscribe to external signal account
[**attachSlaveToMasterInternal**](SignalApi.md#attachSlaveToMasterInternal) | **POST** v2.0/signal/attach/{id} | Subscribe to signal provider
[**detachSlaveFromMasterExternal**](SignalApi.md#detachSlaveFromMasterExternal) | **POST** v2.0/signal/external/detach/{id} | 
[**detachSlaveFromMasterInternal**](SignalApi.md#detachSlaveFromMasterInternal) | **POST** v2.0/signal/detach/{id} | Unsubscribe from signal provider
[**getSignalTradingLog**](SignalApi.md#getSignalTradingLog) | **GET** v2.0/signal/trades/log | Get investors signals trading log
[**getSubscriberAccountsForAsset**](SignalApi.md#getSubscriberAccountsForAsset) | **GET** v2.0/signal/attach/{id}/accounts | Get subscriber accounts for subscribe to signal provider (common method for all signals)
[**updateExternalSubscriptionSettings**](SignalApi.md#updateExternalSubscriptionSettings) | **POST** v2.0/signal/external/{id}/update | Update signal subscription settings
[**updateSubscriptionSettings**](SignalApi.md#updateSubscriptionSettings) | **POST** v2.0/signal/{id}/update | Update signal subscription settings

<a name="attachSlaveToMasterExternalPrivateAccount"></a>
# **attachSlaveToMasterExternalPrivateAccount**
> Void attachSlaveToMasterExternalPrivateAccount(id, body)

Subscribe to external signal account

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SignalApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | 
AttachToExternalSignalProviderExt body = new AttachToExternalSignalProviderExt(); // AttachToExternalSignalProviderExt | 
try {
    Void result = apiInstance.attachSlaveToMasterExternalPrivateAccount(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#attachSlaveToMasterExternalPrivateAccount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**AttachToExternalSignalProviderExt**](AttachToExternalSignalProviderExt.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="attachSlaveToMasterInternal"></a>
# **attachSlaveToMasterInternal**
> Void attachSlaveToMasterInternal(id, body)

Subscribe to signal provider

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SignalApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | 
AttachToSignalProvider body = new AttachToSignalProvider(); // AttachToSignalProvider | 
try {
    Void result = apiInstance.attachSlaveToMasterInternal(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#attachSlaveToMasterInternal");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**AttachToSignalProvider**](AttachToSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="detachSlaveFromMasterExternal"></a>
# **detachSlaveFromMasterExternal**
> Void detachSlaveFromMasterExternal(id, body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SignalApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | 
DetachFromExternalSignalProvider body = new DetachFromExternalSignalProvider(); // DetachFromExternalSignalProvider | 
try {
    Void result = apiInstance.detachSlaveFromMasterExternal(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#detachSlaveFromMasterExternal");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**DetachFromExternalSignalProvider**](DetachFromExternalSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="detachSlaveFromMasterInternal"></a>
# **detachSlaveFromMasterInternal**
> Void detachSlaveFromMasterInternal(id, body)

Unsubscribe from signal provider

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SignalApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | 
DetachFromSignalProvider body = new DetachFromSignalProvider(); // DetachFromSignalProvider | 
try {
    Void result = apiInstance.detachSlaveFromMasterInternal(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#detachSlaveFromMasterInternal");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**DetachFromSignalProvider**](DetachFromSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="getSignalTradingLog"></a>
# **getSignalTradingLog**
> SignalTradingEventItemsViewModel getSignalTradingLog(accountId, accountCurrency, skip, take)

Get investors signals trading log

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SignalApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SignalApi apiInstance = new SignalApi();
UUID accountId = new UUID(); // UUID | 
Currency accountCurrency = new Currency(); // Currency | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    SignalTradingEventItemsViewModel result = apiInstance.getSignalTradingLog(accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#getSignalTradingLog");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | [**Currency**](.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**SignalTradingEventItemsViewModel**](SignalTradingEventItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getSubscriberAccountsForAsset"></a>
# **getSubscriberAccountsForAsset**
> TradingAccountDetailsItemsViewModel getSubscriberAccountsForAsset(id)

Get subscriber accounts for subscribe to signal provider (common method for all signals)

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SignalApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | 
try {
    TradingAccountDetailsItemsViewModel result = apiInstance.getSubscriberAccountsForAsset(id);
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

### Return type

[**TradingAccountDetailsItemsViewModel**](TradingAccountDetailsItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="updateExternalSubscriptionSettings"></a>
# **updateExternalSubscriptionSettings**
> Void updateExternalSubscriptionSettings(id, body)

Update signal subscription settings

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SignalApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | 
AttachToExternalSignalProviderExt body = new AttachToExternalSignalProviderExt(); // AttachToExternalSignalProviderExt | 
try {
    Void result = apiInstance.updateExternalSubscriptionSettings(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#updateExternalSubscriptionSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**AttachToExternalSignalProviderExt**](AttachToExternalSignalProviderExt.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateSubscriptionSettings"></a>
# **updateSubscriptionSettings**
> Void updateSubscriptionSettings(id, body)

Update signal subscription settings

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SignalApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | 
AttachToSignalProvider body = new AttachToSignalProvider(); // AttachToSignalProvider | 
try {
    Void result = apiInstance.updateSubscriptionSettings(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#updateSubscriptionSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**AttachToSignalProvider**](AttachToSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

