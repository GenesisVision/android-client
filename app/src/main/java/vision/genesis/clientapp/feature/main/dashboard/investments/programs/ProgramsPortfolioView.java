package vision.genesis.clientapp.feature.main.dashboard.investments.programs;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.ProgramInvestingDetailsList;
import vision.genesis.clientapp.model.CurrencyEnum;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2020.
 */

interface ProgramsPortfolioView extends MvpView
{
	void setOrderByOptions(ArrayList<String> orderByOptions);

	void setOrderBy(String orderBy, Integer position);

	void setBaseCurrency(CurrencyEnum baseCurrency);

	void setPrograms(List<ProgramInvestingDetailsList> items, Integer total);

	void showProgress(boolean show);

	void setRefreshing(boolean refreshing);

	void showSnackbarMessage(String message);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void finishActivity();
}