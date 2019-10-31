package vision.genesis.clientapp.feature.main.program.profit;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.SimpleChartPoint;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2018.
 */

interface ProgramProfitView extends MvpView
{
	void setChartData(List<SimpleChartPoint> chart);

	void setAmount(String gvtAmount, String baseAmount);

	void setChange(Boolean isChangeNegative, String changePercent);

	void setChangeVisibility(boolean visible);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);

	void setStatisticsData(Integer trades, Double successTradesPercent, Double profitFactor, Double sharpeRatio, Double sortinoRatio, Double calmarRatio, Double maxDrawdown);
}