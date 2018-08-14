package vision.genesis.clientapp.feature.main.dashboard.investor;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.DashboardPortfolioEvent;
import vision.genesis.clientapp.model.InvestmentProgramDashboardExtended;


/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface InvestorDashboardView extends MvpView
{
	void setActivePrograms(List<InvestmentProgramDashboardExtended> programs);

	void setArchivedPrograms(List<InvestmentProgramDashboardExtended> programs);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmpty(boolean show);

	void setRefreshing(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void setTotalPortfolioValue(Double totalPortfolioAmount);

	void setPortfolioEvents(List<DashboardPortfolioEvent> events);
}
