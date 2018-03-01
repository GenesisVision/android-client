# FilesApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiFilesGetByIdGet**](FilesApi.md#apiFilesGetByIdGet) | **GET** api/files/get/{id} | Download file
[**apiFilesGetGet**](FilesApi.md#apiFilesGetGet) | **GET** api/files/get | Download file
[**apiFilesUploadPost**](FilesApi.md#apiFilesUploadPost) | **POST** api/files/upload | Upload file


<a name="apiFilesGetByIdGet"></a>
# **apiFilesGetByIdGet**
> Void apiFilesGetByIdGet(id)

Download file

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FilesApi;


FilesApi apiInstance = new FilesApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.apiFilesGetByIdGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FilesApi#apiFilesGetByIdGet");
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

<a name="apiFilesGetGet"></a>
# **apiFilesGetGet**
> Void apiFilesGetGet(id)

Download file

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FilesApi;


FilesApi apiInstance = new FilesApi();
UUID id = new UUID(); // UUID | 
try {
    Void result = apiInstance.apiFilesGetGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FilesApi#apiFilesGetGet");
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

