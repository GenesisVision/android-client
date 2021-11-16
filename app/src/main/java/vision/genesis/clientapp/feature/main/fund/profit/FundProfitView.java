package vision.genesis.clientapp.feature.main.fund.profit;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.Currency;
import io.swagger.client.model.FundChartStatistic;
import io.swagger.client.model.SimpleChart;
import io.swagger.client.model.SimpleChartPoint;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

interface FundProfitView extends MvpView
{
	void setAbsChart(List<SimpleChartPoint> chart);

	void setPercentChart(List<SimpleChart> chart);

	void updateStatistics(FundChartStatistic statistic, Currency baseCurrency);

	void setValue(boolean isNegative, String value);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);

}