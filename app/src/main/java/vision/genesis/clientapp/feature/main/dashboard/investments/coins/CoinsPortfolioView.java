package vision.genesis.clientapp.feature.main.dashboard.investments.coins;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2020.
 */

interface CoinsPortfolioView extends MvpView
{
	void setAssetsCount(Integer count);

	void setHistoryCount(Integer count);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();
}