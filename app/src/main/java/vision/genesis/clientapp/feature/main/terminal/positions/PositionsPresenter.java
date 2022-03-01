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
import io.swagger.client.model.BinanceRawFuturesPosition;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.events.OnOrderCloseClickedEvent;
import vision.genesis.clientapp.model.events.SetPositionsCountEvent;
import vision.genesis.clientapp.model.terminal.binance_socket.FuturesAccountModel;
import vision.genesis.clientapp.net.ApiErrorResolver;

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

	private Subscription getPositionsSubscription;

	private Subscription positionsUpdateSubscription;

	private Subscription closePositionSubscription;

	private UUID accountId;

	private String symbol;

	private List<BinanceRawFuturesPosition> positions = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);

		getPositions();
	}

	@Override
	public void onDestroy() {
		if (getPositionsSubscription != null) {
			getPositionsSubscription.unsubscribe();
		}
		if (positionsUpdateSubscription != null) {
			positionsUpdateSubscription.unsubscribe();
		}
		if (closePositionSubscription != null) {
			closePositionSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID accountId, String symbol) {
		this.accountId = accountId;
		this.symbol = symbol;

		getPositions();
	}

	void onShow() {
		getPositions();
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getPositions();
	}

	private void getPositions() {
		if (terminalManager != null && accountId != null && symbol != null) {
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

			getViewState().setPositions(positions);

			subscribeToPositionsUpdates();
		}
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
		getViewState().showChangePositionMargin(position);
	}

	@Override
	public void onTpSlClicked(BinanceRawFuturesPosition position) {
		getViewState().showTpSl(position);
	}

	@Override
	public void onClosePositionClicked(BinanceRawFuturesPosition position) {
		getViewState().showClosePosition(position);
	}
}
