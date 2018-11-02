package vision.genesis.clientapp.feature.main.programs_list;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ProgramDetails;
import io.swagger.client.model.ProgramsList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.filters_sorting.SortingFiltersButtonsView;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.ProgramsFilter;
import vision.genesis.clientapp.model.events.OnListProgramFavoriteClickedEvent;
import vision.genesis.clientapp.model.events.OnProgramFavoriteChangedEvent;
import vision.genesis.clientapp.model.events.ProgramsListFiltersAppliedEvent;
import vision.genesis.clientapp.model.events.ProgramsListFiltersClearedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class ProgramsListPresenter extends MvpPresenter<ProgramsListView> implements SortingFiltersButtonsView.OnFilterUpdatedListener
{
	private static int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public ProgramsManager programsManager;

	private Subscription userSubscription;

	private Subscription getProgramsSubscription;

	private Subscription favoriteSubscription;

	private List<ProgramDetails> investmentProgramsList = new ArrayList<ProgramDetails>();

	private int skip = 0;

	private ProgramsFilter filter;

	private List<ProgramDetails> programsToAdd = new ArrayList<>();

	private UUID managerId;

	private Boolean isManagerSet;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.WEEK);

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
		createFilter();
		getViewState().setRefreshing(true);
		getProgramsList(true);
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null)
			userSubscription.unsubscribe();
		if (getProgramsSubscription != null)
			getProgramsSubscription.unsubscribe();
		if (favoriteSubscription != null)
			favoriteSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setManagerId(UUID managerId) {
		this.managerId = managerId;
		this.isManagerSet = true;
		if (filter != null) {
			filter.setManagerId(managerId);
		}
		getProgramsList(true);
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getProgramsList(true);
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		getProgramsList(true);
	}

	void onFiltersClicked() {
		getViewState().showFiltersActivity(filter);
	}

	void onLastListItemVisible() {
		getViewState().showBottomProgress(true);
		getProgramsList(false);
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(user -> userUpdated(), error -> userUpdated());
	}

	private void userUpdated() {
		getProgramsList(true);
	}

	private void createFilter() {
		filter = new ProgramsFilter();
		filter.setSkip(0);
		filter.setTake(TAKE);
		filter.setManagerId(managerId);
		filter.setStatisticDateFrom(dateRange.getFrom());
		filter.setStatisticDateTo(dateRange.getTo());
//		filter.setEquityChartLength(10);
	}

	public void onFilterUpdated(ProgramsFilter filter) {
		this.filter = filter;
		getViewState().setRefreshing(true);
		getProgramsList(true);
	}

	private void getProgramsList(boolean forceUpdate) {
		if (filter != null && programsManager != null && isManagerSet) {
			if (forceUpdate) {
				skip = 0;
				filter.setSkip(skip);
			}

			if (getProgramsSubscription != null)
				getProgramsSubscription.unsubscribe();
			getProgramsSubscription = programsManager.getProgramsList(filter)
					.subscribeOn(Schedulers.computation())
//				.map(this::prepareData)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetProgramsList,
							this::handleGetProgramsListError);
		}
	}

//	private InvestmentProgramsViewModel prepareData(InvestmentProgramsViewModel model) {
//		programsToAdd = InvestmentProgramExtended.extendInvestmentPrograms(model.getInvestmentPrograms());
//		return model;
//	}

	private void handleGetProgramsList(ProgramsList response) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);
		getViewState().showEmptyList(false);
		getViewState().showBottomProgress(false);

		getProgramsSubscription.unsubscribe();

		programsToAdd = response.getPrograms();

		getViewState().setProgramsCount(StringFormatUtil.formatAmount(response.getTotal(), 0, 0));

		if (programsToAdd.size() == 0) {
			if (skip == 0)
				getViewState().showEmptyList(true);
			return;
		}

		if (skip == 0) {
			investmentProgramsList.clear();
			getViewState().setInvestmentPrograms(programsToAdd);
		}
		else {
			getViewState().addInvestmentPrograms(programsToAdd);
		}
		investmentProgramsList.addAll(programsToAdd);
		skip += TAKE;
		filter.setTake(TAKE);
		filter.setSkip(skip);
	}

	private void handleGetProgramsListError(Throwable error) {
		getProgramsSubscription.unsubscribe();
		getViewState().showBottomProgress(false);

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		if (ApiErrorResolver.isNetworkError(error)) {
			if (investmentProgramsList.size() == 0) {
				getViewState().showEmptyList(false);
				getViewState().showNoInternet(true);
			}
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	private void setFavorite(UUID programId, boolean favorite) {
		if (programsManager != null) {
			if (favoriteSubscription != null)
				favoriteSubscription.unsubscribe();
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
		getViewState().changeProgramIsFavorite(programId, favorite);

//		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(ProgramsListFiltersAppliedEvent event) {
		getViewState().showFiltersActive(true);
	}

	@Subscribe
	public void onEventMainThread(ProgramsListFiltersClearedEvent event) {
		getViewState().showFiltersActive(false);
	}

	@Subscribe
	public void onEventMainThread(OnProgramFavoriteChangedEvent event) {
		getViewState().changeProgramIsFavorite(event.getProgramId(), event.isFavorite());
	}

	@Subscribe
	public void onEventMainThread(OnListProgramFavoriteClickedEvent event) {
		setFavorite(event.getProgramId(), event.isFavorite());
	}
}
