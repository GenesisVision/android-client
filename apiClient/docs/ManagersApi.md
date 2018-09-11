# ManagersApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10ManagersByIdGet**](ManagersApi.md#v10ManagersByIdGet) | **GET** v1.0/managers/{id} | Manager profile
[**v10ManagersProgramsByIdClosePost**](ManagersApi.md#v10ManagersProgramsByIdClosePost) | **POST** v1.0/managers/programs/{id}/close | Close existing investment program
[**v10ManagersProgramsByIdPeriodClosePost**](ManagersApi.md#v10ManagersProgramsByIdPeriodClosePost) | **POST** v1.0/managers/programs/{id}/period/close | Close current period
[**v10ManagersProgramsByIdRequestsBySkipByTakeGet**](ManagersApi.md#v10ManagersProgramsByIdRequestsBySkipByTakeGet) | **GET** v1.0/managers/programs/{id}/requests/{skip}/{take} | Get requests
[**v10ManagersProgramsByIdUpdatePost**](ManagersApi.md#v10ManagersProgramsByIdUpdatePost) | **POST** v1.0/managers/programs/{id}/update | Update investment program details
[**v10ManagersProgramsCreatePost**](ManagersApi.md#v10ManagersProgramsCreatePost) | **POST** v1.0/managers/programs/create | Create an investment program
[**v10ManagersProgramsRequestsByIdCancelPost**](ManagersApi.md#v10ManagersProgramsRequestsByIdCancelPost) | **POST** v1.0/managers/programs/requests/{id}/cancel | Cancel request


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

<a name="v10ManagersProgramsByIdClosePost"></a>
# **v10ManagersProgramsByIdClosePost**
> Void v10ManagersProgramsByIdClosePost(id, authorization)

Close existing investment program

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

Get requests

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

Update investment program details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
InvestmentProgramUpdate model = new InvestmentProgramUpdate(); // InvestmentProgramUpdate | 
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
 **model** | [**InvestmentProgramUpdate**](InvestmentProgramUpdate.md)|  | [optional]

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

<a name="v10ManagersProgramsRequestsByIdCancelPost"></a>
# **v10ManagersProgramsRequestsByIdCancelPost**
> Void v10ManagersProgramsRequestsByIdCancelPost(id, authorization)

Cancel request

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

