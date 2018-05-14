package vision.genesis.clientapp.feature.tournament;

import com.arellomobile.mvp.MvpView;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.InvestmentProgram;
import io.swagger.client.model.InvestmentProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/05/2018.
 */

interface TournamentView extends MvpView
{
	void setTournamentPrograms(List<InvestmentProgram> programs);

	void addTournamentPrograms(List<InvestmentProgram> programs);

	void setRefreshing(boolean refreshing);

	void showProgressBar(boolean show);

	void showNoInternet(boolean show);

	void changeProgramIsFavorite(UUID programId, boolean isFavorite);

	void showEmptyList(boolean show);

	void showSnackbarMessage(String message);

	void updateFilter(InvestmentProgramsFilter filter);

	void setProgramsCount(String count);
}