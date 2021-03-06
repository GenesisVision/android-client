package vision.genesis.clientapp.feature.main.programs_list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.ProgramDetailsListItem;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface ProgramsListView extends MvpView
{
	void setInvestmentPrograms(List<ProgramDetailsListItem> programs);

	void addInvestmentPrograms(List<ProgramDetailsListItem> programs);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmptyList(boolean show);

	void showFiltersActivity(ProgramsFilter filter);

	void showFiltersActive(boolean show);

	void changeProgramIsFavorite(UUID programId, boolean isFavorite);

	void showBottomProgress(boolean show);
}
