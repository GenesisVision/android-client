package vision.genesis.clientapp.feature.main.funds_list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.FundDetails;
import vision.genesis.clientapp.model.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

interface FundsListView extends MvpView
{
	void setFunds(List<FundDetails> funds);

	void addFunds(List<FundDetails> funds);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmptyList(boolean show);

	void showFiltersActive(boolean show);

	void changeFundIsFavorite(UUID fundId, boolean isFavorite);

	void setFundsCount(String count);

	void showFiltersActivity(ProgramsFilter filter);

	void showBottomProgress(boolean show);
}
