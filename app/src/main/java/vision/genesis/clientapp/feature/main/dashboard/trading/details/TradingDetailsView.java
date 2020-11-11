package vision.genesis.clientapp.feature.main.dashboard.trading.details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.DashboardTradingAsset;
import io.swagger.client.model.DashboardTradingDetails;
import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.Timeframe;
import vision.genesis.clientapp.model.CurrencyEnum;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

interface TradingDetailsView extends MvpView
{
	void setCreateOptions(ArrayList<String> createPrivateOptions, ArrayList<String> createPublicOptions);

	void setBaseCurrency(CurrencyEnum baseCurrency);

	void setTimeframe(Timeframe timeframe);

	void setTrading(DashboardTradingDetails details);

	void setEvents(List<InvestmentEventViewModel> items);

	void setPrivateAccounts(List<DashboardTradingAsset> items);

	void setPrivateFunds(List<DashboardTradingAsset> items);

	void setPublic(List<DashboardTradingAsset> items);

	void setPrivateCount(int count);

	void setPublicCount(int count);

	void hidePrivateProgress();

	void hidePublicProgress();

	void showCreateTradingAccountActivity();

	void showAttachAccountActivity();

	void showCreateSelfManagedFundActivity();

	void showCreateFundActivity();

	void showProfilePublicInfoActivity();

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setRefreshing(boolean refreshing);

	void showSnackbarMessage(String message);
}
