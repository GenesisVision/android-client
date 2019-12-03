# UsersApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getManagerProfile**](UsersApi.md#getManagerProfile) | **GET** v2.0/users/{id} | Public profile
[**getUsersList**](UsersApi.md#getUsersList) | **GET** v2.0/users/list | Get users list

<a name="getManagerProfile"></a>
# **getManagerProfile**
> PublicProfile getManagerProfile(id)

Public profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String id = "id_example"; // String | 
try {
    PublicProfile result = apiInstance.getManagerProfile(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#getManagerProfile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |

### Return type

[**PublicProfile**](PublicProfile.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getUsersList"></a>
# **getUsersList**
> ItemsViewModelUserDetailsList getUsersList(facetId, sorting, tags, skip, take)

Get users list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String facetId = "facetId_example"; // String | 
String sorting = "sorting_example"; // String | 
List<String> tags = Arrays.asList("tags_example"); // List<String> | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    ItemsViewModelUserDetailsList result = apiInstance.getUsersList(facetId, sorting, tags, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#getUsersList");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **facetId** | **String**|  | [optional]
 **sorting** | **String**|  | [optional] [enum: ByNameAsc, ByNameDesc, ByAgeAsc, ByAgeDesc, ByAumAsc, ByAumDesc, ByFollowersAsc, ByFollowersDesc, ByInvestorsAsc, ByInvestorsDesc, ByPopularityAsc, ByPopularityDesc, ByProfitAsc, ByProfitDesc, ByTradingProfitAsc, ByTradingProfitDesc, ByInvestingProfitAsc, ByInvestingProfitDesc]
 **tags** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**ItemsViewModelUserDetailsList**](ItemsViewModelUserDetailsList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

