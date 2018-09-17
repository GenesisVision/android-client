package vision.genesis.clientapp.feature.main.dashboard.investor;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.DashboardPortfolioEvent;
import io.swagger.client.model.DashboardSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.currency.SelectCurrencyFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.InvestorDashboardManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.OnPortfolioAssetsChangedEvent;
import vision.genesis.clientapp.model.events.OnPortfolioChartViewModeChangedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class InvestorDashboardPresenter extends MvpPresenter<InvestorDashboardView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, SelectCurrencyFragment.OnCurrencyChangedListener
{
	@Inject
	public Context context;

	@Inject
	public InvestorDashboardManager dashboardManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription baseCurrencySubscription;

	private Subscription dateRangeSubscription;

	private Subscription getEventsSubscription;

	private Subscription dashboardSubscription;

	private DateRange dateRange;

	private CurrencyEnum baseCurrency;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToDateRange();
		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null)
			baseCurrencySubscription.unsubscribe();
		if (dateRangeSubscription != null)
			dateRangeSubscription.unsubscribe();
		if (getEventsSubscription != null)
			getEventsSubscription.unsubscribe();
		if (dashboardSubscription != null)
			dashboardSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
//		getEvents();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getDashboard();
//		getEvents();
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
		getDashboard();
	}

	private void subscribeToDateRange() {
		dateRangeSubscription = settingsManager.getDateRange()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::dateRangeChangedHandler);
	}

	private void dateRangeChangedHandler(DateRange dateRange) {
		this.dateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().setRefreshing(true);
		getDashboard();
	}

	private void getDashboard() {
		if (dashboardSubscription != null)
			dashboardSubscription.unsubscribe();
		if (baseCurrency != null && dateRange != null) {
			updateDateRange();
			dashboardSubscription = dashboardManager.getDashboard(dateRange, baseCurrency.getValue())
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetDashboardSuccess,
							this::handleGetDashboardError);
		}
	}

	private void updateDateRange() {
		dateRange.updateDates(dateRange.getSelectedRange());
		getViewState().setDateRange(dateRange);
	}

	private void handleGetDashboardSuccess(DashboardSummary response) {
		dashboardSubscription.unsubscribe();
		getViewState().setRefreshing(false);

		getViewState().setHaveNewNotifications(response.getProfileHeader().getNotificationsCount() > 0);
		getViewState().setChartData(response.getChart());
		getViewState().setPortfolioEvents(response.getEvents().getEvents());
		getViewState().setAssetsCount(response.getProgramsCount(), response.getFundsCount());

	}

	private void handleGetDashboardError(Throwable throwable) {
		dashboardSubscription.unsubscribe();
		getViewState().setRefreshing(false);
	}

	private void getEvents() {
		getEventsSubscription = dashboardManager.getPortfolioEvents()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetEventsSuccess,
						this::handleGetEventsError);
	}

	private void handleGetEventsSuccess(List<DashboardPortfolioEvent> response) {
		List<DashboardPortfolioEvent> events = response.size() > 10 ? response.subList(0, 10) : response;
		getViewState().setPortfolioEvents(events);
	}

	private void handleGetEventsError(Throwable throwable) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
//			if (programs.size() == 0)
//				getViewState().showNoInternet(true);
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		settingsManager.saveDateRange(dateRange);
	}

	@Override
	public void onCurrencyChanged(CurrencyEnum currency) {
		settingsManager.saveBaseCurrency(currency);
	}

	@Subscribe
	public void onEventMainThread(OnPortfolioChartViewModeChangedEvent event) {
		getViewState().setChartViewMode(event.getViewMode(), event.getChartBottomY());
	}

	@Subscribe
	public void onEventMainThread(OnPortfolioAssetsChangedEvent event) {
		getViewState().setPortfolioAssets(event.getValueChartBar());
	}
}