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
	 * User notifications
	 *
	 * @param authorization JWT access token (required)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;NotificationList&gt;
	 */
	@GET("v1.0/notifications")
	Observable<NotificationList> v10NotificationsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("skip") Integer skip, @retrofit2.http.Query("take") Integer take
	);

	/**
	 * Unread notifications count
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Integer&gt;
	 */
	@GET("v1.0/notifications/new")
	Observable<Integer> v10NotificationsNewGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

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
	@POST("v1.0/notifications/settings/add")
	Observable<UUID> v10NotificationsSettingsAddPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("ManagerId") UUID managerId, @retrofit2.http.Query("Type") String type, @retrofit2.http.Query("ConditionType") String conditionType, @retrofit2.http.Query("ConditionAmount") Double conditionAmount
	);

	/**
	 * Enable/disable setting
	 *
	 * @param id            (required)
	 * @param enable        (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;UUID&gt;
	 */
	@POST("v1.0/notifications/settings/{id}/{enable}")
	Observable<UUID> v10NotificationsSettingsByIdByEnablePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("enable") Boolean enable, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * User settings for fund
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;FundNotificationSettingList&gt;
	 */
	@GET("v1.0/notifications/settings/funds/{id}")
	Observable<FundNotificationSettingList> v10NotificationsSettingsFundsByIdGet(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * User settings
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;NotificationSettingList&gt;
	 */
	@GET("v1.0/notifications/settings")
	Observable<NotificationSettingList> v10NotificationsSettingsGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * User settings for manager
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ManagerNotificationSettingList&gt;
	 */
	@GET("v1.0/notifications/settings/managers/{id}")
	Observable<ManagerNotificationSettingList> v10NotificationsSettingsManagersByIdGet(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * User settings for program
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramNotificationSettingList&gt;
	 */
	@GET("v1.0/notifications/settings/programs/{id}")
	Observable<ProgramNotificationSettingList> v10NotificationsSettingsProgramsByIdGet(
			@retrofit2.http.Path("id") String id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Remove setting
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/notifications/settings/remove/{id}")
	Observable<Void> v10NotificationsSettingsRemoveByIdPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

}
