package vision.genesis.clientapp.feature.main.terminal.market_watch;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import io.swagger.client.model.ExchangeAsset;
import io.swagger.client.model.TradingAccountPermission;
import vision.genesis.clientapp.model.CreateAccountModel;
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

	void setCurrentMarket(TradingAccountPermission currentMarket);

	void showSelectAccount(ArrayList<ExchangeAsset> accounts);

	void showCreateAccount(CreateAccountModel model);

	void showSearchFragment(boolean show);

	void setButtonCreateAccount();

	void showLoginActivity();

	void showFavoriteTickersProgress();

	void showNewAccountProcessingDialog();

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

}
