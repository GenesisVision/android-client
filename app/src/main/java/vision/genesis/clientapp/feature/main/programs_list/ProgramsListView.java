package vision.genesis.clientapp.feature.main.programs_list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.InvestmentProgram;
import io.swagger.client.model.InvestmentProgramsFilter;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface ProgramsListView extends MvpView
{
	void setInvestmentPrograms(List<InvestmentProgram> programs);

	void addInvestmentPrograms(List<InvestmentProgram> programs);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmptyList(boolean show);

	void setProgramsCount(Integer count);

	void showFiltersActive(boolean show);

	void changeProgramIsFavorite(UUID programId, boolean isFavorite);

	void updateFilter(InvestmentProgramsFilter filter);
}
