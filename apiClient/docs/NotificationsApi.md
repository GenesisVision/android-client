# NotificationsApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addNotificationsSettings**](NotificationsApi.md#addNotificationsSettings) | **POST** v2.0/notifications/settings/add | Add new setting
[**getNewNotificationsCount**](NotificationsApi.md#getNewNotificationsCount) | **GET** v2.0/notifications/new | Unread notifications count
[**getNotifications**](NotificationsApi.md#getNotifications) | **GET** v2.0/notifications | User notifications
[**getNotificationsFundSettings**](NotificationsApi.md#getNotificationsFundSettings) | **GET** v2.0/notifications/settings/funds/{id} | User settings for fund
[**getNotificationsManagerSettings**](NotificationsApi.md#getNotificationsManagerSettings) | **GET** v2.0/notifications/settings/managers/{id} | User settings for manager
[**getNotificationsProgramSettings**](NotificationsApi.md#getNotificationsProgramSettings) | **GET** v2.0/notifications/settings/programs/{id} | User settings for program
[**getNotificationsSettings**](NotificationsApi.md#getNotificationsSettings) | **GET** v2.0/notifications/settings | User settings
[**readNotification**](NotificationsApi.md#readNotification) | **POST** v2.0/notifications/{id}/read | Read notification
[**removeNotificationsSettings**](NotificationsApi.md#removeNotificationsSettings) | **POST** v2.0/notifications/settings/remove/{id} | Remove setting
[**toggleNotificationSettings**](NotificationsApi.md#toggleNotificationSettings) | **POST** v2.0/notifications/settings/{id}/{enable} | Enable/disable setting

<a name="addNotificationsSettings"></a>
# **addNotificationsSettings**
> UUID addNotificationsSettings(authorization, assetId, managerId, type, conditionType, conditionAmount)

Add new setting

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
String authorization = "authorization_example"; // String | JWT access token
UUID assetId = new UUID(); // UUID | 
UUID managerId = new UUID(); // UUID | 
String type = "type_example"; // String | 
String conditionType = "conditionType_example"; // String | 
Double conditionAmount = 3.4D; // Double | 
try {
    UUID result = apiInstance.addNotificationsSettings(authorization, assetId, managerId, type, conditionType, conditionAmount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#addNotificationsSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **assetId** | [**UUID**](.md)|  | [optional]
 **managerId** | [**UUID**](.md)|  | [optional]
 **type** | **String**|  | [optional] [enum: PlatformNewsAndUpdates, PlatformEmergency, PlatformOther, ProfileUpdated, ProfilePwdUpdated, ProfileVerification, Profile2FA, ProfileSecurity, TradingAccountPwdUpdated, ProgramNewsAndUpdates, ProgramEndOfPeriod, ProgramCondition, ProgramExceedInvestmentLimit, FundNewsAndUpdates, FundEndOfPeriod, FundRebalancing, ManagerNewProgram, ManagerNewFund, ManagerNewExternalSignalAccount, ManagerNewSignalProvider, Signals, ExternalSignals]
 **conditionType** | **String**|  | [optional] [enum: Empty, Profit, Level, AvailableToInvest]
 **conditionAmount** | **Double**|  | [optional]

### Return type

[**UUID**](UUID.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNewNotificationsCount"></a>
# **getNewNotificationsCount**
> Integer getNewNotificationsCount(authorization)

Unread notifications count

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Integer result = apiInstance.getNewNotificationsCount(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#getNewNotificationsCount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

**Integer**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNotifications"></a>
# **getNotifications**
> NotificationList getNotifications(authorization, skip, take)

User notifications

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
String authorization = "authorization_example"; // String | JWT access token
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    NotificationList result = apiInstance.getNotifications(authorization, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#getNotifications");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**NotificationList**](NotificationList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNotificationsFundSettings"></a>
# **getNotificationsFundSettings**
> FundNotificationSettingList getNotificationsFundSettings(id, authorization)

User settings for fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
String id = "id_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    FundNotificationSettingList result = apiInstance.getNotificationsFundSettings(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#getNotificationsFundSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**FundNotificationSettingList**](FundNotificationSettingList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNotificationsManagerSettings"></a>
# **getNotificationsManagerSettings**
> ManagerNotificationSettingList getNotificationsManagerSettings(id, authorization)

User settings for manager

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
String id = "id_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ManagerNotificationSettingList result = apiInstance.getNotificationsManagerSettings(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#getNotificationsManagerSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**ManagerNotificationSettingList**](ManagerNotificationSettingList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNotificationsProgramSettings"></a>
# **getNotificationsProgramSettings**
> ProgramNotificationSettingList getNotificationsProgramSettings(id, authorization)

User settings for program

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
String id = "id_example"; // String | 
String authorization = "authorization_example"; // String | JWT access token
try {
    ProgramNotificationSettingList result = apiInstance.getNotificationsProgramSettings(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#getNotificationsProgramSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**ProgramNotificationSettingList**](ProgramNotificationSettingList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNotificationsSettings"></a>
# **getNotificationsSettings**
> NotificationSettingList getNotificationsSettings(authorization)

User settings

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    NotificationSettingList result = apiInstance.getNotificationsSettings(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#getNotificationsSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**NotificationSettingList**](NotificationSettingList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="readNotification"></a>
# **readNotification**
> Void readNotification(id, authorization)

Read notification

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.readNotification(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#readNotification");
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

<a name="removeNotificationsSettings"></a>
# **removeNotificationsSettings**
> Void removeNotificationsSettings(id, authorization)

Remove setting

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.removeNotificationsSettings(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#removeNotificationsSettings");
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

<a name="toggleNotificationSettings"></a>
# **toggleNotificationSettings**
> UUID toggleNotificationSettings(id, enable, authorization)

Enable/disable setting

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
UUID id = new UUID(); // UUID | 
Boolean enable = true; // Boolean | 
String authorization = "authorization_example"; // String | JWT access token
try {
    UUID result = apiInstance.toggleNotificationSettings(id, enable, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#toggleNotificationSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **enable** | **Boolean**|  |
 **authorization** | **String**| JWT access token |

### Return type

[**UUID**](UUID.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

