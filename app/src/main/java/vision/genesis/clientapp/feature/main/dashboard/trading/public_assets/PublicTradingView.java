package vision.genesis.clientapp.feature.main.dashboard.trading.public_assets;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.DashboardTradingAsset;
import vision.genesis.clientapp.model.CurrencyEnum;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2020.
 */

interface PublicTradingView extends MvpView
{
	void setBaseCurrency(CurrencyEnum baseCurrency);

	void setPublic(List<DashboardTradingAsset> items);

	void showProgress(boolean show);

	void setRefreshing(boolean refreshing);

	void showSnackbarMessage(String message);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();
}