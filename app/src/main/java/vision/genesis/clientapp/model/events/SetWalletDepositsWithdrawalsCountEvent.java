package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/04/2019.
 */
public class SetWalletDepositsWithdrawalsCountEvent
{
	private Integer depositsWithdrawalsCount;

	public SetWalletDepositsWithdrawalsCountEvent(Integer depositsWithdrawalsCount) {
		this.depositsWithdrawalsCount = depositsWithdrawalsCount;
	}

	public Integer getDepositsWithdrawalsCount() {
		return depositsWithdrawalsCount;
	}
}
