package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/04/2019.
 */
public class SetSpecificWalletTransactionsCountEvent
{
	private Integer transactionsCount;

	public SetSpecificWalletTransactionsCountEvent(Integer transactionsCount) {
		this.transactionsCount = transactionsCount;
	}

	public Integer getTransactionsCount() {
		return transactionsCount;
	}
}
