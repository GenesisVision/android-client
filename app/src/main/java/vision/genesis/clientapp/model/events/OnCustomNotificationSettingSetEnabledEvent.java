package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/10/2018.
 */
public class OnCustomNotificationSettingSetEnabledEvent
{
	private final UUID settingId;

	private final Boolean enabled;

	public OnCustomNotificationSettingSetEnabledEvent(UUID settingId, Boolean enabled) {
		this.settingId = settingId;
		this.enabled = enabled;
	}

	public UUID getSettingId() {
		return settingId;
	}

	public Boolean isEnabled() {
		return enabled;
	}
}
