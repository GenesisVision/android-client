package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.FundNotificationSettingList;
import io.swagger.client.model.ManagerNotificationSettingList;
import io.swagger.client.model.NotificationList;
import io.swagger.client.model.NotificationSettingList;
import io.swagger.client.model.ProgramNotificationSettingList;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface NotificationsApi
{
	/**
	 * Add new setting
	 *
	 * @param authorization   JWT access token (required)
	 * @param assetId         (optional)
	 * @param managerId       (optional)
	 * @param type            (optional)
	 * @param conditionType   (optional)
	 * @param conditionAmount (optional)
	 * @return Call&lt;UUID&gt;
	 */
	@POST("v2.0/notifications/settings/add")
	Observable<UUID> addNotificationsSettings(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("ManagerId") UUID managerId, @retrofit2.http.Query("Type") String type, @retrofit2.http.Query("ConditionType") String conditionType, @retrofit2.http.Query("ConditionAmount") Double conditionAmount
	);

	/**
	 * Unread notifications count
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Integer&gt;
	 */
	@GET("v2.0/notifications/new")
	Observable<Integer> getNewNotificationsCount(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * User notifications
	 *
	 * @param authorization JWT access token (required)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;NotificationList&gt;
	 */
	@GET("v2.0/notifications")
	Observable<NotificationList> getNotifications(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("skip") Integer skip, @retrofit2.http.Query("take") Integer take
	);

	/**
	 * User settings for fund
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;FundNotificationSettingList&gt;
	 */
	@GET("v2.0/notifications/settings/funds/{id}")
	Observable<FundNotificationSettingList> getNotificationsFundSettings(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * User settings for manager
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ManagerNotificationSettingList&gt;
	 */
	@GET("v2.0/notifications/settings/managers/{id}")
	Observable<ManagerNotificationSettingList> getNotificationsManagerSettings(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * User settings for program
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramNotificationSettingList&gt;
	 */
	@GET("v2.0/notifications/settings/programs/{id}")
	Observable<ProgramNotificationSettingList> getNotificationsProgramSettings(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * User settings
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;NotificationSettingList&gt;
	 */
	@GET("v2.0/notifications/settings")
	Observable<NotificationSettingList> getNotificationsSettings(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Read all notification
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/notifications/all/read")
	Observable<Void> readAllNotification(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Read notification
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/notifications/{id}/read")
	Observable<Void> readNotification(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Remove setting
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/notifications/settings/remove/{id}")
	Observable<Void> removeNotificationsSettings(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Enable/disable setting
	 *
	 * @param id            (required)
	 * @param enable        (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;UUID&gt;
	 */
	@POST("v2.0/notifications/settings/{id}/{enable}")
	Observable<UUID> toggleNotificationSettings(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("enable") Boolean enable, @retrofit2.http.Header("Authorization") String authorization
	);

}
