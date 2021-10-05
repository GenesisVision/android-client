package vision.genesis.clientapp.feature.main.terminal.market_watch;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.BrokerTradeServerType;
import io.swagger.client.model.Currency;
import io.swagger.client.model.ExchangeAccountType;
import io.swagger.client.model.ExchangeAsset;
import io.swagger.client.model.ExchangeInfo;
import io.swagger.client.model.ExchangeInfoItemsViewModel;
import io.swagger.client.model.TradingAccountPermission;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.terminal.select_account.SelectAccountBottomSheetFragment;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.BrokersManager;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.CreateAccountModel;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.OnCreateAccountSuccessEvent;
import vision.genesis.clientapp.model.events.OnFavoriteTickersSelectAccountClickedEvent;
import vision.genesis.clientapp.model.terminal.MarketWatchTickerModel;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceRawExchangeInfo;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceRawSymbol;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceSymbolStatus;
import vision.genesis.clientapp.model.terminal.binance_api.TickerPriceModel;
import vision.genesis.clientapp.model.terminal.binance_socket.TickerModel;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.Constants;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

@InjectViewState
public class MarketWatchPresenter extends MvpPresenter<MarketWatchView> implements SelectAccountBottomSheetFragment.OnAccountSelectedListener
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
	public AuthManager authManager;

	@Inject
	public TerminalManager terminalManager;

	@Inject
	public BrokersManager brokersManager;

	private Subscription userSubscription;

	private Subscription getAccountsSubscription;

	private Subscription selectedAccountSubscription;

	private Subscription serverInfoSubscription;

	private Subscription tickersPricesSubscription;

	private Subscription tickersUpdateSubscription;

	private Subscription getFavoritesSubscription;

	private Subscription getBrokersSubscription;

	private HashMap<String, MarketWatchTickerModel> tickers = new HashMap<>();

	private String mask;

	private String currentSorting = SORTING_SYMBOL;

	private ArrayList<ExchangeAsset> accounts = new ArrayList<>();

	private List<String> favoriteTickers = new ArrayList<>();

	private User user = null;

	private boolean isWaitingForNewAccount = false;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
		getServerInfo();
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}
		if (getAccountsSubscription != null) {
			getAccountsSubscription.unsubscribe();
		}
		if (selectedAccountSubscription != null) {
			selectedAccountSubscription.unsubscribe();
		}
		if (serverInfoSubscription != null) {
			serverInfoSubscription.unsubscribe();
		}
		if (tickersPricesSubscription != null) {
			tickersPricesSubscription.unsubscribe();
		}
		if (tickersUpdateSubscription != null) {
			tickersUpdateSubscription.unsubscribe();
		}
		if (getFavoritesSubscription != null) {
			getFavoritesSubscription.unsubscribe();
		}
		if (getBrokersSubscription != null) {
			getBrokersSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
		if (tickersUpdateSubscription == null || tickersUpdateSubscription.isUnsubscribed()) {
			subscribeToTickersUpdate();
		}
		if ((tickers == null || tickers.isEmpty())) {
			getServerInfo();
		}
		if (user != null && accounts.isEmpty()) {
			getAccounts();
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

	private void getAccounts() {
		if (terminalManager != null) {
			getAccountsSubscription = terminalManager.getAccountsFor(BrokerTradeServerType.BINANCE, TradingAccountPermission.SPOT)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetAccountsSuccess,
							this::handleGetAccountsError);
		}
	}

	private void handleGetAccountsSuccess(List<ExchangeAsset> response) {
		getAccountsSubscription.unsubscribe();

		this.accounts = new ArrayList<>(response);
		if (accounts.size() == 1) {
			isWaitingForNewAccount = false;
			onAccountSelected(accounts.get(0));
		}
		else if (accounts.isEmpty()) {
			getViewState().setButtonCreateAccount();
		}
		subscribeToSelectedAccount();
	}

	private void handleGetAccountsError(Throwable throwable) {
		getAccountsSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::handleUserUpdate, error -> this.user = null);
	}

	private void handleUserUpdate(User user) {
		this.user = user;
		if (user != null) {
			getAccounts();
			subscribeToFavoriteTickers();
		}
		else {
			if (getAccountsSubscription != null) {
				getAccountsSubscription.unsubscribe();
			}
			if (getFavoritesSubscription != null) {
				getFavoritesSubscription.unsubscribe();
			}
			this.favoriteTickers = new ArrayList<>();
		}
	}

	private void subscribeToSelectedAccount() {
		if (terminalManager != null) {
			if (selectedAccountSubscription != null) {
				selectedAccountSubscription.unsubscribe();
			}
			selectedAccountSubscription = terminalManager.subscribeToSelectedAccount()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleAccountChanged,
							error -> {
							});
		}
	}

	private void handleAccountChanged(ExchangeAsset account) {
		getViewState().showFavoriteTickersProgress();
	}

	@Override
	public void onAccountSelected(ExchangeAsset account) {
		if (terminalManager != null) {
			terminalManager.setSelectedAccount(account);
		}
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
			if (symbol.getStatus() != null && symbol.getStatus().equals(BinanceSymbolStatus.TRADING)) {
				tickers.put(symbol.getName(), MarketWatchTickerModel.from(symbol));
			}
		}

		getTickersPrices();
	}

	private void handleGetServerInfoError(Throwable throwable) {
		serverInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void subscribeToFavoriteTickers() {
		if (terminalManager != null) {
			if (getFavoritesSubscription != null) {
				getFavoritesSubscription.unsubscribe();
			}
			getFavoritesSubscription = terminalManager.getFavoriteTickers()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(
							response -> this.favoriteTickers = response,
							throwable -> ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message))
					);
		}
	}

	private void getTickersPrices() {
		if (terminalManager != null) {
			if (tickersPricesSubscription != null) {
				tickersPricesSubscription.unsubscribe();
			}
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
				if (favoriteTickers != null && favoriteTickers.contains(ticker.getSymbol())) {
					favsList.add(ticker);
				}
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

	@Subscribe
	public void onEventMainThread(OnFavoriteTickersSelectAccountClickedEvent event) {
		if (user == null) {
			getViewState().showLoginActivity();
		}
		else if (!accounts.isEmpty()) {
			getViewState().showSelectAccount(accounts);
		}
		else {
			showCreateAccount();
		}
	}

	@Subscribe
	public void onEventMainThread(OnCreateAccountSuccessEvent event) {
		if (isWaitingForNewAccount) {
			getViewState().showFavoriteTickersProgress();
			getViewState().showNewAccountProcessingDialog();
		}
	}

	private void showCreateAccount() {
		if (brokersManager != null) {
			if (getBrokersSubscription != null) {
				getBrokersSubscription.unsubscribe();
			}
			getBrokersSubscription = brokersManager.getExchanges()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetBrokersSuccess,
							this::handleGetBrokersError);
		}
	}

	private void handleGetBrokersSuccess(ExchangeInfoItemsViewModel response) {
		getBrokersSubscription.unsubscribe();
		if (!response.getItems().isEmpty()) {
			brokersLoop:
			for (ExchangeInfo exchange : response.getItems()) {
				for (ExchangeAccountType accountType : exchange.getAccountTypes()) {
					if (accountType.getType().equals(BrokerTradeServerType.BINANCE)) {
						CreateAccountModel model = new CreateAccountModel(
								exchange.getAccountTypes().get(0).getId(),
								exchange.getLogoUrl(),
								Currency.fromValue(exchange.getAccountTypes().get(0).getCurrencies().get(0)),
								1
						);
						getViewState().showCreateAccount(model);
						isWaitingForNewAccount = true;
						break brokersLoop;
					}
				}
			}
		}
	}

	private void handleGetBrokersError(Throwable throwable) {
		getBrokersSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}