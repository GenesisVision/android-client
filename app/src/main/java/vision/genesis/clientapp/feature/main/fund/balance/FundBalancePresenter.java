package vision.genesis.clientapp.feature.main.fund.balance;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.BalanceChartPoint;
import io.swagger.client.model.FundBalanceChart;
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

	private Subscription chartDataSubscription;

	private UUID fundId;

	private Double first;

	private Double selected;

	private FundBalanceChart chartData;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().setDateRange(chartDateRange);
		getViewState().showProgress(true);
		getChartData();
	}

	@Override
	public void onDestroy() {
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

	private void getChartData() {
		if (fundId != null && fundsManager != null) {
			if (chartDataSubscription != null) {
				chartDataSubscription.unsubscribe();
			}
			//TODO: calculate maxPointCount
			chartDataSubscription = fundsManager.getBalanceChart(fundId, chartDateRange, 30)
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
			BalanceChartPoint firstElement = chartData.getChart().get(0);
			BalanceChartPoint lastElement = chartData.getChart().get(chartData.getChart().size() - 1);
			first = firstElement.getInvestorsFunds() + firstElement.getManagerFunds();
			selected = lastElement.getInvestorsFunds() + lastElement.getManagerFunds();
		}
		updateValues();
	}

	private void updateValues() {
		if (first == null || selected == null) {
			return;
		}

		//TODO: getValueString(selected * rate
		getViewState().setAmount(StringFormatUtil.getGvtValueString(selected), StringFormatUtil.getValueString(selected, CurrencyEnum.USD.getValue()));

		Double changeValue = selected - first;
		//TODO: getValueString(changeValue * rate
		getViewState().setChange(changeValue < 0,
				StringFormatUtil.getChangePercentString(first, selected),
				StringFormatUtil.getChangeValueString(changeValue),
				StringFormatUtil.getValueString(changeValue, CurrencyEnum.USD.getValue()));
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.chartDateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getChartData();
	}

	@Override
	public void onTouch(float value) {
		selected = (double) value;
		updateValues();
	}

	@Override
	public void onStop() {
		resetValuesSelection();
	}
}
