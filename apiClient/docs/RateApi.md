# RateApi

All URIs are relative to *https://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10RateByExchangeByFromByToGet**](RateApi.md#v10RateByExchangeByFromByToGet) | **GET** v1.0/rate/{exchange}/{from}/{to} | Get rate
[**v10RateByFromByToGet**](RateApi.md#v10RateByFromByToGet) | **GET** v1.0/rate/{from}/{to} | Get rate
[**v10RateGet**](RateApi.md#v10RateGet) | **GET** v1.0/rate | Get rates


<a name="v10RateByExchangeByFromByToGet"></a>
# **v10RateByExchangeByFromByToGet**
> Double v10RateByExchangeByFromByToGet(exchange, from, to)

Get rate

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.RateApi;


RateApi apiInstance = new RateApi();
String exchange = "exchange_example"; // String | 
String from = "from_example"; // String | 
String to = "to_example"; // String | 
try {
    Double result = apiInstance.v10RateByExchangeByFromByToGet(exchange, from, to);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RateApi#v10RateByExchangeByFromByToGet");
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

**Double**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10RateByFromByToGet"></a>
# **v10RateByFromByToGet**
> Double v10RateByFromByToGet(from, to)

Get rate

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.RateApi;


RateApi apiInstance = new RateApi();
String from = "from_example"; // String | 
String to = "to_example"; // String | 
try {
    Double result = apiInstance.v10RateByFromByToGet(from, to);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RateApi#v10RateByFromByToGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **from** | **String**|  |
 **to** | **String**|  |

### Return type

**Double**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10RateGet"></a>
# **v10RateGet**
> RatesModel v10RateGet(from, to)

Get rates

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.RateApi;


RateApi apiInstance = new RateApi();
List<String> from = Arrays.asList("from_example"); // List<String> | 
List<String> to = Arrays.asList("to_example"); // List<String> | 
try {
    RatesModel result = apiInstance.v10RateGet(from, to);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RateApi#v10RateGet");
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

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

