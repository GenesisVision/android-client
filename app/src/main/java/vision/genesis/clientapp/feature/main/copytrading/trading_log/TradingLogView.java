package vision.genesis.clientapp.feature.main.copytrading.trading_log;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.SignalTradingEvent;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/08/2019.
 */

interface TradingLogView extends MvpView
{
	void setEvents(List<SignalTradingEvent> newEvents, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void addEvents(List<SignalTradingEvent> newEvents, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void showProgress(boolean show);

	void showEmpty(boolean show);

	void showSnackbarMessage(String message);

	void setDateRange(DateRange dateRange);
}
