package vision.genesis.clientapp.feature.main.wallet.withdraw;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVision
 * Created by Vitaly on 2/27/18.
 */

interface WithdrawWalletView extends MvpView
{
	void setWalletAddress(String address);

	void setWithdrawButtonEnabled(boolean enabled);

	void showProgress(boolean show);

	void finishActivity();
}
