package vision.genesis.clientapp.feature.main.dashboard.investor.header;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.events.OnPortfolioChartViewModeChangedEvent;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/08/2018.
 */

@InjectViewState
public class InvestorDashboardHeaderPortfolioPresenter extends MvpPresenter<InvestorDashboardHeaderPortfolioView>
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	private Subscription baseCurrencySubscription;

//	private DashboardChartValue chartValue;

	private Boolean isViewMode = false;

	private CurrencyEnum baseCurrency = CurrencyEnum.BTC;

	private Double first;

	private Double selected;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onShow() {
	}

//	void setData(DashboardChartValue chartValue) {
//		this.chartValue = chartValue;
//		resetValuesSelection();
//	}

	private void resetValuesSelection() {
//		selected = chartValue.getValue();
//		if (!chartValue.getBalanceChart().isEmpty()) {
//			first = chartValue.getBalanceChart().get(0).getValue();
//		}
//		else {
//			first = selected;
//		}
//		getViewState().hideHighlight();
//		updateValues();
	}

	private void subscribeToBaseCurrency() {
		baseCurrencySubscription = settingsManager.getBaseCurrency()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::baseCurrencyChangedHandler);
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;

		updateValues();
	}

	public void onPortfolioChartTouch(int lineIndex, int barIndex, float chartBottomY) {
//		if (!isViewMode) {
//			isViewMode = true;
//			getViewState().hideRequests();
//			EventBus.getDefault().post(new OnPortfolioChartViewModeChangedEvent(isViewMode, chartBottomY));
//		}
//
//		EventBus.getDefault().post(new OnPortfolioAssetsChangedEvent(barIndex));
//
//		selected = chartValue.getBalanceChart().get(lineIndex).getValue();
//
//		updateValues();
	}

	private void updateValues() {
//		if (first == null || selected == null)
//			return;
//
//		getViewState().setBalance(StringFormatUtil.getGvtValueString(selected),
//				StringFormatUtil.getValueString(selected * chartValue.getRate(), baseCurrency.getValue()));
//
//		Double changeValue = selected - first;
//		getViewState().setChange(changeValue < 0,
//				StringFormatUtil.getChangePercentString(first, selected),
//				StringFormatUtil.getChangeValueString(changeValue),
//				StringFormatUtil.getValueString(changeValue * chartValue.getRate(), baseCurrency.getValue()));
	}

	public void chartViewModeTurnOff() {
		if (isViewMode) {
			isViewMode = false;
			getViewState().showRequests();
			resetValuesSelection();
			EventBus.getDefault().post(new OnPortfolioChartViewModeChangedEvent(isViewMode, 0f));
		}
	}

	public void setInRequestsData(Double totalValue, Double rate) {
		getViewState().setInRequests(
//				StringFormatUtil.getChangeValueString(totalValue),
				StringFormatUtil.getGvtValueString(totalValue),
				StringFormatUtil.getValueString(totalValue * rate, baseCurrency.getValue()));
	}
}
