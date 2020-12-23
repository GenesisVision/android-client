# AssetsApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelChangeBroker**](AssetsApi.md#cancelChangeBroker) | **POST** v2.0/assets/programs/{id}/broker/change/cancel | Cancel changing broker in existing program
[**changeBroker**](AssetsApi.md#changeBroker) | **POST** v2.0/assets/programs/{id}/broker/change | Change broker in existing program
[**changeTradingAccountPassword**](AssetsApi.md#changeTradingAccountPassword) | **POST** v2.0/assets/tradingaccounts/{id}/password/change | Change trading account password
[**closeCurrentPeriod**](AssetsApi.md#closeCurrentPeriod) | **POST** v2.0/assets/programs/{id}/period/close | Close current period
[**closeExchangeAccount**](AssetsApi.md#closeExchangeAccount) | **POST** v2.0/assets/tradingaccounts/exchange/{id}/close | Close exchange account
[**closeFund**](AssetsApi.md#closeFund) | **POST** v2.0/assets/funds/{id}/close | Close existing fund
[**closeInvestmentProgram**](AssetsApi.md#closeInvestmentProgram) | **POST** v2.0/assets/programs/{id}/close | Close existing investment program
[**closeTradingAccount**](AssetsApi.md#closeTradingAccount) | **POST** v2.0/assets/tradingaccounts/{id}/close | Close trading account
[**confirmProgram2FA**](AssetsApi.md#confirmProgram2FA) | **POST** v2.0/assets/programs/{id}/2fa/confirm | Confirm 2FA for program if required (for brokers like Huobi)
[**createAccountApiKey**](AssetsApi.md#createAccountApiKey) | **POST** v2.0/assets/tradingaccounts/{id}/keys/create | Create account api key
[**createExchangeAccount**](AssetsApi.md#createExchangeAccount) | **POST** v2.0/assets/tradingaccounts/exchange/create | Create exchange account
[**createExternalTradingAccount**](AssetsApi.md#createExternalTradingAccount) | **POST** v2.0/assets/tradingaccounts/external/create | Create external trading account
[**createFund**](AssetsApi.md#createFund) | **POST** v2.0/assets/funds/create | Create fund
[**createSelfManagedFund**](AssetsApi.md#createSelfManagedFund) | **POST** v2.0/assets/funds/selfmanaged/create | Create self managed fund
[**createTradingAccount**](AssetsApi.md#createTradingAccount) | **POST** v2.0/assets/tradingaccounts/create | Create trading account
[**deleteAccountApiKey**](AssetsApi.md#deleteAccountApiKey) | **POST** v2.0/assets/tradingaccounts/keys/{id}/delete | Delete account api key
[**editAccountApiKeyRestrictions**](AssetsApi.md#editAccountApiKeyRestrictions) | **POST** v2.0/assets/tradingaccounts/keys/{id}/edit | Edit account api key restrictions
[**getAccountApiKey**](AssetsApi.md#getAccountApiKey) | **GET** v2.0/assets/tradingaccounts/{id}/keys | Get account api key
[**getLevelsCalculator**](AssetsApi.md#getLevelsCalculator) | **GET** v2.0/assets/programs/{id}/levels/info | Get program data for levels calculator
[**getProgram2FA**](AssetsApi.md#getProgram2FA) | **GET** v2.0/assets/programs/{id}/2fa/get | Get 2FA for program if needed
[**makeAccountProgram**](AssetsApi.md#makeAccountProgram) | **POST** v2.0/assets/programs/fromaccount/create | Create an investment program from trading account
[**makeAccountSignalProvider**](AssetsApi.md#makeAccountSignalProvider) | **POST** v2.0/assets/signal/create | Make account signal provider
[**makeDemoTradingAccountDeposit**](AssetsApi.md#makeDemoTradingAccountDeposit) | **POST** v2.0/assets/tradingaccounts/{id}/demo/deposit | Make demo trading account deposit
[**makeExchangeAccountProgram**](AssetsApi.md#makeExchangeAccountProgram) | **POST** v2.0/assets/programs/fromexchangeaccount/create | Create an investment program from exchange account
[**makeExchangeProgram**](AssetsApi.md#makeExchangeProgram) | **POST** v2.0/assets/programs/exchange/create | Create an exchange investment program
[**makeExternalAccountSignalProvider**](AssetsApi.md#makeExternalAccountSignalProvider) | **POST** v2.0/assets/tradingaccounts/external/fromaccount/create | Make external trading account signal provider
[**makeProgram**](AssetsApi.md#makeProgram) | **POST** v2.0/assets/programs/create | Create an investment program
[**makeSelfManagedFundPublic**](AssetsApi.md#makeSelfManagedFundPublic) | **POST** v2.0/assets/funds/selfmanaged/makepublic | Make self managed fund public
[**makeSignalProviderProgram**](AssetsApi.md#makeSignalProviderProgram) | **POST** v2.0/assets/programs/fromsignalprovider/create | Create an investment program
[**updateAsset**](AssetsApi.md#updateAsset) | **POST** v2.0/assets/follow/{id}/update | Update investment program/fund details
[**updateAsset_0**](AssetsApi.md#updateAsset_0) | **POST** v2.0/assets/funds/{id}/update | Update investment program/fund details
[**updateAsset_1**](AssetsApi.md#updateAsset_1) | **POST** v2.0/assets/programs/{id}/update | Update investment program/fund details
[**updateFundAssets**](AssetsApi.md#updateFundAssets) | **POST** v2.0/assets/funds/{id}/assets/update | Update fund assets parts
[**updateSignalProviderSettings**](AssetsApi.md#updateSignalProviderSettings) | **POST** v2.0/assets/signal/edit | Edit account signal settings

<a name="cancelChangeBroker"></a>
# **cancelChangeBroker**
> Void cancelChangeBroker(id)

Cancel changing broker in existing program

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.cancelChangeBroker(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#cancelChangeBroker");
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

<a name="changeBroker"></a>
# **changeBroker**
> Void changeBroker(id, body)

Change broker in existing program

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
ChangeBrokerProgramRequest body = new ChangeBrokerProgramRequest(); // ChangeBrokerProgramRequest | 
try {
    Void result = apiInstance.changeBroker(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#changeBroker");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**ChangeBrokerProgramRequest**](ChangeBrokerProgramRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="changeTradingAccountPassword"></a>
# **changeTradingAccountPassword**
> Void changeTradingAccountPassword(id, body)

Change trading account password

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
TradingAccountPwdUpdate body = new TradingAccountPwdUpdate(); // TradingAccountPwdUpdate | 
try {
    Void result = apiInstance.changeTradingAccountPassword(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#changeTradingAccountPassword");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**TradingAccountPwdUpdate**](TradingAccountPwdUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="closeCurrentPeriod"></a>
# **closeCurrentPeriod**
> Void closeCurrentPeriod(id)

Close current period

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.closeCurrentPeriod(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#closeCurrentPeriod");
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

<a name="closeExchangeAccount"></a>
# **closeExchangeAccount**
> Void closeExchangeAccount(id)

Close exchange account

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.closeExchangeAccount(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#closeExchangeAccount");
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

<a name="closeFund"></a>
# **closeFund**
> Void closeFund(id, body)

Close existing fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
TwoFactorCodeModel body = new TwoFactorCodeModel(); // TwoFactorCodeModel | 
try {
    Void result = apiInstance.closeFund(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#closeFund");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**TwoFactorCodeModel**](TwoFactorCodeModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="closeInvestmentProgram"></a>
# **closeInvestmentProgram**
> Void closeInvestmentProgram(id, body)

Close existing investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
TwoFactorCodeModel body = new TwoFactorCodeModel(); // TwoFactorCodeModel | 
try {
    Void result = apiInstance.closeInvestmentProgram(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#closeInvestmentProgram");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**TwoFactorCodeModel**](TwoFactorCodeModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="closeTradingAccount"></a>
# **closeTradingAccount**
> Void closeTradingAccount(id)

Close trading account

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.closeTradingAccount(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#closeTradingAccount");
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

<a name="confirmProgram2FA"></a>
# **confirmProgram2FA**
> Void confirmProgram2FA(id, body)

Confirm 2FA for program if required (for brokers like Huobi)

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
TwoFactorCodeModel body = new TwoFactorCodeModel(); // TwoFactorCodeModel | 
try {
    Void result = apiInstance.confirmProgram2FA(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#confirmProgram2FA");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**TwoFactorCodeModel**](TwoFactorCodeModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="createAccountApiKey"></a>
# **createAccountApiKey**
> ExchangeCredentials createAccountApiKey(id, body)

Create account api key

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
CreateApiKeyModel body = new CreateApiKeyModel(); // CreateApiKeyModel | 
try {
    ExchangeCredentials result = apiInstance.createAccountApiKey(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#createAccountApiKey");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**CreateApiKeyModel**](CreateApiKeyModel.md)|  | [optional]

### Return type

[**ExchangeCredentials**](ExchangeCredentials.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="createExchangeAccount"></a>
# **createExchangeAccount**
> TradingAccountCreateResult createExchangeAccount(body)

Create exchange account

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
NewExchangeAccountRequest body = new NewExchangeAccountRequest(); // NewExchangeAccountRequest | 
try {
    TradingAccountCreateResult result = apiInstance.createExchangeAccount(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#createExchangeAccount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NewExchangeAccountRequest**](NewExchangeAccountRequest.md)|  | [optional]

### Return type

[**TradingAccountCreateResult**](TradingAccountCreateResult.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="createExternalTradingAccount"></a>
# **createExternalTradingAccount**
> TradingAccountCreateResult createExternalTradingAccount(body)

Create external trading account

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
NewExternalTradingAccountRequest body = new NewExternalTradingAccountRequest(); // NewExternalTradingAccountRequest | 
try {
    TradingAccountCreateResult result = apiInstance.createExternalTradingAccount(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#createExternalTradingAccount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NewExternalTradingAccountRequest**](NewExternalTradingAccountRequest.md)|  | [optional]

### Return type

[**TradingAccountCreateResult**](TradingAccountCreateResult.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="createFund"></a>
# **createFund**
> Void createFund(body)

Create fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
NewFundRequest body = new NewFundRequest(); // NewFundRequest | 
try {
    Void result = apiInstance.createFund(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#createFund");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NewFundRequest**](NewFundRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="createSelfManagedFund"></a>
# **createSelfManagedFund**
> Void createSelfManagedFund(body)

Create self managed fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
NewSelfManagedFundRequest body = new NewSelfManagedFundRequest(); // NewSelfManagedFundRequest | 
try {
    Void result = apiInstance.createSelfManagedFund(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#createSelfManagedFund");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NewSelfManagedFundRequest**](NewSelfManagedFundRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="createTradingAccount"></a>
# **createTradingAccount**
> TradingAccountCreateResult createTradingAccount(body)

Create trading account

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
NewTradingAccountRequest body = new NewTradingAccountRequest(); // NewTradingAccountRequest | 
try {
    TradingAccountCreateResult result = apiInstance.createTradingAccount(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#createTradingAccount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NewTradingAccountRequest**](NewTradingAccountRequest.md)|  | [optional]

### Return type

[**TradingAccountCreateResult**](TradingAccountCreateResult.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="deleteAccountApiKey"></a>
# **deleteAccountApiKey**
> Void deleteAccountApiKey(id)

Delete account api key

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.deleteAccountApiKey(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#deleteAccountApiKey");
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

<a name="editAccountApiKeyRestrictions"></a>
# **editAccountApiKeyRestrictions**
> Void editAccountApiKeyRestrictions(id, body)

Edit account api key restrictions

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
EditApiKeyRestrictionsModel body = new EditApiKeyRestrictionsModel(); // EditApiKeyRestrictionsModel | 
try {
    Void result = apiInstance.editAccountApiKeyRestrictions(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#editAccountApiKeyRestrictions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**EditApiKeyRestrictionsModel**](EditApiKeyRestrictionsModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="getAccountApiKey"></a>
# **getAccountApiKey**
> ExchangeCredentialsInfoItemsViewModel getAccountApiKey(id)

Get account api key

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
try {
    ExchangeCredentialsInfoItemsViewModel result = apiInstance.getAccountApiKey(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#getAccountApiKey");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**ExchangeCredentialsInfoItemsViewModel**](ExchangeCredentialsInfoItemsViewModel.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getLevelsCalculator"></a>
# **getLevelsCalculator**
> ProgramLevelInfo getLevelsCalculator(id)

Get program data for levels calculator

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
try {
    ProgramLevelInfo result = apiInstance.getLevelsCalculator(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#getLevelsCalculator");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**ProgramLevelInfo**](ProgramLevelInfo.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgram2FA"></a>
# **getProgram2FA**
> TwoFactorAuthenticator getProgram2FA(id)

Get 2FA for program if needed

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
try {
    TwoFactorAuthenticator result = apiInstance.getProgram2FA(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#getProgram2FA");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**TwoFactorAuthenticator**](TwoFactorAuthenticator.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="makeAccountProgram"></a>
# **makeAccountProgram**
> Void makeAccountProgram(body)

Create an investment program from trading account

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
MakeTradingAccountProgram body = new MakeTradingAccountProgram(); // MakeTradingAccountProgram | 
try {
    Void result = apiInstance.makeAccountProgram(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeAccountProgram");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MakeTradingAccountProgram**](MakeTradingAccountProgram.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="makeAccountSignalProvider"></a>
# **makeAccountSignalProvider**
> Void makeAccountSignalProvider(body)

Make account signal provider

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
MakeTradingAccountSignalProvider body = new MakeTradingAccountSignalProvider(); // MakeTradingAccountSignalProvider | 
try {
    Void result = apiInstance.makeAccountSignalProvider(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeAccountSignalProvider");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MakeTradingAccountSignalProvider**](MakeTradingAccountSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="makeDemoTradingAccountDeposit"></a>
# **makeDemoTradingAccountDeposit**
> Void makeDemoTradingAccountDeposit(id, body)

Make demo trading account deposit

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
TradingAccountDemoDeposit body = new TradingAccountDemoDeposit(); // TradingAccountDemoDeposit | 
try {
    Void result = apiInstance.makeDemoTradingAccountDeposit(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeDemoTradingAccountDeposit");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**TradingAccountDemoDeposit**](TradingAccountDemoDeposit.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="makeExchangeAccountProgram"></a>
# **makeExchangeAccountProgram**
> Void makeExchangeAccountProgram(body)

Create an investment program from exchange account

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
MakeExchangeAccountProgram body = new MakeExchangeAccountProgram(); // MakeExchangeAccountProgram | 
try {
    Void result = apiInstance.makeExchangeAccountProgram(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeExchangeAccountProgram");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MakeExchangeAccountProgram**](MakeExchangeAccountProgram.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="makeExchangeProgram"></a>
# **makeExchangeProgram**
> TradingAccountCreateResult makeExchangeProgram(body)

Create an exchange investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
MakeExchangeProgram body = new MakeExchangeProgram(); // MakeExchangeProgram | 
try {
    TradingAccountCreateResult result = apiInstance.makeExchangeProgram(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeExchangeProgram");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MakeExchangeProgram**](MakeExchangeProgram.md)|  | [optional]

### Return type

[**TradingAccountCreateResult**](TradingAccountCreateResult.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="makeExternalAccountSignalProvider"></a>
# **makeExternalAccountSignalProvider**
> Void makeExternalAccountSignalProvider(body)

Make external trading account signal provider

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
MakeTradingAccountSignalProvider body = new MakeTradingAccountSignalProvider(); // MakeTradingAccountSignalProvider | 
try {
    Void result = apiInstance.makeExternalAccountSignalProvider(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeExternalAccountSignalProvider");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MakeTradingAccountSignalProvider**](MakeTradingAccountSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="makeProgram"></a>
# **makeProgram**
> TradingAccountCreateResult makeProgram(body)

Create an investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
MakeProgram body = new MakeProgram(); // MakeProgram | 
try {
    TradingAccountCreateResult result = apiInstance.makeProgram(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeProgram");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MakeProgram**](MakeProgram.md)|  | [optional]

### Return type

[**TradingAccountCreateResult**](TradingAccountCreateResult.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="makeSelfManagedFundPublic"></a>
# **makeSelfManagedFundPublic**
> Void makeSelfManagedFundPublic(body)

Make self managed fund public

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
MakeSelfManagedFundPublicRequest body = new MakeSelfManagedFundPublicRequest(); // MakeSelfManagedFundPublicRequest | 
try {
    Void result = apiInstance.makeSelfManagedFundPublic(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeSelfManagedFundPublic");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MakeSelfManagedFundPublicRequest**](MakeSelfManagedFundPublicRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="makeSignalProviderProgram"></a>
# **makeSignalProviderProgram**
> Void makeSignalProviderProgram(body)

Create an investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
MakeSignalProviderProgram body = new MakeSignalProviderProgram(); // MakeSignalProviderProgram | 
try {
    Void result = apiInstance.makeSignalProviderProgram(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeSignalProviderProgram");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MakeSignalProviderProgram**](MakeSignalProviderProgram.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateAsset"></a>
# **updateAsset**
> Void updateAsset(id, body)

Update investment program/fund details

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
ProgramUpdate body = new ProgramUpdate(); // ProgramUpdate | 
try {
    Void result = apiInstance.updateAsset(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#updateAsset");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**ProgramUpdate**](ProgramUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateAsset_0"></a>
# **updateAsset_0**
> Void updateAsset_0(id, body)

Update investment program/fund details

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
ProgramUpdate body = new ProgramUpdate(); // ProgramUpdate | 
try {
    Void result = apiInstance.updateAsset_0(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#updateAsset_0");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**ProgramUpdate**](ProgramUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateAsset_1"></a>
# **updateAsset_1**
> Void updateAsset_1(id, body)

Update investment program/fund details

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
ProgramUpdate body = new ProgramUpdate(); // ProgramUpdate | 
try {
    Void result = apiInstance.updateAsset_1(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#updateAsset_1");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**ProgramUpdate**](ProgramUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateFundAssets"></a>
# **updateFundAssets**
> Void updateFundAssets(id, body)

Update fund assets parts

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
List<FundAssetPart> body = Arrays.asList(new FundAssetPart()); // List<FundAssetPart> | 
try {
    Void result = apiInstance.updateFundAssets(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#updateFundAssets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**List&lt;FundAssetPart&gt;**](FundAssetPart.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateSignalProviderSettings"></a>
# **updateSignalProviderSettings**
> Void updateSignalProviderSettings(body)

Edit account signal settings

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

AssetsApi apiInstance = new AssetsApi();
CreateSignalProvider body = new CreateSignalProvider(); // CreateSignalProvider | 
try {
    Void result = apiInstance.updateSignalProviderSettings(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#updateSignalProviderSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateSignalProvider**](CreateSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

