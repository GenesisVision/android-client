package vision.genesis.clientapp.feature.main.terminal.open_orders;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.BinanceRawFuturesOrder;
import io.swagger.client.model.BinanceRawFuturesOrderItemsViewModel;
import io.swagger.client.model.BinanceRawOrder;
import io.swagger.client.model.BinanceRawOrderItemsViewModel;
import io.swagger.client.model.TradingAccountPermission;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.events.OnOrderCloseClickedEvent;
import vision.genesis.clientapp.model.events.SetOpenOrdersCountEvent;
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
public class OpenOrdersPresenter extends MvpPresenter<OpenOrdersView>
{
	@Inject
	public Context context;

	@Inject
	public TerminalManager terminalManager;

	private Subscription getOrdersSubscription;

	private Subscription ordersUpdateSubscription;

	private Subscription closeOrderSubscription;

	private UUID accountId;

	private List<BinanceOrder> orders = new ArrayList<>();

	private TradingAccountPermission currentMarket = TradingAccountPermission.SPOT;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);

		getOrders();
	}

	@Override
	public void onDestroy() {
		if (getOrdersSubscription != null) {
			getOrdersSubscription.unsubscribe();
		}
		if (ordersUpdateSubscription != null) {
			ordersUpdateSubscription.unsubscribe();
		}
		if (closeOrderSubscription != null) {
			closeOrderSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID programId) {
		this.accountId = programId;

		getOrders();
	}

	void onShow() {
		getOrders();
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getOrders();
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
								this::handleGetOrdersError);
			}
			else if (currentMarket.equals(TradingAccountPermission.FUTURES)) {
				getOrdersSubscription = terminalManager.getFuturesOpenOrders(accountId)
						.observeOn(AndroidSchedulers.mainThread())
						.subscribeOn(Schedulers.io())
						.subscribe(this::handleGetFuturesOrdersResponse,
								this::handleGetOrdersError);
			}
		}
	}

	private void handleGetSpotOrdersResponse(BinanceRawOrderItemsViewModel model) {
		getOrdersSubscription.unsubscribe();
		getViewState().showProgress(false);

		EventBus.getDefault().post(new SetOpenOrdersCountEvent(model.getTotal()));

		orders.clear();
		List<BinanceOrder> newOrders = new ArrayList<>();
		for (BinanceRawOrder item : model.getItems()) {
			newOrders.add(BinanceOrder.from(item));
		}
		orders.addAll(newOrders);

		getViewState().setOrders(newOrders);

		subscribeToOrdersUpdates();
	}

	private void handleGetFuturesOrdersResponse(BinanceRawFuturesOrderItemsViewModel model) {
		getOrdersSubscription.unsubscribe();
		getViewState().showProgress(false);

		EventBus.getDefault().post(new SetOpenOrdersCountEvent(model.getTotal()));

		orders.clear();
		List<BinanceOrder> newOrders = new ArrayList<>();
		for (BinanceRawFuturesOrder item : model.getItems()) {
			newOrders.add(BinanceOrder.from(item));
		}
		orders.addAll(newOrders);

		getViewState().setOrders(newOrders);

		subscribeToOrdersUpdates();
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
					for (BinanceOrder order : orders) {
						if (order.getOrderId().equals(model.getOrderId())) {
							break;
						}
					}
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

			getViewState().setOrders(orders);
			EventBus.getDefault().post(new SetOpenOrdersCountEvent(orders.size()));
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
					for (BinanceOrder binanceOrder : orders) {
						if (binanceOrder.getOrderId().equals(order.getOrderId())) {
							break;
						}
					}
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

			getViewState().setOrders(orders);
			EventBus.getDefault().post(new SetOpenOrdersCountEvent(orders.size()));
		}
	}

	private void closeOrder(BinanceOrder order) {
		if (terminalManager != null && order != null) {
			getViewState().showProgress(true);
			order.setAccountId(accountId);
			closeOrderSubscription = (currentMarket.equals(TradingAccountPermission.SPOT)
					? terminalManager.cancelSpotOrder(order)
					: terminalManager.cancelFuturesOrder(order))
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleCloseOrderResponse,
							this::handleCloseOrderError);
		}
	}

	private void handleCloseOrderResponse(Object response) {
		closeOrderSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void handleCloseOrderError(Throwable error) {
		closeOrderSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	@Subscribe
	public void onEventMainThread(OnOrderCloseClickedEvent event) {
		closeOrder(event.getOrder());
	}
}
