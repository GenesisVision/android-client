package vision.genesis.clientapp.feature.main.fund.reallocate_history;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.ReallocationModel;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2019.
 */

interface ReallocateHistoryView extends MvpView
{
	void setReallocates(List<ReallocationModel> reallocates);

	void addReallocates(List<ReallocationModel> reallocates);

	void showReallocationDetails(ReallocationModel reallocation);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);

	void showSnackbarMessage(String message);
}
