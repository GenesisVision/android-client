package vision.genesis.clientapp.feature.main.dashboard.investments.coins.portfolio;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.CoinsAsset;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2021.
 */

interface CoinsAssetsView extends MvpView
{
	void setCoins(List<CoinsAsset> coins);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);
}
