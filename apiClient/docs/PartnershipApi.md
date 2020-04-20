# PartnershipApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**exportHistory**](PartnershipApi.md#exportHistory) | **GET** v2.0/partnership/rewards/history/export | Export rewards history.
[**getDetails**](PartnershipApi.md#getDetails) | **GET** v2.0/partnership/details | Get partnership details.
[**getReferrals**](PartnershipApi.md#getReferrals) | **GET** v2.0/partnership/referrals | Get agent friends (referrals and second level referrals).
[**getRewardsHistory**](PartnershipApi.md#getRewardsHistory) | **GET** v2.0/partnership/rewards/history | Get history of agent rewards.

<a name="exportHistory"></a>
# **exportHistory**
> byte[] exportHistory(dateFrom, dateTo, skip, take)

Export rewards history.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.PartnershipApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

PartnershipApi apiInstance = new PartnershipApi();
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    byte[] result = apiInstance.exportHistory(dateFrom, dateTo, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PartnershipApi#exportHistory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

**byte[]**

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getDetails"></a>
# **getDetails**
> PartnershipDetails getDetails(currency)

Get partnership details.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.PartnershipApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

PartnershipApi apiInstance = new PartnershipApi();
Currency currency = new Currency(); // Currency | 
try {
    PartnershipDetails result = apiInstance.getDetails(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PartnershipApi#getDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | [**Currency**](.md)|  | [optional]

### Return type

[**PartnershipDetails**](PartnershipDetails.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getReferrals"></a>
# **getReferrals**
> ReferralFriendItemsViewModel getReferrals(dateFrom, dateTo, skip, take)

Get agent friends (referrals and second level referrals).

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.PartnershipApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

PartnershipApi apiInstance = new PartnershipApi();
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ReferralFriendItemsViewModel result = apiInstance.getReferrals(dateFrom, dateTo, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PartnershipApi#getReferrals");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ReferralFriendItemsViewModel**](ReferralFriendItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getRewardsHistory"></a>
# **getRewardsHistory**
> RewardDetailsItemsViewModel getRewardsHistory(dateFrom, dateTo, skip, take)

Get history of agent rewards.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.PartnershipApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

PartnershipApi apiInstance = new PartnershipApi();
DateTime dateFrom = new DateTime(); // DateTime | 
DateTime dateTo = new DateTime(); // DateTime | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    RewardDetailsItemsViewModel result = apiInstance.getRewardsHistory(dateFrom, dateTo, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PartnershipApi#getRewardsHistory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dateFrom** | **DateTime**|  | [optional]
 **dateTo** | **DateTime**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**RewardDetailsItemsViewModel**](RewardDetailsItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

