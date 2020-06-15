# NotificationsApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addNotificationsSettings**](NotificationsApi.md#addNotificationsSettings) | **POST** v2.0/notifications/settings/add | Add new setting
[**getNewNotificationsCount**](NotificationsApi.md#getNewNotificationsCount) | **GET** v2.0/notifications/new | Unread notifications count
[**getNotifications**](NotificationsApi.md#getNotifications) | **GET** v2.0/notifications | User notifications
[**getNotificationsFollowSettings**](NotificationsApi.md#getNotificationsFollowSettings) | **GET** v2.0/notifications/settings/follow/{id} | User settings for follow
[**getNotificationsFundSettings**](NotificationsApi.md#getNotificationsFundSettings) | **GET** v2.0/notifications/settings/funds/{id} | User settings for fund
[**getNotificationsManagerSettings**](NotificationsApi.md#getNotificationsManagerSettings) | **GET** v2.0/notifications/settings/managers/{id} | User settings for manager
[**getNotificationsProgramSettings**](NotificationsApi.md#getNotificationsProgramSettings) | **GET** v2.0/notifications/settings/programs/{id} | User settings for program
[**getNotificationsSettings**](NotificationsApi.md#getNotificationsSettings) | **GET** v2.0/notifications/settings | User settings
[**readAllNotification**](NotificationsApi.md#readAllNotification) | **POST** v2.0/notifications/all/read | Read all notification
[**readNotification**](NotificationsApi.md#readNotification) | **POST** v2.0/notifications/{id}/read | Read notification
[**removeNotificationsSettings**](NotificationsApi.md#removeNotificationsSettings) | **POST** v2.0/notifications/settings/remove/{id} | Remove setting
[**toggleNotificationSettings**](NotificationsApi.md#toggleNotificationSettings) | **POST** v2.0/notifications/settings/{id}/{enable} | Enable/disable setting

<a name="addNotificationsSettings"></a>
# **addNotificationsSettings**
> UUID addNotificationsSettings(assetId, managerId, type, conditionType, conditionAmount)

Add new setting

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
UUID assetId = new UUID(); // UUID | 
UUID managerId = new UUID(); // UUID | 
NotificationType type = new NotificationType(); // NotificationType | 
NotificationSettingConditionType conditionType = new NotificationSettingConditionType(); // NotificationSettingConditionType | 
Double conditionAmount = 3.4D; // Double | 
try {
    UUID result = apiInstance.addNotificationsSettings(assetId, managerId, type, conditionType, conditionAmount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#addNotificationsSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **assetId** | [**UUID**](.md)|  | [optional]
 **managerId** | [**UUID**](.md)|  | [optional]
 **type** | [**NotificationType**](.md)|  | [optional]
 **conditionType** | [**NotificationSettingConditionType**](.md)|  | [optional]
 **conditionAmount** | **Double**|  | [optional]

### Return type

[**UUID**](UUID.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNewNotificationsCount"></a>
# **getNewNotificationsCount**
> Integer getNewNotificationsCount()

Unread notifications count

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
try {
    Integer result = apiInstance.getNewNotificationsCount();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#getNewNotificationsCount");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Integer**

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNotifications"></a>
# **getNotifications**
> NotificationList getNotifications(skip, take)

User notifications

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
Integer skip = 56; // Integer | 
Integer take = 56; // Integer | 
try {
    NotificationList result = apiInstance.getNotifications(skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#getNotifications");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **skip** | **Integer**|  | [optional]
 **take** | **Integer**|  | [optional]

### Return type

[**NotificationList**](NotificationList.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNotificationsFollowSettings"></a>
# **getNotificationsFollowSettings**
> FollowNotificationSettingList getNotificationsFollowSettings(id)

User settings for follow

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
String id = "id_example"; // String | 
try {
    FollowNotificationSettingList result = apiInstance.getNotificationsFollowSettings(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#getNotificationsFollowSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  |

### Return type

[**FollowNotificationSettingList**](FollowNotificationSettingList.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNotificationsFundSettings"></a>
# **getNotificationsFundSettings**
> FundNotificationSettingList getNotificationsFundSettings(id)

User settings for fund

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
String id = "id_example"; // String | 
try {
    FundNotificationSettingList result = apiInstance.getNotificationsFundSettings(id);
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

### Return type

[**FundNotificationSettingList**](FundNotificationSettingList.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNotificationsManagerSettings"></a>
# **getNotificationsManagerSettings**
> ManagerNotificationSettingList getNotificationsManagerSettings(id)

User settings for manager

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
String id = "id_example"; // String | 
try {
    ManagerNotificationSettingList result = apiInstance.getNotificationsManagerSettings(id);
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

### Return type

[**ManagerNotificationSettingList**](ManagerNotificationSettingList.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNotificationsProgramSettings"></a>
# **getNotificationsProgramSettings**
> ProgramNotificationSettingList getNotificationsProgramSettings(id)

User settings for program

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
String id = "id_example"; // String | 
try {
    ProgramNotificationSettingList result = apiInstance.getNotificationsProgramSettings(id);
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

### Return type

[**ProgramNotificationSettingList**](ProgramNotificationSettingList.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="getNotificationsSettings"></a>
# **getNotificationsSettings**
> NotificationSettingList getNotificationsSettings()

User settings

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
try {
    NotificationSettingList result = apiInstance.getNotificationsSettings();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#getNotificationsSettings");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**NotificationSettingList**](NotificationSettingList.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="readAllNotification"></a>
# **readAllNotification**
> Void readAllNotification()

Read all notification

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
try {
    Void result = apiInstance.readAllNotification();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#readAllNotification");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="readNotification"></a>
# **readNotification**
> Void readNotification(id)

Read notification

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.readNotification(id);
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

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="removeNotificationsSettings"></a>
# **removeNotificationsSettings**
> Void removeNotificationsSettings(id)

Remove setting

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.removeNotificationsSettings(id);
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

### Return type

[**Void**](.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="toggleNotificationSettings"></a>
# **toggleNotificationSettings**
> UUID toggleNotificationSettings(id, enable)

Enable/disable setting

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
UUID id = new UUID(); // UUID | 
Boolean enable = true; // Boolean | 
try {
    UUID result = apiInstance.toggleNotificationSettings(id, enable);
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

### Return type

[**UUID**](UUID.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

