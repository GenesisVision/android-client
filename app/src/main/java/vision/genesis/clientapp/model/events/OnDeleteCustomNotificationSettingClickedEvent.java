package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/10/2018.
 */
public class OnDeleteCustomNotificationSettingClickedEvent
{
	private final UUID settingId;

	public OnDeleteCustomNotificationSettingClickedEvent(UUID settingId) {
		this.settingId = settingId;
	}

	public UUID getSettingId() {
		return settingId;
	}
}
