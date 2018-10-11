package vision.genesis.clientapp.feature.main.wallet;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.WalletSummary;
import io.swagger.client.model.WalletTransaction;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface WalletView extends MvpView
{
	void setBaseCurrency(CurrencyEnum baseCurrency);

	void setDateRange(DateRange dateRange);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setBalance(WalletSummary data);

	void showProgress(boolean show);

	void setRefreshing(boolean refreshing);

	void showSnackbarMessage(String message);

	void setTransactions(List<WalletTransaction> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void addTransactions(List<WalletTransaction> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections);
}
