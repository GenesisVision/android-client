# ManagerApi

All URIs are relative to *https://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10ManagerAssetsGet**](ManagerApi.md#v10ManagerAssetsGet) | **GET** v1.0/manager/assets | Manager assets list
[**v10ManagerByIdDetailsGet**](ManagerApi.md#v10ManagerByIdDetailsGet) | **GET** v1.0/manager/{id}/details | Manager details
[**v10ManagerByIdGet**](ManagerApi.md#v10ManagerByIdGet) | **GET** v1.0/manager/{id} | Manager profile
[**v10ManagerEventsGet**](ManagerApi.md#v10ManagerEventsGet) | **GET** v1.0/manager/events | Manager events
[**v10ManagerFundsByIdAssetsUpdatePost**](ManagerApi.md#v10ManagerFundsByIdAssetsUpdatePost) | **POST** v1.0/manager/funds/{id}/assets/update | Update fund assets parts
[**v10ManagerFundsByIdClosePost**](ManagerApi.md#v10ManagerFundsByIdClosePost) | **POST** v1.0/manager/funds/{id}/close | Close existing fund
[**v10ManagerFundsByIdInvestByAmountPost**](ManagerApi.md#v10ManagerFundsByIdInvestByAmountPost) | **POST** v1.0/manager/funds/{id}/invest/{amount} | Deposit.  Invest in GVT if currency is empty
[**v10ManagerFundsByIdInvestInfoByCurrencyGet**](ManagerApi.md#v10ManagerFundsByIdInvestInfoByCurrencyGet) | **GET** v1.0/manager/funds/{id}/invest/info/{currency} | Data for investing into the fund
[**v10ManagerFundsByIdRequestsBySkipByTakeGet**](ManagerApi.md#v10ManagerFundsByIdRequestsBySkipByTakeGet) | **GET** v1.0/manager/funds/{id}/requests/{skip}/{take} | Get investment program/fund requests
[**v10ManagerFundsByIdUpdatePost**](ManagerApi.md#v10ManagerFundsByIdUpdatePost) | **POST** v1.0/manager/funds/{id}/update | Update investment program/fund details
[**v10ManagerFundsByIdWithdrawByPercentPost**](ManagerApi.md#v10ManagerFundsByIdWithdrawByPercentPost) | **POST** v1.0/manager/funds/{id}/withdraw/{percent} | Withdraw from fund. Percent is % of manager total money.  Withdraw in GVT if currency is empty
[**v10ManagerFundsByIdWithdrawInfoByCurrencyGet**](ManagerApi.md#v10ManagerFundsByIdWithdrawInfoByCurrencyGet) | **GET** v1.0/manager/funds/{id}/withdraw/info/{currency} | Data for withdrawal from fund
[**v10ManagerFundsCreatePost**](ManagerApi.md#v10ManagerFundsCreatePost) | **POST** v1.0/manager/funds/create | Create fund
[**v10ManagerFundsGet**](ManagerApi.md#v10ManagerFundsGet) | **GET** v1.0/manager/funds | Manager funds
[**v10ManagerFundsInvestmentAmountGet**](ManagerApi.md#v10ManagerFundsInvestmentAmountGet) | **GET** v1.0/manager/funds/investment/amount | Get GVT investment to create fund
[**v10ManagerFundsRequestsByIdCancelPost**](ManagerApi.md#v10ManagerFundsRequestsByIdCancelPost) | **POST** v1.0/manager/funds/requests/{id}/cancel | Cancel investment program/fund request
[**v10ManagerGet**](ManagerApi.md#v10ManagerGet) | **GET** v1.0/manager | Manager dashboard
[**v10ManagerPrograms2faConfirmPost**](ManagerApi.md#v10ManagerPrograms2faConfirmPost) | **POST** v1.0/manager/programs/2fa/confirm | Confirm 2FA for program if required (for brokers like Huobi)
[**v10ManagerPrograms2faGetGet**](ManagerApi.md#v10ManagerPrograms2faGetGet) | **GET** v1.0/manager/programs/2fa/get | Get 2FA for program if needed
[**v10ManagerProgramsBrokerChangeCancelPost**](ManagerApi.md#v10ManagerProgramsBrokerChangeCancelPost) | **POST** v1.0/manager/programs/broker/change/cancel | Cancel changing broker in existing program
[**v10ManagerProgramsBrokerChangePost**](ManagerApi.md#v10ManagerProgramsBrokerChangePost) | **POST** v1.0/manager/programs/broker/change | Change broker in existing program
[**v10ManagerProgramsByIdClosePost**](ManagerApi.md#v10ManagerProgramsByIdClosePost) | **POST** v1.0/manager/programs/{id}/close | Close existing investment program
[**v10ManagerProgramsByIdInvestByAmountPost**](ManagerApi.md#v10ManagerProgramsByIdInvestByAmountPost) | **POST** v1.0/manager/programs/{id}/invest/{amount} | Deposit  Invest in GVT if currency is empty
[**v10ManagerProgramsByIdInvestInfoByCurrencyGet**](ManagerApi.md#v10ManagerProgramsByIdInvestInfoByCurrencyGet) | **GET** v1.0/manager/programs/{id}/invest/info/{currency} | Data for investing into the program
[**v10ManagerProgramsByIdLevelsInfoGet**](ManagerApi.md#v10ManagerProgramsByIdLevelsInfoGet) | **GET** v1.0/manager/programs/{id}/levels/info | Get program data for levels calculator
[**v10ManagerProgramsByIdPasswordChangePost**](ManagerApi.md#v10ManagerProgramsByIdPasswordChangePost) | **POST** v1.0/manager/programs/{id}/password/change | Change program password
[**v10ManagerProgramsByIdPeriodClosePost**](ManagerApi.md#v10ManagerProgramsByIdPeriodClosePost) | **POST** v1.0/manager/programs/{id}/period/close | Close current period
[**v10ManagerProgramsByIdRequestsBySkipByTakeGet**](ManagerApi.md#v10ManagerProgramsByIdRequestsBySkipByTakeGet) | **GET** v1.0/manager/programs/{id}/requests/{skip}/{take} | Get investment program/fund requests
[**v10ManagerProgramsByIdUpdatePost**](ManagerApi.md#v10ManagerProgramsByIdUpdatePost) | **POST** v1.0/manager/programs/{id}/update | Update investment program/fund details
[**v10ManagerProgramsByIdWithdrawByAmountPost**](ManagerApi.md#v10ManagerProgramsByIdWithdrawByAmountPost) | **POST** v1.0/manager/programs/{id}/withdraw/{amount} | Withdraw from investment program in GVT
[**v10ManagerProgramsByIdWithdrawInfoByCurrencyGet**](ManagerApi.md#v10ManagerProgramsByIdWithdrawInfoByCurrencyGet) | **GET** v1.0/manager/programs/{id}/withdraw/info/{currency} | Data for withdrawal from investment program
[**v10ManagerProgramsByIdWithdrawMultiByAmountPost**](ManagerApi.md#v10ManagerProgramsByIdWithdrawMultiByAmountPost) | **POST** v1.0/manager/programs/{id}/withdraw/multi/{amount} | Withdraw from investment program in program currency
[**v10ManagerProgramsCreatePost**](ManagerApi.md#v10ManagerProgramsCreatePost) | **POST** v1.0/manager/programs/create | Create an investment program
[**v10ManagerProgramsGet**](ManagerApi.md#v10ManagerProgramsGet) | **GET** v1.0/manager/programs | Manager programs
[**v10ManagerProgramsInvestmentAmountGet**](ManagerApi.md#v10ManagerProgramsInvestmentAmountGet) | **GET** v1.0/manager/programs/investment/amount | Get investment amount to create program
[**v10ManagerProgramsRequestsByIdCancelPost**](ManagerApi.md#v10ManagerProgramsRequestsByIdCancelPost) | **POST** v1.0/manager/programs/requests/{id}/cancel | Cancel investment program/fund request
[**v10ManagerRequestsBySkipByTakeGet**](ManagerApi.md#v10ManagerRequestsBySkipByTakeGet) | **GET** v1.0/manager/requests/{skip}/{take} | Get all requests
[**v10ManagerSignalCreatePost**](ManagerApi.md#v10ManagerSignalCreatePost) | **POST** v1.0/manager/signal/create | Make manager&#39;s program signal provider
[**v10ManagerSignalEditPost**](ManagerApi.md#v10ManagerSignalEditPost) | **POST** v1.0/manager/signal/edit | Make manager&#39;s program signal provider


<a name="v10ManagerAssetsGet"></a>
# **v10ManagerAssetsGet**
> ManagerAssets v10ManagerAssetsGet(authorization)

Manager assets list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    ManagerAssets result = apiInstance.v10ManagerAssetsGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerAssetsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**ManagerAssets**](ManagerAssets.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

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
> ManagerPortfolioEvents v10ManagerEventsGet(authorization, assetId, from, to, type, assetType, skip, take)

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
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ManagerPortfolioEvents result = apiInstance.v10ManagerEventsGet(authorization, assetId, from, to, type, assetType, skip, take);
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
 **type** | **String**|  | [optional] [enum: All, AssetStarted, ProgramPeriodStarts, ProgramPeriodEnds, InvestorInvest, InvestorWithdraw, ManagerInvest, ManagerWithdraw, AssetFinished, EntranceFee, ExitFee, ProgramStopOut, ProgramManagerTradingFeeAccrual, ProgramSignalSubscribe, ProgramBrokerChanged]
 **assetType** | **String**|  | [optional] [enum: All, Program, Fund]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ManagerPortfolioEvents**](ManagerPortfolioEvents.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsByIdAssetsUpdatePost"></a>
# **v10ManagerFundsByIdAssetsUpdatePost**
> Void v10ManagerFundsByIdAssetsUpdatePost(id, authorization, assets)

Update fund assets parts

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
List<FundAssetPart> assets = Arrays.asList(new FundAssetPart()); // List<FundAssetPart> | 
try {
    Void result = apiInstance.v10ManagerFundsByIdAssetsUpdatePost(id, authorization, assets);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerFundsByIdAssetsUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **assets** | [**List&lt;FundAssetPart&gt;**](FundAssetPart.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerFundsByIdClosePost"></a>
# **v10ManagerFundsByIdClosePost**
> Void v10ManagerFundsByIdClosePost(id, authorization, twoFactorCode)

Close existing fund

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
> Void v10ManagerFundsByIdInvestByAmountPost(id, amount, authorization, currency)

Deposit.  Invest in GVT if currency is empty

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
String currency = "100"; // String | 
try {
    Void result = apiInstance.v10ManagerFundsByIdInvestByAmountPost(id, amount, authorization, currency);
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
 **currency** | **String**|  | [optional] [default to 100] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]

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
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
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
> Void v10ManagerFundsByIdWithdrawByPercentPost(id, percent, authorization, currency)

Withdraw from fund. Percent is % of manager total money.  Withdraw in GVT if currency is empty

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
Double percent = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
String currency = "100"; // String | 
try {
    Void result = apiInstance.v10ManagerFundsByIdWithdrawByPercentPost(id, percent, authorization, currency);
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
 **currency** | **String**|  | [optional] [default to 100] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]

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
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
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
> FundsList v10ManagerFundsGet(authorization, sorting, from, to, chartPointsCount, currencySecondary, actionStatus, dashboardActionStatus, skip, take)

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
Integer chartPointsCount = 56; // Integer | 
String currencySecondary = "currencySecondary_example"; // String | 
String actionStatus = "actionStatus_example"; // String | 
String dashboardActionStatus = "dashboardActionStatus_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    FundsList result = apiInstance.v10ManagerFundsGet(authorization, sorting, from, to, chartPointsCount, currencySecondary, actionStatus, dashboardActionStatus, skip, take);
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
 **sorting** | **String**|  | [optional] [enum: ByProfitAsc, ByProfitDesc, ByDrawdownAsc, ByDrawdownDesc, ByInvestorsAsc, ByInvestorsDesc, ByNewAsc, ByNewDesc, ByTitleAsc, ByTitleDesc, ByBalanceAsc, ByBalanceDesc]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **currencySecondary** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **actionStatus** | **String**|  | [optional] [enum: Pending, Active, Investing, Withdrawing, Ended]
 **dashboardActionStatus** | **String**|  | [optional] [enum: All, Active]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**FundsList**](FundsList.md)

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
> ManagerDashboard v10ManagerGet(authorization, assetId, from, to, type, assetType, skip, take)

Manager dashboard

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
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ManagerDashboard result = apiInstance.v10ManagerGet(authorization, assetId, from, to, type, assetType, skip, take);
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
 **assetId** | [**UUID**](.md)|  | [optional]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **type** | **String**|  | [optional] [enum: All, AssetStarted, ProgramPeriodStarts, ProgramPeriodEnds, InvestorInvest, InvestorWithdraw, ManagerInvest, ManagerWithdraw, AssetFinished, EntranceFee, ExitFee, ProgramStopOut, ProgramManagerTradingFeeAccrual, ProgramSignalSubscribe, ProgramBrokerChanged]
 **assetType** | **String**|  | [optional] [enum: All, Program, Fund]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ManagerDashboard**](ManagerDashboard.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerPrograms2faConfirmPost"></a>
# **v10ManagerPrograms2faConfirmPost**
> Void v10ManagerPrograms2faConfirmPost(authorization, programId, code)

Confirm 2FA for program if required (for brokers like Huobi)

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
UUID programId = new UUID(); // UUID | 
String code = "code_example"; // String | 
try {
    Void result = apiInstance.v10ManagerPrograms2faConfirmPost(authorization, programId, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerPrograms2faConfirmPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **programId** | [**UUID**](.md)|  | [optional]
 **code** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerPrograms2faGetGet"></a>
# **v10ManagerPrograms2faGetGet**
> TwoFactorAuthenticator v10ManagerPrograms2faGetGet(authorization, programId)

Get 2FA for program if needed

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
UUID programId = new UUID(); // UUID | 
try {
    TwoFactorAuthenticator result = apiInstance.v10ManagerPrograms2faGetGet(authorization, programId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerPrograms2faGetGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **programId** | [**UUID**](.md)|  | [optional]

### Return type

[**TwoFactorAuthenticator**](TwoFactorAuthenticator.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsBrokerChangeCancelPost"></a>
# **v10ManagerProgramsBrokerChangeCancelPost**
> Void v10ManagerProgramsBrokerChangeCancelPost(authorization, programId)

Cancel changing broker in existing program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
UUID programId = new UUID(); // UUID | 
try {
    Void result = apiInstance.v10ManagerProgramsBrokerChangeCancelPost(authorization, programId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsBrokerChangeCancelPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **programId** | [**UUID**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsBrokerChangePost"></a>
# **v10ManagerProgramsBrokerChangePost**
> Void v10ManagerProgramsBrokerChangePost(authorization, request)

Change broker in existing program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
ChangeBrokerProgramRequest request = new ChangeBrokerProgramRequest(); // ChangeBrokerProgramRequest | 
try {
    Void result = apiInstance.v10ManagerProgramsBrokerChangePost(authorization, request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsBrokerChangePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **request** | [**ChangeBrokerProgramRequest**](ChangeBrokerProgramRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsByIdClosePost"></a>
# **v10ManagerProgramsByIdClosePost**
> Void v10ManagerProgramsByIdClosePost(id, authorization, twoFactorCode)

Close existing investment program

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
> Void v10ManagerProgramsByIdInvestByAmountPost(id, amount, authorization, currency)

Deposit  Invest in GVT if currency is empty

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
String currency = "100"; // String | 
try {
    Void result = apiInstance.v10ManagerProgramsByIdInvestByAmountPost(id, amount, authorization, currency);
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
 **currency** | **String**|  | [optional] [default to 100] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]

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
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**ProgramInvestInfo**](ProgramInvestInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsByIdLevelsInfoGet"></a>
# **v10ManagerProgramsByIdLevelsInfoGet**
> ProgramLevelInfo v10ManagerProgramsByIdLevelsInfoGet(id, authorization)

Get program data for levels calculator

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramLevelInfo result = apiInstance.v10ManagerProgramsByIdLevelsInfoGet(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsByIdLevelsInfoGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**ProgramLevelInfo**](ProgramLevelInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsByIdPasswordChangePost"></a>
# **v10ManagerProgramsByIdPasswordChangePost**
> Void v10ManagerProgramsByIdPasswordChangePost(id, authorization, model)

Change program password

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
ProgramPwdUpdate model = new ProgramPwdUpdate(); // ProgramPwdUpdate | 
try {
    Void result = apiInstance.v10ManagerProgramsByIdPasswordChangePost(id, authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsByIdPasswordChangePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **model** | [**ProgramPwdUpdate**](ProgramPwdUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
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

Withdraw from investment program in GVT

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
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**ManagerProgramWithdrawInfo**](ManagerProgramWithdrawInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsByIdWithdrawMultiByAmountPost"></a>
# **v10ManagerProgramsByIdWithdrawMultiByAmountPost**
> Void v10ManagerProgramsByIdWithdrawMultiByAmountPost(id, amount, authorization)

Withdraw from investment program in program currency

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
    Void result = apiInstance.v10ManagerProgramsByIdWithdrawMultiByAmountPost(id, amount, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerProgramsByIdWithdrawMultiByAmountPost");
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

<a name="v10ManagerProgramsCreatePost"></a>
# **v10ManagerProgramsCreatePost**
> ManagerProgramCreateResult v10ManagerProgramsCreatePost(authorization, request)

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
    ManagerProgramCreateResult result = apiInstance.v10ManagerProgramsCreatePost(authorization, request);
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

[**ManagerProgramCreateResult**](ManagerProgramCreateResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsGet"></a>
# **v10ManagerProgramsGet**
> ProgramsList v10ManagerProgramsGet(authorization, sorting, from, to, chartPointsCount, currencySecondary, actionStatus, dashboardActionStatus, skip, take)

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
Integer chartPointsCount = 56; // Integer | 
String currencySecondary = "currencySecondary_example"; // String | 
String actionStatus = "actionStatus_example"; // String | 
String dashboardActionStatus = "dashboardActionStatus_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ProgramsList result = apiInstance.v10ManagerProgramsGet(authorization, sorting, from, to, chartPointsCount, currencySecondary, actionStatus, dashboardActionStatus, skip, take);
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
 **sorting** | **String**|  | [optional] [enum: ByLevelAsc, ByLevelDesc, ByProfitAsc, ByProfitDesc, ByDrawdownAsc, ByDrawdownDesc, ByTradesAsc, ByTradesDesc, ByInvestorsAsc, ByInvestorsDesc, ByNewDesc, ByNewAsc, ByEndOfPeriodAsc, ByEndOfPeriodDesc, ByTitleAsc, ByTitleDesc, ByBalanceAsc, ByBalanceDesc, ByCurrDesc, ByCurrAsc, ByLevelProgressDesc, ByLevelProgressAsc]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **currencySecondary** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **actionStatus** | **String**|  | [optional] [enum: Pending, Active, Investing, Withdrawing, Ended]
 **dashboardActionStatus** | **String**|  | [optional] [enum: All, Active]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ProgramsList**](ProgramsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerProgramsInvestmentAmountGet"></a>
# **v10ManagerProgramsInvestmentAmountGet**
> ProgramMinimumDeposit v10ManagerProgramsInvestmentAmountGet(authorization, brokerTradingAccount)

Get investment amount to create program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
UUID brokerTradingAccount = new UUID(); // UUID | 
try {
    ProgramMinimumDeposit result = apiInstance.v10ManagerProgramsInvestmentAmountGet(authorization, brokerTradingAccount);
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
 **brokerTradingAccount** | [**UUID**](.md)|  | [optional]

### Return type

[**ProgramMinimumDeposit**](ProgramMinimumDeposit.md)

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

<a name="v10ManagerSignalCreatePost"></a>
# **v10ManagerSignalCreatePost**
> Void v10ManagerSignalCreatePost(authorization, programId, volumeFee, successFee)

Make manager&#39;s program signal provider

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
UUID programId = new UUID(); // UUID | 
Double volumeFee = 3.4D; // Double | 
Double successFee = 3.4D; // Double | 
try {
    Void result = apiInstance.v10ManagerSignalCreatePost(authorization, programId, volumeFee, successFee);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerSignalCreatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **programId** | [**UUID**](.md)|  | [optional]
 **volumeFee** | **Double**|  | [optional]
 **successFee** | **Double**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagerSignalEditPost"></a>
# **v10ManagerSignalEditPost**
> Void v10ManagerSignalEditPost(authorization, programId, volumeFee, successFee)

Make manager&#39;s program signal provider

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
UUID programId = new UUID(); // UUID | 
Double volumeFee = 3.4D; // Double | 
Double successFee = 3.4D; // Double | 
try {
    Void result = apiInstance.v10ManagerSignalEditPost(authorization, programId, volumeFee, successFee);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#v10ManagerSignalEditPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **programId** | [**UUID**](.md)|  | [optional]
 **volumeFee** | **Double**|  | [optional]
 **successFee** | **Double**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

