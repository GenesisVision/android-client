# InvestorApi

All URIs are relative to *https://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10InvestorFundsByIdInvestByAmountPost**](InvestorApi.md#v10InvestorFundsByIdInvestByAmountPost) | **POST** v1.0/investor/funds/{id}/invest/{amount} | Investing into the fund.  Invest in GVT if currency is empty
[**v10InvestorFundsByIdInvestInfoByCurrencyGet**](InvestorApi.md#v10InvestorFundsByIdInvestInfoByCurrencyGet) | **GET** v1.0/investor/funds/{id}/invest/info/{currency} | Data for investing into the fund
[**v10InvestorFundsByIdRequestsBySkipByTakeGet**](InvestorApi.md#v10InvestorFundsByIdRequestsBySkipByTakeGet) | **GET** v1.0/investor/funds/{id}/requests/{skip}/{take} | Get program/fund requests
[**v10InvestorFundsByIdWithdrawByPercentPost**](InvestorApi.md#v10InvestorFundsByIdWithdrawByPercentPost) | **POST** v1.0/investor/funds/{id}/withdraw/{percent} | Withdraw from fund. Percent is % of investor total money.  Withdraw in GVT if currency is empty
[**v10InvestorFundsByIdWithdrawInfoByCurrencyGet**](InvestorApi.md#v10InvestorFundsByIdWithdrawInfoByCurrencyGet) | **GET** v1.0/investor/funds/{id}/withdraw/info/{currency} | Data for withdrawal from fund
[**v10InvestorFundsGet**](InvestorApi.md#v10InvestorFundsGet) | **GET** v1.0/investor/funds | Dashboard funds list
[**v10InvestorGet**](InvestorApi.md#v10InvestorGet) | **GET** v1.0/investor | Summary dashboard info
[**v10InvestorInvestmentsEventsGet**](InvestorApi.md#v10InvestorInvestmentsEventsGet) | **GET** v1.0/investor/investments/events | Events
[**v10InvestorPortfolioChartGet**](InvestorApi.md#v10InvestorPortfolioChartGet) | **GET** v1.0/investor/portfolio/chart | Portfolio charts
[**v10InvestorPortfolioEventsGet**](InvestorApi.md#v10InvestorPortfolioEventsGet) | **GET** v1.0/investor/portfolio/events | Portfolio events
[**v10InvestorProgramsByIdInvestByAmountPost**](InvestorApi.md#v10InvestorProgramsByIdInvestByAmountPost) | **POST** v1.0/investor/programs/{id}/invest/{amount} | Investing into the program.  Invest in GVT if currency is empty
[**v10InvestorProgramsByIdInvestInfoByCurrencyGet**](InvestorApi.md#v10InvestorProgramsByIdInvestInfoByCurrencyGet) | **GET** v1.0/investor/programs/{id}/invest/info/{currency} | Data for investing into the program
[**v10InvestorProgramsByIdReinvestOffPost**](InvestorApi.md#v10InvestorProgramsByIdReinvestOffPost) | **POST** v1.0/investor/programs/{id}/reinvest/off | Disable reinvesting
[**v10InvestorProgramsByIdReinvestOnPost**](InvestorApi.md#v10InvestorProgramsByIdReinvestOnPost) | **POST** v1.0/investor/programs/{id}/reinvest/on | Enable reinvesting
[**v10InvestorProgramsByIdRequestsBySkipByTakeGet**](InvestorApi.md#v10InvestorProgramsByIdRequestsBySkipByTakeGet) | **GET** v1.0/investor/programs/{id}/requests/{skip}/{take} | Get program/fund requests
[**v10InvestorProgramsByIdWithdrawByAmountPost**](InvestorApi.md#v10InvestorProgramsByIdWithdrawByAmountPost) | **POST** v1.0/investor/programs/{id}/withdraw/{amount} | Withdraw from investment program in GVT
[**v10InvestorProgramsByIdWithdrawInfoByCurrencyGet**](InvestorApi.md#v10InvestorProgramsByIdWithdrawInfoByCurrencyGet) | **GET** v1.0/investor/programs/{id}/withdraw/info/{currency} | Data for withdrawal from investment program
[**v10InvestorProgramsByIdWithdrawMultiByAmountPost**](InvestorApi.md#v10InvestorProgramsByIdWithdrawMultiByAmountPost) | **POST** v1.0/investor/programs/{id}/withdraw/multi/{amount} | Withdraw from investment program in program currency
[**v10InvestorProgramsByIdWithdrawMultiPost**](InvestorApi.md#v10InvestorProgramsByIdWithdrawMultiPost) | **POST** v1.0/investor/programs/{id}/withdraw/multi | Withdraw from investment program in program currency
[**v10InvestorProgramsGet**](InvestorApi.md#v10InvestorProgramsGet) | **GET** v1.0/investor/programs | Dashboard program list
[**v10InvestorProgramsRequestsByIdCancelPost**](InvestorApi.md#v10InvestorProgramsRequestsByIdCancelPost) | **POST** v1.0/investor/programs/requests/{id}/cancel | Cancel investment program request
[**v10InvestorRequestsBySkipByTakeGet**](InvestorApi.md#v10InvestorRequestsBySkipByTakeGet) | **GET** v1.0/investor/requests/{skip}/{take} | Get all requests
[**v10InvestorSignalsGet**](InvestorApi.md#v10InvestorSignalsGet) | **GET** v1.0/investor/signals | Dashboard signal providers list


<a name="v10InvestorFundsByIdInvestByAmountPost"></a>
# **v10InvestorFundsByIdInvestByAmountPost**
> Void v10InvestorFundsByIdInvestByAmountPost(id, amount, authorization, currency)

Investing into the fund.  Invest in GVT if currency is empty

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
String currency = "100"; // String | 
try {
    Void result = apiInstance.v10InvestorFundsByIdInvestByAmountPost(id, amount, authorization, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorFundsByIdInvestByAmountPost");
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

<a name="v10InvestorFundsByIdInvestInfoByCurrencyGet"></a>
# **v10InvestorFundsByIdInvestInfoByCurrencyGet**
> FundInvestInfo v10InvestorFundsByIdInvestInfoByCurrencyGet(id, currency, authorization)

Data for investing into the fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    FundInvestInfo result = apiInstance.v10InvestorFundsByIdInvestInfoByCurrencyGet(id, currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorFundsByIdInvestInfoByCurrencyGet");
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

<a name="v10InvestorFundsByIdRequestsBySkipByTakeGet"></a>
# **v10InvestorFundsByIdRequestsBySkipByTakeGet**
> ProgramRequests v10InvestorFundsByIdRequestsBySkipByTakeGet(id, skip, take, authorization)

Get program/fund requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramRequests result = apiInstance.v10InvestorFundsByIdRequestsBySkipByTakeGet(id, skip, take, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorFundsByIdRequestsBySkipByTakeGet");
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

<a name="v10InvestorFundsByIdWithdrawByPercentPost"></a>
# **v10InvestorFundsByIdWithdrawByPercentPost**
> Void v10InvestorFundsByIdWithdrawByPercentPost(id, percent, authorization, currency)

Withdraw from fund. Percent is % of investor total money.  Withdraw in GVT if currency is empty

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
Double percent = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
String currency = "100"; // String | 
try {
    Void result = apiInstance.v10InvestorFundsByIdWithdrawByPercentPost(id, percent, authorization, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorFundsByIdWithdrawByPercentPost");
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

<a name="v10InvestorFundsByIdWithdrawInfoByCurrencyGet"></a>
# **v10InvestorFundsByIdWithdrawInfoByCurrencyGet**
> FundWithdrawInfo v10InvestorFundsByIdWithdrawInfoByCurrencyGet(id, currency, authorization)

Data for withdrawal from fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    FundWithdrawInfo result = apiInstance.v10InvestorFundsByIdWithdrawInfoByCurrencyGet(id, currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorFundsByIdWithdrawInfoByCurrencyGet");
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

[**FundWithdrawInfo**](FundWithdrawInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorFundsGet"></a>
# **v10InvestorFundsGet**
> FundsList v10InvestorFundsGet(authorization, sorting, currency, from, to, chartPointsCount, currencySecondary, actionStatus, dashboardActionStatus, skip, take)

Dashboard funds list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
String sorting = "sorting_example"; // String | 
String currency = "currency_example"; // String | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String currencySecondary = "currencySecondary_example"; // String | 
String actionStatus = "actionStatus_example"; // String | 
String dashboardActionStatus = "dashboardActionStatus_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    FundsList result = apiInstance.v10InvestorFundsGet(authorization, sorting, currency, from, to, chartPointsCount, currencySecondary, actionStatus, dashboardActionStatus, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorFundsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **sorting** | **String**|  | [optional] [enum: ByProfitAsc, ByProfitDesc, ByDrawdownAsc, ByDrawdownDesc, ByInvestorsAsc, ByInvestorsDesc, ByNewAsc, ByNewDesc, ByTitleAsc, ByTitleDesc, ByBalanceAsc, ByBalanceDesc]
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
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

<a name="v10InvestorGet"></a>
# **v10InvestorGet**
> DashboardSummary v10InvestorGet(authorization, chartCurrency, from, to, balancePoints, programsPoints, eventsTake, requestsSkip, requestsTake)

Summary dashboard info

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
String chartCurrency = "chartCurrency_example"; // String | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
Integer balancePoints = 56; // Integer | 
Integer programsPoints = 56; // Integer | 
Integer eventsTake = 56; // Integer | 
Integer requestsSkip = 56; // Integer | 
Integer requestsTake = 56; // Integer | 
try {
    DashboardSummary result = apiInstance.v10InvestorGet(authorization, chartCurrency, from, to, balancePoints, programsPoints, eventsTake, requestsSkip, requestsTake);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **chartCurrency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **balancePoints** | **Integer**|  | [optional]
 **programsPoints** | **Integer**|  | [optional]
 **eventsTake** | **Integer**|  | [optional]
 **requestsSkip** | **Integer**|  | [optional]
 **requestsTake** | **Integer**|  | [optional]

### Return type

[**DashboardSummary**](DashboardSummary.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorInvestmentsEventsGet"></a>
# **v10InvestorInvestmentsEventsGet**
> InvestmentEventViewModels v10InvestorInvestmentsEventsGet(authorization, eventLocation, assetId, from, to, eventType, assetType, skip, take)

Events

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
String eventLocation = "eventLocation_example"; // String | 
UUID assetId = new UUID(); // UUID | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
String eventType = "eventType_example"; // String | 
String assetType = "assetType_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    InvestmentEventViewModels result = apiInstance.v10InvestorInvestmentsEventsGet(authorization, eventLocation, assetId, from, to, eventType, assetType, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorInvestmentsEventsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **eventLocation** | **String**|  | [optional] [enum: Asset, Dashboard, EventsAll]
 **assetId** | [**UUID**](.md)|  | [optional]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **eventType** | **String**|  | [optional] [enum: All, AssetStarted, AssetFinished, AssetPeriodStarted, AssetPeriodEnded, AssetPeriodEndedDueToStopOut, AssetBrokerChanged, AssetInvestByInvestor, AssetWithdrawalByInvestor, AssetInvestByManager, AssetWithdrawalByManager, AssetPeriodProcessed, AssetReallocation, AssetSubscribeByInvestor, AssetUnsubscribeByInvestor, AssetTradeOpen, AssetTradeClosed, AssetSubscriptionEdit, AssetEnterInvestment]
 **assetType** | **String**|  | [optional] [enum: All, Program, Fund, Signal]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**InvestmentEventViewModels**](InvestmentEventViewModels.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorPortfolioChartGet"></a>
# **v10InvestorPortfolioChartGet**
> DashboardChartValue v10InvestorPortfolioChartGet(authorization, currency, from, to, balancePoints, programsPoints)

Portfolio charts

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
String currency = "currency_example"; // String | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
Integer balancePoints = 56; // Integer | 
Integer programsPoints = 56; // Integer | 
try {
    DashboardChartValue result = apiInstance.v10InvestorPortfolioChartGet(authorization, currency, from, to, balancePoints, programsPoints);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorPortfolioChartGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **balancePoints** | **Integer**|  | [optional]
 **programsPoints** | **Integer**|  | [optional]

### Return type

[**DashboardChartValue**](DashboardChartValue.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorPortfolioEventsGet"></a>
# **v10InvestorPortfolioEventsGet**
> DashboardPortfolioEvents v10InvestorPortfolioEventsGet(authorization, assetId, from, to, type, assetType, skip, take)

Portfolio events

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
UUID assetId = new UUID(); // UUID | 
DateTime from = new DateTime(); // DateTime | 
DateTime to = new DateTime(); // DateTime | 
String type = "type_example"; // String | 
String assetType = "assetType_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    DashboardPortfolioEvents result = apiInstance.v10InvestorPortfolioEventsGet(authorization, assetId, from, to, type, assetType, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorPortfolioEventsGet");
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
 **type** | **String**|  | [optional] [enum: All, Invest, Withdraw, Profit, Loss, Reinvest, Canceled, Ended, WithdrawByStopOut]
 **assetType** | **String**|  | [optional] [enum: All, Program, Fund, Signal]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**DashboardPortfolioEvents**](DashboardPortfolioEvents.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorProgramsByIdInvestByAmountPost"></a>
# **v10InvestorProgramsByIdInvestByAmountPost**
> Void v10InvestorProgramsByIdInvestByAmountPost(id, amount, authorization, currency)

Investing into the program.  Invest in GVT if currency is empty

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
String currency = "100"; // String | 
try {
    Void result = apiInstance.v10InvestorProgramsByIdInvestByAmountPost(id, amount, authorization, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorProgramsByIdInvestByAmountPost");
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

<a name="v10InvestorProgramsByIdInvestInfoByCurrencyGet"></a>
# **v10InvestorProgramsByIdInvestInfoByCurrencyGet**
> ProgramInvestInfo v10InvestorProgramsByIdInvestInfoByCurrencyGet(id, currency, authorization)

Data for investing into the program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramInvestInfo result = apiInstance.v10InvestorProgramsByIdInvestInfoByCurrencyGet(id, currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorProgramsByIdInvestInfoByCurrencyGet");
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

<a name="v10InvestorProgramsByIdReinvestOffPost"></a>
# **v10InvestorProgramsByIdReinvestOffPost**
> Void v10InvestorProgramsByIdReinvestOffPost(id, authorization)

Disable reinvesting

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10InvestorProgramsByIdReinvestOffPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorProgramsByIdReinvestOffPost");
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

<a name="v10InvestorProgramsByIdReinvestOnPost"></a>
# **v10InvestorProgramsByIdReinvestOnPost**
> Void v10InvestorProgramsByIdReinvestOnPost(id, authorization)

Enable reinvesting

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10InvestorProgramsByIdReinvestOnPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorProgramsByIdReinvestOnPost");
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

<a name="v10InvestorProgramsByIdRequestsBySkipByTakeGet"></a>
# **v10InvestorProgramsByIdRequestsBySkipByTakeGet**
> ProgramRequests v10InvestorProgramsByIdRequestsBySkipByTakeGet(id, skip, take, authorization)

Get program/fund requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramRequests result = apiInstance.v10InvestorProgramsByIdRequestsBySkipByTakeGet(id, skip, take, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorProgramsByIdRequestsBySkipByTakeGet");
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

<a name="v10InvestorProgramsByIdWithdrawByAmountPost"></a>
# **v10InvestorProgramsByIdWithdrawByAmountPost**
> Void v10InvestorProgramsByIdWithdrawByAmountPost(id, amount, authorization)

Withdraw from investment program in GVT

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10InvestorProgramsByIdWithdrawByAmountPost(id, amount, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorProgramsByIdWithdrawByAmountPost");
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

<a name="v10InvestorProgramsByIdWithdrawInfoByCurrencyGet"></a>
# **v10InvestorProgramsByIdWithdrawInfoByCurrencyGet**
> ProgramWithdrawInfo v10InvestorProgramsByIdWithdrawInfoByCurrencyGet(id, currency, authorization)

Data for withdrawal from investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramWithdrawInfo result = apiInstance.v10InvestorProgramsByIdWithdrawInfoByCurrencyGet(id, currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorProgramsByIdWithdrawInfoByCurrencyGet");
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

[**ProgramWithdrawInfo**](ProgramWithdrawInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorProgramsByIdWithdrawMultiByAmountPost"></a>
# **v10InvestorProgramsByIdWithdrawMultiByAmountPost**
> Void v10InvestorProgramsByIdWithdrawMultiByAmountPost(id, amount, authorization, withdrawAll)

Withdraw from investment program in program currency

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
Boolean withdrawAll = false; // Boolean | 
try {
    Void result = apiInstance.v10InvestorProgramsByIdWithdrawMultiByAmountPost(id, amount, authorization, withdrawAll);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorProgramsByIdWithdrawMultiByAmountPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **amount** | **Double**|  |
 **authorization** | **String**| JWT access token |
 **withdrawAll** | **Boolean**|  | [optional] [default to false]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorProgramsByIdWithdrawMultiPost"></a>
# **v10InvestorProgramsByIdWithdrawMultiPost**
> Void v10InvestorProgramsByIdWithdrawMultiPost(id, authorization, amount, withdrawAll)

Withdraw from investment program in program currency

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
Double amount = 3.4D; // Double | 
Boolean withdrawAll = false; // Boolean | 
try {
    Void result = apiInstance.v10InvestorProgramsByIdWithdrawMultiPost(id, authorization, amount, withdrawAll);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorProgramsByIdWithdrawMultiPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **amount** | **Double**|  | [optional]
 **withdrawAll** | **Boolean**|  | [optional] [default to false]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorProgramsGet"></a>
# **v10InvestorProgramsGet**
> ProgramsList v10InvestorProgramsGet(authorization, sorting, from, to, chartPointsCount, currencySecondary, actionStatus, dashboardActionStatus, skip, take)

Dashboard program list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
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
    ProgramsList result = apiInstance.v10InvestorProgramsGet(authorization, sorting, from, to, chartPointsCount, currencySecondary, actionStatus, dashboardActionStatus, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorProgramsGet");
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

<a name="v10InvestorProgramsRequestsByIdCancelPost"></a>
# **v10InvestorProgramsRequestsByIdCancelPost**
> Void v10InvestorProgramsRequestsByIdCancelPost(id, authorization)

Cancel investment program request

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10InvestorProgramsRequestsByIdCancelPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorProgramsRequestsByIdCancelPost");
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

<a name="v10InvestorRequestsBySkipByTakeGet"></a>
# **v10InvestorRequestsBySkipByTakeGet**
> ProgramRequests v10InvestorRequestsBySkipByTakeGet(skip, take, authorization)

Get all requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramRequests result = apiInstance.v10InvestorRequestsBySkipByTakeGet(skip, take, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorRequestsBySkipByTakeGet");
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

<a name="v10InvestorSignalsGet"></a>
# **v10InvestorSignalsGet**
> SignalsList v10InvestorSignalsGet(authorization, sorting, from, to, chartPointsCount, currencySecondary, actionStatus, dashboardActionStatus, skip, take)

Dashboard signal providers list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
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
    SignalsList result = apiInstance.v10InvestorSignalsGet(authorization, sorting, from, to, chartPointsCount, currencySecondary, actionStatus, dashboardActionStatus, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#v10InvestorSignalsGet");
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

[**SignalsList**](SignalsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

