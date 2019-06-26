package vision.genesis.clientapp.feature.main.copytrading.create_account;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/06/2019.
 */

interface CreateCopytradingAccountView extends MvpView
{
	void setWalletsFrom(List<WalletData> wallets);

	void setWalletFrom(WalletData selectedWalletFrom);

	void setMinDepositAmount(Double minDepositAmount, String currency);

	void setAmount(String amountText);

	void setAmountBase(String amountBaseString);

	void setNextButtonEnabled(boolean enabled);

	void showSubscriptionSettings(SubscriptionSettingsModel model);

	void showProgress(boolean show);

	void showAmountProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
