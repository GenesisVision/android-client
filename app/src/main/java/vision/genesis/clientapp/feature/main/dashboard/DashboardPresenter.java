package vision.genesis.clientapp.feature.main.dashboard;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.Timeframe;
import io.swagger.client.model.UserVerificationStatus;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.timeframe_profit.TimeframeProfitView;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/11/2019.
 */

@InjectViewState
public class DashboardPresenter extends MvpPresenter<DashboardView> implements TimeframeProfitView.Listener
{
	@Inject
	public Context context;

	@Inject
	public DashboardManager dashboardManager;

	@Inject
	public ProfileManager profileManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription baseCurrencySubscription;

	private Subscription profileSubscription;

	private Subscription summarySubscription;

	private CurrencyEnum baseCurrency;

	private Timeframe selectedTimeframe = Timeframe.DAY;

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
		if (profileSubscription != null) {
			profileSubscription.unsubscribe();
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
		getProfile();
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

	private void getProfile() {
		profileSubscription = profileManager.getProfileFull(true)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);
	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		if (profile != null && profile.getVerificationStatus() != null) {
			getViewState().setLimitViewVisibility(!profile.getVerificationStatus().equals(UserVerificationStatus.VERIFIED));
			getViewState().setLimitViewButtonStatus(profile.getVerificationStatus());
		}
	}

	private void handleGetProfileError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
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
		getViewState().setTimeframe(selectedTimeframe);

//		this.requests = response.getRequestsByAsset().getRequestsByAsset();
//
//		getViewState().setHaveNewNotifications(response.getProfileHeader().getNotificationsCount() > 0);
//		getViewState().setAbsChart(response.getChart());
//		getViewState().setInRequests(response.getRequestsByAsset().getTotalValue(), response.getChart().getRate());

	}

	private void handleGetSummaryError(Throwable throwable) {
		summarySubscription.unsubscribe();
		getViewState().setRefreshing(false);
//		getViewState().showProgressBar(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onTimeframeSelected(Timeframe timeframe) {
		this.selectedTimeframe = timeframe;
		getViewState().setTimeframe(timeframe);
	}

//	@Override
//	public void onCurrencyChanged(CurrencyEnum currency) {
//		settingsManager.saveBaseCurrency(currency);
//	}
}
