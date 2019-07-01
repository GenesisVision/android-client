# ProfileApi

All URIs are relative to *https://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10ProfileAvatarRemovePost**](ProfileApi.md#v10ProfileAvatarRemovePost) | **POST** v1.0/profile/avatar/remove | Remove avatar
[**v10ProfileAvatarUpdateByFileIdPost**](ProfileApi.md#v10ProfileAvatarUpdateByFileIdPost) | **POST** v1.0/profile/avatar/update/{fileId} | Update avatar
[**v10ProfileGet**](ProfileApi.md#v10ProfileGet) | **GET** v1.0/profile | Get full profile
[**v10ProfileHeaderGet**](ProfileApi.md#v10ProfileHeaderGet) | **GET** v1.0/profile/header | Get header profile
[**v10ProfilePersonalUpdatePost**](ProfileApi.md#v10ProfilePersonalUpdatePost) | **POST** v1.0/profile/personal/update | Update user personal details
[**v10ProfilePushTokenPost**](ProfileApi.md#v10ProfilePushTokenPost) | **POST** v1.0/profile/push/token | 
[**v10ProfileSociallinksGet**](ProfileApi.md#v10ProfileSociallinksGet) | **GET** v1.0/profile/sociallinks | Get social links
[**v10ProfileSociallinksUpdatePost**](ProfileApi.md#v10ProfileSociallinksUpdatePost) | **POST** v1.0/profile/sociallinks/update | Add or update social links
[**v10ProfileUpdatePost**](ProfileApi.md#v10ProfileUpdatePost) | **POST** v1.0/profile/update | Update profile
[**v10ProfileVerificationTokenPost**](ProfileApi.md#v10ProfileVerificationTokenPost) | **POST** v1.0/profile/verification/token | 


<a name="v10ProfileAvatarRemovePost"></a>
# **v10ProfileAvatarRemovePost**
> Void v10ProfileAvatarRemovePost(authorization)

Remove avatar

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ProfileAvatarRemovePost(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#v10ProfileAvatarRemovePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProfileAvatarUpdateByFileIdPost"></a>
# **v10ProfileAvatarUpdateByFileIdPost**
> Void v10ProfileAvatarUpdateByFileIdPost(fileId, authorization)

Update avatar

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String fileId = "fileId_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ProfileAvatarUpdateByFileIdPost(fileId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#v10ProfileAvatarUpdateByFileIdPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **fileId** | **String**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProfileGet"></a>
# **v10ProfileGet**
> ProfileFullViewModel v10ProfileGet(authorization)

Get full profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    ProfileFullViewModel result = apiInstance.v10ProfileGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#v10ProfileGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**ProfileFullViewModel**](ProfileFullViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProfileHeaderGet"></a>
# **v10ProfileHeaderGet**
> ProfileHeaderViewModel v10ProfileHeaderGet(authorization)

Get header profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    ProfileHeaderViewModel result = apiInstance.v10ProfileHeaderGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#v10ProfileHeaderGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**ProfileHeaderViewModel**](ProfileHeaderViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProfilePersonalUpdatePost"></a>
# **v10ProfilePersonalUpdatePost**
> Void v10ProfilePersonalUpdatePost(authorization, model)

Update user personal details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
UpdatePersonalDetailViewModel model = new UpdatePersonalDetailViewModel(); // UpdatePersonalDetailViewModel | 
try {
    Void result = apiInstance.v10ProfilePersonalUpdatePost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#v10ProfilePersonalUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**UpdatePersonalDetailViewModel**](UpdatePersonalDetailViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProfilePushTokenPost"></a>
# **v10ProfilePushTokenPost**
> Void v10ProfilePushTokenPost(authorization, token)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
FcmTokenViewModel token = new FcmTokenViewModel(); // FcmTokenViewModel | 
try {
    Void result = apiInstance.v10ProfilePushTokenPost(authorization, token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#v10ProfilePushTokenPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **token** | [**FcmTokenViewModel**](FcmTokenViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProfileSociallinksGet"></a>
# **v10ProfileSociallinksGet**
> SocialLinksViewModel v10ProfileSociallinksGet(authorization)

Get social links

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    SocialLinksViewModel result = apiInstance.v10ProfileSociallinksGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#v10ProfileSociallinksGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**SocialLinksViewModel**](SocialLinksViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProfileSociallinksUpdatePost"></a>
# **v10ProfileSociallinksUpdatePost**
> Void v10ProfileSociallinksUpdatePost(authorization, model)

Add or update social links

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
UpdateSocialLinkViewModel model = new UpdateSocialLinkViewModel(); // UpdateSocialLinkViewModel | 
try {
    Void result = apiInstance.v10ProfileSociallinksUpdatePost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#v10ProfileSociallinksUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**UpdateSocialLinkViewModel**](UpdateSocialLinkViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProfileUpdatePost"></a>
# **v10ProfileUpdatePost**
> Void v10ProfileUpdatePost(authorization, model)

Update profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
UpdateProfileViewModel model = new UpdateProfileViewModel(); // UpdateProfileViewModel | 
try {
    Void result = apiInstance.v10ProfileUpdatePost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#v10ProfileUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**UpdateProfileViewModel**](UpdateProfileViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProfileVerificationTokenPost"></a>
# **v10ProfileVerificationTokenPost**
> String v10ProfileVerificationTokenPost(authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    String result = apiInstance.v10ProfileVerificationTokenPost(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#v10ProfileVerificationTokenPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

