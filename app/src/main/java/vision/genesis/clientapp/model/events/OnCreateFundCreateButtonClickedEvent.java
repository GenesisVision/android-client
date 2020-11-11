package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */
public class OnCreateFundCreateButtonClickedEvent
{
	private final double amount;

	private final UUID walletId;

	public OnCreateFundCreateButtonClickedEvent(double amount, UUID walletId) {

		this.amount = amount;
		this.walletId = walletId;
	}

	public double getAmount() {
		return amount;
	}

	public UUID getWalletId() {
		return walletId;
	}
}
