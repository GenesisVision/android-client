package vision.genesis.clientapp.feature.main.program.create.deposit;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.WalletData;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 29/11/2019.
 */

interface CreateProgramDepositView extends MvpView
{
	void setMinDepositWalletCurrencyAmount(Double minDepositAmount, String currency);

	void setWallets(List<WalletData> wallets);

	void setWallet(WalletData selectedWallet);

	void setAmount(String amountText);

	void setConfirmButtonEnabled(boolean enabled);

	void showSnackbarMessage(String message);

	void showRateProgress(boolean show);

	void showButtonProgress(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);
}