package vision.genesis.clientapp.feature.main.managers_list;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.ManagerProfile;
import io.swagger.client.model.ManagersList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.filters_sorting.SortingFiltersButtonsView;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.ProgramsFilter;
import vision.genesis.clientapp.model.events.ProgramsListFiltersAppliedEvent;
import vision.genesis.clientapp.model.events.ProgramsListFiltersClearedEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/03/2019.
 */

@InjectViewState
public class ManagersListPresenter extends MvpPresenter<ManagersListView> implements SortingFiltersButtonsView.OnFilterUpdatedListener
{
	private static int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public FundsManager fundsManager;

	private Subscription userSubscription;

	private Subscription getManagersSubscription;

	private Subscription favoriteSubscription;

	private List<ManagerProfile> managersList = new ArrayList<>();

	private int skip = 0;

//	private ProgramsFilter filter;

	private List<ManagerProfile> managersToAdd = new ArrayList<>();

	private Boolean isDataSet = false;

	private String location = "";

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
		createFilter();
		getViewState().setRefreshing(true);
//		getManagersList(true);
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null)
			userSubscription.unsubscribe();
		if (getManagersSubscription != null)
			getManagersSubscription.unsubscribe();
		if (favoriteSubscription != null)
			favoriteSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(String location) {
		this.location = location;

		if (!location.equals(ManagersListFragment.LOCATION_SEARCH)) {
			isDataSet = true;
//			getManagersList(true);
		}
	}

	void showSearchResults(ManagersList result) {
		skip = 0;
		handleGetManagersList(result);
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
//		getManagersList(true);
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
//		getManagersList(true);
	}

	void onLastListItemVisible() {
//		getManagersList(false);
	}

	void onFiltersClicked() {
//		getViewState().showFiltersActivity(filter);
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(user -> userUpdated(), error -> userUpdated());
	}

	private void userUpdated() {
//		getManagersList(true);
	}

	private void createFilter() {
//		filter = new ProgramsFilter();
//		filter.setSkip(0);
//		filter.setTake(TAKE);
//		filter.setDateRange(dateRange);
//		filter.setSorting(SortingEnum.BYPROFITDESC);
//		filter.setEquityChartLength(10);
	}

	public void onFilterUpdated(ProgramsFilter filter) {
//		this.filter = filter;
		getViewState().setRefreshing(true);
//		getManagersList(true);
	}

//	private void getManagersList(boolean forceUpdate) {
//		if (filter != null && fundsManager != null && isDataSet) {
//			if (forceUpdate) {
//				skip = 0;
//				filter.setSkip(skip);
//			}
//
//			if (getManagersSubscription != null)
//				getManagersSubscription.unsubscribe();
//			getManagersSubscription = fundsManager.getFundsList(filter)
//					.subscribeOn(Schedulers.computation())
////				.map(this::prepareData)
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribe(this::handleGetManagersList,
//							this::handleGetFundsListError);
//		}
//	}

//	private InvestmentProgramsViewModel prepareData(InvestmentProgramsViewModel model) {
//		managersToAdd = InvestmentProgramExtended.extendInvestmentPrograms(model.getInvestmentPrograms());
//		return model;
//	}

	private void handleGetManagersList(ManagersList response) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);
		getViewState().showEmptyList(false);

		if (getManagersSubscription != null)
			getManagersSubscription.unsubscribe();

		managersToAdd = response.getManagers();

//		getViewState().setFundsCount(StringFormatUtil.formatAmount(response.getTotal(), 0, 0));

		if (managersToAdd.size() == 0) {
			if (skip == 0)
				getViewState().showEmptyList(true);
			return;
		}

		if (skip == 0) {
			managersList.clear();
			getViewState().setManagers(managersToAdd);
		}
		else {
			getViewState().addManagers(managersToAdd);
		}
		managersList.addAll(managersToAdd);
		skip += TAKE;
//		filter.setTake(TAKE);
//		filter.setSkip(skip);
	}

	@Subscribe
	public void onEventMainThread(ProgramsListFiltersAppliedEvent event) {
		getViewState().showFiltersActive(true);
	}

	@Subscribe
	public void onEventMainThread(ProgramsListFiltersClearedEvent event) {
		getViewState().showFiltersActive(false);
	}
}
