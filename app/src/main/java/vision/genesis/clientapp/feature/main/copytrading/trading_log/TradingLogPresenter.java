package vision.genesis.clientapp.feature.main.copytrading.trading_log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ItemsViewModelSignalTradingEvent;
import io.swagger.client.model.SignalTradingEvent;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.SetCopytradingTradingLogCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/08/2019.
 */

@InjectViewState
public class TradingLogPresenter extends MvpPresenter<TradingLogView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static int TAKE = 20;

	@Inject
	public FollowsManager followsManager;

	private Subscription getTradingLogSubscription;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private List<SignalTradingEvent> events = new ArrayList<>();

	private List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

	private UUID accountId;

	private int skip;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		getTradingLog(true);
	}

	@Override
	public void onDestroy() {
		if (getTradingLogSubscription != null) {
			getTradingLogSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(UUID accountId) {
		this.accountId = accountId;

		getTradingLog(true);
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getTradingLog(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getTradingLog(false);
	}

	private void getTradingLog(boolean forceUpdate) {
		if (dateRange != null && followsManager != null) {
			if (forceUpdate) {
				skip = 0;
			}
			getTradingLogSubscription = followsManager.getTradingLog(accountId, skip, TAKE)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetTradingLogSuccess,
							this::handleGetTradingLogError);
		}
	}

	private void handleGetTradingLogSuccess(ItemsViewModelSignalTradingEvent response) {
		getTradingLogSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (skip == 0) {
			events.clear();
		}

		EventBus.getDefault().post(new SetCopytradingTradingLogCountEvent(response.getTotal()));

		List<SignalTradingEvent> newEvents = response.getItems();

		int index = events.size();
		for (SignalTradingEvent newEvent : newEvents) {
			String dateString = DateTimeUtil.formatShortDate(newEvent.getDate());
			String lastSectionDate = sections.isEmpty() ? "" : sections.get(sections.size() - 1).getTitle().toString();
			if (!lastSectionDate.equals(dateString)) {
				sections.add(new SimpleSectionedRecyclerViewAdapter.Section(index, dateString));
			}
			index++;
		}

		events.addAll(newEvents);

		if (skip == 0) {
			getViewState().setEvents(newEvents, sections);
		}
		else {
			getViewState().addEvents(newEvents, sections);
		}

		skip += TAKE;
	}

	private void handleGetTradingLogError(Throwable throwable) {
		getTradingLogSubscription.unsubscribe();

		getViewState().showProgress(false);
		getViewState().showEmpty(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.dateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getTradingLog(true);
	}
}
