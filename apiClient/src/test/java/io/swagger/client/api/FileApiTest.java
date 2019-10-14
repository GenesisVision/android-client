package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

import io.swagger.client.ApiClient;

/**
 * API tests for FileApi
 */
public class FileApiTest
{

	private FileApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(FileApi.class);
	}


	/**
	 * Download file
	 */
	@Test
	public void getFileTest() {
		UUID id = null;
		// Void response = api.getFile(id);

		// TODO: test validations
	}

	/**
	 * Upload file
	 */
	@Test
	public void uploadFileTest() {
		File uploadedFile = null;
		String authorization = null;
		// UploadResult response = api.uploadFile(uploadedFile, authorization);

		// TODO: test validations
	}
}
