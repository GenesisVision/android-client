package vision.genesis.clientapp.feature.main.dashboard.investments.coins.history;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.CoinsHistoryEvent;
import io.swagger.client.model.CoinsHistoryEventItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.CoinsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.SetCoinsPortfolioHistoryCountEvent;
import vision.genesis.clientapp.model.events.ShowFundEventDetailsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2021.
 */

@InjectViewState
public class CoinsHistoryPresenter extends MvpPresenter<CoinsHistoryView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public CoinsManager coinsManager;

	private Subscription historySubscription;

	private int skip = 0;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private List<CoinsHistoryEvent> historyItems = new ArrayList<CoinsHistoryEvent>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		getHistory(true);
	}

	@Override
	public void onDestroy() {
		if (historySubscription != null) {
			historySubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getHistory(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getHistory(false);
	}

	private void getHistory(boolean forceUpdate) {
		if (dateRange != null) {
			if (forceUpdate) {
				skip = 0;
			}

			if (historySubscription != null) {
				historySubscription.unsubscribe();
			}
			historySubscription = coinsManager.getHistory(dateRange, skip, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetHistoryResponse,
							this::handleGetHistoryError);
		}
	}

	private void handleGetHistoryResponse(CoinsHistoryEventItemsViewModel response) {
		historySubscription.unsubscribe();
		getViewState().showProgress(false);

		if (skip == 0) {
			historyItems.clear();
		}

		EventBus.getDefault().post(new SetCoinsPortfolioHistoryCountEvent(response.getTotal()));

		List<CoinsHistoryEvent> newHistoryItems = response.getItems();

		historyItems.addAll(newHistoryItems);

		if (skip == 0) {
			getViewState().setHistory(newHistoryItems);
		}
		else {
			getViewState().addHistory(newHistoryItems);
		}

		skip += TAKE;
	}

	private void handleGetHistoryError(Throwable error) {
		historySubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.dateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getHistory(true);
	}

	@Subscribe
	public void onEventMainThread(ShowFundEventDetailsEvent event) {
		getViewState().showEventDetails(event.getEvent());
	}
}
