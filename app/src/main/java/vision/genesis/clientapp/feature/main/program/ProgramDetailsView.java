package vision.genesis.clientapp.feature.main.program;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.ProgramDetailsFull;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

interface ProgramDetailsView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setProgram(ProgramDetailsFull programDetails);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();

	void showToast(String message);

	void showFavoriteButton(boolean show);

	void showNoInternet(boolean show);

	void showSnackbarMessage(String message);

	void showNoInternetProgress(boolean show);

	void showProgress(boolean show);

//	@StateStrategyType(AddToEndSingleStrategy.class)
//	void setRefreshing(boolean show);

	void showTrades();

	void setRefreshing(boolean refreshing);
}