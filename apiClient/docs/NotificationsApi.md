# NotificationsApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10NotificationsGet**](NotificationsApi.md#v10NotificationsGet) | **GET** v1.0/notifications | 
[**v10NotificationsNewGet**](NotificationsApi.md#v10NotificationsNewGet) | **GET** v1.0/notifications/new | 
[**v10NotificationsSettingsGet**](NotificationsApi.md#v10NotificationsSettingsGet) | **GET** v1.0/notifications/settings | 
[**v10NotificationsSettingsProgramByIdAddPost**](NotificationsApi.md#v10NotificationsSettingsProgramByIdAddPost) | **POST** v1.0/notifications/settings/program/{id}/add | 
[**v10NotificationsSettingsProgramByIdGet**](NotificationsApi.md#v10NotificationsSettingsProgramByIdGet) | **GET** v1.0/notifications/settings/program/{id} | 
[**v10NotificationsSettingsProgramByIdRemoveByNtfIdPost**](NotificationsApi.md#v10NotificationsSettingsProgramByIdRemoveByNtfIdPost) | **POST** v1.0/notifications/settings/program/{id}/remove/{ntfId} | 
[**v10NotificationsSettingsUpdatePost**](NotificationsApi.md#v10NotificationsSettingsUpdatePost) | **POST** v1.0/notifications/settings/update | 


<a name="v10NotificationsGet"></a>
# **v10NotificationsGet**
> Void v10NotificationsGet(authorization, skip, take)



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
    Void result = apiInstance.v10NotificationsGet(authorization, skip, take);
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

[**Void**](.md)

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

<a name="v10NotificationsSettingsGet"></a>
# **v10NotificationsSettingsGet**
> Void v10NotificationsSettingsGet(authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10NotificationsSettingsGet(authorization);
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

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10NotificationsSettingsProgramByIdAddPost"></a>
# **v10NotificationsSettingsProgramByIdAddPost**
> Void v10NotificationsSettingsProgramByIdAddPost(id, authorization, model)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
Map<String, String> model = new HashMap(); // Map<String, String> | 
try {
    Void result = apiInstance.v10NotificationsSettingsProgramByIdAddPost(id, authorization, model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsSettingsProgramByIdAddPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |
 **model** | [**Map&lt;String, String&gt;**](String.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10NotificationsSettingsProgramByIdGet"></a>
# **v10NotificationsSettingsProgramByIdGet**
> Void v10NotificationsSettingsProgramByIdGet(id, authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
UUID id = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10NotificationsSettingsProgramByIdGet(id, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsSettingsProgramByIdGet");
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

<a name="v10NotificationsSettingsProgramByIdRemoveByNtfIdPost"></a>
# **v10NotificationsSettingsProgramByIdRemoveByNtfIdPost**
> Void v10NotificationsSettingsProgramByIdRemoveByNtfIdPost(id, ntfId, authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
UUID id = new UUID(); // UUID | 
UUID ntfId = new UUID(); // UUID | 
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10NotificationsSettingsProgramByIdRemoveByNtfIdPost(id, ntfId, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsSettingsProgramByIdRemoveByNtfIdPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **ntfId** | [**UUID**](.md)|  |
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="v10NotificationsSettingsUpdatePost"></a>
# **v10NotificationsSettingsUpdatePost**
> Void v10NotificationsSettingsUpdatePost(authorization)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationsApi;


NotificationsApi apiInstance = new NotificationsApi();
String authorization = "authorization_example"; // String | JWT access token
try {
    Void result = apiInstance.v10NotificationsSettingsUpdatePost(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#v10NotificationsSettingsUpdatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

