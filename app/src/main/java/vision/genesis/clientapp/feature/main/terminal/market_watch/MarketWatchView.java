package vision.genesis.clientapp.feature.main.terminal.market_watch;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

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

	void showClearButton(boolean show);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}