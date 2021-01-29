package vision.genesis.clientapp.feature.main.terminal.market_watch;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.BinanceRawExchangeInfo;
import io.swagger.client.model.BinanceRawSymbol;
import io.swagger.client.model.BinanceSymbolStatus;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.terminal.MarketWatchTickerModel;
import vision.genesis.clientapp.model.terminal.binance_api.TickerPriceModel;
import vision.genesis.clientapp.model.terminal.binance_socket.TickerModel;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

@InjectViewState
public class MarketWatchPresenter extends MvpPresenter<MarketWatchView>
{
	@Inject
	public Context context;

	@Inject
	public TerminalManager terminalManager;

	private Subscription serverInfoSubscription;

	private Subscription tickersPricesSubscription;

	private Subscription tickersUpdateSubscription;

	private ArrayList<MarketWatchTickerModel> tickers;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToTickersUpdate();
		getServerInfo();
	}

	@Override
	public void onDestroy() {
		if (serverInfoSubscription != null) {
			serverInfoSubscription.unsubscribe();
		}
		if (tickersPricesSubscription != null) {
			tickersPricesSubscription.unsubscribe();
		}
		if (tickersUpdateSubscription != null) {
			tickersUpdateSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onResume() {
		if (tickers != null &&
				(tickersUpdateSubscription == null || tickersUpdateSubscription.isUnsubscribed())) {
			subscribeToTickersUpdate();
		}
	}

	void onPause() {
		if (tickersUpdateSubscription != null) {
			tickersUpdateSubscription.unsubscribe();
		}
	}

	private void getServerInfo() {
		if (terminalManager != null) {
			serverInfoSubscription = terminalManager.getBinanceServerInfo()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetServerInfoSuccess,
							this::handleGetServerInfoError);
		}
	}

	private void handleGetServerInfoSuccess(BinanceRawExchangeInfo info) {
		serverInfoSubscription.unsubscribe();

		tickers = new ArrayList<>();
		for (BinanceRawSymbol symbol : info.getSymbols()) {
			if (symbol.getStatus().equals(BinanceSymbolStatus.TRADING)) {
				tickers.add(MarketWatchTickerModel.from(symbol));
			}
		}
		getTickersPrices();
	}

	private void handleGetServerInfoError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void getTickersPrices() {
		if (terminalManager != null) {
			tickersPricesSubscription = terminalManager.getTickersPrices()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetTickersPricesSuccess,
							this::handleGetTickersPricesError);
		}
	}

	private void handleGetTickersPricesSuccess(List<TickerPriceModel> response) {
		tickersPricesSubscription.unsubscribe();
		getViewState().showProgress(false);

		for (TickerPriceModel tickerPriceModel : response) {
			for (MarketWatchTickerModel ticker : tickers) {
				if (ticker.getSymbol().equals(tickerPriceModel.getSymbol())) {
					ticker.setLastPrice(tickerPriceModel.getPrice());
					break;
				}
			}
		}
	}

	private void handleGetTickersPricesError(Throwable throwable) {
		tickersPricesSubscription.unsubscribe();
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void subscribeToTickersUpdate() {
		if (terminalManager != null) {
			if (tickersUpdateSubscription != null) {
				tickersUpdateSubscription.unsubscribe();
			}
			tickersUpdateSubscription = terminalManager.getTickersSubject()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::onTickersGetUpdateSuccess,
							this::onTickersGetUpdateError);
		}
	}

	private void onTickersGetUpdateSuccess(List<TickerModel> response) {
		if (tickers != null && !tickers.isEmpty()) {
			for (TickerModel update : response) {
				for (MarketWatchTickerModel ticker : tickers) {
					if (ticker.getSymbol().equals(update.getSymbol())) {
						ticker.setLastPrice(update.getLastPrice());
						ticker.setChange(update.getPriceChangePercent());
						ticker.setVolume(update.getTotalTradedBaseAssetVolume());
						break;
					}
				}
			}
			getViewState().updateTickers(tickers);
		}
	}

	private void onTickersGetUpdateError(Throwable throwable) {
		tickersUpdateSubscription.unsubscribe();
	}
}
