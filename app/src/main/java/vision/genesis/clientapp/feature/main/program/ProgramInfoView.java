package vision.genesis.clientapp.feature.main.program;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.InvestmentProgramDetails;
import vision.genesis.clientapp.model.TooltipModel;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

interface ProgramInfoView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void setProgram(InvestmentProgramDetails programDetails);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();

	void showToast(String message);

	void showTooltipActivity(TooltipModel tooltipModel);

	void showFavoriteButton(boolean show);

	void showNoInternet(boolean show);

	void showSnackbarMessage(String message);

	void showNoInternetProgress(boolean show);

	void showProgress(boolean show);

	void showTrades();
}