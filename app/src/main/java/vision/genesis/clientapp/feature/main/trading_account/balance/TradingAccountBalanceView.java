package vision.genesis.clientapp.feature.main.trading_account.balance;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.SimpleChartPoint;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

interface TradingAccountBalanceView extends MvpView
{
	void setChartData(List<SimpleChartPoint> balanceChart);

	void setAmount(String amount);

	void setChange(Boolean isChangeNegative, String changePercent, String changeValue, String baseChangeValue);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);
}