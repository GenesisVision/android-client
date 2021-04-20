# AuthApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authorize**](AuthApi.md#authorize) | **POST** v2.0/auth/signin | Authorize
[**authorizeBySignature**](AuthApi.md#authorizeBySignature) | **POST** v2.0/auth/signature/signin | Authorize by signature
[**changePassword**](AuthApi.md#changePassword) | **POST** v2.0/auth/password/change | Change password
[**confirmEmail**](AuthApi.md#confirmEmail) | **POST** v2.0/auth/signup/confirm | Confirm email after registration
[**confirmThreeStepAuth**](AuthApi.md#confirmThreeStepAuth) | **POST** v2.0/auth/3fa/confirm | 3FA confirm
[**confirmTwoStepAuth**](AuthApi.md#confirmTwoStepAuth) | **POST** v2.0/auth/2fa/confirm | 2FA confirm
[**createTwoStepAuth**](AuthApi.md#createTwoStepAuth) | **POST** v2.0/auth/2fa/create | 2FA create
[**createTwoStepAuthRecoveryCodes**](AuthApi.md#createTwoStepAuthRecoveryCodes) | **POST** v2.0/auth/2fa/recoverycodes/new | 2FA generate new recovery codes
[**disableTwoStepAuth**](AuthApi.md#disableTwoStepAuth) | **POST** v2.0/auth/2fa/disable | 2FA disable
[**forgotPassword**](AuthApi.md#forgotPassword) | **POST** v2.0/auth/password/forgot | Forgot password
[**getTwoStepAuthRecoveryCodes**](AuthApi.md#getTwoStepAuthRecoveryCodes) | **POST** v2.0/auth/2fa/recoverycodes | 2FA recovery codes
[**getTwoStepAuthStatus**](AuthApi.md#getTwoStepAuthStatus) | **GET** v2.0/auth/2fa | 2FA status
[**logoutFromAnotherDevices**](AuthApi.md#logoutFromAnotherDevices) | **POST** v2.0/auth/token/devices/logout | Logout from another devices
[**register**](AuthApi.md#register) | **POST** v2.0/auth/signup | New registration
[**resendConfirmationLink**](AuthApi.md#resendConfirmationLink) | **POST** v2.0/auth/resendconfirmationlink | Resend Confirmation Link
[**resetPassword**](AuthApi.md#resetPassword) | **POST** v2.0/auth/password/reset | Reset password
[**updateAuthToken**](AuthApi.md#updateAuthToken) | **POST** v2.0/auth/token/update | Update auth token

<a name="authorize"></a>
# **authorize**
> String authorize(body)

Authorize

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

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

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="authorizeBySignature"></a>
# **authorizeBySignature**
> String authorizeBySignature(body)

Authorize by signature

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AuthApi apiInstance = new AuthApi();
LoginSignViewModel body = new LoginSignViewModel(); // LoginSignViewModel | 
try {
    String result = apiInstance.authorizeBySignature(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#authorizeBySignature");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**LoginSignViewModel**](LoginSignViewModel.md)|  | [optional]

### Return type

**String**

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="changePassword"></a>
# **changePassword**
> String changePassword(body)

Change password

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AuthApi apiInstance = new AuthApi();
ChangePasswordViewModel body = new ChangePasswordViewModel(); // ChangePasswordViewModel | 
try {
    String result = apiInstance.changePassword(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#changePassword");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ChangePasswordViewModel**](ChangePasswordViewModel.md)|  | [optional]

### Return type

**String**

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="confirmEmail"></a>
# **confirmEmail**
> String confirmEmail(userId, code)

Confirm email after registration

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

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

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="confirmThreeStepAuth"></a>
# **confirmThreeStepAuth**
> String confirmThreeStepAuth(body)

3FA confirm

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AuthApi apiInstance = new AuthApi();
ThreeFactorAuthenticatorConfirm body = new ThreeFactorAuthenticatorConfirm(); // ThreeFactorAuthenticatorConfirm | 
try {
    String result = apiInstance.confirmThreeStepAuth(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#confirmThreeStepAuth");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ThreeFactorAuthenticatorConfirm**](ThreeFactorAuthenticatorConfirm.md)|  | [optional]

### Return type

**String**

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="confirmTwoStepAuth"></a>
# **confirmTwoStepAuth**
> RecoveryCodesViewModel confirmTwoStepAuth(body)

2FA confirm

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AuthApi apiInstance = new AuthApi();
TwoFactorAuthenticatorConfirm body = new TwoFactorAuthenticatorConfirm(); // TwoFactorAuthenticatorConfirm | 
try {
    RecoveryCodesViewModel result = apiInstance.confirmTwoStepAuth(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#confirmTwoStepAuth");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**TwoFactorAuthenticatorConfirm**](TwoFactorAuthenticatorConfirm.md)|  | [optional]

### Return type

[**RecoveryCodesViewModel**](RecoveryCodesViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="createTwoStepAuth"></a>
# **createTwoStepAuth**
> TwoFactorAuthenticator createTwoStepAuth()

2FA create

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AuthApi apiInstance = new AuthApi();
try {
    TwoFactorAuthenticator result = apiInstance.createTwoStepAuth();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#createTwoStepAuth");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**TwoFactorAuthenticator**](TwoFactorAuthenticator.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="createTwoStepAuthRecoveryCodes"></a>
# **createTwoStepAuthRecoveryCodes**
> RecoveryCodesViewModel createTwoStepAuthRecoveryCodes(body)

2FA generate new recovery codes

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AuthApi apiInstance = new AuthApi();
PasswordModel body = new PasswordModel(); // PasswordModel | 
try {
    RecoveryCodesViewModel result = apiInstance.createTwoStepAuthRecoveryCodes(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#createTwoStepAuthRecoveryCodes");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**PasswordModel**](PasswordModel.md)|  | [optional]

### Return type

[**RecoveryCodesViewModel**](RecoveryCodesViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="disableTwoStepAuth"></a>
# **disableTwoStepAuth**
> Void disableTwoStepAuth(body)

2FA disable

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AuthApi apiInstance = new AuthApi();
TwoFactorCodeWithPassword body = new TwoFactorCodeWithPassword(); // TwoFactorCodeWithPassword | 
try {
    Void result = apiInstance.disableTwoStepAuth(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#disableTwoStepAuth");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**TwoFactorCodeWithPassword**](TwoFactorCodeWithPassword.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="forgotPassword"></a>
# **forgotPassword**
> Void forgotPassword(body)

Forgot password

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

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

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="getTwoStepAuthRecoveryCodes"></a>
# **getTwoStepAuthRecoveryCodes**
> RecoveryCodesViewModel getTwoStepAuthRecoveryCodes(body)

2FA recovery codes

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AuthApi apiInstance = new AuthApi();
PasswordModel body = new PasswordModel(); // PasswordModel | 
try {
    RecoveryCodesViewModel result = apiInstance.getTwoStepAuthRecoveryCodes(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#getTwoStepAuthRecoveryCodes");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**PasswordModel**](PasswordModel.md)|  | [optional]

### Return type

[**RecoveryCodesViewModel**](RecoveryCodesViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="getTwoStepAuthStatus"></a>
# **getTwoStepAuthStatus**
> TwoFactorStatus getTwoStepAuthStatus()

2FA status

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AuthApi apiInstance = new AuthApi();
try {
    TwoFactorStatus result = apiInstance.getTwoStepAuthStatus();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#getTwoStepAuthStatus");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**TwoFactorStatus**](TwoFactorStatus.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="logoutFromAnotherDevices"></a>
# **logoutFromAnotherDevices**
> String logoutFromAnotherDevices()

Logout from another devices

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AuthApi apiInstance = new AuthApi();
try {
    String result = apiInstance.logoutFromAnotherDevices();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#logoutFromAnotherDevices");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

[Bearer](../README.md#Bearer)

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
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

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

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="resendConfirmationLink"></a>
# **resendConfirmationLink**
> Void resendConfirmationLink(body)

Resend Confirmation Link

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

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

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="resetPassword"></a>
# **resetPassword**
> String resetPassword(body)

Reset password

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

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

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateAuthToken"></a>
# **updateAuthToken**
> String updateAuthToken()

Update auth token

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AuthApi apiInstance = new AuthApi();
try {
    String result = apiInstance.updateAuthToken();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#updateAuthToken");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

