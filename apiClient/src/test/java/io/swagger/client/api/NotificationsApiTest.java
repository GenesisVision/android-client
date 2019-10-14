package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

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
	 * Add new setting
	 */
	@Test
	public void addNotificationsSettingsTest() {
		String authorization = null;
		UUID assetId = null;
		UUID managerId = null;
		String type = null;
		String conditionType = null;
		Double conditionAmount = null;
		// UUID response = api.addNotificationsSettings(authorization, assetId, managerId, type, conditionType, conditionAmount);

		// TODO: test validations
	}

	/**
	 * Unread notifications count
	 */
	@Test
	public void getNewNotificationsCountTest() {
		String authorization = null;
		// Integer response = api.getNewNotificationsCount(authorization);

		// TODO: test validations
	}

	/**
	 * User notifications
	 */
	@Test
	public void getNotificationsTest() {
		String authorization = null;
		Integer skip = null;
		Integer take = null;
		// NotificationList response = api.getNotifications(authorization, skip, take);

		// TODO: test validations
	}

	/**
	 * User settings for fund
	 */
	@Test
	public void getNotificationsFundSettingsTest() {
		String id = null;
		String authorization = null;
		// FundNotificationSettingList response = api.getNotificationsFundSettings(id, authorization);

		// TODO: test validations
	}

	/**
	 * User settings for manager
	 */
	@Test
	public void getNotificationsManagerSettingsTest() {
		String id = null;
		String authorization = null;
		// ManagerNotificationSettingList response = api.getNotificationsManagerSettings(id, authorization);

		// TODO: test validations
	}

	/**
	 * User settings for program
	 */
	@Test
	public void getNotificationsProgramSettingsTest() {
		String id = null;
		String authorization = null;
		// ProgramNotificationSettingList response = api.getNotificationsProgramSettings(id, authorization);

		// TODO: test validations
	}

	/**
	 * User settings
	 */
	@Test
	public void getNotificationsSettingsTest() {
		String authorization = null;
		// NotificationSettingList response = api.getNotificationsSettings(authorization);

		// TODO: test validations
	}

	/**
	 * Read notification
	 */
	@Test
	public void readNotificationTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.readNotification(id, authorization);

		// TODO: test validations
	}

	/**
	 * Remove setting
	 */
	@Test
	public void removeNotificationsSettingsTest() {
		UUID id = null;
		String authorization = null;
		// Void response = api.removeNotificationsSettings(id, authorization);

		// TODO: test validations
	}

	/**
	 * Enable/disable setting
	 */
	@Test
	public void toggleNotificationSettingsTest() {
		UUID id = null;
		Boolean enable = null;
		String authorization = null;
		// UUID response = api.toggleNotificationSettings(id, enable, authorization);

		// TODO: test validations
	}
}
