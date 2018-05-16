package vision.genesis.clientapp.feature.main.search;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import vision.genesis.clientapp.model.InvestmentProgramExtended;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/05/2018.
 */

interface SearchView extends MvpView
{
	void setInvestmentPrograms(List<InvestmentProgramExtended> programs);

	void addInvestmentPrograms(List<InvestmentProgramExtended> programs);

	void setTournamentPrograms(List<InvestmentProgramExtended> programs);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmptyList(boolean show);

	void finishActivity();

	void changeProgramIsFavorite(UUID programId, boolean isFavorite);

	void setResultsCount(String count);
}
