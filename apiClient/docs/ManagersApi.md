# ManagersApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10ManagersByIdDetailsGet**](ManagersApi.md#v10ManagersByIdDetailsGet) | **GET** v1.0/managers/{id}/details | Manager details
[**v10ManagersByIdGet**](ManagersApi.md#v10ManagersByIdGet) | **GET** v1.0/managers/{id} | Manager profile
[**v10ManagersFundsByIdClosePost**](ManagersApi.md#v10ManagersFundsByIdClosePost) | **POST** v1.0/managers/funds/{id}/close | Close existing investment program/fund
[**v10ManagersFundsByIdInvestByAmountPost**](ManagersApi.md#v10ManagersFundsByIdInvestByAmountPost) | **POST** v1.0/managers/funds/{id}/invest/{amount} | Deposit
[**v10ManagersFundsByIdRequestsBySkipByTakeGet**](ManagersApi.md#v10ManagersFundsByIdRequestsBySkipByTakeGet) | **GET** v1.0/managers/funds/{id}/requests/{skip}/{take} | Get investment program/fund requests
[**v10ManagersFundsByIdUpdatePost**](ManagersApi.md#v10ManagersFundsByIdUpdatePost) | **POST** v1.0/managers/funds/{id}/update | Update investment program/fund details
[**v10ManagersFundsByIdWithdrawByPercentPost**](ManagersApi.md#v10ManagersFundsByIdWithdrawByPercentPost) | **POST** v1.0/managers/funds/{id}/withdraw/{percent} | Withdraw from fund. Percent is % of investor total money.
[**v10ManagersFundsByIdWithdrawInfoByCurrencyGet**](ManagersApi.md#v10ManagersFundsByIdWithdrawInfoByCurrencyGet) | **GET** v1.0/managers/funds/{id}/withdraw/info/{currency} | Data for withdrawal from fund
[**v10ManagersFundsCreatePost**](ManagersApi.md#v10ManagersFundsCreatePost) | **POST** v1.0/managers/funds/create | Create fund
[**v10ManagersFundsInvestmentAmountGet**](ManagersApi.md#v10ManagersFundsInvestmentAmountGet) | **GET** v1.0/managers/funds/investment/amount | Get GVT investment to create fund
[**v10ManagersFundsRequestsByIdCancelPost**](ManagersApi.md#v10ManagersFundsRequestsByIdCancelPost) | **POST** v1.0/managers/funds/requests/{id}/cancel | Cancel investment program/fund request
[**v10ManagersProgramsByIdClosePost**](ManagersApi.md#v10ManagersProgramsByIdClosePost) | **POST** v1.0/managers/programs/{id}/close | Close existing investment program/fund
[**v10ManagersProgramsByIdInvestByAmountPost**](ManagersApi.md#v10ManagersProgramsByIdInvestByAmountPost) | **POST** v1.0/managers/programs/{id}/invest/{amount} | Deposit
[**v10ManagersProgramsByIdPeriodClosePost**](ManagersApi.md#v10ManagersProgramsByIdPeriodClosePost) | **POST** v1.0/managers/programs/{id}/period/close | Close current period
[**v10ManagersProgramsByIdRequestsBySkipByTakeGet**](ManagersApi.md#v10ManagersProgramsByIdRequestsBySkipByTakeGet) | **GET** v1.0/managers/programs/{id}/requests/{skip}/{take} | Get investment program/fund requests
[**v10ManagersProgramsByIdUpdatePost**](ManagersApi.md#v10ManagersProgramsByIdUpdatePost) | **POST** v1.0/managers/programs/{id}/update | Update investment program/fund details
[**v10ManagersProgramsByIdWithdrawByAmountPost**](ManagersApi.md#v10ManagersProgramsByIdWithdrawByAmountPost) | **POST** v1.0/managers/programs/{id}/withdraw/{amount} | Withdraw from program
[**v10ManagersProgramsByIdWithdrawInfoByCurrencyGet**](ManagersApi.md#v10ManagersProgramsByIdWithdrawInfoByCurrencyGet) | **GET** v1.0/managers/programs/{id}/withdraw/info/{currency} | Data for withdrawal from investment program
[**v10ManagersProgramsCreatePost**](ManagersApi.md#v10ManagersProgramsCreatePost) | **POST** v1.0/managers/programs/create | Create an investment program
[**v10ManagersProgramsInvestmentAmountGet**](ManagersApi.md#v10ManagersProgramsInvestmentAmountGet) | **GET** v1.0/managers/programs/investment/amount | Get GVT investment to create program
[**v10ManagersProgramsRequestsByIdCancelPost**](ManagersApi.md#v10ManagersProgramsRequestsByIdCancelPost) | **POST** v1.0/managers/programs/requests/{id}/cancel | Cancel investment program/fund request


<a name="v10ManagersByIdDetailsGet"></a>
# **v10ManagersByIdDetailsGet**
> ManagerProfileDetails v10ManagersByIdDetailsGet(id)

Manager details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
String id = "id_example"; // String | 
try {
    ManagerProfileDetails result = apiInstance.v10ManagersByIdDetailsGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersByIdDetailsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |

### Return type

[**ManagerProfileDetails**](ManagerProfileDetails.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

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
String id = "id_example"; // String | 
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
 **id** | **String**|  |

### Return type

[**ManagerProfile**](ManagerProfile.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersFundsByIdClosePost"></a>
# **v10ManagersFundsByIdClosePost**
> Void v10ManagersFundsByIdClosePost(id, authorization, twoFactorCode)

Close existing investment program/fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
String twoFactorCode = "twoFactorCode_example"; // String | 
try {
    Void result = apiInstance.v10ManagersFundsByIdClosePost(id, authorization, twoFactorCode);
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
 **twoFactorCode** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersFundsByIdInvestByAmountPost"></a>
# **v10ManagersFundsByIdInvestByAmountPost**
> Void v10ManagersFundsByIdInvestByAmountPost(id, amount, authorization)

Deposit

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagersFundsByIdInvestByAmountPost(id, amount, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersFundsByIdInvestByAmountPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **amount** | **Double**|  |
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

<a name="v10ManagersFundsByIdWithdrawByPercentPost"></a>
# **v10ManagersFundsByIdWithdrawByPercentPost**
> Void v10ManagersFundsByIdWithdrawByPercentPost(id, percent, authorization)

Withdraw from fund. Percent is % of investor total money.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
Double percent = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagersFundsByIdWithdrawByPercentPost(id, percent, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersFundsByIdWithdrawByPercentPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **percent** | **Double**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersFundsByIdWithdrawInfoByCurrencyGet"></a>
# **v10ManagersFundsByIdWithdrawInfoByCurrencyGet**
> ManagerFundWithdrawInfo v10ManagersFundsByIdWithdrawInfoByCurrencyGet(id, currency, authorization)

Data for withdrawal from fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ManagerFundWithdrawInfo result = apiInstance.v10ManagersFundsByIdWithdrawInfoByCurrencyGet(id, currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersFundsByIdWithdrawInfoByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, USD, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**ManagerFundWithdrawInfo**](ManagerFundWithdrawInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
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
> Void v10ManagersProgramsByIdClosePost(id, authorization, twoFactorCode)

Close existing investment program/fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
String twoFactorCode = "twoFactorCode_example"; // String | 
try {
    Void result = apiInstance.v10ManagersProgramsByIdClosePost(id, authorization, twoFactorCode);
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
 **twoFactorCode** | **String**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersProgramsByIdInvestByAmountPost"></a>
# **v10ManagersProgramsByIdInvestByAmountPost**
> Void v10ManagersProgramsByIdInvestByAmountPost(id, amount, authorization)

Deposit

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagersProgramsByIdInvestByAmountPost(id, amount, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersProgramsByIdInvestByAmountPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **amount** | **Double**|  |
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

<a name="v10ManagersProgramsByIdWithdrawByAmountPost"></a>
# **v10ManagersProgramsByIdWithdrawByAmountPost**
> Void v10ManagersProgramsByIdWithdrawByAmountPost(id, amount, authorization)

Withdraw from program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10ManagersProgramsByIdWithdrawByAmountPost(id, amount, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersProgramsByIdWithdrawByAmountPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **amount** | **Double**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10ManagersProgramsByIdWithdrawInfoByCurrencyGet"></a>
# **v10ManagersProgramsByIdWithdrawInfoByCurrencyGet**
> ManagerProgramWithdrawInfo v10ManagersProgramsByIdWithdrawInfoByCurrencyGet(id, currency, authorization)

Data for withdrawal from investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ManagersApi;


ManagersApi apiInstance = new ManagersApi();
UUID id = new UUID(); // UUID | 
String currency = "currency_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ManagerProgramWithdrawInfo result = apiInstance.v10ManagersProgramsByIdWithdrawInfoByCurrencyGet(id, currency, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ManagersApi#v10ManagersProgramsByIdWithdrawInfoByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **currency** | **String**|  | [enum: Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, USD, EUR]
 **authorization** | **String**| JWT access token |

### Return type

[**ManagerProgramWithdrawInfo**](ManagerProgramWithdrawInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
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

