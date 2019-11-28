package vision.genesis.clientapp.feature.main.dashboard;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.DashboardSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/11/2019.
 */

@InjectViewState
public class DashboardPresenter extends MvpPresenter<DashboardView>
{
	@Inject
	public Context context;

	@Inject
	public DashboardManager dashboardManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription baseCurrencySubscription;

	private Subscription summarySubscription;

	private DateRange dateRange;

	private CurrencyEnum baseCurrency;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

//		EventBus.getDefault().register(this);

		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (summarySubscription != null) {
			summarySubscription.unsubscribe();
		}

//		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
		updateAll();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		updateAll();
	}

	private void updateAll() {
		getSummary();
		getViewState().updateInvesting();
		getViewState().updateTrading();
		getViewState().updateWallet();
	}

	private void subscribeToBaseCurrency() {
		baseCurrencySubscription = settingsManager.getBaseCurrency()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::baseCurrencyChangedHandler);
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getViewState().setBaseCurrency(baseCurrency);
		getViewState().showProgressBar(true);
		updateAll();
	}

	private void getSummary() {
		if (summarySubscription != null) {
			summarySubscription.unsubscribe();
		}
		if (baseCurrency != null) {
			summarySubscription = dashboardManager.getSummary(baseCurrency.getValue())
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetSummarySuccess,
							this::handleGetSummaryError);
		}
	}

	private void handleGetSummarySuccess(DashboardSummary response) {
		summarySubscription.unsubscribe();
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);

		getViewState().setSummary(response);

//		this.requests = response.getRequestsByAsset().getRequestsByAsset();
//
//		getViewState().setHaveNewNotifications(response.getProfileHeader().getNotificationsCount() > 0);
//		getViewState().setChartData(response.getChart());
//		getViewState().setInRequests(response.getRequestsByAsset().getTotalValue(), response.getChart().getRate());

	}

	private void handleGetSummaryError(Throwable throwable) {
		summarySubscription.unsubscribe();
		getViewState().setRefreshing(false);
//		getViewState().showProgressBar(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

//	@Override
//	public void onCurrencyChanged(CurrencyEnum currency) {
//		settingsManager.saveBaseCurrency(currency);
//	}
}
