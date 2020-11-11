package vision.genesis.clientapp.feature.main.dashboard;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.Timeframe;
import io.swagger.client.model.UserVerificationStatus;
import vision.genesis.clientapp.model.CurrencyEnum;


/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/11/2019.
 */

interface DashboardView extends MvpView
{
	void setBaseCurrency(CurrencyEnum baseCurrency);

	void setLimitViewVisibility(boolean visible);

	void setLimitViewButtonStatus(UserVerificationStatus verificationStatus);

	void setSummary(DashboardSummary summary);

	void setRefreshing(boolean show);

	void showProgressBar(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void setHaveNewNotifications(boolean have);

	void setTimeframe(Timeframe timeframe);

	void updateInvesting();

	void updateTrading();

	void updateWallet();
}
