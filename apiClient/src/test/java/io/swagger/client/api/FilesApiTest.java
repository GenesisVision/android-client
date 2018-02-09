package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

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
	public void apiFilesGetGetTest() {
		String fileName = null;
		// Void response = api.apiFilesGetGet(fileName);

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
