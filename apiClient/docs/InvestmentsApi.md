# InvestmentsApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelRequest**](InvestmentsApi.md#cancelRequest) | **POST** v2.0/investments/requests/{id}/cancel | Cancel investment request
[**getFundWithdrawInfo**](InvestmentsApi.md#getFundWithdrawInfo) | **GET** v2.0/investments/funds/{id}/withdraw/info | Data for withdrawal from fund (in selected currency)
[**getProgramWithdrawInfo**](InvestmentsApi.md#getProgramWithdrawInfo) | **GET** v2.0/investments/programs/{id}/withdraw/info | Data for withdrawal from investment program (in program currency)
[**getRequests**](InvestmentsApi.md#getRequests) | **GET** v2.0/investments/requests/{skip}/{take} | Get all requests
[**getRequestsByProgram**](InvestmentsApi.md#getRequestsByProgram) | **GET** v2.0/investments/requests/{id}/{skip}/{take} | Get program/fund requests
[**investIntoFund**](InvestmentsApi.md#investIntoFund) | **POST** v2.0/investments/funds/{id}/invest | Investing into the fund
[**investIntoProgram**](InvestmentsApi.md#investIntoProgram) | **POST** v2.0/investments/programs/{id}/invest | Investing into the program
[**switchReinvestOff**](InvestmentsApi.md#switchReinvestOff) | **POST** v2.0/investments/programs/{id}/reinvest/off | Disable reinvesting
[**switchReinvestOn**](InvestmentsApi.md#switchReinvestOn) | **POST** v2.0/investments/programs/{id}/reinvest/on | Enable reinvesting
[**withdrawFromFund**](InvestmentsApi.md#withdrawFromFund) | **POST** v2.0/investments/funds/{id}/withdraw | Withdraw from fund. Percent is % of manager total money
[**withdrawFromProgram**](InvestmentsApi.md#withdrawFromProgram) | **POST** v2.0/investments/programs/{id}/withdraw | Withdrawal from program

<a name="cancelRequest"></a>
# **cancelRequest**
> Void cancelRequest(id, authorization)

Cancel investment request

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestmentsApi;


InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.cancelRequest(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#cancelRequest");
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

<a name="getFundWithdrawInfo"></a>
# **getFundWithdrawInfo**
> FundWithdrawInfo getFundWithdrawInfo(id, authorization, currency)

Data for withdrawal from fund (in selected currency)

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestmentsApi;


InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
String currency = "currency_example"; // String | 
try {
    FundWithdrawInfo result = apiInstance.getFundWithdrawInfo(id, authorization, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#getFundWithdrawInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**FundWithdrawInfo**](FundWithdrawInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramWithdrawInfo"></a>
# **getProgramWithdrawInfo**
> ProgramWithdrawInfo getProgramWithdrawInfo(id, authorization)

Data for withdrawal from investment program (in program currency)

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestmentsApi;


InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramWithdrawInfo result = apiInstance.getProgramWithdrawInfo(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#getProgramWithdrawInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**ProgramWithdrawInfo**](ProgramWithdrawInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getRequests"></a>
# **getRequests**
> ItemsViewModelAssetInvestmentRequest getRequests(skip, take, authorization)

Get all requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestmentsApi;


InvestmentsApi apiInstance = new InvestmentsApi();
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ItemsViewModelAssetInvestmentRequest result = apiInstance.getRequests(skip, take, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#getRequests");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **skip** | **Integer**|  |
 **take** | **Integer**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**ItemsViewModelAssetInvestmentRequest**](ItemsViewModelAssetInvestmentRequest.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getRequestsByProgram"></a>
# **getRequestsByProgram**
> ItemsViewModelAssetInvestmentRequest getRequestsByProgram(id, skip, take, authorization)

Get program/fund requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestmentsApi;


InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ItemsViewModelAssetInvestmentRequest result = apiInstance.getRequestsByProgram(id, skip, take, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#getRequestsByProgram");
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

[**ItemsViewModelAssetInvestmentRequest**](ItemsViewModelAssetInvestmentRequest.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="investIntoFund"></a>
# **investIntoFund**
> Void investIntoFund(id, authorization, amount, walletId)

Investing into the fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestmentsApi;


InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
Double amount = 3.4D; // Double | 
UUID walletId = new UUID(); // UUID | 
try {
    Void result = apiInstance.investIntoFund(id, authorization, amount, walletId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#investIntoFund");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **amount** | **Double**|  | [optional]
 **walletId** | [**UUID**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="investIntoProgram"></a>
# **investIntoProgram**
> Void investIntoProgram(id, authorization, amount, walletId)

Investing into the program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestmentsApi;


InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
Double amount = 3.4D; // Double | 
UUID walletId = new UUID(); // UUID | 
try {
    Void result = apiInstance.investIntoProgram(id, authorization, amount, walletId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#investIntoProgram");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **amount** | **Double**|  | [optional]
 **walletId** | [**UUID**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="switchReinvestOff"></a>
# **switchReinvestOff**
> Void switchReinvestOff(id, authorization)

Disable reinvesting

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestmentsApi;


InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.switchReinvestOff(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#switchReinvestOff");
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

<a name="switchReinvestOn"></a>
# **switchReinvestOn**
> Void switchReinvestOn(id, authorization)

Enable reinvesting

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestmentsApi;


InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.switchReinvestOn(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#switchReinvestOn");
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

<a name="withdrawFromFund"></a>
# **withdrawFromFund**
> Void withdrawFromFund(id, authorization, percent, currency)

Withdraw from fund. Percent is % of manager total money

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestmentsApi;


InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
Double percent = 3.4D; // Double | 
String currency = "currency_example"; // String | 
try {
    Void result = apiInstance.withdrawFromFund(id, authorization, percent, currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#withdrawFromFund");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **percent** | **Double**|  | [optional]
 **currency** | **String**|  | [optional] [enum: USD, Undefined, GVT, ETH, BTC, ADA, USDT, XRP, BCH, LTC, DOGE, BNB, EUR]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="withdrawFromProgram"></a>
# **withdrawFromProgram**
> Void withdrawFromProgram(id, authorization, amount, withdrawAll)

Withdrawal from program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InvestmentsApi;


InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
Double amount = 3.4D; // Double | 
Boolean withdrawAll = true; // Boolean | 
try {
    Void result = apiInstance.withdrawFromProgram(id, authorization, amount, withdrawAll);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#withdrawFromProgram");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **amount** | **Double**|  | [optional]
 **withdrawAll** | **Boolean**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

