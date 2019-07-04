package vision.genesis.clientapp.feature.main.wallet.transfer_copytrading_account;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.WalletData;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/02/2019.
 */

interface TransferCopytradingAccountView extends MvpView
{
	void setWallets(List<WalletData> walletsTo);

	void setSelectedWallet(WalletData walletTo);

	void setAmount(String amountString);

	void setRate(String feeAmountString);

	void setFinalAmount(String finalAmountString);

	void setConfirmButtonEnabled(boolean enabled);

	void showProgress(boolean show);

	void showRateProgress(boolean show);

	void showButtonProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
