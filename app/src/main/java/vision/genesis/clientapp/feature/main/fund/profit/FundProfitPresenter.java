package vision.genesis.clientapp.feature.main.fund.profit;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.FundProfitChart;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.chart.ProfitChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

@InjectViewState
public class FundProfitPresenter extends MvpPresenter<FundProfitView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, ProfitChartView.TouchListener
{
	@Inject
	public FundsManager fundsManager;

	private Subscription chartDataSubscription;

	private UUID fundId;

	private Double first;

	private Double selected;

	private FundProfitChart chartData;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.WEEK);

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
		if (chartDataSubscription != null)
			chartDataSubscription.unsubscribe();

		super.onDestroy();
	}

	void setFundId(UUID programId) {
		this.fundId = programId;
		getChartData();
	}

	void onShow() {
		getChartData();
	}

	private void getChartData() {
		if (fundId != null && fundsManager != null) {
			if (chartDataSubscription != null)
				chartDataSubscription.unsubscribe();
			//TODO: calculate maxPointCount
			chartDataSubscription = fundsManager.getProfitChart(fundId, chartDateRange, 30)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetChartDataSuccess,
							this::handleGetChartDataError);
		}
	}

	private void handleGetChartDataSuccess(FundProfitChart response) {
		chartDataSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.chartData = response;

		getViewState().setChartData(chartData.getEquityChart());

		resetValuesSelection();
	}

	private void handleGetChartDataError(Throwable throwable) {
		chartDataSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void resetValuesSelection() {
		first = 0.0;
		selected = 0.0;
		if (chartData.getEquityChart() != null && !chartData.getEquityChart().isEmpty()) {
			first = chartData.getEquityChart().get(0).getValue();
			selected = chartData.getEquityChart().get(chartData.getEquityChart().size() - 1).getValue();
		}
		updateValues();
	}

	private void updateValues() {
		if (first == null || selected == null)
			return;

		getViewState().setAmount(StringFormatUtil.getGvtValueString(chartData.getTotalGvtProfit()), StringFormatUtil.getBaseValueString(chartData.getTotalUsdProfit(), CurrencyEnum.USD.getValue()));
		//TODO: getBaseValueString(selected * rate
//		getViewState().setAmount(StringFormatUtil.getGvtValueString(selected), StringFormatUtil.getBaseValueString(selected, CurrencyEnum.USD.getValue()));
//		getViewState().setAmount(StringFormatUtil.getGvtValueString(selected), StringFormatUtil.getBaseValueString(selected * 7, baseCurrency.getValue()));

		Double changeValue = selected - first;
//		getViewState().setChange(changeValue < 0, StringFormatUtil.getChangePercentString(first, selected),
//				StringFormatUtil.getChangeValueString(changeValue), StringFormatUtil.getBaseValueString(changeValue * chartData.get(), baseCurrency.getValue()));
		getViewState().setChange(chartData.getProfitChangePercent() != null && chartData.getProfitChangePercent() < 0,
				chartData.getProfitChangePercent() == null ? "∞" : String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(chartData.getProfitChangePercent(), 0, 2)),
				StringFormatUtil.getChangeValueString(chartData.getTimeframeGvtProfit()),
				StringFormatUtil.getBaseValueString(chartData.getTimeframeUsdProfit(), CurrencyEnum.USD.getValue()));
		//TODO: getBaseValueString(changeValue * rate
//		getViewState().setChange(changeValue < 0,
//				StringFormatUtil.getChangePercentString(first, selected),
//				StringFormatUtil.getChangeValueString(changeValue),
//				StringFormatUtil.getBaseValueString(changeValue, CurrencyEnum.USD.getValue()));

		getViewState().setStatisticsData(chartData.getRebalances(), chartData.getSharpeRatio(), chartData.getSortinoRatio(),
				chartData.getCalmarRatio(), chartData.getMaxDrawdown());
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
