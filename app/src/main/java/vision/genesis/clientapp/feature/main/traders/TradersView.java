package vision.genesis.clientapp.feature.main.traders;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.InvestmentProgram;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface TradersView extends MvpView
{
	void setInvestmentPrograms(List<InvestmentProgram> programs);

	void addInvestmentPrograms(List<InvestmentProgram> programs);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);
}
