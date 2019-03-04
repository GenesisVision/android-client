package vision.genesis.clientapp.model.events;

import org.joda.time.DateTime;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */
public class ShowTransactionDetailsEvent
{
	private final UUID transactionId;

	private final String transactionType;

	private DateTime transactionDate;

	public ShowTransactionDetailsEvent(UUID transactionId, String transactionType, DateTime transactionDate) {
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
	}

	public UUID getTransactionId() {
		return transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public DateTime getTransactionDate() {
		return transactionDate;
	}
}
