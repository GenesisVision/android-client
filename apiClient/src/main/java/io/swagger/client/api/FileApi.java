package io.swagger.client.api;//retrofit2

import io.swagger.client.model.ImageLocation;
import io.swagger.client.model.UploadResult;
import okhttp3.RequestBody;
import retrofit2.http.POST;
import rx.Observable;

public interface FileApi {
  /**
   * Upload file
   * 
   * @param uploadedFile  (optional)
   * @param location  (optional)
   * @param waitForResize  (optional, default to false)
   * @return Call&lt;UploadResult&gt;
   */
  @retrofit2.http.Multipart
  @POST("v2.0/file/upload")
  Observable<UploadResult> uploadFile(
                        @retrofit2.http.Part("uploadedFile\"; filename=\"uploadedFile") RequestBody uploadedFile,     @retrofit2.http.Query("location") ImageLocation location                ,     @retrofit2.http.Query("waitForResize") Boolean waitForResize                
  );

}
