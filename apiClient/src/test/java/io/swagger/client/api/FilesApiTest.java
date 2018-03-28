package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

import io.swagger.client.ApiClient;

/**
 * API tests for FilesApi
 */
public class FilesApiTest
{

	private FilesApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(FilesApi.class);
	}

	/**
	 * Download file
	 */
	@Test
	public void apiFilesByIdGetTest() {
		UUID id = null;
		// Void response = api.apiFilesByIdGet(id);

		// TODO: test validations
	}

	/**
	 * Download file
	 */
	@Test
	public void apiFilesGetTest() {
		UUID id = null;
		// Void response = api.apiFilesGet(id);

		// TODO: test validations
	}

	/**
	 * Upload file
	 */
	@Test
	public void apiFilesUploadPostTest() {
		File uploadedFile = null;
		// UploadResult response = api.apiFilesUploadPost(uploadedFile);

		// TODO: test validations
	}
}
