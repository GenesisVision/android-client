package vision.genesis.clientapp.feature.main.program.balance;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.BalanceChartPoint;
import io.swagger.client.model.ProgramBalanceChart;
import io.swagger.client.model.ProgramFollowDetailsFull;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
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

	private Subscription baseCurrencySubscription;

	private Subscription chartDataSubscription;

	private Double first;

	private Float selectedX;

	private Double selectedY;

	private ProgramBalanceChart chartData;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	private CurrencyEnum baseCurrency;

	private ProgramFollowDetailsFull details;

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
		if (chartDataSubscription != null) {
			chartDataSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(ProgramFollowDetailsFull details) {
		this.details = details;
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
		this.baseCurrency = baseCurrency;
		getChartData();
	}

	private void getChartData() {
		if (baseCurrency != null && details != null && programsManager != null) {
			if (chartDataSubscription != null) {
				chartDataSubscription.unsubscribe();
			}
			//TODO: calculate maxPointCount
			chartDataSubscription = programsManager.getBalanceChart(details.getId(), chartDateRange, 30, null)
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

		getViewState().setAmount(StringFormatUtil.getValueString(chartData.getBalance(), chartData.getProgramCurrency().getValue()));
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
		if (first == null || selectedX == null || selectedY == null || baseCurrency == null) {
			return;
		}

		Double changeValue = selectedY - first;

		getViewState().setChange(changeValue < 0,
				StringFormatUtil.getChangePercentString(first, selectedY),
				StringFormatUtil.getChangeValueString(changeValue),
				StringFormatUtil.getValueString(changeValue, baseCurrency.getValue()));

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
		getViewState().setFunds(StringFormatUtil.getValueString(selectedPoint.getManagerFunds(), baseCurrency.getValue()),
				StringFormatUtil.getValueString(selectedPoint.getInvestorsFunds(), baseCurrency.getValue()));

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
}
