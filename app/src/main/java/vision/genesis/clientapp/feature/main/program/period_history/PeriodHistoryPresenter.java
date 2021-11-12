package vision.genesis.clientapp.feature.main.program.period_history;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ProgramPeriodViewModel;
import io.swagger.client.model.ProgramPeriodsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.ExportManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.SetProgramDetailsPeriodHistoryCountEvent;
import vision.genesis.clientapp.model.events.ShowPeriodDetailsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/08/2019.
 */

@InjectViewState
public class PeriodHistoryPresenter extends MvpPresenter<PeriodHistoryView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public ProgramsManager programsManager;

	@Inject
	public ExportManager exportManager;

	private Subscription historySubscription;

	private int skip = 0;

	private UUID programId;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private List<ProgramPeriodViewModel> periods = new ArrayList<>();

	private String programName = "";

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		if (programId != null) {
			getHistory(true);
		}
	}

	@Override
	public void onDestroy() {
		if (historySubscription != null) {
			historySubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID programId, String programName) {
		this.programId = programId;
		this.programName = programName;
		if (programsManager != null) {
			getHistory(true);
		}
	}

	void onShow() {
		getHistory(false);
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
		if (dateRange != null && programId != null) {
			if (forceUpdate) {
				skip = 0;
			}

			if (historySubscription != null) {
				historySubscription.unsubscribe();
			}
			historySubscription = programsManager.getPeriodHistory(programId, dateRange, null, false, skip, TAKE)
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

		EventBus.getDefault().post(new SetProgramDetailsPeriodHistoryCountEvent(response.getTotal()));

		List<ProgramPeriodViewModel> newPeriods = response.getPeriods();

		periods.addAll(newPeriods);

		if (skip == 0) {
			getViewState().setPeriods(newPeriods);
		}
		else {
			getViewState().addPeriods(newPeriods);
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
	public void onEventMainThread(ShowPeriodDetailsEvent event) {
		getViewState().showPeriodDetails(event.getPeriod());
	}

	void onExportClicked() {
		if (exportManager != null && dateRange != null && programId != null) {
			exportManager.exportPeriods(programId, programName, dateRange);
		}
	}
}
