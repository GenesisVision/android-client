# ProfileApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10ProfileGet**](ProfileApi.md#v10ProfileGet) | **GET** v1.0/profile | Get full profile
[**v10ProfileHeaderGet**](ProfileApi.md#v10ProfileHeaderGet) | **GET** v1.0/profile/header | Get header profile
[**v10ProfilePhoneRequestVerificationCodePost**](ProfileApi.md#v10ProfilePhoneRequestVerificationCodePost) | **POST** v1.0/profile/phone/requestVerificationCode | Request to send phone verification code via SMS.
[**v10ProfilePhoneVerifyByCodePost**](ProfileApi.md#v10ProfilePhoneVerifyByCodePost) | **POST** v1.0/profile/phone/verify/{code} | Verifies phone verification code that was sent by SMS.
[**v10ProfileUpdatePost**](ProfileApi.md#v10ProfileUpdatePost) | **POST** v1.0/profile/update | Update profile


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

<a name="v10ProfilePhoneRequestVerificationCodePost"></a>
# **v10ProfilePhoneRequestVerificationCodePost**
> Integer v10ProfilePhoneRequestVerificationCodePost(authorization)

Request to send phone verification code via SMS.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Integer result = apiInstance.v10ProfilePhoneRequestVerificationCodePost(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#v10ProfilePhoneRequestVerificationCodePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

**Integer**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProfilePhoneVerifyByCodePost"></a>
# **v10ProfilePhoneVerifyByCodePost**
> Void v10ProfilePhoneVerifyByCodePost(code, authorization)

Verifies phone verification code that was sent by SMS.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileApi;


ProfileApi apiInstance = new ProfileApi();
String code = "code_example"; // String | Verification code that was received by client by SMS
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ProfilePhoneVerifyByCodePost(code, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileApi#v10ProfilePhoneVerifyByCodePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| Verification code that was received by client by SMS |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
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

