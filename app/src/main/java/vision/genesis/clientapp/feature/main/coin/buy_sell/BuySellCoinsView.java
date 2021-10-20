package vision.genesis.clientapp.feature.main.coin.buy_sell;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.model.BuySellCoinsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2021.
 */

interface BuySellCoinsView extends MvpView
{
	void updateView(BuySellCoinsModel model);

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
