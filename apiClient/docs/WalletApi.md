# WalletApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelWithdrawalRequest**](WalletApi.md#cancelWithdrawalRequest) | **POST** v2.0/wallet/withdraw/request/cancel/{txId} | 
[**confirmWithdrawalRequestByCode**](WalletApi.md#confirmWithdrawalRequestByCode) | **POST** v2.0/wallet/withdraw/request/confirm | 
[**createWithdrawalRequest**](WalletApi.md#createWithdrawalRequest) | **POST** v2.0/wallet/withdraw/request/new | 
[**getAccountsAvailable**](WalletApi.md#getAccountsAvailable) | **GET** v2.0/wallet/accounts/{currency}/available | Accounts available
[**getGMCommissionData**](WalletApi.md#getGMCommissionData) | **GET** v2.0/wallet/fee/gvtholding | GenesisMarkets commission data
[**getTransactionsExternal**](WalletApi.md#getTransactionsExternal) | **GET** v2.0/wallet/transactions/external | External transactions
[**getTransactionsInternal**](WalletApi.md#getTransactionsInternal) | **GET** v2.0/wallet/transactions/internal | Internal transactions
[**getUserWithdrawalSummary**](WalletApi.md#getUserWithdrawalSummary) | **GET** v2.0/wallet/withdraw/info | 
[**getWalletAvailable**](WalletApi.md#getWalletAvailable) | **GET** v2.0/wallet/{currency}/available | Wallet available
[**getWalletSummary**](WalletApi.md#getWalletSummary) | **GET** v2.0/wallet/{currency} | Wallet summary
[**resendWithdrawalRequestEmail**](WalletApi.md#resendWithdrawalRequestEmail) | **POST** v2.0/wallet/withdraw/request/resend/{txId} | 
[**switchPayFeeInGvtOff**](WalletApi.md#switchPayFeeInGvtOff) | **POST** v2.0/wallet/paygvtfee/off | Disable paying platform fees with GVT
[**switchPayFeeInGvtOn**](WalletApi.md#switchPayFeeInGvtOn) | **POST** v2.0/wallet/paygvtfee/on | Enable paying platform fees with GVT
[**transfer**](WalletApi.md#transfer) | **POST** v2.0/wallet/transfer | Transfer money
[**updateDepositWallets**](WalletApi.md#updateDepositWallets) | **POST** v2.0/wallet/deposit/update | Update deposit wallets

<a name="cancelWithdrawalRequest"></a>
# **cancelWithdrawalRequest**
> Void cancelWithdrawalRequest(txId, authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
UUID txId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.cancelWithdrawalRequest(txId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#cancelWithdrawalRequest");
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

<a name="confirmWithdrawalRequestByCode"></a>
# **confirmWithdrawalRequestByCode**
> Void confirmWithdrawalRequestByCode(requestId, code)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
UUID requestId = new UUID(); // UUID | 
String code = "code_example"; // String | 
try {
    Void result = apiInstance.confirmWithdrawalRequestByCode(requestId, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#confirmWithdrawalRequestByCode");
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

<a name="createWithdrawalRequest"></a>
# **createWithdrawalRequest**
> Void createWithdrawalRequest(authorization, body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
CreateWithdrawalRequestModel body = new CreateWithdrawalRequestModel(); // CreateWithdrawalRequestModel | 
try {
    Void result = apiInstance.createWithdrawalRequest(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#createWithdrawalRequest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**CreateWithdrawalRequestModel**](CreateWithdrawalRequestModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="getAccountsAvailable"></a>
# **getAccountsAvailable**
> WalletMultiAvailable getAccountsAvailable(currency, authorization)

Accounts available

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    WalletMultiAvailable result = apiInstance.getAccountsAvailable(currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getAccountsAvailable");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**WalletMultiAvailable**](WalletMultiAvailable.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getGMCommissionData"></a>
# **getGMCommissionData**
> UserCommissionData getGMCommissionData(authorization)

GenesisMarkets commission data

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    UserCommissionData result = apiInstance.getGMCommissionData(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getGMCommissionData");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**UserCommissionData**](UserCommissionData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getTransactionsExternal"></a>
# **getTransactionsExternal**
> ItemsViewModelTransactionViewModel getTransactionsExternal(authorization, transactionType, dateFrom, dateTo, currency, skip, take)

External transactions

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
String transactionType = "transactionType_example"; // String | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String currency = "currency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ItemsViewModelTransactionViewModel result = apiInstance.getTransactionsExternal(authorization, transactionType, dateFrom, dateTo, currency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getTransactionsExternal");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **transactionType** | **String**|  | [optional] [enum: All, Withdrawal, Deposit, Platform]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelTransactionViewModel**](ItemsViewModelTransactionViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getTransactionsInternal"></a>
# **getTransactionsInternal**
> ItemsViewModelTransactionViewModel getTransactionsInternal(authorization, transactionType, dateFrom, dateTo, currency, skip, take)

Internal transactions

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
String transactionType = "transactionType_example"; // String | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String currency = "currency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ItemsViewModelTransactionViewModel result = apiInstance.getTransactionsInternal(authorization, transactionType, dateFrom, dateTo, currency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getTransactionsInternal");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **transactionType** | **String**|  | [optional] [enum: All, Investment, Withdrawal, Conversion, Commission, Program, Fund, Follow, TradingAccounts, AgentReward]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelTransactionViewModel**](ItemsViewModelTransactionViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getUserWithdrawalSummary"></a>
# **getUserWithdrawalSummary**
> WithdrawalSummary getUserWithdrawalSummary(authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    WithdrawalSummary result = apiInstance.getUserWithdrawalSummary(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getUserWithdrawalSummary");
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

<a name="getWalletAvailable"></a>
# **getWalletAvailable**
> WalletMultiAvailable getWalletAvailable(currency, authorization)

Wallet available

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    WalletMultiAvailable result = apiInstance.getWalletAvailable(currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getWalletAvailable");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**WalletMultiAvailable**](WalletMultiAvailable.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getWalletSummary"></a>
# **getWalletSummary**
> WalletSummary getWalletSummary(currency, authorization)

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
    WalletSummary result = apiInstance.getWalletSummary(currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getWalletSummary");
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

<a name="resendWithdrawalRequestEmail"></a>
# **resendWithdrawalRequestEmail**
> Void resendWithdrawalRequestEmail(txId, authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
UUID txId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.resendWithdrawalRequestEmail(txId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#resendWithdrawalRequestEmail");
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

<a name="switchPayFeeInGvtOff"></a>
# **switchPayFeeInGvtOff**
> Void switchPayFeeInGvtOff(authorization)

Disable paying platform fees with GVT

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.switchPayFeeInGvtOff(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#switchPayFeeInGvtOff");
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

<a name="switchPayFeeInGvtOn"></a>
# **switchPayFeeInGvtOn**
> Void switchPayFeeInGvtOn(authorization)

Enable paying platform fees with GVT

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.switchPayFeeInGvtOn(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#switchPayFeeInGvtOn");
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

<a name="transfer"></a>
# **transfer**
> Void transfer(authorization, body)

Transfer money

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
InternalTransferRequest body = new InternalTransferRequest(); // InternalTransferRequest | 
try {
    Void result = apiInstance.transfer(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#transfer");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**InternalTransferRequest**](InternalTransferRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateDepositWallets"></a>
# **updateDepositWallets**
> WalletDepositSummary updateDepositWallets(authorization)

Update deposit wallets

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    WalletDepositSummary result = apiInstance.updateDepositWallets(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#updateDepositWallets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**WalletDepositSummary**](WalletDepositSummary.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

