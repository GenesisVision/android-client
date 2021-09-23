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

import io.swagger.client.model.BinanceRawCancelOrder;
import io.swagger.client.model.BinanceRawOrder;
import io.swagger.client.model.BinanceRawOrderItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.events.OnOpenOrderClickedEvent;
import vision.genesis.clientapp.model.events.OnOrderCloseClickedEvent;
import vision.genesis.clientapp.model.events.SetOpenOrdersCountEvent;
import vision.genesis.clientapp.model.terminal.binance_socket.AccountModel;
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

	private List<BinanceRawOrder> orders = new ArrayList<>();

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
			getOrdersSubscription = terminalManager.getOpenOrders(accountId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetOrdersResponse,
							this::handleGetOrdersError);
		}
	}

	private void handleGetOrdersResponse(BinanceRawOrderItemsViewModel model) {
		getOrdersSubscription.unsubscribe();
		getViewState().showProgress(false);

		EventBus.getDefault().post(new SetOpenOrdersCountEvent(model.getTotal()));

		orders.clear();
		List<BinanceRawOrder> newOrders = model.getItems();
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
		if (terminalManager != null) {
			if (ordersUpdateSubscription != null) {
				ordersUpdateSubscription.unsubscribe();
			}
			ordersUpdateSubscription = terminalManager.getAccountSubject(accountId)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleOpenOrdersUpdate, this::onOpenOrdersUpdateError);
		}
	}

	private void handleOpenOrdersUpdate(AccountModel model) {
		if ("executionReport".equals(model.getEventType())) {
			switch (model.getExecutionType()) {
				case NEW:
					orders.add(0, model.toBinanceRawOrder());
					break;
				case TRADE:
					for (int i = 0; i < orders.size(); i++) {
						if (orders.get(i).getOrderId().equals(model.getOrderId())) {
							orders.remove(i);
							if (!Objects.equals(model.getQuantity(), model.getQuantityFilled())) {
								orders.add(i, model.toBinanceRawOrder());
							}
							break;
						}
					}
					break;
				case CANCELED:
				case EXPIRED:
					for (BinanceRawOrder order : orders) {
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

	private void onOpenOrdersUpdateError(Throwable throwable) {

	}

	private void closeOrder(BinanceRawOrder order) {
		if (terminalManager != null && order != null) {
			getViewState().showProgress(true);
			closeOrderSubscription = terminalManager.cancelOrder(order)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleCloseOrderResponse,
							this::handleCloseOrderError);
		}
	}

	private void handleCloseOrderResponse(BinanceRawCancelOrder response) {
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
	public void onEventMainThread(OnOpenOrderClickedEvent event) {
		getViewState().showOrderDetails(event.getOrder());
	}

	@Subscribe
	public void onEventMainThread(OnOrderCloseClickedEvent event) {
		closeOrder(event.getOrder());
	}
}
