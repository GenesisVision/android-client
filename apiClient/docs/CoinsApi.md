# CoinsApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getCoins**](CoinsApi.md#getCoins) | **GET** v2.0/coins | Coins list
[**getCoinsConvertingHistory**](CoinsApi.md#getCoinsConvertingHistory) | **GET** v2.0/coins/history | Get coins history
[**getUserCoins**](CoinsApi.md#getUserCoins) | **GET** v2.0/coins/portfolio | Get user coins
[**transfer**](CoinsApi.md#transfer) | **POST** v2.0/coins/transfer | Transfer money

<a name="getCoins"></a>
# **getCoins**
> CoinsAssetItemsViewModel getCoins(sorting, assets, skip, take)

Coins list

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.CoinsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

CoinsApi apiInstance = new CoinsApi();
CoinsFilterSorting sorting = new CoinsFilterSorting(); // CoinsFilterSorting | 
List<String> assets = Arrays.asList("assets_example"); // List<String> | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    CoinsAssetItemsViewModel result = apiInstance.getCoins(sorting, assets, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CoinsApi#getCoins");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sorting** | [**CoinsFilterSorting**](.md)|  | [optional]
 **assets** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**CoinsAssetItemsViewModel**](CoinsAssetItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getCoinsConvertingHistory"></a>
# **getCoinsConvertingHistory**
> CoinsHistoryEventItemsViewModel getCoinsConvertingHistory(dateFrom, dateTo, assets, skip, take)

Get coins history

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.CoinsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

CoinsApi apiInstance = new CoinsApi();
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
List<String> assets = Arrays.asList("assets_example"); // List<String> | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    CoinsHistoryEventItemsViewModel result = apiInstance.getCoinsConvertingHistory(dateFrom, dateTo, assets, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CoinsApi#getCoinsConvertingHistory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **assets** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**CoinsHistoryEventItemsViewModel**](CoinsHistoryEventItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getUserCoins"></a>
# **getUserCoins**
> CoinsAssetItemsViewModel getUserCoins(sorting, assets, skip, take)

Get user coins

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.CoinsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

CoinsApi apiInstance = new CoinsApi();
CoinsFilterSorting sorting = new CoinsFilterSorting(); // CoinsFilterSorting | 
List<String> assets = Arrays.asList("assets_example"); // List<String> | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    CoinsAssetItemsViewModel result = apiInstance.getUserCoins(sorting, assets, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CoinsApi#getUserCoins");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sorting** | [**CoinsFilterSorting**](.md)|  | [optional]
 **assets** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**CoinsAssetItemsViewModel**](CoinsAssetItemsViewModel.md)

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
//import io.swagger.client.api.CoinsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

CoinsApi apiInstance = new CoinsApi();
InternalTransferRequest body = new InternalTransferRequest(); // InternalTransferRequest | 
try {
    Void result = apiInstance.transfer(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CoinsApi#transfer");
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

