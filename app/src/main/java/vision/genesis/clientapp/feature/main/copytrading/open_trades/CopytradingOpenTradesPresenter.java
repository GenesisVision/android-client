package vision.genesis.clientapp.feature.main.copytrading.open_trades;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.OrderSignalModel;
import io.swagger.client.model.TradesSignalViewModel;
import rx.Subscription;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.model.events.OnOpenTradeWholeCloseClickedEvent;
import vision.genesis.clientapp.model.events.SetCopytradingOpenTradesCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

@InjectViewState
public class CopytradingOpenTradesPresenter extends MvpPresenter<CopytradingOpenTradesView>
{
	@Inject
	public FollowsManager followsManager;

	private Subscription getOpenTradesSubscription;

	private Subscription closeTradeSubscription;

	private List<OrderSignalModel> openTrades = new ArrayList<>();

	private Integer totalTradesCount = 0;

	private boolean isFragmentActive;

	private UUID accountId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (getOpenTradesSubscription != null) {
			getOpenTradesSubscription.unsubscribe();
		}
		if (closeTradeSubscription != null) {
			closeTradeSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID accountId) {
		this.accountId = accountId;

		getOpenTrades();
	}

	void onHide() {
		isFragmentActive = false;
	}

	void onShow() {
		isFragmentActive = true;
		getOpenTrades();
	}

	private void getOpenTrades() {
		if (followsManager != null && accountId != null) {
//			getOpenTradesSubscription = followsManager.getOpenTrades("", "", accountId, null, 0, 1000)
//					.subscribeOn(Schedulers.computation())
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribe(this::handleGetOpenTradesSuccess,
//							this::handleGetOpenTradesError);
		}
	}

	private void handleGetOpenTradesSuccess(TradesSignalViewModel response) {
		getOpenTradesSubscription.unsubscribe();

		getViewState().showProgressBar(false);

		openTrades = response.getItems();
		getViewState().setOpenTrades(openTrades);

		totalTradesCount = response.getTotal();
		updateCounter();
	}

	private void updateCounter() {
		EventBus.getDefault().post(new SetCopytradingOpenTradesCountEvent(totalTradesCount));
	}

	private void handleGetOpenTradesError(Throwable throwable) {
		getOpenTradesSubscription.unsubscribe();

		getViewState().showProgressBar(false);
		getViewState().showEmpty(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	void closeTrade(UUID tradeId) {
		if (followsManager != null && tradeId != null) {
//			closeTradeSubscription = followsManager.closeTrade(tradeId, null)
//					.subscribeOn(Schedulers.computation())
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribe(response -> handleCloseTradeSuccess(response, tradeId),
//							this::handleCloseTradeError);
		}
	}

	private void handleCloseTradeSuccess(Void response, UUID tradeId) {
		closeTradeSubscription.unsubscribe();

		int position = 0;
		for (OrderSignalModel trade : openTrades) {
			if (trade.getId().equals(tradeId)) {
				openTrades.remove(trade);
				break;
			}
			position++;
		}

		getViewState().removeOpenTrade(position, openTrades.isEmpty());

		totalTradesCount--;
		if (totalTradesCount < 0) {
			totalTradesCount = 0;
		}
		updateCounter();
	}

	private void handleCloseTradeError(Throwable throwable) {
		closeTradeSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnOpenTradeWholeCloseClickedEvent event) {
		if (isFragmentActive) {
			getViewState().askCloseTrade(event.getTradeId(), event.getSymbol(), event.getVolume());
		}
	}
}
