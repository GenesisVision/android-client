# GuidesApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getGuides**](GuidesApi.md#getGuides) | **GET** v2.0/guides | Get guides
[**passGuide**](GuidesApi.md#passGuide) | **POST** v2.0/guides/pass | Pass guid

<a name="getGuides"></a>
# **getGuides**
> GuidesCategoryItemsViewModel getGuides()

Get guides

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.GuidesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

GuidesApi apiInstance = new GuidesApi();
try {
    GuidesCategoryItemsViewModel result = apiInstance.getGuides();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GuidesApi#getGuides");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GuidesCategoryItemsViewModel**](GuidesCategoryItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="passGuide"></a>
# **passGuide**
> Void passGuide(id)

Pass guid

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.GuidesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

GuidesApi apiInstance = new GuidesApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.passGuide(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GuidesApi#passGuide");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

