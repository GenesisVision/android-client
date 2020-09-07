package vision.genesis.clientapp.feature.main.fund.reallocate_history;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.FundHistoryEventViewModel;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2019.
 */

interface FundHistoryView extends MvpView
{
	void setHistory(List<FundHistoryEventViewModel> history);

	void addHistory(List<FundHistoryEventViewModel> history);

	void showEventDetails(FundHistoryEventViewModel event);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);

	void showSnackbarMessage(String message);
}
