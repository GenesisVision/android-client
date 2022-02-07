package vision.genesis.clientapp.feature.main.terminal.open_orders;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import vision.genesis.clientapp.model.terminal.binance_api.BinanceOrder;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */

interface OpenOrdersView extends MvpView
{
	void setOrders(List<BinanceOrder> orders);

	void showSnackbarMessage(String message);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);
}
