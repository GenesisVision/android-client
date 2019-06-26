# PlatformApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10PlatformDatePost**](PlatformApi.md#v10PlatformDatePost) | **POST** v1.0/platform/date | Server date
[**v10PlatformInfoGet**](PlatformApi.md#v10PlatformInfoGet) | **GET** v1.0/platform/info | Platform info
[**v10PlatformLevelsGet**](PlatformApi.md#v10PlatformLevelsGet) | **GET** v1.0/platform/levels | Investment programs levels
[**v10PlatformRiskcontrolGet**](PlatformApi.md#v10PlatformRiskcontrolGet) | **GET** v1.0/platform/riskcontrol | Platform captcha details.
[**v10PlatformStatisticGet**](PlatformApi.md#v10PlatformStatisticGet) | **GET** v1.0/platform/statistic | Platform statistic


<a name="v10PlatformDatePost"></a>
# **v10PlatformDatePost**
> String v10PlatformDatePost()

Server date

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PlatformApi;


PlatformApi apiInstance = new PlatformApi();
try {
    String result = apiInstance.v10PlatformDatePost();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#v10PlatformDatePost");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10PlatformInfoGet"></a>
# **v10PlatformInfoGet**
> PlatformInfo v10PlatformInfoGet()

Platform info

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PlatformApi;


PlatformApi apiInstance = new PlatformApi();
try {
    PlatformInfo result = apiInstance.v10PlatformInfoGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#v10PlatformInfoGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**PlatformInfo**](PlatformInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10PlatformLevelsGet"></a>
# **v10PlatformLevelsGet**
> ProgramsLevelsInfo v10PlatformLevelsGet(currency)

Investment programs levels

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PlatformApi;


PlatformApi apiInstance = new PlatformApi();
String currency = "100"; // String | 
try {
    ProgramsLevelsInfo result = apiInstance.v10PlatformLevelsGet(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#v10PlatformLevelsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | [optional] [default to 100] [enum: BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, USD, EUR]

### Return type

[**ProgramsLevelsInfo**](ProgramsLevelsInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10PlatformRiskcontrolGet"></a>
# **v10PlatformRiskcontrolGet**
> CaptchaDetails v10PlatformRiskcontrolGet(route, client, version)

Platform captcha details.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PlatformApi;


PlatformApi apiInstance = new PlatformApi();
String route = "route_example"; // String | 
String client = "client_example"; // String | 
String version = "version_example"; // String | 
try {
    CaptchaDetails result = apiInstance.v10PlatformRiskcontrolGet(route, client, version);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#v10PlatformRiskcontrolGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **route** | **String**|  |
 **client** | **String**|  | [optional]
 **version** | **String**|  | [optional]

### Return type

[**CaptchaDetails**](CaptchaDetails.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10PlatformStatisticGet"></a>
# **v10PlatformStatisticGet**
> PlatformStatistic v10PlatformStatisticGet()

Platform statistic

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PlatformApi;


PlatformApi apiInstance = new PlatformApi();
try {
    PlatformStatistic result = apiInstance.v10PlatformStatisticGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#v10PlatformStatisticGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**PlatformStatistic**](PlatformStatistic.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

