package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/11/2020.
 */
public class OnCreateAccountSuccessEvent
{
	private final UUID accountId;

	public OnCreateAccountSuccessEvent(UUID accountId) {
		this.accountId = accountId;
	}

	public UUID getAccountId() {
		return accountId;
	}
}
