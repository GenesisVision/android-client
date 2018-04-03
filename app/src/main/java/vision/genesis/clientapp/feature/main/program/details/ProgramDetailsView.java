package vision.genesis.clientapp.feature.main.program.details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.InvestmentProgramDetails;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

interface ProgramDetailsView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setProgram(InvestmentProgramDetails programDetails);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showInvestWithdrawButtons(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setRefreshing(boolean show);
}