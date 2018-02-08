# InvestorApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiInvestorAuthConfirmEmailGet**](InvestorApi.md#apiInvestorAuthConfirmEmailGet) | **GET** api/investor/auth/confirmEmail | Confirm email after registration
[**apiInvestorAuthSignInPost**](InvestorApi.md#apiInvestorAuthSignInPost) | **POST** api/investor/auth/signIn | Authorize
[**apiInvestorAuthSignUpPost**](InvestorApi.md#apiInvestorAuthSignUpPost) | **POST** api/investor/auth/signUp | Register new investor
[**apiInvestorAuthUpdateTokenGet**](InvestorApi.md#apiInvestorAuthUpdateTokenGet) | **GET** api/investor/auth/updateToken | Update auth token
[**apiInvestorDashboardGet**](InvestorApi.md#apiInvestorDashboardGet) | **GET** api/investor/dashboard | Get investor dashboard
[**apiInvestorInvestmentsInvestPost**](InvestorApi.md#apiInvestorInvestmentsInvestPost) | **POST** api/investor/investments/invest | Invest in manager
[**apiInvestorInvestmentsPost**](InvestorApi.md#apiInvestorInvestmentsPost) | **POST** api/investor/investments | Get investments by filter
[**apiInvestorInvestmentsWithdrawPost**](InvestorApi.md#apiInvestorInvestmentsWithdrawPost) | **POST** api/investor/investments/withdraw | Withdraw from investment program
[**apiInvestorProfileFullGet**](InvestorApi.md#apiInvestorProfileFullGet) | **GET** api/investor/profile/full | Get full profile
[**apiInvestorProfileGet**](InvestorApi.md#apiInvestorProfileGet) | **GET** api/investor/profile | Get short profile
[**apiInvestorProfilePublicGet**](InvestorApi.md#apiInvestorProfilePublicGet) | **GET** api/investor/profile/public | Get public profile
[**apiInvestorProfileUpdatePost**](InvestorApi.md#apiInvestorProfileUpdatePost) | **POST** api/investor/profile/update | Update profile
[**apiInvestorWalletDepositPost**](InvestorApi.md#apiInvestorWalletDepositPost) | **POST** api/investor/wallet/deposit | Deposit
[**apiInvestorWalletTransactionsPost**](InvestorApi.md#apiInvestorWalletTransactionsPost) | **POST** api/investor/wallet/transactions | Get user wallet transactions
[**apiInvestorWalletWithdrawPost**](InvestorApi.md#apiInvestorWalletWithdrawPost) | **POST** api/investor/wallet/withdraw | Withdraw


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

<a name="apiInvestorDashboardGet"></a>
# **apiInvestorDashboardGet**
> InvestorDashboard apiInvestorDashboardGet(authorization)

Get investor dashboard

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    InvestorDashboard result = apiInstance.apiInvestorDashboardGet(authorization);
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

### Return type

[**InvestorDashboard**](InvestorDashboard.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentsInvestPost"></a>
# **apiInvestorInvestmentsInvestPost**
> ProfileShortViewModel apiInvestorInvestmentsInvestPost(authorization, model)

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
    ProfileShortViewModel result = apiInstance.apiInvestorInvestmentsInvestPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentsInvestPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**Invest**](Invest.md)|  | [optional]

### Return type

[**ProfileShortViewModel**](ProfileShortViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentsPost"></a>
# **apiInvestorInvestmentsPost**
> InvestmentProgramsViewModel apiInvestorInvestmentsPost(filter)

Get investments by filter

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
InvestmentsFilter filter = new InvestmentsFilter(); // InvestmentsFilter | 
try {
    InvestmentProgramsViewModel result = apiInstance.apiInvestorInvestmentsPost(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**InvestmentsFilter**](InvestmentsFilter.md)|  | [optional]

### Return type

[**InvestmentProgramsViewModel**](InvestmentProgramsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorInvestmentsWithdrawPost"></a>
# **apiInvestorInvestmentsWithdrawPost**
> Void apiInvestorInvestmentsWithdrawPost(authorization, model)

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
    Void result = apiInstance.apiInvestorInvestmentsWithdrawPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorInvestmentsWithdrawPost");
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

<a name="apiInvestorProfileGet"></a>
# **apiInvestorProfileGet**
> ProfileShortViewModel apiInvestorProfileGet(authorization)

Get short profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    ProfileShortViewModel result = apiInstance.apiInvestorProfileGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorProfileGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**ProfileShortViewModel**](ProfileShortViewModel.md)

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

<a name="apiInvestorWalletDepositPost"></a>
# **apiInvestorWalletDepositPost**
> Void apiInvestorWalletDepositPost(authorization)

Deposit

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.apiInvestorWalletDepositPost(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorWalletDepositPost");
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

<a name="apiInvestorWalletWithdrawPost"></a>
# **apiInvestorWalletWithdrawPost**
> Void apiInvestorWalletWithdrawPost(authorization)

Withdraw

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestorApi;


InvestorApi apiInstance = new InvestorApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.apiInvestorWalletWithdrawPost(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestorApi#apiInvestorWalletWithdrawPost");
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

