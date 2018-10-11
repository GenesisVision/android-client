package vision.genesis.clientapp.feature.main.wallet.deposit;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import io.swagger.client.model.WalletInfo;

/**
 * GenesisVision
 * Created by Vitaly on 2/26/18.
 */

interface DepositWalletView extends MvpView
{
	void setWalletsOptions(ArrayList<String> walletsOptions);

	void setWalletInfo(WalletInfo wallet, String walletName, Integer position);

	void setAmountBase(String amountBaseString);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
