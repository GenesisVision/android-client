package vision.genesis.clientapp.feature.main.program.profit;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.Currency;
import io.swagger.client.model.ProgramChartStatistic;
import io.swagger.client.model.SimpleChart;
import io.swagger.client.model.SimpleChartPoint;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2018.
 */

interface ProgramProfitView extends MvpView
{
	void setAbsChart(List<SimpleChartPoint> chart);

	void setPercentChart(List<SimpleChart> chart);

	void updateStatistics(ProgramChartStatistic statistic, Currency baseCurrency);

	void setValue(Boolean isNegative, String value);

	void setChange(Boolean isChangeNegative, String changePercent);

	void setChangeVisibility(boolean visible);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);
}