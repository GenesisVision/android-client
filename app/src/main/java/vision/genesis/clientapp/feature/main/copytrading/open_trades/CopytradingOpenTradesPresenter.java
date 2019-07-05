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
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SignalsManager;
import vision.genesis.clientapp.model.events.OnOpenTradeWholeCloseClickedEvent;
import vision.genesis.clientapp.model.events.SetCopytradingAccountOpenTradesCountEvent;
import vision.genesis.clientapp.model.events.SetDashboardOpenTradesCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

@InjectViewState
public class CopytradingOpenTradesPresenter extends MvpPresenter<CopytradingOpenTradesView>
{
	@Inject
	public SignalsManager signalsManager;

	private Subscription getOpenTradesSubscription;

	private Subscription closeTradeSubscription;

	private String location;

	private String accountCurrency;

	private List<OrderSignalModel> openTrades = new ArrayList<>();

	private Integer totalTradesCount = 0;

	private boolean isFragmentActive;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (getOpenTradesSubscription != null)
			getOpenTradesSubscription.unsubscribe();
		if (closeTradeSubscription != null)
			closeTradeSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(String location, String accountCurrency) {
		this.location = location;
		this.accountCurrency = accountCurrency;

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
		if (signalsManager != null && location != null)
			getOpenTradesSubscription = signalsManager.getOpenTrades("", "", null, accountCurrency, 0, 1000)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetOpenTradesSuccess,
							this::handleGetOpenTradesError);
	}

	private void handleGetOpenTradesSuccess(TradesSignalViewModel response) {
		getOpenTradesSubscription.unsubscribe();

		getViewState().showProgressBar(false);

		openTrades = response.getTrades();
		getViewState().setOpenTrades(openTrades);

		totalTradesCount = response.getTotal();
		updateCounter();
	}

	private void updateCounter() {
		if (location.equals(CopytradingOpenTradesFragment.LOCATION_DASHBOARD))
			EventBus.getDefault().post(new SetDashboardOpenTradesCountEvent(totalTradesCount));
		else if (location.equals(CopytradingOpenTradesFragment.LOCATION_COPYTRADING_ACCOUNT))
			EventBus.getDefault().post(new SetCopytradingAccountOpenTradesCountEvent(totalTradesCount));
	}

	private void handleGetOpenTradesError(Throwable throwable) {
		getOpenTradesSubscription.unsubscribe();

		getViewState().showProgressBar(false);
		getViewState().showEmpty(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	void closeTrade(UUID tradeId) {
		if (signalsManager != null && tradeId != null)
			closeTradeSubscription = signalsManager.closeTrade(tradeId, null)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(response -> handleCloseTradeSuccess(response, tradeId),
							this::handleCloseTradeError);
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
		if (totalTradesCount < 0)
			totalTradesCount = 0;
		updateCounter();
	}

	private void handleCloseTradeError(Throwable throwable) {
		closeTradeSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnOpenTradeWholeCloseClickedEvent event) {
		if (isFragmentActive)
			getViewState().askCloseTrade(event.getTradeId(), event.getSymbol(), event.getVolume());
	}
}
