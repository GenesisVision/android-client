package vision.genesis.clientapp.feature.main.trading_account.profit_abs;

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
 * Created by Vitaly on 28/11/2019.
 */

interface TradingAccountProfitAbsView extends MvpView
{
	void setAbsChart(List<SimpleChartPoint> chart);

	void setCurrency(PlatformCurrencyInfo selectedCurrency);

	void showChangeBaseCurrencyList(ArrayList<String> optionsList);

	void setValue(Boolean isNegative, String value);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);
}