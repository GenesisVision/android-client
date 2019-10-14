package vision.genesis.clientapp.feature.main.portfolio_events;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.InvestmentEventViewModels;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.ShowEventDetailsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/08/2018.
 */

@InjectViewState
public class PortfolioEventsPresenter extends MvpPresenter<PortfolioEventsView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public DashboardManager dashboardManager;

	private Subscription eventsSubscription;

	private int skip = 0;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private List<InvestmentEventViewModel> events = new ArrayList<>();

	private List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();


	private boolean isActive = false;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		getEvents(true);
	}

	@Override
	public void onDestroy() {
		if (eventsSubscription != null) {
			eventsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
		isActive = true;
	}

	void onPause() {
		isActive = false;
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getEvents(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getEvents(false);
	}

	private void getEvents(boolean forceUpdate) {
		if (dateRange != null) {
			if (forceUpdate) {
				skip = 0;
			}

			if (eventsSubscription != null) {
				eventsSubscription.unsubscribe();
			}
			eventsSubscription = dashboardManager.getEvents("EventsAll", null, dateRange, null, null, skip, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
//					.map(this::prepareData)
					.subscribe(this::handleGetEventsResponse,
							this::handleGetEventsError);
		}
	}

//	private DashboardPortfolioEvents prepareData(DashboardPortfolioEvents dashboardPortfolioEvents) {
//		newPreparedEvents = new ArrayList<>();
//
//		for (DashboardPortfolioEvent event : dashboardPortfolioEvents.getEvents()) {
//			newPreparedEvents.add(PortfolioEvent.createFrom(event));
//		}
//
//		return dashboardPortfolioEvents;
//	}

	private void handleGetEventsResponse(InvestmentEventViewModels response) {
		eventsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		if (skip == 0) {
			events.clear();
			sections.clear();
		}

		List<InvestmentEventViewModel> newEvents = response.getEvents();

		int index = events.size();
		for (InvestmentEventViewModel newEvent : newEvents) {
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

	private void handleGetEventsError(Throwable throwable) {
		eventsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.dateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getEvents(true);
	}

	@Subscribe
	public void onEventMainThread(ShowEventDetailsEvent event) {
		if (isActive) {
			getViewState().showEventDetails(event.getEvent());
		}
	}
}
