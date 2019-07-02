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

	private String location;

	private String accountCurrency;

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

	void setData(String location, String accountCurrency) {
		this.location = location;
		this.accountCurrency = accountCurrency;

		getOpenTrades();
	}

	void onShow() {
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

		getViewState().setOpenTrades(response.getTrades());

		if (location.equals(CopytradingOpenTradesFragment.LOCATION_DASHBOARD))
			EventBus.getDefault().post(new SetDashboardOpenTradesCountEvent(response.getTotal()));
		else if (location.equals(CopytradingOpenTradesFragment.LOCATION_COPYTRADING_ACCOUNT))
			EventBus.getDefault().post(new SetCopytradingAccountOpenTradesCountEvent(response.getTotal()));
	}

	private void handleGetOpenTradesError(Throwable throwable) {
		getOpenTradesSubscription.unsubscribe();

		getViewState().showProgressBar(false);
		getViewState().showEmpty(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}
}
