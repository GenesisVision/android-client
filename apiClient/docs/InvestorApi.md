# InvestorApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiInvestorAuthConfirmEmailGet**](InvestorApi.md#apiInvestorAuthConfirmEmailGet) | **GET** api/investor/auth/confirmEmail | Confirm email after registration
[**apiInvestorAuthForgotPasswordPost**](InvestorApi.md#apiInvestorAuthForgotPasswordPost) | **POST** api/investor/auth/forgotPassword | Forgot password
[**apiInvestorAuthResetPasswordPost**](InvestorApi.md#apiInvestorAuthResetPasswordPost) | **POST** api/investor/auth/resetPassword | Reset password
[**apiInvestorAuthSignInPost**](InvestorApi.md#apiInvestorAuthSignInPost) | **POST** api/investor/auth/signIn | Authorize
[**apiInvestorAuthSignUpPost**](InvestorApi.md#apiInvestorAuthSignUpPost) | **POST** api/investor/auth/signUp | Register new investor
[**apiInvestorAuthUpdateTokenGet**](InvestorApi.md#apiInvestorAuthUpdateTokenGet) | **GET** api/investor/auth/updateToken | Update auth token
[**apiInvestorAuthhangePasswordPost**](InvestorApi.md#apiInvestorAuthhangePasswordPost) | **POST** api/investor/auth/сhangePassword | Change password
[**apiInvestorDashboardGet**](InvestorApi.md#apiInvestorDashboardGet) | **GET** api/investor/dashboard | Get investor dashboard
[**apiInvestorInvestmentProgramBuyTokensGet**](InvestorApi.md#apiInvestorInvestmentProgramBuyTokensGet) | **GET** api/investor/investmentProgram/buyTokens | Get investment program buy token model
[**apiInvestorInvestmentProgramGet**](InvestorApi.md#apiInvestorInvestmentProgramGet) | **GET** api/investor/investmentProgram | Get investment program details by id
[**apiInvestorInvestmentProgramOpenTradesPost**](InvestorApi.md#apiInvestorInvestmentProgramOpenTradesPost) | **POST** api/investor/investmentProgram/openTrades | Get manager open trades
[**apiInvestorInvestmentProgramRequestsPost**](InvestorApi.md#apiInvestorInvestmentProgramRequestsPost) | **POST** api/investor/investmentProgram/requests | Get investment program&#39;s requests
[**apiInvestorInvestmentProgramTradesPost**](InvestorApi.md#apiInvestorInvestmentProgramTradesPost) | **POST** api/investor/investmentProgram/trades | Get manager trade history
[**apiInvestorInvestmentProgramsCancelInvestmentRequestPost**](InvestorApi.md#apiInvestorInvestmentProgramsCancelInvestmentRequestPost) | **POST** api/investor/investmentPrograms/cancelInvestmentRequest | Cancel investment request
[**apiInvestorInvestmentProgramsInvestPost**](InvestorApi.md#apiInvestorInvestmentProgramsInvestPost) | **POST** api/investor/investmentPrograms/invest | Invest in manager
[**apiInvestorInvestmentProgramsPost**](InvestorApi.md#apiInvestorInvestmentProgramsPost) | **POST** api/investor/investmentPrograms | Get public investment program&#39;s list
[**apiInvestorInvestmentProgramsWithdrawPost**](InvestorApi.md#apiInvestorInvestmentProgramsWithdrawPost) | **POST** api/investor/investmentPrograms/withdraw | Withdraw from investment program
[**apiInvestorProfileFullGet**](InvestorApi.md#apiInvestorProfileFullGet) | **GET** api/investor/profile/full | Get full profile
[**apiInvestorProfilePublicGet**](InvestorApi.md#apiInvestorProfilePublicGet) | **GET** api/investor/profile/public | Get public profile
[**apiInvestorProfileUpdatePost**](InvestorApi.md#apiInvestorProfileUpdatePost) | **POST** api/investor/profile/update | Update profile
[**apiInvestorWalletAddressGet**](InvestorApi.md#apiInvestorWalletAddressGet) | **GET** api/investor/wallet/address | Get eth address for GVT depositing
[**apiInvestorWalletGet**](InvestorApi.md#apiInvestorWalletGet) | **GET** api/investor/wallet | Get user wallets
[**apiInvestorWalletStatisticPost**](InvestorApi.md#apiInvestorWalletStatisticPost) | **POST** api/investor/wallet/statistic | Get user wallet statistic
[**apiInvestorWalletTransactionsInvestmentProgramsListGet**](InvestorApi.md#apiInvestorWalletTransactionsInvestmentProgramsListGet) | **GET** api/investor/wallet/transactions/investmentProgramsList | Get user investment programs with tx
[**apiInvestorWalletTransactionsPost**](InvestorApi.md#apiInvestorWalletTransactionsPost) | **POST** api/investor/wallet/transactions | Get user wallet transactions
[**apiInvestorWalletWithdrawRequestPost**](InvestorApi.md#apiInvestorWalletWithdrawRequestPost) | **POST** api/investor/wallet/withdrawRequest | Withdraw request


<a name="apiInvestorAuthConfirmEmailGet"></a>
# **apiInvestorAuthConfirmEmailGet**
> Void apiInvestorAuthConfirmEmailGet(userId, code)

Confirm email after registration

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String userId = "userId_example"; // String | 
String code = "code_example"; // String | 
try {
    Void result = apiInstance.apiInvestorAuthConfirmEmailGet(userId, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorAuthConfirmEmailGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**|  | [optional]
 **code** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorAuthForgotPasswordPost"></a>
# **apiInvestorAuthForgotPasswordPost**
> Void apiInvestorAuthForgotPasswordPost(model)

Forgot password

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
ForgotPasswordViewModel model = new ForgotPasswordViewModel(); // ForgotPasswordViewModel | 
try {
    Void result = apiInstance.apiInvestorAuthForgotPasswordPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorAuthForgotPasswordPost");
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

<a name="apiInvestorAuthResetPasswordPost"></a>
# **apiInvestorAuthResetPasswordPost**
> String apiInvestorAuthResetPasswordPost(model)

Reset password

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
ResetPasswordViewModel model = new ResetPasswordViewModel(); // ResetPasswordViewModel | 
try {
    String result = apiInstance.apiInvestorAuthResetPasswordPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorAuthResetPasswordPost");
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

<a name="apiInvestorAuthSignInPost"></a>
# **apiInvestorAuthSignInPost**
> String apiInvestorAuthSignInPost(model)

Authorize

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
LoginViewModel model = new LoginViewModel(); // LoginViewModel | 
try {
    String result = apiInstance.apiInvestorAuthSignInPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorAuthSignInPost");
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

<a name="apiInvestorAuthSignUpPost"></a>
# **apiInvestorAuthSignUpPost**
> Void apiInvestorAuthSignUpPost(model)

Register new investor

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
RegisterInvestorViewModel model = new RegisterInvestorViewModel(); // RegisterInvestorViewModel | 
try {
    Void result = apiInstance.apiInvestorAuthSignUpPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorAuthSignUpPost");
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

<a name="apiInvestorAuthUpdateTokenGet"></a>
# **apiInvestorAuthUpdateTokenGet**
> String apiInvestorAuthUpdateTokenGet(authorization)

Update auth token

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    String result = apiInstance.apiInvestorAuthUpdateTokenGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorAuthUpdateTokenGet");
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

<a name="apiInvestorAuthhangePasswordPost"></a>
# **apiInvestorAuthhangePasswordPost**
> Void apiInvestorAuthhangePasswordPost(authorization, model)

Change password

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
ChangePasswordViewModel model = new ChangePasswordViewModel(); // ChangePasswordViewModel | 
try {
    Void result = apiInstance.apiInvestorAuthhangePasswordPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorAuthhangePasswordPost");
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

<a name="apiInvestorDashboardGet"></a>
# **apiInvestorDashboardGet**
> InvestorDashboard apiInvestorDashboardGet(authorization, sorting)

Get investor dashboard

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
String sorting = "sorting_example"; // String | 
try {
    InvestorDashboard result = apiInstance.apiInvestorDashboardGet(authorization, sorting);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorDashboardGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **sorting** | **String**|  | [optional] [enum: ByLevelAsc, ByLevelDesc, ByProfitAsc, ByProfitDesc, ByOrdersAsc, ByOrdersDesc, ByEndOfPeriodAsk, ByEndOfPeriodDesc, ByTitleAsk, ByTitleDesc]

### Return type

[**InvestorDashboard**](InvestorDashboard.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentProgramBuyTokensGet"></a>
# **apiInvestorInvestmentProgramBuyTokensGet**
> InvestmentProgramBuyToken apiInvestorInvestmentProgramBuyTokensGet(investmentProgramId, authorization)

Get investment program buy token model

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID investmentProgramId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    InvestmentProgramBuyToken result = apiInstance.apiInvestorInvestmentProgramBuyTokensGet(investmentProgramId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentProgramBuyTokensGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **investmentProgramId** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**InvestmentProgramBuyToken**](InvestmentProgramBuyToken.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentProgramGet"></a>
# **apiInvestorInvestmentProgramGet**
> InvestmentProgramViewModel apiInvestorInvestmentProgramGet(investmentProgramId, authorization)

Get investment program details by id

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID investmentProgramId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | 
try {
    InvestmentProgramViewModel result = apiInstance.apiInvestorInvestmentProgramGet(investmentProgramId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentProgramGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **investmentProgramId** | [**UUID**](.md)|  |
 **authorization** | **String**|  | [optional]

### Return type

[**InvestmentProgramViewModel**](InvestmentProgramViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentProgramOpenTradesPost"></a>
# **apiInvestorInvestmentProgramOpenTradesPost**
> OpenTradesViewModel apiInvestorInvestmentProgramOpenTradesPost(authorization, filter)

Get manager open trades

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
TradesFilter filter = new TradesFilter(); // TradesFilter | 
try {
    OpenTradesViewModel result = apiInstance.apiInvestorInvestmentProgramOpenTradesPost(authorization, filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentProgramOpenTradesPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **filter** | [**TradesFilter**](TradesFilter.md)|  | [optional]

### Return type

[**OpenTradesViewModel**](OpenTradesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentProgramRequestsPost"></a>
# **apiInvestorInvestmentProgramRequestsPost**
> InvestmentProgramRequests apiInvestorInvestmentProgramRequestsPost(authorization, filter)

Get investment program&#39;s requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
InvestmentProgramRequestsFilter filter = new InvestmentProgramRequestsFilter(); // InvestmentProgramRequestsFilter | 
try {
    InvestmentProgramRequests result = apiInstance.apiInvestorInvestmentProgramRequestsPost(authorization, filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentProgramRequestsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **filter** | [**InvestmentProgramRequestsFilter**](InvestmentProgramRequestsFilter.md)|  | [optional]

### Return type

[**InvestmentProgramRequests**](InvestmentProgramRequests.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentProgramTradesPost"></a>
# **apiInvestorInvestmentProgramTradesPost**
> TradesViewModel apiInvestorInvestmentProgramTradesPost(filter)

Get manager trade history

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
TradesFilter filter = new TradesFilter(); // TradesFilter | 
try {
    TradesViewModel result = apiInstance.apiInvestorInvestmentProgramTradesPost(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentProgramTradesPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**TradesFilter**](TradesFilter.md)|  | [optional]

### Return type

[**TradesViewModel**](TradesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentProgramsCancelInvestmentRequestPost"></a>
# **apiInvestorInvestmentProgramsCancelInvestmentRequestPost**
> Void apiInvestorInvestmentProgramsCancelInvestmentRequestPost(requestId, authorization)

Cancel investment request

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID requestId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.apiInvestorInvestmentProgramsCancelInvestmentRequestPost(requestId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentProgramsCancelInvestmentRequestPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestId** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentProgramsInvestPost"></a>
# **apiInvestorInvestmentProgramsInvestPost**
> WalletsViewModel apiInvestorInvestmentProgramsInvestPost(authorization, model)

Invest in manager

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
Invest model = new Invest(); // Invest | 
try {
    WalletsViewModel result = apiInstance.apiInvestorInvestmentProgramsInvestPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentProgramsInvestPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**Invest**](Invest.md)|  | [optional]

### Return type

[**WalletsViewModel**](WalletsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentProgramsPost"></a>
# **apiInvestorInvestmentProgramsPost**
> InvestmentProgramsViewModel apiInvestorInvestmentProgramsPost(authorization, filter)

Get public investment program&#39;s list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | 
InvestmentProgramsFilter filter = new InvestmentProgramsFilter(); // InvestmentProgramsFilter | 
try {
    InvestmentProgramsViewModel result = apiInstance.apiInvestorInvestmentProgramsPost(authorization, filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentProgramsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**|  | [optional]
 **filter** | [**InvestmentProgramsFilter**](InvestmentProgramsFilter.md)|  | [optional]

### Return type

[**InvestmentProgramsViewModel**](InvestmentProgramsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentProgramsWithdrawPost"></a>
# **apiInvestorInvestmentProgramsWithdrawPost**
> Void apiInvestorInvestmentProgramsWithdrawPost(authorization, model)

Withdraw from investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
Invest model = new Invest(); // Invest | 
try {
    Void result = apiInstance.apiInvestorInvestmentProgramsWithdrawPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentProgramsWithdrawPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**Invest**](Invest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorProfileFullGet"></a>
# **apiInvestorProfileFullGet**
> ProfileFullViewModel apiInvestorProfileFullGet(authorization)

Get full profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    ProfileFullViewModel result = apiInstance.apiInvestorProfileFullGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorProfileFullGet");
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

<a name="apiInvestorProfilePublicGet"></a>
# **apiInvestorProfilePublicGet**
> ProfilePublicViewModel apiInvestorProfilePublicGet(userId)

Get public profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
UUID userId = new UUID(); // UUID | 
try {
    ProfilePublicViewModel result = apiInstance.apiInvestorProfilePublicGet(userId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorProfilePublicGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | [**UUID**](.md)|  |

### Return type

[**ProfilePublicViewModel**](ProfilePublicViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorProfileUpdatePost"></a>
# **apiInvestorProfileUpdatePost**
> Void apiInvestorProfileUpdatePost(authorization, model)

Update profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
UpdateProfileViewModel model = new UpdateProfileViewModel(); // UpdateProfileViewModel | 
try {
    Void result = apiInstance.apiInvestorProfileUpdatePost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorProfileUpdatePost");
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

<a name="apiInvestorWalletAddressGet"></a>
# **apiInvestorWalletAddressGet**
> WalletAddressViewModel apiInvestorWalletAddressGet(authorization)

Get eth address for GVT depositing

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    WalletAddressViewModel result = apiInstance.apiInvestorWalletAddressGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorWalletAddressGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**WalletAddressViewModel**](WalletAddressViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorWalletGet"></a>
# **apiInvestorWalletGet**
> WalletsViewModel apiInvestorWalletGet(authorization)

Get user wallets

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    WalletsViewModel result = apiInstance.apiInvestorWalletGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorWalletGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**WalletsViewModel**](WalletsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorWalletStatisticPost"></a>
# **apiInvestorWalletStatisticPost**
> WalletStatistic apiInvestorWalletStatisticPost(authorization, filter)

Get user wallet statistic

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
WalletStatisticFilter filter = new WalletStatisticFilter(); // WalletStatisticFilter | 
try {
    WalletStatistic result = apiInstance.apiInvestorWalletStatisticPost(authorization, filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorWalletStatisticPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **filter** | [**WalletStatisticFilter**](WalletStatisticFilter.md)|  | [optional]

### Return type

[**WalletStatistic**](WalletStatistic.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorWalletTransactionsInvestmentProgramsListGet"></a>
# **apiInvestorWalletTransactionsInvestmentProgramsListGet**
> WalletInvestmentPrograms apiInvestorWalletTransactionsInvestmentProgramsListGet(authorization, mask)

Get user investment programs with tx

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
String mask = "mask_example"; // String | 
try {
    WalletInvestmentPrograms result = apiInstance.apiInvestorWalletTransactionsInvestmentProgramsListGet(authorization, mask);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorWalletTransactionsInvestmentProgramsListGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **mask** | **String**|  | [optional]

### Return type

[**WalletInvestmentPrograms**](WalletInvestmentPrograms.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorWalletTransactionsPost"></a>
# **apiInvestorWalletTransactionsPost**
> WalletTransactionsViewModel apiInvestorWalletTransactionsPost(authorization, filter)

Get user wallet transactions

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
TransactionsFilter filter = new TransactionsFilter(); // TransactionsFilter | 
try {
    WalletTransactionsViewModel result = apiInstance.apiInvestorWalletTransactionsPost(authorization, filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorWalletTransactionsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **filter** | [**TransactionsFilter**](TransactionsFilter.md)|  | [optional]

### Return type

[**WalletTransactionsViewModel**](WalletTransactionsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorWalletWithdrawRequestPost"></a>
# **apiInvestorWalletWithdrawRequestPost**
> Void apiInvestorWalletWithdrawRequestPost(authorization, request)

Withdraw request

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
WalletWithdrawRequestModel request = new WalletWithdrawRequestModel(); // WalletWithdrawRequestModel | 
try {
    Void result = apiInstance.apiInvestorWalletWithdrawRequestPost(authorization, request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorWalletWithdrawRequestPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **request** | [**WalletWithdrawRequestModel**](WalletWithdrawRequestModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

