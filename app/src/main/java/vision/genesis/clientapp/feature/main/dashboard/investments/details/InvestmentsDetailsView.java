package vision.genesis.clientapp.feature.main.dashboard.investments.details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.AssetInvestmentRequestItemsViewModel;
import io.swagger.client.model.CoinsAsset;
import io.swagger.client.model.DashboardInvestingDetails;
import io.swagger.client.model.FundInvestingDetailsList;
import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.ProgramInvestingDetailsList;
import io.swagger.client.model.Timeframe;
import vision.genesis.clientapp.model.CurrencyEnum;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

interface InvestmentsDetailsView extends MvpView
{
	void setBaseCurrency(CurrencyEnum baseCurrency);

	void setTimeframe(Timeframe timeframe);

	void setRequests(AssetInvestmentRequestItemsViewModel data);

	void setInvesting(DashboardInvestingDetails details);

	void setEvents(List<InvestmentEventViewModel> items);

	void setPrograms(List<ProgramInvestingDetailsList> items, int size);

	void setFunds(List<FundInvestingDetailsList> items, int size);

	void setAssets(List<CoinsAsset> items, int size);

	void hideProgramsProgress();

	void hideFundsProgress();

	void hideAssetsProgress();

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setRefreshing(boolean refreshing);

	void showSnackbarMessage(String message);
}
