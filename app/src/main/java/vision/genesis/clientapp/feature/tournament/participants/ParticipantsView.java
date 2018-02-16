package vision.genesis.clientapp.feature.tournament.participants;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.ParticipantViewModel;


/**
 * GenesisVision
 * Created by Vitaly on 2/8/18.
 */

interface ParticipantsView extends MvpView
{
	void setParticipantsCount(int count);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setParticipants(List<ParticipantViewModel> programs);

	void addParticipants(List<ParticipantViewModel> programs);

	@StateStrategyType(SkipStrategy.class)
	void scrollListTo(int position);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showNoInternet(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgressBar(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showTournamentNotStarted(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showSearch(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showEmptyResults(boolean show);
}
