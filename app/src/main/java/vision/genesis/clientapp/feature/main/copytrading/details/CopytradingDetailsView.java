package vision.genesis.clientapp.feature.main.copytrading.details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/12/2019.
 */

interface CopytradingDetailsView extends MvpView
{
	void setOpenTradesCount(Integer openPositionsCount);

	void setTradesCount(Integer tradesCount);

	void setTradingLogCount(Integer tradingLogCount);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();
}