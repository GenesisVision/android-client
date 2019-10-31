# AuthApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authorize**](AuthApi.md#authorize) | **POST** v2.0/auth/signin | Authorize
[**changePassword**](AuthApi.md#changePassword) | **POST** v2.0/auth/password/change | Change password
[**confirmEmail**](AuthApi.md#confirmEmail) | **POST** v2.0/auth/signup/confirm | Confirm email after registration
[**confirmTwoStepAuth**](AuthApi.md#confirmTwoStepAuth) | **POST** v2.0/auth/2fa/confirm | 2FA confirm
[**createTwoStepAuth**](AuthApi.md#createTwoStepAuth) | **POST** v2.0/auth/2fa/create | 2FA create
[**createTwoStepAuthRecoveryCodes**](AuthApi.md#createTwoStepAuthRecoveryCodes) | **POST** v2.0/auth/2fa/recoverycodes/new | 2FA generate new recovery codes
[**disableTwoStepAuth**](AuthApi.md#disableTwoStepAuth) | **POST** v2.0/auth/2fa/disable | 2FA disable
[**forgotPassword**](AuthApi.md#forgotPassword) | **POST** v2.0/auth/password/forgot | Forgot password for investor
[**getTwoStepAuthRecoveryCodes**](AuthApi.md#getTwoStepAuthRecoveryCodes) | **POST** v2.0/auth/2fa/recoverycodes | 2FA recovery codes
[**getTwoStepAuthStatus**](AuthApi.md#getTwoStepAuthStatus) | **GET** v2.0/auth/2fa | 2FA status
[**logoutFromAnotherDevices**](AuthApi.md#logoutFromAnotherDevices) | **POST** v2.0/auth/token/devices/logout | Logout from another devices
[**register**](AuthApi.md#register) | **POST** v2.0/auth/signup | New registration
[**requestPhoneNumberVerificationCode**](AuthApi.md#requestPhoneNumberVerificationCode) | **POST** v2.0/auth/phone/code | Get phone number verification code
[**resendConfirmationLink**](AuthApi.md#resendConfirmationLink) | **POST** v2.0/auth/resendconfirmationlink | Resend Confirmation Link
[**resetPassword**](AuthApi.md#resetPassword) | **POST** v2.0/auth/password/reset | Reset password
[**updateAuthToken**](AuthApi.md#updateAuthToken) | **POST** v2.0/auth/token/update | Update auth token
[**validatePhoneNumber**](AuthApi.md#validatePhoneNumber) | **POST** v2.0/auth/phone/verify | Verify phone number

<a name="authorize"></a>
# **authorize**
> String authorize(body)

Authorize

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
LoginViewModel body = new LoginViewModel(); // LoginViewModel | 
try {
    String result = apiInstance.authorize(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#authorize");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**LoginViewModel**](LoginViewModel.md)|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="changePassword"></a>
# **changePassword**
> String changePassword(authorization, body)

Change password

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
ChangePasswordViewModel body = new ChangePasswordViewModel(); // ChangePasswordViewModel | 
try {
    String result = apiInstance.changePassword(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#changePassword");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**ChangePasswordViewModel**](ChangePasswordViewModel.md)|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="confirmEmail"></a>
# **confirmEmail**
> String confirmEmail(userId, code)

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
    String result = apiInstance.confirmEmail(userId, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#confirmEmail");
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

<a name="confirmTwoStepAuth"></a>
# **confirmTwoStepAuth**
> RecoveryCodesViewModel confirmTwoStepAuth(authorization, body)

2FA confirm

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
TwoFactorAuthenticatorConfirm body = new TwoFactorAuthenticatorConfirm(); // TwoFactorAuthenticatorConfirm | 
try {
    RecoveryCodesViewModel result = apiInstance.confirmTwoStepAuth(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#confirmTwoStepAuth");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**TwoFactorAuthenticatorConfirm**](TwoFactorAuthenticatorConfirm.md)|  | [optional]

### Return type

[**RecoveryCodesViewModel**](RecoveryCodesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="createTwoStepAuth"></a>
# **createTwoStepAuth**
> TwoFactorAuthenticator createTwoStepAuth(authorization)

2FA create

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    TwoFactorAuthenticator result = apiInstance.createTwoStepAuth(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#createTwoStepAuth");
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

<a name="createTwoStepAuthRecoveryCodes"></a>
# **createTwoStepAuthRecoveryCodes**
> RecoveryCodesViewModel createTwoStepAuthRecoveryCodes(authorization, body)

2FA generate new recovery codes

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
PasswordModel body = new PasswordModel(); // PasswordModel | 
try {
    RecoveryCodesViewModel result = apiInstance.createTwoStepAuthRecoveryCodes(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#createTwoStepAuthRecoveryCodes");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**PasswordModel**](PasswordModel.md)|  | [optional]

### Return type

[**RecoveryCodesViewModel**](RecoveryCodesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="disableTwoStepAuth"></a>
# **disableTwoStepAuth**
> Void disableTwoStepAuth(authorization, body)

2FA disable

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
TwoFactorCodeWithPassword body = new TwoFactorCodeWithPassword(); // TwoFactorCodeWithPassword | 
try {
    Void result = apiInstance.disableTwoStepAuth(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#disableTwoStepAuth");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**TwoFactorCodeWithPassword**](TwoFactorCodeWithPassword.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="forgotPassword"></a>
# **forgotPassword**
> Void forgotPassword(body)

Forgot password for investor

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
ForgotPasswordViewModel body = new ForgotPasswordViewModel(); // ForgotPasswordViewModel | 
try {
    Void result = apiInstance.forgotPassword(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#forgotPassword");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ForgotPasswordViewModel**](ForgotPasswordViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="getTwoStepAuthRecoveryCodes"></a>
# **getTwoStepAuthRecoveryCodes**
> RecoveryCodesViewModel getTwoStepAuthRecoveryCodes(authorization, body)

2FA recovery codes

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
PasswordModel body = new PasswordModel(); // PasswordModel | 
try {
    RecoveryCodesViewModel result = apiInstance.getTwoStepAuthRecoveryCodes(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#getTwoStepAuthRecoveryCodes");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**PasswordModel**](PasswordModel.md)|  | [optional]

### Return type

[**RecoveryCodesViewModel**](RecoveryCodesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="getTwoStepAuthStatus"></a>
# **getTwoStepAuthStatus**
> TwoFactorStatus getTwoStepAuthStatus(authorization)

2FA status

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    TwoFactorStatus result = apiInstance.getTwoStepAuthStatus(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#getTwoStepAuthStatus");
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

<a name="logoutFromAnotherDevices"></a>
# **logoutFromAnotherDevices**
> String logoutFromAnotherDevices(authorization)

Logout from another devices

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    String result = apiInstance.logoutFromAnotherDevices(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#logoutFromAnotherDevices");
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

<a name="register"></a>
# **register**
> Void register(body)

New registration

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
RegisterViewModel body = new RegisterViewModel(); // RegisterViewModel | 
try {
    Void result = apiInstance.register(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#register");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**RegisterViewModel**](RegisterViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="requestPhoneNumberVerificationCode"></a>
# **requestPhoneNumberVerificationCode**
> Integer requestPhoneNumberVerificationCode(authorization)

Get phone number verification code

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Integer result = apiInstance.requestPhoneNumberVerificationCode(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#requestPhoneNumberVerificationCode");
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

<a name="resendConfirmationLink"></a>
# **resendConfirmationLink**
> Void resendConfirmationLink(body)

Resend Confirmation Link

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
ResendConfirmationViewModel body = new ResendConfirmationViewModel(); // ResendConfirmationViewModel | 
try {
    Void result = apiInstance.resendConfirmationLink(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#resendConfirmationLink");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ResendConfirmationViewModel**](ResendConfirmationViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="resetPassword"></a>
# **resetPassword**
> String resetPassword(body)

Reset password

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
ResetPasswordViewModel body = new ResetPasswordViewModel(); // ResetPasswordViewModel | 
try {
    String result = apiInstance.resetPassword(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#resetPassword");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ResetPasswordViewModel**](ResetPasswordViewModel.md)|  | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateAuthToken"></a>
# **updateAuthToken**
> String updateAuthToken(authorization)

Update auth token

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    String result = apiInstance.updateAuthToken(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#updateAuthToken");
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

<a name="validatePhoneNumber"></a>
# **validatePhoneNumber**
> Void validatePhoneNumber(authorization, code)

Verify phone number

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthApi;


AuthApi apiInstance = new AuthApi();
String authorization = "authorization_example"; // String | JWT access token
String code = "code_example"; // String | 
try {
    Void result = apiInstance.validatePhoneNumber(authorization, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#validatePhoneNumber");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **code** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

