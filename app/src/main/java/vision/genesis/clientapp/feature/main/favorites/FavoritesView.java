package vision.genesis.clientapp.feature.main.favorites;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;
import java.util.UUID;

import vision.genesis.clientapp.model.InvestmentProgramExtended;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/05/2018.
 */

interface FavoritesView extends MvpView
{
	void setInvestmentPrograms(List<InvestmentProgramExtended> programs);

	void setTournamentPrograms(List<InvestmentProgramExtended> programs);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmptyList(boolean show);

	void removeProgram(UUID programId);

	void showUserLoggedOff(boolean show);

	void setFavoritesCount(String count);
}
