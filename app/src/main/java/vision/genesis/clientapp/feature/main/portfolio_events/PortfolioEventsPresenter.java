package vision.genesis.clientapp.feature.main.portfolio_events;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.DashboardPortfolioEvent;
import io.swagger.client.model.DashboardPortfolioEvents;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.InvestorDashboardManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.PortfolioEvent;
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
	public InvestorDashboardManager investorDashboardManager;

	private Subscription eventsSubscription;

	private int skip = 0;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.WEEK);

	private List<DashboardPortfolioEvent> events = new ArrayList<>();

	private List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

	private List<PortfolioEvent> newPreparedEvents = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		getEvents(true);
	}

	@Override
	public void onDestroy() {
		if (eventsSubscription != null)
			eventsSubscription.unsubscribe();

		super.onDestroy();
	}

	void onShow() {
		getEvents(false);
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

			if (eventsSubscription != null)
				eventsSubscription.unsubscribe();
			eventsSubscription = investorDashboardManager.getPortfolioEvents(dateRange, skip, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.map(this::prepareData)
					.subscribe(this::handleGetEventsResponse,
							this::handleGetEventsError);
		}
	}

	private DashboardPortfolioEvents prepareData(DashboardPortfolioEvents dashboardPortfolioEvents) {
		newPreparedEvents = new ArrayList<>();

		for (DashboardPortfolioEvent event : dashboardPortfolioEvents.getEvents()) {
			newPreparedEvents.add(PortfolioEvent.createFrom(event));
		}

		return dashboardPortfolioEvents;
	}

	private void handleGetEventsResponse(DashboardPortfolioEvents response) {
		eventsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		if (skip == 0) {
			events.clear();
			sections.clear();
		}

		List<DashboardPortfolioEvent> newEvents = response.getEvents();

		int index = events.size();
		for (DashboardPortfolioEvent newEvent : newEvents) {
			String dateString = DateTimeUtil.formatShortDate(newEvent.getDate());
			String lastSectionDate = sections.isEmpty() ? "" : sections.get(sections.size() - 1).getTitle().toString();
			if (!lastSectionDate.equals(dateString))
				sections.add(new SimpleSectionedRecyclerViewAdapter.Section(index, dateString));
			index++;
		}

		events.addAll(newEvents);

		if (skip == 0) {
			getViewState().setEvents(newPreparedEvents, sections);
		}
		else {
			getViewState().addEvents(newPreparedEvents, sections);
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
}
