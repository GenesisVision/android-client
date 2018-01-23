# BrokerApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiBrokerInitDataGet**](BrokerApi.md#apiBrokerInitDataGet) | **GET** api/broker/initData | 
[**apiBrokerPeriodCloseGet**](BrokerApi.md#apiBrokerPeriodCloseGet) | **GET** api/broker/period/close | 
[**apiBrokerPeriodSetStartBalanceGet**](BrokerApi.md#apiBrokerPeriodSetStartBalanceGet) | **GET** api/broker/period/setStartBalance | 
[**apiBrokerPeriodlosingDataGet**](BrokerApi.md#apiBrokerPeriodlosingDataGet) | **GET** api/broker/period/сlosingData | 
[**apiManagerBrokersPost**](BrokerApi.md#apiManagerBrokersPost) | **POST** api/manager/brokers | 


<a name="apiBrokerInitDataGet"></a>
# **apiBrokerInitDataGet**
> BrokerInitData apiBrokerInitDataGet(brokerTradeServerId)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID brokerTradeServerId = new UUID(); // UUID | 
try {
    BrokerInitData result = apiInstance.apiBrokerInitDataGet(brokerTradeServerId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerInitDataGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **brokerTradeServerId** | [**UUID**](.md)|  |

### Return type

[**BrokerInitData**](BrokerInitData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerPeriodCloseGet"></a>
# **apiBrokerPeriodCloseGet**
> Void apiBrokerPeriodCloseGet(investmentProgramId)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID investmentProgramId = new UUID(); // UUID | 
try {
    Void result = apiInstance.apiBrokerPeriodCloseGet(investmentProgramId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodCloseGet");
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

<a name="apiBrokerPeriodSetStartBalanceGet"></a>
# **apiBrokerPeriodSetStartBalanceGet**
> Void apiBrokerPeriodSetStartBalanceGet(periodId, balance)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID periodId = new UUID(); // UUID | 
Double balance = 3.4D; // Double | 
try {
    Void result = apiInstance.apiBrokerPeriodSetStartBalanceGet(periodId, balance);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodSetStartBalanceGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **periodId** | [**UUID**](.md)|  |
 **balance** | **Double**|  |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiBrokerPeriodlosingDataGet"></a>
# **apiBrokerPeriodlosingDataGet**
> ClosePeriodData apiBrokerPeriodlosingDataGet(investmentProgramId)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
UUID investmentProgramId = new UUID(); // UUID | 
try {
    ClosePeriodData result = apiInstance.apiBrokerPeriodlosingDataGet(investmentProgramId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiBrokerPeriodlosingDataGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **investmentProgramId** | [**UUID**](.md)|  |

### Return type

[**ClosePeriodData**](ClosePeriodData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiManagerBrokersPost"></a>
# **apiManagerBrokersPost**
> BrokersViewModel apiManagerBrokersPost(filter)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BrokerApi;


BrokerApi apiInstance = new BrokerApi();
BrokersFilter filter = new BrokersFilter(); // BrokersFilter | 
try {
    BrokersViewModel result = apiInstance.apiManagerBrokersPost(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BrokerApi#apiManagerBrokersPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**BrokersFilter**](BrokersFilter.md)|  | [optional]

### Return type

[**BrokersViewModel**](BrokersViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

