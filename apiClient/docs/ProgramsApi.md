# ProgramsApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addToFavorites**](ProgramsApi.md#addToFavorites) | **POST** v2.0/programs/{id}/favorite/add | Add to favorites
[**exportProgramPeriods**](ProgramsApi.md#exportProgramPeriods) | **GET** v2.0/programs/{id}/periods/export | Export periods
[**exportProgramPeriodsFinStatistic**](ProgramsApi.md#exportProgramPeriodsFinStatistic) | **GET** v2.0/programs/{id}/periods/export/statistic | Export period financial statistic
[**exportProgramTrades**](ProgramsApi.md#exportProgramTrades) | **GET** v2.0/programs/{id}/trades/export | Export trade history
[**getProgramAbsoluteProfitChart**](ProgramsApi.md#getProgramAbsoluteProfitChart) | **GET** v2.0/programs/{id}/charts/profit/absolute | Program absolute profit chart
[**getProgramBalanceChart**](ProgramsApi.md#getProgramBalanceChart) | **GET** v2.0/programs/{id}/charts/balance | Program balance chart
[**getProgramDetails**](ProgramsApi.md#getProgramDetails) | **GET** v2.0/programs/{id} | Program details
[**getProgramOpenTrades**](ProgramsApi.md#getProgramOpenTrades) | **GET** v2.0/programs/{id}/trades/open | Open positions
[**getProgramPeriods**](ProgramsApi.md#getProgramPeriods) | **GET** v2.0/programs/{id}/periods | Program periods
[**getProgramProfitPercentCharts**](ProgramsApi.md#getProgramProfitPercentCharts) | **GET** v2.0/programs/{id}/charts/profit/percent | Program profit percent charts
[**getProgramSubscribers**](ProgramsApi.md#getProgramSubscribers) | **GET** v2.0/programs/{id}/subscribers | Signal subscribers
[**getProgramTrades**](ProgramsApi.md#getProgramTrades) | **GET** v2.0/programs/{id}/trades | Trade history
[**getPrograms**](ProgramsApi.md#getPrograms) | **GET** v2.0/programs | Programs list
[**removeFromFavorites**](ProgramsApi.md#removeFromFavorites) | **POST** v2.0/programs/{id}/favorite/remove | Remove from favorites

<a name="addToFavorites"></a>
# **addToFavorites**
> Void addToFavorites(id, authorization)

Add to favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.addToFavorites(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#addToFavorites");
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

<a name="exportProgramPeriods"></a>
# **exportProgramPeriods**
> String exportProgramPeriods(id, dateFrom, dateTo, numberMin, numberMax, status, skip, take)

Export periods

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
String id = "id_example"; // String | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer numberMin = 56; // Integer | 
Integer numberMax = 56; // Integer | 
String status = "status_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    String result = apiInstance.exportProgramPeriods(id, dateFrom, dateTo, numberMin, numberMax, status, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#exportProgramPeriods");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **numberMin** | **Integer**|  | [optional]
 **numberMax** | **Integer**|  | [optional]
 **status** | **String**|  | [optional] [enum: Planned, InProccess, Closed]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="exportProgramPeriodsFinStatistic"></a>
# **exportProgramPeriodsFinStatistic**
> String exportProgramPeriodsFinStatistic(id, authorization, dateFrom, dateTo, numberMin, numberMax, status, skip, take)

Export period financial statistic

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
String id = "id_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer numberMin = 56; // Integer | 
Integer numberMax = 56; // Integer | 
String status = "status_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    String result = apiInstance.exportProgramPeriodsFinStatistic(id, authorization, dateFrom, dateTo, numberMin, numberMax, status, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#exportProgramPeriodsFinStatistic");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **numberMin** | **Integer**|  | [optional]
 **numberMax** | **Integer**|  | [optional]
 **status** | **String**|  | [optional] [enum: Planned, InProccess, Closed]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="exportProgramTrades"></a>
# **exportProgramTrades**
> String exportProgramTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take)

Export trade history

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String symbol = "symbol_example"; // String | 
String sorting = "sorting_example"; // String | 
UUID accountId = new UUID(); // UUID | 
String accountCurrency = "accountCurrency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    String result = apiInstance.exportProgramTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#exportProgramTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **symbol** | **String**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc, ByCommissionAsc, ByCommissionDesc, BySwapAsc, BySwapDesc]
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramAbsoluteProfitChart"></a>
# **getProgramAbsoluteProfitChart**
> AbsoluteProfitChart getProgramAbsoluteProfitChart(id, dateFrom, dateTo, maxPointCount, currency)

Program absolute profit chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
String currency = "currency_example"; // String | 
try {
    AbsoluteProfitChart result = apiInstance.getProgramAbsoluteProfitChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramAbsoluteProfitChart");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **maxPointCount** | **Integer**|  | [optional]
 **currency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**AbsoluteProfitChart**](AbsoluteProfitChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramBalanceChart"></a>
# **getProgramBalanceChart**
> ProgramBalanceChart getProgramBalanceChart(id, dateFrom, dateTo, maxPointCount, currency)

Program balance chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
String currency = "currency_example"; // String | 
try {
    ProgramBalanceChart result = apiInstance.getProgramBalanceChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramBalanceChart");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **maxPointCount** | **Integer**|  | [optional]
 **currency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**ProgramBalanceChart**](ProgramBalanceChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramDetails"></a>
# **getProgramDetails**
> ProgramFollowDetailsFull getProgramDetails(id, authorization)

Program details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
String id = "id_example"; // String | 
String authorization = "authorization_example"; // String | 
try {
    ProgramFollowDetailsFull result = apiInstance.getProgramDetails(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **authorization** | **String**|  | [optional]

### Return type

[**ProgramFollowDetailsFull**](ProgramFollowDetailsFull.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramOpenTrades"></a>
# **getProgramOpenTrades**
> TradesViewModel getProgramOpenTrades(id, sorting, symbol, accountId, accountCurrency, skip, take)

Open positions

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
String sorting = "sorting_example"; // String | 
String symbol = "symbol_example"; // String | 
UUID accountId = new UUID(); // UUID | 
String accountCurrency = "accountCurrency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesViewModel result = apiInstance.getProgramOpenTrades(id, sorting, symbol, accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramOpenTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc, ByCommissionAsc, ByCommissionDesc, BySwapAsc, BySwapDesc]
 **symbol** | **String**|  | [optional]
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesViewModel**](TradesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramPeriods"></a>
# **getProgramPeriods**
> ProgramPeriodsViewModel getProgramPeriods(id, authorization, dateFrom, dateTo, numberMin, numberMax, status, skip, take)

Program periods

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
String id = "id_example"; // String | 
String authorization = "authorization_example"; // String | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer numberMin = 56; // Integer | 
Integer numberMax = 56; // Integer | 
String status = "status_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ProgramPeriodsViewModel result = apiInstance.getProgramPeriods(id, authorization, dateFrom, dateTo, numberMin, numberMax, status, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramPeriods");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **authorization** | **String**|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **numberMin** | **Integer**|  | [optional]
 **numberMax** | **Integer**|  | [optional]
 **status** | **String**|  | [optional] [enum: Planned, InProccess, Closed]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ProgramPeriodsViewModel**](ProgramPeriodsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramProfitPercentCharts"></a>
# **getProgramProfitPercentCharts**
> ProgramProfitPercentCharts getProgramProfitPercentCharts(id, authorization, dateFrom, dateTo, maxPointCount, currency, currencies)

Program profit percent charts

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
String currency = "currency_example"; // String | 
List<Object> currencies = null; // List<Object> | 
try {
    ProgramProfitPercentCharts result = apiInstance.getProgramProfitPercentCharts(id, authorization, dateFrom, dateTo, maxPointCount, currency, currencies);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramProfitPercentCharts");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**|  | [optional]
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **maxPointCount** | **Integer**|  | [optional]
 **currency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
 **currencies** | [**List&lt;Object&gt;**](Object.md)|  | [optional]

### Return type

[**ProgramProfitPercentCharts**](ProgramProfitPercentCharts.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramSubscribers"></a>
# **getProgramSubscribers**
> SignalProviderSubscribers getProgramSubscribers(id, authorization, status, skip, take)

Signal subscribers

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
String status = "status_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    SignalProviderSubscribers result = apiInstance.getProgramSubscribers(id, authorization, status, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramSubscribers");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **status** | **String**|  | [optional] [enum: All, Active]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**SignalProviderSubscribers**](SignalProviderSubscribers.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramTrades"></a>
# **getProgramTrades**
> TradesViewModel getProgramTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take)

Trade history

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
String symbol = "symbol_example"; // String | 
String sorting = "sorting_example"; // String | 
UUID accountId = new UUID(); // UUID | 
String accountCurrency = "accountCurrency_example"; // String | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    TradesViewModel result = apiInstance.getProgramTrades(id, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getProgramTrades");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **symbol** | **String**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByDateAsc, ByDateDesc, ByTicketAsc, ByTicketDesc, BySymbolAsc, BySymbolDesc, ByDirectionAsc, ByDirectionDesc, ByVolumeAsc, ByVolumeDesc, ByPriceAsc, ByPriceDesc, ByPriceCurrentAsc, ByPriceCurrentDesc, ByProfitAsc, ByProfitDesc, ByCommissionAsc, ByCommissionDesc, BySwapAsc, BySwapDesc]
 **accountId** | [**UUID**](.md)|  | [optional]
 **accountCurrency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**TradesViewModel**](TradesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getPrograms"></a>
# **getPrograms**
> ItemsViewModelProgramDetailsListItem getPrograms(authorization, sorting, showIn, tags, programCurrency, levelMin, levelMax, levelsSet, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take)

Programs list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
String authorization = "authorization_example"; // String | 
String sorting = "sorting_example"; // String | 
String showIn = "showIn_example"; // String | 
List<String> tags = Arrays.asList("tags_example"); // List<String> | 
String programCurrency = "programCurrency_example"; // String | 
Integer levelMin = 56; // Integer | 
Integer levelMax = 56; // Integer | 
List<Integer> levelsSet = Arrays.asList(56); // List<Integer> | 
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
    ItemsViewModelProgramDetailsListItem result = apiInstance.getPrograms(authorization, sorting, showIn, tags, programCurrency, levelMin, levelMax, levelsSet, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#getPrograms");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByTitleAsc, ByTitleDesc, ByEquityAsc, ByEquityDesc, ByInvestorsAsc, ByInvestorsDesc, ByPeriodAsc, ByPeriodDesc, ByDrawdownAsc, ByDrawdownDesc, ByProfitAsc, ByProfitDesc, ByNewAsc, ByNewDesc, ByLevelProgressAsc, ByLevelProgressDesc, ByLevelAsc, ByLevelDesc, ByValueAsc, ByValueDesc]
 **showIn** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
 **tags** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **programCurrency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
 **levelMin** | **Integer**|  | [optional]
 **levelMax** | **Integer**|  | [optional]
 **levelsSet** | [**List&lt;Integer&gt;**](Integer.md)|  | [optional]
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

[**ItemsViewModelProgramDetailsListItem**](ItemsViewModelProgramDetailsListItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="removeFromFavorites"></a>
# **removeFromFavorites**
> Void removeFromFavorites(id, authorization)

Remove from favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramsApi;


ProgramsApi apiInstance = new ProgramsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.removeFromFavorites(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramsApi#removeFromFavorites");
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

