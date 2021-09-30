package vision.genesis.clientapp.feature.main.terminal.order_history;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.BinanceExecutionType;
import io.swagger.client.model.BinanceRawOrder;
import io.swagger.client.model.BinanceRawOrderItemsViewModel;
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
import vision.genesis.clientapp.model.terminal.binance_socket.AccountModel;
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

	private List<BinanceRawOrder> orders = new ArrayList<>();

	private String symbol = "";

	private TradingPlatformBinanceOrdersMode mode = TradingPlatformBinanceOrdersMode.ORDERHISTORY;

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
			getOrdersSubscription = terminalManager.getOrderHistory(accountId, mode, dateRange, null, skip, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetOrdersResponse,
							this::handleGetOrdersError);
		}
	}

	private void handleGetOrdersResponse(BinanceRawOrderItemsViewModel model) {
		getOrdersSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (skip == 0) {
			orders.clear();
		}

		if (mode.equals(TradingPlatformBinanceOrdersMode.ORDERHISTORY)) {
			EventBus.getDefault().post(new SetOrderHistoryCountEvent(model.getTotal()));
		}
		else if (mode.equals(TradingPlatformBinanceOrdersMode.TRADEHISTORY)) {
			EventBus.getDefault().post(new SetTradeHistoryCountEvent(model.getTotal()));
		}

		List<BinanceRawOrder> newOrders = model.getItems();

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
		if (terminalManager != null && accountId != null) {
			if (ordersUpdateSubscription != null) {
				ordersUpdateSubscription.unsubscribe();
			}
			ordersUpdateSubscription = terminalManager.getAccountSubject(accountId)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleOrdersUpdate, error -> {
					});
		}
	}

	private void handleOrdersUpdate(AccountModel model) {
		model.setAccountId(accountId);
		if ("executionReport".equals(model.getEventType())) {
			if (mode.equals(TradingPlatformBinanceOrdersMode.ORDERHISTORY)) {
				if (model.getExecutionType() == BinanceExecutionType.NEW) {
					orders.add(0, model.toBinanceRawOrder());
				}
				else {
					for (int i = 0; i < orders.size(); i++) {
						if (orders.get(i).getOrderId().equals(model.getOrderId())) {
							orders.remove(i);
							orders.add(i, model.toBinanceRawOrder());
							break;
						}
					}
				}
				getViewState().setOrders(orders);
				if (mode.equals(TradingPlatformBinanceOrdersMode.ORDERHISTORY)) {
					EventBus.getDefault().post(new SetOrderHistoryCountEvent(orders.size()));
				}
				else if (mode.equals(TradingPlatformBinanceOrdersMode.TRADEHISTORY)) {
					EventBus.getDefault().post(new SetTradeHistoryCountEvent(orders.size()));
				}
			}
			else {
				if (model.getExecutionType().equals(BinanceExecutionType.TRADE)
						&& Objects.equals(model.getQuantityFilled(), model.getQuantity())) {
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
