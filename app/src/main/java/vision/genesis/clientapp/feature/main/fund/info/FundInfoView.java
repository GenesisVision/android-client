package vision.genesis.clientapp.feature.main.fund.info;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.FundChartStatistic;
import io.swagger.client.model.FundDetailsFull;
import vision.genesis.clientapp.model.FundRequest;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

interface FundInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setFundDetails(FundDetailsFull fundDetails);

	void updateStatistics(FundChartStatistic statistic, String baseCurrency);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showInvestWithdrawButtons();

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showInvestFundActivity(FundRequest request);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showWithdrawFundActivity(FundRequest request);

	void showLoginActivity();

	void showRequestsBottomSheet();
}