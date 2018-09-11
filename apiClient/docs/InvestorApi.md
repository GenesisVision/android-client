# InvestorApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10InvestorGet**](InvestorApi.md#v10InvestorGet) | **GET** v1.0/investor | Summary dashdoard info
[**v10InvestorPortfolioChartGet**](InvestorApi.md#v10InvestorPortfolioChartGet) | **GET** v1.0/investor/portfolio/chart | Portfolio charts
[**v10InvestorPortfolioEventsGet**](InvestorApi.md#v10InvestorPortfolioEventsGet) | **GET** v1.0/investor/portfolio/events | Portfolio events
[**v10InvestorProgramsByIdInvestByAmountPost**](InvestorApi.md#v10InvestorProgramsByIdInvestByAmountPost) | **POST** v1.0/investor/programs/{id}/invest/{amount} | investing
[**v10InvestorProgramsByIdInvestInfoByCurrencyGet**](InvestorApi.md#v10InvestorProgramsByIdInvestInfoByCurrencyGet) | **GET** v1.0/investor/programs/{id}/invest/info/{currency} | Data for investing
[**v10InvestorProgramsByIdReinvestOffPost**](InvestorApi.md#v10InvestorProgramsByIdReinvestOffPost) | **POST** v1.0/investor/programs/{id}/reinvest/off | Disable reinvesting
[**v10InvestorProgramsByIdReinvestOnPost**](InvestorApi.md#v10InvestorProgramsByIdReinvestOnPost) | **POST** v1.0/investor/programs/{id}/reinvest/on | Enable reinvesting
[**v10InvestorProgramsByIdRequestsBySkipByTakeGet**](InvestorApi.md#v10InvestorProgramsByIdRequestsBySkipByTakeGet) | **GET** v1.0/investor/programs/{id}/requests/{skip}/{take} | Get requests
[**v10InvestorProgramsByIdWithdrawByAmountPost**](InvestorApi.md#v10InvestorProgramsByIdWithdrawByAmountPost) | **POST** v1.0/investor/programs/{id}/withdraw/{amount} | Withdrawal
[**v10InvestorProgramsByIdWithdrawInfoByCurrencyGet**](InvestorApi.md#v10InvestorProgramsByIdWithdrawInfoByCurrencyGet) | **GET** v1.0/investor/programs/{id}/withdraw/info/{currency} | Data for withdrawal
[**v10InvestorProgramsGet**](InvestorApi.md#v10InvestorProgramsGet) | **GET** v1.0/investor/programs | Programs list
[**v10InvestorProgramsRequestsByIdCancelPost**](InvestorApi.md#v10InvestorProgramsRequestsByIdCancelPost) | **POST** v1.0/investor/programs/requests/{id}/cancel | Cancel request


<a name="v10InvestorGet"></a>
# **v10InvestorGet**
> DashboardSummary v10InvestorGet(authorization, assetId, from, to, type, assetType, skip, take, chartCurrency, chartFrom, chartTo)

Summary dashdoard info

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
String chartCurrency = "chartCurrency_example"; // String | 
DateTime chartFrom = new DateTime(); // DateTime | 
DateTime chartTo = new DateTime(); // DateTime | 
try {
    DashboardSummary result = apiInstance.v10InvestorGet(authorization, assetId, from, to, type, assetType, skip, take, chartCurrency, chartFrom, chartTo);
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
 **assetId** | [**UUID**](.md)|  | [optional]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
 **type** | **String**|  | [optional] [enum: All, Invest, Withdraw, Profit, Loss, Reinvest, Canceled, Ended]
 **assetType** | **String**|  | [optional] [enum: All, Program, Fund]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]
 **chartCurrency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USD, EUR]
 **chartFrom** | **DateTime**|  | [optional]
 **chartTo** | **DateTime**|  | [optional]

### Return type

[**DashboardSummary**](DashboardSummary.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorPortfolioChartGet"></a>
# **v10InvestorPortfolioChartGet**
> DashboardChartValue v10InvestorPortfolioChartGet(authorization, currency, from, to)

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
try {
    DashboardChartValue result = apiInstance.v10InvestorPortfolioChartGet(authorization, currency, from, to);
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
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USD, EUR]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]

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
 **type** | **String**|  | [optional] [enum: All, Invest, Withdraw, Profit, Loss, Reinvest, Canceled, Ended]
 **assetType** | **String**|  | [optional] [enum: All, Program, Fund]
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
> Void v10InvestorProgramsByIdInvestByAmountPost(id, amount, authorization)

investing

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
    Void result = apiInstance.v10InvestorProgramsByIdInvestByAmountPost(id, amount, authorization);
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

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorProgramsByIdInvestInfoByCurrencyGet"></a>
# **v10InvestorProgramsByIdInvestInfoByCurrencyGet**
> InvestInfo v10InvestorProgramsByIdInvestInfoByCurrencyGet(id, currency, authorization)

Data for investing

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
    InvestInfo result = apiInstance.v10InvestorProgramsByIdInvestInfoByCurrencyGet(id, currency, authorization);
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
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USD, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**InvestInfo**](InvestInfo.md)

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

Get requests

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

Withdrawal

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
> WithdrawInfo v10InvestorProgramsByIdWithdrawInfoByCurrencyGet(id, currency, authorization)

Data for withdrawal

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
    WithdrawInfo result = apiInstance.v10InvestorProgramsByIdWithdrawInfoByCurrencyGet(id, currency, authorization);
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
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USD, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**WithdrawInfo**](WithdrawInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10InvestorProgramsGet"></a>
# **v10InvestorProgramsGet**
> ProgramsList v10InvestorProgramsGet(authorization, sorting, from, to, skip, take)

Programs list

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
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ProgramsList result = apiInstance.v10InvestorProgramsGet(authorization, sorting, from, to, skip, take);
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
 **sorting** | **String**|  | [optional] [enum: ByLevelAsc, ByLevelDesc, ByProfitAsc, ByProfitDesc, ByDrawdownAsc, ByDrawdownDesc, ByTradesAsc, ByTradesDesc, ByInvestorsAsc, ByInvestorsDesc, ByEndOfPeriodAsc, ByEndOfPeriodDesc, ByTitleAsc, ByTitleDesc, ByBalanceAsc, ByBalanceDesc]
 **from** | **DateTime**|  | [optional]
 **to** | **DateTime**|  | [optional]
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

Cancel request

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

