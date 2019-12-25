package vision.genesis.clientapp.feature.main.trading_account.trades;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.OrderSignalModel;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

interface TradingAccountTradesView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);

	void setTrades(List<OrderSignalModel> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void addTrades(List<OrderSignalModel> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void showSnackbarMessage(String message);

	void showTradeDetails(OrderSignalModel trade, Boolean showSwaps, Boolean showTickets);
}
