package vision.genesis.clientapp.feature.main.fund.profit;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.SimpleChartPoint;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

interface FundProfitView extends MvpView
{
	void setChartData(List<SimpleChartPoint> chart);

	void setValue(boolean isNegative, String value);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);

	void setStatisticsData(Double sharpeRatio, Double sortinoRatio, Double calmarRatio, Double maxDrawdown);
}