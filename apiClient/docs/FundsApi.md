# FundsApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addToFavorites**](FundsApi.md#addToFavorites) | **POST** v2.0/funds/{id}/favorite/add | Add to favorites
[**getFundAbsoluteProfitChart**](FundsApi.md#getFundAbsoluteProfitChart) | **GET** v2.0/funds/{id}/charts/profit/absolute | Fund absolute profit chart
[**getFundBalanceChart**](FundsApi.md#getFundBalanceChart) | **GET** v2.0/funds/{id}/charts/balance | Fund balance chart
[**getFundDetails**](FundsApi.md#getFundDetails) | **GET** v2.0/funds/{id} | Fund details
[**getFundProfitPercentCharts**](FundsApi.md#getFundProfitPercentCharts) | **GET** v2.0/funds/{id}/charts/profit/percent | Fund profit percent charts
[**getFunds**](FundsApi.md#getFunds) | **GET** v2.0/funds | Funds list
[**getReallocatingHistory**](FundsApi.md#getReallocatingHistory) | **GET** v2.0/funds/{id}/reallocations | Get history of asset part update requests
[**removeFromFavorites**](FundsApi.md#removeFromFavorites) | **POST** v2.0/funds/{id}/favorite/remove | Remove from favorites

<a name="addToFavorites"></a>
# **addToFavorites**
> Void addToFavorites(id, authorization)

Add to favorites

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.addToFavorites(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#addToFavorites");
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

<a name="getFundAbsoluteProfitChart"></a>
# **getFundAbsoluteProfitChart**
> AbsoluteProfitChart getFundAbsoluteProfitChart(id, dateFrom, dateTo, maxPointCount, currency)

Fund absolute profit chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
String currency = "currency_example"; // String | 
try {
    AbsoluteProfitChart result = apiInstance.getFundAbsoluteProfitChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getFundAbsoluteProfitChart");
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

<a name="getFundBalanceChart"></a>
# **getFundBalanceChart**
> FundBalanceChart getFundBalanceChart(id, dateFrom, dateTo, maxPointCount, currency)

Fund balance chart

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
String currency = "currency_example"; // String | 
try {
    FundBalanceChart result = apiInstance.getFundBalanceChart(id, dateFrom, dateTo, maxPointCount, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getFundBalanceChart");
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

[**FundBalanceChart**](FundBalanceChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFundDetails"></a>
# **getFundDetails**
> FundDetailsFull getFundDetails(id, authorization, currency)

Fund details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
String id = "id_example"; // String | 
String authorization = "authorization_example"; // String | 
String currency = "currency_example"; // String | 
try {
    FundDetailsFull result = apiInstance.getFundDetails(id, authorization, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getFundDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **authorization** | **String**|  | [optional]
 **currency** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**FundDetailsFull**](FundDetailsFull.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFundProfitPercentCharts"></a>
# **getFundProfitPercentCharts**
> FundProfitPercentCharts getFundProfitPercentCharts(id, dateFrom, dateTo, maxPointCount, currency, currencies, chartAssetsCount)

Fund profit percent charts

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer maxPointCount = 56; // Integer | 
String currency = "currency_example"; // String | 
List<Object> currencies = null; // List<Object> | 
Integer chartAssetsCount = 56; // Integer | 
try {
    FundProfitPercentCharts result = apiInstance.getFundProfitPercentCharts(id, dateFrom, dateTo, maxPointCount, currency, currencies, chartAssetsCount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getFundProfitPercentCharts");
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
 **currencies** | [**List&lt;Object&gt;**](Object.md)|  | [optional]
 **chartAssetsCount** | **Integer**|  | [optional]

### Return type

[**FundProfitPercentCharts**](FundProfitPercentCharts.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFunds"></a>
# **getFunds**
> ItemsViewModelFundDetailsListItem getFunds(authorization, sorting, showIn, assets, includeWithInvestments, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take)

Funds list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
String authorization = "authorization_example"; // String | 
String sorting = "sorting_example"; // String | 
String showIn = "showIn_example"; // String | 
List<String> assets = Arrays.asList("assets_example"); // List<String> | 
Boolean includeWithInvestments = true; // Boolean | 
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
    ItemsViewModelFundDetailsListItem result = apiInstance.getFunds(authorization, sorting, showIn, assets, includeWithInvestments, dateFrom, dateTo, chartPointsCount, facetId, mask, ownerId, showFavorites, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getFunds");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByTitleAsc, ByTitleDesc, BySizeAsc, BySizeDesc, ByInvestorsAsc, ByInvestorsDesc, ByDrawdownAsc, ByDrawdownDesc, ByProfitAsc, ByProfitDesc, ByNewAsc, ByNewDesc, ByValueAsc, ByValueDesc]
 **showIn** | **String**|  | [optional] [enum: USD, BTC, ETH, USDT, GVT, Undefined, ADA, XRP, BCH, LTC, DOGE, BNB, EUR]
 **assets** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **includeWithInvestments** | **Boolean**|  | [optional]
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

[**ItemsViewModelFundDetailsListItem**](ItemsViewModelFundDetailsListItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getReallocatingHistory"></a>
# **getReallocatingHistory**
> ItemsViewModelReallocationModel getReallocatingHistory(id, dateFrom, dateTo, skip, take)

Get history of asset part update requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ItemsViewModelReallocationModel result = apiInstance.getReallocatingHistory(id, dateFrom, dateTo, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getReallocatingHistory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelReallocationModel**](ItemsViewModelReallocationModel.md)

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
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.removeFromFavorites(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#removeFromFavorites");
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

