package vision.genesis.clientapp.feature.main.dashboard;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.InvestmentProgramDashboard;


/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface DashboardView extends MvpView
{
	void setActivePrograms(List<InvestmentProgramDashboard> programs);

	void setArchivedPrograms(List<InvestmentProgramDashboard> programs);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmpty(boolean show);

	void setRefreshing(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);
}
