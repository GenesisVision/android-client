package vision.genesis.clientapp.feature.main.dashboard.investor.funds;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.FundDetails;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

interface DashboardFundsView extends MvpView
{
	void setFunds(List<FundDetails> programs);

	void showEmpty(boolean show);

	void showProgressBar(boolean show);
}
