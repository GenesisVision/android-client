package vision.genesis.clientapp.feature.main.wallet.transfer_funds;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.model.TransferFundsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/02/2019.
 */

interface TransferWalletView extends MvpView
{
	void updateView(TransferFundsModel model);

	void setCurrencyOptions(ArrayList<String> currencies);

	void setCurrency(Integer position);

	void setWallets(List<WalletData> wallets);

	void setWallet(WalletData wallet);

	void setAmount(String amountString);

	void setRate(String feeAmountString);

	void showRate(boolean show);

	void setFinalAmount(String finalAmountString);

	void setConfirmButtonEnabled(boolean enabled);

	void showProgress(boolean show);

	void showRateProgress(boolean show);

	void showButtonProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
