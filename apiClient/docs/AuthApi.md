# AuthApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10Auth2faConfirmPost**](AuthApi.md#v10Auth2faConfirmPost) | **POST** v1.0/auth/2fa/confirm | 2FA confirm
[**v10Auth2faCreatePost**](AuthApi.md#v10Auth2faCreatePost) | **POST** v1.0/auth/2fa/create | 2FA create
[**v10Auth2faDisablePost**](AuthApi.md#v10Auth2faDisablePost) | **POST** v1.0/auth/2fa/disable | 2FA disable
[**v10Auth2faGet**](AuthApi.md#v10Auth2faGet) | **GET** v1.0/auth/2fa | 2FA status
[**v10Auth2faRecoverycodesNewPost**](AuthApi.md#v10Auth2faRecoverycodesNewPost) | **POST** v1.0/auth/2fa/recoverycodes/new | 2FA generate new recovery codes
[**v10Auth2faRecoverycodesPost**](AuthApi.md#v10Auth2faRecoverycodesPost) | **POST** v1.0/auth/2fa/recoverycodes | 2FA recovery codes
[**v10AuthPasswordChangePost**](AuthApi.md#v10AuthPasswordChangePost) | **POST** v1.0/auth/password/change | Change password
[**v10AuthPasswordForgotInvestorPost**](AuthApi.md#v10AuthPasswordForgotInvestorPost) | **POST** v1.0/auth/password/forgot/investor | Forgot password for investor
[**v10AuthPasswordForgotManagerPost**](AuthApi.md#v10AuthPasswordForgotManagerPost) | **POST** v1.0/auth/password/forgot/manager | Forgot password for manager
[**v10AuthPasswordResetPost**](AuthApi.md#v10AuthPasswordResetPost) | **POST** v1.0/auth/password/reset | Reset password
[**v10AuthSigninBrokerPost**](AuthApi.md#v10AuthSigninBrokerPost) | **POST** v1.0/auth/signin/broker | Authorize
[**v10AuthSigninInvestorPost**](AuthApi.md#v10AuthSigninInvestorPost) | **POST** v1.0/auth/signin/investor | Authorize
[**v10AuthSigninManagerPost**](AuthApi.md#v10AuthSigninManagerPost) | **POST** v1.0/auth/signin/manager | Authorize
[**v10AuthSignupConfirmPost**](AuthApi.md#v10AuthSignupConfirmPost) | **POST** v1.0/auth/signup/confirm | Confirm email after registration
[**v10AuthSignupInvestorPost**](AuthApi.md#v10AuthSignupInvestorPost) | **POST** v1.0/auth/signup/investor | New investor registration
[**v10AuthSignupManagerPost**](AuthApi.md#v10AuthSignupManagerPost) | **POST** v1.0/auth/signup/manager | New manager registration
[**v10AuthTokenUpdatePost**](AuthApi.md#v10AuthTokenUpdatePost) | **POST** v1.0/auth/token/update | Update auth token


<a name="v10Auth2faConfirmPost"></a>
# **v10Auth2faConfirmPost**
> RecoveryCodesViewModel v10Auth2faConfirmPost(authorization, model)

2FA confirm

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
TwoFactorAuthenticatorConfirm model = new TwoFactorAuthenticatorConfirm(); // TwoFactorAuthenticatorConfirm | 
try {
    RecoveryCodesViewModel result = apiInstance.v10Auth2faConfirmPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10Auth2faConfirmPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**TwoFactorAuthenticatorConfirm**](TwoFactorAuthenticatorConfirm.md)|  | [optional]

### Return type

[**RecoveryCodesViewModel**](RecoveryCodesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10Auth2faCreatePost"></a>
# **v10Auth2faCreatePost**
> TwoFactorAuthenticator v10Auth2faCreatePost(authorization)

2FA create

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    TwoFactorAuthenticator result = apiInstance.v10Auth2faCreatePost(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10Auth2faCreatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**TwoFactorAuthenticator**](TwoFactorAuthenticator.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10Auth2faDisablePost"></a>
# **v10Auth2faDisablePost**
> Void v10Auth2faDisablePost(authorization, model)

2FA disable

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
TwoFactorCodeModel model = new TwoFactorCodeModel(); // TwoFactorCodeModel | 
try {
    Void result = apiInstance.v10Auth2faDisablePost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10Auth2faDisablePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**TwoFactorCodeModel**](TwoFactorCodeModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10Auth2faGet"></a>
# **v10Auth2faGet**
> TwoFactorStatus v10Auth2faGet(authorization)

2FA status

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    TwoFactorStatus result = apiInstance.v10Auth2faGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10Auth2faGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**TwoFactorStatus**](TwoFactorStatus.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10Auth2faRecoverycodesNewPost"></a>
# **v10Auth2faRecoverycodesNewPost**
> RecoveryCodesViewModel v10Auth2faRecoverycodesNewPost(authorization, model)

2FA generate new recovery codes

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
PasswordModel model = new PasswordModel(); // PasswordModel | 
try {
    RecoveryCodesViewModel result = apiInstance.v10Auth2faRecoverycodesNewPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10Auth2faRecoverycodesNewPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**PasswordModel**](PasswordModel.md)|  | [optional]

### Return type

[**RecoveryCodesViewModel**](RecoveryCodesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10Auth2faRecoverycodesPost"></a>
# **v10Auth2faRecoverycodesPost**
> RecoveryCodesViewModel v10Auth2faRecoverycodesPost(authorization, model)

2FA recovery codes

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
PasswordModel model = new PasswordModel(); // PasswordModel | 
try {
    RecoveryCodesViewModel result = apiInstance.v10Auth2faRecoverycodesPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10Auth2faRecoverycodesPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**PasswordModel**](PasswordModel.md)|  | [optional]

### Return type

[**RecoveryCodesViewModel**](RecoveryCodesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10AuthPasswordChangePost"></a>
# **v10AuthPasswordChangePost**
> Void v10AuthPasswordChangePost(authorization, model)

Change password

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
ChangePasswordViewModel model = new ChangePasswordViewModel(); // ChangePasswordViewModel | 
try {
    Void result = apiInstance.v10AuthPasswordChangePost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10AuthPasswordChangePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**ChangePasswordViewModel**](ChangePasswordViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10AuthPasswordForgotInvestorPost"></a>
# **v10AuthPasswordForgotInvestorPost**
> Void v10AuthPasswordForgotInvestorPost(model)

Forgot password for investor

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
ForgotPasswordViewModel model = new ForgotPasswordViewModel(); // ForgotPasswordViewModel | 
try {
    Void result = apiInstance.v10AuthPasswordForgotInvestorPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10AuthPasswordForgotInvestorPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**ForgotPasswordViewModel**](ForgotPasswordViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10AuthPasswordForgotManagerPost"></a>
# **v10AuthPasswordForgotManagerPost**
> Void v10AuthPasswordForgotManagerPost(model)

Forgot password for manager

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
ForgotPasswordViewModel model = new ForgotPasswordViewModel(); // ForgotPasswordViewModel | 
try {
    Void result = apiInstance.v10AuthPasswordForgotManagerPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10AuthPasswordForgotManagerPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**ForgotPasswordViewModel**](ForgotPasswordViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10AuthPasswordResetPost"></a>
# **v10AuthPasswordResetPost**
> String v10AuthPasswordResetPost(model)

Reset password

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
ResetPasswordViewModel model = new ResetPasswordViewModel(); // ResetPasswordViewModel | 
try {
    String result = apiInstance.v10AuthPasswordResetPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10AuthPasswordResetPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**ResetPasswordViewModel**](ResetPasswordViewModel.md)|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10AuthSigninBrokerPost"></a>
# **v10AuthSigninBrokerPost**
> String v10AuthSigninBrokerPost(model)

Authorize

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
LoginViewModel model = new LoginViewModel(); // LoginViewModel | 
try {
    String result = apiInstance.v10AuthSigninBrokerPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10AuthSigninBrokerPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**LoginViewModel**](LoginViewModel.md)|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10AuthSigninInvestorPost"></a>
# **v10AuthSigninInvestorPost**
> String v10AuthSigninInvestorPost(model)

Authorize

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
LoginViewModel model = new LoginViewModel(); // LoginViewModel | 
try {
    String result = apiInstance.v10AuthSigninInvestorPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10AuthSigninInvestorPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**LoginViewModel**](LoginViewModel.md)|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10AuthSigninManagerPost"></a>
# **v10AuthSigninManagerPost**
> String v10AuthSigninManagerPost(model)

Authorize

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
LoginViewModel model = new LoginViewModel(); // LoginViewModel | 
try {
    String result = apiInstance.v10AuthSigninManagerPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10AuthSigninManagerPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**LoginViewModel**](LoginViewModel.md)|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10AuthSignupConfirmPost"></a>
# **v10AuthSignupConfirmPost**
> String v10AuthSignupConfirmPost(userId, code)

Confirm email after registration

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String userId = "userId_example"; // String | 
String code = "code_example"; // String | 
try {
    String result = apiInstance.v10AuthSignupConfirmPost(userId, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10AuthSignupConfirmPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**|  | [optional]
 **code** | **String**|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10AuthSignupInvestorPost"></a>
# **v10AuthSignupInvestorPost**
> Void v10AuthSignupInvestorPost(model)

New investor registration

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
RegisterInvestorViewModel model = new RegisterInvestorViewModel(); // RegisterInvestorViewModel | 
try {
    Void result = apiInstance.v10AuthSignupInvestorPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10AuthSignupInvestorPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**RegisterInvestorViewModel**](RegisterInvestorViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10AuthSignupManagerPost"></a>
# **v10AuthSignupManagerPost**
> Void v10AuthSignupManagerPost(model)

New manager registration

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
RegisterManagerViewModel model = new RegisterManagerViewModel(); // RegisterManagerViewModel | 
try {
    Void result = apiInstance.v10AuthSignupManagerPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10AuthSignupManagerPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**RegisterManagerViewModel**](RegisterManagerViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10AuthTokenUpdatePost"></a>
# **v10AuthTokenUpdatePost**
> String v10AuthTokenUpdatePost(authorization)

Update auth token

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    String result = apiInstance.v10AuthTokenUpdatePost(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#v10AuthTokenUpdatePost");
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

