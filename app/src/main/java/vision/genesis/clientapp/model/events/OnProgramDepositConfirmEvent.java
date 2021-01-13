package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/01/2021.
 */
public class OnProgramDepositConfirmEvent
{
	private final Double depositAmount;

	private final UUID depositWalletId;

	public OnProgramDepositConfirmEvent(Double depositAmount, UUID depositWalletId) {
		this.depositAmount = depositAmount;
		this.depositWalletId = depositWalletId;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public UUID getDepositWalletId() {
		return depositWalletId;
	}
}
