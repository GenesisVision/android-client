package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.FollowNotificationSettingList;
import io.swagger.client.model.FundNotificationSettingList;
import io.swagger.client.model.ManagerNotificationSettingList;
import io.swagger.client.model.NotificationSettingConditionType;
import io.swagger.client.model.NotificationSettingList;
import io.swagger.client.model.NotificationType;
import io.swagger.client.model.NotificationViewModelItemsViewModel;
import io.swagger.client.model.ProgramNotificationSettingList;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface NotificationsApi
{
	/**
	 * Add new setting
	 *
	 * @param assetId         (optional)
	 * @param managerId       (optional)
	 * @param type            (optional)
	 * @param conditionType   (optional)
	 * @param conditionAmount (optional)
	 * @return Call&lt;UUID&gt;
	 */
	@POST("v2.0/notifications/settings/add")
	Observable<UUID> addNotificationsSettings(
			@retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("ManagerId") UUID managerId, @retrofit2.http.Query("Type") NotificationType type, @retrofit2.http.Query("ConditionType") NotificationSettingConditionType conditionType, @retrofit2.http.Query("ConditionAmount") Double conditionAmount
	);

	/**
	 * Unread notifications count
	 *
	 * @return Call&lt;Integer&gt;
	 */
	@GET("v2.0/notifications/new")
	Observable<Integer> getNewNotificationsCount();


	/**
	 * User notifications
	 *
	 * @param skip (optional)
	 * @param take (optional)
     * @return Call&lt;NotificationViewModelItemsViewModel&gt;
	 */
    @GET("v2.1/notifications")
    Observable<NotificationViewModelItemsViewModel> getNotifications(
            @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * User settings for follow
	 *
	 * @param id (required)
	 * @return Call&lt;FollowNotificationSettingList&gt;
	 */
	@GET("v2.0/notifications/settings/follow/{id}")
	Observable<FollowNotificationSettingList> getNotificationsFollowSettings(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * User settings for fund
	 *
	 * @param id (required)
	 * @return Call&lt;FundNotificationSettingList&gt;
	 */
	@GET("v2.0/notifications/settings/funds/{id}")
	Observable<FundNotificationSettingList> getNotificationsFundSettings(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * User settings for manager
	 *
	 * @param id (required)
	 * @return Call&lt;ManagerNotificationSettingList&gt;
	 */
	@GET("v2.0/notifications/settings/managers/{id}")
	Observable<ManagerNotificationSettingList> getNotificationsManagerSettings(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * User settings for program
	 *
	 * @param id (required)
	 * @return Call&lt;ProgramNotificationSettingList&gt;
	 */
	@GET("v2.0/notifications/settings/programs/{id}")
	Observable<ProgramNotificationSettingList> getNotificationsProgramSettings(
			@retrofit2.http.Path("id") String id
	);

	/**
	 * User settings
	 *
	 * @return Call&lt;NotificationSettingList&gt;
	 */
	@GET("v2.0/notifications/settings")
	Observable<NotificationSettingList> getNotificationsSettings();


	/**
	 * Read all notification
	 *
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/notifications/all/read")
	Observable<Void> readAllNotification();


	/**
	 * Read notification
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/notifications/{id}/read")
	Observable<Void> readNotification(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Remove setting
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/notifications/settings/remove/{id}")
	Observable<Void> removeNotificationsSettings(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Enable/disable setting
	 *
	 * @param id     (required)
	 * @param enable (required)
	 * @return Call&lt;UUID&gt;
	 */
	@POST("v2.0/notifications/settings/{id}/{enable}")
	Observable<UUID> toggleNotificationSettings(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("enable") Boolean enable
	);

}
