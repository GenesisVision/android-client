# FileApi

All URIs are relative to *https://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v10FileByIdGet**](FileApi.md#v10FileByIdGet) | **GET** v1.0/file/{id} | Download file
[**v10FileDocumentUploadPost**](FileApi.md#v10FileDocumentUploadPost) | **POST** v1.0/file/document/upload | Upload document
[**v10FileUploadPost**](FileApi.md#v10FileUploadPost) | **POST** v1.0/file/upload | Upload file


<a name="v10FileByIdGet"></a>
# **v10FileByIdGet**
> Void v10FileByIdGet(id)

Download file

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FileApi;


FileApi apiInstance = new FileApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.v10FileByIdGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FileApi#v10FileByIdGet");
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

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="v10FileDocumentUploadPost"></a>
# **v10FileDocumentUploadPost**
> UploadResult v10FileDocumentUploadPost(authorization, uploadedFile)

Upload document

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FileApi;


FileApi apiInstance = new FileApi();
String authorization = "authorization_example"; // String | JWT access token
File uploadedFile = new File("/path/to/file.txt"); // File | Upload File
try {
    UploadResult result = apiInstance.v10FileDocumentUploadPost(authorization, uploadedFile);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FileApi#v10FileDocumentUploadPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| JWT access token |
 **uploadedFile** | **File**| Upload File |

### Return type

[**UploadResult**](UploadResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: text/plain, application/json, text/json

<a name="v10FileUploadPost"></a>
# **v10FileUploadPost**
> UploadResult v10FileUploadPost(uploadedFile, authorization)

Upload file

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FileApi;


FileApi apiInstance = new FileApi();
File uploadedFile = new File("/path/to/file.txt"); // File | Upload File
String authorization = "authorization_example"; // String | 
try {
    UploadResult result = apiInstance.v10FileUploadPost(uploadedFile, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FileApi#v10FileUploadPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uploadedFile** | **File**| Upload File |
 **authorization** | **String**|  | [optional]

### Return type

[**UploadResult**](UploadResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: text/plain, application/json, text/json

