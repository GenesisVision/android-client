# AssetsApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelChangeBroker**](AssetsApi.md#cancelChangeBroker) | **POST** v2.0/assets/programs/{id}/broker/change/cancel | Cancel changing broker in existing program
[**changeBroker**](AssetsApi.md#changeBroker) | **POST** v2.0/assets/programs/{id}/broker/change | Change broker in existing program
[**changeTradingAccountPassword**](AssetsApi.md#changeTradingAccountPassword) | **POST** v2.0/assets/tradingaccounts/{id}/password/change | Change trading account password
[**closeCurrentPeriod**](AssetsApi.md#closeCurrentPeriod) | **POST** v2.0/assets/programs/{id}/period/close | Close current period
[**closeFund**](AssetsApi.md#closeFund) | **POST** v2.0/assets/funds/{id}/close | Close existing fund
[**closeInvestmentProgram**](AssetsApi.md#closeInvestmentProgram) | **POST** v2.0/assets/programs/{id}/close | Close existing investment program
[**closeTradingAccount**](AssetsApi.md#closeTradingAccount) | **POST** v2.0/assets/tradingaccounts/{id}/close | Close trading account
[**confirmProgram2FA**](AssetsApi.md#confirmProgram2FA) | **POST** v2.0/assets/programs/{id}/2fa/confirm | Confirm 2FA for program if required (for brokers like Huobi)
[**createExternalTradingAccount**](AssetsApi.md#createExternalTradingAccount) | **POST** v2.0/assets/tradingaccounts/external/create | Create external trading account
[**createFund**](AssetsApi.md#createFund) | **POST** v2.0/assets/funds/create | Create fund
[**createTradingAccount**](AssetsApi.md#createTradingAccount) | **POST** v2.0/assets/tradingaccounts/create | Create trading account
[**getLevelsCalculator**](AssetsApi.md#getLevelsCalculator) | **GET** v2.0/assets/programs/{id}/levels/info | Get program data for levels calculator
[**getProgram2FA**](AssetsApi.md#getProgram2FA) | **GET** v2.0/assets/programs/{id}/2fa/get | Get 2FA for program if needed
[**makeAccountProgram**](AssetsApi.md#makeAccountProgram) | **POST** v2.0/assets/programs/fromaccount/create | Create an investment program
[**makeAccountSignalProvider**](AssetsApi.md#makeAccountSignalProvider) | **POST** v2.0/assets/signal/create | Make account signal provider
[**makeDemoTradingAccountDeposit**](AssetsApi.md#makeDemoTradingAccountDeposit) | **POST** v2.0/assets/tradingaccounts/{id}/demo/deposit | Make demo trading account deposit
[**makeExternalAccountSignalProvider**](AssetsApi.md#makeExternalAccountSignalProvider) | **POST** v2.0/assets/tradingaccounts/external/fromaccount/create | Make external trading account signal provider
[**makeSignalProviderProgram**](AssetsApi.md#makeSignalProviderProgram) | **POST** v2.0/assets/programs/fromsignalprovider/create | Create an investment program
[**updateAsset**](AssetsApi.md#updateAsset) | **POST** v2.0/assets/follow/{id}/update | Update investment program/fund details
[**updateAsset_0**](AssetsApi.md#updateAsset_0) | **POST** v2.0/assets/funds/{id}/update | Update investment program/fund details
[**updateAsset_1**](AssetsApi.md#updateAsset_1) | **POST** v2.0/assets/programs/{id}/update | Update investment program/fund details
[**updateFundAssets**](AssetsApi.md#updateFundAssets) | **POST** v2.0/assets/funds/{id}/assets/update | Update fund assets parts
[**updateSignalProviderSettings**](AssetsApi.md#updateSignalProviderSettings) | **POST** v2.0/assets/signal/edit | Edit account signal settings

<a name="cancelChangeBroker"></a>
# **cancelChangeBroker**
> Void cancelChangeBroker(id, authorization)

Cancel changing broker in existing program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.cancelChangeBroker(id, authorization);
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
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="changeBroker"></a>
# **changeBroker**
> Void changeBroker(authorization, id, body)

Change broker in existing program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
ChangeBrokerProgramRequest body = new ChangeBrokerProgramRequest(); // ChangeBrokerProgramRequest | 
try {
    Void result = apiInstance.changeBroker(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#changeBroker");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**ChangeBrokerProgramRequest**](ChangeBrokerProgramRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="changeTradingAccountPassword"></a>
# **changeTradingAccountPassword**
> Void changeTradingAccountPassword(authorization, id, body)

Change trading account password

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
TradingAccountPwdUpdate body = new TradingAccountPwdUpdate(); // TradingAccountPwdUpdate | 
try {
    Void result = apiInstance.changeTradingAccountPassword(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#changeTradingAccountPassword");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**TradingAccountPwdUpdate**](TradingAccountPwdUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="closeCurrentPeriod"></a>
# **closeCurrentPeriod**
> Void closeCurrentPeriod(id, authorization)

Close current period

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.closeCurrentPeriod(id, authorization);
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
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="closeFund"></a>
# **closeFund**
> Void closeFund(authorization, id, body)

Close existing fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
TwoFactorCodeModel body = new TwoFactorCodeModel(); // TwoFactorCodeModel | 
try {
    Void result = apiInstance.closeFund(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#closeFund");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**TwoFactorCodeModel**](TwoFactorCodeModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="closeInvestmentProgram"></a>
# **closeInvestmentProgram**
> Void closeInvestmentProgram(authorization, id, body)

Close existing investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
TwoFactorCodeModel body = new TwoFactorCodeModel(); // TwoFactorCodeModel | 
try {
    Void result = apiInstance.closeInvestmentProgram(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#closeInvestmentProgram");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**TwoFactorCodeModel**](TwoFactorCodeModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="closeTradingAccount"></a>
# **closeTradingAccount**
> Void closeTradingAccount(id, authorization)

Close trading account

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.closeTradingAccount(id, authorization);
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
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="confirmProgram2FA"></a>
# **confirmProgram2FA**
> Void confirmProgram2FA(authorization, id, body)

Confirm 2FA for program if required (for brokers like Huobi)

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
TwoFactorCodeModel body = new TwoFactorCodeModel(); // TwoFactorCodeModel | 
try {
    Void result = apiInstance.confirmProgram2FA(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#confirmProgram2FA");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**TwoFactorCodeModel**](TwoFactorCodeModel.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="createExternalTradingAccount"></a>
# **createExternalTradingAccount**
> TradingAccountCreateResult createExternalTradingAccount(authorization, body)

Create external trading account

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
NewExternalTradingAccountRequest body = new NewExternalTradingAccountRequest(); // NewExternalTradingAccountRequest | 
try {
    TradingAccountCreateResult result = apiInstance.createExternalTradingAccount(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#createExternalTradingAccount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**NewExternalTradingAccountRequest**](NewExternalTradingAccountRequest.md)|  | [optional]

### Return type

[**TradingAccountCreateResult**](TradingAccountCreateResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="createFund"></a>
# **createFund**
> Void createFund(authorization, body)

Create fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
NewFundRequest body = new NewFundRequest(); // NewFundRequest | 
try {
    Void result = apiInstance.createFund(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#createFund");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**NewFundRequest**](NewFundRequest.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="createTradingAccount"></a>
# **createTradingAccount**
> TradingAccountCreateResult createTradingAccount(authorization, body)

Create trading account

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
NewTradingAccountRequest body = new NewTradingAccountRequest(); // NewTradingAccountRequest | 
try {
    TradingAccountCreateResult result = apiInstance.createTradingAccount(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#createTradingAccount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**NewTradingAccountRequest**](NewTradingAccountRequest.md)|  | [optional]

### Return type

[**TradingAccountCreateResult**](TradingAccountCreateResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="getLevelsCalculator"></a>
# **getLevelsCalculator**
> ProgramLevelInfo getLevelsCalculator(id, authorization)

Get program data for levels calculator

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramLevelInfo result = apiInstance.getLevelsCalculator(id, authorization);
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
 **authorization** | **String**| JWT access token |

### Return type

[**ProgramLevelInfo**](ProgramLevelInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getProgram2FA"></a>
# **getProgram2FA**
> TwoFactorAuthenticator getProgram2FA(id, authorization)

Get 2FA for program if needed

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    TwoFactorAuthenticator result = apiInstance.getProgram2FA(id, authorization);
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
 **authorization** | **String**| JWT access token |

### Return type

[**TwoFactorAuthenticator**](TwoFactorAuthenticator.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="makeAccountProgram"></a>
# **makeAccountProgram**
> Void makeAccountProgram(authorization, body)

Create an investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
MakeTradingAccountProgram body = new MakeTradingAccountProgram(); // MakeTradingAccountProgram | 
try {
    Void result = apiInstance.makeAccountProgram(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeAccountProgram");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**MakeTradingAccountProgram**](MakeTradingAccountProgram.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="makeAccountSignalProvider"></a>
# **makeAccountSignalProvider**
> Void makeAccountSignalProvider(authorization, body)

Make account signal provider

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
MakeTradingAccountSignalProvider body = new MakeTradingAccountSignalProvider(); // MakeTradingAccountSignalProvider | 
try {
    Void result = apiInstance.makeAccountSignalProvider(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeAccountSignalProvider");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**MakeTradingAccountSignalProvider**](MakeTradingAccountSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="makeDemoTradingAccountDeposit"></a>
# **makeDemoTradingAccountDeposit**
> Void makeDemoTradingAccountDeposit(authorization, id, body)

Make demo trading account deposit

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
TradingAccountDemoDeposit body = new TradingAccountDemoDeposit(); // TradingAccountDemoDeposit | 
try {
    Void result = apiInstance.makeDemoTradingAccountDeposit(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeDemoTradingAccountDeposit");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**TradingAccountDemoDeposit**](TradingAccountDemoDeposit.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="makeExternalAccountSignalProvider"></a>
# **makeExternalAccountSignalProvider**
> Void makeExternalAccountSignalProvider(authorization, body)

Make external trading account signal provider

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
MakeTradingAccountSignalProvider body = new MakeTradingAccountSignalProvider(); // MakeTradingAccountSignalProvider | 
try {
    Void result = apiInstance.makeExternalAccountSignalProvider(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeExternalAccountSignalProvider");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**MakeTradingAccountSignalProvider**](MakeTradingAccountSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="makeSignalProviderProgram"></a>
# **makeSignalProviderProgram**
> Void makeSignalProviderProgram(authorization, body)

Create an investment program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
MakeSignalProviderProgram body = new MakeSignalProviderProgram(); // MakeSignalProviderProgram | 
try {
    Void result = apiInstance.makeSignalProviderProgram(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#makeSignalProviderProgram");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**MakeSignalProviderProgram**](MakeSignalProviderProgram.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateAsset"></a>
# **updateAsset**
> Void updateAsset(authorization, id, body)

Update investment program/fund details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
ProgramUpdate body = new ProgramUpdate(); // ProgramUpdate | 
try {
    Void result = apiInstance.updateAsset(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#updateAsset");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**ProgramUpdate**](ProgramUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateAsset_0"></a>
# **updateAsset_0**
> Void updateAsset_0(authorization, id, body)

Update investment program/fund details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
ProgramUpdate body = new ProgramUpdate(); // ProgramUpdate | 
try {
    Void result = apiInstance.updateAsset_0(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#updateAsset_0");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**ProgramUpdate**](ProgramUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateAsset_1"></a>
# **updateAsset_1**
> Void updateAsset_1(authorization, id, body)

Update investment program/fund details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
ProgramUpdate body = new ProgramUpdate(); // ProgramUpdate | 
try {
    Void result = apiInstance.updateAsset_1(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#updateAsset_1");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**ProgramUpdate**](ProgramUpdate.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateFundAssets"></a>
# **updateFundAssets**
> Void updateFundAssets(authorization, id, body)

Update fund assets parts

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
List<FundAssetPart> body = Arrays.asList(new FundAssetPart()); // List<FundAssetPart> | 
try {
    Void result = apiInstance.updateFundAssets(authorization, id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#updateFundAssets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **id** | [**UUID**](.md)|  |
 **body** | [**List&lt;FundAssetPart&gt;**](FundAssetPart.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="updateSignalProviderSettings"></a>
# **updateSignalProviderSettings**
> Void updateSignalProviderSettings(authorization, body)

Edit account signal settings

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AssetsApi;


AssetsApi apiInstance = new AssetsApi();
String authorization = "authorization_example"; // String | JWT access token
CreateSignalProvider body = new CreateSignalProvider(); // CreateSignalProvider | 
try {
    Void result = apiInstance.updateSignalProviderSettings(authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssetsApi#updateSignalProviderSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **body** | [**CreateSignalProvider**](CreateSignalProvider.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

