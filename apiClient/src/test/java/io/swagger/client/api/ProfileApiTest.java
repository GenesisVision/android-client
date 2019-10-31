package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import io.swagger.client.ApiClient;
import io.swagger.client.model.ExternalKeyAddViewModel;
import io.swagger.client.model.FcmTokenViewModel;
import io.swagger.client.model.IdModel;
import io.swagger.client.model.UpdatePersonalDetailViewModel;
import io.swagger.client.model.UpdateProfileViewModel;
import io.swagger.client.model.UpdateSocialLinkViewModel;

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
	 * Add external exchange key
	 */
	@Test
	public void addExternalKeyTest() {
		String authorization = null;
		ExternalKeyAddViewModel body = null;
		// Void response = api.addExternalKey(authorization, body);

		// TODO: test validations
	}

	/**
	 * Delete external exchange key
	 */
	@Test
	public void deleteExternalKeyTest() {
		String authorization = null;
		IdModel body = null;
		// Void response = api.deleteExternalKey(authorization, body);

		// TODO: test validations
	}

	/**
	 * Get external exchange keys
	 */
	@Test
	public void getExternalKeyTest() {
		String authorization = null;
		// ExternalKeysViewModel response = api.getExternalKey(authorization);

		// TODO: test validations
	}

	/**
	 * Public profile
	 */
	@Test
	public void getManagerProfileTest() {
		String id = null;
		// PublicProfile response = api.getManagerProfile(id);

		// TODO: test validations
	}

	/**
	 * Get full profile
	 */
	@Test
	public void getProfileFullTest() {
		String authorization = null;
		// ProfileFullViewModel response = api.getProfileFull(authorization);

		// TODO: test validations
	}

	/**
	 * Get header profile
	 */
	@Test
	public void getProfileHeaderTest() {
		String authorization = null;
		// ProfileHeaderViewModel response = api.getProfileHeader(authorization);

		// TODO: test validations
	}

	/**
	 * Get social links
	 */
	@Test
	public void getSocialLinksTest() {
		String authorization = null;
		// SocialLinksViewModel response = api.getSocialLinks(authorization);

		// TODO: test validations
	}

	/**
	 *
	 */
	@Test
	public void getVerificationTokenTest() {
		String authorization = null;
		// String response = api.getVerificationToken(authorization);

		// TODO: test validations
	}

	/**
	 * Remove avatar
	 */
	@Test
	public void removeAvatarTest() {
		String authorization = null;
		// Void response = api.removeAvatar(authorization);

		// TODO: test validations
	}

	/**
	 * Update avatar
	 */
	@Test
	public void updateAvatarTest() {
		String fileId = null;
		String authorization = null;
		// Void response = api.updateAvatar(fileId, authorization);

		// TODO: test validations
	}

	/**
	 *
	 */
	@Test
	public void updateFcmTokenTest() {
		String authorization = null;
		FcmTokenViewModel body = null;
		// Void response = api.updateFcmToken(authorization, body);

		// TODO: test validations
	}

	/**
	 * Update user personal details
	 */
	@Test
	public void updatePersonalDetailsTest() {
		String authorization = null;
		UpdatePersonalDetailViewModel body = null;
		// Void response = api.updatePersonalDetails(authorization, body);

		// TODO: test validations
	}

	/**
	 * Update profile
	 */
	@Test
	public void updateProfileTest() {
		String authorization = null;
		UpdateProfileViewModel body = null;
		// Void response = api.updateProfile(authorization, body);

		// TODO: test validations
	}

	/**
	 * Add or update social links
	 */
	@Test
	public void updateSocialLinksTest() {
		String authorization = null;
		UpdateSocialLinkViewModel body = null;
		// Void response = api.updateSocialLinks(authorization, body);

		// TODO: test validations
	}
}
