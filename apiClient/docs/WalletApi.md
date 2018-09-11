# WalletApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10WalletAddressByCurrencyGet**](WalletApi.md#v10WalletAddressByCurrencyGet) | **GET** v1.0/wallet/address/{currency} | 
[**v10WalletByCurrencyGet**](WalletApi.md#v10WalletByCurrencyGet) | **GET** v1.0/wallet/{currency} | Wallet summary
[**v10WalletTransactionsGet**](WalletApi.md#v10WalletTransactionsGet) | **GET** v1.0/wallet/transactions | Wallet transactions
[**v10WalletWithdrawRequestCancelByTxIdPost**](WalletApi.md#v10WalletWithdrawRequestCancelByTxIdPost) | **POST** v1.0/wallet/withdraw/request/cancel/{txId} | 
[**v10WalletWithdrawRequestConfirmPost**](WalletApi.md#v10WalletWithdrawRequestConfirmPost) | **POST** v1.0/wallet/withdraw/request/confirm | 
[**v10WalletWithdrawRequestNewPost**](WalletApi.md#v10WalletWithdrawRequestNewPost) | **POST** v1.0/wallet/withdraw/request/new | 
[**v10WalletWithdrawRequestResendByTxIdPost**](WalletApi.md#v10WalletWithdrawRequestResendByTxIdPost) | **POST** v1.0/wallet/withdraw/request/resend/{txId} | 


<a name="v10WalletAddressByCurrencyGet"></a>
# **v10WalletAddressByCurrencyGet**
> WalletInfo v10WalletAddressByCurrencyGet(currency, authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    WalletInfo result = apiInstance.v10WalletAddressByCurrencyGet(currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletAddressByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USD, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**WalletInfo**](WalletInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletByCurrencyGet"></a>
# **v10WalletByCurrencyGet**
> WalletSummary v10WalletByCurrencyGet(currency, authorization)

Wallet summary

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    WalletSummary result = apiInstance.v10WalletByCurrencyGet(currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USD, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**WalletSummary**](WalletSummary.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletTransactionsGet"></a>
# **v10WalletTransactionsGet**
> WalletTransactionsViewModel v10WalletTransactionsGet(authorization, assetId, from, to, assetType, txAction, skip, take)

Wallet transactions

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
UUID assetId = new UUID(); // UUID | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
String assetType = "assetType_example"; // String | 
String txAction = "txAction_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    WalletTransactionsViewModel result = apiInstance.v10WalletTransactionsGet(authorization, assetId, from, to, assetType, txAction, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletTransactionsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **assetId** | [**UUID**](.md)|  | [optional]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **assetType** | **String**|  | [optional] [enum: All, Program, Fund]
 **txAction** | **String**|  | [optional] [enum: Transfer, ProgramOpen, ProgramProfit, ProgramInvest, ProgramWithdrawal, ProgramRefundPartialExecution, ProgramRefundClose, ProgramRequestInvest, ProgramRequestWithdrawal, ProgramRequestCancel]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**WalletTransactionsViewModel**](WalletTransactionsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletWithdrawRequestCancelByTxIdPost"></a>
# **v10WalletWithdrawRequestCancelByTxIdPost**
> Void v10WalletWithdrawRequestCancelByTxIdPost(txId, authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
UUID txId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10WalletWithdrawRequestCancelByTxIdPost(txId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletWithdrawRequestCancelByTxIdPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **txId** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletWithdrawRequestConfirmPost"></a>
# **v10WalletWithdrawRequestConfirmPost**
> Void v10WalletWithdrawRequestConfirmPost(requestId, code)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
UUID requestId = new UUID(); // UUID | 
String code = "code_example"; // String | 
try {
    Void result = apiInstance.v10WalletWithdrawRequestConfirmPost(requestId, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletWithdrawRequestConfirmPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestId** | [**UUID**](.md)|  | [optional]
 **code** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletWithdrawRequestNewPost"></a>
# **v10WalletWithdrawRequestNewPost**
> Void v10WalletWithdrawRequestNewPost(authorization, model)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
CreateWithdrawalRequestModel model = new CreateWithdrawalRequestModel(); // CreateWithdrawalRequestModel | 
try {
    Void result = apiInstance.v10WalletWithdrawRequestNewPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletWithdrawRequestNewPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**CreateWithdrawalRequestModel**](CreateWithdrawalRequestModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletWithdrawRequestResendByTxIdPost"></a>
# **v10WalletWithdrawRequestResendByTxIdPost**
> Void v10WalletWithdrawRequestResendByTxIdPost(txId, authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
UUID txId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10WalletWithdrawRequestResendByTxIdPost(txId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletWithdrawRequestResendByTxIdPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **txId** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

