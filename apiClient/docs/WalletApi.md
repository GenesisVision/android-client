# WalletApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10WalletByCurrencyGet**](WalletApi.md#v10WalletByCurrencyGet) | **GET** v1.0/wallet/{currency} | Wallet summary
[**v10WalletTransactionsGet**](WalletApi.md#v10WalletTransactionsGet) | **GET** v1.0/wallet/transactions | Wallet transactions


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
> WalletSummary v10WalletTransactionsGet(authorization, assetId, from, to, assetType, txAction, skip, take)

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
    WalletSummary result = apiInstance.v10WalletTransactionsGet(authorization, assetId, from, to, assetType, txAction, skip, take);
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

[**WalletSummary**](WalletSummary.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

