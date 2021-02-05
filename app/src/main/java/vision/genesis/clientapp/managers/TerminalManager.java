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
import vision.genesis.clientapp.model.terminal.binance_api.TickerPriceModel;
import vision.genesis.clientapp.model.terminal.binance_socket.MiniTickerModel;
import vision.genesis.clientapp.model.terminal.binance_socket.TickerModel;
import vision.genesis.clientapp.net.api.BinanceApi;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

public class TerminalManager
{
	public static final String STREAM_MINITICKER = "ws/!miniTicker@arr";

	public static final String STREAM_TICKERS = "ws/!ticker@arr";

	public static final String STREAM_TICKER = "ws/%s@ticker";

	private final BinanceApi binanceApi;

	private final TradingplatformApi tradingplatformApi;

	private final OkHttpClient socketClient = new OkHttpClient();

	private Subscription getServerInfoSubscription;

	private BehaviorSubject<BinanceRawExchangeInfo> binanceServerInfoBehaviorSubject;

	private BehaviorSubject<List<MiniTickerModel>> miniTickersSubject = BehaviorSubject.create();

	private BehaviorSubject<List<TickerModel>> tickersSubject = BehaviorSubject.create();

	private HashMap<String, BehaviorSubject<TickerModel>> tickerSubjectsMap = new HashMap<>();

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

	public Observable<List<TickerPriceModel>> getTickersPrices() {
		return binanceApi.getTickersPrices();
	}

	public BehaviorSubject<List<MiniTickerModel>> getMiniTickersSubject() {
		openSocketMaybe(STREAM_MINITICKER);
		return miniTickersSubject;
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

	private BehaviorSubject<TickerModel> getTickerSubjectFromMap(String streamName) {
		BehaviorSubject<TickerModel> subject = tickerSubjectsMap.get(streamName);
		if (subject == null) {
			subject = BehaviorSubject.create();
			tickerSubjectsMap.put(streamName, subject);
		}
		return subject;
	}

	private void openSocketMaybe(String streamName) {
		WebSocket socket = sockets.get(streamName);
		if (socket == null) {
			openSocket(streamName);
		}
	}

	private WebSocket openSocket(String streamName) {
		Request request = new Request.Builder().url(BuildConfig.BINANCE_SOCKET_ADDRESS.concat(streamName)).build();
		return socketClient.newWebSocket(request, new WebSocketListener()
		{
			@Override
			public void onOpen(WebSocket webSocket, Response response) {
				Timber.d("SOCKET %s onOpen %d", streamName, response.code());
				super.onOpen(webSocket, response);
				addSocketToList(streamName, webSocket);
			}

			@Override
			public void onMessage(WebSocket webSocket, String text) {
				Timber.d("SOCKET %s onMessage text", streamName);
				super.onMessage(webSocket, text);
				parseSocketMessage(streamName, text);
				closeSocketIfNoSubscribers(streamName);
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
		if (streamName.equals(STREAM_MINITICKER)) {
			parseMiniTickerData(message);
			return;
		}
		if (streamName.equals(STREAM_TICKERS)) {
			parseTickersData(message);
			return;
		}

		WebSocket socket = sockets.get(streamName);
		if (socket != null) {
			parseTickerData(streamName, message);
			return;
		}
	}

	private void parseMiniTickerData(String data) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(
						DateTime.class,
						(JsonDeserializer<DateTime>) (json, typeOfT, context) -> new DateTime(json.getAsLong())
				)
				.create();

		Type typeToken = new TypeToken<ArrayList<MiniTickerModel>>()
		{
		}.getType();

		List<MiniTickerModel> tickers = new ArrayList<>();
		try {
			tickers = gson.fromJson(data, typeToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (tickers != null && !tickers.isEmpty()) {
			miniTickersSubject.onNext(tickers);
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

		List<TickerModel> tickers = new ArrayList<>();
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

		TickerModel ticker = new TickerModel();
		try {
			ticker = gson.fromJson(data, typeToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ticker != null) {
			BehaviorSubject<TickerModel> subject = getTickerSubjectFromMap(streamName);
			if (subject != null) {
				subject.onNext(ticker);
			}
		}
	}

	private void closeSocketIfNoSubscribers(String streamName) {
		if (streamName.equals(STREAM_MINITICKER)) {
			if (!miniTickersSubject.hasObservers()) {
				closeSocket(streamName);
			}
			return;
		}
		if (streamName.equals(STREAM_TICKERS)) {
			if (!tickersSubject.hasObservers()) {
				closeSocket(streamName);
			}
			return;
		}
		BehaviorSubject<TickerModel> subject = tickerSubjectsMap.get(streamName);
		if (subject != null) {
			if (!subject.hasObservers()) {
				closeSocket(streamName);
			}
			return;
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
}