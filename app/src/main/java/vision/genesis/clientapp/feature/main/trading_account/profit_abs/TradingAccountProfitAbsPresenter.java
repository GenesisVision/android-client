package vision.genesis.clientapp.feature.main.trading_account.profit_abs;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.AccountProfitPercentCharts;
import io.swagger.client.model.Currency;
import io.swagger.client.model.PlatformCurrencyInfo;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.PrivateTradingAccountFull;
import io.swagger.client.model.SimpleChartPoint;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.TradingAccountManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.OnChartBaseCurrencyChangedEvent;
import vision.genesis.clientapp.ui.chart.ProfitChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

@InjectViewState
public class TradingAccountProfitAbsPresenter extends MvpPresenter<TradingAccountProfitAbsView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, ProfitChartView.TouchListener
{
	@Inject
	public TradingAccountManager tradingAccountManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription platformInfoSubscription;

	private Subscription absSubscription;


	private Double first;

	private Double selected;

	private AbsoluteProfitChart absChart;

	private AccountProfitPercentCharts percentChart;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	private PrivateTradingAccountFull details;

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
		if (absSubscription != null) {
			absSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(PrivateTradingAccountFull details) {
		this.details = details;
		getPlatformInfo();
	}

	void onShow() {
		getProfitAbs();
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
		getProfitAbs();
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();
	}

	private void getProfitAbs() {
		if (selectedCurrency != null && details != null && tradingAccountManager != null) {
			if (absSubscription != null) {
				absSubscription.unsubscribe();
			}

			absSubscription = tradingAccountManager.getProfitAbsoluteChart(
					details.getId(), chartDateRange, 100,
					Currency.fromValue(selectedCurrency.getName()))
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
		if (first == null || selected == null || selectedCurrency == null) {
			return;
		}

		getViewState().setValue(selected < 0, String.format(Locale.getDefault(), "%s",
				StringFormatUtil.getValueString(selected, selectedCurrency.getName())));
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.chartDateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getProfitAbs();
	}

	@Override
	public void onTouch(float value, float x) {
		selected = (double) value;
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
