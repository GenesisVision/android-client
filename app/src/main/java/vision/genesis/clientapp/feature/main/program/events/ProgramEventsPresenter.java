package vision.genesis.clientapp.feature.main.program.events;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.InvestmentEventViewModels;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.SetFundDetailsEventsCountEvent;
import vision.genesis.clientapp.model.events.SetProgramDetailsEventsCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/10/2018.
 */

@InjectViewState
public class ProgramEventsPresenter extends MvpPresenter<ProgramEventsView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public ProgramsManager programsManager;

	private Subscription eventsSubscription;

	private int skip = 0;

	private UUID programId;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private List<InvestmentEventViewModel> events = new ArrayList<>();

	private List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

	private String location;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		if (programId != null) {
			getEvents(true);
		}
	}

	@Override
	public void onDestroy() {
		if (eventsSubscription != null) {
			eventsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(String location, UUID programId) {
		this.location = location;
		this.programId = programId;
		if (programsManager != null) {
			getEvents(true);
		}
	}

	void onShow() {
//		getEvents(false);
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getEvents(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getEvents(false);
	}

	private void getEvents(boolean forceUpdate) {
		if (dateRange != null && programId != null) {
			if (forceUpdate) {
				skip = 0;
			}

			if (eventsSubscription != null) {
				eventsSubscription.unsubscribe();
			}
			eventsSubscription = programsManager.getEvents(programId, dateRange, skip, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
//					.map(this::prepareData)
					.subscribe(this::handleGetEventsResponse,
							this::handleGetEventsError);
		}
	}

//	private DashboardPortfolioEvents prepareData(InvestmentEventViewModels response) {
//		newPreparedEvents = new ArrayList<>();
//
//		for (InvestmentEventViewModel event : response.getEvents()) {
//			newPreparedEvents.add(PortfolioEvent.createFrom(event));
//		}
//
//		return dashboardPortfolioEvents;
//	}

	private void handleGetEventsResponse(InvestmentEventViewModels response) {
		eventsSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (skip == 0) {
			events.clear();
			sections.clear();
		}

		if (location.equals(ProgramEventsFragment.LOCATION_PROGRAM)) {
			EventBus.getDefault().post(new SetProgramDetailsEventsCountEvent(response.getTotal()));
		}
		else if (location.equals(ProgramEventsFragment.LOCATION_FUND)) {
			EventBus.getDefault().post(new SetFundDetailsEventsCountEvent(response.getTotal()));
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

	private void handleGetEventsError(Throwable error) {
		eventsSubscription.unsubscribe();
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
		getEvents(true);
	}
}
