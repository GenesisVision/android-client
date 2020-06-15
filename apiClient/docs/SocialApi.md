# SocialApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addPost**](SocialApi.md#addPost) | **POST** v2.0/social/feed/add | Add post
[**deletePost**](SocialApi.md#deletePost) | **POST** v2.0/social/feed/{id}/delete | Delete post
[**editPost**](SocialApi.md#editPost) | **POST** v2.0/social/feed/edit | Edit post
[**followUser**](SocialApi.md#followUser) | **POST** v2.0/social/user/{userId}/follow | Follow user
[**getFeed**](SocialApi.md#getFeed) | **GET** v2.0/social/feed | Get feed
[**getPost**](SocialApi.md#getPost) | **GET** v2.0/social/feed/{id} | Get post
[**getSocialMedia**](SocialApi.md#getSocialMedia) | **GET** v2.0/social/feed/media | Get social media
[**getSocialSummary**](SocialApi.md#getSocialSummary) | **GET** v2.0/social/feed/summary | Get social summary
[**likePost**](SocialApi.md#likePost) | **POST** v2.0/social/feed/{id}/like | Like
[**pinPost**](SocialApi.md#pinPost) | **POST** v2.0/social/feed/{id}/pin | Pin post
[**rePost**](SocialApi.md#rePost) | **POST** v2.0/social/feed/repost | RePost
[**revertDeletingPost**](SocialApi.md#revertDeletingPost) | **POST** v2.0/social/feed/{id}/delete/revert | Revert deleting post
[**unfollowUser**](SocialApi.md#unfollowUser) | **POST** v2.0/social/user/{userId}/unfollow | Unfollow user
[**unlikePost**](SocialApi.md#unlikePost) | **POST** v2.0/social/feed/{id}/unlike | Unlike
[**unpinPost**](SocialApi.md#unpinPost) | **POST** v2.0/social/feed/{id}/unpin | Unpin post

<a name="addPost"></a>
# **addPost**
> Void addPost(body)

Add post

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
NewPost body = new NewPost(); // NewPost | 
try {
    Void result = apiInstance.addPost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#addPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NewPost**](NewPost.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="deletePost"></a>
# **deletePost**
> Void deletePost(id)

Delete post

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.deletePost(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#deletePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="editPost"></a>
# **editPost**
> Void editPost(body)

Edit post

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
EditPost body = new EditPost(); // EditPost | 
try {
    Void result = apiInstance.editPost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#editPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**EditPost**](EditPost.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="followUser"></a>
# **followUser**
> Void followUser(userId)

Follow user

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
UUID userId = new UUID(); // UUID | 
try {
    Void result = apiInstance.followUser(userId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#followUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFeed"></a>
# **getFeed**
> PostItemsViewModel getFeed(userId, tagContentId, tagContentIds, userMode, hashTags, mask, showTop, showLiked, skip, take)

Get feed

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
UUID userId = new UUID(); // UUID | 
UUID tagContentId = new UUID(); // UUID | 
List<UUID> tagContentIds = Arrays.asList(new UUID()); // List<UUID> | 
UserFeedMode userMode = new UserFeedMode(); // UserFeedMode | 
List<String> hashTags = Arrays.asList("hashTags_example"); // List<String> | 
String mask = "mask_example"; // String | 
Boolean showTop = true; // Boolean | 
Boolean showLiked = true; // Boolean | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    PostItemsViewModel result = apiInstance.getFeed(userId, tagContentId, tagContentIds, userMode, hashTags, mask, showTop, showLiked, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#getFeed");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | [**UUID**](.md)|  | [optional]
 **tagContentId** | [**UUID**](.md)|  | [optional]
 **tagContentIds** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **userMode** | [**UserFeedMode**](.md)|  | [optional]
 **hashTags** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **mask** | **String**|  | [optional]
 **showTop** | **Boolean**|  | [optional]
 **showLiked** | **Boolean**|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**PostItemsViewModel**](PostItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getPost"></a>
# **getPost**
> EditablePost getPost(id)

Get post

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
UUID id = new UUID(); // UUID | 
try {
    EditablePost result = apiInstance.getPost(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#getPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**EditablePost**](EditablePost.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getSocialMedia"></a>
# **getSocialMedia**
> MediaPostItemsViewModel getSocialMedia(mask, type, skip, take)

Get social media

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
String mask = "mask_example"; // String | 
SocialLinkType type = new SocialLinkType(); // SocialLinkType | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    MediaPostItemsViewModel result = apiInstance.getSocialMedia(mask, type, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#getSocialMedia");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **mask** | **String**|  | [optional]
 **type** | [**SocialLinkType**](.md)|  | [optional]
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**MediaPostItemsViewModel**](MediaPostItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getSocialSummary"></a>
# **getSocialSummary**
> SocialSummary getSocialSummary()

Get social summary

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
try {
    SocialSummary result = apiInstance.getSocialSummary();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#getSocialSummary");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**SocialSummary**](SocialSummary.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="likePost"></a>
# **likePost**
> Void likePost(id)

Like

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.likePost(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#likePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="pinPost"></a>
# **pinPost**
> Void pinPost(id)

Pin post

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.pinPost(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#pinPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="rePost"></a>
# **rePost**
> Void rePost(body)

RePost

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
RePost body = new RePost(); // RePost | 
try {
    Void result = apiInstance.rePost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#rePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**RePost**](RePost.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="revertDeletingPost"></a>
# **revertDeletingPost**
> Void revertDeletingPost(id)

Revert deleting post

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.revertDeletingPost(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#revertDeletingPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="unfollowUser"></a>
# **unfollowUser**
> Void unfollowUser(userId)

Unfollow user

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
UUID userId = new UUID(); // UUID | 
try {
    Void result = apiInstance.unfollowUser(userId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#unfollowUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="unlikePost"></a>
# **unlikePost**
> Void unlikePost(id)

Unlike

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.unlikePost(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#unlikePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="unpinPost"></a>
# **unpinPost**
> Void unpinPost(id)

Unpin post

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SocialApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

SocialApi apiInstance = new SocialApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.unpinPost(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SocialApi#unpinPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

