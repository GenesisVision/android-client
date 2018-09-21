package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.UploadResult;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface FileApi
{
	/**
	 * Download file
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@GET("v1.0/file/{id}")
	Observable<Void> v10FileByIdGet(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Upload file
	 *
	 * @param uploadedFile  Upload File (required)
	 * @param authorization (optional)
	 * @return Call&lt;UploadResult&gt;
	 */
	@retrofit2.http.Multipart
	@POST("v1.0/file/upload")
	Observable<UploadResult> v10FileUploadPost(
			@retrofit2.http.Part("uploadedFile\"; filename=\"uploadedFile") RequestBody uploadedFile, @retrofit2.http.Header("Authorization") String authorization
	);

}
