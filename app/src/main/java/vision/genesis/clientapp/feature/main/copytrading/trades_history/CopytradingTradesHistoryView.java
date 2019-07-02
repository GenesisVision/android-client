package vision.genesis.clientapp.feature.main.copytrading.trades_history;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.OrderSignalModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

interface CopytradingTradesHistoryView extends MvpView
{
	void setTrades(List<OrderSignalModel> trades);

	void addTrades(List<OrderSignalModel> newTrades);

	void showProgressBar(boolean show);

	void showEmpty(boolean show);

	void showSnackbarMessage(String message);
}
