package vision.genesis.clientapp.feature.main.dashboard.programs;

import com.arellomobile.mvp.MvpView;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.InvestmentProgramDashboardInvestor;

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

	void setPrograms(List<InvestmentProgramDashboardInvestor> transactions);

	void changeProgramIsFavorite(UUID programId, boolean isFavorite);
}
