# BrokerApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiBrokerAccountCreatePost**](BrokerApi.md#apiBrokerAccountCreatePost) | **POST** api/broker/account/create | Create manager
[**apiBrokerAuthSignInPost**](BrokerApi.md#apiBrokerAuthSignInPost) | **POST** api/broker/auth/signIn | Authorize
[**apiBrokerAuthUpdateTokenGet**](BrokerApi.md#apiBrokerAuthUpdateTokenGet) | **GET** api/broker/auth/updateToken | Update auth token
[**apiBrokerInitDataGet**](BrokerApi.md#apiBrokerInitDataGet) | **GET** api/broker/initData | Get broker initial data
[**apiBrokerPeriodCloseGet**](BrokerApi.md#apiBrokerPeriodCloseGet) | **GET** api/broker/period/close | Close investment period
[**apiBrokerPeriodSetStartBalanceGet**](BrokerApi.md#apiBrokerPeriodSetStartBalanceGet) | **GET** api/broker/period/setStartBalance | Set investment period start balance
[**apiBrokerPeriodlosingDataGet**](BrokerApi.md#apiBrokerPeriodlosingDataGet) | **GET** api/broker/period/сlosingData | Get data for closing investment period


<a name="apiBrokerAccountCreatePost"></a>
# **apiBrokerAccountCreatePost**
> UUID apiBrokerAccountCreatePost(authorization, request)

Create manager

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
String authorization = "authorization_example"; // String | JWT access token
NewManager request = new NewManager(); // NewManager | 
try {
    UUID result = apiInstance.apiBrokerAccountCreatePost(authorization, request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerAccountCreatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **request** | [**NewManager**](NewManager.md)|  | [optional]

### Return type

[**UUID**](UUID.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerAuthSignInPost"></a>
# **apiBrokerAuthSignInPost**
> String apiBrokerAuthSignInPost(model)

Authorize

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
LoginViewModel model = new LoginViewModel(); // LoginViewModel | 
try {
    String result = apiInstance.apiBrokerAuthSignInPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerAuthSignInPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**LoginViewModel**](LoginViewModel.md)|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerAuthUpdateTokenGet"></a>
# **apiBrokerAuthUpdateTokenGet**
> String apiBrokerAuthUpdateTokenGet(authorization)

Update auth token

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    String result = apiInstance.apiBrokerAuthUpdateTokenGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerAuthUpdateTokenGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerInitDataGet"></a>
# **apiBrokerInitDataGet**
> BrokerInitData apiBrokerInitDataGet(brokerTradeServerId, authorization)

Get broker initial data

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID brokerTradeServerId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    BrokerInitData result = apiInstance.apiBrokerInitDataGet(brokerTradeServerId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerInitDataGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **brokerTradeServerId** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**BrokerInitData**](BrokerInitData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerPeriodCloseGet"></a>
# **apiBrokerPeriodCloseGet**
> Void apiBrokerPeriodCloseGet(investmentProgramId, authorization)

Close investment period

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID investmentProgramId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.apiBrokerPeriodCloseGet(investmentProgramId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodCloseGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **investmentProgramId** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerPeriodSetStartBalanceGet"></a>
# **apiBrokerPeriodSetStartBalanceGet**
> Void apiBrokerPeriodSetStartBalanceGet(periodId, balance, authorization)

Set investment period start balance

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID periodId = new UUID(); // UUID | 
Double balance = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.apiBrokerPeriodSetStartBalanceGet(periodId, balance, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodSetStartBalanceGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **periodId** | [**UUID**](.md)|  |
 **balance** | **Double**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerPeriodlosingDataGet"></a>
# **apiBrokerPeriodlosingDataGet**
> ClosePeriodData apiBrokerPeriodlosingDataGet(investmentProgramId, authorization)

Get data for closing investment period

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID investmentProgramId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ClosePeriodData result = apiInstance.apiBrokerPeriodlosingDataGet(investmentProgramId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodlosingDataGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **investmentProgramId** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**ClosePeriodData**](ClosePeriodData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

