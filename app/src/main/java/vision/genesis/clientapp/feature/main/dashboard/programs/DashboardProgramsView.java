package vision.genesis.clientapp.feature.main.dashboard.programs;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.InvestmentProgramDashboard;

/**
 * GenesisVision
 * Created by Vitaly on 3/13/18.
 */

interface DashboardProgramsView extends MvpView
{
	void setRefreshing(boolean refreshing);

	void showProgressBar(boolean show);

	void showEmpty(boolean show);

	void showNoInternet(boolean show);

	void setPrograms(List<InvestmentProgramDashboard> transactions);
}
