package vision.genesis.clientapp.feature.main.fund.create.deposit;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.WalletData;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

interface CreateFundDepositView extends MvpView
{
	void setMinDepositWalletCurrencyAmount(Double minDepositAmount);

	void setWallets(List<WalletData> wallets);

	void setWallet(WalletData selectedWallet);

	void setAmount(String amountText);

	void setAmountBase(String amountBaseString);

	void setCreateButtonEnabled(boolean enabled);

	void showSnackbarMessage(String message);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);
}