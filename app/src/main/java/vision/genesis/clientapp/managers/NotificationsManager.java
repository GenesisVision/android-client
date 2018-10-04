package vision.genesis.clientapp.managers;


import io.swagger.client.api.NotificationsApi;
import io.swagger.client.model.NotificationList;
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
}