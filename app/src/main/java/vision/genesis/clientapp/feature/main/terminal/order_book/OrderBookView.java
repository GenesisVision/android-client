package vision.genesis.clientapp.feature.main.terminal.order_book;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.BinanceOrderSide;
import io.swagger.client.model.BinanceRawFuturesOrder;
import io.swagger.client.model.BinanceRawFuturesOrderItemsViewModel;
import io.swagger.client.model.BinanceRawOrder;
import io.swagger.client.model.BinanceRawOrderItemsViewModel;
import io.swagger.client.model.TradingAccountPermission;
import rx.BackpressureOverflow;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.BinanceExchangeInfo;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceOrder;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceRawSymbol;
import vision.genesis.clientapp.model.terminal.binance_api.DepthListModel;
import vision.genesis.clientapp.model.terminal.binance_socket.DepthUpdateModel;
import vision.genesis.clientapp.model.terminal.binance_socket.FuturesAccountModel;
import vision.genesis.clientapp.model.terminal.binance_socket.FuturesOrderModel;
import vision.genesis.clientapp.model.terminal.binance_socket.SpotAccountModel;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.DepthPairsPriceComparator;
import vision.genesis.clientapp.utils.NumberFormatUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/02/2021.
 */

public class OrderBookView extends RelativeLayout
{
	private static final Integer LIMIT = 500;

	private static final int TICK_SIZE_COUNT = 4;

	protected static Integer VIEWS_COUNT = 15;

	@Inject
	public TerminalManager terminalManager;

	@BindView(R.id.text_tick_size)
	public TextView tickSizeText;

	@BindView(R.id.group_bids)
	public LinearLayout bidsGroup;

	@BindView(R.id.group_asks)
	public LinearLayout asksGroup;

	public Subscription getSymbolInfoSubscription;

	public Subscription getDepthSubscription;

	public Subscription depthUpdateSubscription;

	public Subscription getOrdersSubscription;

	public Subscription ordersUpdateSubscription;

	protected ArrayList<DepthItemView> asksViews = new ArrayList<>();

	protected String symbol = "";

	private FragmentActivity activity;

	private ArrayList<String> tickSizeOptions = new ArrayList<>();

	private Integer selectedTickSizePosition = -1;

	private Unbinder unbinder;

	private ArrayList<DepthItemView> bidsViews = new ArrayList<>();

	private HashMap<String, String> asks = new HashMap<>();

	private HashMap<String, String> bids = new HashMap<>();

	private ArrayList<DepthUpdateModel> updatesBuffer = new ArrayList<>();

	private ArrayList<Double> tickSizes = new ArrayList<>();

	private Long lastUpdateId = 0L;

	private BinanceRawSymbol symbolInfo;

	private Double selectedTickSize = 1.0;

	private int qtyDigits = 0;

	private UUID accountId;

	private List<BinanceOrder> orders = new ArrayList<>();

	private TradingAccountPermission currentMarket = TradingAccountPermission.SPOT;

	public OrderBookView(Context context) {
		super(context);
		initView();
	}

	public OrderBookView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public OrderBookView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.group_tick_size)
	public void onTickSizeClicked() {
		handleTickSizeClick();
	}

	private void handleTickSizeClick() {
		if (activity != null && tickSizeOptions != null && tickSizeOptions.size() > 1) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					null, tickSizeOptions, selectedTickSizePosition);
			fragment.setListener(this::onTickSizeOptionSelected);
			fragment.show(activity.getSupportFragmentManager(), fragment.getTag());
		}
	}

	private void initView() {
		inflateView();

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		onSymbolChanged();
	}

	protected void inflateView() {
		inflate(getContext(), R.layout.view_order_book, this);
	}

	public void onDestroy() {
		unsubscribeFromUpdates();

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void unsubscribeFromUpdates() {
		if (getSymbolInfoSubscription != null) {
			getSymbolInfoSubscription.unsubscribe();
		}
		if (getDepthSubscription != null) {
			getDepthSubscription.unsubscribe();
		}
		if (depthUpdateSubscription != null) {
			depthUpdateSubscription.unsubscribe();
		}
		if (getOrdersSubscription != null) {
			getOrdersSubscription.unsubscribe();
		}
		if (ordersUpdateSubscription != null) {
			ordersUpdateSubscription.unsubscribe();
		}
	}

	public void onResume() {
		if (depthUpdateSubscription == null || depthUpdateSubscription.isUnsubscribed()) {
			onSymbolChanged();
		}
	}

	public void setActivity(FragmentActivity activity) {
		this.activity = activity;
	}

	public void setData(String symbol, @Nullable UUID accountId) {
		this.symbol = symbol;
		this.accountId = accountId;
		onSymbolChanged();
	}

	protected void onSymbolChanged() {
		unsubscribeFromUpdates();
		this.asks = new HashMap<>();
		this.bids = new HashMap<>();
		this.updatesBuffer = new ArrayList<>();
		lastUpdateId = 0L;
		this.symbolInfo = null;
		getSymbolInfo();
	}

	private void getSymbolInfo() {
		if (symbol != null && !symbol.isEmpty() && terminalManager != null) {
			if (getSymbolInfoSubscription != null) {
				getSymbolInfoSubscription.unsubscribe();
			}
			getSymbolInfoSubscription = terminalManager.getBinanceServerInfo()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetSymbolInfoSuccess,
							this::handleGetSymbolInfoError);
		}
	}

	private void handleGetSymbolInfoSuccess(BinanceExchangeInfo response) {
		getSymbolInfoSubscription.unsubscribe();

		for (BinanceRawSymbol symbolInfo : terminalManager.getCurrentSymbolsShortened()) {
			if (symbolInfo.getName().equals(symbol)) {
				this.symbolInfo = symbolInfo;
				this.qtyDigits = getDigitsCount(symbolInfo.getLotSizeFilter().getMinQuantity());
				break;
			}
		}

		if (this.symbolInfo != null) {
			initTickSizes();
			getDepth();
			subscribeToDepthUpdates();
			getOrders();
		}
	}

	private void handleGetSymbolInfoError(Throwable throwable) {
		getSymbolInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, this::showToast);
	}

	private void initTickSizes() {
		double startTickSize = symbolInfo.getPriceFilter().getTickSize();
		for (int i = 0; i < TICK_SIZE_COUNT; i++) {
//			tickSizes.add((new BigDecimal(startTickSize)).multiply(BigDecimal.valueOf(Math.pow(10, i))).floatValue());
//			tickSizes.add((startTickSize * Math.pow(10, i)));
			tickSizes.add(NumberFormatUtil.multipleDouble(startTickSize, Math.pow(10, i)));
			tickSizeOptions.add(StringFormatUtil.formatAmount(tickSizes.get(i), 0, 8));
		}
		onTickSizeOptionSelected(0, tickSizeOptions.get(0));
	}

	private void onTickSizeOptionSelected(Integer position, String text) {
		this.selectedTickSize = tickSizes.get(position);
		this.tickSizeText.setText(text);
		this.selectedTickSizePosition = position;

		updateView();
	}

	private void getOrders() {
		if (terminalManager != null && accountId != null) {
			if (getOrdersSubscription != null) {
				getOrdersSubscription.unsubscribe();
			}
			currentMarket = terminalManager.getCurrentMarket();
			if (currentMarket.equals(TradingAccountPermission.SPOT)) {
				getOrdersSubscription = terminalManager.getSpotOpenOrders(accountId)
						.observeOn(AndroidSchedulers.mainThread())
						.subscribeOn(Schedulers.io())
						.subscribe(this::handleGetSpotOrdersResponse,
								error -> {
								});
			}
			else if (currentMarket.equals(TradingAccountPermission.FUTURES)) {
				getOrdersSubscription = terminalManager.getFuturesOpenOrders(accountId)
						.observeOn(AndroidSchedulers.mainThread())
						.subscribeOn(Schedulers.io())
						.subscribe(this::handleGetFuturesOrdersResponse,
								error -> {
								});
			}
		}
	}

	private void handleGetSpotOrdersResponse(BinanceRawOrderItemsViewModel model) {
		getOrdersSubscription.unsubscribe();

		orders.clear();
		List<BinanceOrder> newOrders = new ArrayList<>();
		for (BinanceRawOrder item : model.getItems()) {
			newOrders.add(BinanceOrder.from(item));
		}
		orders.addAll(newOrders);

		updateBooksWithOrders();

		subscribeToOpenOrdersUpdates();
	}

	private void handleGetFuturesOrdersResponse(BinanceRawFuturesOrderItemsViewModel model) {
		getOrdersSubscription.unsubscribe();

		orders.clear();
		List<BinanceOrder> newOrders = new ArrayList<>();
		for (BinanceRawFuturesOrder item : model.getItems()) {
			newOrders.add(BinanceOrder.from(item));
		}
		orders.addAll(newOrders);

		updateBooksWithOrders();

		subscribeToOpenOrdersUpdates();
	}

	private void subscribeToOpenOrdersUpdates() {
		if (currentMarket.equals(TradingAccountPermission.SPOT)) {
			subscribeToSpotOrdersUpdate();
		}
		else if (currentMarket.equals(TradingAccountPermission.FUTURES)) {
			subscribeToFuturesOrdersUpdate();
		}
	}

	private void subscribeToSpotOrdersUpdate() {
		if (terminalManager != null) {
			if (ordersUpdateSubscription != null) {
				ordersUpdateSubscription.unsubscribe();
			}
			ordersUpdateSubscription = terminalManager.getSpotAccountSubject(accountId)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleSpotOrdersUpdate, error -> {
					});
		}
	}

	private void handleSpotOrdersUpdate(SpotAccountModel model) {
		if ("executionReport".equals(model.getEventType())) {
			switch (model.getExecutionType()) {
				case NEW:
					orders.add(0, model.toBinanceOrder());
					break;
				case TRADE:
					for (int i = 0; i < orders.size(); i++) {
						if (orders.get(i).getOrderId().equals(model.getOrderId())) {
							orders.remove(i);
							if (!Objects.equals(model.getQuantity(), model.getQuantityFilled())) {
								orders.add(i, model.toBinanceOrder());
							}
							break;
						}
					}
					break;
				case CANCELED:
				case EXPIRED:
					for (BinanceOrder order : orders) {
						if (order.getOrderId().equals(model.getOrderId())) {
							orders.remove(order);
							break;
						}
					}
					break;
			}

			updateBooksWithOrders();
		}
	}

	private void subscribeToFuturesOrdersUpdate() {
		if (terminalManager != null) {
			if (ordersUpdateSubscription != null) {
				ordersUpdateSubscription.unsubscribe();
			}
			ordersUpdateSubscription = terminalManager.getFuturesAccountSubject(accountId)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleFuturesOrdersUpdate, error -> {
					});
		}
	}

	private void handleFuturesOrdersUpdate(FuturesAccountModel model) {
		if ("ORDER_TRADE_UPDATE".equals(model.getEventType())) {
			FuturesOrderModel order = model.getOrder();
			switch (order.getExecutionType()) {
				case NEW:
					orders.add(0, model.getOrder().toBinanceOrder());
					break;
				case TRADE:
					for (int i = 0; i < orders.size(); i++) {
						if (orders.get(i).getOrderId().equals(order.getOrderId())) {
							orders.remove(i);
							if (!Objects.equals(order.getQuantity(), order.getQuantityFilled())) {
								orders.add(i, order.toBinanceOrder());
							}
							break;
						}
					}
					break;
				case CANCELED:
				case EXPIRED:
					for (BinanceOrder binanceOrder : orders) {
						if (binanceOrder.getOrderId().equals(order.getOrderId())) {
							orders.remove(binanceOrder);
							break;
						}
					}
					break;
			}

			updateBooksWithOrders();
		}
	}

	private void updateBooksWithOrders() {
		for (DepthItemView view : asksViews) {
			view.clearOrders();
		}
		for (DepthItemView view : bidsViews) {
			view.clearOrders();
		}

		for (BinanceOrder order : orders) {
			if (order.getSide().equals(BinanceOrderSide.SELL)) {
				updateBookWithOrders(asksViews, order);
			}
			else if (order.getSide().equals(BinanceOrderSide.BUY)) {
				updateBookWithOrders(bidsViews, order);
			}
		}
	}

	private void updateBookWithOrders(ArrayList<DepthItemView> views, BinanceOrder order) {
		if (!views.isEmpty()) {
			for (int i = 0; i < views.size(); i++) {
				if (views.get(i).getPrice() == null) {
					continue;
				}
				if (views.get(i).getPrice() <= order.getPrice()
						&& views.get(i).getPrice() + selectedTickSize > order.getPrice()) {
					views.get(i).addOrder(order);
					return;
				}
			}
		}
	}

	private void getDepth() {
		if (symbol != null && !symbol.isEmpty() && terminalManager != null) {
			if (getDepthSubscription != null) {
				getDepthSubscription.unsubscribe();
			}
			getDepthSubscription = terminalManager.getDepth(symbol, LIMIT)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetDepthSuccess,
							this::handleGetDepthError);
		}
	}

	private void handleGetDepthSuccess(DepthListModel response) {
		getDepthSubscription.unsubscribe();

		lastUpdateId = response.getLastUpdateId();

		for (List<String> ask : response.getAsks()) {
			asks.put(ask.get(0), ask.get(1));
		}
		for (List<String> bid : response.getBids()) {
			bids.put(bid.get(0), bid.get(1));
		}
		processUpdate(updatesBuffer);

		updateView();
	}

	private void handleGetDepthError(Throwable throwable) {
		getDepthSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, this::showToast);
	}

	private void showToast(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
	}

	private void subscribeToDepthUpdates() {
		if (terminalManager != null && symbol != null && !symbol.isEmpty()) {
			if (depthUpdateSubscription != null) {
				depthUpdateSubscription.unsubscribe();
			}
			depthUpdateSubscription = terminalManager.getDepthSubject(symbol)
					.subscribeOn(Schedulers.newThread())
					.onBackpressureBuffer(10, () -> {
					}, BackpressureOverflow.ON_OVERFLOW_DROP_OLDEST)
					.onBackpressureDrop()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::onDepthUpdate, this::onDepthUpdateError);
		}
	}

	private void onDepthUpdate(DepthUpdateModel update) {
		updatesBuffer.add(update);
		if (getDepthSubscription != null && getDepthSubscription.isUnsubscribed()) {
			processUpdate(updatesBuffer);
			updateView();
			updatesBuffer = new ArrayList<>();
		}
	}

	private void processUpdate(ArrayList<DepthUpdateModel> updates) {
		for (DepthUpdateModel update : updates) {
			if (update.getFinalUpdateId() < lastUpdateId) {
				continue;
			}
			updateMap(asks, update.getAsks());
			updateMap(bids, update.getBids());

			lastUpdateId = update.getFinalUpdateId();
		}
	}

	private void updateMap(HashMap<String, String> map, List<List<String>> updates) {
		for (List<String> update : updates) {
			try {
				if (Double.parseDouble(update.get(1)) == 0f) {
					map.remove(update.get(0));
				}
				else {
					map.put(update.get(0), update.get(1));
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	private void onDepthUpdateError(Throwable throwable) {
		throwable.printStackTrace();
	}

	private void updateView() {
		if (asksViews.isEmpty() || bidsViews.isEmpty()) {
			createViews();
		}

		List<Pair<Double, Double>> asksList = parseMapToList(asks, false);
		List<Pair<Double, Double>> bidsList = parseMapToList(bids, true);

		Collections.sort(asksList, new DepthPairsPriceComparator());
		Collections.sort(bidsList, new DepthPairsPriceComparator());
		Collections.reverse(bidsList);

		asksList = getListForViews(asksList);
		bidsList = getListForViews(bidsList);

		Double asksTotalVolume = getTotalVolume(asksList);
		Double bidsTotalVolume = getTotalVolume(bidsList);
		Double maxTotalVolume = Math.max(asksTotalVolume, bidsTotalVolume);

		List<Double> asksFillsList = getFillsList(asksList, asksTotalVolume, maxTotalVolume);
		List<Double> bidsFillsList = getFillsList(bidsList, bidsTotalVolume, maxTotalVolume);

		int priceDigits = getDigitsCount(selectedTickSize);
		for (int i = 0; i < VIEWS_COUNT; i++) {
			setAsk(asksList, asksFillsList, i, priceDigits);
			setBid(bidsList, bidsFillsList, i, priceDigits);
		}

		updateBooksWithOrders();
	}

	private void createViews() {
		asksGroup.removeAllViews();
		bidsGroup.removeAllViews();

		asksViews = new ArrayList<>();
		bidsViews = new ArrayList<>();

		for (int i = 0; i < VIEWS_COUNT; i++) {
			DepthItemView askView = createDepthItemView(DepthItemView.TYPE_ASK);
			asksViews.add(askView);
			addAskView(askView);

			DepthItemView bidView = createDepthItemView(DepthItemView.TYPE_BID);
			bidsViews.add(bidView);
			bidsGroup.addView(bidView);
		}
	}

	protected void addAskView(DepthItemView askView) {
		asksGroup.addView(askView);
	}

	protected DepthItemView createDepthItemView(String type) {
		DepthItemView view = new DepthItemView(getContext());
		view.initView(type, false);
		return view;
	}

	private List<Pair<Double, Double>> parseMapToList(HashMap<String, String> map, Boolean isFloor) {
		List<Pair<Double, Double>> list = new ArrayList<>();
		HashMap<Double, Double> tempMap = new HashMap<>();

		double scale = Math.pow(selectedTickSize, -1);
		double priceLevel;

		for (Map.Entry<String, String> entry : map.entrySet()) {
			try {
				double price = Double.parseDouble(entry.getKey());
				double amount = Double.parseDouble(entry.getValue());

				if (isFloor) {
					priceLevel = Math.floor(price * scale) / scale;
				}
				else {
					priceLevel = Math.ceil(price * scale) / scale;
				}
				Double amountLevel = tempMap.get(priceLevel);
				if (amountLevel == null) {
					amountLevel = 0.0;
				}
				tempMap.put(priceLevel, amount + amountLevel);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		for (Map.Entry<Double, Double> entry : tempMap.entrySet()) {
			list.add(new Pair<>(entry.getKey(), entry.getValue()));
		}
		return list;
	}

	private List<Pair<Double, Double>> getListForViews(List<Pair<Double, Double>> list) {
		return list.subList(0, Math.min(list.size(), VIEWS_COUNT));
	}

	private Double getTotalVolume(List<Pair<Double, Double>> list) {
		double totalVolume = 0.0;
		for (Pair<Double, Double> pair : list) {
			totalVolume += pair.second;
		}
		return totalVolume;
	}

	private List<Double> getFillsList(List<Pair<Double, Double>> list, Double totalVolume, Double maxTotalVolume) {
		List<Double> fillsList = new ArrayList<>();
		fillsList.add(totalVolume / maxTotalVolume);
		for (int i = list.size() - 2; i >= 0; i--) {
			totalVolume -= list.get(i + 1).second;
			fillsList.add(0, totalVolume / maxTotalVolume);
		}
		return fillsList;
	}

	private int getDigitsCount(double size) {
		double log = Math.log10(size);
		return log > 0 ? 0 : (int) Math.abs(log);
	}

	private void setAsk(List<Pair<Double, Double>> asksList, List<Double> asksFillsList, int index, int priceDigits) {
		Pair<Double, Double> ask = index < asksList.size() ? asksList.get(index) : null;
		Double fill = index < asksFillsList.size() ? asksFillsList.get(index) : 0.0;
		asksViews.get(index).setData(ask, fill, priceDigits, qtyDigits);
	}

	private void setBid(List<Pair<Double, Double>> bidsList, List<Double> bidsFillsList, int index, int priceDigits) {
		Pair<Double, Double> bid = index < bidsList.size() ? bidsList.get(index) : null;
		Double fill = index < bidsFillsList.size() ? bidsFillsList.get(index) : 0.0;
		bidsViews.get(index).setData(bid, fill, priceDigits, qtyDigits);
	}
}
