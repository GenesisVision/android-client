package vision.genesis.clientapp.feature.main.terminal.place_order;

import static vision.genesis.clientapp.feature.main.terminal.place_order.PlaceOrderActivity.OPERATION_TYPE_BUY;
import static vision.genesis.clientapp.feature.main.terminal.place_order.PlaceOrderActivity.OPERATION_TYPE_SELL;

import android.content.Context;
import android.util.Pair;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import io.swagger.client.model.BinanceOrderSide;
import io.swagger.client.model.BinanceOrderType;
import io.swagger.client.model.BinanceRawAccountInfo;
import io.swagger.client.model.BinanceRawBinanceBalance;
import io.swagger.client.model.BinanceRawPlaceOrder;
import io.swagger.client.model.BinanceRawPlacedOrder;
import io.swagger.client.model.BinanceRawSymbolLotSizeFilter;
import io.swagger.client.model.BinanceRawSymbolPriceFilter;
import io.swagger.client.model.BinanceTimeInForce;
import io.swagger.client.model.ExchangeAsset;
import io.swagger.client.model.TradingAccountPermission;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.BinanceExchangeInfo;
import vision.genesis.clientapp.model.events.SetOpenOrdersCountEvent;
import vision.genesis.clientapp.model.events.SetOrderHistoryCountEvent;
import vision.genesis.clientapp.model.events.SetTradeHistoryCountEvent;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceRawSymbol;
import vision.genesis.clientapp.model.terminal.binance_socket.AccountModel;
import vision.genesis.clientapp.model.terminal.binance_socket.BinanceAccountBalance;
import vision.genesis.clientapp.model.terminal.binance_socket.TickerModel;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.SelectPercentView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/02/2021.
 */

@InjectViewState
public class PlaceOrderPresenter extends MvpPresenter<PlaceOrderView> implements MiniOrderBookView.OnPriceSelectedListener, SelectPercentView.OnPercentChangeListener
{
	private static final int ORDER_TYPE_MARKET = 0;

	private static final int ORDER_TYPE_LIMIT = 1;

	private static final int ORDER_TYPE_STOP_LIMIT = 2;

	@Inject
	public Context context;

	@Inject
	public TerminalManager terminalManager;

	private Subscription getSymbolInfoSubscription;

	private Subscription getAccountInfoSubscription;

	private Subscription tickerUpdateSubscription;

	private Subscription accountUpdateSubscription;

	private Subscription placeOrderSubscription;

	private String symbol;

	private ExchangeAsset selectedAccount;

	private Integer orderTypePosition = -1;

	private Double currentTickerPrice = 0.0;

	private Double price = 0.0;

	private Double stop = 0.0;

	private Double limit = 0.0;

	private Double amount = 0.0;

	private Double total = 0.0;

	private int percent;

	private BinanceRawAccountInfo accountInfo;

	private String operationType = OPERATION_TYPE_BUY;

	private String baseAsset = "";

	private String quoteAsset = "";

	private String availableCurrency = "";

	private Double available = 0.0;

	private boolean isStopHasFocus = false;

	private boolean isLimitHasFocus = false;

	private BinanceRawSymbol symbolInfo;

	private int amountStepSize = 8;

	private int priceStepSize = 2;

	private TradingAccountPermission currentMarket = TradingAccountPermission.SPOT;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		initOrderTypeOptions();
		getAccountInfo();
		getSymbolInfo();
		getBaseQuoteAssets();
		subscribeToTickerUpdates();
		subscribeToAccountUpdate();
	}

	@Override
	public void onDestroy() {
		if (getSymbolInfoSubscription != null) {
			getSymbolInfoSubscription.unsubscribe();
		}
		if (getAccountInfoSubscription != null) {
			getAccountInfoSubscription.unsubscribe();
		}
		if (tickerUpdateSubscription != null) {
			tickerUpdateSubscription.unsubscribe();
		}
		if (accountUpdateSubscription != null) {
			accountUpdateSubscription.unsubscribe();
		}
		if (placeOrderSubscription != null) {
			placeOrderSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	public void onResume() {
		if (tickerUpdateSubscription == null || tickerUpdateSubscription.isUnsubscribed()) {
			subscribeToTickerUpdates();
		}
	}

	private void subscribeToTickerUpdates() {
		if (terminalManager != null && symbol != null) {
			if (tickerUpdateSubscription != null) {
				tickerUpdateSubscription.unsubscribe();
			}
			tickerUpdateSubscription = terminalManager.getTickerSubject(symbol)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleTickerUpdate, error -> {
					});
		}
	}

	private void handleTickerUpdate(TickerModel model) {
		this.currentTickerPrice = model.getLastPrice();

		if (orderTypePosition.equals(ORDER_TYPE_MARKET)) {
			onPriceSelected(currentTickerPrice);
		}
	}

	private void subscribeToAccountUpdate() {
		if (terminalManager != null && selectedAccount != null) {
			if (accountUpdateSubscription != null) {
				accountUpdateSubscription.unsubscribe();
			}
			accountUpdateSubscription = terminalManager.getAccountSubject(selectedAccount.getId())
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleAccountUpdate, error -> {
					});
		}
	}

	private void handleAccountUpdate(AccountModel model) {
		if ("outboundAccountPosition".equals(model.getEventType())) {
			for (BinanceRawBinanceBalance balance : accountInfo.getBalances()) {
				for (BinanceAccountBalance modelBalance : model.getBalances()) {
					if (modelBalance.getAsset().equals(balance.getAsset())) {
						balance.setFree(modelBalance.getFree());
						balance.setLocked(modelBalance.getLocked());
						break;
					}
				}
			}
			updateAvailable();
		}
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

		currentMarket = terminalManager.getCurrentMarket();

		if (currentMarket.equals(TradingAccountPermission.SPOT)) {
			for (BinanceRawSymbol symbolInfo : terminalManager.getCurrentSymbolsShortened()) {
				if (symbolInfo.getName().equals(symbol)) {
					this.symbolInfo = symbolInfo;
					setPriceFilter(symbolInfo.getPriceFilter());
					setLotFilter(symbolInfo.getLotSizeFilter());
					break;
				}
			}
		}
	}

	private void setPriceFilter(BinanceRawSymbolPriceFilter filter) {
		this.priceStepSize = filter.getTickSize() != null ? getDigitsCount(filter.getTickSize()) : 8;
		getViewState().setPriceFilter(filter.getMaxPrice(), priceStepSize);
	}

	private void setLotFilter(BinanceRawSymbolLotSizeFilter filter) {
		this.amountStepSize = filter.getStepSize() != null ? getDigitsCount(filter.getStepSize()) : 8;
		getViewState().setLotFilter(filter.getMaxQuantity(), amountStepSize);
	}

	private int getDigitsCount(double size) {
		double log = Math.log10(size);
		return log > 0 ? 0 : (int) Math.abs(log);
	}

	private void handleGetSymbolInfoError(Throwable throwable) {
		getSymbolInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void initOrderTypeOptions() {
		ArrayList<String> orderTypeOptions = new ArrayList<>();
		orderTypeOptions.add(context.getString(R.string.market).toUpperCase());
		orderTypeOptions.add(context.getString(R.string.limit).toUpperCase());
		orderTypeOptions.add(context.getString(R.string.stop_limit).toUpperCase());
		getViewState().setOrderTypeOptions(orderTypeOptions);
		onOrderTypeSelected(0, orderTypeOptions.get(0));
	}

	void setData(String selectedSymbol, ExchangeAsset selectedAccount, String operationType) {
		this.symbol = selectedSymbol;
		this.selectedAccount = selectedAccount;

		getBaseQuoteAssets();
		setOperationType(operationType);
		getSymbolInfo();
		getAccountInfo();
		getViewState().setSelectedSymbol(this.symbol);
		subscribeToAccountUpdate();
	}

	void onSelectBuyClicked() {
		setOperationType(OPERATION_TYPE_BUY);
	}

	void onSelectSellClicked() {
		setOperationType(OPERATION_TYPE_SELL);
	}

	private void setOperationType(String operationType) {
		this.operationType = operationType;
		getViewState().selectOperationType(operationType);

		clearInputs();
		updateAvailable();
	}

	private void clearInputs() {
		this.amount = 0.0;
		this.percent = 0;
		this.total = 0.0;

		updateAmount();
		updatePercent();
		updateTotal();
	}

	private void updateAvailable() {
		if (accountInfo != null) {
			available = 0.0;
			availableCurrency = operationType.equals(OPERATION_TYPE_BUY) ? quoteAsset : baseAsset;
			for (BinanceRawBinanceBalance balance : accountInfo.getBalances()) {
				if (balance.getAsset().equals(availableCurrency)) {
					available = balance.getFree();
					break;
				}
			}
			getViewState().setPercentSelectEnabled(available > 0);
			getViewState().setAvailable(StringFormatUtil.getValueString(available, availableCurrency));
		}
	}

	void onOrderTypeSelected(Integer position, String text) {
		orderTypePosition = position;
		getViewState().setOrderType(text, position);
		updateFieldsVisibility();
		if (orderTypePosition.equals(ORDER_TYPE_LIMIT)) {
			getViewState().setPrice(StringFormatUtil.formatAmountWithoutGrouping(currentTickerPrice, priceStepSize));
		}
//		updateButtonAvailability();
	}

	private void getBaseQuoteAssets() {
		if (symbol != null && terminalManager != null) {
			Pair<String, String> baseQuoteAssets = terminalManager.getBaseQuoteAssets(symbol);
			if (baseQuoteAssets != null) {
				this.baseAsset = baseQuoteAssets.first;
				this.quoteAsset = baseQuoteAssets.second;
				getViewState().setBaseQuoteAssets(baseAsset, quoteAsset);
			}
		}
	}

	private void getAccountInfo() {
		if (terminalManager != null && selectedAccount != null) {
			if (getAccountInfoSubscription != null) {
				getAccountInfoSubscription.unsubscribe();
			}
			getAccountInfoSubscription = terminalManager.getAccountDetails(selectedAccount.getId(), "USD")
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetAccountInfoSuccess,
							this::handleGetAccountInfoError);
		}
	}

	private void handleGetAccountInfoSuccess(BinanceRawAccountInfo response) {
		getAccountInfoSubscription.unsubscribe();

		this.accountInfo = response;
		updateAvailable();
	}

	private void handleGetAccountInfoError(Throwable throwable) {
		getAccountInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void updateFieldsVisibility() {
		switch (orderTypePosition) {
			default:
			case ORDER_TYPE_MARKET:
				getViewState().showMarketPrice(true);
				getViewState().showPrice(false);
				getViewState().showStop(false);
				getViewState().showLimit(false);
				break;
			case ORDER_TYPE_LIMIT:
				getViewState().showMarketPrice(false);
				getViewState().showPrice(true);
				getViewState().showStop(false);
				getViewState().showLimit(false);
				break;
			case ORDER_TYPE_STOP_LIMIT:
				getViewState().showMarketPrice(false);
				getViewState().showPrice(false);
				getViewState().showStop(true);
				getViewState().showLimit(true);
				break;
		}
	}

	@Override
	public void onPriceSelected(Double price) {
		this.price = price;
		getViewState().setPrice(StringFormatUtil.formatAmountWithoutGrouping(price, priceStepSize));
		if (isStopHasFocus) {
			getViewState().setStop(StringFormatUtil.formatAmountWithoutGrouping(price, priceStepSize));
		}
		if (isLimitHasFocus) {
			getViewState().setLimit(StringFormatUtil.formatAmountWithoutGrouping(price, priceStepSize));
		}
		onPriceUpdated();
	}

	void onStopFocusChange(boolean hasFocus) {
		this.isStopHasFocus = hasFocus;
	}

	void onLimitFocusChange(boolean hasFocus) {
		this.isLimitHasFocus = hasFocus;
	}

	void onPriceChanged(String newPriceString, boolean hasFocus) {
		if (newPriceString.startsWith("0") && !newPriceString.startsWith("0.") && newPriceString.length() > 1) {
			getViewState().setPrice("0");
			return;
		}
		try {
			Double newPrice = Double.parseDouble(newPriceString);
			if (price.equals(newPrice)) {
				return;
			}
			price = newPrice;
		} catch (NumberFormatException e) {
			price = 0.0;
		}

		if (hasFocus) {
			onPriceUpdated();
		}
	}

	private void onPriceUpdated() {
		total = amount * price;
		if (operationType.equals(OPERATION_TYPE_BUY)) {
			percent = available != 0 ? (int) (total / available * 100) : 0;
		}
		else if (operationType.equals(OPERATION_TYPE_SELL)) {
			percent = available != 0 ? (int) (amount / available * 100) : 0;
		}

		updateAmount();
		updatePercent();
		updateTotal();
	}

	void onStopChanged(String newStopString, boolean hasFocus) {
		if (newStopString.startsWith("0") && !newStopString.startsWith("0.") && newStopString.length() > 1) {
			getViewState().setStop("0");
			return;
		}
		try {
			Double newStop = Double.parseDouble(newStopString);
			if (stop.equals(newStop)) {
				return;
			}
			stop = newStop;
		} catch (NumberFormatException e) {
			stop = 0.0;
		}
	}

	void onLimitChanged(String newLimitString, boolean hasFocus) {
		if (newLimitString.startsWith("0") && !newLimitString.startsWith("0.") && newLimitString.length() > 1) {
			getViewState().setLimit("0");
			return;
		}
		try {
			Double newLimit = Double.parseDouble(newLimitString);
			if (limit.equals(newLimit)) {
				return;
			}
			limit = newLimit;
		} catch (NumberFormatException e) {
			limit = 0.0;
		}
	}

	void onAmountChanged(String newAmountString, boolean hasFocus) {
		if (newAmountString.startsWith("0") && !newAmountString.startsWith("0.") && newAmountString.length() > 1) {
			getViewState().setAmount("0");
			return;
		}
		try {
			Double newAmount = Double.parseDouble(newAmountString);
			if (amount.equals(newAmount)) {
				return;
			}
			amount = newAmount;
		} catch (NumberFormatException e) {
			amount = 0.0;
		}

		if (hasFocus) {
			total = amount * price;
			if (operationType.equals(OPERATION_TYPE_BUY)) {
				percent = available != 0 ? (int) (total / available * 100) : 0;
			}
			else if (operationType.equals(OPERATION_TYPE_SELL)) {
				percent = available != 0 ? (int) (amount / available * 100) : 0;
			}

			updatePercent();
			updateTotal();
		}
	}

	void onTotalChanged(String newTotalString, boolean hasFocus) {
		if (newTotalString.startsWith("0") && !newTotalString.startsWith("0.") && newTotalString.length() > 1) {
			getViewState().setTotal("0");
			return;
		}
		try {
			Double newTotal = Double.parseDouble(newTotalString);
			if (total.equals(newTotal)) {
				return;
			}
			total = newTotal;
		} catch (NumberFormatException e) {
			total = 0.0;
		}

		if (hasFocus) {
			if (price != 0) {
				amount = total / price;
				if (operationType.equals(OPERATION_TYPE_BUY)) {
					percent = available != 0 ? (int) (total / available * 100) : 0;
				}
				else if (operationType.equals(OPERATION_TYPE_SELL)) {
					percent = available != 0 ? (int) (amount / available * 100) : 0;
				}

				updateAmount();
				updatePercent();
			}
			getViewState().setTotalError(this.total > available);
		}
	}

	@Override
	public void onPercentChange(int percent, boolean fromUser) {
		if (fromUser) {
			this.percent = percent;
			if (price != 0) {
				if (operationType.equals(OPERATION_TYPE_BUY)) {
					total = available / 100 * percent;
					amount = total / price;
				}
				else if (operationType.equals(OPERATION_TYPE_SELL)) {
					amount = available / 100 * percent;
					total = price * amount;
				}

				updateAmount();
				updateTotal();
			}
			updatePercent();
		}
	}

	private void updateAmount() {
		getViewState().setAmount(this.amount != 0 ? StringFormatUtil.formatAmountWithoutGrouping(this.amount, amountStepSize) : "");
		getViewState().setAmountError(operationType.equals(OPERATION_TYPE_SELL) && this.amount > available);
	}

	private void updatePercent() {
		getViewState().setPercent(this.percent);
	}

	private void updateTotal() {
		getViewState().setTotal(this.total != 0 ? StringFormatUtil.formatAmountWithoutGrouping(this.total) : "");
		getViewState().setTotalError(operationType.equals(OPERATION_TYPE_BUY) && this.total > available);
	}


	void onBuyClicked() {
		placeOrder(BinanceOrderSide.BUY);
	}

	void onSellClicked() {
		placeOrder(BinanceOrderSide.SELL);
	}

	private void placeOrder(BinanceOrderSide side) {
		if (terminalManager != null && selectedAccount != null) {
			BinanceRawPlaceOrder order = new BinanceRawPlaceOrder();
			order.setSide(side);
			order.setSymbol(symbol);
			order.setType(getBinanceOrderType(side));
			if (orderTypePosition.equals(ORDER_TYPE_STOP_LIMIT)) {
				order.setStopPrice(stop);
				order.setPrice(limit);
				order.setTimeInForce(BinanceTimeInForce.GOODTILLCANCEL);
			}
			else if (orderTypePosition.equals(ORDER_TYPE_LIMIT)) {
				order.setTimeInForce(BinanceTimeInForce.GOODTILLCANCEL);
				order.setPrice(price);
			}
			order.setQuantity(amount);

			if (placeOrderSubscription != null) {
				placeOrderSubscription.unsubscribe();
			}
			getViewState().showProgressBarButton(true);
			placeOrderSubscription = terminalManager.placeOrder(order, selectedAccount.getId())
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handlePlaceOrderSuccess,
							this::handlePlaceOrderError);
		}
	}

	private void handlePlaceOrderSuccess(BinanceRawPlacedOrder response) {
		placeOrderSubscription.unsubscribe();
		getViewState().showProgressBarButton(false);
	}

	private void handlePlaceOrderError(Throwable throwable) {
		placeOrderSubscription.unsubscribe();
		getViewState().showProgressBarButton(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private BinanceOrderType getBinanceOrderType(BinanceOrderSide side) {
		switch (orderTypePosition) {
			default:
			case ORDER_TYPE_MARKET:
				return BinanceOrderType.MARKET;
			case ORDER_TYPE_LIMIT:
				return BinanceOrderType.LIMIT;
			case ORDER_TYPE_STOP_LIMIT:
				return getStopLimitOrderType(side);
		}
	}

	private BinanceOrderType getStopLimitOrderType(BinanceOrderSide side) {
		if (side.equals(BinanceOrderSide.BUY)) {
			if ((limit < currentTickerPrice && stop < currentTickerPrice)
					|| (limit > currentTickerPrice && stop < currentTickerPrice)) {
				return BinanceOrderType.TAKEPROFITLIMIT;
			}
			if ((limit > currentTickerPrice && stop > currentTickerPrice)
					|| (limit < currentTickerPrice && stop > currentTickerPrice)) {
				return BinanceOrderType.STOPLOSSLIMIT;
			}
		}
		if (side.equals(BinanceOrderSide.SELL)) {
			if ((limit > currentTickerPrice && stop > currentTickerPrice)
					|| (limit < currentTickerPrice && stop > currentTickerPrice)) {
				return BinanceOrderType.TAKEPROFITLIMIT;
			}
			if ((limit < currentTickerPrice && stop < currentTickerPrice)
					|| (limit > currentTickerPrice && stop < currentTickerPrice)) {
				return BinanceOrderType.STOPLOSSLIMIT;
			}
		}
		return null;
	}

	@Subscribe
	public void onEventMainThread(SetOpenOrdersCountEvent event) {
		getViewState().setOpenOrdersCount(event.getCount());
	}

	@Subscribe
	public void onEventMainThread(SetOrderHistoryCountEvent event) {
		getViewState().setOrderHistoryCount(event.getCount());
	}

	@Subscribe
	public void onEventMainThread(SetTradeHistoryCountEvent event) {
		getViewState().setTradeHistoryCount(event.getCount());
	}
}
