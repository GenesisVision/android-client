package vision.genesis.clientapp.feature.main.follows_list;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.FollowDetailsList;
import io.swagger.client.model.ItemsViewModelFollowDetailsList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.OnFollowFavoriteChangedEvent;
import vision.genesis.clientapp.model.events.OnListFollowFavoriteClickedEvent;
import vision.genesis.clientapp.model.events.SetManagerDetailsFollowsCountEvent;
import vision.genesis.clientapp.model.filter.ProgramsFilter;
import vision.genesis.clientapp.model.filter.UserFilter;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/11/2019.
 */

@InjectViewState
public class FollowsListPresenter extends MvpPresenter<FollowsListView>
{
	private static int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public FollowsManager followsManager;

	private Subscription userSubscription;

	private Subscription getFollowsSubscription;

	private Subscription favoriteSubscription;

	private List<FollowDetailsList> followsList = new ArrayList<FollowDetailsList>();

	private int skip = 0;

	private ProgramsFilter filter;

	private List<FollowDetailsList> followsToAdd = new ArrayList<>();

	private Boolean isDataSet = false;

	private String location = "";

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
		getViewState().setRefreshing(true);
		getFollowsList(true);
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}
		if (getFollowsSubscription != null) {
			getFollowsSubscription.unsubscribe();
		}
		if (favoriteSubscription != null) {
			favoriteSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(String location, ProgramsFilter filter) {
		this.location = location;
		createFilter(filter);
		if (location.equals(FollowsListFragment.LOCATION_RATING)) {
			this.filter.setDateRange(null);
			this.filter.setSorting(null);
			this.filter.setChartPointsCount(null);
		}
		if (!location.equals(FollowsListFragment.LOCATION_SEARCH)) {
			isDataSet = true;
			getFollowsList(true);
		}
	}

	void showSearchResults(ItemsViewModelFollowDetailsList result) {
		skip = 0;
		handleGetFollowsList(result);
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getFollowsList(true);
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		getFollowsList(true);
	}

	void onFiltersClicked() {
		getViewState().showFiltersActivity(filter);
	}

	void onLastListItemVisible() {
		getViewState().showBottomProgress(true);
		getFollowsList(false);
	}

	void onFilterUpdated(UserFilter userFilter) {
		this.filter.updateWithUserFilter(userFilter);
		getViewState().setRefreshing(true);
		getFollowsList(true);
	}

	private void createFilter(ProgramsFilter filter) {
		if (filter == null) {
			filter = new ProgramsFilter();
		}
		this.filter = filter;
		this.filter.setSkip(0);
		this.filter.setTake(TAKE);
		this.filter.setDateRange(dateRange);
		this.filter.setChartPointsCount(10);
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(user -> userUpdated(), error -> userUpdated());
	}

	private void userUpdated() {
		getFollowsList(true);
	}

	private void getFollowsList(boolean forceUpdate) {
		if (filter != null && followsManager != null && isDataSet) {
			if (forceUpdate) {
				skip = 0;
				filter.setSkip(skip);
			}

			if (getFollowsSubscription != null) {
				getFollowsSubscription.unsubscribe();
			}
			getFollowsSubscription = followsManager.getFollows(filter)
					.subscribeOn(Schedulers.computation())
//				.map(this::prepareData)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetFollowsList,
							this::handleGetFollowsListError);
		}
	}

//	private InvestmentProgramsViewModel prepareData(InvestmentProgramsViewModel model) {
//		followsToAdd = InvestmentProgramExtended.extendInvestmentPrograms(model.getInvestmentPrograms());
//		return model;
//	}

	private void handleGetFollowsList(ItemsViewModelFollowDetailsList response) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);
		getViewState().showEmptyList(false);
		getViewState().showBottomProgress(false);

		if (getFollowsSubscription != null) {
			getFollowsSubscription.unsubscribe();
		}

		followsToAdd = response.getItems();

		if (location.equals(FollowsListFragment.LOCATION_MANAGER)) {
			EventBus.getDefault().post(new SetManagerDetailsFollowsCountEvent(response.getTotal()));
		}

		if (followsToAdd.size() == 0) {
			if (skip == 0) {
				getViewState().showEmptyList(true);
			}
			return;
		}

		if (skip == 0) {
			followsList.clear();
			getViewState().setFollows(followsToAdd);
		}
		else {
			getViewState().addFollows(followsToAdd);
		}
		followsList.addAll(followsToAdd);
		skip += TAKE;
		filter.setTake(TAKE);
		filter.setSkip(skip);
	}

	private void handleGetFollowsListError(Throwable error) {
		getFollowsSubscription.unsubscribe();
		getViewState().showBottomProgress(false);

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		if (ApiErrorResolver.isNetworkError(error)) {
			if (followsList.size() == 0) {
				getViewState().showEmptyList(false);
				getViewState().showNoInternet(true);
			}
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	private void setFavorite(UUID followId, boolean favorite) {
		if (followsManager != null) {
			if (favoriteSubscription != null) {
				favoriteSubscription.unsubscribe();
			}
//			favoriteSubscription = followsManager.setFollowFavorite(followId, favorite)
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribeOn(Schedulers.io())
//					.subscribe(response -> handleFavoriteSuccess(followId, favorite),
//							throwable -> handleFavoriteError(throwable, followId, favorite));
		}
	}

	private void handleFavoriteSuccess(UUID followId, Boolean favorite) {
		favoriteSubscription.unsubscribe();

		EventBus.getDefault().post(new OnFollowFavoriteChangedEvent(followId, favorite));
	}

	private void handleFavoriteError(Throwable throwable, UUID followId, Boolean favorite) {
		favoriteSubscription.unsubscribe();
		getViewState().changeFollowIsFavorite(followId, favorite);

//		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

//	@Subscribe
//	public void onEventMainThread(ProgramsListFiltersAppliedEvent event) {
//		getViewState().showFiltersActive(true);
//	}
//
//	@Subscribe
//	public void onEventMainThread(ProgramsListFiltersClearedEvent event) {
//		getViewState().showFiltersActive(false);
//	}

	@Subscribe
	public void onEventMainThread(OnFollowFavoriteChangedEvent event) {
		getViewState().changeFollowIsFavorite(event.getFollowId(), event.isFavorite());
	}

	@Subscribe
	public void onEventMainThread(OnListFollowFavoriteClickedEvent event) {
		setFavorite(event.getFollowId(), event.isFavorite());
	}
}
