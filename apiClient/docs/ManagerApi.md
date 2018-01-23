# ManagerApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiBrokerAccountCreatePost**](ManagerApi.md#apiBrokerAccountCreatePost) | **POST** api/broker/account/create | 
[**apiManagerAccountNewInvestmentRequestPost**](ManagerApi.md#apiManagerAccountNewInvestmentRequestPost) | **POST** api/manager/account/newInvestmentRequest | 
[**apiManagerInvestmentCloseGet**](ManagerApi.md#apiManagerInvestmentCloseGet) | **GET** api/manager/investment/close | 
[**apiManagerInvestmentGet**](ManagerApi.md#apiManagerInvestmentGet) | **GET** api/manager/investment | 


<a name="apiBrokerAccountCreatePost"></a>
# **apiBrokerAccountCreatePost**
> UUID apiBrokerAccountCreatePost(request)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
NewManager request = new NewManager(); // NewManager | 
try {
    UUID result = apiInstance.apiBrokerAccountCreatePost(request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiBrokerAccountCreatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **request** | [**NewManager**](NewManager.md)|  | [optional]

### Return type

[**UUID**](UUID.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiManagerAccountNewInvestmentRequestPost"></a>
# **apiManagerAccountNewInvestmentRequestPost**
> UUID apiManagerAccountNewInvestmentRequestPost(request)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
NewInvestmentRequest request = new NewInvestmentRequest(); // NewInvestmentRequest | 
try {
    UUID result = apiInstance.apiManagerAccountNewInvestmentRequestPost(request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerAccountNewInvestmentRequestPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **request** | [**NewInvestmentRequest**](NewInvestmentRequest.md)|  | [optional]

### Return type

[**UUID**](UUID.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiManagerInvestmentCloseGet"></a>
# **apiManagerInvestmentCloseGet**
> Void apiManagerInvestmentCloseGet(investmentProgramId)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID investmentProgramId = new UUID(); // UUID | 
try {
    Void result = apiInstance.apiManagerInvestmentCloseGet(investmentProgramId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerInvestmentCloseGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **investmentProgramId** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiManagerInvestmentGet"></a>
# **apiManagerInvestmentGet**
> InvestmentProgramViewModel apiManagerInvestmentGet(investmentProgramId)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagerApi;


ManagerApi apiInstance = new ManagerApi();
UUID investmentProgramId = new UUID(); // UUID | 
try {
    InvestmentProgramViewModel result = apiInstance.apiManagerInvestmentGet(investmentProgramId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagerApi#apiManagerInvestmentGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **investmentProgramId** | [**UUID**](.md)|  |

### Return type

[**InvestmentProgramViewModel**](InvestmentProgramViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

