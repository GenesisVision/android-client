package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */
public class OnSelectSubscriptionTradingAccountConfirmEvent
{
	private final UUID tradingAccountId;

	public OnSelectSubscriptionTradingAccountConfirmEvent(UUID tradingAccountId) {
		this.tradingAccountId = tradingAccountId;
	}

	public UUID getTradingAccountId() {
		return tradingAccountId;
	}
}
