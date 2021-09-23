package vision.genesis.clientapp.feature.main.terminal.market_watch;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import io.swagger.client.model.ExchangeAsset;
import vision.genesis.clientapp.model.terminal.MarketWatchTickerModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

interface MarketWatchView extends MvpView
{
	void updateTickers(ArrayList<MarketWatchTickerModel> favsTickers,
	                   ArrayList<MarketWatchTickerModel> btcTickers,
	                   ArrayList<MarketWatchTickerModel> bnbTickers,
	                   ArrayList<MarketWatchTickerModel> altsTickers,
	                   ArrayList<MarketWatchTickerModel> fiatsTickers);

	void updateSorting(String currentSorting, int sortingDirection);

	void showSelectAccount(ArrayList<ExchangeAsset> accounts);

	void showClearButton(boolean show);

	void showFavoriteTickersProgress();

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
