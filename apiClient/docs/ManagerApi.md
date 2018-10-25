# ManagerApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10ManagerByIdDetailsGet**](ManagerApi.md#v10ManagerByIdDetailsGet) | **GET** v1.0/manager/{id}/details | Manager details
[**v10ManagerByIdGet**](ManagerApi.md#v10ManagerByIdGet) | **GET** v1.0/manager/{id} | Manager profile
[**v10ManagerEventsGet**](ManagerApi.md#v10ManagerEventsGet) | **GET** v1.0/manager/events | Manager events
[**v10ManagerFundsByIdClosePost**](ManagerApi.md#v10ManagerFundsByIdClosePost) | **POST** v1.0/manager/funds/{id}/close | Close existing investment program/fund
[**v10ManagerFundsByIdInvestByAmountPost**](ManagerApi.md#v10ManagerFundsByIdInvestByAmountPost) | **POST** v1.0/manager/funds/{id}/invest/{amount} | Deposit
[**v10ManagerFundsByIdInvestInfoByCurrencyGet**](ManagerApi.md#v10ManagerFundsByIdInvestInfoByCurrencyGet) | **GET** v1.0/manager/funds/{id}/invest/info/{currency} | Data for investing into the fund
[**v10ManagerFundsByIdRequestsBySkipByTakeGet**](ManagerApi.md#v10ManagerFundsByIdRequestsBySkipByTakeGet) | **GET** v1.0/manager/funds/{id}/requests/{skip}/{take} | Get investment program/fund requests
[**v10ManagerFundsByIdUpdatePost**](ManagerApi.md#v10ManagerFundsByIdUpdatePost) | **POST** v1.0/manager/funds/{id}/update | Update investment program/fund details
[**v10ManagerFundsByIdWithdrawByPercentPost**](ManagerApi.md#v10ManagerFundsByIdWithdrawByPercentPost) | **POST** v1.0/manager/funds/{id}/withdraw/{percent} | Withdraw from fund. Percent is % of investor total money.
[**v10ManagerFundsByIdWithdrawInfoByCurrencyGet**](ManagerApi.md#v10ManagerFundsByIdWithdrawInfoByCurrencyGet) | **GET** v1.0/manager/funds/{id}/withdraw/info/{currency} | Data for withdrawal from fund
[**v10ManagerFundsCreatePost**](ManagerApi.md#v10ManagerFundsCreatePost) | **POST** v1.0/manager/funds/create | Create fund
[**v10ManagerFundsGet**](ManagerApi.md#v10ManagerFundsGet) | **GET** v1.0/manager/funds | Manager funds
[**v10ManagerFundsInvestmentAmountGet**](ManagerApi.md#v10ManagerFundsInvestmentAmountGet) | **GET** v1.0/manager/funds/investment/amount | Get GVT investment to create fund
[**v10ManagerFundsRequestsByIdCancelPost**](ManagerApi.md#v10ManagerFundsRequestsByIdCancelPost) | **POST** v1.0/manager/funds/requests/{id}/cancel | Cancel investment program/fund request
[**v10ManagerGet**](ManagerApi.md#v10ManagerGet) | **GET** v1.0/manager | Manager dashboard
[**v10ManagerProgramsByIdClosePost**](ManagerApi.md#v10ManagerProgramsByIdClosePost) | **POST** v1.0/manager/programs/{id}/close | Close existing investment program/fund
[**v10ManagerProgramsByIdInvestByAmountPost**](ManagerApi.md#v10ManagerProgramsByIdInvestByAmountPost) | **POST** v1.0/manager/programs/{id}/invest/{amount} | Deposit
[**v10ManagerProgramsByIdInvestInfoByCurrencyGet**](ManagerApi.md#v10ManagerProgramsByIdInvestInfoByCurrencyGet) | **GET** v1.0/manager/programs/{id}/invest/info/{currency} | Data for investing into the program
[**v10ManagerProgramsByIdPeriodClosePost**](ManagerApi.md#v10ManagerProgramsByIdPeriodClosePost) | **POST** v1.0/manager/programs/{id}/period/close | Close current period
[**v10ManagerProgramsByIdRequestsBySkipByTakeGet**](ManagerApi.md#v10ManagerProgramsByIdRequestsBySkipByTakeGet) | **GET** v1.0/manager/programs/{id}/requests/{skip}/{take} | Get investment program/fund requests
[**v10ManagerProgramsByIdUpdatePost**](ManagerApi.md#v10ManagerProgramsByIdUpdatePost) | **POST** v1.0/manager/programs/{id}/update | Update investment program/fund details
[**v10ManagerProgramsByIdWithdrawByAmountPost**](ManagerApi.md#v10ManagerProgramsByIdWithdrawByAmountPost) | **POST** v1.0/manager/programs/{id}/withdraw/{amount} | Withdraw from program
[**v10ManagerProgramsByIdWithdrawInfoByCurrencyGet**](ManagerApi.md#v10ManagerProgramsByIdWithdrawInfoByCurrencyGet) | **GET** v1.0/manager/programs/{id}/withdraw/info/{currency} | Data for withdrawal from investment program
[**v10ManagerProgramsCreatePost**](ManagerApi.md#v10ManagerProgramsCreatePost) | **POST** v1.0/manager/programs/create | Create an investment program
[**v10ManagerProgramsGet**](ManagerApi.md#v10ManagerProgramsGet) | **GET** v1.0/manager/programs | Manager programs
[**v10ManagerProgramsInvestmentAmountGet**](ManagerApi.md#v10ManagerProgramsInvestmentAmountGet) | **GET** v1.0/manager/programs/investment/amount | Get GVT investment to create program
[**v10ManagerProgramsRequestsByIdCancelPost**](ManagerApi.md#v10ManagerProgramsRequestsByIdCancelPost) | **POST** v1.0/manager/programs/requests/{id}/cancel | Cancel investment program/fund request
[**v10ManagerRequestsBySkipByTakeGet**](ManagerApi.md#v10ManagerRequestsBySkipByTakeGet) | **GET** v1.0/manager/requests/{skip}/{take} | Get all requests


<a name="v10ManagerByIdDetailsGet"></a>
# **v10ManagerByIdDetailsGet**
> ManagerProfileDetails v10ManagerByIdDetailsGet(id)

Manager details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String id = "id_example"; // String | 
try {
    ManagerProfileDetails result = apiInstance.v10ManagerByIdDetailsGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerByIdDetailsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |

### Return type

[**ManagerProfileDetails**](ManagerProfileDetails.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerByIdGet"></a>
# **v10ManagerByIdGet**
> ManagerProfile v10ManagerByIdGet(id)

Manager profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String id = "id_example"; // String | 
try {
    ManagerProfile result = apiInstance.v10ManagerByIdGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerByIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |

### Return type

[**ManagerProfile**](ManagerProfile.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerEventsGet"></a>
# **v10ManagerEventsGet**
> ManagerPortfolioEvents v10ManagerEventsGet(authorization, assetId, from, to, type, assetType)

Manager events

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
UUID assetId = new UUID(); // UUID | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
String type = "type_example"; // String | 
String assetType = "assetType_example"; // String | 
try {
    ManagerPortfolioEvents result = apiInstance.v10ManagerEventsGet(authorization, assetId, from, to, type, assetType);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerEventsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **assetId** | [**UUID**](.md)|  | [optional]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **type** | **String**|  | [optional] [enum: AssetStarted, ProgramPeriodStats, ProgramPeriodEnds, InvestorInvest, InvestorWithdraw, ManagerInvest, ManagerWithdraw, AssetFinished]
 **assetType** | **String**|  | [optional] [enum: All, Program, Fund]

### Return type

[**ManagerPortfolioEvents**](ManagerPortfolioEvents.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsByIdClosePost"></a>
# **v10ManagerFundsByIdClosePost**
> Void v10ManagerFundsByIdClosePost(id, authorization, twoFactorCode)

Close existing investment program/fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
String twoFactorCode = "twoFactorCode_example"; // String | 
try {
    Void result = apiInstance.v10ManagerFundsByIdClosePost(id, authorization, twoFactorCode);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsByIdClosePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **twoFactorCode** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsByIdInvestByAmountPost"></a>
# **v10ManagerFundsByIdInvestByAmountPost**
> Void v10ManagerFundsByIdInvestByAmountPost(id, amount, authorization)

Deposit

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagerFundsByIdInvestByAmountPost(id, amount, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsByIdInvestByAmountPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **amount** | **Double**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsByIdInvestInfoByCurrencyGet"></a>
# **v10ManagerFundsByIdInvestInfoByCurrencyGet**
> FundInvestInfo v10ManagerFundsByIdInvestInfoByCurrencyGet(id, currency, authorization)

Data for investing into the fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    FundInvestInfo result = apiInstance.v10ManagerFundsByIdInvestInfoByCurrencyGet(id, currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsByIdInvestInfoByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, USD, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**FundInvestInfo**](FundInvestInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsByIdRequestsBySkipByTakeGet"></a>
# **v10ManagerFundsByIdRequestsBySkipByTakeGet**
> ProgramRequests v10ManagerFundsByIdRequestsBySkipByTakeGet(id, skip, take, authorization)

Get investment program/fund requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramRequests result = apiInstance.v10ManagerFundsByIdRequestsBySkipByTakeGet(id, skip, take, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsByIdRequestsBySkipByTakeGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **skip** | **Integer**|  |
 **take** | **Integer**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**ProgramRequests**](ProgramRequests.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsByIdUpdatePost"></a>
# **v10ManagerFundsByIdUpdatePost**
> Void v10ManagerFundsByIdUpdatePost(id, authorization, model)

Update investment program/fund details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
ProgramUpdate model = new ProgramUpdate(); // ProgramUpdate | 
try {
    Void result = apiInstance.v10ManagerFundsByIdUpdatePost(id, authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsByIdUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **model** | [**ProgramUpdate**](ProgramUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsByIdWithdrawByPercentPost"></a>
# **v10ManagerFundsByIdWithdrawByPercentPost**
> Void v10ManagerFundsByIdWithdrawByPercentPost(id, percent, authorization)

Withdraw from fund. Percent is % of investor total money.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
Double percent = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagerFundsByIdWithdrawByPercentPost(id, percent, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsByIdWithdrawByPercentPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **percent** | **Double**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsByIdWithdrawInfoByCurrencyGet"></a>
# **v10ManagerFundsByIdWithdrawInfoByCurrencyGet**
> ManagerFundWithdrawInfo v10ManagerFundsByIdWithdrawInfoByCurrencyGet(id, currency, authorization)

Data for withdrawal from fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ManagerFundWithdrawInfo result = apiInstance.v10ManagerFundsByIdWithdrawInfoByCurrencyGet(id, currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsByIdWithdrawInfoByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, USD, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**ManagerFundWithdrawInfo**](ManagerFundWithdrawInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsCreatePost"></a>
# **v10ManagerFundsCreatePost**
> Void v10ManagerFundsCreatePost(authorization, request)

Create fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
NewFundRequest request = new NewFundRequest(); // NewFundRequest | 
try {
    Void result = apiInstance.v10ManagerFundsCreatePost(authorization, request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsCreatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **request** | [**NewFundRequest**](NewFundRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsGet"></a>
# **v10ManagerFundsGet**
> ManagerFunds v10ManagerFundsGet(authorization, sorting, from, to, pointsCount, skip, take)

Manager funds

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
String sorting = "sorting_example"; // String | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
Integer pointsCount = 56; // Integer | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ManagerFunds result = apiInstance.v10ManagerFundsGet(authorization, sorting, from, to, pointsCount, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **sorting** | **String**|  | [optional] [enum: ByTitleAsc, ByTitleDesc, ByShareAsc, ByShareDesc, ByInvestmentsAsc, ByInvestmentsDesc, ByWithdrawalsAsc, ByWithdrawalsDesc, ByBalanceAsc, ByBalanceDesc, ByProfitAsc, ByProfitDesc]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **pointsCount** | **Integer**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ManagerFunds**](ManagerFunds.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsInvestmentAmountGet"></a>
# **v10ManagerFundsInvestmentAmountGet**
> Double v10ManagerFundsInvestmentAmountGet(authorization)

Get GVT investment to create fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Double result = apiInstance.v10ManagerFundsInvestmentAmountGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsInvestmentAmountGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

**Double**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsRequestsByIdCancelPost"></a>
# **v10ManagerFundsRequestsByIdCancelPost**
> Void v10ManagerFundsRequestsByIdCancelPost(id, authorization)

Cancel investment program/fund request

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagerFundsRequestsByIdCancelPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsRequestsByIdCancelPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerGet"></a>
# **v10ManagerGet**
> ManagerDashboard v10ManagerGet(authorization, eventsTake, requestsSkip, requestsTake)

Manager dashboard

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
Integer eventsTake = 56; // Integer | 
Integer requestsSkip = 56; // Integer | 
Integer requestsTake = 56; // Integer | 
try {
    ManagerDashboard result = apiInstance.v10ManagerGet(authorization, eventsTake, requestsSkip, requestsTake);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **eventsTake** | **Integer**|  | [optional]
 **requestsSkip** | **Integer**|  | [optional]
 **requestsTake** | **Integer**|  | [optional]

### Return type

[**ManagerDashboard**](ManagerDashboard.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsByIdClosePost"></a>
# **v10ManagerProgramsByIdClosePost**
> Void v10ManagerProgramsByIdClosePost(id, authorization, twoFactorCode)

Close existing investment program/fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
String twoFactorCode = "twoFactorCode_example"; // String | 
try {
    Void result = apiInstance.v10ManagerProgramsByIdClosePost(id, authorization, twoFactorCode);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsByIdClosePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **twoFactorCode** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsByIdInvestByAmountPost"></a>
# **v10ManagerProgramsByIdInvestByAmountPost**
> Void v10ManagerProgramsByIdInvestByAmountPost(id, amount, authorization)

Deposit

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagerProgramsByIdInvestByAmountPost(id, amount, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsByIdInvestByAmountPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **amount** | **Double**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsByIdInvestInfoByCurrencyGet"></a>
# **v10ManagerProgramsByIdInvestInfoByCurrencyGet**
> ProgramInvestInfo v10ManagerProgramsByIdInvestInfoByCurrencyGet(id, currency, authorization)

Data for investing into the program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramInvestInfo result = apiInstance.v10ManagerProgramsByIdInvestInfoByCurrencyGet(id, currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsByIdInvestInfoByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, USD, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**ProgramInvestInfo**](ProgramInvestInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsByIdPeriodClosePost"></a>
# **v10ManagerProgramsByIdPeriodClosePost**
> Void v10ManagerProgramsByIdPeriodClosePost(id, authorization)

Close current period

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagerProgramsByIdPeriodClosePost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsByIdPeriodClosePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsByIdRequestsBySkipByTakeGet"></a>
# **v10ManagerProgramsByIdRequestsBySkipByTakeGet**
> ProgramRequests v10ManagerProgramsByIdRequestsBySkipByTakeGet(id, skip, take, authorization)

Get investment program/fund requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramRequests result = apiInstance.v10ManagerProgramsByIdRequestsBySkipByTakeGet(id, skip, take, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsByIdRequestsBySkipByTakeGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **skip** | **Integer**|  |
 **take** | **Integer**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**ProgramRequests**](ProgramRequests.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsByIdUpdatePost"></a>
# **v10ManagerProgramsByIdUpdatePost**
> Void v10ManagerProgramsByIdUpdatePost(id, authorization, model)

Update investment program/fund details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
ProgramUpdate model = new ProgramUpdate(); // ProgramUpdate | 
try {
    Void result = apiInstance.v10ManagerProgramsByIdUpdatePost(id, authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsByIdUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **model** | [**ProgramUpdate**](ProgramUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsByIdWithdrawByAmountPost"></a>
# **v10ManagerProgramsByIdWithdrawByAmountPost**
> Void v10ManagerProgramsByIdWithdrawByAmountPost(id, amount, authorization)

Withdraw from program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagerProgramsByIdWithdrawByAmountPost(id, amount, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsByIdWithdrawByAmountPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **amount** | **Double**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsByIdWithdrawInfoByCurrencyGet"></a>
# **v10ManagerProgramsByIdWithdrawInfoByCurrencyGet**
> ManagerProgramWithdrawInfo v10ManagerProgramsByIdWithdrawInfoByCurrencyGet(id, currency, authorization)

Data for withdrawal from investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ManagerProgramWithdrawInfo result = apiInstance.v10ManagerProgramsByIdWithdrawInfoByCurrencyGet(id, currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsByIdWithdrawInfoByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, USD, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**ManagerProgramWithdrawInfo**](ManagerProgramWithdrawInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsCreatePost"></a>
# **v10ManagerProgramsCreatePost**
> Void v10ManagerProgramsCreatePost(authorization, request)

Create an investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
NewProgramRequest request = new NewProgramRequest(); // NewProgramRequest | 
try {
    Void result = apiInstance.v10ManagerProgramsCreatePost(authorization, request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsCreatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **request** | [**NewProgramRequest**](NewProgramRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsGet"></a>
# **v10ManagerProgramsGet**
> ManagerPrograms v10ManagerProgramsGet(authorization, sorting, from, to, pointsCount, skip, take)

Manager programs

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
String sorting = "sorting_example"; // String | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
Integer pointsCount = 56; // Integer | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ManagerPrograms result = apiInstance.v10ManagerProgramsGet(authorization, sorting, from, to, pointsCount, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **sorting** | **String**|  | [optional] [enum: ByTitleAsc, ByTitleDesc, ByShareAsc, ByShareDesc, ByInvestmentsAsc, ByInvestmentsDesc, ByWithdrawalsAsc, ByWithdrawalsDesc, ByTimeLeftAsc, ByTimeLeftDesc, ByBalanceAsc, ByBalanceDesc, ByProfitAsc, ByProfitDesc]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **pointsCount** | **Integer**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ManagerPrograms**](ManagerPrograms.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsInvestmentAmountGet"></a>
# **v10ManagerProgramsInvestmentAmountGet**
> Double v10ManagerProgramsInvestmentAmountGet(authorization)

Get GVT investment to create program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Double result = apiInstance.v10ManagerProgramsInvestmentAmountGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsInvestmentAmountGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

**Double**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsRequestsByIdCancelPost"></a>
# **v10ManagerProgramsRequestsByIdCancelPost**
> Void v10ManagerProgramsRequestsByIdCancelPost(id, authorization)

Cancel investment program/fund request

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagerProgramsRequestsByIdCancelPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsRequestsByIdCancelPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerRequestsBySkipByTakeGet"></a>
# **v10ManagerRequestsBySkipByTakeGet**
> ProgramRequests v10ManagerRequestsBySkipByTakeGet(skip, take, authorization)

Get all requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramRequests result = apiInstance.v10ManagerRequestsBySkipByTakeGet(skip, take, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerRequestsBySkipByTakeGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **skip** | **Integer**|  |
 **take** | **Integer**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**ProgramRequests**](ProgramRequests.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

