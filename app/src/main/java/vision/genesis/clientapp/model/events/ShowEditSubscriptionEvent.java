package vision.genesis.clientapp.model.events;

import java.util.UUID;

import vision.genesis.clientapp.model.SubscriptionSettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 29/06/2020.
 */
public class ShowEditSubscriptionEvent
{
	private final SubscriptionSettingsModel model;

	private final UUID followId;

	private final UUID tradingAccountId;

	private final Boolean isExternal;

	public ShowEditSubscriptionEvent(SubscriptionSettingsModel model, UUID followId, UUID tradingAccountId, Boolean isExternal) {
		this.model = model;
		this.followId = followId;
		this.tradingAccountId = tradingAccountId;
		this.isExternal = isExternal;
	}

	public SubscriptionSettingsModel getModel() {
		return model;
	}

	public UUID getFollowId() {
		return followId;
	}

	public UUID getTradingAccountId() {
		return tradingAccountId;
	}

	public Boolean getExternal() {
		return isExternal;
	}
}
