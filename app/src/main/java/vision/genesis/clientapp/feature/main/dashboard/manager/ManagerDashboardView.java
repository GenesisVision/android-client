package vision.genesis.clientapp.feature.main.dashboard.manager;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/07/2018.
 */

interface ManagerDashboardView extends MvpView
{
	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmpty(boolean show);

	void setRefreshing(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);
}
