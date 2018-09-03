# ProgramApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10ProgramCancelInvestmentRequestPost**](ProgramApi.md#v10ProgramCancelInvestmentRequestPost) | **POST** v1.0/program/cancelInvestmentRequest | Cancel investment request
[**v10ProgramCreatePost**](ProgramApi.md#v10ProgramCreatePost) | **POST** v1.0/program/create | Create a investment program
[**v10ProgramInvestPost**](ProgramApi.md#v10ProgramInvestPost) | **POST** v1.0/program/invest | Invest to program


<a name="v10ProgramCancelInvestmentRequestPost"></a>
# **v10ProgramCancelInvestmentRequestPost**
> Void v10ProgramCancelInvestmentRequestPost(authorization, requestId)

Cancel investment request

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramApi;


ProgramApi apiInstance = new ProgramApi();
String authorization = "authorization_example"; // String | JWT access token
UUID requestId = new UUID(); // UUID | 
try {
    Void result = apiInstance.v10ProgramCancelInvestmentRequestPost(authorization, requestId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramApi#v10ProgramCancelInvestmentRequestPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **requestId** | [**UUID**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ProgramCreatePost"></a>
# **v10ProgramCreatePost**
> Void v10ProgramCreatePost(authorization, request)

Create a investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramApi;


ProgramApi apiInstance = new ProgramApi();
String authorization = "authorization_example"; // String | JWT access token
NewProgramRequest request = new NewProgramRequest(); // NewProgramRequest | 
try {
    Void result = apiInstance.v10ProgramCreatePost(authorization, request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramApi#v10ProgramCreatePost");
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

<a name="v10ProgramInvestPost"></a>
# **v10ProgramInvestPost**
> WalletsViewModel v10ProgramInvestPost(authorization, model)

Invest to program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProgramApi;


ProgramApi apiInstance = new ProgramApi();
String authorization = "authorization_example"; // String | JWT access token
InvestToProgram model = new InvestToProgram(); // InvestToProgram | 
try {
    WalletsViewModel result = apiInstance.v10ProgramInvestPost(authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProgramApi#v10ProgramInvestPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **model** | [**InvestToProgram**](InvestToProgram.md)|  | [optional]

### Return type

[**WalletsViewModel**](WalletsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

