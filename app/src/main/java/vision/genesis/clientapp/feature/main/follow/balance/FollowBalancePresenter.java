package vision.genesis.clientapp.feature.main.follow.balance;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.AccountBalanceChart;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.SimpleChartPoint;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.chart.BalanceChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/12/2018.
 */

@InjectViewState
public class FollowBalancePresenter extends MvpPresenter<FollowBalanceView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, BalanceChartView.TouchListener
{
	@Inject
	public FollowsManager followsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription baseCurrencySubscription;

	private Subscription chartDataSubscription;

	private Double first;

	private Double selected;

	private AccountBalanceChart chartData;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

//	private Currency baseCurrency;

	private ProgramFollowDetailsFull details;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().setDateRange(chartDateRange);
		getViewState().showProgress(true);
//		subscribeToBaseCurrency();
		getChartData();
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
//		this.baseCurrency = Currency.fromValue(baseCurrency.getValue());
		getChartData();
	}

	private void getChartData() {
		if (details != null && followsManager != null) {
			if (chartDataSubscription != null) {
				chartDataSubscription.unsubscribe();
			}

			chartDataSubscription = followsManager.getBalanceChart(details.getId(), chartDateRange, 30, details.getTradingAccountInfo().getCurrency())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetFollowChartDataSuccess,
							this::handleGetChartDataError);
		}
	}

	private void handleGetFollowChartDataSuccess(AccountBalanceChart response) {
		chartDataSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.chartData = response;

		getViewState().setAmount(StringFormatUtil.getValueString(chartData.getBalance(), details.getTradingAccountInfo().getCurrency().getValue()));
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
			SimpleChartPoint lastElement = chartData.getChart().get(chartData.getChart().size() - 1);
			first = firstElement.getValue();
//			selected = lastElement.getInvestorsFunds() + lastElement.getManagerFunds() + lastElement.getProfit();
			selected = chartData.getBalance();
		}
		updateValues();
	}

	private void updateValues() {
		if (first == null || selected == null) {
			return;
		}

//		getViewState().setAmount(StringFormatUtil.getGvtValueString(chartData.getGvtBalance()), StringFormatUtil.getValueString(chartData.getProgramCurrencyBalance(), chartData.getProgramCurrency().getValue()));
		//TODO: getValueString(selected * rate
//		getViewState().setAmount(StringFormatUtil.getGvtValueString(selected), StringFormatUtil.getValueString(selected, chartData.getProgramCurrency().getValue()));
//		getViewState().setAmount(StringFormatUtil.getGvtValueString(selected), StringFormatUtil.getValueString(selected * 7, baseCurrency.getValue()));

		Double changeValue = selected - first;
//		getViewState().setChange(changeValue < 0, StringFormatUtil.getChangePercentString(first, selected),
//				StringFormatUtil.getChangeValueString(changeValue), StringFormatUtil.getValueString(changeValue * chartData.get(), baseCurrency.getValue()));
//		getViewState().setChange(chartData.getProfitChangePercent() != null && chartData.getProfitChangePercent() < 0,
//				chartData.getProfitChangePercent() == null ? "∞" : String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(chartData.getProfitChangePercent(), 0, 2)),
//				StringFormatUtil.getChangeValueString(chartData.getTimeframeGvtProfit()),
//				StringFormatUtil.getValueString(chartData.getTimeframeProgramCurrencyProfit(), chartData.getProgramCurrency().getValue()));
		//TODO: getValueString(changeValue * rate
		getViewState().setChange(changeValue < 0,
				StringFormatUtil.getChangePercentString(first, selected),
				StringFormatUtil.getChangeValueString(changeValue),
				StringFormatUtil.getValueString(changeValue, details.getTradingAccountInfo().getCurrency().getValue()));
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
}
