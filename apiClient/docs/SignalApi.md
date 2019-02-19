# SignalApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10SignalAccountsGet**](SignalApi.md#v10SignalAccountsGet) | **GET** v1.0/signal/accounts | Get copytrading accounts
[**v10SignalAttachByIdPost**](SignalApi.md#v10SignalAttachByIdPost) | **POST** v1.0/signal/attach/{id} | Subscribe to programs signals
[**v10SignalDetachByIdPost**](SignalApi.md#v10SignalDetachByIdPost) | **POST** v1.0/signal/detach/{id} | Unsubscribe from program signals
[**v10SignalOpensignaltradesGet**](SignalApi.md#v10SignalOpensignaltradesGet) | **GET** v1.0/signal/opensignaltrades | Get investors signals open trades
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

<a name="v10SignalOpensignaltradesGet"></a>
# **v10SignalOpensignaltradesGet**
> OpenSignalTradesList v10SignalOpensignaltradesGet(authorization)

Get investors signals open trades

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SignalApi;


SignalApi apiInstance = new SignalApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    OpenSignalTradesList result = apiInstance.v10SignalOpensignaltradesGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SignalApi#v10SignalOpensignaltradesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**OpenSignalTradesList**](OpenSignalTradesList.md)

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

