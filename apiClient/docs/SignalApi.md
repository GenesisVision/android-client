# SignalApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10SignalAccountsGet**](SignalApi.md#v10SignalAccountsGet) | **GET** v1.0/signal/accounts | Get copytrading accounts
[**v10SignalAttachByIdPost**](SignalApi.md#v10SignalAttachByIdPost) | **POST** v1.0/signal/attach/{id} | Subscribe to programs signals
[**v10SignalDetachByIdPost**](SignalApi.md#v10SignalDetachByIdPost) | **POST** v1.0/signal/detach/{id} | Unsubscribe from program signals
[**v10SignalTradesByIdClosePost**](SignalApi.md#v10SignalTradesByIdClosePost) | **POST** v1.0/signal/trades/{id}/close | Close signal trade
[**v10SignalTradesGet**](SignalApi.md#v10SignalTradesGet) | **GET** v1.0/signal/trades | Get investors signals trades history
[**v10SignalTradesOpenGet**](SignalApi.md#v10SignalTradesOpenGet) | **GET** v1.0/signal/trades/open | Get investors signals open trades
[**v10SignalUpdatePost**](SignalApi.md#v10SignalUpdatePost) | **POST** v1.0/signal/update | Update signal subscription settings


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

<a name="v10SignalAttachByIdPost"></a>
# **v10SignalAttachByIdPost**
> Void v10SignalAttachByIdPost(id, authorization, mode, percent, openTolerancePercent, fixedVolume, fixedCurrency, initialDepositCurrency, initialDepositAmount)

Subscribe to programs signals

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | Program Id
String authorization = "authorization_example"; // String | JWT access token
String mode = "mode_example"; // String | 
Double percent = 3.4D; // Double | 
Double openTolerancePercent = 3.4D; // Double | 
Double fixedVolume = 3.4D; // Double | 
String fixedCurrency = "fixedCurrency_example"; // String | 
String initialDepositCurrency = "initialDepositCurrency_example"; // String | 
Double initialDepositAmount = 3.4D; // Double | 
try {
    Void result = apiInstance.v10SignalAttachByIdPost(id, authorization, mode, percent, openTolerancePercent, fixedVolume, fixedCurrency, initialDepositCurrency, initialDepositAmount);
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
 **mode** | **String**|  | [optional] [enum: ByBalance, Percent, Fixed]
 **percent** | **Double**|  | [optional]
 **openTolerancePercent** | **Double**|  | [optional]
 **fixedVolume** | **Double**|  | [optional]
 **fixedCurrency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **initialDepositCurrency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **initialDepositAmount** | **Double**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10SignalDetachByIdPost"></a>
# **v10SignalDetachByIdPost**
> Void v10SignalDetachByIdPost(id, authorization)

Unsubscribe from program signals

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | Program id
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10SignalDetachByIdPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#v10SignalDetachByIdPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)| Program id |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10SignalTradesByIdClosePost"></a>
# **v10SignalTradesByIdClosePost**
> Void v10SignalTradesByIdClosePost(id, authorization)

Close signal trade

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
UUID id = new UUID(); // UUID | Trade id
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10SignalTradesByIdClosePost(id, authorization);
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

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10SignalTradesGet"></a>
# **v10SignalTradesGet**
> TradesHistorySignalSlaveViewModel v10SignalTradesGet(authorization, dateFrom, dateTo, symbol, sorting, skip, take)

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
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesHistorySignalSlaveViewModel result = apiInstance.v10SignalTradesGet(authorization, dateFrom, dateTo, symbol, sorting, skip, take);
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
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesHistorySignalSlaveViewModel**](TradesHistorySignalSlaveViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10SignalTradesOpenGet"></a>
# **v10SignalTradesOpenGet**
> TradesOpenSignalSlaveViewModel v10SignalTradesOpenGet(authorization, sorting, skip, take)

Get investors signals open trades

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
String sorting = "sorting_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesOpenSignalSlaveViewModel result = apiInstance.v10SignalTradesOpenGet(authorization, sorting, skip, take);
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
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesOpenSignalSlaveViewModel**](TradesOpenSignalSlaveViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10SignalUpdatePost"></a>
# **v10SignalUpdatePost**
> Void v10SignalUpdatePost(authorization, id, mode, percent, openTolerancePercent, fixedVolume, fixedCurrency, initialDepositCurrency, initialDepositAmount)

Update signal subscription settings

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | Program id
String mode = "mode_example"; // String | 
Double percent = 3.4D; // Double | 
Double openTolerancePercent = 3.4D; // Double | 
Double fixedVolume = 3.4D; // Double | 
String fixedCurrency = "fixedCurrency_example"; // String | 
String initialDepositCurrency = "initialDepositCurrency_example"; // String | 
Double initialDepositAmount = 3.4D; // Double | 
try {
    Void result = apiInstance.v10SignalUpdatePost(authorization, id, mode, percent, openTolerancePercent, fixedVolume, fixedCurrency, initialDepositCurrency, initialDepositAmount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#v10SignalUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)| Program id | [optional]
 **mode** | **String**|  | [optional] [enum: ByBalance, Percent, Fixed]
 **percent** | **Double**|  | [optional]
 **openTolerancePercent** | **Double**|  | [optional]
 **fixedVolume** | **Double**|  | [optional]
 **fixedCurrency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **initialDepositCurrency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **initialDepositAmount** | **Double**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

