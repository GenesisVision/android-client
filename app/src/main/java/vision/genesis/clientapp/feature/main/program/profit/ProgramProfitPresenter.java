package vision.genesis.clientapp.feature.main.program.profit;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramProfitPercentCharts;
import io.swagger.client.model.SimpleChartPoint;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
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
	public FollowsManager followsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription baseCurrencySubscription;

	private Subscription absSubscription;

	private Subscription percentSubscription;

	private Double first;

	private Double selected;

	private AbsoluteProfitChart absChart;

	private ProgramProfitPercentCharts percentChart;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	private ProgramFollowDetailsFull details;

	private List<Object> currencies = new ArrayList<>();

	private CurrencyEnum baseCurrency;

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
		if (absSubscription != null) {
			absSubscription.unsubscribe();
		}
		if (percentSubscription != null) {
			percentSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(ProgramFollowDetailsFull details) {
		this.details = details;
		updateAll();
	}

	void onShow() {
		updateAll();
	}

	private void updateAll() {
		getProfitAbsolute();
		getProfitPercent();
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
		updateAll();
	}

	private void getProfitAbsolute() {
		if (baseCurrency != null && details != null && programsManager != null) {
			if (absSubscription != null) {
				absSubscription.unsubscribe();
			}

			absSubscription = (details.getProgramDetails() != null
					? programsManager.getProfitAbsoluteChart(details.getId(), chartDateRange, 100, details.getTradingAccountInfo().getCurrency().getValue())
					: followsManager.getProfitAbsoluteChart(details.getId(), chartDateRange, 100, details.getTradingAccountInfo().getCurrency().getValue()))
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

	private void getProfitPercent() {
		if (baseCurrency != null && details != null && programsManager != null) {
			if (percentSubscription != null) {
				percentSubscription.unsubscribe();
			}

			currencies = new ArrayList<>();
			currencies.add(baseCurrency);

			percentSubscription = (details.getProgramDetails() != null
					? programsManager.getProfitPercentChart(details.getId(), chartDateRange, 100, baseCurrency.getValue(), currencies)
					: followsManager.getProfitPercentChart(details.getId(), chartDateRange, 100, baseCurrency.getValue(), currencies))
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetPercentSuccess,
							this::handleGetPercentError);
		}
	}

	private void handleGetPercentSuccess(ProgramProfitPercentCharts response) {
		percentSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.percentChart = response;

		getViewState().setPercentChart(percentChart.getCharts());
		getViewState().updateStatistics(percentChart.getStatistic());
//		resetValuesSelection();
	}

	private void handleGetPercentError(Throwable throwable) {
		percentSubscription.unsubscribe();
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
		getViewState().setChangeVisibility(false);
		updateValues();
	}

	private void updateValues() {
		if (first == null || selected == null) {
			getViewState().setChangeVisibility(false);
			if (details.getTradingAccountInfo() != null && details.getTradingAccountInfo().getCurrency() != null) {
				getViewState().setValue(absChart.getProfit() < 0, String.format(Locale.getDefault(), "%s",
						StringFormatUtil.getValueString(absChart.getProfit(), details.getTradingAccountInfo().getCurrency().getValue())));
			}
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

		if (details.getTradingAccountInfo() != null && details.getTradingAccountInfo().getCurrency() != null) {
			getViewState().setValue(changeValue < 0, String.format(Locale.getDefault(), "%s",
					StringFormatUtil.getValueString(changeValue, details.getTradingAccountInfo().getCurrency().getValue())));
		}

//		if (absChart != null & absChart.getStatistic() != null) {
//			getViewState().setStatisticsData(absChart.getStatistic().getTrades(), absChart.getStatistic().getSuccessTradesPercent(),
//					absChart.getStatistic().getProfitFactor(), absChart.getStatistic().getSharpeRatio(), absChart.getStatistic().getSortinoRatio(),
//					absChart.getStatistic().getCalmarRatio(), absChart.getStatistic().getMaxDrawdown());
//		}
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.chartDateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		updateAll();
	}

	@Override
	public void onTouch(float value) {
		selected = (double) value;
//		getViewState().setChangeVisibility(true);
		updateValues();
	}

	@Override
	public void onStop() {
		resetValuesSelection();
	}
}
