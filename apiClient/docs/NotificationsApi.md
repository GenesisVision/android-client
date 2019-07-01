# NotificationsApi

All URIs are relative to *https://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10NotificationsByIdReadPost**](NotificationsApi.md#v10NotificationsByIdReadPost) | **POST** v1.0/notifications/{id}/read | Read notification
[**v10NotificationsGet**](NotificationsApi.md#v10NotificationsGet) | **GET** v1.0/notifications | User notifications
[**v10NotificationsNewGet**](NotificationsApi.md#v10NotificationsNewGet) | **GET** v1.0/notifications/new | Unread notifications count
[**v10NotificationsSettingsAddPost**](NotificationsApi.md#v10NotificationsSettingsAddPost) | **POST** v1.0/notifications/settings/add | Add new setting
[**v10NotificationsSettingsByIdByEnablePost**](NotificationsApi.md#v10NotificationsSettingsByIdByEnablePost) | **POST** v1.0/notifications/settings/{id}/{enable} | Enable/disable setting
[**v10NotificationsSettingsFundsByIdGet**](NotificationsApi.md#v10NotificationsSettingsFundsByIdGet) | **GET** v1.0/notifications/settings/funds/{id} | User settings for fund
[**v10NotificationsSettingsGet**](NotificationsApi.md#v10NotificationsSettingsGet) | **GET** v1.0/notifications/settings | User settings
[**v10NotificationsSettingsManagersByIdGet**](NotificationsApi.md#v10NotificationsSettingsManagersByIdGet) | **GET** v1.0/notifications/settings/managers/{id} | User settings for manager
[**v10NotificationsSettingsProgramsByIdGet**](NotificationsApi.md#v10NotificationsSettingsProgramsByIdGet) | **GET** v1.0/notifications/settings/programs/{id} | User settings for program
[**v10NotificationsSettingsRemoveByIdPost**](NotificationsApi.md#v10NotificationsSettingsRemoveByIdPost) | **POST** v1.0/notifications/settings/remove/{id} | Remove setting


<a name="v10NotificationsByIdReadPost"></a>
# **v10NotificationsByIdReadPost**
> Void v10NotificationsByIdReadPost(id, authorization)

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
    Void result = apiInstance.v10NotificationsByIdReadPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsByIdReadPost");
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

<a name="v10NotificationsGet"></a>
# **v10NotificationsGet**
> NotificationList v10NotificationsGet(authorization, skip, take)

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
    NotificationList result = apiInstance.v10NotificationsGet(authorization, skip, take);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsGet");
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

<a name="v10NotificationsNewGet"></a>
# **v10NotificationsNewGet**
> Integer v10NotificationsNewGet(authorization)

Unread notifications count

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Integer result = apiInstance.v10NotificationsNewGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsNewGet");
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

<a name="v10NotificationsSettingsAddPost"></a>
# **v10NotificationsSettingsAddPost**
> UUID v10NotificationsSettingsAddPost(authorization, assetId, managerId, type, conditionType, conditionAmount)

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
    UUID result = apiInstance.v10NotificationsSettingsAddPost(authorization, assetId, managerId, type, conditionType, conditionAmount);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsSettingsAddPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **assetId** | [**UUID**](.md)|  | [optional]
 **managerId** | [**UUID**](.md)|  | [optional]
 **type** | **String**|  | [optional] [enum: PlatformNewsAndUpdates, PlatformEmergency, PlatformOther, ProfileUpdated, ProfilePwdUpdated, ProfileVerification, Profile2FA, ProfileSecurity, TradingAccountPwdUpdated, ProgramNewsAndUpdates, ProgramEndOfPeriod, ProgramCondition, ProgramExceedInvestmentLimit, FundNewsAndUpdates, FundEndOfPeriod, FundRebalancing, ManagerNewProgram, Signals]
 **conditionType** | **String**|  | [optional] [enum: Empty, Profit, Level, AvailableToInvest]
 **conditionAmount** | **Double**|  | [optional]

### Return type

[**UUID**](UUID.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10NotificationsSettingsByIdByEnablePost"></a>
# **v10NotificationsSettingsByIdByEnablePost**
> UUID v10NotificationsSettingsByIdByEnablePost(id, enable, authorization)

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
    UUID result = apiInstance.v10NotificationsSettingsByIdByEnablePost(id, enable, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsSettingsByIdByEnablePost");
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

<a name="v10NotificationsSettingsFundsByIdGet"></a>
# **v10NotificationsSettingsFundsByIdGet**
> FundNotificationSettingList v10NotificationsSettingsFundsByIdGet(id, authorization)

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
    FundNotificationSettingList result = apiInstance.v10NotificationsSettingsFundsByIdGet(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsSettingsFundsByIdGet");
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

<a name="v10NotificationsSettingsGet"></a>
# **v10NotificationsSettingsGet**
> NotificationSettingList v10NotificationsSettingsGet(authorization)

User settings

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    NotificationSettingList result = apiInstance.v10NotificationsSettingsGet(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsSettingsGet");
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

<a name="v10NotificationsSettingsManagersByIdGet"></a>
# **v10NotificationsSettingsManagersByIdGet**
> ManagerNotificationSettingList v10NotificationsSettingsManagersByIdGet(id, authorization)

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
    ManagerNotificationSettingList result = apiInstance.v10NotificationsSettingsManagersByIdGet(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsSettingsManagersByIdGet");
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

<a name="v10NotificationsSettingsProgramsByIdGet"></a>
# **v10NotificationsSettingsProgramsByIdGet**
> ProgramNotificationSettingList v10NotificationsSettingsProgramsByIdGet(id, authorization)

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
    ProgramNotificationSettingList result = apiInstance.v10NotificationsSettingsProgramsByIdGet(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsSettingsProgramsByIdGet");
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

<a name="v10NotificationsSettingsRemoveByIdPost"></a>
# **v10NotificationsSettingsRemoveByIdPost**
> Void v10NotificationsSettingsRemoveByIdPost(id, authorization)

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
    Void result = apiInstance.v10NotificationsSettingsRemoveByIdPost(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsSettingsRemoveByIdPost");
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

