# WalletApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10WalletAddressesByCurrencyGet**](WalletApi.md#v10WalletAddressesByCurrencyGet) | **GET** v1.0/wallet/addresses/{currency} | 
[**v10WalletAddressesGet**](WalletApi.md#v10WalletAddressesGet) | **GET** v1.0/wallet/addresses | 
[**v10WalletByCurrencyGet**](WalletApi.md#v10WalletByCurrencyGet) | **GET** v1.0/wallet/{currency} | Wallet summary
[**v10WalletMultiByCurrencyGet**](WalletApi.md#v10WalletMultiByCurrencyGet) | **GET** v1.0/wallet/multi/{currency} | Multi wallet summary
[**v10WalletMultiFiltersGet**](WalletApi.md#v10WalletMultiFiltersGet) | **GET** v1.0/wallet/multi/filters | Get filters
[**v10WalletMultiTransactionsExternalGet**](WalletApi.md#v10WalletMultiTransactionsExternalGet) | **GET** v1.0/wallet/multi/transactions/external | Wallet pending transactions
[**v10WalletMultiTransactionsGet**](WalletApi.md#v10WalletMultiTransactionsGet) | **GET** v1.0/wallet/multi/transactions | Multi wallet transactions
[**v10WalletPaygvtfeeOffPost**](WalletApi.md#v10WalletPaygvtfeeOffPost) | **POST** v1.0/wallet/paygvtfee/off | Disable paying platform fees with GVT
[**v10WalletPaygvtfeeOnPost**](WalletApi.md#v10WalletPaygvtfeeOnPost) | **POST** v1.0/wallet/paygvtfee/on | Enable paying platform fees with GVT
[**v10WalletTransactionByIdGet**](WalletApi.md#v10WalletTransactionByIdGet) | **GET** v1.0/wallet/transaction/{id} | Get transaction details
[**v10WalletTransactionsGet**](WalletApi.md#v10WalletTransactionsGet) | **GET** v1.0/wallet/transactions | Wallet transactions
[**v10WalletTransferPost**](WalletApi.md#v10WalletTransferPost) | **POST** v1.0/wallet/transfer | Transfer money
[**v10WalletWithdrawInfoGet**](WalletApi.md#v10WalletWithdrawInfoGet) | **GET** v1.0/wallet/withdraw/info | 
[**v10WalletWithdrawRequestCancelByTxIdPost**](WalletApi.md#v10WalletWithdrawRequestCancelByTxIdPost) | **POST** v1.0/wallet/withdraw/request/cancel/{txId} | 
[**v10WalletWithdrawRequestConfirmPost**](WalletApi.md#v10WalletWithdrawRequestConfirmPost) | **POST** v1.0/wallet/withdraw/request/confirm | 
[**v10WalletWithdrawRequestNewPost**](WalletApi.md#v10WalletWithdrawRequestNewPost) | **POST** v1.0/wallet/withdraw/request/new | 
[**v10WalletWithdrawRequestResendByTxIdPost**](WalletApi.md#v10WalletWithdrawRequestResendByTxIdPost) | **POST** v1.0/wallet/withdraw/request/resend/{txId} | 


<a name="v10WalletAddressesByCurrencyGet"></a>
# **v10WalletAddressesByCurrencyGet**
> WalletInfo v10WalletAddressesByCurrencyGet(currency, authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    WalletInfo result = apiInstance.v10WalletAddressesByCurrencyGet(currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletAddressesByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**WalletInfo**](WalletInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletAddressesGet"></a>
# **v10WalletAddressesGet**
> WalletsInfo v10WalletAddressesGet(authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    WalletsInfo result = apiInstance.v10WalletAddressesGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletAddressesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**WalletsInfo**](WalletsInfo.md)

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
 **currency** | **String**|  | [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**WalletSummary**](WalletSummary.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletMultiByCurrencyGet"></a>
# **v10WalletMultiByCurrencyGet**
> WalletMultiSummary v10WalletMultiByCurrencyGet(currency, authorization)

Multi wallet summary

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    WalletMultiSummary result = apiInstance.v10WalletMultiByCurrencyGet(currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletMultiByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**WalletMultiSummary**](WalletMultiSummary.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletMultiFiltersGet"></a>
# **v10WalletMultiFiltersGet**
> MultiWalletFilters v10WalletMultiFiltersGet(authorization)

Get filters

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    MultiWalletFilters result = apiInstance.v10WalletMultiFiltersGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletMultiFiltersGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**MultiWalletFilters**](MultiWalletFilters.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletMultiTransactionsExternalGet"></a>
# **v10WalletMultiTransactionsExternalGet**
> MultiWalletExternalTransactionsViewModel v10WalletMultiTransactionsExternalGet(authorization, from, to, type, currency, skip, take)

Wallet pending transactions

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
String type = "type_example"; // String | 
String currency = "currency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    MultiWalletExternalTransactionsViewModel result = apiInstance.v10WalletMultiTransactionsExternalGet(authorization, from, to, type, currency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletMultiTransactionsExternalGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **type** | **String**|  | [optional] [enum: All, Deposit, Withdrawal]
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**MultiWalletExternalTransactionsViewModel**](MultiWalletExternalTransactionsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletMultiTransactionsGet"></a>
# **v10WalletMultiTransactionsGet**
> MultiWalletTransactionsViewModel v10WalletMultiTransactionsGet(authorization, from, to, type, currency, skip, take)

Multi wallet transactions

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
String type = "type_example"; // String | 
String currency = "currency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    MultiWalletTransactionsViewModel result = apiInstance.v10WalletMultiTransactionsGet(authorization, from, to, type, currency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletMultiTransactionsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **type** | **String**|  | [optional] [enum: All, Investment, Converting, Withdrawal, Close, Open, Fee, Profits]
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**MultiWalletTransactionsViewModel**](MultiWalletTransactionsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletPaygvtfeeOffPost"></a>
# **v10WalletPaygvtfeeOffPost**
> Void v10WalletPaygvtfeeOffPost(authorization)

Disable paying platform fees with GVT

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10WalletPaygvtfeeOffPost(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletPaygvtfeeOffPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletPaygvtfeeOnPost"></a>
# **v10WalletPaygvtfeeOnPost**
> Void v10WalletPaygvtfeeOnPost(authorization)

Enable paying platform fees with GVT

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10WalletPaygvtfeeOnPost(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletPaygvtfeeOnPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletTransactionByIdGet"></a>
# **v10WalletTransactionByIdGet**
> TransactionDetatils v10WalletTransactionByIdGet(id, authorization)

Get transaction details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    TransactionDetatils result = apiInstance.v10WalletTransactionByIdGet(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletTransactionByIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**TransactionDetatils**](TransactionDetatils.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletTransactionsGet"></a>
# **v10WalletTransactionsGet**
> WalletTransactionsViewModel v10WalletTransactionsGet(authorization, assetId, from, to, assetType, txAction, wallet, skip, take)

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
String wallet = "wallet_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    WalletTransactionsViewModel result = apiInstance.v10WalletTransactionsGet(authorization, assetId, from, to, assetType, txAction, wallet, skip, take);
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
 **wallet** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**WalletTransactionsViewModel**](WalletTransactionsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletTransferPost"></a>
# **v10WalletTransferPost**
> Void v10WalletTransferPost(authorization, from, to, amount)

Transfer money

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
String from = "from_example"; // String | 
String to = "to_example"; // String | 
Double amount = 3.4D; // Double | 
try {
    Void result = apiInstance.v10WalletTransferPost(authorization, from, to, amount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletTransferPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **from** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **to** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **amount** | **Double**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10WalletWithdrawInfoGet"></a>
# **v10WalletWithdrawInfoGet**
> WithdrawalSummary v10WalletWithdrawInfoGet(authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    WithdrawalSummary result = apiInstance.v10WalletWithdrawInfoGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#v10WalletWithdrawInfoGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**WithdrawalSummary**](WithdrawalSummary.md)

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

