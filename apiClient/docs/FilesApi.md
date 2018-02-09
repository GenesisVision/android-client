# FilesApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiFilesGetGet**](FilesApi.md#apiFilesGetGet) | **GET** api/files/get | Download file
[**apiFilesUploadPost**](FilesApi.md#apiFilesUploadPost) | **POST** api/files/upload | Upload file


<a name="apiFilesGetGet"></a>
# **apiFilesGetGet**
> Void apiFilesGetGet(fileName)

Download file

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FilesApi;


FilesApi apiInstance = new FilesApi();
String fileName = "fileName_example"; // String | 
try {
    Void result = apiInstance.apiFilesGetGet(fileName);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FilesApi#apiFilesGetGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **fileName** | **String**|  | [optional]

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

