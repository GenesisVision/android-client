package vision.genesis.clientapp.feature.main.manager.profit;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.ChartSimple;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

interface ManagerProfitView extends MvpView
{
	void setChartData(List<ChartSimple> equityChart);

	void setAmount(String gvtAmount, String baseAmount);

	void setChange(Boolean isChangeNegative, String changePercent, String changeValue, String baseChangeValue);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);

	void setStatisticsData(Integer trades, Double successTradesPercent, Double profitFactor, Double sharpeRatio, Double sortinoRatio, Double calmarRatio, Double maxDrawdown);
}