package vision.genesis.clientapp.feature.main.copytrading.trades_history;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.OrderSignalModel;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

interface CopytradingTradesHistoryView extends MvpView
{
	void setTrades(List<OrderSignalModel> trades);

	void addTrades(List<OrderSignalModel> newTrades);

	void showCommissions(OrderSignalModel trade);

	void showProgress(boolean show);

	void showEmpty(boolean show);

	void showSnackbarMessage(String message);

	void setDateRange(DateRange dateRange);
}
