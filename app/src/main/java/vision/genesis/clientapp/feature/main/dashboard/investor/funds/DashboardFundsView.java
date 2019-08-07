package vision.genesis.clientapp.feature.main.dashboard.investor.funds;

import com.arellomobile.mvp.MvpView;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.FundDetails;
import vision.genesis.clientapp.model.filter.DashboardFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

interface DashboardFundsView extends MvpView
{
	void setFunds(List<FundDetails> programs);

	void setFundFavorite(UUID fundId, Boolean favorite);

	void showEmpty(boolean show);

	void showProgressBar(boolean show);

	void showFiltersActivity(DashboardFilter filter);
}
