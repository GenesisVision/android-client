package vision.genesis.clientapp.feature.main.wallet;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface WalletView extends MvpView
{
	void setBalance(double balance);

	void showBalanceProgress();

	void hideBalanceProgress();
}
