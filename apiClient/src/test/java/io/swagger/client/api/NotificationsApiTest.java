package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.UUID;

import io.swagger.client.ApiClient;

/**
 * API tests for NotificationsApi
 */
public class NotificationsApiTest
{

	private NotificationsApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(NotificationsApi.class);
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void v10NotificationsGetTest() {
		String authorization = null;
		Integer skip = null;
		Integer take = null;
		// Void response = api.v10NotificationsGet(authorization, skip, take);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void v10NotificationsNewGetTest() {
		String authorization = null;
		// Integer response = api.v10NotificationsNewGet(authorization);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void v10NotificationsSettingsGetTest() {
		String authorization = null;
		// Void response = api.v10NotificationsSettingsGet(authorization);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void v10NotificationsSettingsProgramByIdAddPostTest() {
		UUID id = null;
		String authorization = null;
		Map<String, String> model = null;
		// Void response = api.v10NotificationsSettingsProgramByIdAddPost(id, authorization, model);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void v10NotificationsSettingsProgramByIdGetTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.v10NotificationsSettingsProgramByIdGet(id, authorization);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void v10NotificationsSettingsProgramByIdRemoveByNtfIdPostTest() {
		UUID id = null;
		UUID ntfId = null;
		String authorization = null;
		// Void response = api.v10NotificationsSettingsProgramByIdRemoveByNtfIdPost(id, ntfId, authorization);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void v10NotificationsSettingsUpdatePostTest() {
		String authorization = null;
		// Void response = api.v10NotificationsSettingsUpdatePost(authorization);

		// TODO: test validations
	}
}
