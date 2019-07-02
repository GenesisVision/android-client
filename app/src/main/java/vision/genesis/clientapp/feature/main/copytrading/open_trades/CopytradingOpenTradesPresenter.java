package vision.genesis.clientapp.feature.main.copytrading.open_trades;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.TradesSignalViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SignalsManager;
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

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (getOpenTradesSubscription != null)
			getOpenTradesSubscription.unsubscribe();

		super.onDestroy();
	}

	void onShow() {
		getOpenTrades();
	}

	private void getOpenTrades() {
		if (signalsManager != null)
			getOpenTradesSubscription = signalsManager.getOpenTrades("", "", null, null, 0, 100)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetOpenTradesSuccess,
							this::handleGetOpenTradesError);
	}

	private void handleGetOpenTradesSuccess(TradesSignalViewModel response) {
		getOpenTradesSubscription.unsubscribe();

		getViewState().showProgressBar(false);

		getViewState().setOpenTrades(response.getTrades());
		EventBus.getDefault().post(new SetDashboardOpenTradesCountEvent(response.getTotal()));
	}

	private void handleGetOpenTradesError(Throwable throwable) {
		getOpenTradesSubscription.unsubscribe();

		getViewState().showProgressBar(false);
		getViewState().showEmpty(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}
}
