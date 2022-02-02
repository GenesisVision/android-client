package vision.genesis.clientapp.managers;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;

import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.swagger.client.api.DashboardApi;
import io.swagger.client.api.ExchangesApi;
import io.swagger.client.api.TradingplatformApi;
import io.swagger.client.model.BinanceExecutionType;
import io.swagger.client.model.BinanceKlineInterval;
import io.swagger.client.model.BinanceOrderSide;
import io.swagger.client.model.BinanceOrderStatus;
import io.swagger.client.model.BinanceOrderType;
import io.swagger.client.model.BinanceRaw24HPrice;
import io.swagger.client.model.BinanceRawAccountInfo;
import io.swagger.client.model.BinanceRawCancelOrder;
import io.swagger.client.model.BinanceRawExchangeInfo;
import io.swagger.client.model.BinanceRawFutures24HPrice;
import io.swagger.client.model.BinanceRawFuturesUsdtExchangeInfo;
import io.swagger.client.model.BinanceRawFuturesUsdtSymbol;
import io.swagger.client.model.BinanceRawKlineItemsViewModel;
import io.swagger.client.model.BinanceRawOrder;
import io.swagger.client.model.BinanceRawOrderItemsViewModel;
import io.swagger.client.model.BinanceRawPlaceOrder;
import io.swagger.client.model.BinanceRawPlacedOrder;
import io.swagger.client.model.BrokerTradeServerType;
import io.swagger.client.model.Currency;
import io.swagger.client.model.ExchangeAccountType;
import io.swagger.client.model.ExchangeAsset;
import io.swagger.client.model.ExchangeAssetItemsViewModel;
import io.swagger.client.model.ExchangeInfo;
import io.swagger.client.model.ExchangeInfoItemsViewModel;
import io.swagger.client.model.TradingAccountPermission;
import io.swagger.client.model.TradingPlatformBinanceOrdersMode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import timber.log.Timber;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.model.BinanceExchangeInfo;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceRawSymbol;
import vision.genesis.clientapp.model.terminal.binance_api.DepthListModel;
import vision.genesis.clientapp.model.terminal.binance_api.TradeModel;
import vision.genesis.clientapp.model.terminal.binance_socket.AccountModel;
import vision.genesis.clientapp.model.terminal.binance_socket.DepthUpdateModel;
import vision.genesis.clientapp.model.terminal.binance_socket.KlineModel;
import vision.genesis.clientapp.model.terminal.binance_socket.KlinePayloadModel;
import vision.genesis.clientapp.model.terminal.binance_socket.NewTradeModel;
import vision.genesis.clientapp.model.terminal.binance_socket.TickerModel;
import vision.genesis.clientapp.net.api.BinanceApi;
import vision.genesis.clientapp.utils.BinanceKlineIntervalUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

public class TerminalManager
{
	public static final String STREAM_TICKERS = "ws/!ticker@arr";

	public static final String STREAM_TICKER = "ws/%s@ticker";

	public static final String STREAM_KLINE = "ws/%s@kline_%s";

	public static final String STREAM_DEPTH = "ws/%s@depth";

	public static final String STREAM_TRADE = "ws/%s@trade";

	public static final String STREAM_ACCOUNT = "ws/%s";

	private static final int MESSAGES_TO_CHECK_SOCKET_FOR_CLOSING = 50;

	private final BinanceApi binanceApi;

	private final ExchangesApi exchangesApi;

	private final DashboardApi dashboardApi;

	private final TradingplatformApi tradingplatformApi;

	private final OkHttpClient socketClient = new OkHttpClient();

	private Subscription getServerInfoSubscription;

	private Subscription getExchangesInfoSubscription;

	private Subscription getAccountsSubscription;

	private Subscription getAccountStreamSubscription;

	private BehaviorSubject<BinanceExchangeInfo> binanceServerInfoBehaviorSubject;

	private BehaviorSubject<List<TickerModel>> tickersSubject = BehaviorSubject.create();

	private BehaviorSubject<List<String>> favoriteTickersSubject = BehaviorSubject.create();

	private BehaviorSubject<ExchangeAsset> selectedAccountSubject = BehaviorSubject.create();

	private HashMap<String, BehaviorSubject<TickerModel>> tickerSubjectsMap = new HashMap<>();

	private HashMap<String, BehaviorSubject<KlineModel>> klineSubjectsMap = new HashMap<>();

	private HashMap<String, BehaviorSubject<DepthUpdateModel>> depthUpdateSubjectsMap = new HashMap<>();

	private HashMap<String, BehaviorSubject<NewTradeModel>> tradeSubjectsMap = new HashMap<>();

	private HashMap<UUID, BehaviorSubject<AccountModel>> accountSubjectsMap = new HashMap<>();

	private HashMap<String, WebSocket> sockets = new HashMap<>();

	private List<ExchangeInfo> exchanges = new ArrayList<>();

	private BehaviorSubject<List<ExchangeAsset>> accountsBehaviorSubject = BehaviorSubject.create();

	private HashMap<UUID, String> listenKeysMap = new HashMap<>();

	private HashMap<String, UUID> streamNameAccountIdMap = new HashMap<>();

	private TradingAccountPermission currentMarket = TradingAccountPermission.SPOT;


	public TerminalManager(TradingplatformApi tradingplatformApi, BinanceApi binanceApi, ExchangesApi exchangesApi, DashboardApi dashboardApi) {
		this.tradingplatformApi = tradingplatformApi;
		this.binanceApi = binanceApi;
		this.exchangesApi = exchangesApi;
		this.dashboardApi = dashboardApi;
	}

	public TradingAccountPermission getCurrentMarket() {
		return currentMarket;
	}

	public void setCurrentMarket(TradingAccountPermission newMarket) {
		this.currentMarket = newMarket;
		this.accountsBehaviorSubject = BehaviorSubject.create();
		this.selectedAccountSubject = BehaviorSubject.create();
		this.favoriteTickersSubject = BehaviorSubject.create();
		for (Map.Entry<String, WebSocket> stringWebSocketEntry : sockets.entrySet()) {
			closeSocket(stringWebSocketEntry.getKey());
		}
		this.sockets = new HashMap<>();
	}

	public Observable<BinanceExchangeInfo> getBinanceServerInfo() {
		if (currentMarket.equals(TradingAccountPermission.SPOT)) {
			getSpotServerInfo();
		}
		else if (currentMarket.equals(TradingAccountPermission.FUTURES)) {
			getFuturesServerInfo();
		}
		return binanceServerInfoBehaviorSubject;
	}

	private void getSpotServerInfo() {
		if (binanceServerInfoBehaviorSubject == null) {
			binanceServerInfoBehaviorSubject = BehaviorSubject.create();
		}
		if (binanceServerInfoBehaviorSubject.getValue() == null
				|| binanceServerInfoBehaviorSubject.getValue().getSpotInfo() == null) {
			getServerInfoSubscription = tradingplatformApi.getExchangeInfo()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetSpotServerInfoSuccess,
							this::handleGetServerInfoError);
		}
	}

	private void handleGetSpotServerInfoSuccess(BinanceRawExchangeInfo response) {
		getServerInfoSubscription.unsubscribe();
		BinanceExchangeInfo exchangeInfo = binanceServerInfoBehaviorSubject.getValue();
		if (exchangeInfo == null) {
			exchangeInfo = new BinanceExchangeInfo();
		}
		exchangeInfo.setSpotInfo(response);
		binanceServerInfoBehaviorSubject.onNext(exchangeInfo);
	}

	private void getFuturesServerInfo() {
		if (binanceServerInfoBehaviorSubject == null) {
			binanceServerInfoBehaviorSubject = BehaviorSubject.create();
		}
		if (binanceServerInfoBehaviorSubject.getValue() == null
				|| binanceServerInfoBehaviorSubject.getValue().getFuturesInfo() == null) {
			getServerInfoSubscription = tradingplatformApi.getFuturesExchangeInfo()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetFuturesServerInfoSuccess,
							this::handleGetServerInfoError);
		}
	}

	private void handleGetFuturesServerInfoSuccess(BinanceRawFuturesUsdtExchangeInfo response) {
		getServerInfoSubscription.unsubscribe();
		BinanceExchangeInfo exchangeInfo = binanceServerInfoBehaviorSubject.getValue();
		if (exchangeInfo == null) {
			exchangeInfo = new BinanceExchangeInfo();
		}
		exchangeInfo.setFuturesInfo(response);
		binanceServerInfoBehaviorSubject.onNext(exchangeInfo);
	}

	private void handleGetServerInfoError(Throwable error) {
		if (binanceServerInfoBehaviorSubject != null) {
			getServerInfoSubscription.unsubscribe();
			binanceServerInfoBehaviorSubject.onError(error);
			binanceServerInfoBehaviorSubject = null;
		}
	}

	public BehaviorSubject<List<ExchangeAsset>> getAccountsFor(BrokerTradeServerType exchangeName, TradingAccountPermission permission) {
		if (exchanges == null || exchanges.isEmpty()) {
			getExchangesInfoSubscription = exchangesApi.getExchanges()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleGetExchangesSuccess(response, exchangeName, permission),
							this::handleGetExchangesError);
		}
		else {
			getAccountsFor(findAccountTypeId(exchangeName, permission));
		}
		return accountsBehaviorSubject;
	}

	private void handleGetExchangesSuccess(ExchangeInfoItemsViewModel response, BrokerTradeServerType exchangeName, TradingAccountPermission permission) {
		getExchangesInfoSubscription.unsubscribe();

		exchanges = response.getItems();
		getAccountsFor(findAccountTypeId(exchangeName, permission));
	}

	private void handleGetExchangesError(Throwable error) {
		if (getExchangesInfoSubscription != null) {
			getExchangesInfoSubscription.unsubscribe();
			accountsBehaviorSubject.onError(error);
			accountsBehaviorSubject = null;
		}
	}

	private UUID findAccountTypeId(BrokerTradeServerType exchangeName, TradingAccountPermission permission) {
		if (exchanges != null) {
			for (ExchangeInfo exchange : exchanges) {
				for (ExchangeAccountType accountType : exchange.getAccountTypes()) {
					if (accountType.getType().equals(exchangeName) && accountType.getPermissions().contains(permission)) {
						return accountType.getId();
					}
				}
			}
		}
		return null;
	}

	private void getAccountsFor(UUID accountTypeId) {
		getAccountsSubscription = dashboardApi.getExchangeAccountsCredentials(accountTypeId)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetAccountsSuccess,
						this::handleGetAccountsError);
	}

	private void handleGetAccountsSuccess(ExchangeAssetItemsViewModel response) {
		getAccountsSubscription.unsubscribe();

		accountsBehaviorSubject.onNext(response.getItems());
	}

	private void handleGetAccountsError(Throwable error) {
		if (getAccountsSubscription != null) {
			getAccountsSubscription.unsubscribe();
			accountsBehaviorSubject.onError(error);
			accountsBehaviorSubject = null;
		}
	}

	public Observable<BinanceRawAccountInfo> getAccountDetails(UUID accountId, String currency) {
		return tradingplatformApi.getAccountInfo(accountId, Currency.fromValue(currency));
	}

	public Observable<BinanceRawOrderItemsViewModel> getOpenOrders(UUID accountId) {
		return tradingplatformApi.getOpenOrders(accountId);
	}

	public Observable<BinanceRawOrderItemsViewModel> getOrderHistory(@NonNull UUID accountId, TradingPlatformBinanceOrdersMode mode, @NonNull DateRange dateRange, String symbol, int skip, int take) {
		return tradingplatformApi.getTradesHistory(accountId, mode, dateRange.getFrom(), dateRange.getSelectedRange().equals(DateRange.DateRangeEnum.CUSTOM) ? dateRange.getTo() : null, symbol, skip, take);
	}

	public Observable<BinanceRawKlineItemsViewModel> getKlineData(String symbol, BinanceKlineInterval interval, DateTime startTime, DateTime endTime, Integer limit) {
		return currentMarket.equals(TradingAccountPermission.SPOT)
				? tradingplatformApi.getKlines(symbol, interval, startTime, endTime, limit)
				: tradingplatformApi.getFuturesKlines(symbol, interval, startTime, endTime, limit);
	}

	public BehaviorSubject<List<String>> getFavoriteTickers() {
		if (selectedAccountSubject.getValue() != null) {
			tradingplatformApi.getFavoriteSymbols(selectedAccountSubject.getValue().getId())
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(response -> {
								favoriteTickersSubject.onNext(response.getItems());
							},
							error -> {
							});
		}
		return favoriteTickersSubject;
	}

	public void addFavoriteTicker(String symbol) {
		if (selectedAccountSubject.getValue() != null) {
			tradingplatformApi.addFavoriteSymbol(selectedAccountSubject.getValue().getId(), symbol)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(
							response -> getFavoriteTickers(),
							error -> {
							}
					);
		}
	}

	public void removeFavoriteTicker(String symbol) {
		if (selectedAccountSubject.getValue() != null) {
			tradingplatformApi.removeFavoriteSymbol(selectedAccountSubject.getValue().getId(), symbol)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(
							response -> getFavoriteTickers(),
							error -> {
							}
					);
		}
	}

	public BehaviorSubject<ExchangeAsset> subscribeToSelectedAccount() {
		return selectedAccountSubject;
	}

	public void setSelectedAccount(ExchangeAsset selectedAccount) {
		selectedAccountSubject.onNext(selectedAccount);
		getFavoriteTickers();
	}

	public Observable<List<BinanceRaw24HPrice>> getSpotTickersPrices() {
		return tradingplatformApi.get24HPrices();
	}

	public Observable<List<BinanceRawFutures24HPrice>> getFuturesTickersPrices() {
		return tradingplatformApi.getFutures24HPrices("");
	}

	public Observable<List<TradeModel>> getTrades(String symbol, Integer limit) {
		return binanceApi.getTrades(symbol, limit);
	}

	public Observable<DepthListModel> getDepth(String symbol, Integer limit) {
		return binanceApi.getDepth(symbol, limit);
	}

	public Observable<BinanceRawPlacedOrder> placeOrder(BinanceRawPlaceOrder body, UUID accountId) {
		return tradingplatformApi.placeOrder(body, accountId);
	}

	public Observable<BinanceRawCancelOrder> cancelOrder(BinanceRawOrder order) {
		return tradingplatformApi.cancelOrder(order.getAccountId(), order.getSymbol(), String.valueOf(order.getOrderId()));
	}


	public BehaviorSubject<List<TickerModel>> getTickersSubject() {
		openSocketMaybe(STREAM_TICKERS);
		return tickersSubject;
	}

	public BehaviorSubject<TickerModel> getTickerSubject(String symbol) {
		String streamName = String.format(STREAM_TICKER, symbol.toLowerCase());
		openSocketMaybe(streamName);
		return getTickerSubjectFromMap(streamName);
	}

	public BehaviorSubject<KlineModel> getKlineSubject(String symbol, BinanceKlineInterval interval) {
		String streamName = String.format(STREAM_KLINE, symbol.toLowerCase(), BinanceKlineIntervalUtil.toShortString(interval));
		openSocketMaybe(streamName);
		return getKlineSubjectFromMap(streamName);
	}

	public BehaviorSubject<DepthUpdateModel> getDepthSubject(String symbol) {
		String streamName = String.format(STREAM_DEPTH, symbol.toLowerCase());
		openSocketMaybe(streamName);
		return getDepthUpdateSubjectFromMap(streamName);
	}

	public BehaviorSubject<NewTradeModel> getTradeSubject(String symbol) {
		String streamName = String.format(STREAM_TRADE, symbol.toLowerCase());
		openSocketMaybe(streamName);
		return getTradeSubjectFromMap(streamName);
	}

	public BehaviorSubject<AccountModel> getAccountSubject(UUID accountId) {
		if (listenKeysMap.get(accountId) == null) {
			getListenKeyAndOpenAccountSocket(accountId);
		}
		else {
			openAccountSocket(accountId);
		}
		return getAccountSubjectFromMap(accountId);
	}

	private void getListenKeyAndOpenAccountSocket(UUID accountId) {
		if (getAccountStreamSubscription != null) {
			getAccountStreamSubscription.unsubscribe();
		}
		getAccountStreamSubscription = tradingplatformApi.startAccountStream(accountId)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(response -> handleGetAccountStreamSuccess(accountId, response),
						this::handleGetAccountStreamError);
	}

	private void handleGetAccountStreamSuccess(UUID accountId, String listenKey) {
		getAccountStreamSubscription.unsubscribe();
		listenKeysMap.put(accountId, listenKey);
		openAccountSocket(accountId);
	}

	private void handleGetAccountStreamError(Throwable error) {
		if (getAccountStreamSubscription != null) {
			getAccountStreamSubscription.unsubscribe();
		}
	}

	private void openAccountSocket(UUID accountId) {
		if (listenKeysMap.get(accountId) != null) {
			String streamName = String.format(STREAM_ACCOUNT, listenKeysMap.get(accountId));
			openSocketMaybe(streamName);
			streamNameAccountIdMap.put(streamName, accountId);
		}
	}

	private BehaviorSubject<TickerModel> getTickerSubjectFromMap(String streamName) {
		BehaviorSubject<TickerModel> subject = tickerSubjectsMap.get(streamName);
		if (subject == null) {
			subject = BehaviorSubject.create();
			tickerSubjectsMap.put(streamName, subject);
		}
		return subject;
	}

	private BehaviorSubject<KlineModel> getKlineSubjectFromMap(String streamName) {
		BehaviorSubject<KlineModel> subject = klineSubjectsMap.get(streamName);
		if (subject == null) {
			subject = BehaviorSubject.create();
			klineSubjectsMap.put(streamName, subject);
		}
		return subject;
	}

	private BehaviorSubject<DepthUpdateModel> getDepthUpdateSubjectFromMap(String streamName) {
		BehaviorSubject<DepthUpdateModel> subject = depthUpdateSubjectsMap.get(streamName);
		if (subject == null) {
			subject = BehaviorSubject.create();
			depthUpdateSubjectsMap.put(streamName, subject);
		}
		return subject;
	}

	private BehaviorSubject<NewTradeModel> getTradeSubjectFromMap(String streamName) {
		BehaviorSubject<NewTradeModel> subject = tradeSubjectsMap.get(streamName);
		if (subject == null) {
			subject = BehaviorSubject.create();
			tradeSubjectsMap.put(streamName, subject);
		}
		return subject;
	}

	private BehaviorSubject<AccountModel> getAccountSubjectFromMap(UUID accountId) {
		BehaviorSubject<AccountModel> subject = accountSubjectsMap.get(accountId);
		if (subject == null) {
			subject = BehaviorSubject.create();
			accountSubjectsMap.put(accountId, subject);
		}
		return subject;
	}

	private void openSocketMaybe(String streamName) {
		WebSocket socket = sockets.get(streamName);
		if (socket == null) {
			addSocketToList(streamName, openSocket(streamName));
		}
	}

	private WebSocket openSocket(String streamName) {
		String socketAddress = currentMarket == TradingAccountPermission.SPOT
				? BuildConfig.BINANCE_SPOT_SOCKET_ADDRESS
				: BuildConfig.BINANCE_FUTURES_SOCKET_ADDRESS;
		Request request = new Request.Builder().url(socketAddress.concat(streamName)).build();
		return socketClient.newWebSocket(request, new WebSocketListener()
		{
			private int messagesCount = 0;

			@Override
			public void onOpen(WebSocket webSocket, Response response) {
				Timber.d("SOCKET %s onOpen %d", streamName, response.code());
				super.onOpen(webSocket, response);
//				addSocketToList(streamName, webSocket);
			}

			@Override
			public void onMessage(WebSocket webSocket, String text) {
//				Timber.d("SOCKET %s onMessage text", streamName);
				super.onMessage(webSocket, text);
				parseSocketMessage(streamName, text);

				messagesCount++;
				if (messagesCount > MESSAGES_TO_CHECK_SOCKET_FOR_CLOSING) {
					closeSocketIfNoSubscribers(streamName);
					messagesCount = 0;
				}
			}

			@Override
			public void onMessage(WebSocket webSocket, ByteString bytes) {
//				Timber.d("SOCKET onMessage bytes");
				super.onMessage(webSocket, bytes);
			}

			@Override
			public void onClosing(WebSocket webSocket, int code, String reason) {
//				Timber.d("SOCKET onClosing");
				super.onClosing(webSocket, code, reason);
			}

			@Override
			public void onClosed(WebSocket webSocket, int code, String reason) {
				Timber.d("SOCKET %s onClosed", streamName);
				super.onClosed(webSocket, code, reason);
				removeSocketFromList(streamName);
			}

			@Override
			public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
				Timber.d("SOCKET %s onFailure", streamName);
				super.onFailure(webSocket, t, response);
				removeSocketFromList(streamName);
			}
		});
	}

	private void addSocketToList(String streamName, WebSocket webSocket) {
		sockets.put(streamName, webSocket);
	}

	private void parseSocketMessage(String streamName, String message) {
		if (streamName.equals(STREAM_TICKERS)) {
			parseTickersData(message);
			return;
		}

		WebSocket socket = sockets.get(streamName);
		if (socket != null) {
			if (streamName.contains("@ticker")) {
				parseTickerData(streamName, message);
			}
			else if (streamName.contains("@kline")) {
				parseKlineData(streamName, message);
			}
			else if (streamName.contains("@depth")) {
				parseDepthData(streamName, message);
			}
			else if (streamName.contains("@trade")) {
				parseTradeData(streamName, message);
			}
			else {
				parseAccountData(streamName, message);
			}
		}
	}

	private void parseTickersData(String data) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(
						DateTime.class,
						(JsonDeserializer<DateTime>) (json, typeOfT, context) -> new DateTime(json.getAsLong())
				)
				.create();

		Type typeToken = new TypeToken<ArrayList<TickerModel>>()
		{
		}.getType();

		List<TickerModel> tickers = null;
		try {
			tickers = gson.fromJson(data, typeToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (tickers != null && !tickers.isEmpty()) {
			tickersSubject.onNext(tickers);
		}
	}

	private void parseTickerData(String streamName, String data) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(
						DateTime.class,
						(JsonDeserializer<DateTime>) (json, typeOfT, context) -> new DateTime(json.getAsLong())
				)
				.create();

		Type typeToken = new TypeToken<TickerModel>()
		{
		}.getType();

		TickerModel ticker = null;
		try {
			ticker = gson.fromJson(data, typeToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ticker != null) {
			BehaviorSubject<TickerModel> subject = getTickerSubjectFromMap(streamName);
			subject.onNext(ticker);
		}
	}

	private void parseKlineData(String streamName, String data) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(
						DateTime.class,
						(JsonDeserializer<DateTime>) (json, typeOfT, context) -> new DateTime(json.getAsLong())
				)
				.create();

		Type typeToken = new TypeToken<KlinePayloadModel>()
		{
		}.getType();

		KlinePayloadModel payload = null;
		try {
			payload = gson.fromJson(data, typeToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (payload != null && payload.getKline() != null) {
			BehaviorSubject<KlineModel> subject = getKlineSubjectFromMap(streamName);
			subject.onNext(payload.getKline());
		}
	}

	private void parseDepthData(String streamName, String data) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(
						DateTime.class,
						(JsonDeserializer<DateTime>) (json, typeOfT, context) -> new DateTime(json.getAsLong())
				)
				.create();

		Type typeToken = new TypeToken<DepthUpdateModel>()
		{
		}.getType();

		DepthUpdateModel model = null;
		try {
			model = gson.fromJson(data, typeToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (model != null) {
			BehaviorSubject<DepthUpdateModel> subject = getDepthUpdateSubjectFromMap(streamName);
			subject.onNext(model);
		}
	}

	private void parseTradeData(String streamName, String data) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(
						DateTime.class,
						(JsonDeserializer<DateTime>) (json, typeOfT, context) -> new DateTime(json.getAsLong())
				)
				.create();

		Type typeToken = new TypeToken<NewTradeModel>()
		{
		}.getType();

		NewTradeModel model = new NewTradeModel();
		try {
			model = gson.fromJson(data, typeToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (model != null) {
			BehaviorSubject<NewTradeModel> subject = getTradeSubjectFromMap(streamName);
			subject.onNext(model);
		}
	}

	private void parseAccountData(String streamName, String data) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(
						DateTime.class,
						(JsonDeserializer<DateTime>) (json, typeOfT, context) -> new DateTime(json.getAsLong())
				)
				.registerTypeAdapter(
						BinanceOrderType.class,
						(JsonDeserializer<BinanceOrderType>) (json, typeOfT, context) -> {
							if (json.getAsString().equals("TAKE_PROFIT_LIMIT")) {
								return BinanceOrderType.TAKEPROFITLIMIT;
							}
							else {
								return
										BinanceOrderType.valueOf(json.getAsString());
							}
						}
				)
				.registerTypeAdapter(
						BinanceOrderSide.class,
						(JsonDeserializer<BinanceOrderSide>) (json, typeOfT, context) -> BinanceOrderSide.valueOf(json.getAsString())
				)
				.registerTypeAdapter(
						BinanceExecutionType.class,
						(JsonDeserializer<BinanceExecutionType>) (json, typeOfT, context) -> BinanceExecutionType.valueOf(json.getAsString())
				)
				.registerTypeAdapter(
						BinanceOrderStatus.class,
						(JsonDeserializer<BinanceOrderStatus>) (json, typeOfT, context) -> BinanceOrderStatus.valueOf(json.getAsString())
				)
				.create();

		Type typeToken = new TypeToken<AccountModel>()
		{
		}.getType();

		AccountModel model = new AccountModel();
		try {
			model = gson.fromJson(data, typeToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UUID accountId = streamNameAccountIdMap.get(streamName);
		if (model != null && accountId != null) {
			BehaviorSubject<AccountModel> subject = getAccountSubjectFromMap(accountId);
			subject.onNext(model);
		}
	}

	private void closeSocketIfNoSubscribers(String streamName) {
		if (streamName.equals(STREAM_TICKERS)) {
			if (!tickersSubject.hasObservers()) {
				closeSocket(streamName);
			}
			return;
		}

		checkMapForSocketsToClose(tickerSubjectsMap, streamName);
		checkMapForSocketsToClose(klineSubjectsMap, streamName);
		checkMapForSocketsToClose(depthUpdateSubjectsMap, streamName);
		checkMapForSocketsToClose(tradeSubjectsMap, streamName);
//		BehaviorSubject<TickerModel> subject = tickerSubjectsMap.get(streamName);
//		if (subject != null) {
//			if (!subject.hasObservers()) {
//				closeSocket(streamName);
//			}
//		}
	}

	private void checkMapForSocketsToClose(HashMap map, String streamName) {
		BehaviorSubject subject = (BehaviorSubject) map.get(streamName);
		if (subject != null) {
			if (!subject.hasObservers()) {
				closeSocket(streamName);
			}
		}
	}

	private void closeSocket(String streamName) {
		WebSocket socket = sockets.get(streamName);
		if (socket != null) {
			socket.cancel();
		}
	}

	private void removeSocketFromList(String streamName) {
		sockets.remove(streamName);
	}

	public Pair<String, String> getBaseQuoteAssets(String symbol) {
		for (BinanceRawSymbol ticker : getCurrentSymbolsShortened()) {
			if (ticker.getName().equals(symbol)) {
				return new Pair<>(ticker.getBaseAsset(), ticker.getQuoteAsset());
			}
		}
		return null;
	}

	public Integer getSymbolPrecision(String symbol) {
		for (BinanceRawSymbol ticker : getCurrentSymbolsShortened()) {
			if (ticker.getName().equals(symbol)) {
				return (int) (Math.abs(Math.log10(ticker.getPriceFilter().getTickSize())));
			}
		}
		return null;
	}

	public List<BinanceRawSymbol> getCurrentSymbolsShortened() {
		List<BinanceRawSymbol> result = new ArrayList<>();
		if (binanceServerInfoBehaviorSubject != null && binanceServerInfoBehaviorSubject.getValue() != null) {
			if (currentMarket.equals(TradingAccountPermission.SPOT)) {
				for (io.swagger.client.model.BinanceRawSymbol symbol : binanceServerInfoBehaviorSubject.getValue().getSpotInfo().getSymbols()) {
					result.add(BinanceRawSymbol.from(symbol));
				}
			}
			else if (currentMarket.equals(TradingAccountPermission.FUTURES)) {
				for (BinanceRawFuturesUsdtSymbol symbol : binanceServerInfoBehaviorSubject.getValue().getFuturesInfo().getSymbols()) {
					result.add(BinanceRawSymbol.from(symbol));
				}
			}

		}
		return result;
	}

	public TickerModel getTickerData(String symbol) {
		if (tickersSubject != null && tickersSubject.getValue() != null) {
			for (TickerModel tickerModel : tickersSubject.getValue()) {
				if (tickerModel.getSymbol().equals(symbol)) {
					return tickerModel;
				}
			}
		}
		return null;
	}

	public void logout() {
		accountsBehaviorSubject = BehaviorSubject.create();
		selectedAccountSubject = BehaviorSubject.create();
		favoriteTickersSubject = BehaviorSubject.create();
		accountSubjectsMap = new HashMap<>();
	}
}