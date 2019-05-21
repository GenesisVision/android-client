package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/05/2019.
 */
public class OnTransactionCanceledEvent
{
	private UUID transactionId;

	public OnTransactionCanceledEvent(UUID transactionId) {
		this.transactionId = transactionId;
	}

	public UUID getTransactionId() {
		return transactionId;
	}
}
