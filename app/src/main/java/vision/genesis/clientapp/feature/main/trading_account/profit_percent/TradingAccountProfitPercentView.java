package vision.genesis.clientapp.feature.main.trading_account.profit_percent;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.AccountChartStatistic;
import io.swagger.client.model.PlatformCurrencyInfo;
import io.swagger.client.model.SimpleChart;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/11/2021.
 */

interface TradingAccountProfitPercentView extends MvpView
{
	void setCurrencies(List<PlatformCurrencyInfo> selectedCurrencies, boolean showAddButton);

	void showAddCurrenciesList(ArrayList<String> optionsList);

	void showReplaceCurrenciesList(ArrayList<String> optionsList, PlatformCurrencyInfo assetToReplace);

	void showChangeBaseCurrenciesList(ArrayList<String> optionsList);

	void setPercentChart(List<SimpleChart> chart);

	void updateStatistics(AccountChartStatistic statistic, String baseCurrency);

	void setValue(Boolean isNegative, String value);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);
}