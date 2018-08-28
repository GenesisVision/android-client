package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

import com.arellomobile.mvp.MvpView;

import java.util.List;
import java.util.UUID;

import vision.genesis.clientapp.model.InvestmentProgramDashboardExtended;

/**
 * GenesisVision
 * Created by Vitaly on 3/13/18.
 */

interface DashboardProgramsView extends MvpView
{
	void showProgressBar(boolean show);

	void showEmpty(boolean show);

	void showNoInternet(boolean show);

	void setPrograms(List<InvestmentProgramDashboardExtended> transactions);

	void changeProgramIsFavorite(UUID programId, boolean isFavorite);
}
