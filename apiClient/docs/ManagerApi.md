# ManagerApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiManagerAccountNewInvestmentRequestPost**](ManagerApi.md#apiManagerAccountNewInvestmentRequestPost) | **POST** api/manager/account/newInvestmentRequest | Create new investment request
[**apiManagerAuthConfirmEmailGet**](ManagerApi.md#apiManagerAuthConfirmEmailGet) | **GET** api/manager/auth/confirmEmail | Confirm email after registration
[**apiManagerAuthSignInPost**](ManagerApi.md#apiManagerAuthSignInPost) | **POST** api/manager/auth/signIn | Authorize
[**apiManagerAuthSignUpPost**](ManagerApi.md#apiManagerAuthSignUpPost) | **POST** api/manager/auth/signUp | Register new manager
[**apiManagerAuthUpdateTokenGet**](ManagerApi.md#apiManagerAuthUpdateTokenGet) | **GET** api/manager/auth/updateToken | Update auth token
[**apiManagerBrokersPost**](ManagerApi.md#apiManagerBrokersPost) | **POST** api/manager/brokers | Get all enabled trade servers
[**apiManagerInvestmentCancelInvestmentRequestPost**](ManagerApi.md#apiManagerInvestmentCancelInvestmentRequestPost) | **POST** api/manager/investment/cancelInvestmentRequest | Cancel investment request
[**apiManagerInvestmentClosePost**](ManagerApi.md#apiManagerInvestmentClosePost) | **POST** api/manager/investment/close | Close existing investment program
[**apiManagerInvestmentInvestPost**](ManagerApi.md#apiManagerInvestmentInvestPost) | **POST** api/manager/investment/invest | Manager deposit in his own investment program
[**apiManagerInvestmentWithdrawPost**](ManagerApi.md#apiManagerInvestmentWithdrawPost) | **POST** api/manager/investment/withdraw | Manager withdrawal from his own investment program
[**apiManagerProfileFullGet**](ManagerApi.md#apiManagerProfileFullGet) | **GET** api/manager/profile/full | Get full profile
[**apiManagerProfileGet**](ManagerApi.md#apiManagerProfileGet) | **GET** api/manager/profile | Get short profile
[**apiManagerProfilePublicGet**](ManagerApi.md#apiManagerProfilePublicGet) | **GET** api/manager/profile/public | Get public profile
[**apiManagerProfileUpdatePost**](ManagerApi.md#apiManagerProfileUpdatePost) | **POST** api/manager/profile/update | Update profile
[**apiManagerWalletAddressGet**](ManagerApi.md#apiManagerWalletAddressGet) | **GET** api/manager/wallet/address | Get eth address for GVT depositing
[**apiManagerWalletTransactionsPost**](ManagerApi.md#apiManagerWalletTransactionsPost) | **POST** api/manager/wallet/transactions | Get user wallet transactions
[**apiManagerWalletWithdrawrequestPost**](ManagerApi.md#apiManagerWalletWithdrawrequestPost) | **POST** api/manager/wallet/withdrawrequest | Withdraw request


<a name="apiManagerAccountNewInvestmentRequestPost"></a>
# **apiManagerAccountNewInvestmentRequestPost**
> UUID apiManagerAccountNewInvestmentRequestPost(authorization, request)

Create new investment request

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
NewInvestmentRequest request = new NewInvestmentRequest(); // NewInvestmentRequest | 
try {
    UUID result = apiInstance.apiManagerAccountNewInvestmentRequestPost(authorization, request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerAccountNewInvestmentRequestPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **request** | [**NewInvestmentRequest**](NewInvestmentRequest.md)|  | [optional]

### Return type

[**UUID**](UUID.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiManagerAuthConfirmEmailGet"></a>
# **apiManagerAuthConfirmEmailGet**
> Void apiManagerAuthConfirmEmailGet(userId, code)

Confirm email after registration

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String userId = "userId_example"; // String | 
String code = "code_example"; // String | 
try {
    Void result = apiInstance.apiManagerAuthConfirmEmailGet(userId, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerAuthConfirmEmailGet");
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

<a name="apiManagerAuthSignInPost"></a>
# **apiManagerAuthSignInPost**
> String apiManagerAuthSignInPost(model)

Authorize

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
LoginViewModel model = new LoginViewModel(); // LoginViewModel | 
try {
    String result = apiInstance.apiManagerAuthSignInPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerAuthSignInPost");
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

<a name="apiManagerAuthSignUpPost"></a>
# **apiManagerAuthSignUpPost**
> Void apiManagerAuthSignUpPost(model)

Register new manager

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
RegisterManagerViewModel model = new RegisterManagerViewModel(); // RegisterManagerViewModel | 
try {
    Void result = apiInstance.apiManagerAuthSignUpPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerAuthSignUpPost");
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

<a name="apiManagerAuthUpdateTokenGet"></a>
# **apiManagerAuthUpdateTokenGet**
> String apiManagerAuthUpdateTokenGet(authorization)

Update auth token

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    String result = apiInstance.apiManagerAuthUpdateTokenGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerAuthUpdateTokenGet");
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

<a name="apiManagerBrokersPost"></a>
# **apiManagerBrokersPost**
> BrokersViewModel apiManagerBrokersPost(filter)

Get all enabled trade servers

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
BrokersFilter filter = new BrokersFilter(); // BrokersFilter | 
try {
    BrokersViewModel result = apiInstance.apiManagerBrokersPost(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerBrokersPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**BrokersFilter**](BrokersFilter.md)|  | [optional]

### Return type

[**BrokersViewModel**](BrokersViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiManagerInvestmentCancelInvestmentRequestPost"></a>
# **apiManagerInvestmentCancelInvestmentRequestPost**
> Void apiManagerInvestmentCancelInvestmentRequestPost(requestId, authorization)

Cancel investment request

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID requestId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.apiManagerInvestmentCancelInvestmentRequestPost(requestId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerInvestmentCancelInvestmentRequestPost");
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

<a name="apiManagerInvestmentClosePost"></a>
# **apiManagerInvestmentClosePost**
> Void apiManagerInvestmentClosePost(investmentProgramId, authorization)

Close existing investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID investmentProgramId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.apiManagerInvestmentClosePost(investmentProgramId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerInvestmentClosePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **investmentProgramId** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiManagerInvestmentInvestPost"></a>
# **apiManagerInvestmentInvestPost**
> Void apiManagerInvestmentInvestPost(authorization, model)

Manager deposit in his own investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
Invest model = new Invest(); // Invest | 
try {
    Void result = apiInstance.apiManagerInvestmentInvestPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerInvestmentInvestPost");
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

<a name="apiManagerInvestmentWithdrawPost"></a>
# **apiManagerInvestmentWithdrawPost**
> Void apiManagerInvestmentWithdrawPost(authorization, model)

Manager withdrawal from his own investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
Invest model = new Invest(); // Invest | 
try {
    Void result = apiInstance.apiManagerInvestmentWithdrawPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerInvestmentWithdrawPost");
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

<a name="apiManagerProfileFullGet"></a>
# **apiManagerProfileFullGet**
> ProfileFullViewModel apiManagerProfileFullGet(authorization)

Get full profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    ProfileFullViewModel result = apiInstance.apiManagerProfileFullGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerProfileFullGet");
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

<a name="apiManagerProfileGet"></a>
# **apiManagerProfileGet**
> ProfileShortViewModel apiManagerProfileGet(authorization)

Get short profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    ProfileShortViewModel result = apiInstance.apiManagerProfileGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerProfileGet");
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

<a name="apiManagerProfilePublicGet"></a>
# **apiManagerProfilePublicGet**
> ProfilePublicViewModel apiManagerProfilePublicGet(userId)

Get public profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID userId = new UUID(); // UUID | 
try {
    ProfilePublicViewModel result = apiInstance.apiManagerProfilePublicGet(userId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerProfilePublicGet");
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

<a name="apiManagerProfileUpdatePost"></a>
# **apiManagerProfileUpdatePost**
> Void apiManagerProfileUpdatePost(authorization, model)

Update profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
UpdateProfileViewModel model = new UpdateProfileViewModel(); // UpdateProfileViewModel | 
try {
    Void result = apiInstance.apiManagerProfileUpdatePost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerProfileUpdatePost");
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

<a name="apiManagerWalletAddressGet"></a>
# **apiManagerWalletAddressGet**
> WalletAddressViewModel apiManagerWalletAddressGet(authorization)

Get eth address for GVT depositing

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    WalletAddressViewModel result = apiInstance.apiManagerWalletAddressGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerWalletAddressGet");
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

<a name="apiManagerWalletTransactionsPost"></a>
# **apiManagerWalletTransactionsPost**
> WalletTransactionsViewModel apiManagerWalletTransactionsPost(authorization, filter)

Get user wallet transactions

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
TransactionsFilter filter = new TransactionsFilter(); // TransactionsFilter | 
try {
    WalletTransactionsViewModel result = apiInstance.apiManagerWalletTransactionsPost(authorization, filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerWalletTransactionsPost");
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

<a name="apiManagerWalletWithdrawrequestPost"></a>
# **apiManagerWalletWithdrawrequestPost**
> Void apiManagerWalletWithdrawrequestPost(authorization, request)

Withdraw request

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
String authorization = "authorization_example"; // String | JWT access token
WalletWithdrawRequestModel request = new WalletWithdrawRequestModel(); // WalletWithdrawRequestModel | 
try {
    Void result = apiInstance.apiManagerWalletWithdrawrequestPost(authorization, request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerWalletWithdrawrequestPost");
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

