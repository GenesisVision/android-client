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
import java.util.List;

import io.swagger.client.api.TradingplatformApi;
import io.swagger.client.model.BinanceRawExchangeInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import rx.Observable;
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

	public static final String STREAM_TICKER = "ws/!ticker@arr";

	private final BinanceApi binanceApi;

	private final TradingplatformApi tradingplatformApi;

	private final OkHttpClient socketClient = new OkHttpClient();

	private BehaviorSubject<List<MiniTickerModel>> miniTickersSubject = BehaviorSubject.create();

	private BehaviorSubject<List<TickerModel>> tickersSubject = BehaviorSubject.create();

	private List<Pair<String, WebSocket>> sockets = new ArrayList<>();

	public TerminalManager(TradingplatformApi tradingplatformApi, BinanceApi binanceApi) {
		this.tradingplatformApi = tradingplatformApi;
		this.binanceApi = binanceApi;
	}

	public Observable<BinanceRawExchangeInfo> getBinanceServerInfo() {
		return tradingplatformApi.getExchangeInfo();
	}

	public Observable<List<TickerPriceModel>> getTickersPrices() {
		return binanceApi.getTickersPrices();
	}

	public BehaviorSubject<List<MiniTickerModel>> getMiniTickersSubject() {
		openSocketMaybe(STREAM_MINITICKER);
		return miniTickersSubject;
	}

	public BehaviorSubject<List<TickerModel>> getTickersSubject() {
		openSocketMaybe(STREAM_TICKER);
		return tickersSubject;
	}

	private void openSocketMaybe(String streamName) {
		boolean socketIsOpened = false;
		for (Pair<String, WebSocket> pair : sockets) {
			if (pair.first.equals(streamName)) {
				socketIsOpened = true;
				break;
			}
		}
		if (!socketIsOpened) {
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
				removeSocketFromList(webSocket);
			}

			@Override
			public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
				Timber.d("SOCKET %s onFailure", streamName);
				super.onFailure(webSocket, t, response);
				removeSocketFromList(webSocket);
			}
		});
	}

	private void addSocketToList(String streamName, WebSocket webSocket) {
		sockets.add(new Pair<>(streamName, webSocket));
	}

	private void parseSocketMessage(String streamName, String message) {
		switch (streamName) {
			case STREAM_MINITICKER:
				parseMiniTickerData(message);
				break;
			case STREAM_TICKER:
				parseTickerData(message);
				break;
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

	private void parseTickerData(String data) {
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

	private void closeSocketIfNoSubscribers(String streamName) {
		switch (streamName) {
			case STREAM_MINITICKER:
				if (!miniTickersSubject.hasObservers()) {
					closeSocket(streamName);
				}
				break;
			case STREAM_TICKER:
				if (!tickersSubject.hasObservers()) {
					closeSocket(streamName);
				}
				break;
		}
	}

	private void closeSocket(String streamName) {
		for (Pair<String, WebSocket> pair : sockets) {
			if (pair.first.equals(streamName)) {
				pair.second.cancel();
				break;
			}
		}
	}

	private void removeSocketFromList(WebSocket webSocket) {
		for (Pair<String, WebSocket> pair : sockets) {
			if (pair.second.equals(webSocket)) {
				sockets.remove(pair);
				break;
			}
		}
	}
}