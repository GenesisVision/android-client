# FundsApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10FundsGet**](FundsApi.md#v10FundsGet) | **GET** v1.0/funds | Funds list


<a name="v10FundsGet"></a>
# **v10FundsGet**
> ProgramsList v10FundsGet(authorization, sorting, statisticDateFrom, statisticDateTo, chartPointsCount, mask, facetId, isFavorite, ids, skip, take)

Funds list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FundsApi;


FundsApi apiInstance = new FundsApi();
String authorization = "authorization_example"; // String | 
String sorting = "sorting_example"; // String | 
DateTime statisticDateFrom = new DateTime(); // DateTime | 
DateTime statisticDateTo = new DateTime(); // DateTime | 
Integer chartPointsCount = 56; // Integer | 
String mask = "mask_example"; // String | 
UUID facetId = new UUID(); // UUID | 
Boolean isFavorite = true; // Boolean | 
List<UUID> ids = Arrays.asList(new UUID()); // List<UUID> | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ProgramsList result = apiInstance.v10FundsGet(authorization, sorting, statisticDateFrom, statisticDateTo, chartPointsCount, mask, facetId, isFavorite, ids, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsApi#v10FundsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByProfitAsc, ByProfitDesc, ByDrawdownAsc, ByDrawdownDesc, ByInvestorsAsc, ByInvestorsDesc, ByTitleAsc, ByTitleDesc, ByBalanceAsc, ByBalanceDesc]
 **statisticDateFrom** | **DateTime**|  | [optional]
 **statisticDateTo** | **DateTime**|  | [optional]
 **chartPointsCount** | **Integer**|  | [optional]
 **mask** | **String**|  | [optional]
 **facetId** | [**UUID**](.md)|  | [optional]
 **isFavorite** | **Boolean**|  | [optional]
 **ids** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ProgramsList**](ProgramsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

