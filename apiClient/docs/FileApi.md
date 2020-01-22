# FileApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getFile**](FileApi.md#getFile) | **GET** v2.0/file/{id} | Download file
[**uploadFile**](FileApi.md#uploadFile) | **POST** v2.0/file/upload | Upload file

<a name="getFile"></a>
# **getFile**
> Void getFile(id, quality)

Download file

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FileApi;


FileApi apiInstance = new FileApi();
UUID id = new UUID(); // UUID | 
String quality = "quality_example"; // String | 
try {
    Void result = apiInstance.getFile(id, quality);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FileApi#getFile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **quality** | **String**|  | [optional] [enum: Low, Medium, High]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="uploadFile"></a>
# **uploadFile**
> UploadResult uploadFile(uploadedFile, authorization)

Upload file

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.FileApi;


FileApi apiInstance = new FileApi();
File uploadedFile = new File("uploadedFile_example"); // File | 
String authorization = "authorization_example"; // String | 
try {
    UploadResult result = apiInstance.uploadFile(uploadedFile, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FileApi#uploadFile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uploadedFile** | **File**|  |
 **authorization** | **String**|  | [optional]

### Return type

[**UploadResult**](UploadResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: text/plain, application/json, text/json

