package vision.genesis.clientapp.feature.main.dashboard.trading.details;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import io.swagger.client.model.DashboardTradingDetails;
import io.swagger.client.model.ItemsViewModelDashboardTradingAsset;
import io.swagger.client.model.Timeframe;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.timeframe_profit.TimeframeProfitView;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

@InjectViewState
public class TradingDetailsPresenter extends MvpPresenter<TradingDetailsView> implements TimeframeProfitView.Listener
{
	private static final int TAKE = 100;

	private static final int EVENTS_TAKE = 5;

	@Inject
	public Context context;

	@Inject
	public DashboardManager dashboardManager;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public ProgramsManager programsManager;

	@Inject
	public FundsManager fundsManager;

	private Subscription baseCurrencySubscription;

	private Subscription getTradingSubscription;

	private Subscription privateSubscription;

	private Subscription publicSubscription;

	private CurrencyEnum baseCurrency;

	private DashboardTradingDetails details;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.DAY);

	private Timeframe selectedTimeframe = Timeframe.DAY;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		initCreateOptions();
		getViewState().showProgress(true);
		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (getTradingSubscription != null) {
			getTradingSubscription.unsubscribe();
		}
		if (privateSubscription != null) {
			privateSubscription.unsubscribe();
		}
		if (publicSubscription != null) {
			publicSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onResume() {
		updateAll();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		updateAll();
	}

	void onCreatePrivateOptionSelected(Integer position, String optionName) {
		switch (position) {
			case 0:
				getViewState().showCreateTradingAccountActivity();
				break;
			case 1:
				getViewState().showAttachAccountActivity();
				break;
		}
	}

	void onCreatePublicOptionSelected(Integer position, String optionName) {
		switch (position) {
			case 0:
				getViewState().showCreateFundActivity();
				break;
		}
	}

	private void initCreateOptions() {
		ArrayList<String> createPrivateOptions = new ArrayList<>();
		ArrayList<String> createPublicOptions = new ArrayList<>();

		createPrivateOptions.add(context.getString(R.string.create_trading_account));
		createPrivateOptions.add(context.getString(R.string.attach_external_account));

		createPublicOptions.add(context.getString(R.string.create_fund));

		getViewState().setCreateOptions(createPrivateOptions, createPublicOptions);
	}

	private void subscribeToBaseCurrency() {
		baseCurrencySubscription = settingsManager.getBaseCurrency()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::baseCurrencyChangedHandler);
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getViewState().setBaseCurrency(baseCurrency);
		getViewState().showProgress(true);
		updateAll();
	}

	private void updateAll() {
		getTrading();
		getPrivate();
		getPublic();
	}

	private void getTrading() {
		if (dashboardManager != null && baseCurrency != null) {
			getTradingSubscription = dashboardManager.getTradingDetails(baseCurrency.getValue(), EVENTS_TAKE)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleTradingSuccess, this::handleTradingError);
		}
	}

	private void handleTradingSuccess(DashboardTradingDetails details) {
		getTradingSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		this.details = details;

		getViewState().setTrading(details);
		getViewState().setTimeframe(selectedTimeframe);
		getViewState().setEvents(details.getEvents().getItems());
	}

	private void handleTradingError(Throwable throwable) {
		getTradingSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getPrivate() {
		if (dashboardManager != null && baseCurrency != null) {
			if (privateSubscription != null) {
				privateSubscription.unsubscribe();
			}

			privateSubscription = dashboardManager.getPrivate(dateRange, baseCurrency.getValue(), 0, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPrivateResponse,
							this::handleGetPrivateError);
		}
	}

	private void handleGetPrivateResponse(ItemsViewModelDashboardTradingAsset response) {
		privateSubscription.unsubscribe();
		getViewState().hidePrivateProgress();

		getViewState().setPrivateCount(response.getTotal());
		getViewState().setPrivate(response.getItems());
	}

	private void handleGetPrivateError(Throwable throwable) {
		privateSubscription.unsubscribe();
		getViewState().hidePrivateProgress();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getPublic() {
		if (dashboardManager != null && baseCurrency != null) {
			if (publicSubscription != null) {
				publicSubscription.unsubscribe();
			}

			publicSubscription = dashboardManager.getPublic(dateRange, baseCurrency.getValue(), 0, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPublicResponse,
							this::handleGetPublicError);
		}
	}

	private void handleGetPublicResponse(ItemsViewModelDashboardTradingAsset response) {
		publicSubscription.unsubscribe();
		getViewState().hidePublicProgress();

		getViewState().setPublicCount(response.getTotal());
		getViewState().setPublic(response.getItems());
	}

	private void handleGetPublicError(Throwable throwable) {
		publicSubscription.unsubscribe();
		getViewState().hidePublicProgress();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onTimeframeSelected(Timeframe timeframe) {
		this.selectedTimeframe = timeframe;
		dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.fromValue(timeframe.getValue()));
		getViewState().setTimeframe(timeframe);
		getViewState().showProgress(true);
		updateAll();
	}

//	@Subscribe
//	public void onEventMainThread(ShowProgramDetailsEvent event) {
//		getViewState().showProgramDetails(event.programDetailsModel);
//	}

//	@Subscribe
//	public void onEventMainThread(ShowFundDetailsEvent event) {
//		getViewState().showFundDetails(event.getFundDetailsModel());
//	}
}
