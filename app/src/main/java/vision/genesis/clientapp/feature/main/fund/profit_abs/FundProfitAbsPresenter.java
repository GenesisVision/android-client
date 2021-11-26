package vision.genesis.clientapp.feature.main.fund.profit_abs;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.Currency;
import io.swagger.client.model.PlatformCurrencyInfo;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.SimpleChartPoint;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.chart.ProfitChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

@InjectViewState
public class FundProfitAbsPresenter extends MvpPresenter<FundProfitAbsView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, ProfitChartView.TouchListener
{
	@Inject
	public FundsManager fundsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription baseCurrencySubscription;

	private Subscription platformInfoSubscription;

	private Subscription absSubscription;

	private UUID fundId;

	private Double first;

	private Double selected;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	private Currency baseCurrency;

	private List<PlatformCurrencyInfo> platformCurrencies = new ArrayList<>();

	private PlatformCurrencyInfo selectedCurrency;

	private AbsoluteProfitChart absChart;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().setDateRange(chartDateRange);
		getViewState().showProgress(true);

		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}
		if (absSubscription != null) {
			absSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setFundId(UUID programId) {
		this.fundId = programId;
		getProfitAbs();
	}

	void onShow() {
		getProfitAbs();
	}

	private void subscribeToBaseCurrency() {
		if (settingsManager != null) {
			baseCurrencySubscription = settingsManager.getBaseCurrency()
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::baseCurrencyChangedHandler);
		}
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = Currency.fromValue(baseCurrency.getValue());
		getPlatformInfo();
	}

	private void getPlatformInfo() {
		if (settingsManager != null) {
			platformInfoSubscription = settingsManager.getPlatformInfo()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPlatformInfoSuccess,
							this::handleGetPlatformInfoError);
		}
	}

	private void handleGetPlatformInfoSuccess(PlatformInfo platformInfo) {
		platformInfoSubscription.unsubscribe();

		platformCurrencies = platformInfo.getCommonInfo().getPlatformCurrencies();

		for (PlatformCurrencyInfo platformCurrency : platformCurrencies) {
			if (platformCurrency.getName().equals(baseCurrency.getValue())) {
				selectedCurrency = platformCurrency;
				break;
			}
		}
		onSelectedCurrencyChanged();
	}

	private void onSelectedCurrencyChanged() {
		getViewState().setCurrency(selectedCurrency);
		getProfitAbs();
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();
	}

	private void getProfitAbs() {
		if (fundsManager != null && selectedCurrency != null && fundId != null) {
			if (absSubscription != null) {
				absSubscription.unsubscribe();
			}

			absSubscription = fundsManager.getProfitAbsoluteChart(fundId, chartDateRange,
					100, Currency.fromValue(selectedCurrency.getName()))
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetAbsSuccess,
							this::handleGetAbsDataError);
		}
	}

	private void handleGetAbsSuccess(AbsoluteProfitChart response) {
		absSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.absChart = response;

		getViewState().setAbsChart(absChart.getChart());
		resetValuesSelection();
	}

	private void handleGetAbsDataError(Throwable throwable) {
		absSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void resetValuesSelection() {
		first = 0.0;
		selected = 0.0;
		if (absChart != null && absChart.getChart() != null) {
			List<SimpleChartPoint> chart = absChart.getChart();
			if (chart != null && !chart.isEmpty()) {
				if (chart.get(0) != null) {
					first = chart.get(0).getValue();
					selected = chart.get(absChart.getChart().size() - 1).getValue();
				}
			}
		}
		updateValues();
	}

	private void updateValues() {
		if (first == null || selected == null || selectedCurrency == null) {
			return;
		}

		getViewState().setValue(selected < 0, String.format(Locale.getDefault(), "%s",
				StringFormatUtil.getValueString(selected, selectedCurrency.getName())));
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.chartDateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getProfitAbs();
	}

	@Override
	public void onTouch(float value, float x) {
		selected = (double) value;
		updateValues();
	}

	@Override
	public void onStop() {
		resetValuesSelection();
	}

	public void onAssetClicked(PlatformCurrencyInfo asset) {
		ArrayList<String> optionsList = new ArrayList<>();
		for (PlatformCurrencyInfo platformCurrency : platformCurrencies) {
			if (!platformCurrency.equals(asset)) {
				optionsList.add(platformCurrency.getName());
			}
		}
		getViewState().showChangeBaseCurrencyList(optionsList);
	}

	public void onChangeBaseCurrencySelected(String currency) {
		for (PlatformCurrencyInfo platformCurrency : platformCurrencies) {
			if (platformCurrency.getName().equals(currency)) {
				selectedCurrency = platformCurrency;
				onSelectedCurrencyChanged();
				break;
			}
		}
	}
}
