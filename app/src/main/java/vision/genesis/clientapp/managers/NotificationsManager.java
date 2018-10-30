package vision.genesis.clientapp.managers;


import java.util.UUID;

import io.swagger.client.api.NotificationsApi;
import io.swagger.client.model.FundNotificationSettingList;
import io.swagger.client.model.NotificationList;
import io.swagger.client.model.NotificationSettingList;
import io.swagger.client.model.ProgramNotificationSettingList;
import rx.Observable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/10/2018.
 */

public class NotificationsManager
{
	private NotificationsApi notificationsApi;

	public NotificationsManager(NotificationsApi notificationsApi) {
		this.notificationsApi = notificationsApi;
	}

	public Observable<NotificationList> getNotifications(Integer skip, Integer take) {
		return notificationsApi.v10NotificationsGet(AuthManager.token.getValue(), skip, take);
	}

	public Observable<NotificationSettingList> getNotificationsSettings() {
		return notificationsApi.v10NotificationsSettingsGet(AuthManager.token.getValue());
	}

	public Observable<UUID> addNotificationSetting(UUID assetId, UUID managerId, String settingType, String conditionType, Double conditionAmount) {
		return notificationsApi.v10NotificationsSettingsAddPost(AuthManager.token.getValue(), assetId, managerId, settingType, conditionType, conditionAmount);
	}

	public Observable<Void> removeNotificationSetting(UUID settingId) {
		return notificationsApi.v10NotificationsSettingsRemoveByIdPost(settingId, AuthManager.token.getValue());
	}

	public Observable<ProgramNotificationSettingList> getProgramNotificationsSettings(UUID programId) {
		return notificationsApi.v10NotificationsSettingsProgramsByIdGet(programId.toString(), AuthManager.token.getValue());
	}

	public Observable<FundNotificationSettingList> getFundNotificationsSettings(UUID fundId) {
		return notificationsApi.v10NotificationsSettingsFundsByIdGet(fundId.toString(), AuthManager.token.getValue());
	}

	public Observable<UUID> setEnabledNotificationSetting(UUID settingId, Boolean enabled) {
		return notificationsApi.v10NotificationsSettingsByIdByEnablePost(settingId, enabled, AuthManager.token.getValue());
	}
}