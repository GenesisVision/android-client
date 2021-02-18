package vision.genesis.clientapp.feature.main.terminal.order_book;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.BackpressureOverflow;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceRawExchangeInfo;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceRawSymbol;
import vision.genesis.clientapp.model.terminal.binance_api.DepthListModel;
import vision.genesis.clientapp.model.terminal.binance_socket.DepthUpdateModel;
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

	private static final Integer VIEWS_COUNT = 15;

	private static final int TICK_SIZE_COUNT = 4;

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

	private FragmentActivity activity;

	private ArrayList<String> tickSizeOptions = new ArrayList<>();

	private Integer selectedTickSizePosition = -1;

	private Unbinder unbinder;

	private String symbol = "";

	private HashMap<String, String> asks = new HashMap<>();

	private HashMap<String, String> bids = new HashMap<>();

	private ArrayList<DepthUpdateModel> updatesBuffer = new ArrayList<>();

	private ArrayList<DepthItemView> asksViews = new ArrayList<>();

	private ArrayList<DepthItemView> bidsViews = new ArrayList<>();

	private ArrayList<Double> tickSizes = new ArrayList<>();

	private Long lastUpdateId = 0L;

	private BinanceRawSymbol symbolInfo;

	private Double selectedTickSize = 1.0;

	private int qtyDigits = 0;

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
	public void onCurrencyClicked() {
		if (activity != null && tickSizeOptions != null && tickSizeOptions.size() > 1) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					null, tickSizeOptions, selectedTickSizePosition);
			fragment.setListener(this::onTickSizeOptionSelected);
			fragment.show(activity.getSupportFragmentManager(), fragment.getTag());
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_order_book, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		onSymbolChanged();
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
	}

	public void onResume() {
		if (depthUpdateSubscription == null || depthUpdateSubscription.isUnsubscribed()) {
			onSymbolChanged();
		}
	}

	public void setActivity(FragmentActivity activity) {
		this.activity = activity;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
		onSymbolChanged();
	}

	private void onSymbolChanged() {
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

	private void handleGetSymbolInfoSuccess(BinanceRawExchangeInfo response) {
		getSymbolInfoSubscription.unsubscribe();

		for (BinanceRawSymbol symbolInfo : response.getSymbols()) {
			if (symbolInfo.getName().equals(symbol)) {
				this.symbolInfo = symbolInfo;
				this.qtyDigits = getDigitsCount(symbolInfo.getLotSizeFilter().getMinQty());
				break;
			}
		}

		if (this.symbolInfo != null) {
			initTickSizes();
			getDepth();
			subscribeToDepthUpdates();
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
	}

	private void createViews() {
		asksGroup.removeAllViews();
		bidsGroup.removeAllViews();


		asksViews = new ArrayList<>();
		bidsViews = new ArrayList<>();

		for (int i = 0; i < VIEWS_COUNT; i++) {
			DepthItemView askView = new DepthItemView(getContext());
			askView.initView(DepthItemView.TYPE_ASK);
			asksViews.add(askView);
			asksGroup.addView(askView);

			DepthItemView bidView = new DepthItemView(getContext());
			bidView.initView(DepthItemView.TYPE_BID);
			bidsViews.add(bidView);
			bidsGroup.addView(bidView);
		}
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
