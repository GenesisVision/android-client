package vision.genesis.clientapp.feature.tournament.round;

import com.arellomobile.mvp.MvpView;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.InvestmentProgramDashboardInvestor;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/05/2018.
 */

interface TournamentProgramsView extends MvpView
{
	void setRefreshing(boolean refreshing);

	void showProgressBar(boolean show);

	void showEmpty(boolean show);

	void showNoInternet(boolean show);

	void setPrograms(List<InvestmentProgramDashboardInvestor> transactions);

	void changeProgramIsFavorite(UUID programId, boolean isFavorite);
}
