package vision.genesis.clientapp.feature.main.fund.balance;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.BalanceChartPoint;
import io.swagger.client.model.Currency;
import io.swagger.client.model.FundBalanceChart;
import io.swagger.client.model.PlatformCurrencyInfo;
import io.swagger.client.model.PlatformInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.chart.BalanceChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

@InjectViewState
public class FundBalancePresenter extends MvpPresenter<FundBalanceView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, BalanceChartView.TouchListener
{
	@Inject
	public FundsManager fundsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription baseCurrencySubscription;

	private Subscription platformInfoSubscription;

	private Subscription chartDataSubscription;

	private UUID fundId;

	private Double first;

	private Float selectedX;

	private Double selectedY;

	private FundBalanceChart chartData;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	private Currency baseCurrency;

	private List<PlatformCurrencyInfo> platformCurrencies = new ArrayList<>();

	private PlatformCurrencyInfo selectedCurrency;

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
		if (chartDataSubscription != null) {
			chartDataSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setFundId(UUID fundId) {
		this.fundId = fundId;
		getChartData();
	}

	void onShow() {
		getChartData();
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
		getChartData();
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();
	}

	private void getChartData() {
		if (fundsManager != null && fundId != null && selectedCurrency != null) {
			if (chartDataSubscription != null) {
				chartDataSubscription.unsubscribe();
			}

			chartDataSubscription = fundsManager.getBalanceChart(
					fundId, chartDateRange, 30,
					Currency.fromValue(selectedCurrency.getName()))
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetChartDataSuccess,
							this::handleGetChartDataError);
		}
	}

	private void handleGetChartDataSuccess(FundBalanceChart response) {
		chartDataSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.chartData = response;
		getViewState().setAmount(StringFormatUtil.getValueString(chartData.getBalance(), selectedCurrency.getName()));
		getViewState().setChartData(chartData.getChart());

		resetValuesSelection();
	}

	private void handleGetChartDataError(Throwable throwable) {
		chartDataSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void resetValuesSelection() {
		first = 0.0;
		selectedY = 0.0;
		if (chartData != null && chartData.getChart() != null && !chartData.getChart().isEmpty()) {
			BalanceChartPoint firstElement = chartData.getChart().get(0);
			BalanceChartPoint lastElement = chartData.getChart().get(chartData.getChart().size() - 1);
			first = firstElement.getInvestorsFunds() + firstElement.getManagerFunds();
			selectedY = lastElement.getInvestorsFunds() + lastElement.getManagerFunds();
			selectedX = (float) (lastElement.getDate() / 1000 / 60);
		}
		updateValues();
	}

	private void updateValues() {
		if (first == null || selectedX == null || selectedY == null) {
			return;
		}

		Double changeValue = selectedY - first;

		getViewState().setChange(changeValue < 0,
				StringFormatUtil.getChangePercentString(first, selectedY),
				StringFormatUtil.getValueString(changeValue, selectedCurrency.getName()));

		Long selectedDate = selectedX.longValue() * 1000 * 60;
		BalanceChartPoint selectedPoint = null;
		for (BalanceChartPoint point : chartData.getChart()) {
			if (point.getDate().equals(selectedDate)) {
				selectedPoint = point;
				break;
			}
		}
		if (selectedPoint == null) {
			selectedPoint = chartData.getChart().get(chartData.getChart().size() - 1);
		}
		getViewState().setFunds(StringFormatUtil.getValueString(selectedPoint.getManagerFunds(), selectedCurrency.getName()),
				StringFormatUtil.getValueString(selectedPoint.getInvestorsFunds(), selectedCurrency.getName()));

	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.chartDateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getChartData();
	}

	@Override
	public void onTouch(float x, float y) {
		selectedX = x;
		selectedY = (double) y;
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
