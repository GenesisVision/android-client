package vision.genesis.clientapp.feature.main.dashboard.old.investor.programs;

import com.arellomobile.mvp.MvpView;

import java.util.UUID;

import vision.genesis.clientapp.model.filter.DashboardFilter;

/**
 * GenesisVision
 * Created by Vitaly on 3/13/18.
 */

interface DashboardProgramsView extends MvpView
{
	void showProgressBar(boolean show);

	void showEmpty(boolean show);

//	void setPrograms(List<ProgramDetails> programs);

	void setProgramReinvest(UUID programId, Boolean reinvest);

	void setProgramFavorite(UUID programId, Boolean favorite);

	void showFiltersActivity(DashboardFilter filter);
}
