package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

import com.arellomobile.mvp.MvpView;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.ProgramDetails;

/**
 * GenesisVision
 * Created by Vitaly on 3/13/18.
 */

interface DashboardProgramsView extends MvpView
{
	void showProgressBar(boolean show);

	void showEmpty(boolean show);

	void setPrograms(List<ProgramDetails> programs);

	void setProgramReinvest(UUID programId, Boolean reinvest);
}
