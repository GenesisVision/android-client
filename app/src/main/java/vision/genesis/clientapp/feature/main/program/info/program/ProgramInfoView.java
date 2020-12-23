package vision.genesis.clientapp.feature.main.program.info.program;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.ProgramFollowDetailsFull;
import vision.genesis.clientapp.model.ProgramRequest;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

interface ProgramInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setDetails(ProgramFollowDetailsFull details);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showInvestWithdrawButtons();

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showInvestProgramActivity(ProgramRequest request);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showWithdrawProgramActivity(ProgramRequest request);

	void setReinvest(Boolean isReinvest);

	void setIgnoreSo(Boolean isIgnoreSo);

	void setInvestWithdrawInfo(String info);

	void showRequestsBottomSheet();

	void showSnackbarMessage(String message);

	void showLoginActivity();
}