# FileApi

All URIs are relative to *https://red.genesis.vision/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**uploadFile**](FileApi.md#uploadFile) | **POST** v2.0/file/upload | Upload file

<a name="uploadFile"></a>
# **uploadFile**
> UploadResult uploadFile(uploadedFile, location, waitForResize)

Upload file

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FileApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: Bearer
ApiKeyAuth Bearer = (ApiKeyAuth) defaultClient.getAuthentication("Bearer");
Bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//Bearer.setApiKeyPrefix("Token");

FileApi apiInstance = new FileApi();
File uploadedFile = new File("uploadedFile_example"); // File | 
ImageLocation location = new ImageLocation(); // ImageLocation | 
Boolean waitForResize = true; // Boolean | 
try {
    UploadResult result = apiInstance.uploadFile(uploadedFile, location, waitForResize);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FileApi#uploadFile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uploadedFile** | **File**|  | [optional]
 **location** | [**ImageLocation**](.md)|  | [optional]
 **waitForResize** | **Boolean**|  | [optional]

### Return type

[**UploadResult**](UploadResult.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: text/plain, application/json, text/json

