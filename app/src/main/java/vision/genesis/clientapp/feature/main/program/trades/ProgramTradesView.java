package vision.genesis.clientapp.feature.main.program.trades;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.OrderModel;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVision
 * Created by Vitaly on 4/1/18.
 */

interface ProgramTradesView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setDateRange(DateRange dateRange);

	void setTrades(List<OrderModel> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void addTrades(List<OrderModel> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void showSnackbarMessage(String message);

	void showTradeDetails(OrderModel trade, Boolean showSwaps, Boolean showTickets);
}
