package vision.genesis.clientapp.feature.main.dashboard.investments.coins.history;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.CoinsHistoryEvent;
import io.swagger.client.model.FundHistoryEventViewModel;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2021.
 */

interface CoinsHistoryView extends MvpView
{
	void setHistory(List<CoinsHistoryEvent> history);

	void addHistory(List<CoinsHistoryEvent> history);

	void showEventDetails(FundHistoryEventViewModel event);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);

	void showSnackbarMessage(String message);
}
