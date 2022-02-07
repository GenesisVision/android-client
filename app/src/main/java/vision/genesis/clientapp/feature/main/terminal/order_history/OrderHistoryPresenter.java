package vision.genesis.clientapp.feature.main.terminal.order_history;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.BinanceExecutionType;
import io.swagger.client.model.BinanceRawFuturesOrder;
import io.swagger.client.model.BinanceRawFuturesOrderItemsViewModel;
import io.swagger.client.model.BinanceRawOrder;
import io.swagger.client.model.BinanceRawOrderItemsViewModel;
import io.swagger.client.model.TradingAccountPermission;
import io.swagger.client.model.TradingPlatformBinanceOrdersMode;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.SetOrderHistoryCountEvent;
import vision.genesis.clientapp.model.events.SetTradeHistoryCountEvent;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceOrder;
import vision.genesis.clientapp.model.terminal.binance_socket.FuturesAccountModel;
import vision.genesis.clientapp.model.terminal.binance_socket.FuturesOrderModel;
import vision.genesis.clientapp.model.terminal.binance_socket.SpotAccountModel;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */

@InjectViewState
public class OrderHistoryPresenter extends MvpPresenter<OrderHistoryView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public TerminalManager terminalManager;

	private Subscription getOrdersSubscription;

	private Subscription ordersUpdateSubscription;

	private int skip = 0;

	private UUID accountId;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private List<BinanceOrder> orders = new ArrayList<>();

	private String symbol = "";

	private TradingPlatformBinanceOrdersMode mode = TradingPlatformBinanceOrdersMode.ORDERHISTORY;

	private Integer ordersTotal = 0;

	private TradingAccountPermission currentMarket = TradingAccountPermission.SPOT;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		getOrders(true);
		subscribeToOrdersUpdates();
	}

	@Override
	public void onDestroy() {
		if (getOrdersSubscription != null) {
			getOrdersSubscription.unsubscribe();
		}
		if (ordersUpdateSubscription != null) {
			ordersUpdateSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(UUID programId, String symbol, TradingPlatformBinanceOrdersMode mode) {
		this.accountId = programId;
		this.symbol = symbol;
		this.mode = mode;

		getOrders(true);
		subscribeToOrdersUpdates();
	}

	void onShow() {
		getOrders(false);
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getOrders(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getOrders(false);
	}

	private void getOrders(boolean forceUpdate) {
		if (terminalManager != null && dateRange != null && accountId != null) {
			if (forceUpdate) {
				skip = 0;
			}

			if (getOrdersSubscription != null) {
				getOrdersSubscription.unsubscribe();
			}

			currentMarket = terminalManager.getCurrentMarket();
			if (currentMarket.equals(TradingAccountPermission.SPOT)) {
				getOrdersSubscription = terminalManager.getSpotOrderHistory(accountId, mode, dateRange, null, skip, TAKE)
						.observeOn(AndroidSchedulers.mainThread())
						.subscribeOn(Schedulers.io())
						.subscribe(this::handleGetSpotOrdersResponse,
								this::handleGetOrdersError);
			}
			else if (currentMarket.equals(TradingAccountPermission.FUTURES)) {
				getOrdersSubscription = terminalManager.getFuturesOrderHistory(accountId, mode, dateRange, null, skip, TAKE)
						.observeOn(AndroidSchedulers.mainThread())
						.subscribeOn(Schedulers.io())
						.subscribe(this::handleGetFuturesOrdersResponse,
								this::handleGetOrdersError);
			}
		}
	}

	private void handleGetSpotOrdersResponse(BinanceRawOrderItemsViewModel model) {
		List<BinanceOrder> newOrders = new ArrayList<>();
		for (BinanceRawOrder item : model.getItems()) {
			newOrders.add(BinanceOrder.from(item));
		}
		finishHandleGetSpotOrdersResponse(model.getTotal(), newOrders);
	}

	private void handleGetFuturesOrdersResponse(BinanceRawFuturesOrderItemsViewModel model) {
		List<BinanceOrder> newOrders = new ArrayList<>();
		for (BinanceRawFuturesOrder item : model.getItems()) {
			newOrders.add(BinanceOrder.from(item));
		}
		finishHandleGetSpotOrdersResponse(model.getTotal(), newOrders);
	}

	private void finishHandleGetSpotOrdersResponse(Integer total, List<BinanceOrder> newOrders) {
		getOrdersSubscription.unsubscribe();
		getViewState().showProgress(false);

		ordersTotal = total;

		if (skip == 0) {
			orders.clear();
		}

		if (mode.equals(TradingPlatformBinanceOrdersMode.ORDERHISTORY)) {
			EventBus.getDefault().post(new SetOrderHistoryCountEvent(total));
		}
		else if (mode.equals(TradingPlatformBinanceOrdersMode.TRADEHISTORY)) {
			EventBus.getDefault().post(new SetTradeHistoryCountEvent(total));
		}

		orders.addAll(newOrders);

		if (skip == 0) {
			getViewState().setOrders(newOrders);
		}
		else {
			getViewState().addOrders(newOrders);
		}

		skip += TAKE;
	}

	private void handleGetOrdersError(Throwable error) {
		getOrdersSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	private void subscribeToOrdersUpdates() {
		if (currentMarket.equals(TradingAccountPermission.SPOT)) {
			subscribeToSpotOrdersUpdate();
		}
		else if (currentMarket.equals(TradingAccountPermission.FUTURES)) {
			subscribeToFuturesOrdersUpdate();
		}
	}

	private void subscribeToSpotOrdersUpdate() {
		if (terminalManager != null && accountId != null) {
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
		model.setAccountId(accountId);
		if ("executionReport".equals(model.getEventType())) {
			if (mode.equals(TradingPlatformBinanceOrdersMode.ORDERHISTORY)) {
				if (model.getExecutionType() == BinanceExecutionType.NEW) {
					for (BinanceOrder binanceOrder : orders) {
						if (binanceOrder.getOrderId().equals(model.getOrderId())) {
							return;
						}
					}
					orders.add(0, model.toBinanceOrder());
					ordersTotal++;
				}
				else {
					for (int i = 0; i < orders.size(); i++) {
						if (orders.get(i).getOrderId().equals(model.getOrderId())) {
							orders.remove(i);
							orders.add(i, model.toBinanceOrder());
							break;
						}
					}
				}
				getViewState().setOrders(orders);
				EventBus.getDefault().post(new SetOrderHistoryCountEvent(ordersTotal));

			}
			else {
				if (model.getExecutionType().equals(BinanceExecutionType.TRADE)
						&& ((int) (model.getQuantityFilled() / model.getQuantity() * 100)) == 100) {
					getOrders(true);
				}
			}
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
		model.setAccountId(accountId);
		if ("ORDER_TRADE_UPDATE".equals(model.getEventType())) {
			FuturesOrderModel order = model.getOrder();
			if (mode.equals(TradingPlatformBinanceOrdersMode.ORDERHISTORY)) {
				if (order.getExecutionType() == BinanceExecutionType.NEW) {
					for (BinanceOrder binanceOrder : orders) {
						if (binanceOrder.getOrderId().equals(order.getOrderId())) {
							return;
						}
					}
					orders.add(0, order.toBinanceOrder());
					ordersTotal++;
				}
				else {
					for (int i = 0; i < orders.size(); i++) {
						if (orders.get(i).getOrderId().equals(order.getOrderId())) {
							orders.remove(i);
							orders.add(i, order.toBinanceOrder());
							break;
						}
					}
				}
				getViewState().setOrders(orders);
				EventBus.getDefault().post(new SetOrderHistoryCountEvent(ordersTotal));

			}
			else {
				if (order.getExecutionType().equals(BinanceExecutionType.TRADE)
						&& ((int) (order.getQuantityFilled() / order.getQuantity() * 100)) == 100) {
					getOrders(true);
				}
			}
		}
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.dateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getOrders(true);
	}
}
