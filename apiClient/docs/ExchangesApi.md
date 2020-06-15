# ExchangesApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getExchanges**](ExchangesApi.md#getExchanges) | **GET** v2.0/exchanges | Get exchanges for creating trading accounts

<a name="getExchanges"></a>
# **getExchanges**
> ExchangeInfoItemsViewModel getExchanges()

Get exchanges for creating trading accounts

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ExchangesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ExchangesApi apiInstance = new ExchangesApi();
try {
    ExchangeInfoItemsViewModel result = apiInstance.getExchanges();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ExchangesApi#getExchanges");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ExchangeInfoItemsViewModel**](ExchangeInfoItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

