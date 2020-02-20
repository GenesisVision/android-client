package vision.genesis.clientapp.feature.main.fund.profit;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.FundProfitPercentCharts;
import io.swagger.client.model.SimpleChartPoint;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.OnFundStatisticsUpdatedEvent;
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

	@Inject
	public SettingsManager settingsManager;

	private Subscription baseCurrencySubscription;

	private Subscription absSubscription;

	private Subscription percentSubscription;

	private UUID fundId;

	private Double first;

	private Double selected;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	private CurrencyEnum baseCurrency;

	private ArrayList<Object> currencies = new ArrayList<>();

	private AbsoluteProfitChart absChart;

	private FundProfitPercentCharts percentChart;

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

	void setFundId(UUID programId) {
		this.fundId = programId;
		updateAll();
	}

	void onShow() {
		updateAll();
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

	private void updateAll() {
		getProfitAbsolute();
		getProfitPercent();
	}

	private void getProfitAbsolute() {
		if (fundsManager != null && baseCurrency != null && fundId != null) {
			if (absSubscription != null) {
				absSubscription.unsubscribe();
			}

			absSubscription = fundsManager.getProfitAbsoluteChart(fundId, chartDateRange,
					100, baseCurrency.getValue())
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
		if (fundsManager != null && fundId != null && baseCurrency != null) {
			if (percentSubscription != null) {
				percentSubscription.unsubscribe();
			}

			currencies = new ArrayList<>();
			currencies.add(baseCurrency.getValue());

			percentSubscription = fundsManager.getProfitPercentChart(fundId, chartDateRange,
					100, baseCurrency.getValue(), currencies, 1)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetPercentSuccess,
							this::handleGetPercentError);
		}
	}

	private void handleGetPercentSuccess(FundProfitPercentCharts response) {
		percentSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.percentChart = response;

		getViewState().setPercentChart(percentChart.getCharts());
		EventBus.getDefault().post(new OnFundStatisticsUpdatedEvent(fundId, percentChart.getStatistic(), baseCurrency.getValue()));
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
		updateValues();
	}

	private void updateValues() {
		if (first == null || selected == null || baseCurrency == null) {
			return;
		}

		getViewState().setValue(selected < 0, String.format(Locale.getDefault(), "%s",
				StringFormatUtil.getValueString(selected, baseCurrency.getValue())));

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
		updateValues();
	}

	@Override
	public void onStop() {
		resetValuesSelection();
	}
}
