package vision.genesis.clientapp.feature.main.program.balance;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ProgramBalanceChart;
import io.swagger.client.model.ProgramBalanceChartElement;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.SettingsManager;
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

	private Subscription chartDataSubscription;

	private UUID programId;

	private Double first;

	private Double selected;

	private ProgramBalanceChart chartData;

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

	void setProgramId(UUID programId) {
		this.programId = programId;
		getChartData();
	}

	void onShow() {
		getChartData();
	}

	private void getChartData() {
		if (programId != null && programsManager != null) {
			if (chartDataSubscription != null)
				chartDataSubscription.unsubscribe();
			//TODO: calculate maxPointCount
			chartDataSubscription = programsManager.getBalanceChart(programId, chartDateRange, 30)
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

		getViewState().setChartData(chartData.getBalanceChart());

		resetValuesSelection();
	}

	private void handleGetChartDataError(Throwable throwable) {
		chartDataSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void resetValuesSelection() {
		first = 0.0;
		selected = 0.0;
		if (chartData.getBalanceChart() != null && !chartData.getBalanceChart().isEmpty()) {
			ProgramBalanceChartElement firstElement = chartData.getBalanceChart().get(0);
			ProgramBalanceChartElement lastElement = chartData.getBalanceChart().get(chartData.getBalanceChart().size() - 1);
			first = firstElement.getInvestorsFunds() + firstElement.getManagerFunds() + firstElement.getProfit();
			selected = lastElement.getInvestorsFunds() + lastElement.getManagerFunds() + lastElement.getProfit();
		}
		updateValues();
	}

	private void updateValues() {
		if (first == null || selected == null)
			return;

//		getViewState().setAmount(StringFormatUtil.getGvtValueString(chartData.getTotalGvtProfit()), StringFormatUtil.getBaseValueString(chartData.getTotalProgramCurrencyProfit(), chartData.getProgramCurrency().getValue()));
		//TODO: getBaseValueString(selected * rate
		getViewState().setAmount(StringFormatUtil.getGvtValueString(selected), StringFormatUtil.getBaseValueString(selected, chartData.getProgramCurrency().getValue()));
//		getViewState().setAmount(StringFormatUtil.getGvtValueString(selected), StringFormatUtil.getBaseValueString(selected * 7, baseCurrency.getValue()));

		Double changeValue = selected - first;
//		getViewState().setChange(changeValue < 0, StringFormatUtil.getChangePercentString(first, selected),
//				StringFormatUtil.getChangeValueString(changeValue), StringFormatUtil.getBaseValueString(changeValue * chartData.get(), baseCurrency.getValue()));
//		getViewState().setChange(chartData.getProfitChangePercent() != null && chartData.getProfitChangePercent() < 0,
//				chartData.getProfitChangePercent() == null ? "∞" : String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(chartData.getProfitChangePercent(), 0, 2)),
//				StringFormatUtil.getChangeValueString(chartData.getTimeframeGvtProfit()),
//				StringFormatUtil.getBaseValueString(chartData.getTimeframeProgramCurrencyProfit(), chartData.getProgramCurrency().getValue()));
		//TODO: getBaseValueString(changeValue * rate
		getViewState().setChange(changeValue < 0,
				StringFormatUtil.getChangePercentString(first, selected),
				StringFormatUtil.getChangeValueString(changeValue),
				StringFormatUtil.getBaseValueString(changeValue, chartData.getProgramCurrency().getValue()));
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
