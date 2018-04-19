package vision.genesis.clientapp.feature.main.program.details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.InvestmentProgramDetails;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

interface ProgramDetailsView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setProgramDetails(InvestmentProgramDetails programDetails);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showInvestWithdrawButtons(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setRefreshing(boolean show);
}