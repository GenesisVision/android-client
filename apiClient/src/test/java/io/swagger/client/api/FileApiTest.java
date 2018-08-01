package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
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
	public void v10FileByIdGetTest() {
		UUID id = null;
		// Void response = api.v10FileByIdGet(id);

		// TODO: test validations
	}

	/**
	 * Upload file
	 */
	@Test
	public void v10FileUploadPostTest() {
		String contentType = null;
		String contentDisposition = null;
		Map<String, String> headers = null;
		Long length = null;
		String name = null;
		String fileName = null;
		// UploadResult response = api.v10FileUploadPost(contentType, contentDisposition, headers, length, name, fileName);

		// TODO: test validations
	}
}
