package vision.genesis.clientapp.feature.main.terminal.market_watch;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
import vision.genesis.clientapp.utils.Constants;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

@InjectViewState
public class MarketWatchPresenter extends MvpPresenter<MarketWatchView>
{
	static final String SORTING_SYMBOL = "sorting_symbol";

	static final String SORTING_VOLUME = "sorting_volume";

	static final String SORTING_PRICE = "sorting_price";

	static final String SORTING_CHANGE = "sorting_change";

	private static final String KEY_FAVS = "key_favs";

	private static final String KEY_BTC = "key_btc";

	private static final String KEY_BNB = "key_bnb";

	private static final String KEY_ALTS = "key_alts";

	private static final String KEY_FIATS = "key_fiats";

	private static int sortingDirection = 1; // 1 == ASC; -1 == DESC

	private final Comparator<MarketWatchTickerModel> symbolsComparator =
			(Comparator<MarketWatchTickerModel>) (t1, t2) -> (t1.getSymbol().compareToIgnoreCase(t2.getSymbol()) * sortingDirection);

	private final Comparator<MarketWatchTickerModel> volumeComparator =
			(Comparator<MarketWatchTickerModel>) (t1, t2) -> (t1.getVolume().compareTo(t2.getVolume()) * sortingDirection);

	private final Comparator<MarketWatchTickerModel> priceComparator =
			(Comparator<MarketWatchTickerModel>) (t1, t2) -> (t1.getLastPrice().compareTo(t2.getLastPrice()) * sortingDirection);

	private final Comparator<MarketWatchTickerModel> changeComparator =
			(Comparator<MarketWatchTickerModel>) (t1, t2) -> (t1.getChange().compareTo(t2.getChange()) * sortingDirection);

	@Inject
	public Context context;

	@Inject
	public TerminalManager terminalManager;

	private Subscription serverInfoSubscription;

	private Subscription tickersPricesSubscription;

	private Subscription tickersUpdateSubscription;

	private HashMap<String, MarketWatchTickerModel> tickers = new HashMap<>();

	private String mask;

	private String currentSorting = SORTING_SYMBOL;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

//		subscribeToTickersUpdate();
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
		if (tickersUpdateSubscription == null || tickersUpdateSubscription.isUnsubscribed()) {
			subscribeToTickersUpdate();
		}
		if ((tickers == null || tickers.isEmpty())) {
			getServerInfo();
		}
	}

	void onPause() {
		if (tickersUpdateSubscription != null) {
			tickersUpdateSubscription.unsubscribe();
		}
	}

	void onTickersMaskChanged(String mask) {
		this.mask = mask;
		if (mask != null && tickers != null) {
			onTickersGetUpdateSuccess(getTickersListsFitMask());
		}
		getViewState().showClearButton(mask != null && !mask.isEmpty());
	}

	void onSortSymbolClicked() {
		changeSorting(SORTING_SYMBOL);
	}

	void onSortVolumeClicked() {
		changeSorting(SORTING_VOLUME);
	}

	void onSortPriceClicked() {
		changeSorting(SORTING_PRICE);
	}

	void onSortChangeClicked() {
		changeSorting(SORTING_CHANGE);
	}

	private void changeSorting(String newSorting) {
		sortingDirection = this.currentSorting.equals(newSorting)
				? sortingDirection * -1
				: 1;
		this.currentSorting = newSorting;

		getViewState().updateSorting(currentSorting, sortingDirection);

		onTickersGetUpdateSuccess(getTickersListsFitMask());
	}

	private void getServerInfo() {
		if (terminalManager != null) {
			if (serverInfoSubscription != null) {
				serverInfoSubscription.unsubscribe();
			}
			serverInfoSubscription = terminalManager.getBinanceServerInfo()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetServerInfoSuccess,
							this::handleGetServerInfoError);
		}
	}

	private void handleGetServerInfoSuccess(BinanceRawExchangeInfo info) {
		serverInfoSubscription.unsubscribe();

		tickers = new HashMap<>();

		for (BinanceRawSymbol symbol : info.getSymbols()) {
			if (symbol.getStatus().equals(BinanceSymbolStatus.TRADING)) {
				tickers.put(symbol.getName(), MarketWatchTickerModel.from(symbol));
			}
		}

		getTickersPrices();
	}

	private void handleGetServerInfoError(Throwable throwable) {
		serverInfoSubscription.unsubscribe();

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
			updateLastPrice(tickers, tickerPriceModel);
		}
	}

	private void updateLastPrice(HashMap<String, MarketWatchTickerModel> hashMap, TickerPriceModel model) {
		MarketWatchTickerModel ticker = hashMap.get(model.getSymbol());
		if (ticker != null) {
			ticker.setLastPrice(model.getPrice());
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
					.map(this::updateTickers)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::onTickersGetUpdateSuccess,
							this::onTickersGetUpdateError);
		}
	}

	private HashMap<String, ArrayList<MarketWatchTickerModel>> updateTickers(List<TickerModel> response) {
		if (tickers != null && !tickers.isEmpty()) {
			for (TickerModel update : response) {
				updateTicker(tickers, update);
			}

			return getTickersListsFitMask();
		}
		return null;
	}

	private HashMap<String, ArrayList<MarketWatchTickerModel>> getTickersListsFitMask() {
		if (tickers != null && !tickers.isEmpty()) {
			ArrayList<MarketWatchTickerModel> tickersList = new ArrayList<>(tickers.values());
			ArrayList<MarketWatchTickerModel> favsList = new ArrayList<>();
			ArrayList<MarketWatchTickerModel> btcList = new ArrayList<>();
			ArrayList<MarketWatchTickerModel> bnbList = new ArrayList<>();
			ArrayList<MarketWatchTickerModel> altsList = new ArrayList<>();
			ArrayList<MarketWatchTickerModel> fiatsList = new ArrayList<>();

			sortTickersList(tickersList);

			for (MarketWatchTickerModel ticker : tickersList) {
				if (ticker.getQuoteAsset().equals("BTC")) {
					addToListIfFitMask(ticker, btcList);
				}
				else if (ticker.getQuoteAsset().equals("BNB")) {
					addToListIfFitMask(ticker, bnbList);
				}
				else if (Constants.getAltsQuoteAssets().contains(ticker.getQuoteAsset())) {
					addToListIfFitMask(ticker, altsList);
				}
				else if (Constants.getFiatsQuoteAssets().contains(ticker.getQuoteAsset())) {
					addToListIfFitMask(ticker, fiatsList);
				}
			}

			HashMap<String, ArrayList<MarketWatchTickerModel>> result = new HashMap<>();
			result.put(KEY_FAVS, favsList);
			result.put(KEY_BTC, btcList);
			result.put(KEY_BNB, bnbList);
			result.put(KEY_ALTS, altsList);
			result.put(KEY_FIATS, fiatsList);

			return result;
		}
		return null;
	}

	private void addToListIfFitMask(MarketWatchTickerModel ticker, ArrayList<MarketWatchTickerModel> list) {
		if (mask == null || mask.isEmpty() || ticker.getSymbol().toLowerCase().contains(mask.toLowerCase())) {
			list.add(ticker);
		}
	}

	private void onTickersGetUpdateSuccess(HashMap<String, ArrayList<MarketWatchTickerModel>> updatedLists) {
		if (updatedLists != null) {
			getViewState().updateTickers(updatedLists.get(KEY_FAVS),
					updatedLists.get(KEY_BTC),
					updatedLists.get(KEY_BNB),
					updatedLists.get(KEY_ALTS),
					updatedLists.get(KEY_FIATS));
		}
	}

	private void updateTicker(HashMap<String, MarketWatchTickerModel> hashMap, TickerModel update) {
		MarketWatchTickerModel ticker = hashMap.get(update.getSymbol());
		if (ticker != null) {
			if (ticker.getLastPrice() != null) {
				ticker.setPreviousPrice(ticker.getLastPrice());
			}
			ticker.setLastPrice(update.getLastPrice());
			ticker.setChange(update.getPriceChangePercent());
			ticker.setVolume(update.getTotalTradedBaseAssetVolume());
		}
	}

	private void sortTickersList(ArrayList<MarketWatchTickerModel> list) {
		Comparator<MarketWatchTickerModel> comparator;
		switch (this.currentSorting) {
			default:
			case SORTING_SYMBOL:
				comparator = symbolsComparator;
				break;
			case SORTING_VOLUME:
				comparator = volumeComparator;
				break;
			case SORTING_PRICE:
				comparator = priceComparator;
				break;
			case SORTING_CHANGE:
				comparator = changeComparator;
				break;
		}
		Collections.sort(list, comparator);
	}

	private void onTickersGetUpdateError(Throwable throwable) {
	}
}