# RateApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getRate**](RateApi.md#getRate) | **GET** v2.0/rate/{from}/{to} | Get rate
[**getRateExchange**](RateApi.md#getRateExchange) | **GET** v2.0/rate/{exchange}/{from}/{to} | Get rate by exchange
[**getRates**](RateApi.md#getRates) | **GET** v2.0/rate | Get rates

<a name="getRate"></a>
# **getRate**
> RateModel getRate(from, to)

Get rate

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.RateApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

RateApi apiInstance = new RateApi();
String from = "from_example"; // String | 
String to = "to_example"; // String | 
try {
    RateModel result = apiInstance.getRate(from, to);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RateApi#getRate");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **from** | **String**|  |
 **to** | **String**|  |

### Return type

[**RateModel**](RateModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getRateExchange"></a>
# **getRateExchange**
> RateModel getRateExchange(exchange, from, to)

Get rate by exchange

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.RateApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

RateApi apiInstance = new RateApi();
String exchange = "exchange_example"; // String | 
String from = "from_example"; // String | 
String to = "to_example"; // String | 
try {
    RateModel result = apiInstance.getRateExchange(exchange, from, to);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RateApi#getRateExchange");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **exchange** | **String**|  |
 **from** | **String**|  |
 **to** | **String**|  |

### Return type

[**RateModel**](RateModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getRates"></a>
# **getRates**
> RatesModel getRates(from, to)

Get rates

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.RateApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

RateApi apiInstance = new RateApi();
List<String> from = Arrays.asList("from_example"); // List<String> | 
List<String> to = Arrays.asList("to_example"); // List<String> | 
try {
    RatesModel result = apiInstance.getRates(from, to);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RateApi#getRates");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **from** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **to** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**RatesModel**](RatesModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

