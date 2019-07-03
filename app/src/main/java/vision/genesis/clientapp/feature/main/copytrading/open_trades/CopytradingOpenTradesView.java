package vision.genesis.clientapp.feature.main.copytrading.open_trades;

import com.arellomobile.mvp.MvpView;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.OrderSignalModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

interface CopytradingOpenTradesView extends MvpView
{
	void showProgressBar(boolean show);

	void showEmpty(boolean show);

	void showSnackbarMessage(String message);

	void setOpenTrades(List<OrderSignalModel> trades);

	void removeOpenTrade(int position, boolean isListEmpty);

	void askCloseTrade(UUID tradeId, String symbol, Double volume);
}
