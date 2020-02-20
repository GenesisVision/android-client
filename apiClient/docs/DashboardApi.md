# DashboardApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getChart**](DashboardApi.md#getChart) | **GET** v2.0/dashboard/chart | 
[**getChartAssets**](DashboardApi.md#getChartAssets) | **GET** v2.0/dashboard/chart/assets | Active assets for chart
[**getHoldings**](DashboardApi.md#getHoldings) | **GET** v2.0/dashboard/holdings | 
[**getInvestingDetails**](DashboardApi.md#getInvestingDetails) | **GET** v2.0/dashboard/investing | 
[**getInvestingFunds**](DashboardApi.md#getInvestingFunds) | **GET** v2.0/dashboard/investing/funds | 
[**getInvestingPrograms**](DashboardApi.md#getInvestingPrograms) | **GET** v2.0/dashboard/investing/programs | 
[**getMostProfitableAssets**](DashboardApi.md#getMostProfitableAssets) | **GET** v2.0/dashboard/trading/mostprofitable | 
[**getPortfolio**](DashboardApi.md#getPortfolio) | **GET** v2.0/dashboard/portfolio | Money distribution in percents
[**getPrivateTradingAssets**](DashboardApi.md#getPrivateTradingAssets) | **GET** v2.0/dashboard/trading/private | 
[**getPublicTradingAssets**](DashboardApi.md#getPublicTradingAssets) | **GET** v2.0/dashboard/trading/public | 
[**getRecommendations**](DashboardApi.md#getRecommendations) | **GET** v2.0/dashboard/recommendations | Recommended assets to invest (programs, funds and follows). Funds in passed currency
[**getSummary**](DashboardApi.md#getSummary) | **GET** v2.0/dashboard/summary | 
[**getTradingDetails**](DashboardApi.md#getTradingDetails) | **GET** v2.0/dashboard/trading | 

<a name="getChart"></a>
# **getChart**
> DashboardChart getChart(authorization, assets, dateFrom, dateTo, chartPointsCount, showIn)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
List<UUID> assets = Arrays.asList(new UUID()); // List<UUID> | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String showIn = "showIn_example"; // String | 
try {
    DashboardChart result = apiInstance.getChart(authorization, assets, dateFrom, dateTo, chartPointsCount, showIn);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getChart");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **assets** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **showIn** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**DashboardChart**](DashboardChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getChartAssets"></a>
# **getChartAssets**
> DashboardChartAssets getChartAssets(authorization)

Active assets for chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    DashboardChartAssets result = apiInstance.getChartAssets(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getChartAssets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**DashboardChartAssets**](DashboardChartAssets.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getHoldings"></a>
# **getHoldings**
> DashboardAssets getHoldings(authorization, topAssetsCount)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
Integer topAssetsCount = 56; // Integer | 
try {
    DashboardAssets result = apiInstance.getHoldings(authorization, topAssetsCount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getHoldings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **topAssetsCount** | **Integer**|  | [optional]

### Return type

[**DashboardAssets**](DashboardAssets.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getInvestingDetails"></a>
# **getInvestingDetails**
> DashboardInvestingDetails getInvestingDetails(authorization, currency, eventsTake)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
String currency = "currency_example"; // String | 
Integer eventsTake = 56; // Integer | 
try {
    DashboardInvestingDetails result = apiInstance.getInvestingDetails(authorization, currency, eventsTake);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getInvestingDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **eventsTake** | **Integer**|  | [optional]

### Return type

[**DashboardInvestingDetails**](DashboardInvestingDetails.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getInvestingFunds"></a>
# **getInvestingFunds**
> ItemsViewModelFundInvestingDetailsList getInvestingFunds(authorization, sorting, showIn, status, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
String sorting = "sorting_example"; // String | 
String showIn = "showIn_example"; // String | 
String status = "status_example"; // String | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String facetId = "facetId_example"; // String | 
String mask = "mask_example"; // String | 
UUID ownerId = new UUID(); // UUID | 
Boolean showFavorites = true; // Boolean | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ItemsViewModelFundInvestingDetailsList result = apiInstance.getInvestingFunds(authorization, sorting, showIn, status, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getInvestingFunds");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **sorting** | **String**|  | [optional] [enum: ByTitleAsc, ByTitleDesc, BySizeAsc, BySizeDesc, ByInvestorsAsc, ByInvestorsDesc, ByDrawdownAsc, ByDrawdownDesc, ByProfitAsc, ByProfitDesc, ByNewAsc, ByNewDesc, ByValueAsc, ByValueDesc]
 **showIn** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **status** | **String**|  | [optional] [enum: All, Active]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **facetId** | **String**|  | [optional]
 **mask** | **String**|  | [optional]
 **ownerId** | [**UUID**](.md)|  | [optional]
 **showFavorites** | **Boolean**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelFundInvestingDetailsList**](ItemsViewModelFundInvestingDetailsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getInvestingPrograms"></a>
# **getInvestingPrograms**
> ItemsViewModelProgramInvestingDetailsList getInvestingPrograms(authorization, sorting, showIn, status, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
String sorting = "sorting_example"; // String | 
String showIn = "showIn_example"; // String | 
String status = "status_example"; // String | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String facetId = "facetId_example"; // String | 
String mask = "mask_example"; // String | 
UUID ownerId = new UUID(); // UUID | 
Boolean showFavorites = true; // Boolean | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ItemsViewModelProgramInvestingDetailsList result = apiInstance.getInvestingPrograms(authorization, sorting, showIn, status, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getInvestingPrograms");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **sorting** | **String**|  | [optional] [enum: ByTitleAsc, ByTitleDesc, ByEquityAsc, ByEquityDesc, ByInvestorsAsc, ByInvestorsDesc, ByPeriodAsc, ByPeriodDesc, ByDrawdownAsc, ByDrawdownDesc, ByProfitAsc, ByProfitDesc, ByNewAsc, ByNewDesc, ByLevelProgressAsc, ByLevelProgressDesc, ByLevelAsc, ByLevelDesc, ByValueAsc, ByValueDesc]
 **showIn** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **status** | **String**|  | [optional] [enum: All, Active]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **facetId** | **String**|  | [optional]
 **mask** | **String**|  | [optional]
 **ownerId** | [**UUID**](.md)|  | [optional]
 **showFavorites** | **Boolean**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelProgramInvestingDetailsList**](ItemsViewModelProgramInvestingDetailsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getMostProfitableAssets"></a>
# **getMostProfitableAssets**
> ItemsViewModelDashboardTradingAsset getMostProfitableAssets(authorization, dateFrom, dateTo, chartPointsCount, showIn)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String showIn = "showIn_example"; // String | 
try {
    ItemsViewModelDashboardTradingAsset result = apiInstance.getMostProfitableAssets(authorization, dateFrom, dateTo, chartPointsCount, showIn);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getMostProfitableAssets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **showIn** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**ItemsViewModelDashboardTradingAsset**](ItemsViewModelDashboardTradingAsset.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getPortfolio"></a>
# **getPortfolio**
> DashboardPortfolio getPortfolio(authorization)

Money distribution in percents

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    DashboardPortfolio result = apiInstance.getPortfolio(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getPortfolio");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**DashboardPortfolio**](DashboardPortfolio.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getPrivateTradingAssets"></a>
# **getPrivateTradingAssets**
> ItemsViewModelDashboardTradingAsset getPrivateTradingAssets(authorization, dateFrom, dateTo, chartPointsCount, showIn, status, skip, take)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String showIn = "showIn_example"; // String | 
String status = "status_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ItemsViewModelDashboardTradingAsset result = apiInstance.getPrivateTradingAssets(authorization, dateFrom, dateTo, chartPointsCount, showIn, status, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getPrivateTradingAssets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **showIn** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **status** | **String**|  | [optional] [enum: All, Active]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelDashboardTradingAsset**](ItemsViewModelDashboardTradingAsset.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getPublicTradingAssets"></a>
# **getPublicTradingAssets**
> ItemsViewModelDashboardTradingAsset getPublicTradingAssets(authorization, dateFrom, dateTo, chartPointsCount, showIn, status, skip, take)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String showIn = "showIn_example"; // String | 
String status = "status_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ItemsViewModelDashboardTradingAsset result = apiInstance.getPublicTradingAssets(authorization, dateFrom, dateTo, chartPointsCount, showIn, status, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getPublicTradingAssets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **showIn** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **status** | **String**|  | [optional] [enum: All, Active]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelDashboardTradingAsset**](ItemsViewModelDashboardTradingAsset.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getRecommendations"></a>
# **getRecommendations**
> CommonPublicAssetsViewModel getRecommendations(authorization, currency, take, onlyFollows)

Recommended assets to invest (programs, funds and follows). Funds in passed currency

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
String currency = "currency_example"; // String | 
Integer take = 56; // Integer | 
Boolean onlyFollows = true; // Boolean | 
try {
    CommonPublicAssetsViewModel result = apiInstance.getRecommendations(authorization, currency, take, onlyFollows);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getRecommendations");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **take** | **Integer**|  | [optional]
 **onlyFollows** | **Boolean**|  | [optional]

### Return type

[**CommonPublicAssetsViewModel**](CommonPublicAssetsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getSummary"></a>
# **getSummary**
> DashboardSummary getSummary(authorization, currency)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
String currency = "currency_example"; // String | 
try {
    DashboardSummary result = apiInstance.getSummary(authorization, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getSummary");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**DashboardSummary**](DashboardSummary.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getTradingDetails"></a>
# **getTradingDetails**
> DashboardTradingDetails getTradingDetails(authorization, currency, eventsTake)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DashboardApi;


DashboardApi apiInstance = new DashboardApi();
String authorization = "authorization_example"; // String | JWT access token
String currency = "currency_example"; // String | 
Integer eventsTake = 56; // Integer | 
try {
    DashboardTradingDetails result = apiInstance.getTradingDetails(authorization, currency, eventsTake);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DashboardApi#getTradingDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]
 **eventsTake** | **Integer**|  | [optional]

### Return type

[**DashboardTradingDetails**](DashboardTradingDetails.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

