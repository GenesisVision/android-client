package vision.genesis.clientapp.feature.main.fund.info;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.FundDetailsFull;
import vision.genesis.clientapp.model.ProgramRequest;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

interface FundInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setFundDetails(FundDetailsFull fundDetails);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showInvestWithdrawButtons(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showInvestFundActivity(ProgramRequest request);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showWithdrawFundActivity(ProgramRequest request);

	void showLoginActivity();
}