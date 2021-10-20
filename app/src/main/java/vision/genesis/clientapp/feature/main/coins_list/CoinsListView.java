package vision.genesis.clientapp.feature.main.coins_list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.CoinsAsset;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/10/2021.
 */

interface CoinsListView extends MvpView
{
	void setAdapterUserLoggedIn(boolean isLoggedIn);

	void setCoins(List<CoinsAsset> coins);

	void addCoins(List<CoinsAsset> coins);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmptyList(boolean show);

	void showFiltersActive(boolean show);

	void changeCoinIsFavorite(UUID fundId, boolean isFavorite);

	void showFiltersActivity(ProgramsFilter filter);

	void showBottomProgress(boolean show);

	void showBuyCoinActivity(CoinsAsset coin);
}
