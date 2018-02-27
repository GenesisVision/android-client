# BrokerApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiBrokerAccountCreatePost**](BrokerApi.md#apiBrokerAccountCreatePost) | **POST** api/broker/account/create | Create manager
[**apiBrokerAuthSignInPost**](BrokerApi.md#apiBrokerAuthSignInPost) | **POST** api/broker/auth/signIn | Authorize
[**apiBrokerAuthUpdateTokenGet**](BrokerApi.md#apiBrokerAuthUpdateTokenGet) | **GET** api/broker/auth/updateToken | Update auth token
[**apiBrokerInitDataGet**](BrokerApi.md#apiBrokerInitDataGet) | **GET** api/broker/initData | Get broker initial data
[**apiBrokerPeriodAccrueProfitsPost**](BrokerApi.md#apiBrokerPeriodAccrueProfitsPost) | **POST** api/broker/period/accrueProfits | Accrue investors&#39; profits
[**apiBrokerPeriodClosePost**](BrokerApi.md#apiBrokerPeriodClosePost) | **POST** api/broker/period/close | Close investment period
[**apiBrokerPeriodProcessInvestmentRequestsPost**](BrokerApi.md#apiBrokerPeriodProcessInvestmentRequestsPost) | **POST** api/broker/period/processInvestmentRequests | Process investment requests
[**apiBrokerPeriodSetStartBalancePost**](BrokerApi.md#apiBrokerPeriodSetStartBalancePost) | **POST** api/broker/period/setStartBalance | Set investment period start balance
[**apiBrokerPeriodlosingDataGet**](BrokerApi.md#apiBrokerPeriodlosingDataGet) | **GET** api/broker/period/сlosingData | Get data for closing investment period
[**apiBrokerTradesNewPost**](BrokerApi.md#apiBrokerTradesNewPost) | **POST** api/broker/trades/new | New trade event
[**apiBrokerTradesOpenTradesNewPost**](BrokerApi.md#apiBrokerTradesOpenTradesNewPost) | **POST** api/broker/trades/openTrades/new | New open trades event
[**apiBrokerTradesReevaluateManagerTokenPost**](BrokerApi.md#apiBrokerTradesReevaluateManagerTokenPost) | **POST** api/broker/trades/reevaluateManagerToken | Update manager token initial price/total supply after loss


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

<a name="apiBrokerPeriodAccrueProfitsPost"></a>
# **apiBrokerPeriodAccrueProfitsPost**
> UUID apiBrokerPeriodAccrueProfitsPost(authorization, accrual)

Accrue investors&#39; profits

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
String authorization = "authorization_example"; // String | JWT access token
InvestmentProgramAccrual accrual = new InvestmentProgramAccrual(); // InvestmentProgramAccrual | 
try {
    UUID result = apiInstance.apiBrokerPeriodAccrueProfitsPost(authorization, accrual);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodAccrueProfitsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **accrual** | [**InvestmentProgramAccrual**](InvestmentProgramAccrual.md)|  | [optional]

### Return type

[**UUID**](UUID.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerPeriodClosePost"></a>
# **apiBrokerPeriodClosePost**
> Void apiBrokerPeriodClosePost(investmentProgramId, authorization)

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
    Void result = apiInstance.apiBrokerPeriodClosePost(investmentProgramId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodClosePost");
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

<a name="apiBrokerPeriodProcessInvestmentRequestsPost"></a>
# **apiBrokerPeriodProcessInvestmentRequestsPost**
> UUID apiBrokerPeriodProcessInvestmentRequestsPost(investmentProgramId, authorization)

Process investment requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID investmentProgramId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    UUID result = apiInstance.apiBrokerPeriodProcessInvestmentRequestsPost(investmentProgramId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodProcessInvestmentRequestsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **investmentProgramId** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**UUID**](UUID.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerPeriodSetStartBalancePost"></a>
# **apiBrokerPeriodSetStartBalancePost**
> Void apiBrokerPeriodSetStartBalancePost(periodId, balance, authorization)

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
    Void result = apiInstance.apiBrokerPeriodSetStartBalancePost(periodId, balance, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodSetStartBalancePost");
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

<a name="apiBrokerTradesNewPost"></a>
# **apiBrokerTradesNewPost**
> Void apiBrokerTradesNewPost(authorization, tradeEvent)

New trade event

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
String authorization = "authorization_example"; // String | JWT access token
NewTradeEvent tradeEvent = new NewTradeEvent(); // NewTradeEvent | 
try {
    Void result = apiInstance.apiBrokerTradesNewPost(authorization, tradeEvent);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerTradesNewPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **tradeEvent** | [**NewTradeEvent**](NewTradeEvent.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerTradesOpenTradesNewPost"></a>
# **apiBrokerTradesOpenTradesNewPost**
> Void apiBrokerTradesOpenTradesNewPost(authorization, trades)

New open trades event

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
String authorization = "authorization_example"; // String | JWT access token
NewOpenTradesEvent trades = new NewOpenTradesEvent(); // NewOpenTradesEvent | 
try {
    Void result = apiInstance.apiBrokerTradesOpenTradesNewPost(authorization, trades);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerTradesOpenTradesNewPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **trades** | [**NewOpenTradesEvent**](NewOpenTradesEvent.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerTradesReevaluateManagerTokenPost"></a>
# **apiBrokerTradesReevaluateManagerTokenPost**
> Void apiBrokerTradesReevaluateManagerTokenPost(investmentProgramId, investorLossShare, authorization)

Update manager token initial price/total supply after loss

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID investmentProgramId = new UUID(); // UUID | 
Double investorLossShare = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.apiBrokerTradesReevaluateManagerTokenPost(investmentProgramId, investorLossShare, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerTradesReevaluateManagerTokenPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **investmentProgramId** | [**UUID**](.md)|  |
 **investorLossShare** | **Double**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

