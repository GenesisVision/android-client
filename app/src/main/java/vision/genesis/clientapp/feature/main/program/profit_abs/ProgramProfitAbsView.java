package vision.genesis.clientapp.feature.main.program.profit_abs;

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
 * Created by Vitaly on 27/09/2018.
 */

interface ProgramProfitAbsView extends MvpView
{
	void setAbsChart(List<SimpleChartPoint> chart);

	void setValue(Boolean isNegative, String value);

	void setCurrency(PlatformCurrencyInfo selectedCurrency);

	void showChangeBaseCurrencyList(ArrayList<String> optionsList);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);
}