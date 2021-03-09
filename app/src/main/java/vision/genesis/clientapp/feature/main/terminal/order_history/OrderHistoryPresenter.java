package vision.genesis.clientapp.feature.main.terminal.order_history;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

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
import vision.genesis.clientapp.model.events.OnOpenOrderClickedEvent;
import vision.genesis.clientapp.model.events.SetOrderHistoryCountEvent;
import vision.genesis.clientapp.model.events.SetTradeHistoryCountEvent;
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

	private Subscription ordersSubscription;

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

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		getOrders(true);
	}

	@Override
	public void onDestroy() {
		if (ordersSubscription != null) {
			ordersSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID programId, String symbol, TradingPlatformBinanceOrdersMode mode) {
		this.accountId = programId;
		this.symbol = symbol;
		this.mode = mode;

		getOrders(true);
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

			if (ordersSubscription != null) {
				ordersSubscription.unsubscribe();
			}
			ordersSubscription = terminalManager.getOrderHistory(accountId, mode, dateRange, symbol, skip, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetOrdersResponse,
							this::handleGetOrdersError);
		}
	}

	private void handleGetOrdersResponse(BinanceRawOrderItemsViewModel model) {
		ordersSubscription.unsubscribe();
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
		ordersSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.dateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getOrders(true);
	}

	@Subscribe
	public void onEventMainThread(OnOpenOrderClickedEvent event) {
		getViewState().showOrderDetails(event.getOrder());
	}
}
