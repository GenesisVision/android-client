package vision.genesis.clientapp.model.events;

import io.swagger.client.model.NotificationLocationViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/01/2021.
 */
public class OnNotificationClickedEvent
{
	private NotificationLocationViewModel location;

	public OnNotificationClickedEvent(NotificationLocationViewModel location) {
		this.location = location;
	}

	public NotificationLocationViewModel getLocation() {
		return location;
	}
}
