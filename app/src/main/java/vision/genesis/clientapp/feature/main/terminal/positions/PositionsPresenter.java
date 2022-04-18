package vision.genesis.clientapp.feature.main.terminal.positions;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.BinanceFuturesMarginType;
import io.swagger.client.model.BinanceRawFuturesAccountInfo;
import io.swagger.client.model.BinanceRawFuturesBracket;
import io.swagger.client.model.BinanceRawFuturesPosition;
import io.swagger.client.model.BinanceRawFuturesSymbolBracket;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.events.OnOrderCloseClickedEvent;
import vision.genesis.clientapp.model.events.SetPositionsCountEvent;
import vision.genesis.clientapp.model.terminal.binance_socket.FuturesAccountModel;
import vision.genesis.clientapp.model.terminal.binance_socket.TickerModel;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.PositionsHelper;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */

@InjectViewState
public class PositionsPresenter extends MvpPresenter<PositionsView> implements PositionsListAdapter.OnItemClickListener
{
	@Inject
	public Context context;

	@Inject
	public TerminalManager terminalManager;

	private Subscription getAccountInfoSubscription;

	private Subscription getFuturesBracketsSubscription;

	private Subscription getPositionsSubscription;

	private Subscription tickerUpdateSubscription;

	private Subscription accountUpdateSubscription;

	private Subscription positionsUpdateSubscription;

	private UUID accountId;

	private String symbol;

	private ArrayList<BinanceRawFuturesBracket> futuresBrackets = null;

	private List<BinanceRawFuturesPosition> positions = new ArrayList<>();

	private BinanceRawFuturesAccountInfo futuresAccountInfo = null;

	private Double currentTickerPrice = null;

	private Double available = 0.0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);

		subscribeToTickerUpdates();
		subscribeToAccountUpdate();
		getFuturesAccountInfo();
	}

	@Override
	public void onDestroy() {
		if (getAccountInfoSubscription != null) {
			getAccountInfoSubscription.unsubscribe();
		}
		if (getFuturesBracketsSubscription != null) {
			getFuturesBracketsSubscription.unsubscribe();
		}
		if (getPositionsSubscription != null) {
			getPositionsSubscription.unsubscribe();
		}
		if (tickerUpdateSubscription != null) {
			tickerUpdateSubscription.unsubscribe();
		}
		if (accountUpdateSubscription != null) {
			accountUpdateSubscription.unsubscribe();
		}
		if (positionsUpdateSubscription != null) {
			positionsUpdateSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID accountId, String symbol) {
		this.accountId = accountId;
		this.symbol = symbol;

		subscribeToTickerUpdates();
		subscribeToAccountUpdate();
		getFuturesAccountInfo();
	}

	void onShow() {
		getPositions();
		if (tickerUpdateSubscription == null || tickerUpdateSubscription.isUnsubscribed()) {
			subscribeToTickerUpdates();
		}
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getPositions();
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
		getViewState().setPositions(positions, calculateMarginRatios(positions));
	}

	private void getFuturesAccountInfo() {
		if (terminalManager != null && accountId != null) {
			if (getAccountInfoSubscription != null) {
				getAccountInfoSubscription.unsubscribe();
			}
			getAccountInfoSubscription = terminalManager.getFuturesAccountInfo(accountId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetFuturesAccountInfoSuccess,
							this::handleGetAccountInfoError);
		}
	}

	private void handleGetFuturesAccountInfoSuccess(BinanceRawFuturesAccountInfo response) {
		getAccountInfoSubscription.unsubscribe();

		this.futuresAccountInfo = response;

		if (futuresBrackets == null) {
			getFuturesBrackets();
		}
	}

	private void handleGetAccountInfoError(Throwable throwable) {
		getAccountInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void subscribeToAccountUpdate() {
		if (terminalManager != null && accountId != null) {
			if (accountUpdateSubscription != null) {
				accountUpdateSubscription.unsubscribe();
			}
			accountUpdateSubscription = terminalManager.getFuturesAccountSubject(accountId)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleFuturesAccountUpdate, error -> {
					});
		}
	}

	private void handleFuturesAccountUpdate(FuturesAccountModel model) {
		if ("ORDER_TRADE_UPDATE".equals(model.getEventType())) {
			getFuturesAccountInfo();
		}
	}

	private void getFuturesBrackets() {
		if (terminalManager != null && accountId != null && symbol != null) {
			if (getFuturesBracketsSubscription != null) {
				getFuturesBracketsSubscription.unsubscribe();
			}
			getFuturesBracketsSubscription = terminalManager.getFuturesBrackets(accountId, symbol)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetFuturesBracketsSuccess,
							this::handleGetFuturesBracketsError);
		}
	}

	private void handleGetFuturesBracketsSuccess(List<BinanceRawFuturesSymbolBracket> response) {
		getFuturesBracketsSubscription.unsubscribe();

		if (response != null && !response.isEmpty()) {
			futuresBrackets = new ArrayList<>(response.get(0).getBrackets());
		}
		getPositions();
	}

	private void handleGetFuturesBracketsError(Throwable throwable) {
		getFuturesBracketsSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void getPositions() {
		if (terminalManager != null && accountId != null && symbol != null && futuresBrackets != null) {
			if (getPositionsSubscription != null) {
				getPositionsSubscription.unsubscribe();
			}
			getPositionsSubscription = terminalManager.getFuturesPositionInfo(accountId, symbol)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleSuccess,
							this::handleError);
		}
	}

	private void handleSuccess(List<BinanceRawFuturesPosition> response) {
		getPositionsSubscription.unsubscribe();
		getViewState().showProgress(false);

		List<BinanceRawFuturesPosition> newPositions = new ArrayList<>();
		if (response != null) {
			boolean hasIsolated = false;
			for (BinanceRawFuturesPosition position : response) {
				if (position.getQuantity() == 0) {
					continue;
				}
				newPositions.add(position);
				if (position.getMarginType().equals(BinanceFuturesMarginType.ISOLATED)) {
					hasIsolated = true;
					break;
				}
			}
			EventBus.getDefault().post(new SetPositionsCountEvent(newPositions.size(), hasIsolated));

			positions.clear();
			positions.addAll(newPositions);

			getViewState().setPositions(positions, calculateMarginRatios(positions));

			subscribeToPositionsUpdates();
		}
	}

	private ArrayList<Double> calculateMarginRatios(List<BinanceRawFuturesPosition> newPositions) {
		ArrayList<Double> marginRatios = new ArrayList<>();
		boolean crossPositions = false;
		double crossPnl = 0.0;
		double crossMaintMargin = 0.0;
		double crossMarginBalance = 0.0;
		double crossMarginRatio = 0.0;

		for (BinanceRawFuturesPosition position : newPositions) {
			if (position.getQuantity() == 0) {
				continue;
			}
			if (currentTickerPrice != null) {
				position.setMarkPrice(currentTickerPrice);
			}
			if (position.getMarginType().equals(BinanceFuturesMarginType.ISOLATED)) {
				marginRatios.add(PositionsHelper.calculateIsolatedPositionMarginRatio(position, futuresBrackets));
			}
			else if (position.getMarginType().equals(BinanceFuturesMarginType.CROSS)) {
				if (futuresAccountInfo == null) {
					continue;
				}
				crossPositions = true;
				crossPnl = crossPnl + PositionsHelper.calculateUnrealisedPnl(position);
				crossMaintMargin = crossMaintMargin + PositionsHelper.calculateCrossPositionMaintMargin(position, futuresBrackets);
				crossMarginBalance = futuresAccountInfo.getTotalCrossWalletBalance() + crossPnl;
				crossMarginRatio = (crossMaintMargin / crossMarginBalance) * 100;
			}
		}
		if (crossPositions) {
			marginRatios.add(crossMarginRatio);
		}

		return marginRatios;
	}

	private void handleError(Throwable error) {
		getPositionsSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	private void subscribeToPositionsUpdates() {
		if (terminalManager != null) {
			if (positionsUpdateSubscription != null) {
				positionsUpdateSubscription.unsubscribe();
			}
			positionsUpdateSubscription = terminalManager.getFuturesAccountSubject(accountId)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleFuturesOrdersUpdate, error -> {
					});
		}
	}

	private void handleFuturesOrdersUpdate(FuturesAccountModel model) {
		if ("ACCOUNT_UPDATE".equals(model.getEventType())) {
			getPositions();
		}
	}

//	private void closePosition(BinanceRawFuturesPosition position) {
//		if (terminalManager != null && position != null) {
//			getViewState().showProgress(true);
//			position.setAccountId(accountId);
//			closePositionSubscription = terminalManager.cancelFuturesOrder(position)
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribeOn(Schedulers.io())
//					.subscribe(this::handleCloseOrderResponse,
//							this::handleCloseOrderError);
//		}
//	}
//
//	private void handleCloseOrderResponse(Object response) {
//		closePositionSubscription.unsubscribe();
//		getViewState().showProgress(false);
//	}
//
//	private void handleCloseOrderError(Throwable error) {
//		closePositionSubscription.unsubscribe();
//		getViewState().showProgress(false);
//
//		if (ApiErrorResolver.isNetworkError(error)) {
//			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
//		}
//	}

	@Subscribe
	public void onEventMainThread(OnOrderCloseClickedEvent event) {
//		closePosition(event.getOrder());
	}

	@Override
	public void onAddMarginClicked(BinanceRawFuturesPosition position) {
		getViewState().showChangePositionMargin(position, futuresBrackets, available);
	}

	@Override
	public void onTpSlClicked(BinanceRawFuturesPosition position) {
		getViewState().showTpSl(position);
	}

	@Override
	public void onClosePositionClicked(BinanceRawFuturesPosition position) {
		getViewState().showClosePosition(position);
	}

	public void setAvailable(Double available, String availableCurrency) {
		this.available = available;
	}
}
