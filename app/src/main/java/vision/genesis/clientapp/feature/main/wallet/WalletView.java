package vision.genesis.clientapp.feature.main.wallet;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.WalletSummary;
import vision.genesis.clientapp.model.CurrencyEnum;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface WalletView extends MvpView
{
	void setBaseCurrency(CurrencyEnum baseCurrency);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setBalance(WalletSummary data);

	void showProgress(boolean show);

	void setRefreshing(boolean refreshing);

	void showSnackbarMessage(String message);
}
