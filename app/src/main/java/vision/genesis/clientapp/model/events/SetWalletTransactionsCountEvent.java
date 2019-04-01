package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/04/2019.
 */
public class SetWalletTransactionsCountEvent
{
	private Integer transactionsCount;

	public SetWalletTransactionsCountEvent(Integer transactionsCount) {
		this.transactionsCount = transactionsCount;
	}

	public Integer getTransactionsCount() {
		return transactionsCount;
	}
}
