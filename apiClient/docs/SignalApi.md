# SignalApi

All URIs are relative to *https://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10SignalAccountsGet**](SignalApi.md#v10SignalAccountsGet) | **GET** v1.0/signal/accounts | Get copytrading accounts
[**v10SignalAttachByIdInfoGet**](SignalApi.md#v10SignalAttachByIdInfoGet) | **GET** v1.0/signal/attach/{id}/info | Get subscribe to programs signals info
[**v10SignalAttachByIdPost**](SignalApi.md#v10SignalAttachByIdPost) | **POST** v1.0/signal/attach/{id} | Subscribe to programs signals
[**v10SignalByIdUpdatePost**](SignalApi.md#v10SignalByIdUpdatePost) | **POST** v1.0/signal/{id}/update | Update signal subscription settings
[**v10SignalDetachByIdPost**](SignalApi.md#v10SignalDetachByIdPost) | **POST** v1.0/signal/detach/{id} | Unsubscribe from program signals
[**v10SignalTradesByIdClosePost**](SignalApi.md#v10SignalTradesByIdClosePost) | **POST** v1.0/signal/trades/{id}/close | Close signal trade
[**v10SignalTradesGet**](SignalApi.md#v10SignalTradesGet) | **GET** v1.0/signal/trades | Get investors signals trades history
[**v10SignalTradesLogGet**](SignalApi.md#v10SignalTradesLogGet) | **GET** v1.0/signal/trades/log | Get investors signals trading log
[**v10SignalTradesOpenGet**](SignalApi.md#v10SignalTradesOpenGet) | **GET** v1.0/signal/trades/open | Get investors signals open trades


<a name="v10SignalAccountsGet"></a>
# **v10SignalAccountsGet**
> CopyTradingAccountsList v10SignalAccountsGet(authorization)

Get copytrading accounts

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    CopyTradingAccountsList result = apiInstance.v10SignalAccountsGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#v10SignalAccountsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**CopyTradingAccountsList**](CopyTradingAccountsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10SignalAttachByIdInfoGet"></a>
# **v10SignalAttachByIdInfoGet**
> AttachToSignalProviderInfo v10SignalAttachByIdInfoGet(id, authorization)

Get subscribe to programs signals info

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    AttachToSignalProviderInfo result = apiInstance.v10SignalAttachByIdInfoGet(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#v10SignalAttachByIdInfoGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**AttachToSignalProviderInfo**](AttachToSignalProviderInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10SignalAttachByIdPost"></a>
# **v10SignalAttachByIdPost**
> Void v10SignalAttachByIdPost(id, authorization, model)

Subscribe to programs signals

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | Program Id
String authorization = "authorization_example"; // String | JWT access token
AttachToSignalProvider model = new AttachToSignalProvider(); // AttachToSignalProvider | Subscription settings
try {
    Void result = apiInstance.v10SignalAttachByIdPost(id, authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#v10SignalAttachByIdPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)| Program Id |
 **authorization** | **String**| JWT access token |
 **model** | [**AttachToSignalProvider**](AttachToSignalProvider.md)| Subscription settings | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10SignalByIdUpdatePost"></a>
# **v10SignalByIdUpdatePost**
> Void v10SignalByIdUpdatePost(id, authorization, model)

Update signal subscription settings

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | Program id
String authorization = "authorization_example"; // String | JWT access token
AttachToSignalProvider model = new AttachToSignalProvider(); // AttachToSignalProvider | Subscription settings
try {
    Void result = apiInstance.v10SignalByIdUpdatePost(id, authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#v10SignalByIdUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)| Program id |
 **authorization** | **String**| JWT access token |
 **model** | [**AttachToSignalProvider**](AttachToSignalProvider.md)| Subscription settings | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10SignalDetachByIdPost"></a>
# **v10SignalDetachByIdPost**
> Void v10SignalDetachByIdPost(id, authorization, model)

Unsubscribe from program signals

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
DetachFromSignalProvider model = new DetachFromSignalProvider(); // DetachFromSignalProvider | 
try {
    Void result = apiInstance.v10SignalDetachByIdPost(id, authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#v10SignalDetachByIdPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **model** | [**DetachFromSignalProvider**](DetachFromSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10SignalTradesByIdClosePost"></a>
# **v10SignalTradesByIdClosePost**
> Void v10SignalTradesByIdClosePost(id, authorization, programId)

Close signal trade

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | Trade id
String authorization = "authorization_example"; // String | JWT access token
UUID programId = new UUID(); // UUID | Provider program id
try {
    Void result = apiInstance.v10SignalTradesByIdClosePost(id, authorization, programId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#v10SignalTradesByIdClosePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)| Trade id |
 **authorization** | **String**| JWT access token |
 **programId** | [**UUID**](.md)| Provider program id | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10SignalTradesGet"></a>
# **v10SignalTradesGet**
> TradesSignalViewModel v10SignalTradesGet(authorization, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take)

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
    TradesSignalViewModel result = apiInstance.v10SignalTradesGet(authorization, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#v10SignalTradesGet");
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

<a name="v10SignalTradesLogGet"></a>
# **v10SignalTradesLogGet**
> SignalTradingEvents v10SignalTradesLogGet(authorization, accountId, accountCurrency, skip, take)

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
    SignalTradingEvents result = apiInstance.v10SignalTradesLogGet(authorization, accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#v10SignalTradesLogGet");
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

<a name="v10SignalTradesOpenGet"></a>
# **v10SignalTradesOpenGet**
> TradesSignalViewModel v10SignalTradesOpenGet(authorization, sorting, symbol, accountId, accountCurrency, skip, take)

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
    TradesSignalViewModel result = apiInstance.v10SignalTradesOpenGet(authorization, sorting, symbol, accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#v10SignalTradesOpenGet");
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

