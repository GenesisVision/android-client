package vision.genesis.clientapp.feature.main.trading_account.create.deposit;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.WalletData;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

interface CreateAccountDepositView extends MvpView
{
	void setMinDepositWalletCurrencyAmount(Double minDepositAmount, String currency);

	void setWallets(List<WalletData> wallets);

	void setWallet(WalletData selectedWallet);

	void setAmount(String amountText);

	void setCreateButtonEnabled(boolean enabled);

	void showSnackbarMessage(String message);

	void showRateProgress(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);
}