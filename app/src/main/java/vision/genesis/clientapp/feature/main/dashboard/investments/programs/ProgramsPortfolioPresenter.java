package vision.genesis.clientapp.feature.main.dashboard.investments.programs;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.ProgramInvestingDetailsListItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.filter.ProgramsFilter;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

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

	private List<SortingEnum> orderByOptions = new ArrayList<>();

	private SortingEnum orderBy = SortingEnum.BYTITLEDESC;

	private Integer selectedOrderByPosition = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		initOrderBy();

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

	private void initOrderBy() {
		ArrayList<String> orderByStrings = new ArrayList<>();

		orderByStrings.add(StringFormatUtil.capitalize(context.getString(R.string.name)));
		orderByStrings.add(StringFormatUtil.capitalize(context.getString(R.string.value)));
		orderByStrings.add(StringFormatUtil.capitalize(context.getString(R.string.profit)));
		orderByStrings.add(StringFormatUtil.capitalize(context.getString(R.string.period)));

		getViewState().setOrderByOptions(orderByStrings);
		getViewState().setOrderBy(orderByStrings.get(selectedOrderByPosition), selectedOrderByPosition);

		orderByOptions = new ArrayList<>();
		orderByOptions.add(SortingEnum.BYTITLEDESC);
		orderByOptions.add(SortingEnum.BYVALUEDESC);
		orderByOptions.add(SortingEnum.BYPROFITDESC);
		orderByOptions.add(SortingEnum.BYPERIODDESC);
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
			filter.setStatus(settingsManager.getSavedInvestmentsProgramsStatus().getValue());
			filter.setDateRange(dateRange);
			filter.setChartPointsCount(10);
			filter.setShowIn(CurrencyEnum.fromValue(baseCurrency.getValue()));
			filter.setSorting(orderBy);
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

	public void onOrderByOptionSelected(Integer position, String text) {
		this.selectedOrderByPosition = position;
		this.orderBy = orderByOptions.get(position);
		getViewState().setOrderBy(text, position);

		getViewState().showProgress(true);
		getPrograms();
	}
}
