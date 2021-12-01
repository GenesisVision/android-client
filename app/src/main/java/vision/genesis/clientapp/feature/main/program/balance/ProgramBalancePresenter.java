package vision.genesis.clientapp.feature.main.program.balance;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.BalanceChartPoint;
import io.swagger.client.model.Currency;
import io.swagger.client.model.PlatformCurrencyInfo;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.ProgramBalanceChart;
import io.swagger.client.model.ProgramFollowDetailsFull;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.OnChartBaseCurrencyChangedEvent;
import vision.genesis.clientapp.ui.chart.BalanceChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/10/2018.
 */

@InjectViewState
public class ProgramBalancePresenter extends MvpPresenter<ProgramBalanceView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, BalanceChartView.TouchListener
{
	@Inject
	public ProgramsManager programsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription platformInfoSubscription;

	private Subscription chartDataSubscription;

	private Double first;

	private Float selectedX;

	private Double selectedY;

	private ProgramBalanceChart chartData;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	private ProgramFollowDetailsFull details;

	private List<PlatformCurrencyInfo> platformCurrencies = new ArrayList<>();

	private PlatformCurrencyInfo selectedCurrency;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

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

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(ProgramFollowDetailsFull details) {
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
		if (selectedCurrency != null && details != null && programsManager != null) {
			if (chartDataSubscription != null) {
				chartDataSubscription.unsubscribe();
			}

			chartDataSubscription = programsManager.getBalanceChart(
					details.getId(), chartDateRange, 30,
					Currency.fromValue(selectedCurrency.getName()))
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetChartDataSuccess,
							this::handleGetChartDataError);
		}
	}

	private void handleGetChartDataSuccess(ProgramBalanceChart response) {
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
			selectedY = chartData.getBalance();
			selectedX = (float) (lastElement.getDate() / 1000 / 60);
		}
		updateValues();
	}

	private void updateValues() {
		if (first == null || selectedX == null || selectedY == null || selectedCurrency == null) {
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
		EventBus.getDefault().post(new OnChartBaseCurrencyChangedEvent(details.getId(), currency));
	}

	@Subscribe
	public void onEventMainThread(OnChartBaseCurrencyChangedEvent event) {
		if (details == null || details.getId() != event.getAssetId()) {
			return;
		}
		for (PlatformCurrencyInfo platformCurrency : platformCurrencies) {
			if (platformCurrency.getName().equals(event.getCurrency())) {
				selectedCurrency = platformCurrency;
				onSelectedCurrencyChanged();
				break;
			}
		}
	}
}
