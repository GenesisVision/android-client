# BrokerApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiBrokerAccountCreatePost**](BrokerApi.md#apiBrokerAccountCreatePost) | **POST** api/broker/account/create | Create manager
[**apiBrokerAuthConfirmEmailPost**](BrokerApi.md#apiBrokerAuthConfirmEmailPost) | **POST** api/broker/auth/confirmEmail | Confirm email after registration
[**apiBrokerAuthResetPasswordPost**](BrokerApi.md#apiBrokerAuthResetPasswordPost) | **POST** api/broker/auth/resetPassword | Reset password
[**apiBrokerAuthSignInPost**](BrokerApi.md#apiBrokerAuthSignInPost) | **POST** api/broker/auth/signIn | Authorize
[**apiBrokerAuthUpdateTokenGet**](BrokerApi.md#apiBrokerAuthUpdateTokenGet) | **GET** api/broker/auth/updateToken | Update auth token
[**apiBrokerAuthhangePasswordPost**](BrokerApi.md#apiBrokerAuthhangePasswordPost) | **POST** api/broker/auth/сhangePassword | Change password
[**apiBrokerInitDataGet**](BrokerApi.md#apiBrokerInitDataGet) | **GET** api/broker/initData | Get broker initial data
[**apiBrokerPeriodAccrueProfitsPost**](BrokerApi.md#apiBrokerPeriodAccrueProfitsPost) | **POST** api/broker/period/accrueProfits | Accrue investors&#39; profits
[**apiBrokerPeriodClosePost**](BrokerApi.md#apiBrokerPeriodClosePost) | **POST** api/broker/period/close | Close investment period
[**apiBrokerPeriodProcessClosingProgramPost**](BrokerApi.md#apiBrokerPeriodProcessClosingProgramPost) | **POST** api/broker/period/processClosingProgram | Close investment program
[**apiBrokerPeriodProcessInvestmentRequestsPost**](BrokerApi.md#apiBrokerPeriodProcessInvestmentRequestsPost) | **POST** api/broker/period/processInvestmentRequests | Process investment requests
[**apiBrokerPeriodReevaluateManagerTokenPost**](BrokerApi.md#apiBrokerPeriodReevaluateManagerTokenPost) | **POST** api/broker/period/reevaluateManagerToken | Update manager token initial price/total supply after loss
[**apiBrokerPeriodSetStartValuesPost**](BrokerApi.md#apiBrokerPeriodSetStartValuesPost) | **POST** api/broker/period/setStartValues | Set investment period start balance, manager share, manager balance
[**apiBrokerPeriodTerminatePost**](BrokerApi.md#apiBrokerPeriodTerminatePost) | **POST** api/broker/period/terminate | Terminate program
[**apiBrokerPeriodlosingDataGet**](BrokerApi.md#apiBrokerPeriodlosingDataGet) | **GET** api/broker/period/сlosingData | Get data for closing investment period
[**apiBrokerTradesIpfsHashUpdatePost**](BrokerApi.md#apiBrokerTradesIpfsHashUpdatePost) | **POST** api/broker/trades/ipfsHash/update | Update manager history ipfs hash
[**apiBrokerTradesNewPost**](BrokerApi.md#apiBrokerTradesNewPost) | **POST** api/broker/trades/new | New trade event
[**apiBrokerTradesOpenTradesNewPost**](BrokerApi.md#apiBrokerTradesOpenTradesNewPost) | **POST** api/broker/trades/openTrades/new | New open trades event


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

<a name="apiBrokerAuthConfirmEmailPost"></a>
# **apiBrokerAuthConfirmEmailPost**
> String apiBrokerAuthConfirmEmailPost(userId, code)

Confirm email after registration

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
String userId = "userId_example"; // String | 
String code = "code_example"; // String | 
try {
    String result = apiInstance.apiBrokerAuthConfirmEmailPost(userId, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerAuthConfirmEmailPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**|  | [optional]
 **code** | **String**|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerAuthResetPasswordPost"></a>
# **apiBrokerAuthResetPasswordPost**
> String apiBrokerAuthResetPasswordPost(model)

Reset password

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
ResetPasswordViewModel model = new ResetPasswordViewModel(); // ResetPasswordViewModel | 
try {
    String result = apiInstance.apiBrokerAuthResetPasswordPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerAuthResetPasswordPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**ResetPasswordViewModel**](ResetPasswordViewModel.md)|  | [optional]

### Return type

**String**

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

<a name="apiBrokerAuthhangePasswordPost"></a>
# **apiBrokerAuthhangePasswordPost**
> Void apiBrokerAuthhangePasswordPost(authorization, model)

Change password

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
String authorization = "authorization_example"; // String | JWT access token
ChangePasswordViewModel model = new ChangePasswordViewModel(); // ChangePasswordViewModel | 
try {
    Void result = apiInstance.apiBrokerAuthhangePasswordPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerAuthhangePasswordPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**ChangePasswordViewModel**](ChangePasswordViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
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
> Void apiBrokerPeriodAccrueProfitsPost(authorization, accrual)

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
    Void result = apiInstance.apiBrokerPeriodAccrueProfitsPost(authorization, accrual);
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

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerPeriodClosePost"></a>
# **apiBrokerPeriodClosePost**
> Void apiBrokerPeriodClosePost(investmentProgramId, currentBalance, authorization)

Close investment period

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID investmentProgramId = new UUID(); // UUID | 
Double currentBalance = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.apiBrokerPeriodClosePost(investmentProgramId, currentBalance, authorization);
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
 **currentBalance** | **Double**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerPeriodProcessClosingProgramPost"></a>
# **apiBrokerPeriodProcessClosingProgramPost**
> Void apiBrokerPeriodProcessClosingProgramPost(investmentProgramId, managerBalance, authorization)

Close investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID investmentProgramId = new UUID(); // UUID | 
Double managerBalance = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.apiBrokerPeriodProcessClosingProgramPost(investmentProgramId, managerBalance, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodProcessClosingProgramPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **investmentProgramId** | [**UUID**](.md)|  |
 **managerBalance** | **Double**|  |
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

<a name="apiBrokerPeriodReevaluateManagerTokenPost"></a>
# **apiBrokerPeriodReevaluateManagerTokenPost**
> Void apiBrokerPeriodReevaluateManagerTokenPost(investmentProgramId, investorLossShare, authorization)

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
    Void result = apiInstance.apiBrokerPeriodReevaluateManagerTokenPost(investmentProgramId, investorLossShare, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodReevaluateManagerTokenPost");
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

<a name="apiBrokerPeriodSetStartValuesPost"></a>
# **apiBrokerPeriodSetStartValuesPost**
> Void apiBrokerPeriodSetStartValuesPost(authorization, model)

Set investment period start balance, manager share, manager balance

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
String authorization = "authorization_example"; // String | JWT access token
StartValues model = new StartValues(); // StartValues | 
try {
    Void result = apiInstance.apiBrokerPeriodSetStartValuesPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodSetStartValuesPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**StartValues**](StartValues.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerPeriodTerminatePost"></a>
# **apiBrokerPeriodTerminatePost**
> Void apiBrokerPeriodTerminatePost(investmentProgramId, authorization)

Terminate program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID investmentProgramId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.apiBrokerPeriodTerminatePost(investmentProgramId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodTerminatePost");
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

<a name="apiBrokerTradesIpfsHashUpdatePost"></a>
# **apiBrokerTradesIpfsHashUpdatePost**
> Void apiBrokerTradesIpfsHashUpdatePost(authorization, data)

Update manager history ipfs hash

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
String authorization = "authorization_example"; // String | JWT access token
ManagerHistoryIpfsHash data = new ManagerHistoryIpfsHash(); // ManagerHistoryIpfsHash | 
try {
    Void result = apiInstance.apiBrokerTradesIpfsHashUpdatePost(authorization, data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerTradesIpfsHashUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **data** | [**ManagerHistoryIpfsHash**](ManagerHistoryIpfsHash.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
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

