package vision.genesis.clientapp.feature.main.dashboard.investor;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.DashboardChartValue;
import io.swagger.client.model.DashboardPortfolioEvent;
import io.swagger.client.model.ValueChartBar;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;


/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface InvestorDashboardView extends MvpView
{
	void setChartData(DashboardChartValue chart);

	void setPortfolioEvents(List<DashboardPortfolioEvent> events);

	void setRefreshing(boolean show);

	void showProgressBar(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void setAssetsCount(Integer programsCount, Integer fundsCount);

	void setDateRange(DateRange dateRange);

	void setHaveNewNotifications(boolean have);

	void setBaseCurrency(CurrencyEnum currency);

	void setChartViewMode(Boolean viewMode, Float chartBottomY);

	void setPortfolioAssets(ValueChartBar valueChartBar);
}
