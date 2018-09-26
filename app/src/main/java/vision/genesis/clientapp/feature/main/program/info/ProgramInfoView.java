package vision.genesis.clientapp.feature.main.program.info;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.ChartSimple;
import io.swagger.client.model.ProgramDetailsFull;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

interface ProgramInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setProgramDetails(ProgramDetailsFull programDetails);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showInvestWithdrawButtons(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setRefreshing(boolean show);

	void setChartData(List<ChartSimple> chart);
}