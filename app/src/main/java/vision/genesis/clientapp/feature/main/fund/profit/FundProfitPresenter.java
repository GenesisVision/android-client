package vision.genesis.clientapp.feature.main.fund.profit;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.FundProfitCharts;
import io.swagger.client.model.SimpleChart;
import rx.Subscription;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.FundsManager;
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

	private FundProfitCharts chartData;

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

	void setFundId(UUID programId) {
		this.fundId = programId;
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
//			chartDataSubscription = fundsManager.getProfitChart(fundId, chartDateRange, 30)
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribeOn(Schedulers.io())
//					.subscribe(this::handleGetChartDataSuccess,
//							this::handleGetChartDataError);
		}
	}

	private void handleGetChartDataSuccess(FundProfitCharts response) {
		chartDataSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.chartData = response;

		List<SimpleChart> charts = chartData.getCharts();
		if (charts != null && !charts.isEmpty()) {
			if (charts.get(0) != null) {
				getViewState().setChartData(charts.get(0).getChart());
				resetValuesSelection();
			}
		}
	}

	private void handleGetChartDataError(Throwable throwable) {
		chartDataSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void resetValuesSelection() {
		first = 0.0;
		selected = 0.0;
		if (chartData != null && chartData.getCharts() != null) {
			List<SimpleChart> charts = chartData.getCharts();
			if (charts != null && !charts.isEmpty()) {
				if (charts.get(0) != null) {
					first = charts.get(0).getChart().get(0).getValue();
					selected = charts.get(0).getChart().get(chartData.getCharts().size() - 1).getValue();
				}
			}
		}
		updateValues();
	}

	private void updateValues() {
		if (first == null || selected == null) {
			return;
		}

		getViewState().setValue(selected < 0, String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(selected, 0, 4)));
		if (chartData != null && chartData.getStatistic() != null) {
			getViewState().setStatisticsData(chartData.getStatistic().getSharpeRatio(), chartData.getStatistic().getSortinoRatio(),
					chartData.getStatistic().getCalmarRatio(), chartData.getStatistic().getMaxDrawdown());
		}
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
