package vision.genesis.clientapp.feature.main.dashboard.investments.funds;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.DashboardAssetStatus;
import io.swagger.client.model.FundInvestingDetailsListItemsViewModel;
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
public class FundsPortfolioPresenter extends MvpPresenter<FundsPortfolioView>
{
	private static final int TAKE = 1000;

	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public DashboardManager dashboardManager;

	private Subscription baseCurrencySubscription;

	private Subscription fundsSubscription;

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
		if (fundsSubscription != null) {
			fundsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getFunds();
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
		getFunds();
	}

	private void getFunds() {
		if (dashboardManager != null && baseCurrency != null) {
			if (fundsSubscription != null) {
				fundsSubscription.unsubscribe();
			}

			ProgramsFilter filter = new ProgramsFilter();
			filter.setSkip(0);
			filter.setTake(TAKE);
			filter.setStatus(DashboardAssetStatus.ACTIVE.getValue());
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
		getViewState().showProgress(false);

		getViewState().setFunds(response.getItems(), response.getTotal());
	}

	private void handleGetFundsError(Throwable throwable) {
		fundsSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

}
