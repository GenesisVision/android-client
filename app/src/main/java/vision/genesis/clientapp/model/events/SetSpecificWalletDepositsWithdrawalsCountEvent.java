package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/04/2019.
 */
public class SetSpecificWalletDepositsWithdrawalsCountEvent
{
	private Integer depositsWithdrawalsCount;

	public SetSpecificWalletDepositsWithdrawalsCountEvent(Integer depositsWithdrawalsCount) {
		this.depositsWithdrawalsCount = depositsWithdrawalsCount;
	}

	public Integer getDepositsWithdrawalsCount() {
		return depositsWithdrawalsCount;
	}
}
