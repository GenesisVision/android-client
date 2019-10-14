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
		return notificationsApi.getNotifications(AuthManager.token.getValue(), skip, take);
	}

	public Observable<NotificationSettingList> getNotificationsSettings() {
		return notificationsApi.getNotificationsSettings(AuthManager.token.getValue());
	}

	public Observable<UUID> addNotificationSetting(UUID assetId, UUID managerId, String settingType, String conditionType, Double conditionAmount) {
		return notificationsApi.addNotificationsSettings(AuthManager.token.getValue(), assetId, managerId, settingType, conditionType, conditionAmount);
	}

	public Observable<Void> removeNotificationSetting(UUID settingId) {
		return notificationsApi.removeNotificationsSettings(settingId, AuthManager.token.getValue());
	}

	public Observable<ProgramNotificationSettingList> getProgramNotificationsSettings(UUID programId) {
		return notificationsApi.getNotificationsProgramSettings(programId.toString(), AuthManager.token.getValue());
	}

	public Observable<FundNotificationSettingList> getFundNotificationsSettings(UUID fundId) {
		return notificationsApi.getNotificationsFundSettings(fundId.toString(), AuthManager.token.getValue());
	}

	public Observable<UUID> setEnabledNotificationSetting(UUID settingId, Boolean enabled) {
		return notificationsApi.toggleNotificationSettings(settingId, enabled, AuthManager.token.getValue());
	}
}