package io.swagger.client.api;

import io.swagger.client.model.UploadResult;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface FilesApi
{
	/**
	 * Download file
	 *
	 * @param id (optional)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/files/get")
	Observable<Void> apiFilesGetGet(
			@retrofit2.http.Query("id") String id
	);

	/**
	 * Upload file
	 *
	 * @param uploadedFile (optional)
	 * @return Call&lt;UploadResult&gt;
	 */
	@POST("api/files/upload")
	Observable<UploadResult> apiFilesUploadPost(
			@retrofit2.http.Query("uploadedFile") String uploadedFile
	);

}
