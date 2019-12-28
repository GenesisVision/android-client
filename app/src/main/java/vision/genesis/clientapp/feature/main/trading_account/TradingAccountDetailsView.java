package vision.genesis.clientapp.feature.main.trading_account;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.UUID;

import io.swagger.client.model.PrivateTradingAccountFull;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

interface TradingAccountDetailsView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setDetails(PrivateTradingAccountFull accountDetails);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();

	void showToast(String message);

	void showNoInternet(boolean show);

	void showSnackbarMessage(String message);

	void showNoInternetProgress(boolean show);

	void showProgress(boolean show);

	void showTrades();

	void setRefreshing(boolean refreshing);

	void setOpenPositionsCount(Integer openPositionsCount);

	void setTradesCount(Integer tradesCount);

	void showUnfollowTradesActivity(UUID followId, UUID tradingAccountId, String followName, Boolean isExternal);
}