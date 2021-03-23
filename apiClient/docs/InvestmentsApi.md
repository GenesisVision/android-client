# InvestmentsApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelRequest**](InvestmentsApi.md#cancelRequest) | **POST** v2.0/investments/requests/{id}/cancel | Cancel investment request
[**getFundWithdrawInfo**](InvestmentsApi.md#getFundWithdrawInfo) | **GET** v2.0/investments/funds/{id}/withdraw/info | Data for withdrawal from fund (in selected currency)
[**getProgramWithdrawInfo**](InvestmentsApi.md#getProgramWithdrawInfo) | **GET** v2.0/investments/programs/{id}/withdraw/info | Data for withdrawal from investment program (in program currency)
[**getRequests**](InvestmentsApi.md#getRequests) | **GET** v2.0/investments/requests/{skip}/{take} | Get all requests
[**getRequestsByProgram**](InvestmentsApi.md#getRequestsByProgram) | **GET** v2.0/investments/requests/{id}/{skip}/{take} | Get program/fund requests
[**investIntoFund**](InvestmentsApi.md#investIntoFund) | **POST** v2.0/investments/funds/{id}/invest | Investing into the fund
[**investIntoProgram**](InvestmentsApi.md#investIntoProgram) | **POST** v2.0/investments/programs/{id}/invest | Investing into the program
[**switchAutoJoinOff**](InvestmentsApi.md#switchAutoJoinOff) | **POST** v2.0/investments/programs/{id}/autojoin/off | Disable autojoin
[**switchAutoJoinOn**](InvestmentsApi.md#switchAutoJoinOn) | **POST** v2.0/investments/programs/{id}/autojoin/on | Enable autojoin
[**switchReinvestOff**](InvestmentsApi.md#switchReinvestOff) | **POST** v2.0/investments/programs/{id}/reinvest/off | Disable reinvesting
[**switchReinvestOn**](InvestmentsApi.md#switchReinvestOn) | **POST** v2.0/investments/programs/{id}/reinvest/on | Enable reinvesting
[**withdrawFromFund**](InvestmentsApi.md#withdrawFromFund) | **POST** v2.0/investments/funds/{id}/withdraw | Withdraw from fund. Percent is % of manager total money
[**withdrawFromProgram**](InvestmentsApi.md#withdrawFromProgram) | **POST** v2.0/investments/programs/{id}/withdraw | Withdrawal from program

<a name="cancelRequest"></a>
# **cancelRequest**
> Void cancelRequest(id)

Cancel investment request

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.cancelRequest(id);
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

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getFundWithdrawInfo"></a>
# **getFundWithdrawInfo**
> FundWithdrawInfo getFundWithdrawInfo(id, currency)

Data for withdrawal from fund (in selected currency)

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
Currency currency = new Currency(); // Currency | 
try {
    FundWithdrawInfo result = apiInstance.getFundWithdrawInfo(id, currency);
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
 **currency** | [**Currency**](.md)|  | [optional]

### Return type

[**FundWithdrawInfo**](FundWithdrawInfo.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgramWithdrawInfo"></a>
# **getProgramWithdrawInfo**
> ProgramWithdrawInfo getProgramWithdrawInfo(id)

Data for withdrawal from investment program (in program currency)

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
try {
    ProgramWithdrawInfo result = apiInstance.getProgramWithdrawInfo(id);
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

### Return type

[**ProgramWithdrawInfo**](ProgramWithdrawInfo.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getRequests"></a>
# **getRequests**
> AssetInvestmentRequestItemsViewModel getRequests(skip, take)

Get all requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    AssetInvestmentRequestItemsViewModel result = apiInstance.getRequests(skip, take);
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

### Return type

[**AssetInvestmentRequestItemsViewModel**](AssetInvestmentRequestItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getRequestsByProgram"></a>
# **getRequestsByProgram**
> AssetInvestmentRequestItemsViewModel getRequestsByProgram(id, skip, take)

Get program/fund requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    AssetInvestmentRequestItemsViewModel result = apiInstance.getRequestsByProgram(id, skip, take);
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

### Return type

[**AssetInvestmentRequestItemsViewModel**](AssetInvestmentRequestItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="investIntoFund"></a>
# **investIntoFund**
> Void investIntoFund(id, amount, walletId)

Investing into the fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
UUID walletId = new UUID(); // UUID | 
try {
    Void result = apiInstance.investIntoFund(id, amount, walletId);
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
 **amount** | **Double**|  | [optional]
 **walletId** | [**UUID**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="investIntoProgram"></a>
# **investIntoProgram**
> Void investIntoProgram(id, amount, walletId)

Investing into the program

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
UUID walletId = new UUID(); // UUID | 
try {
    Void result = apiInstance.investIntoProgram(id, amount, walletId);
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
 **amount** | **Double**|  | [optional]
 **walletId** | [**UUID**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="switchAutoJoinOff"></a>
# **switchAutoJoinOff**
> Void switchAutoJoinOff(id)

Disable autojoin

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.switchAutoJoinOff(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#switchAutoJoinOff");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="switchAutoJoinOn"></a>
# **switchAutoJoinOn**
> Void switchAutoJoinOn(id)

Enable autojoin

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.switchAutoJoinOn(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InvestmentsApi#switchAutoJoinOn");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="switchReinvestOff"></a>
# **switchReinvestOff**
> Void switchReinvestOff(id)

Disable reinvesting

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.switchReinvestOff(id);
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

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="switchReinvestOn"></a>
# **switchReinvestOn**
> Void switchReinvestOn(id)

Enable reinvesting

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.switchReinvestOn(id);
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

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="withdrawFromFund"></a>
# **withdrawFromFund**
> Void withdrawFromFund(id, percent, currency)

Withdraw from fund. Percent is % of manager total money

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
Double percent = 3.4D; // Double | 
Currency currency = new Currency(); // Currency | 
try {
    Void result = apiInstance.withdrawFromFund(id, percent, currency);
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
 **percent** | **Double**|  | [optional]
 **currency** | [**Currency**](.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="withdrawFromProgram"></a>
# **withdrawFromProgram**
> Void withdrawFromProgram(id, amount, withdrawAll)

Withdrawal from program

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InvestmentsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

InvestmentsApi apiInstance = new InvestmentsApi();
UUID id = new UUID(); // UUID | 
Double amount = 3.4D; // Double | 
Boolean withdrawAll = false; // Boolean | 
try {
    Void result = apiInstance.withdrawFromProgram(id, amount, withdrawAll);
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
 **amount** | **Double**|  | [optional]
 **withdrawAll** | **Boolean**|  | [optional] [default to false]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

