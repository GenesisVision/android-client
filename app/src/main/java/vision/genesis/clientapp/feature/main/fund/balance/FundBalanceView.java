package vision.genesis.clientapp.feature.main.fund.balance;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.BalanceChartPoint;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

interface FundBalanceView extends MvpView
{
	void setChartData(List<BalanceChartPoint> balanceChart);

	void setAmount(String gvtAmount, String baseAmount);

	void setChange(Boolean isChangeNegative, String changePercent, String changeValue, String baseChangeValue);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);
}