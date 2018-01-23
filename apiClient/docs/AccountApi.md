# AccountApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiBrokerAuthSignInPost**](AccountApi.md#apiBrokerAuthSignInPost) | **POST** api/broker/auth/signIn | 
[**apiBrokerAuthUpdateTokenGet**](AccountApi.md#apiBrokerAuthUpdateTokenGet) | **GET** api/broker/auth/updateToken | 
[**apiInvestorAuthConfirmEmailGet**](AccountApi.md#apiInvestorAuthConfirmEmailGet) | **GET** api/investor/auth/confirmEmail | 
[**apiInvestorAuthSignInPost**](AccountApi.md#apiInvestorAuthSignInPost) | **POST** api/investor/auth/signIn | 
[**apiInvestorAuthSignUpPost**](AccountApi.md#apiInvestorAuthSignUpPost) | **POST** api/investor/auth/signUp | 
[**apiInvestorAuthUpdateTokenGet**](AccountApi.md#apiInvestorAuthUpdateTokenGet) | **GET** api/investor/auth/updateToken | 
[**apiInvestorProfileFullGet**](AccountApi.md#apiInvestorProfileFullGet) | **GET** api/investor/profile/full | 
[**apiInvestorProfileGet**](AccountApi.md#apiInvestorProfileGet) | **GET** api/investor/profile | 
[**apiInvestorProfileUpdatePost**](AccountApi.md#apiInvestorProfileUpdatePost) | **POST** api/investor/profile/update | 
[**apiManagerAuthConfirmEmailGet**](AccountApi.md#apiManagerAuthConfirmEmailGet) | **GET** api/manager/auth/confirmEmail | 
[**apiManagerAuthSignInPost**](AccountApi.md#apiManagerAuthSignInPost) | **POST** api/manager/auth/signIn | 
[**apiManagerAuthSignUpPost**](AccountApi.md#apiManagerAuthSignUpPost) | **POST** api/manager/auth/signUp | 
[**apiManagerAuthUpdateTokenGet**](AccountApi.md#apiManagerAuthUpdateTokenGet) | **GET** api/manager/auth/updateToken | 
[**apiManagerProfileFullGet**](AccountApi.md#apiManagerProfileFullGet) | **GET** api/manager/profile/full | 
[**apiManagerProfileGet**](AccountApi.md#apiManagerProfileGet) | **GET** api/manager/profile | 
[**apiManagerProfileUpdatePost**](AccountApi.md#apiManagerProfileUpdatePost) | **POST** api/manager/profile/update | 


<a name="apiBrokerAuthSignInPost"></a>
# **apiBrokerAuthSignInPost**
> String apiBrokerAuthSignInPost(model)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
LoginViewModel model = new LoginViewModel(); // LoginViewModel | 
try {
    String result = apiInstance.apiBrokerAuthSignInPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiBrokerAuthSignInPost");
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

<a name="apiBrokerAuthUpdateTokenGet"></a>
# **apiBrokerAuthUpdateTokenGet**
> String apiBrokerAuthUpdateTokenGet()



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
try {
    String result = apiInstance.apiBrokerAuthUpdateTokenGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiBrokerAuthUpdateTokenGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorAuthConfirmEmailGet"></a>
# **apiInvestorAuthConfirmEmailGet**
> Void apiInvestorAuthConfirmEmailGet(userId, code)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
String userId = "userId_example"; // String | 
String code = "code_example"; // String | 
try {
    Void result = apiInstance.apiInvestorAuthConfirmEmailGet(userId, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiInvestorAuthConfirmEmailGet");
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



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
LoginViewModel model = new LoginViewModel(); // LoginViewModel | 
try {
    String result = apiInstance.apiInvestorAuthSignInPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiInvestorAuthSignInPost");
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



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
RegisterInvestorViewModel model = new RegisterInvestorViewModel(); // RegisterInvestorViewModel | 
try {
    Void result = apiInstance.apiInvestorAuthSignUpPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiInvestorAuthSignUpPost");
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
> String apiInvestorAuthUpdateTokenGet()



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
try {
    String result = apiInstance.apiInvestorAuthUpdateTokenGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiInvestorAuthUpdateTokenGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorProfileFullGet"></a>
# **apiInvestorProfileFullGet**
> ProfileFullViewModel apiInvestorProfileFullGet()



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
try {
    ProfileFullViewModel result = apiInstance.apiInvestorProfileFullGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiInvestorProfileFullGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ProfileFullViewModel**](ProfileFullViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorProfileGet"></a>
# **apiInvestorProfileGet**
> ProfileShortViewModel apiInvestorProfileGet()



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
try {
    ProfileShortViewModel result = apiInstance.apiInvestorProfileGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiInvestorProfileGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ProfileShortViewModel**](ProfileShortViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiInvestorProfileUpdatePost"></a>
# **apiInvestorProfileUpdatePost**
> Void apiInvestorProfileUpdatePost(model)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
ProfileFullViewModel model = new ProfileFullViewModel(); // ProfileFullViewModel | 
try {
    Void result = apiInstance.apiInvestorProfileUpdatePost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiInvestorProfileUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**ProfileFullViewModel**](ProfileFullViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiManagerAuthConfirmEmailGet"></a>
# **apiManagerAuthConfirmEmailGet**
> Void apiManagerAuthConfirmEmailGet(userId, code)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
String userId = "userId_example"; // String | 
String code = "code_example"; // String | 
try {
    Void result = apiInstance.apiManagerAuthConfirmEmailGet(userId, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiManagerAuthConfirmEmailGet");
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



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
LoginViewModel model = new LoginViewModel(); // LoginViewModel | 
try {
    String result = apiInstance.apiManagerAuthSignInPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiManagerAuthSignInPost");
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



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
RegisterManagerViewModel model = new RegisterManagerViewModel(); // RegisterManagerViewModel | 
try {
    Void result = apiInstance.apiManagerAuthSignUpPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiManagerAuthSignUpPost");
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
> String apiManagerAuthUpdateTokenGet()



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
try {
    String result = apiInstance.apiManagerAuthUpdateTokenGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiManagerAuthUpdateTokenGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiManagerProfileFullGet"></a>
# **apiManagerProfileFullGet**
> ProfileFullViewModel apiManagerProfileFullGet()



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
try {
    ProfileFullViewModel result = apiInstance.apiManagerProfileFullGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiManagerProfileFullGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ProfileFullViewModel**](ProfileFullViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiManagerProfileGet"></a>
# **apiManagerProfileGet**
> ProfileShortViewModel apiManagerProfileGet()



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
try {
    ProfileShortViewModel result = apiInstance.apiManagerProfileGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiManagerProfileGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ProfileShortViewModel**](ProfileShortViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiManagerProfileUpdatePost"></a>
# **apiManagerProfileUpdatePost**
> Void apiManagerProfileUpdatePost(model)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountApi;


AccountApi apiInstance = new AccountApi();
ProfileFullViewModel model = new ProfileFullViewModel(); // ProfileFullViewModel | 
try {
    Void result = apiInstance.apiManagerProfileUpdatePost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#apiManagerProfileUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**ProfileFullViewModel**](ProfileFullViewModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

