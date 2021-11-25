package vision.genesis.clientapp.feature.main.fund.profit_abs;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.PlatformCurrencyInfo;
import io.swagger.client.model.SimpleChartPoint;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

interface FundProfitAbsView extends MvpView
{
	void setAbsChart(List<SimpleChartPoint> chart);

	void setValue(boolean isNegative, String value);

	void setCurrency(PlatformCurrencyInfo selectedCurrency);

	void showChangeBaseCurrencyList(ArrayList<String> optionsList);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);
}