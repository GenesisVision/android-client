package vision.genesis.clientapp.managers;

import android.util.Pair;

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

import io.swagger.client.api.TradingplatformApi;
import io.swagger.client.model.BinanceRawExchangeInfo;
import io.swagger.client.model.BinanceRawKlineInterval;
import io.swagger.client.model.BinanceRawKlineItemsViewModel;
import io.swagger.client.model.BinanceRawSymbol;
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
import vision.genesis.clientapp.model.terminal.binance_api.DepthListModel;
import vision.genesis.clientapp.model.terminal.binance_api.TickerPriceModel;
import vision.genesis.clientapp.model.terminal.binance_api.TradeModel;
import vision.genesis.clientapp.model.terminal.binance_socket.DepthUpdateModel;
import vision.genesis.clientapp.model.terminal.binance_socket.KlineModel;
import vision.genesis.clientapp.model.terminal.binance_socket.KlinePayloadModel;
import vision.genesis.clientapp.model.terminal.binance_socket.NewTradeModel;
import vision.genesis.clientapp.model.terminal.binance_socket.TickerModel;
import vision.genesis.clientapp.net.api.BinanceApi;
import vision.genesis.clientapp.utils.BinanceRawKlineIntervalUtil;

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

	private static final int MESSAGES_TO_CHECK_SOCKET_FOR_CLOSING = 50;

	private final BinanceApi binanceApi;

	private final TradingplatformApi tradingplatformApi;

	private final OkHttpClient socketClient = new OkHttpClient();

	private Subscription getServerInfoSubscription;

	private BehaviorSubject<BinanceRawExchangeInfo> binanceServerInfoBehaviorSubject;

	private BehaviorSubject<List<TickerModel>> tickersSubject = BehaviorSubject.create();

	private HashMap<String, BehaviorSubject<TickerModel>> tickerSubjectsMap = new HashMap<>();

	private HashMap<String, BehaviorSubject<KlineModel>> klineSubjectsMap = new HashMap<>();

	private HashMap<String, BehaviorSubject<DepthUpdateModel>> depthUpdateSubjectsMap = new HashMap<>();

	private HashMap<String, BehaviorSubject<NewTradeModel>> tradeSubjectsMap = new HashMap<>();

	private HashMap<String, WebSocket> sockets = new HashMap<>();

	public TerminalManager(TradingplatformApi tradingplatformApi, BinanceApi binanceApi) {
		this.tradingplatformApi = tradingplatformApi;
		this.binanceApi = binanceApi;
	}

	public Observable<BinanceRawExchangeInfo> getBinanceServerInfo() {
		if (binanceServerInfoBehaviorSubject == null) {
			binanceServerInfoBehaviorSubject = BehaviorSubject.create();
			getServerInfoSubscription = tradingplatformApi.getExchangeInfo()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetServerInfoSuccess,
							this::handleGetServerInfoError);
		}
		return binanceServerInfoBehaviorSubject;
	}

	private void handleGetServerInfoSuccess(BinanceRawExchangeInfo response) {
		getServerInfoSubscription.unsubscribe();
		binanceServerInfoBehaviorSubject.onNext(response);
	}

	private void handleGetServerInfoError(Throwable error) {
		if (binanceServerInfoBehaviorSubject != null) {
			getServerInfoSubscription.unsubscribe();
			binanceServerInfoBehaviorSubject.onError(error);
			binanceServerInfoBehaviorSubject = null;
		}
	}

	public Observable<BinanceRawKlineItemsViewModel> getKlineData(String symbol, BinanceRawKlineInterval interval, DateTime startTime, DateTime endTime, Integer limit) {
		return tradingplatformApi.getKlines(symbol, interval, startTime, endTime, limit);
	}

	public Observable<List<TickerPriceModel>> getTickersPrices() {
		return binanceApi.getTickersPrices();
	}

	public Observable<List<TradeModel>> getTrades(String symbol, Integer limit) {
		return binanceApi.getTrades(symbol, limit);
	}

	public Observable<DepthListModel> getDepth(String symbol, Integer limit) {
		return binanceApi.getDepth(symbol, limit);
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

	public BehaviorSubject<KlineModel> getKlineSubject(String symbol, BinanceRawKlineInterval interval) {
		String streamName = String.format(STREAM_KLINE, symbol.toLowerCase(), BinanceRawKlineIntervalUtil.toShortString(interval));
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

	private void openSocketMaybe(String streamName) {
		WebSocket socket = sockets.get(streamName);
		if (socket == null) {
			addSocketToList(streamName, openSocket(streamName));
		}
	}

	private WebSocket openSocket(String streamName) {
		Request request = new Request.Builder().url(BuildConfig.BINANCE_SOCKET_ADDRESS.concat(streamName)).build();
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
				Timber.d("SOCKET %s onMessage text", streamName);
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
		if (binanceServerInfoBehaviorSubject != null && binanceServerInfoBehaviorSubject.getValue() != null) {
			for (BinanceRawSymbol ticker : binanceServerInfoBehaviorSubject.getValue().getSymbols()) {
				if (ticker.getName().equals(symbol)) {
					return new Pair<>(ticker.getBaseAsset(), ticker.getQuoteAsset());
				}
			}
		}
		return null;
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
}