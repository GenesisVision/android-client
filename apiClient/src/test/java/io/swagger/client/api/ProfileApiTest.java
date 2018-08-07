package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.UpdateProfileViewModel;

/**
 * API tests for ProfileApi
 */
public class ProfileApiTest
{

	private ProfileApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(ProfileApi.class);
	}

	/**
	 * Get public profile
	 */
	@Test
	public void v10ProfileByIdPublicGetTest() {
		UUID id = null;
		// ManagerProfile response = api.v10ProfileByIdPublicGet(id);

		// TODO: test validations
	}

	/**
	 * Get full profile
	 */
	@Test
	public void v10ProfileGetTest() {
		String authorization = null;
		// ProfileFullViewModel response = api.v10ProfileGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get header profile
	 */
	@Test
	public void v10ProfileHeaderGetTest() {
		String authorization = null;
		// ProfileHeaderViewModel response = api.v10ProfileHeaderGet(authorization);

		// TODO: test validations
	}

	/**
	 * Update profile
	 */
	@Test
	public void v10ProfileUpdatePostTest() {
		String authorization = null;
		UpdateProfileViewModel model = null;
		// Void response = api.v10ProfileUpdatePost(authorization, model);

		// TODO: test validations
	}
}
