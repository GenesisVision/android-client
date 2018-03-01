package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.UploadResult;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface FilesApi
{
	/**
	 * Download file
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/files/get/{id}")
	Observable<Void> apiFilesGetByIdGet(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Download file
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/files/get")
	Observable<Void> apiFilesGetGet(
			@retrofit2.http.Query("id") UUID id
	);

	/**
	 * Upload file
	 *
	 * @param uploadedFile Upload File (required)
	 * @return Call&lt;UploadResult&gt;
	 */
	@retrofit2.http.Multipart
	@POST("api/files/upload")
	Observable<UploadResult> apiFilesUploadPost(
			@retrofit2.http.Part("uploadedFile\"; filename=\"uploadedFile") RequestBody uploadedFile
	);

}
