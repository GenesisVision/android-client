package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.WalletModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/02/2019.
 */
public class ShowSpecificWalletEvent
{
	private WalletModel walletModel;

	public ShowSpecificWalletEvent(WalletModel walletModel) {
		this.walletModel = walletModel;
	}

	public WalletModel getWalletModel() {
		return walletModel;
	}
}