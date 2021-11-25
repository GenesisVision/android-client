package vision.genesis.clientapp.feature.main.fund.balance;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.BalanceChartPoint;
import io.swagger.client.model.PlatformCurrencyInfo;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

interface FundBalanceView extends MvpView
{
	void setChartData(List<BalanceChartPoint> balanceChart);

	void setAmount(String amount);

	void setChange(Boolean isChangeNegative, String changePercent, String changeValue);

	void setFunds(String managerFunds, String investorsFunds);

	void setCurrency(PlatformCurrencyInfo selectedCurrency);

	void showChangeBaseCurrencyList(ArrayList<String> optionsList);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);
}