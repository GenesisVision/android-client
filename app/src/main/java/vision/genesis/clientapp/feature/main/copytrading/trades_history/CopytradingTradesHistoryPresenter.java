package vision.genesis.clientapp.feature.main.copytrading.trades_history;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.OrderSignalModel;
import io.swagger.client.model.TradesSignalViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.SignalsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.SetCopytradingAccountTradesHistoryCountEvent;
import vision.genesis.clientapp.model.events.SetDashboardTradesHistoryCountEvent;
import vision.genesis.clientapp.model.events.ShowCopytradingCommissionsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

@InjectViewState
public class CopytradingTradesHistoryPresenter extends MvpPresenter<CopytradingTradesHistoryView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static int TAKE = 20;

	@Inject
	public SignalsManager signalsManager;

	private Subscription getTradesHistorySubscription;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private int skip;

	private List<OrderSignalModel> trades = new ArrayList<>();

	private String location;

	private String accountCurrency;

	private boolean isFragmentActive;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		getTradesHistory(true);
	}

	@Override
	public void onDestroy() {
		if (getTradesHistorySubscription != null)
			getTradesHistorySubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(String location, String accountCurrency) {
		this.location = location;
		this.accountCurrency = accountCurrency;

		getTradesHistory(true);
	}

	void onShow() {
		isFragmentActive = true;
	}

	void onHide() {
		isFragmentActive = false;
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getTradesHistory(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getTradesHistory(false);
	}

	private void getTradesHistory(boolean forceUpdate) {
		if (dateRange != null && signalsManager != null) {
			if (forceUpdate) {
				skip = 0;
			}
			getTradesHistorySubscription = signalsManager.getTradesHistory(dateRange, "", "", null, accountCurrency, skip, TAKE)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetTradesHistorySuccess,
							this::handleGetTradesHistoryError);
		}
	}

	private void handleGetTradesHistorySuccess(TradesSignalViewModel response) {
		getTradesHistorySubscription.unsubscribe();
		getViewState().showProgress(false);

		if (skip == 0) {
			trades.clear();
		}

		if (location.equals(CopytradingTradesHistoryFragment.LOCATION_DASHBOARD))
			EventBus.getDefault().post(new SetDashboardTradesHistoryCountEvent(response.getTotal()));
		else if (location.equals(CopytradingTradesHistoryFragment.LOCATION_COPYTRADING_ACCOUNT))
			EventBus.getDefault().post(new SetCopytradingAccountTradesHistoryCountEvent(response.getTotal()));

		List<OrderSignalModel> newTrades = response.getTrades();
		trades.addAll(newTrades);

		if (skip == 0) {
			getViewState().setTrades(newTrades);
		}
		else {
			getViewState().addTrades(newTrades);
		}

		skip += TAKE;
	}

	private void handleGetTradesHistoryError(Throwable throwable) {
		getTradesHistorySubscription.unsubscribe();

		getViewState().showProgress(false);
		getViewState().showEmpty(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(ShowCopytradingCommissionsEvent event) {
		if (isFragmentActive)
			getViewState().showCommissions(event.getTrade());
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.dateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getTradesHistory(true);
	}
}
