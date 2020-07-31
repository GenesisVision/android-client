package vision.genesis.clientapp.feature.main.users_list.fragment;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.PublicProfile;
import io.swagger.client.model.PublicProfileItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.filters_sorting.SortingFiltersButtonsView;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.UsersManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.ProgramsListFiltersAppliedEvent;
import vision.genesis.clientapp.model.events.ProgramsListFiltersClearedEvent;
import vision.genesis.clientapp.model.filter.ProgramsFilter;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/03/2019.
 */

@InjectViewState
public class UsersListPresenter extends MvpPresenter<UsersListView> implements SortingFiltersButtonsView.OnFilterUpdatedListener
{
	private static int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public UsersManager usersManager;

	private Subscription userSubscription;

	private Subscription getUsersSubscription;

	private List<PublicProfile> usersList = new ArrayList<>();

	private int skip = 0;

	private List<PublicProfile> usersToAdd = new ArrayList<>();

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
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}
		if (getUsersSubscription != null) {
			getUsersSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(String location) {
		this.location = location;

		if (!location.equals(UsersListFragment.LOCATION_SEARCH)) {
			isDataSet = true;
			getUsersList(true);
		}
	}

	void showSearchResults(PublicProfileItemsViewModel result) {
		skip = 0;
		handleGetUsersList(result);
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getUsersList(true);
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		getUsersList(true);
	}

	void onLastListItemVisible() {
		getUsersList(false);
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
		getUsersList(true);
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

	private void getUsersList(boolean forceUpdate) {
		if (usersManager != null && isDataSet) {
			if (forceUpdate) {
				skip = 0;
			}

			if (getUsersSubscription != null) {
				getUsersSubscription.unsubscribe();
			}
//			getUsersSubscription = usersManager.getUsers(UsersFilterSorting.BYFOLLOWERSDESC, UsersFilterTimeframe.MONTH, null, skip, TAKE)
//					.subscribeOn(Schedulers.computation())
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribe(this::handleGetUsersList,
//							this::handleGetUsersListError);
		}
	}

	private void handleGetUsersList(PublicProfileItemsViewModel response) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);
		getViewState().showEmptyList(false);

		if (getUsersSubscription != null) {
			getUsersSubscription.unsubscribe();
		}

		usersToAdd = response.getItems();

		if (usersToAdd.size() == 0) {
			if (skip == 0) {
				getViewState().showEmptyList(true);
			}
			return;
		}

		if (skip == 0) {
			usersList.clear();
			getViewState().setUsers(usersToAdd);
		}
		else {
			getViewState().addUsers(usersToAdd);
		}
		usersList.addAll(usersToAdd);
		skip += TAKE;
	}

	private void handleGetUsersListError(Throwable throwable) {
		getUsersSubscription.unsubscribe();
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
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
