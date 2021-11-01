package vision.genesis.clientapp.feature.main.dashboard.investments.programs;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.DashboardAssetStatus;
import io.swagger.client.model.ProgramInvestingDetailsListItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.filter.ProgramsFilter;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2020.
 */

@InjectViewState
public class ProgramsPortfolioPresenter extends MvpPresenter<ProgramsPortfolioView>
{
	private static final int TAKE = 1000;

	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public DashboardManager dashboardManager;

	private Subscription baseCurrencySubscription;

	private Subscription programsSubscription;

	private CurrencyEnum baseCurrency;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.DAY);

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
		if (programsSubscription != null) {
			programsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getPrograms();
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
		getPrograms();
	}

	private void getPrograms() {
		if (dashboardManager != null && baseCurrency != null) {
			if (programsSubscription != null) {
				programsSubscription.unsubscribe();
			}

			ProgramsFilter filter = new ProgramsFilter();
			filter.setSkip(0);
			filter.setTake(TAKE);
			filter.setStatus(DashboardAssetStatus.ACTIVE.getValue());
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
		getViewState().showProgress(false);

		getViewState().setPrograms(response.getItems(), response.getTotal());
	}

	private void handleGetProgramsError(Throwable throwable) {
		programsSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

}
