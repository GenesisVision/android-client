# ManagersApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10ManagersByIdGet**](ManagersApi.md#v10ManagersByIdGet) | **GET** v1.0/managers/{id} | Manager profile
[**v10ManagersFundsByIdAssetsUpdatePost**](ManagersApi.md#v10ManagersFundsByIdAssetsUpdatePost) | **POST** v1.0/managers/funds/{id}/assets/update | Update fund assets parts
[**v10ManagersFundsByIdClosePost**](ManagersApi.md#v10ManagersFundsByIdClosePost) | **POST** v1.0/managers/funds/{id}/close | Close existing investment program/fund
[**v10ManagersFundsByIdRequestsBySkipByTakeGet**](ManagersApi.md#v10ManagersFundsByIdRequestsBySkipByTakeGet) | **GET** v1.0/managers/funds/{id}/requests/{skip}/{take} | Get investment program/fund requests
[**v10ManagersFundsByIdUpdatePost**](ManagersApi.md#v10ManagersFundsByIdUpdatePost) | **POST** v1.0/managers/funds/{id}/update | Update investment program/fund details
[**v10ManagersFundsCreatePost**](ManagersApi.md#v10ManagersFundsCreatePost) | **POST** v1.0/managers/funds/create | Create fund
[**v10ManagersFundsInvestmentAmountGet**](ManagersApi.md#v10ManagersFundsInvestmentAmountGet) | **GET** v1.0/managers/funds/investment/amount | Get GVT investment to create fund
[**v10ManagersFundsRequestsByIdCancelPost**](ManagersApi.md#v10ManagersFundsRequestsByIdCancelPost) | **POST** v1.0/managers/funds/requests/{id}/cancel | Cancel investment program/fund request
[**v10ManagersProgramsByIdClosePost**](ManagersApi.md#v10ManagersProgramsByIdClosePost) | **POST** v1.0/managers/programs/{id}/close | Close existing investment program/fund
[**v10ManagersProgramsByIdPeriodClosePost**](ManagersApi.md#v10ManagersProgramsByIdPeriodClosePost) | **POST** v1.0/managers/programs/{id}/period/close | Close current period
[**v10ManagersProgramsByIdRequestsBySkipByTakeGet**](ManagersApi.md#v10ManagersProgramsByIdRequestsBySkipByTakeGet) | **GET** v1.0/managers/programs/{id}/requests/{skip}/{take} | Get investment program/fund requests
[**v10ManagersProgramsByIdUpdatePost**](ManagersApi.md#v10ManagersProgramsByIdUpdatePost) | **POST** v1.0/managers/programs/{id}/update | Update investment program/fund details
[**v10ManagersProgramsCreatePost**](ManagersApi.md#v10ManagersProgramsCreatePost) | **POST** v1.0/managers/programs/create | Create an investment program
[**v10ManagersProgramsInvestmentAmountGet**](ManagersApi.md#v10ManagersProgramsInvestmentAmountGet) | **GET** v1.0/managers/programs/investment/amount | Get GVT investment to create program
[**v10ManagersProgramsRequestsByIdCancelPost**](ManagersApi.md#v10ManagersProgramsRequestsByIdCancelPost) | **POST** v1.0/managers/programs/requests/{id}/cancel | Cancel investment program/fund request


<a name="v10ManagersByIdGet"></a>
# **v10ManagersByIdGet**
> ManagerProfile v10ManagersByIdGet(id)

Manager profile

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
try {
    ManagerProfile result = apiInstance.v10ManagersByIdGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersByIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**ManagerProfile**](ManagerProfile.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersFundsByIdAssetsUpdatePost"></a>
# **v10ManagersFundsByIdAssetsUpdatePost**
> Void v10ManagersFundsByIdAssetsUpdatePost(id, authorization, model)

Update fund assets parts

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
AssetsPartsChangeRequest model = new AssetsPartsChangeRequest(); // AssetsPartsChangeRequest | 
try {
    Void result = apiInstance.v10ManagersFundsByIdAssetsUpdatePost(id, authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersFundsByIdAssetsUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **model** | [**AssetsPartsChangeRequest**](AssetsPartsChangeRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersFundsByIdClosePost"></a>
# **v10ManagersFundsByIdClosePost**
> Void v10ManagersFundsByIdClosePost(id, authorization)

Close existing investment program/fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagersFundsByIdClosePost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersFundsByIdClosePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersFundsByIdRequestsBySkipByTakeGet"></a>
# **v10ManagersFundsByIdRequestsBySkipByTakeGet**
> ProgramRequests v10ManagersFundsByIdRequestsBySkipByTakeGet(id, skip, take, authorization)

Get investment program/fund requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramRequests result = apiInstance.v10ManagersFundsByIdRequestsBySkipByTakeGet(id, skip, take, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersFundsByIdRequestsBySkipByTakeGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **skip** | **Integer**|  |
 **take** | **Integer**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**ProgramRequests**](ProgramRequests.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersFundsByIdUpdatePost"></a>
# **v10ManagersFundsByIdUpdatePost**
> Void v10ManagersFundsByIdUpdatePost(id, authorization, model)

Update investment program/fund details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
ProgramUpdate model = new ProgramUpdate(); // ProgramUpdate | 
try {
    Void result = apiInstance.v10ManagersFundsByIdUpdatePost(id, authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersFundsByIdUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **model** | [**ProgramUpdate**](ProgramUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersFundsCreatePost"></a>
# **v10ManagersFundsCreatePost**
> Void v10ManagersFundsCreatePost(authorization, request)

Create fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
String authorization = "authorization_example"; // String | JWT access token
NewFundRequest request = new NewFundRequest(); // NewFundRequest | 
try {
    Void result = apiInstance.v10ManagersFundsCreatePost(authorization, request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersFundsCreatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **request** | [**NewFundRequest**](NewFundRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersFundsInvestmentAmountGet"></a>
# **v10ManagersFundsInvestmentAmountGet**
> Double v10ManagersFundsInvestmentAmountGet(authorization)

Get GVT investment to create fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Double result = apiInstance.v10ManagersFundsInvestmentAmountGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersFundsInvestmentAmountGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

**Double**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersFundsRequestsByIdCancelPost"></a>
# **v10ManagersFundsRequestsByIdCancelPost**
> Void v10ManagersFundsRequestsByIdCancelPost(id, authorization)

Cancel investment program/fund request

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagersFundsRequestsByIdCancelPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersFundsRequestsByIdCancelPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersProgramsByIdClosePost"></a>
# **v10ManagersProgramsByIdClosePost**
> Void v10ManagersProgramsByIdClosePost(id, authorization)

Close existing investment program/fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagersProgramsByIdClosePost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersProgramsByIdClosePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersProgramsByIdPeriodClosePost"></a>
# **v10ManagersProgramsByIdPeriodClosePost**
> Void v10ManagersProgramsByIdPeriodClosePost(id, authorization)

Close current period

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagersProgramsByIdPeriodClosePost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersProgramsByIdPeriodClosePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersProgramsByIdRequestsBySkipByTakeGet"></a>
# **v10ManagersProgramsByIdRequestsBySkipByTakeGet**
> ProgramRequests v10ManagersProgramsByIdRequestsBySkipByTakeGet(id, skip, take, authorization)

Get investment program/fund requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramRequests result = apiInstance.v10ManagersProgramsByIdRequestsBySkipByTakeGet(id, skip, take, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersProgramsByIdRequestsBySkipByTakeGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **skip** | **Integer**|  |
 **take** | **Integer**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**ProgramRequests**](ProgramRequests.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersProgramsByIdUpdatePost"></a>
# **v10ManagersProgramsByIdUpdatePost**
> Void v10ManagersProgramsByIdUpdatePost(id, authorization, model)

Update investment program/fund details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
ProgramUpdate model = new ProgramUpdate(); // ProgramUpdate | 
try {
    Void result = apiInstance.v10ManagersProgramsByIdUpdatePost(id, authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersProgramsByIdUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **model** | [**ProgramUpdate**](ProgramUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersProgramsCreatePost"></a>
# **v10ManagersProgramsCreatePost**
> Void v10ManagersProgramsCreatePost(authorization, request)

Create an investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
String authorization = "authorization_example"; // String | JWT access token
NewProgramRequest request = new NewProgramRequest(); // NewProgramRequest | 
try {
    Void result = apiInstance.v10ManagersProgramsCreatePost(authorization, request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersProgramsCreatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **request** | [**NewProgramRequest**](NewProgramRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersProgramsInvestmentAmountGet"></a>
# **v10ManagersProgramsInvestmentAmountGet**
> Double v10ManagersProgramsInvestmentAmountGet(authorization)

Get GVT investment to create program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Double result = apiInstance.v10ManagersProgramsInvestmentAmountGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersProgramsInvestmentAmountGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

**Double**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersProgramsRequestsByIdCancelPost"></a>
# **v10ManagersProgramsRequestsByIdCancelPost**
> Void v10ManagersProgramsRequestsByIdCancelPost(id, authorization)

Cancel investment program/fund request

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagersProgramsRequestsByIdCancelPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersProgramsRequestsByIdCancelPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

