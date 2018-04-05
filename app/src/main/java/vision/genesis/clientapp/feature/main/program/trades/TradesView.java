package vision.genesis.clientapp.feature.main.program.trades;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.OrderModel;
import io.swagger.client.model.TradesViewModel;

/**
 * GenesisVision
 * Created by Vitaly on 4/1/18.
 */

interface TradesView extends MvpView
{
	void setRefreshing(boolean refreshing);

	void setTrades(List<OrderModel> trades, TradesViewModel.TradeServerTypeEnum tradeServerType);

	void addTrades(List<OrderModel> trades);

	void showSnackbarMessage(String message);

	void setTradeServerType(TradesViewModel.TradeServerTypeEnum tradeServerType);
}
