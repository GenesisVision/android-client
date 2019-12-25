# PlatformApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAllPlatformAssets**](PlatformApi.md#getAllPlatformAssets) | **GET** v2.0/platform/assets | Get all supported assets for funds
[**getPlatformAssetInfo**](PlatformApi.md#getPlatformAssetInfo) | **GET** v2.0/platform/asset/{asset} | Get asset description
[**getPlatformDate**](PlatformApi.md#getPlatformDate) | **POST** v2.0/platform/date | Server date
[**getPlatformInfo**](PlatformApi.md#getPlatformInfo) | **GET** v2.0/platform/info | Platform info
[**getProgramLevels**](PlatformApi.md#getProgramLevels) | **GET** v2.0/platform/levels | Investment programs levels
[**getProgramLevelsParams**](PlatformApi.md#getProgramLevelsParams) | **GET** v2.0/platform/levels/parameters | Investment programs levels parameters
[**getRiskControlInfo**](PlatformApi.md#getRiskControlInfo) | **GET** v2.0/platform/riskcontrol | Risk control

<a name="getAllPlatformAssets"></a>
# **getAllPlatformAssets**
> PlatformAssets getAllPlatformAssets()

Get all supported assets for funds

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PlatformApi;


PlatformApi apiInstance = new PlatformApi();
try {
    PlatformAssets result = apiInstance.getAllPlatformAssets();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#getAllPlatformAssets");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**PlatformAssets**](PlatformAssets.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getPlatformAssetInfo"></a>
# **getPlatformAssetInfo**
> AssetInfo getPlatformAssetInfo(asset)

Get asset description

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PlatformApi;


PlatformApi apiInstance = new PlatformApi();
String asset = "asset_example"; // String | 
try {
    AssetInfo result = apiInstance.getPlatformAssetInfo(asset);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#getPlatformAssetInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **asset** | **String**|  |

### Return type

[**AssetInfo**](AssetInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getPlatformDate"></a>
# **getPlatformDate**
> String getPlatformDate()

Server date

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PlatformApi;


PlatformApi apiInstance = new PlatformApi();
try {
    String result = apiInstance.getPlatformDate();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#getPlatformDate");
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

<a name="getPlatformInfo"></a>
# **getPlatformInfo**
> PlatformInfo getPlatformInfo()

Platform info

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PlatformApi;


PlatformApi apiInstance = new PlatformApi();
try {
    PlatformInfo result = apiInstance.getPlatformInfo();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#getPlatformInfo");
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

<a name="getProgramLevels"></a>
# **getProgramLevels**
> ProgramsLevelsInfo getProgramLevels(currency)

Investment programs levels

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PlatformApi;


PlatformApi apiInstance = new PlatformApi();
String currency = "currency_example"; // String | 
try {
    ProgramsLevelsInfo result = apiInstance.getProgramLevels(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#getProgramLevels");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**ProgramsLevelsInfo**](ProgramsLevelsInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramLevelsParams"></a>
# **getProgramLevelsParams**
> LevelsParamsInfo getProgramLevelsParams(currency)

Investment programs levels parameters

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PlatformApi;


PlatformApi apiInstance = new PlatformApi();
String currency = "currency_example"; // String | 
try {
    LevelsParamsInfo result = apiInstance.getProgramLevelsParams(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#getProgramLevelsParams");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**LevelsParamsInfo**](LevelsParamsInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getRiskControlInfo"></a>
# **getRiskControlInfo**
> CaptchaDetails getRiskControlInfo(route, client, version)

Risk control

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
    CaptchaDetails result = apiInstance.getRiskControlInfo(route, client, version);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlatformApi#getRiskControlInfo");
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

