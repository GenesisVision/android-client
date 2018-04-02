package vision.genesis.clientapp.feature.main.program.trades;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.OrderModel;

/**
 * GenesisVision
 * Created by Vitaly on 4/1/18.
 */

interface TradesView extends MvpView
{
	void setRefreshing(boolean refreshing);

	void setTrades(List<OrderModel> trades);

	void addTrades(List<OrderModel> trades);

	void showSnackbarMessage(String message);
}
