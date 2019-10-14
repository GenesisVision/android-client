# FundsApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getFundBalanceChart**](FundsApi.md#getFundBalanceChart) | **GET** v2.0/funds/{id}/charts/balance | Fund balance chart
[**getFundProfitChart**](FundsApi.md#getFundProfitChart) | **GET** v2.0/funds/{id}/charts/profit | Fund profit chart

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
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]

### Return type

[**FundBalanceChart**](FundBalanceChart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFundProfitChart"></a>
# **getFundProfitChart**
> FundProfitCharts getFundProfitChart(id, dateFrom, dateTo, maxPointCount, currency, currencies, chartAssetsCount)

Fund profit chart

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
List<String> currencies = Arrays.asList("currencies_example"); // List<String> | 
Integer chartAssetsCount = 56; // Integer | 
try {
    FundProfitCharts result = apiInstance.getFundProfitChart(id, dateFrom, dateTo, maxPointCount, currency, currencies, chartAssetsCount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#getFundProfitChart");
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
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **currencies** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]
 **chartAssetsCount** | **Integer**|  | [optional]

### Return type

[**FundProfitCharts**](FundProfitCharts.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

