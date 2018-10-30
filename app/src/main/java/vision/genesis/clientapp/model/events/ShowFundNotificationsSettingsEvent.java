package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/10/2018.
 */
public class ShowFundNotificationsSettingsEvent
{
	private final UUID fundId;

	private final String fundName;

	public ShowFundNotificationsSettingsEvent(UUID fundId, String fundName) {
		this.fundId = fundId;
		this.fundName = fundName;
	}

	public UUID getFundId() {
		return fundId;
	}

	public String getFundName() {
		return fundName;
	}
}
