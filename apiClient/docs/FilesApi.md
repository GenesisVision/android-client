# FilesApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiFilesByIdGet**](FilesApi.md#apiFilesByIdGet) | **GET** api/files/{id} | Download file
[**apiFilesGet**](FilesApi.md#apiFilesGet) | **GET** api/files | Download file
[**apiFilesUploadPost**](FilesApi.md#apiFilesUploadPost) | **POST** api/files/upload | Upload file


<a name="apiFilesByIdGet"></a>
# **apiFilesByIdGet**
> Void apiFilesByIdGet(id)

Download file

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FilesApi;


FilesApi apiInstance = new FilesApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.apiFilesByIdGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FilesApi#apiFilesByIdGet");
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

<a name="apiFilesGet"></a>
# **apiFilesGet**
> Void apiFilesGet(id)

Download file

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FilesApi;


FilesApi apiInstance = new FilesApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.apiFilesGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FilesApi#apiFilesGet");
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

<a name="apiFilesUploadPost"></a>
# **apiFilesUploadPost**
> UploadResult apiFilesUploadPost(uploadedFile)

Upload file

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FilesApi;


FilesApi apiInstance = new FilesApi();
File uploadedFile = new File("/path/to/file.txt"); // File | Upload File
try {
    UploadResult result = apiInstance.apiFilesUploadPost(uploadedFile);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FilesApi#apiFilesUploadPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uploadedFile** | **File**| Upload File |

### Return type

[**UploadResult**](UploadResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: text/plain, application/json, text/json

