package vision.genesis.clientapp.feature.main.program.period_history;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.ProgramPeriodViewModel;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/08/2019.
 */

interface PeriodHistoryView extends MvpView
{
	void setPeriods(List<ProgramPeriodViewModel> periods);

	void addPeriods(List<ProgramPeriodViewModel> periods);

	void showPeriodDetails(ProgramPeriodViewModel period);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);

	void showSnackbarMessage(String message);
}
