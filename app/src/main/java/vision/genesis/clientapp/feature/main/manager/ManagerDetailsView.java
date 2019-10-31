package vision.genesis.clientapp.feature.main.manager;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.PublicProfile;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

interface ManagerDetailsView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setManagerDetails(PublicProfile managerDetails);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();

	void showToast(String message);

	void showToolbarButtons(boolean show);

	void showNoInternet(boolean show);

	void showSnackbarMessage(String message);

	void showNoInternetProgress(boolean show);

	void showProgress(boolean show);

	void setRefreshing(boolean refreshing);

	void setProgramsCount(Integer programsCount);

	void setFundsCount(Integer fundsCount);
}