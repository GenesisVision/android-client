# PartnershipApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**exportHistory**](PartnershipApi.md#exportHistory) | **GET** v2.0/partnership/rewards/history/export | Export rewards history.
[**getDetails**](PartnershipApi.md#getDetails) | **GET** v2.0/partnership/details | Get partnership details.
[**getReferrals**](PartnershipApi.md#getReferrals) | **GET** v2.0/partnership/referrals | Get agent friends (referrals and second level referrals).
[**getRewardsHistory**](PartnershipApi.md#getRewardsHistory) | **GET** v2.0/partnership/rewards/history | Get history of agent rewards.

<a name="exportHistory"></a>
# **exportHistory**
> String exportHistory(authorization, dateFrom, dateTo, skip, take)

Export rewards history.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PartnershipApi;


PartnershipApi apiInstance = new PartnershipApi();
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    String result = apiInstance.exportHistory(authorization, dateFrom, dateTo, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PartnershipApi#exportHistory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getDetails"></a>
# **getDetails**
> PartnershipDetails getDetails(authorization, currency)

Get partnership details.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PartnershipApi;


PartnershipApi apiInstance = new PartnershipApi();
String authorization = "authorization_example"; // String | JWT access token
String currency = "currency_example"; // String | 
try {
    PartnershipDetails result = apiInstance.getDetails(authorization, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PartnershipApi#getDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **currency** | **String**|  | [optional] [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, USD, EUR]

### Return type

[**PartnershipDetails**](PartnershipDetails.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getReferrals"></a>
# **getReferrals**
> ItemsViewModelReferralFriend getReferrals(authorization, dateFrom, dateTo, skip, take)

Get agent friends (referrals and second level referrals).

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PartnershipApi;


PartnershipApi apiInstance = new PartnershipApi();
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ItemsViewModelReferralFriend result = apiInstance.getReferrals(authorization, dateFrom, dateTo, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PartnershipApi#getReferrals");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelReferralFriend**](ItemsViewModelReferralFriend.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getRewardsHistory"></a>
# **getRewardsHistory**
> ItemsViewModelRewardDetails getRewardsHistory(authorization, dateFrom, dateTo, skip, take)

Get history of agent rewards.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PartnershipApi;


PartnershipApi apiInstance = new PartnershipApi();
String authorization = "authorization_example"; // String | JWT access token
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ItemsViewModelRewardDetails result = apiInstance.getRewardsHistory(authorization, dateFrom, dateTo, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PartnershipApi#getRewardsHistory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelRewardDetails**](ItemsViewModelRewardDetails.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

