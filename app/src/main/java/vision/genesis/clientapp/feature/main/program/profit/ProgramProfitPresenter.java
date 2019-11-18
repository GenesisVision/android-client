package vision.genesis.clientapp.feature.main.program.profit;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ProgramProfitCharts;
import io.swagger.client.model.SimpleChart;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.chart.ProfitChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2018.
 */

@InjectViewState
public class ProgramProfitPresenter extends MvpPresenter<ProgramProfitView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, ProfitChartView.TouchListener
{
	@Inject
	public ProgramsManager programsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription chartDataSubscription;

	private UUID programId;

	private Double first;

	private Double selected;

	private ProgramProfitCharts chartData;

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

	void setProgramId(UUID programId) {
		this.programId = programId;
		getChartData();
	}

	void onShow() {
		getChartData();
	}

	private void getChartData() {
		if (programId != null && programsManager != null) {
			if (chartDataSubscription != null) {
				chartDataSubscription.unsubscribe();
			}
			//TODO: calculate maxPointCount
			chartDataSubscription = programsManager.getProfitChart(programId, chartDateRange, 100)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetChartDataSuccess,
							this::handleGetChartDataError);
		}
	}

	private void handleGetChartDataSuccess(ProgramProfitCharts response) {
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
		getViewState().setChangeVisibility(false);
		updateValues();
	}

	private void updateValues() {
		if (first == null || selected == null) {
			getViewState().setChangeVisibility(false);
			return;
		}

//		getViewState().setAmount(StringFormatUtil.getGvtValueString(chartData.getTimeframeGvtProfit()), StringFormatUtil.getValueString(chartData.getTotalProgramCurrencyProfit(), chartData.getProgramCurrency().getValue()));
		//TODO: getValueString(selected * rate
//		getViewState().setAmount(StringFormatUtil.getGvtValueString(selected), StringFormatUtil.getValueString(selected, chartData.getProgramCurrency().getValue()));
//		getViewState().setAmount(StringFormatUtil.getGvtValueString(selected), StringFormatUtil.getValueString(selected * 7, baseCurrency.getValue()));

		Double changeValue = selected;
//		getViewState().setChange(changeValue < 0, StringFormatUtil.getChangePercentString(first, selected),
//				StringFormatUtil.getChangeValueString(changeValue), StringFormatUtil.getValueString(changeValue * chartData.get(), baseCurrency.getValue()));
//		getViewState().setChange(chartData.getProfitChangePercent() != null && chartData.getProfitChangePercent() < 0,
//				chartData.getProfitChangePercent() == null ? "∞" : String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(chartData.getProfitChangePercent(), 0, 2)),
//				StringFormatUtil.getChangeValueString(chartData.getTimeframeGvtProfit()),
//				StringFormatUtil.getValueString(chartData.getTimeframeProgramCurrencyProfit(), chartData.getProgramCurrency().getValue()));

		getViewState().setChange(changeValue < 0, String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(changeValue, 0, 4)));

		if (chartData != null & chartData.getStatistic() != null) {
			getViewState().setStatisticsData(chartData.getStatistic().getTrades(), chartData.getStatistic().getSuccessTradesPercent(),
					chartData.getStatistic().getProfitFactor(), chartData.getStatistic().getSharpeRatio(), chartData.getStatistic().getSortinoRatio(),
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
		getViewState().setChangeVisibility(true);
		updateValues();
	}

	@Override
	public void onStop() {
		resetValuesSelection();
	}
}
