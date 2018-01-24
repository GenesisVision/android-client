# WalletApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiInvestorWalletTransactionsPost**](WalletApi.md#apiInvestorWalletTransactionsPost) | **POST** api/investor/wallet/transactions | 
[**apiManagerWalletTransactionsPost**](WalletApi.md#apiManagerWalletTransactionsPost) | **POST** api/manager/wallet/transactions | 


<a name="apiInvestorWalletTransactionsPost"></a>
# **apiInvestorWalletTransactionsPost**
> WalletTransactionsViewModel apiInvestorWalletTransactionsPost(filter)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
TransactionsFilter filter = new TransactionsFilter(); // TransactionsFilter | 
try {
    WalletTransactionsViewModel result = apiInstance.apiInvestorWalletTransactionsPost(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#apiInvestorWalletTransactionsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**TransactionsFilter**](TransactionsFilter.md)|  | [optional]

### Return type

[**WalletTransactionsViewModel**](WalletTransactionsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiManagerWalletTransactionsPost"></a>
# **apiManagerWalletTransactionsPost**
> WalletTransactionsViewModel apiManagerWalletTransactionsPost(filter)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.WalletApi;


WalletApi apiInstance = new WalletApi();
TransactionsFilter filter = new TransactionsFilter(); // TransactionsFilter | 
try {
    WalletTransactionsViewModel result = apiInstance.apiManagerWalletTransactionsPost(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#apiManagerWalletTransactionsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**TransactionsFilter**](TransactionsFilter.md)|  | [optional]

### Return type

[**WalletTransactionsViewModel**](WalletTransactionsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

