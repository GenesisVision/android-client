# WalletApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelWithdrawalRequest**](WalletApi.md#cancelWithdrawalRequest) | **POST** v2.0/wallet/withdraw/request/cancel/{txId} | 
[**confirmWithdrawalRequestByCode**](WalletApi.md#confirmWithdrawalRequestByCode) | **POST** v2.0/wallet/withdraw/request/confirm | 
[**createWithdrawalRequest**](WalletApi.md#createWithdrawalRequest) | **POST** v2.0/wallet/withdraw/request/new | 
[**getGMCommissionData**](WalletApi.md#getGMCommissionData) | **GET** v2.0/wallet/fee/gvtholding | GenesisMarkets commission data
[**getTransactionsExternal**](WalletApi.md#getTransactionsExternal) | **GET** v2.0/wallet/transactions/external | External transactions
[**getTransactionsInternal**](WalletApi.md#getTransactionsInternal) | **GET** v2.0/wallet/transactions/internal | Internal transactions
[**getUserWithdrawalSummary**](WalletApi.md#getUserWithdrawalSummary) | **GET** v2.0/wallet/withdraw/info | 
[**getWalletAvailable**](WalletApi.md#getWalletAvailable) | **GET** v2.0/wallet/{currency}/available | Wallet available
[**getWalletSummary**](WalletApi.md#getWalletSummary) | **GET** v2.0/wallet/{currency} | Wallet summary
[**resendWithdrawalRequestEmail**](WalletApi.md#resendWithdrawalRequestEmail) | **POST** v2.0/wallet/withdraw/request/resend/{txId} | 
[**transfer**](WalletApi.md#transfer) | **POST** v2.0/wallet/transfer | Transfer money

<a name="cancelWithdrawalRequest"></a>
# **cancelWithdrawalRequest**
> Void cancelWithdrawalRequest(txId)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
UUID txId = new UUID(); // UUID | 
try {
    Void result = apiInstance.cancelWithdrawalRequest(txId);
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

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="confirmWithdrawalRequestByCode"></a>
# **confirmWithdrawalRequestByCode**
> Void confirmWithdrawalRequestByCode(requestId, code)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

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

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="createWithdrawalRequest"></a>
# **createWithdrawalRequest**
> Void createWithdrawalRequest(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
CreateWithdrawalRequestModel body = new CreateWithdrawalRequestModel(); // CreateWithdrawalRequestModel | 
try {
    Void result = apiInstance.createWithdrawalRequest(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#createWithdrawalRequest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateWithdrawalRequestModel**](CreateWithdrawalRequestModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="getGMCommissionData"></a>
# **getGMCommissionData**
> UserCommissionData getGMCommissionData()

GenesisMarkets commission data

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
try {
    UserCommissionData result = apiInstance.getGMCommissionData();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getGMCommissionData");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**UserCommissionData**](UserCommissionData.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getTransactionsExternal"></a>
# **getTransactionsExternal**
> TransactionViewModelItemsViewModel getTransactionsExternal(transactionType, dateFrom, dateTo, currency, skip, take)

External transactions

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
TransactionExternalType transactionType = new TransactionExternalType(); // TransactionExternalType | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Currency currency = new Currency(); // Currency | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TransactionViewModelItemsViewModel result = apiInstance.getTransactionsExternal(transactionType, dateFrom, dateTo, currency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getTransactionsExternal");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **transactionType** | [**TransactionExternalType**](.md)|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **currency** | [**Currency**](.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TransactionViewModelItemsViewModel**](TransactionViewModelItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getTransactionsInternal"></a>
# **getTransactionsInternal**
> TransactionViewModelItemsViewModel getTransactionsInternal(transactionType, dateFrom, dateTo, currency, skip, take)

Internal transactions

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
TransactionInternalType transactionType = new TransactionInternalType(); // TransactionInternalType | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Currency currency = new Currency(); // Currency | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TransactionViewModelItemsViewModel result = apiInstance.getTransactionsInternal(transactionType, dateFrom, dateTo, currency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getTransactionsInternal");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **transactionType** | [**TransactionInternalType**](.md)|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **currency** | [**Currency**](.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TransactionViewModelItemsViewModel**](TransactionViewModelItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getUserWithdrawalSummary"></a>
# **getUserWithdrawalSummary**
> WithdrawalSummary getUserWithdrawalSummary()



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
try {
    WithdrawalSummary result = apiInstance.getUserWithdrawalSummary();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getUserWithdrawalSummary");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**WithdrawalSummary**](WithdrawalSummary.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getWalletAvailable"></a>
# **getWalletAvailable**
> WalletMultiAvailable getWalletAvailable(currency)

Wallet available

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
Currency currency = new Currency(); // Currency | 
try {
    WalletMultiAvailable result = apiInstance.getWalletAvailable(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getWalletAvailable");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | [**Currency**](.md)|  |

### Return type

[**WalletMultiAvailable**](WalletMultiAvailable.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getWalletSummary"></a>
# **getWalletSummary**
> WalletSummary getWalletSummary(currency)

Wallet summary

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
Currency currency = new Currency(); // Currency | 
try {
    WalletSummary result = apiInstance.getWalletSummary(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getWalletSummary");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | [**Currency**](.md)|  |

### Return type

[**WalletSummary**](WalletSummary.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="resendWithdrawalRequestEmail"></a>
# **resendWithdrawalRequestEmail**
> Void resendWithdrawalRequestEmail(txId)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
UUID txId = new UUID(); // UUID | 
try {
    Void result = apiInstance.resendWithdrawalRequestEmail(txId);
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

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="transfer"></a>
# **transfer**
> Void transfer(body)

Transfer money

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
InternalTransferRequest body = new InternalTransferRequest(); // InternalTransferRequest | 
try {
    Void result = apiInstance.transfer(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#transfer");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**InternalTransferRequest**](InternalTransferRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

