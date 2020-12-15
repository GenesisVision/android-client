package vision.genesis.clientapp.feature.main.program.reports;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.ProgramPeriodViewModel;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2020.
 */

interface ProgramReportsView extends MvpView
{
	void setIntervalOptions(ArrayList<String> intervalOptions);

	void setInterval(String interval, Integer position);

	void setData(List<ProgramPeriodViewModel> periods);

	void addData(List<ProgramPeriodViewModel> periods);

	void showReportDetails(ProgramPeriodViewModel period);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);

	void showSnackbarMessage(String message);
}
