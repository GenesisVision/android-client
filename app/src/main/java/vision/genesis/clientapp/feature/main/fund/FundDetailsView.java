package vision.genesis.clientapp.feature.main.fund;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.InvestmentEventViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

interface FundDetailsView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void showGuest(FundDetailsFull fundDetails);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showOwner(FundDetailsFull fundDetails);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishInit(boolean showEvents);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();

	void showToast(String message);

	void showToolbarButtons(boolean show);

	void showNoInternet(boolean show);

	void showSnackbarMessage(String message);

	void showNoInternetProgress(boolean show);

	void showProgress(boolean show);

	void setRefreshing(boolean refreshing);

	void setReallocatesCount(Integer count);

	void setEventsCount(Integer eventsCount);

	void showEventDetails(InvestmentEventViewModel event);
}