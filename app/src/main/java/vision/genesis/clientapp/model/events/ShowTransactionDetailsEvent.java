package vision.genesis.clientapp.model.events;

import io.swagger.client.model.TransactionViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */
public class ShowTransactionDetailsEvent
{
	private final TransactionViewModel transaction;

	public ShowTransactionDetailsEvent(TransactionViewModel transaction) {
		this.transaction = transaction;
	}

	public TransactionViewModel getTransaction() {
		return transaction;
	}
}
