package vision.genesis.clientapp.feature.main.dashboard.investments.details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.DashboardInvestingDetails;
import io.swagger.client.model.FundDetailsList;
import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.ProgramDetailsList;
import vision.genesis.clientapp.model.CurrencyEnum;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

interface InvestmentsDetailsView extends MvpView
{
	void setBaseCurrency(CurrencyEnum baseCurrency);

	void setInvesting(DashboardInvestingDetails details);

	void setEvents(List<InvestmentEventViewModel> items);

	void setPrograms(List<ProgramDetailsList> items);

	void setFunds(List<FundDetailsList> items);

	void hideProgramsProgress();

	void hideFundsProgress();

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setRefreshing(boolean refreshing);

	void showSnackbarMessage(String message);
}
