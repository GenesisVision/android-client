package vision.genesis.clientapp.feature.main.dashboard.investor.header;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.DashboardChartValue;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.events.OnPortfolioChartTouchEvent;
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

	private DashboardChartValue chartValue;

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
		if (baseCurrencySubscription != null)
			baseCurrencySubscription.unsubscribe();

		super.onDestroy();
	}

	void onShow() {
	}

	void setData(DashboardChartValue chartValue) {
		this.chartValue = chartValue;
		first = chartValue.getChart().get(0).getValue();
		selected = chartValue.getChart().get(chartValue.getChart().size() - 1).getValue();
		updateValues();
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

	public void onPortfolioChartTouch(int index) {
		if (isViewMode != null) {
			isViewMode = true;
			getViewState().hideRequests();
			EventBus.getDefault().post(new OnPortfolioChartTouchEvent());
		}

		EventBus.getDefault().post(new OnPortfolioAssetsChangedEvent(chartValue.getBars().get(index)));

		selected = chartValue.getChart().get(index).getValue();

		updateValues();
	}

	private void updateValues() {
		if (first == null || selected == null)
			return;

		getViewState().setBalance(getGvtValueString(selected), getBaseValueString(selected * chartValue.getRate()));

		Double changeValue = selected - first;
		getViewState().setChange(changeValue < 0, getChangePercentString(first, selected),
				getChangeValueString(changeValue), getBaseValueString(changeValue * chartValue.getRate()));
	}

	private String getGvtValueString(Double gvtValue) {
		return String.format(Locale.getDefault(), "%s GVT", StringFormatUtil.formatCurrencyAmount(gvtValue, CurrencyEnum.GVT.getValue()));
	}

	private String getBaseValueString(Double baseValue) {
		return String.format(Locale.getDefault(), "%s %s", StringFormatUtil.formatCurrencyAmount(baseValue, baseCurrency.getValue()), baseCurrency.getValue());
	}

	private String getChangePercentString(Double first, Double last) {
		return String.format(Locale.getDefault(), "%s%%",
				StringFormatUtil.formatAmount(Math.abs(first != 0 ? 100 / first * (first - last) : 0), 0, 2));
	}

	private String getChangeValueString(Double changeValue) {
		return String.format(Locale.getDefault(), "%s%s GVT", changeValue > 0 ? "+" : "",
				StringFormatUtil.formatCurrencyAmount(changeValue, CurrencyEnum.GVT.getValue()));
	}
}
