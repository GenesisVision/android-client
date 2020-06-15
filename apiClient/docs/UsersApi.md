# UsersApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getUserProfile**](UsersApi.md#getUserProfile) | **GET** v2.0/users/{id} | Public profile
[**getUserProfileFollowDetails**](UsersApi.md#getUserProfileFollowDetails) | **GET** v2.0/users/{id}/follow | Public profile follow details
[**getUsersList**](UsersApi.md#getUsersList) | **GET** v2.0/users | Get users list

<a name="getUserProfile"></a>
# **getUserProfile**
> PublicProfile getUserProfile(id, logoQuality)

Public profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.UsersApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

UsersApi apiInstance = new UsersApi();
String id = "id_example"; // String | 
ImageQuality logoQuality = new ImageQuality(); // ImageQuality | 
try {
    PublicProfile result = apiInstance.getUserProfile(id, logoQuality);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#getUserProfile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **logoQuality** | [**ImageQuality**](.md)|  | [optional]

### Return type

[**PublicProfile**](PublicProfile.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getUserProfileFollowDetails"></a>
# **getUserProfileFollowDetails**
> PublicProfileFollow getUserProfileFollowDetails(id)

Public profile follow details

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.UsersApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

UsersApi apiInstance = new UsersApi();
String id = "id_example"; // String | 
try {
    PublicProfileFollow result = apiInstance.getUserProfileFollowDetails(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#getUserProfileFollowDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |

### Return type

[**PublicProfileFollow**](PublicProfileFollow.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getUsersList"></a>
# **getUsersList**
> UserDetailsListItemsViewModel getUsersList(sorting, timeframe, tags, skip, take)

Get users list

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.UsersApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

UsersApi apiInstance = new UsersApi();
UsersFilterSorting sorting = new UsersFilterSorting(); // UsersFilterSorting | 
UsersFilterTimeframe timeframe = new UsersFilterTimeframe(); // UsersFilterTimeframe | 
List<String> tags = Arrays.asList("tags_example"); // List<String> | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    UserDetailsListItemsViewModel result = apiInstance.getUsersList(sorting, timeframe, tags, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#getUsersList");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sorting** | [**UsersFilterSorting**](.md)|  | [optional]
 **timeframe** | [**UsersFilterTimeframe**](.md)|  | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**UserDetailsListItemsViewModel**](UserDetailsListItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

