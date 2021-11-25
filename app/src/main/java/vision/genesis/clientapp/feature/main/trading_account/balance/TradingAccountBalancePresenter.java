package vision.genesis.clientapp.feature.main.trading_account.balance;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.AccountBalanceChart;
import io.swagger.client.model.Currency;
import io.swagger.client.model.PlatformCurrencyInfo;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.PrivateTradingAccountFull;
import io.swagger.client.model.SimpleChartPoint;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.TradingAccountManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.chart.BalanceChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

@InjectViewState
public class TradingAccountBalancePresenter extends MvpPresenter<TradingAccountBalanceView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, BalanceChartView.TouchListener
{
	@Inject
	public TradingAccountManager tradingAccountManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription platformInfoSubscription;

	private Subscription chartDataSubscription;

	private Double first;

	private Double selected;

	private AccountBalanceChart chartData;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	private PrivateTradingAccountFull details;

	private List<PlatformCurrencyInfo> platformCurrencies = new ArrayList<>();

	private PlatformCurrencyInfo selectedCurrency;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().setDateRange(chartDateRange);
		getViewState().showProgress(true);
		getPlatformInfo();
	}

	@Override
	public void onDestroy() {
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}
		if (chartDataSubscription != null) {
			chartDataSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(PrivateTradingAccountFull details) {
		this.details = details;
		getPlatformInfo();
	}

	void onShow() {
		getChartData();
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
			if (platformCurrency.getName().equals(details.getTradingAccountInfo().getCurrency().getValue())) {
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
		if (selectedCurrency != null && details != null && tradingAccountManager != null) {
			if (chartDataSubscription != null) {
				chartDataSubscription.unsubscribe();
			}

			chartDataSubscription = tradingAccountManager.getBalanceChart(
					details.getId(), chartDateRange, 30,
					Currency.fromValue(selectedCurrency.getName()))
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetChartDataSuccess,
							this::handleGetChartDataError);
		}
	}

	private void handleGetChartDataSuccess(AccountBalanceChart response) {
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
		selected = 0.0;
		if (chartData != null && chartData.getChart() != null && !chartData.getChart().isEmpty()) {
			SimpleChartPoint firstElement = chartData.getChart().get(0);
			first = firstElement.getValue();
			selected = chartData.getBalance();
		}
		updateValues();
	}

	private void updateValues() {
		if (first == null || selected == null) {
			return;
		}

		Double changeValue = selected - first;

		getViewState().setChange(changeValue < 0,
				StringFormatUtil.getChangePercentString(first, selected),
				StringFormatUtil.getValueString(changeValue, selectedCurrency.getName()));
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.chartDateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getChartData();
	}

	@Override
	public void onTouch(float x, float value) {
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
