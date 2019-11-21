package vision.genesis.clientapp.feature.main.dashboard.investments.details;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.DashboardInvestingDetails;
import io.swagger.client.model.ItemsViewModelFundDetailsList;
import io.swagger.client.model.ItemsViewModelProgramDetailsList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.filter.ProgramsFilter;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

@InjectViewState
public class InvestmentsDetailsPresenter extends MvpPresenter<InvestmentsDetailsView>
{
	private static final int TAKE = 20;

	private static final int EVENTS_TAKE = 5;

	@Inject
	public Context context;

	@Inject
	public DashboardManager dashboardManager;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public FundsManager fundsManager;

	private Subscription baseCurrencySubscription;

	private Subscription getInvestingSubscription;

	private Subscription programsSubscription;

	private Subscription fundsSubscription;

	private CurrencyEnum baseCurrency;

	private DashboardInvestingDetails details;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
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

		super.onDestroy();
	}

	void onResume() {
		updateAll();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		updateAll();
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
		getInvesting();
		getPrograms();
		getFunds();
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
		if (programsSubscription != null) {
			programsSubscription.unsubscribe();
		}

		//TODO: remove filter
		ProgramsFilter filter = new ProgramsFilter();
		filter.setSkip(0);
		filter.setTake(TAKE);
		filter.setDateRange(dateRange);
		filter.setChartPointsCount(10);
//		programsSubscription = dashboardManager.getPrograms(filter)
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribeOn(Schedulers.newThread())
//				.subscribe(this::handleGetProgramsResponse,
//						this::handleGetProgramsError);
	}

	private void handleGetProgramsResponse(ItemsViewModelProgramDetailsList response) {
		programsSubscription.unsubscribe();
		getViewState().hideProgramsProgress();

		getViewState().setPrograms(response.getItems());
	}

	private void handleGetProgramsError(Throwable throwable) {
		programsSubscription.unsubscribe();
		getViewState().hideProgramsProgress();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getFunds() {
		if (fundsSubscription != null) {
			fundsSubscription.unsubscribe();
		}

		//TODO: remove filter
		ProgramsFilter filter = new ProgramsFilter();
		filter.setSkip(0);
		filter.setTake(TAKE);
		filter.setDateRange(dateRange);
		filter.setChartPointsCount(10);
		fundsSubscription = fundsManager.getFundsList(filter)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::handleGetFundsResponse,
						this::handleGetFundsError);
	}

	private void handleGetFundsResponse(ItemsViewModelFundDetailsList response) {
		fundsSubscription.unsubscribe();
		getViewState().hideFundsProgress();

		getViewState().setFunds(response.getItems());
	}

	private void handleGetFundsError(Throwable throwable) {
		fundsSubscription.unsubscribe();
		getViewState().hideFundsProgress();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

//	@Subscribe
//	public void onEventMainThread(ShowProgramDetailsEvent event) {
//		getViewState().showProgramDetails(event.programDetailsModel);
//	}

//	@Subscribe
//	public void onEventMainThread(ShowFundDetailsEvent event) {
//		getViewState().showFundDetails(event.getFundDetailsModel());
//	}
}
