package vision.genesis.clientapp.feature.main.copytrading.open_trade_details;

import com.arellomobile.mvp.MvpView;

import java.util.List;
import java.util.UUID;

import vision.genesis.clientapp.model.OpenTradeProviderModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

interface OpenTradeDetailsView extends MvpView
{
	void setOpenTrades(List<OpenTradeProviderModel> trades);

	void removeOpenTrade(int position, boolean isListEmpty);

	void askCloseTrade(UUID programId, String symbol, Double volume);

	void showSnackbarMessage(String message);

	void finishActivity();

	void showProgress(Boolean show);
}
