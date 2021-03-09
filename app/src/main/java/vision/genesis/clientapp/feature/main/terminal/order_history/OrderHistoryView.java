package vision.genesis.clientapp.feature.main.terminal.order_history;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.BinanceRawOrder;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */

interface OrderHistoryView extends MvpView
{
	void setDateRange(DateRange dateRange);

	void setOrders(List<BinanceRawOrder> orders);

	void addOrders(List<BinanceRawOrder> orders);

	void showSnackbarMessage(String message);

	void showOrderDetails(BinanceRawOrder order);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);
}
