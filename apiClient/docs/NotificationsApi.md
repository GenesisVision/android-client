# NotificationsApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10NotificationsGet**](NotificationsApi.md#v10NotificationsGet) | **GET** v1.0/notifications | 
[**v10NotificationsNewGet**](NotificationsApi.md#v10NotificationsNewGet) | **GET** v1.0/notifications/new | 
[**v10NotificationsSettingsAddPost**](NotificationsApi.md#v10NotificationsSettingsAddPost) | **POST** v1.0/notifications/settings/add | 
[**v10NotificationsSettingsGet**](NotificationsApi.md#v10NotificationsSettingsGet) | **GET** v1.0/notifications/settings | 
[**v10NotificationsSettingsRemoveByIdPost**](NotificationsApi.md#v10NotificationsSettingsRemoveByIdPost) | **POST** v1.0/notifications/settings/remove/{id} | 


<a name="v10NotificationsGet"></a>
# **v10NotificationsGet**
> NotificationList v10NotificationsGet(authorization, skip, take)



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
> Void v10NotificationsSettingsAddPost(authorization, id, programId, managerId, type, conditionType, conditionAmount)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
String authorization = "authorization_example"; // String | JWT access token
UUID id = new UUID(); // UUID | 
UUID programId = new UUID(); // UUID | 
UUID managerId = new UUID(); // UUID | 
String type = "type_example"; // String | 
String conditionType = "conditionType_example"; // String | 
Double conditionAmount = 3.4D; // Double | 
try {
    Void result = apiInstance.v10NotificationsSettingsAddPost(authorization, id, programId, managerId, type, conditionType, conditionAmount);
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
 **id** | [**UUID**](.md)|  | [optional]
 **programId** | [**UUID**](.md)|  | [optional]
 **managerId** | [**UUID**](.md)|  | [optional]
 **type** | **String**|  | [optional] [enum: PlatformNewsAndUpdates, PlatformEmergency, PlatformOther, ProfileUpdated, ProfilePwdUpdated, ProfileVerification, Profile2FA, ProfileSecurity, ProgramNewsAndUpdates, ProgramEndOfPeriod, ProgramCondition, ManagerNewProgram]
 **conditionType** | **String**|  | [optional] [enum: Empty, Profit, Level]
 **conditionAmount** | **Double**|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10NotificationsSettingsGet"></a>
# **v10NotificationsSettingsGet**
> NotificationSettingList v10NotificationsSettingsGet(authorization)



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

<a name="v10NotificationsSettingsRemoveByIdPost"></a>
# **v10NotificationsSettingsRemoveByIdPost**
> Void v10NotificationsSettingsRemoveByIdPost(id, authorization)



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

