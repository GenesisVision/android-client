package vision.genesis.clientapp.feature.main.program.profit_percent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.Currency;
import io.swagger.client.model.PlatformCurrencyInfo;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramProfitPercentCharts;
import io.swagger.client.model.SimpleChart;
import io.swagger.client.model.SimpleChartPoint;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.chart.ProfitChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/11/2021.
 */

@InjectViewState
public class ProgramProfitPercentPresenter extends MvpPresenter<ProgramProfitPercentView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener, ProfitChartView.TouchListener
{
	@Inject
	public ProgramsManager programsManager;

	@Inject
	public FollowsManager followsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription platformInfoSubscription;

	private Subscription percentSubscription;

	private Double first;

	private Double selected;

	private ProgramProfitPercentCharts percentChart;

	private DateRange chartDateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	private ProgramFollowDetailsFull details;

	private List<PlatformCurrencyInfo> platformCurrencies = new ArrayList<>();

	private List<PlatformCurrencyInfo> selectedCurrencies = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().setDateRange(chartDateRange);
		getViewState().showProgress(true);
		getPlatformInfo();
	}

	@Override
	public void onDestroy() {
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}
		if (percentSubscription != null) {
			percentSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(ProgramFollowDetailsFull details) {
		this.details = details;
		getPlatformInfo();
	}

	void onShow() {
		getProfitPercent();
	}

	private void getPlatformInfo() {
		if (settingsManager != null && details != null) {
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
			if (platformCurrency.getName().equals(details.getTradingAccountInfo().getCurrency().getValue())) {
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
		if (details != null && programsManager != null && !selectedCurrencies.isEmpty()) {
			if (percentSubscription != null) {
				percentSubscription.unsubscribe();
			}

			List<Currency> currencies = new ArrayList<>();
			for (PlatformCurrencyInfo selectedCurrency : selectedCurrencies) {
				currencies.add(Currency.fromValue(selectedCurrency.getName()));
			}

			Currency baseCurrency = Currency.fromValue(selectedCurrencies.get(0).getName());
			percentSubscription = (details.getProgramDetails() != null
					? programsManager.getProfitPercentChart(details.getId(), chartDateRange, 100, baseCurrency, currencies)
					: followsManager.getProfitPercentChart(details.getId(), chartDateRange, 100, baseCurrency, currencies))
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
		SimpleChart simpleChart = percentChart.getCharts().get(0);
		if (percentChart != null && simpleChart != null) {
			List<SimpleChartPoint> chart = simpleChart.getChart();
			if (chart != null && !chart.isEmpty()) {
				if (chart.get(0) != null) {
					first = chart.get(0).getValue();
					selected = chart.get(chart.size() - 1).getValue();
				}
			}
		}
		updateValues();
	}

	private void updateValues() {
		getViewState().setValue(selected < 0, StringFormatUtil.getPercentString(selected));
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
		for (PlatformCurrencyInfo platformCurrency : platformCurrencies) {
			if (platformCurrency.getName().equals(currency)) {
				selectedCurrencies.remove(platformCurrency);
				selectedCurrencies.remove(0);
				selectedCurrencies.add(0, platformCurrency);
				onSelectedCurrenciesChanged();
				break;
			}
		}
	}
}
