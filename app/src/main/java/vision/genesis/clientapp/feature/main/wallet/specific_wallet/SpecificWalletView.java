package vision.genesis.clientapp.feature.main.wallet.specific_wallet;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.WalletData;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/02/2019.
 */

interface SpecificWalletView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setWalletData(WalletData data);

	void showProgress(boolean show);

	void setRefreshing(boolean refreshing);

	void showSnackbarMessage(String message);

	void setTransactionsCount(Integer transactionsCount);

	void setDepositsWithdrawalsCount(Integer depositsWithdrawalsCount);
}
