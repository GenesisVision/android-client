package vision.genesis.clientapp.feature.main.program;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.ProgramFollowDetailsFull;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

interface ProgramDetailsView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgram(ProgramFollowDetailsFull details);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showFollow(ProgramFollowDetailsFull details);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showOwner(ProgramFollowDetailsFull details);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();

	void showToast(String message);

	void showToolbarButtons(boolean show);

	void showNoInternet(boolean show);

	void showSnackbarMessage(String message);

	void showNoInternetProgress(boolean show);

	void showProgress(boolean show);

	void showTrades();

	void setRefreshing(boolean refreshing);

	void setOpenPositionsCount(Integer openPositionsCount);

	void setTradesCount(Integer tradesCount);

	void setPeriodHistoryCount(Integer periodHistoryCount);

	void setEventsCount(Integer eventsCount);

	void showEventDetails(InvestmentEventViewModel event);
}