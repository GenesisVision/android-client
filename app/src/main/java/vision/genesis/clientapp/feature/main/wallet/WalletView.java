package vision.genesis.clientapp.feature.main.wallet;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface WalletView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setBalance(double balance);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setFiatBalance(double balance);

//	@StateStrategyType(AddToEndSingleStrategy.class)
//	void setTransactionsFilterType(TransactionsFilter.TypeEnum type);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showBalanceProgress();

	@StateStrategyType(AddToEndSingleStrategy.class)
	void hideBalanceProgress();

	void showSnackbarMessage(String message);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showPage(int position);
}
