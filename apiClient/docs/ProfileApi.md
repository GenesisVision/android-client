# ProfileApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getManagerProfile**](ProfileApi.md#getManagerProfile) | **GET** v2.0/profile/{id}/public | Public profile
[**getProfileFull**](ProfileApi.md#getProfileFull) | **GET** v2.0/profile | Get full profile
[**getProfileHeader**](ProfileApi.md#getProfileHeader) | **GET** v2.0/profile/header | Get header profile
[**getSocialLinks**](ProfileApi.md#getSocialLinks) | **GET** v2.0/profile/sociallinks | Get social links
[**getVerificationToken**](ProfileApi.md#getVerificationToken) | **POST** v2.0/profile/verification/token | 
[**removeAvatar**](ProfileApi.md#removeAvatar) | **POST** v2.0/profile/avatar/remove | Remove avatar
[**switchPublicInvestorOff**](ProfileApi.md#switchPublicInvestorOff) | **POST** v2.0/profile/investor/public/off | Disable public investor profile
[**switchPublicInvestorOn**](ProfileApi.md#switchPublicInvestorOn) | **POST** v2.0/profile/investor/public/on | Enable public investor profile
[**updateAvatar**](ProfileApi.md#updateAvatar) | **POST** v2.0/profile/avatar/update/{fileId} | Update avatar
[**updateFcmToken**](ProfileApi.md#updateFcmToken) | **POST** v2.0/profile/push/token | 
[**updatePersonalDetails**](ProfileApi.md#updatePersonalDetails) | **POST** v2.0/profile/personal/update | Update user personal details
[**updateProfile**](ProfileApi.md#updateProfile) | **POST** v2.0/profile/update | Update profile
[**updateSocialLinks**](ProfileApi.md#updateSocialLinks) | **POST** v2.0/profile/sociallinks/update | Add or update social links

<a name="getManagerProfile"></a>
# **getManagerProfile**
> PublicProfile getManagerProfile(id)

Public profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String id = "id_example"; // String | 
try {
    PublicProfile result = apiInstance.getManagerProfile(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#getManagerProfile");
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

<a name="getProfileFull"></a>
# **getProfileFull**
> ProfileFullViewModel getProfileFull(authorization)

Get full profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    ProfileFullViewModel result = apiInstance.getProfileFull(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#getProfileFull");
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

<a name="getProfileHeader"></a>
# **getProfileHeader**
> ProfileHeaderViewModel getProfileHeader(authorization)

Get header profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    ProfileHeaderViewModel result = apiInstance.getProfileHeader(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#getProfileHeader");
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

<a name="getSocialLinks"></a>
# **getSocialLinks**
> SocialLinksViewModel getSocialLinks(authorization)

Get social links

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    SocialLinksViewModel result = apiInstance.getSocialLinks(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#getSocialLinks");
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

<a name="getVerificationToken"></a>
# **getVerificationToken**
> String getVerificationToken(authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    String result = apiInstance.getVerificationToken(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#getVerificationToken");
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

<a name="removeAvatar"></a>
# **removeAvatar**
> Void removeAvatar(authorization)

Remove avatar

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.removeAvatar(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#removeAvatar");
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

<a name="switchPublicInvestorOff"></a>
# **switchPublicInvestorOff**
> Void switchPublicInvestorOff(authorization)

Disable public investor profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.switchPublicInvestorOff(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#switchPublicInvestorOff");
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

<a name="switchPublicInvestorOn"></a>
# **switchPublicInvestorOn**
> Void switchPublicInvestorOn(authorization)

Enable public investor profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.switchPublicInvestorOn(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#switchPublicInvestorOn");
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

<a name="updateAvatar"></a>
# **updateAvatar**
> Void updateAvatar(fileId, authorization)

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
    Void result = apiInstance.updateAvatar(fileId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#updateAvatar");
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

<a name="updateFcmToken"></a>
# **updateFcmToken**
> Void updateFcmToken(authorization, body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
FcmTokenViewModel body = new FcmTokenViewModel(); // FcmTokenViewModel | 
try {
    Void result = apiInstance.updateFcmToken(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#updateFcmToken");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**FcmTokenViewModel**](FcmTokenViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updatePersonalDetails"></a>
# **updatePersonalDetails**
> Void updatePersonalDetails(authorization, body)

Update user personal details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
UpdatePersonalDetailViewModel body = new UpdatePersonalDetailViewModel(); // UpdatePersonalDetailViewModel | 
try {
    Void result = apiInstance.updatePersonalDetails(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#updatePersonalDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**UpdatePersonalDetailViewModel**](UpdatePersonalDetailViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateProfile"></a>
# **updateProfile**
> Void updateProfile(authorization, body)

Update profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
UpdateProfileViewModel body = new UpdateProfileViewModel(); // UpdateProfileViewModel | 
try {
    Void result = apiInstance.updateProfile(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#updateProfile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**UpdateProfileViewModel**](UpdateProfileViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateSocialLinks"></a>
# **updateSocialLinks**
> Void updateSocialLinks(authorization, body)

Add or update social links

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
UpdateSocialLinkViewModel body = new UpdateSocialLinkViewModel(); // UpdateSocialLinkViewModel | 
try {
    Void result = apiInstance.updateSocialLinks(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#updateSocialLinks");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**UpdateSocialLinkViewModel**](UpdateSocialLinkViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

