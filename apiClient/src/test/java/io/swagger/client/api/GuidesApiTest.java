package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;

/**
 * API tests for GuidesApi
 */
public class GuidesApiTest
{

	private GuidesApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(GuidesApi.class);
	}


	/**
	 * Get guides
	 */
	@Test
	public void getGuidesTest() {
		// GuidesCategoryItemsViewModel response = api.getGuides();

		// TODO: test validations
	}

	/**
	 * Pass guid
	 */
	@Test
	public void passGuideTest() {
		UUID id = null;
		// Void response = api.passGuide(id);

		// TODO: test validations
	}
}
