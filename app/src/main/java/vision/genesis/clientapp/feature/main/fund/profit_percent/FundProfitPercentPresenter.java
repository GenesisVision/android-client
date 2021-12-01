package vision.genesis.clientapp.feature.main.fund.profit_percent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.Currency;
import io.swagger.client.model.FundAssetsState;
import io.swagger.client.model.FundProfitPercentCharts;
import io.swagger.client.model.PlatformCurrencyInfo;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.SimpleChart;
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
import vision.genesis.clientapp.model.events.OnChartBaseCurrencyChangedEvent;
import vision.genesis.clientapp.ui.chart.ProfitChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/11/2021.
 */

@InjectViewState
public class FundProfitPercentPresenter extends MvpPresenter<FundProfitPercentView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, ProfitChartView.TouchListener
{
	@Inject
	public FundsManager fundsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription baseCurrencySubscription;

	private Subscription platformInfoSubscription;

	private Subscription percentSubscription;

	private UUID fundId;

	private Double first;

	private Double selected;

	private Long selectedDate;

	private FundProfitPercentCharts percentChart;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	private Currency baseCurrency;

	private List<PlatformCurrencyInfo> platformCurrencies = new ArrayList<>();

	private List<PlatformCurrencyInfo> selectedCurrencies = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().setDateRange(chartDateRange);
		getViewState().showProgress(true);

		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}
		if (percentSubscription != null) {
			percentSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setFundId(UUID fundId) {
		this.fundId = fundId;
		getProfitPercent();
	}

	void onShow() {
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
		this.baseCurrency = Currency.fromValue(baseCurrency.getValue());
		getPlatformInfo();
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

		selectedCurrencies = new ArrayList<>();
		for (PlatformCurrencyInfo platformCurrency : platformCurrencies) {
			if (platformCurrency.getName().equals(baseCurrency.getValue())) {
				selectedCurrencies.add(platformCurrency);
				break;
			}
		}
		onSelectedCurrenciesChanged();
	}

	private void onSelectedCurrenciesChanged() {
		getViewState().setCurrencies(selectedCurrencies, selectedCurrencies.size() != platformCurrencies.size());
		getProfitPercent();
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();
	}

	private void getProfitPercent() {
		if (baseCurrency != null && fundId != null && fundsManager != null && !selectedCurrencies.isEmpty()) {
			if (percentSubscription != null) {
				percentSubscription.unsubscribe();
			}

			List<Currency> currencies = new ArrayList<>();
			for (PlatformCurrencyInfo selectedCurrency : selectedCurrencies) {
				currencies.add(Currency.fromValue(selectedCurrency.getName()));
			}

			percentSubscription = (fundsManager.getProfitPercentChart(fundId, chartDateRange, 100, baseCurrency, currencies, 5))
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
		getViewState().updateStatistics(percentChart.getStatistic(), selectedCurrencies.get(0).getName());
		resetValuesSelection();
	}

	private void handleGetPercentError(Throwable throwable) {
		percentSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void resetValuesSelection() {
		first = 0.0;
		selected = 0.0;
		selectedDate = null;
		SimpleChart simpleChart = percentChart.getCharts().get(0);
		if (percentChart != null && simpleChart != null) {
			List<SimpleChartPoint> chart = simpleChart.getChart();
			if (chart != null && !chart.isEmpty()) {
				if (chart.get(0) != null) {
					first = chart.get(0).getValue();
					selected = chart.get(chart.size() - 1).getValue();
					selectedDate = chart.get(chart.size() - 1).getDate();
				}
			}
		}
		updateValues();
	}

	private void updateValues() {
		getViewState().setValue(selected < 0, StringFormatUtil.getPercentString(selected));
		if (selectedDate != null) {
			for (FundAssetsState assetState : percentChart.getAssets()) {
				if (assetState.getDate().equals(selectedDate)) {
					getViewState().setAssets(assetState.getAssets());
					break;
				}
			}
		}
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.chartDateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getProfitPercent();
	}

	@Override
	public void onTouch(float value, float x) {
		selected = (double) value;
		selectedDate = (Double.valueOf(x)).longValue() * 1000 * 60;
		updateValues();
	}

	@Override
	public void onStop() {
		resetValuesSelection();
	}


	public void onAssetClicked(PlatformCurrencyInfo asset, int position) {
		if (position > 0 && selectedCurrencies.size() < platformCurrencies.size()) {
			getViewState().showReplaceCurrenciesList(getCurrenciesOptionsList(), asset);
		}
		else if (position == 0) {
			ArrayList<String> optionsList = new ArrayList<>();
			for (PlatformCurrencyInfo platformCurrency : platformCurrencies) {
				if (!platformCurrency.equals(asset)) {
					optionsList.add(platformCurrency.getName());
				}
			}
			getViewState().showChangeBaseCurrenciesList(optionsList);
		}
	}

	public void onRemoveAssetClicked(PlatformCurrencyInfo asset) {
		selectedCurrencies.remove(asset);
		onSelectedCurrenciesChanged();
	}

	public void onAddAssetClicked() {
		getViewState().showAddCurrenciesList(getCurrenciesOptionsList());
	}

	private ArrayList<String> getCurrenciesOptionsList() {
		ArrayList<String> optionsList = new ArrayList<>();
		outer:
		for (PlatformCurrencyInfo platformCurrency : platformCurrencies) {
			for (PlatformCurrencyInfo selectedCurrency : selectedCurrencies) {
				if (platformCurrency.getName().equals(selectedCurrency.getName())) {
					continue outer;
				}
			}
			optionsList.add(platformCurrency.getName());
		}
		return optionsList;
	}

	public void onAddCurrencySelected(String currency) {
		for (PlatformCurrencyInfo platformCurrency : platformCurrencies) {
			if (platformCurrency.getName().equals(currency)) {
				selectedCurrencies.add(platformCurrency);
				onSelectedCurrenciesChanged();
				break;
			}
		}
	}

	public void onReplaceCurrencySelected(String currency, PlatformCurrencyInfo assetToReplace) {
		for (PlatformCurrencyInfo platformCurrency : platformCurrencies) {
			if (platformCurrency.getName().equals(currency)) {
				int index = selectedCurrencies.indexOf(assetToReplace);
				selectedCurrencies.remove(assetToReplace);
				selectedCurrencies.add(index, platformCurrency);
				onSelectedCurrenciesChanged();
				break;
			}
		}
	}

	public void onChangeBaseCurrencySelected(String currency) {
		EventBus.getDefault().post(new OnChartBaseCurrencyChangedEvent(fundId, currency));
	}

	@Subscribe
	public void onEventMainThread(OnChartBaseCurrencyChangedEvent event) {
		if (fundId != event.getAssetId()) {
			return;
		}
		for (PlatformCurrencyInfo platformCurrency : platformCurrencies) {
			if (platformCurrency.getName().equals(event.getCurrency())) {
				selectedCurrencies.remove(platformCurrency);
				selectedCurrencies.remove(0);
				selectedCurrencies.add(0, platformCurrency);
				onSelectedCurrenciesChanged();
				break;
			}
		}
	}
}
