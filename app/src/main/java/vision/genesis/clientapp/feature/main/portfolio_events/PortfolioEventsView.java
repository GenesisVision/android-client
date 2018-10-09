package vision.genesis.clientapp.feature.main.portfolio_events;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.PortfolioEvent;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/08/2018.
 */

interface PortfolioEventsView extends MvpView
{
	void setDateRange(DateRange dateRange);

	void setEvents(List<PortfolioEvent> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void addEvents(List<PortfolioEvent> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void showSnackbarMessage(String message);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);
}
