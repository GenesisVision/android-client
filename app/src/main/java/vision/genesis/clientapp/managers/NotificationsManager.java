package vision.genesis.clientapp.managers;


import java.util.UUID;

import io.swagger.client.api.NotificationsApi;
import io.swagger.client.model.FundNotificationSettingList;
import io.swagger.client.model.NotificationList;
import io.swagger.client.model.NotificationSettingConditionType;
import io.swagger.client.model.NotificationSettingList;
import io.swagger.client.model.NotificationType;
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
		return notificationsApi.getNotifications(skip, take);
	}

	public Observable<NotificationSettingList> getNotificationsSettings() {
		return notificationsApi.getNotificationsSettings();
	}

	public Observable<UUID> addNotificationSetting(UUID assetId, UUID managerId, String settingType, String conditionType, Double conditionAmount) {
		return notificationsApi.addNotificationsSettings(assetId, managerId, NotificationType.fromValue(settingType), NotificationSettingConditionType.fromValue(conditionType), conditionAmount);
	}

	public Observable<Void> removeNotificationSetting(UUID settingId) {
		return notificationsApi.removeNotificationsSettings(settingId);
	}

	public Observable<ProgramNotificationSettingList> getProgramNotificationsSettings(UUID programId) {
		return notificationsApi.getNotificationsProgramSettings(programId.toString());
	}

	public Observable<FundNotificationSettingList> getFundNotificationsSettings(UUID fundId) {
		return notificationsApi.getNotificationsFundSettings(fundId.toString());
	}

	public Observable<UUID> setEnabledNotificationSetting(UUID settingId, Boolean enabled) {
		return notificationsApi.toggleNotificationSettings(settingId, enabled);
	}
}