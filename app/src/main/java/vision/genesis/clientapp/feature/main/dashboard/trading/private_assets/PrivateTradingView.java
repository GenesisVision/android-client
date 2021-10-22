package vision.genesis.clientapp.feature.main.dashboard.trading.private_assets;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.DashboardTradingAsset;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2020.
 */

interface PrivateTradingView extends MvpView
{
	void setPrivateAccounts(List<DashboardTradingAsset> items, String baseCurrency);

	void setPrivateFunds(List<DashboardTradingAsset> items, String baseCurrency);

	void setRefreshing(boolean refreshing);

	void showSnackbarMessage(String message);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();
}