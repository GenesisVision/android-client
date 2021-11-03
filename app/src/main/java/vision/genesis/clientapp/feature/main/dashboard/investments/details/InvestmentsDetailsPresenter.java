package vision.genesis.clientapp.feature.main.dashboard.investments.details;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetInvestmentRequestItemsViewModel;
import io.swagger.client.model.CoinsAssetItemsViewModel;
import io.swagger.client.model.DashboardAssetStatus;
import io.swagger.client.model.DashboardInvestingDetails;
import io.swagger.client.model.FundInvestingDetailsListItemsViewModel;
import io.swagger.client.model.ProgramInvestingDetailsListItemsViewModel;
import io.swagger.client.model.Timeframe;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.timeframe_profit.TimeframeProfitView;
import vision.genesis.clientapp.managers.CoinsManager;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.OnCancelRequestClickedEvent;
import vision.genesis.clientapp.model.events.OnRequestCancelledEvent;
import vision.genesis.clientapp.model.filter.ProgramsFilter;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

@InjectViewState
public class InvestmentsDetailsPresenter extends MvpPresenter<InvestmentsDetailsView> implements TimeframeProfitView.Listener
{
	private static final int TAKE = 5;

	private static final int EVENTS_TAKE = 5;

	@Inject
	public Context context;

	@Inject
	public DashboardManager dashboardManager;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public FundsManager fundsManager;

	@Inject
	public CoinsManager coinsManager;

	private Subscription baseCurrencySubscription;

	private Subscription getRequestsSubscription;

	private Subscription getInvestingSubscription;

	private Subscription programsSubscription;

	private Subscription fundsSubscription;

	private Subscription assetsSubscription;

	private Subscription cancelRequestSubscription;

	private CurrencyEnum baseCurrency;

	private DashboardInvestingDetails details;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.DAY);

	private Timeframe selectedTimeframe = Timeframe.DAY;

	private boolean isActive = false;

	private DashboardAssetStatus programsStatus;

	private DashboardAssetStatus fundsStatus;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		initStatusOptions();

		getViewState().showProgress(true);
		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (getRequestsSubscription != null) {
			getRequestsSubscription.unsubscribe();
		}
		if (getInvestingSubscription != null) {
			getInvestingSubscription.unsubscribe();
		}
		if (programsSubscription != null) {
			programsSubscription.unsubscribe();
		}
		if (fundsSubscription != null) {
			fundsSubscription.unsubscribe();
		}
		if (assetsSubscription != null) {
			assetsSubscription.unsubscribe();
		}
		if (cancelRequestSubscription != null) {
			cancelRequestSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
		isActive = true;
		updateAll();
	}

	void onPause() {
		isActive = false;
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		updateAll();
	}

	private void initStatusOptions() {
		ArrayList<String> statusProgramsOptions = new ArrayList<>();
		ArrayList<String> statusFundsOptions = new ArrayList<>();

		statusProgramsOptions.add(context.getString(R.string.active));
		statusProgramsOptions.add(context.getString(R.string.all));

		statusFundsOptions.add(context.getString(R.string.active));
		statusFundsOptions.add(context.getString(R.string.all));

		getViewState().setStatusOptions(statusProgramsOptions, statusFundsOptions);

		int programsPosition = getStatusPosition(settingsManager.getSavedInvestmentsProgramsStatus());
		onProgramsStatusOptionSelected(programsPosition, statusProgramsOptions.get(programsPosition));

		int fundsPosition = getStatusPosition(settingsManager.getSavedInvestmentsFundsStatus());
		onFundsStatusOptionSelected(fundsPosition, statusFundsOptions.get(fundsPosition));
	}

	private int getStatusPosition(DashboardAssetStatus status) {
		switch (status) {
			case ALL:
				return 1;
			default:
			case ACTIVE:
				return 0;
		}
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
		getViewState().showProgress(true);
		updateAll();
	}

	private void updateAll() {
		getRequests();
		getInvesting();
		getPrograms();
		getFunds();
		getAssets();
	}

	private void getRequests() {
		if (dashboardManager != null && baseCurrency != null) {
			getRequestsSubscription = dashboardManager.getRequests(0, 100)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleRequestsSuccess, this::handleRequestsError);
		}
	}

	private void handleRequestsSuccess(AssetInvestmentRequestItemsViewModel response) {
		getRequestsSubscription.unsubscribe();

		getViewState().setRequests(response);
	}

	private void handleRequestsError(Throwable throwable) {
		getRequestsSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getInvesting() {
		if (dashboardManager != null && baseCurrency != null) {
			getInvestingSubscription = dashboardManager.getInvestingDetails(baseCurrency.getValue(), EVENTS_TAKE)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleInvestingSuccess, this::handleInvestingError);
		}
	}

	private void handleInvestingSuccess(DashboardInvestingDetails details) {
		getInvestingSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		this.details = details;

		getViewState().setInvesting(details);
		getViewState().setTimeframe(selectedTimeframe);
		getViewState().setEvents(details.getEvents().getItems());
	}

	private void handleInvestingError(Throwable throwable) {
		getInvestingSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getPrograms() {
		if (dashboardManager != null && baseCurrency != null) {
			if (programsSubscription != null) {
				programsSubscription.unsubscribe();
			}

			ProgramsFilter filter = new ProgramsFilter();
			filter.setSkip(0);
			filter.setTake(TAKE);
			filter.setStatus(programsStatus.getValue());
			filter.setDateRange(dateRange);
			filter.setChartPointsCount(10);
			filter.setShowIn(CurrencyEnum.fromValue(baseCurrency.getValue()));
			programsSubscription = dashboardManager.getPrograms(filter)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetProgramsResponse,
							this::handleGetProgramsError);
		}
	}

	private void handleGetProgramsResponse(ProgramInvestingDetailsListItemsViewModel response) {
		programsSubscription.unsubscribe();
		getViewState().hideProgramsProgress();

		getViewState().setPrograms(response.getItems(), response.getTotal());
	}

	private void handleGetProgramsError(Throwable throwable) {
		programsSubscription.unsubscribe();
		getViewState().hideProgramsProgress();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getFunds() {
		if (dashboardManager != null && baseCurrency != null) {
			if (fundsSubscription != null) {
				fundsSubscription.unsubscribe();
			}

			ProgramsFilter filter = new ProgramsFilter();
			filter.setSkip(0);
			filter.setTake(TAKE);
			filter.setStatus(fundsStatus.getValue());
			filter.setDateRange(dateRange);
			filter.setChartPointsCount(10);
			filter.setShowIn(CurrencyEnum.fromValue(baseCurrency.getValue()));
			fundsSubscription = dashboardManager.getFunds(filter)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetFundsResponse,
							this::handleGetFundsError);
		}
	}

	private void handleGetFundsResponse(FundInvestingDetailsListItemsViewModel response) {
		fundsSubscription.unsubscribe();
		getViewState().hideFundsProgress();

		getViewState().setFunds(response.getItems(), response.getTotal());
	}

	private void handleGetFundsError(Throwable throwable) {
		fundsSubscription.unsubscribe();
		getViewState().hideFundsProgress();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getAssets() {
		if (coinsManager != null) {
			if (assetsSubscription != null) {
				assetsSubscription.unsubscribe();
			}

			assetsSubscription = coinsManager.getPortfolio(0, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetAssetsResponse,
							this::handleGetAssetsError);
		}
	}

	private void handleGetAssetsResponse(CoinsAssetItemsViewModel response) {
		assetsSubscription.unsubscribe();
		getViewState().hideAssetsProgress();

		getViewState().setAssets(response.getItems(), response.getTotal());
	}

	private void handleGetAssetsError(Throwable throwable) {
		assetsSubscription.unsubscribe();
		getViewState().hideAssetsProgress();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void cancelRequest(UUID requestId) {
		cancelRequestSubscription = dashboardManager.cancelRequest(requestId)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(response -> handleCancelRequestSuccess(requestId),
						this::handleCancelRequestError);
	}

	private void handleCancelRequestSuccess(UUID requestId) {
		cancelRequestSubscription.unsubscribe();

		getRequests();
	}

	private void handleCancelRequestError(Throwable throwable) {
		cancelRequestSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnCancelRequestClickedEvent event) {

		cancelRequest(event.getRequestId());
	}

	@Override
	public void onTimeframeSelected(Timeframe timeframe) {
		this.selectedTimeframe = timeframe;
		dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.fromValue(timeframe.getValue()));
		getViewState().setTimeframe(timeframe);
		getViewState().showProgress(true);
		updateAll();
	}

	@Subscribe
	public void onEventMainThread(OnRequestCancelledEvent event) {
		getRequests();
	}

	void onProgramsStatusOptionSelected(Integer position, String text) {
		switch (position) {
			case 0:
				this.programsStatus = DashboardAssetStatus.ACTIVE;
				break;
			case 1:
				this.programsStatus = DashboardAssetStatus.ALL;
				break;
		}
		settingsManager.saveInvestmentsProgramsStatus(programsStatus);
		getViewState().setProgramsStatus(text, position);
		getPrograms();
	}

	void onFundsStatusOptionSelected(Integer position, String text) {
		switch (position) {
			case 0:
				this.fundsStatus = DashboardAssetStatus.ACTIVE;
				break;
			case 1:
				this.fundsStatus = DashboardAssetStatus.ALL;
				break;
		}
		settingsManager.saveInvestmentsFundsStatus(fundsStatus);
		getViewState().setFundsStatus(text, position);
		getFunds();
	}
}
