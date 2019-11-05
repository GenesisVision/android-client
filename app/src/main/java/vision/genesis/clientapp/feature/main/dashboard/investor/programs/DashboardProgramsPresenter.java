package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.events.OnBrowseProgramsClickedEvent;
import vision.genesis.clientapp.model.events.OnDashboardProgramFavoriteClickedEvent;
import vision.genesis.clientapp.model.events.OnDashboardProgramsUpdateEvent;
import vision.genesis.clientapp.model.events.OnDashboardReinvestClickedEvent;
import vision.genesis.clientapp.model.events.OnProgramFavoriteChangedEvent;
import vision.genesis.clientapp.model.events.OnProgramReinvestChangedEvent;
import vision.genesis.clientapp.model.filter.DashboardFilter;
import vision.genesis.clientapp.model.filter.UserFilter;

/**
 * GenesisVision
 * Created by Vitaly on 3/13/18.
 */

@InjectViewState
public class DashboardProgramsPresenter extends MvpPresenter<DashboardProgramsView>
{
	@Inject
	public DashboardManager dashboardManager;

	@Inject
	public ProgramsManager programsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription getProgramsSubscription;

	private Subscription reinvestSubscription;

	private Subscription favoriteSubscription;

	private DashboardFilter filter;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		createFilter();
	}

	@Override
	public void onDestroy() {
		if (getProgramsSubscription != null) {
			getProgramsSubscription.unsubscribe();
		}
		if (reinvestSubscription != null) {
			reinvestSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	private void createFilter() {
		if (filter == null) {
			filter = new DashboardFilter();
		}
		this.filter.setSkip(0);
		this.filter.setTake(1000);
		this.filter.setDateRange(DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH));
		this.filter.setChartPointsCount(10);
		filter.setSorting(SortingEnum.BYTITLEASC);
	}

	void onShow() {
		getPrograms();
	}

	void onStartInvestingClicked() {
		EventBus.getDefault().post(new OnBrowseProgramsClickedEvent());
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		EventBus.getDefault().post(new OnDashboardProgramsUpdateEvent());
	}

	void onFiltersClicked() {
		getViewState().showFiltersActivity(filter);
	}

	void onFilterUpdated(UserFilter userFilter) {
		this.filter.updateWithUserFilter(userFilter);
		getViewState().showProgressBar(true);
		getPrograms();
	}

	private void getPrograms() {
//		if (filter != null)
//			getProgramsSubscription = dashboardManager.getPrograms(filter)
//					.subscribeOn(Schedulers.computation())
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribe(this::handleGetProgramsSuccess,
//							this::handleGetProgramsError);
	}

//	private void handleGetProgramsSuccess(ProgramsList response) {
//		getProgramsSubscription.unsubscribe();
//
//		getViewState().showProgressBar(false);
//
//		getViewState().setPrograms(response.getPrograms());
//		EventBus.getDefault().post(new SetDashboardProgramsCountEvent(response.getTotal()));
//	}
//
//	private void handleGetProgramsError(Throwable throwable) {
//		getProgramsSubscription.unsubscribe();
//
//		getViewState().showProgressBar(false);
//		getViewState().showEmpty(false);
//
//		if (ApiErrorResolver.isNetworkError(throwable)) {
//		}
//	}

	private void setReinvest(UUID programId, boolean reinvest) {
		if (programsManager != null) {
			if (reinvestSubscription != null) {
				reinvestSubscription.unsubscribe();
			}
			reinvestSubscription = programsManager.setReinvest(programId, reinvest)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleReinvestSuccess(programId, reinvest),
							throwable -> handleReinvestError(throwable, programId, reinvest));
		}
	}

	private void handleReinvestSuccess(UUID programId, Boolean reinvest) {
		reinvestSubscription.unsubscribe();

		EventBus.getDefault().post(new OnProgramReinvestChangedEvent(programId, reinvest));
	}

	private void handleReinvestError(Throwable throwable, UUID programId, Boolean reinvest) {
		reinvestSubscription.unsubscribe();
		getViewState().setProgramReinvest(programId, reinvest);

//		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void setFavorite(UUID programId, boolean favorite) {
		if (programsManager != null) {
			if (favoriteSubscription != null) {
				favoriteSubscription.unsubscribe();
			}
			favoriteSubscription = programsManager.setProgramFavorite(programId, favorite)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleFavoriteSuccess(programId, favorite),
							throwable -> handleFavoriteError(throwable, programId, favorite));
		}
	}

	private void handleFavoriteSuccess(UUID programId, Boolean favorite) {
		favoriteSubscription.unsubscribe();

		EventBus.getDefault().post(new OnProgramFavoriteChangedEvent(programId, favorite));
	}

	private void handleFavoriteError(Throwable throwable, UUID programId, Boolean favorite) {
		favoriteSubscription.unsubscribe();
		getViewState().setProgramFavorite(programId, favorite);

//		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnProgramReinvestChangedEvent event) {
		getViewState().setProgramReinvest(event.getProgramId(), event.getReinvest());
	}

	@Subscribe
	public void onEventMainThread(OnDashboardReinvestClickedEvent event) {
		setReinvest(event.getProgramId(), event.getReinvest());
	}

	@Subscribe
	public void onEventMainThread(OnProgramFavoriteChangedEvent event) {
		getViewState().setProgramFavorite(event.getProgramId(), event.isFavorite());
	}

	@Subscribe
	public void onEventMainThread(OnDashboardProgramFavoriteClickedEvent event) {
		setFavorite(event.getProgramId(), event.isFavorite());
	}
}
