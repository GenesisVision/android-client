# ProfileApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getMobileVerificationToken**](ProfileApi.md#getMobileVerificationToken) | **POST** v2.0/profile/verification/mobile/token | 
[**getProfileFull**](ProfileApi.md#getProfileFull) | **GET** v2.0/profile | Get full profile
[**getProfileHeader**](ProfileApi.md#getProfileHeader) | **GET** v2.0/profile/header | Get header profile
[**getSocialLinks**](ProfileApi.md#getSocialLinks) | **GET** v2.0/profile/sociallinks | Get social links
[**getWebVerificationToken**](ProfileApi.md#getWebVerificationToken) | **POST** v2.0/profile/verification/web/token | 
[**removeAvatar**](ProfileApi.md#removeAvatar) | **POST** v2.0/profile/avatar/remove | Remove avatar
[**removeFcmToken**](ProfileApi.md#removeFcmToken) | **POST** v2.0/profile/push/token/remove | 
[**switchBetaFeatureOff**](ProfileApi.md#switchBetaFeatureOff) | **POST** v2.0/profile/beta/off | Disable beta feature
[**switchBetaFeatureOn**](ProfileApi.md#switchBetaFeatureOn) | **POST** v2.0/profile/beta/on | Enable beta feature
[**switchPublicInvestorOff**](ProfileApi.md#switchPublicInvestorOff) | **POST** v2.0/profile/investor/public/off | Disable public investor profile
[**switchPublicInvestorOn**](ProfileApi.md#switchPublicInvestorOn) | **POST** v2.0/profile/investor/public/on | Enable public investor profile
[**updateAllSocialLinks**](ProfileApi.md#updateAllSocialLinks) | **POST** v2.0/profile/sociallinks/all/update | Add or update all social links
[**updateAvatar**](ProfileApi.md#updateAvatar) | **POST** v2.0/profile/avatar/update/{fileId} | Update avatar
[**updateFcmToken**](ProfileApi.md#updateFcmToken) | **POST** v2.0/profile/push/token | 
[**updatePersonalDetails**](ProfileApi.md#updatePersonalDetails) | **POST** v2.0/profile/personal/update | Update user personal details
[**updateProfile**](ProfileApi.md#updateProfile) | **POST** v2.0/profile/update | Update profile
[**updateSocialLinks**](ProfileApi.md#updateSocialLinks) | **POST** v2.0/profile/sociallinks/update | Add or update social links
[**updateUserPlatformCurrency**](ProfileApi.md#updateUserPlatformCurrency) | **POST** v2.0/profile/currency/update | Update platform currency
[**updateUserSocialSettings**](ProfileApi.md#updateUserSocialSettings) | **POST** v2.0/profile/social/settings/update | Update user social settings

<a name="getMobileVerificationToken"></a>
# **getMobileVerificationToken**
> ExternalKycAccessToken getMobileVerificationToken()



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
try {
    ExternalKycAccessToken result = apiInstance.getMobileVerificationToken();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#getMobileVerificationToken");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ExternalKycAccessToken**](ExternalKycAccessToken.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProfileFull"></a>
# **getProfileFull**
> ProfileFullViewModel getProfileFull()

Get full profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
try {
    ProfileFullViewModel result = apiInstance.getProfileFull();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#getProfileFull");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ProfileFullViewModel**](ProfileFullViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProfileHeader"></a>
# **getProfileHeader**
> ProfileHeaderViewModel getProfileHeader()

Get header profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
try {
    ProfileHeaderViewModel result = apiInstance.getProfileHeader();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#getProfileHeader");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ProfileHeaderViewModel**](ProfileHeaderViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getSocialLinks"></a>
# **getSocialLinks**
> SocialLinksViewModel getSocialLinks()

Get social links

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
try {
    SocialLinksViewModel result = apiInstance.getSocialLinks();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#getSocialLinks");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**SocialLinksViewModel**](SocialLinksViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getWebVerificationToken"></a>
# **getWebVerificationToken**
> ExternalKycAccessToken getWebVerificationToken()



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
try {
    ExternalKycAccessToken result = apiInstance.getWebVerificationToken();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#getWebVerificationToken");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ExternalKycAccessToken**](ExternalKycAccessToken.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="removeAvatar"></a>
# **removeAvatar**
> Void removeAvatar()

Remove avatar

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
try {
    Void result = apiInstance.removeAvatar();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#removeAvatar");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="removeFcmToken"></a>
# **removeFcmToken**
> Void removeFcmToken(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
FcmTokenViewModel body = new FcmTokenViewModel(); // FcmTokenViewModel | 
try {
    Void result = apiInstance.removeFcmToken(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#removeFcmToken");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**FcmTokenViewModel**](FcmTokenViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="switchBetaFeatureOff"></a>
# **switchBetaFeatureOff**
> Void switchBetaFeatureOff(feature)

Disable beta feature

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
BetaTestingType feature = new BetaTestingType(); // BetaTestingType | 
try {
    Void result = apiInstance.switchBetaFeatureOff(feature);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#switchBetaFeatureOff");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **feature** | [**BetaTestingType**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="switchBetaFeatureOn"></a>
# **switchBetaFeatureOn**
> Void switchBetaFeatureOn(feature)

Enable beta feature

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
BetaTestingType feature = new BetaTestingType(); // BetaTestingType | 
try {
    Void result = apiInstance.switchBetaFeatureOn(feature);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#switchBetaFeatureOn");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **feature** | [**BetaTestingType**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="switchPublicInvestorOff"></a>
# **switchPublicInvestorOff**
> Void switchPublicInvestorOff()

Disable public investor profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
try {
    Void result = apiInstance.switchPublicInvestorOff();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#switchPublicInvestorOff");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="switchPublicInvestorOn"></a>
# **switchPublicInvestorOn**
> Void switchPublicInvestorOn()

Enable public investor profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
try {
    Void result = apiInstance.switchPublicInvestorOn();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#switchPublicInvestorOn");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="updateAllSocialLinks"></a>
# **updateAllSocialLinks**
> Void updateAllSocialLinks(body)

Add or update all social links

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
UpdateSocialLinksViewModel body = new UpdateSocialLinksViewModel(); // UpdateSocialLinksViewModel | 
try {
    Void result = apiInstance.updateAllSocialLinks(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#updateAllSocialLinks");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UpdateSocialLinksViewModel**](UpdateSocialLinksViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateAvatar"></a>
# **updateAvatar**
> Void updateAvatar(fileId)

Update avatar

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
String fileId = "fileId_example"; // String | 
try {
    Void result = apiInstance.updateAvatar(fileId);
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

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="updateFcmToken"></a>
# **updateFcmToken**
> Void updateFcmToken(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
FcmTokenViewModel body = new FcmTokenViewModel(); // FcmTokenViewModel | 
try {
    Void result = apiInstance.updateFcmToken(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#updateFcmToken");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**FcmTokenViewModel**](FcmTokenViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updatePersonalDetails"></a>
# **updatePersonalDetails**
> Void updatePersonalDetails(body)

Update user personal details

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
UpdatePersonalDetailViewModel body = new UpdatePersonalDetailViewModel(); // UpdatePersonalDetailViewModel | 
try {
    Void result = apiInstance.updatePersonalDetails(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#updatePersonalDetails");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UpdatePersonalDetailViewModel**](UpdatePersonalDetailViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateProfile"></a>
# **updateProfile**
> Void updateProfile(body)

Update profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
UpdateProfileViewModel body = new UpdateProfileViewModel(); // UpdateProfileViewModel | 
try {
    Void result = apiInstance.updateProfile(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#updateProfile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UpdateProfileViewModel**](UpdateProfileViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateSocialLinks"></a>
# **updateSocialLinks**
> Void updateSocialLinks(body)

Add or update social links

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
UpdateSocialLinkViewModel body = new UpdateSocialLinkViewModel(); // UpdateSocialLinkViewModel | 
try {
    Void result = apiInstance.updateSocialLinks(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#updateSocialLinks");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UpdateSocialLinkViewModel**](UpdateSocialLinkViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateUserPlatformCurrency"></a>
# **updateUserPlatformCurrency**
> Void updateUserPlatformCurrency(currency)

Update platform currency

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
Currency currency = new Currency(); // Currency | 
try {
    Void result = apiInstance.updateUserPlatformCurrency(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#updateUserPlatformCurrency");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | [**Currency**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="updateUserSocialSettings"></a>
# **updateUserSocialSettings**
> Void updateUserSocialSettings(whoCanPostToMayWall, whoCanViewCommentsOnMyPosts, whoCanCommentOnMyPosts)

Update user social settings

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProfileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

ProfileApi apiInstance = new ProfileApi();
SocialViewMode whoCanPostToMayWall = new SocialViewMode(); // SocialViewMode | 
SocialViewMode whoCanViewCommentsOnMyPosts = new SocialViewMode(); // SocialViewMode | 
SocialViewMode whoCanCommentOnMyPosts = new SocialViewMode(); // SocialViewMode | 
try {
    Void result = apiInstance.updateUserSocialSettings(whoCanPostToMayWall, whoCanViewCommentsOnMyPosts, whoCanCommentOnMyPosts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#updateUserSocialSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **whoCanPostToMayWall** | [**SocialViewMode**](.md)|  | [optional]
 **whoCanViewCommentsOnMyPosts** | [**SocialViewMode**](.md)|  | [optional]
 **whoCanCommentOnMyPosts** | [**SocialViewMode**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

