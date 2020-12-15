package vision.genesis.clientapp.feature.main.program.reports;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.Days;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ProgramPeriodViewModel;
import io.swagger.client.model.ProgramPeriodsViewModel;
import io.swagger.client.model.Timeframe;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.SetProgramDetailsReportsCountEvent;
import vision.genesis.clientapp.model.events.ShowReportDetailsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2020.
 */

@InjectViewState
public class ProgramReportsPresenter extends MvpPresenter<ProgramReportsView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public ProgramsManager programsManager;

	private Subscription historySubscription;

	private int skip = 0;

	private UUID programId;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private List<ProgramPeriodViewModel> periods = new ArrayList<>();

	private ArrayList<Timeframe> intervals = new ArrayList<>();

	private Timeframe interval;

	private Integer selectedIntervalPosition = -1;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		onDateRangeChanged(dateRange);

		getData(true);
	}

	@Override
	public void onDestroy() {
		if (historySubscription != null) {
			historySubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setProgramId(UUID programId) {
		this.programId = programId;
		getData(true);
	}

	void onShow() {
		getData(false);
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getData(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getData(false);
	}

	private void getData(boolean forceUpdate) {
		if (programsManager != null && dateRange != null && programId != null) {
			if (forceUpdate) {
				skip = 0;
			}

			if (historySubscription != null) {
				historySubscription.unsubscribe();
			}
			historySubscription = programsManager.getPeriodHistory(programId, dateRange, interval, true, skip, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetHistoryResponse,
							this::handleGetHistoryError);
		}
	}

	private void handleGetHistoryResponse(ProgramPeriodsViewModel response) {
		historySubscription.unsubscribe();
		getViewState().showProgress(false);

		if (skip == 0) {
			periods.clear();
		}

		EventBus.getDefault().post(new SetProgramDetailsReportsCountEvent(response.getTotal()));

		List<ProgramPeriodViewModel> newPeriods = response.getPeriods();

		periods.addAll(newPeriods);

		if (skip == 0) {
			getViewState().setData(newPeriods);
		}
		else {
			getViewState().addData(newPeriods);
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
		if (updateIntervals()) {
			getViewState().showProgress(true);
			getData(true);
		}
	}

	private boolean updateIntervals() {
		intervals = new ArrayList<>();
		ArrayList<String> intervalOptions = new ArrayList<>();

		if (Days.daysBetween(dateRange.getFrom(), dateRange.getTo()).getDays() < 7) {
			intervals.add(Timeframe.DAY);

			intervalOptions.add(context.getString(R.string.day));
		}
		else if (dateRange.getSelectedRange().equals(DateRange.DateRangeEnum.WEEK)) {
			intervals.add(Timeframe.DAY);
			intervals.add(Timeframe.WEEK);

			intervalOptions.add(context.getString(R.string.day));
			intervalOptions.add(context.getString(R.string.week));
		}
		else {
			intervals.add(Timeframe.DAY);
			intervals.add(Timeframe.WEEK);
			intervals.add(Timeframe.MONTH);

			intervalOptions.add(context.getString(R.string.day));
			intervalOptions.add(context.getString(R.string.week));
			intervalOptions.add(context.getString(R.string.month));
		}

		getViewState().setIntervalOptions(intervalOptions);

		if (selectedIntervalPosition > intervals.size() - 1 || selectedIntervalPosition == -1) {
			selectedIntervalPosition = intervals.size() - 1;
			onIntervalOptionSelected(selectedIntervalPosition, intervalOptions.get(selectedIntervalPosition));
			return false;
		}
		else {
			return true;
		}
	}

	@Subscribe
	public void onEventMainThread(ShowReportDetailsEvent event) {
		getViewState().showReportDetails(event.getPeriod());
	}

	void onIntervalOptionSelected(Integer position, String text) {
		this.selectedIntervalPosition = position;
		this.interval = intervals.get(position);
		getViewState().setInterval(text, position);

		getViewState().showProgress(true);
		getData(true);
	}
}
