package vision.genesis.clientapp.feature.main.search;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.ProgramDetails;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/05/2018.
 */

interface SearchView extends MvpView
{
	void setInvestmentPrograms(List<ProgramDetails> programs);

	void addInvestmentPrograms(List<ProgramDetails> programs);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmptyList(boolean show);

	void finishActivity();

	void changeProgramIsFavorite(UUID programId, boolean isFavorite);

	void setResultsCount(String count);
}
