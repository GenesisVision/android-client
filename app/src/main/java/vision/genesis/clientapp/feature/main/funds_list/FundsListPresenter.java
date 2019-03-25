package vision.genesis.clientapp.feature.main.funds_list;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.FundDetails;
import io.swagger.client.model.FundsList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.filters_sorting.SortingFiltersButtonsView;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.ProgramsFilter;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.events.OnFundFavoriteChangedEvent;
import vision.genesis.clientapp.model.events.OnListFundFavoriteClickedEvent;
import vision.genesis.clientapp.model.events.ProgramsListFiltersAppliedEvent;
import vision.genesis.clientapp.model.events.ProgramsListFiltersClearedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

@InjectViewState
public class FundsListPresenter extends MvpPresenter<FundsListView> implements SortingFiltersButtonsView.OnFilterUpdatedListener
{
	private static int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public FundsManager fundsManager;

	private Subscription userSubscription;

	private Subscription getFundsSubscription;

	private Subscription favoriteSubscription;

	private List<FundDetails> fundsList = new ArrayList<>();

	private int skip = 0;

	private ProgramsFilter filter;

	private List<FundDetails> fundsToAdd = new ArrayList<>();

	private UUID managerId;

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
		getFundsList(true);
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null)
			userSubscription.unsubscribe();
		if (getFundsSubscription != null)
			getFundsSubscription.unsubscribe();
		if (favoriteSubscription != null)
			favoriteSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(String location, UUID managerId) {
		this.location = location;
		this.managerId = managerId;
		if (filter != null) {
			filter.setManagerId(managerId);
		}
		if (!location.equals(FundsListFragment.LOCATION_SEARCH)) {
			isDataSet = true;
			getFundsList(true);
		}
	}

	void showSearchResults(FundsList result) {
		skip = 0;
		handleGetFundsList(result);
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getFundsList(true);
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		getFundsList(true);
	}

	void onLastListItemVisible() {
		getViewState().showBottomProgress(true);
		getFundsList(false);
	}

	void onFiltersClicked() {
		getViewState().showFiltersActivity(filter);
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(user -> userUpdated(), error -> userUpdated());
	}

	private void userUpdated() {
		getFundsList(true);
	}

	private void createFilter() {
		filter = new ProgramsFilter();
		filter.setSkip(0);
		filter.setTake(TAKE);
		filter.setManagerId(managerId);
		filter.setDateRange(dateRange);
		filter.setSorting(SortingEnum.BYPROFITDESC);
//		filter.setEquityChartLength(10);
	}

	public void onFilterUpdated(ProgramsFilter filter) {
		this.filter = filter;
		getViewState().setRefreshing(true);
		getFundsList(true);
	}

	private void getFundsList(boolean forceUpdate) {
		if (filter != null && fundsManager != null && isDataSet) {
			if (forceUpdate) {
				skip = 0;
				filter.setSkip(skip);
			}

			if (getFundsSubscription != null)
				getFundsSubscription.unsubscribe();
			getFundsSubscription = fundsManager.getFundsList(filter)
					.subscribeOn(Schedulers.computation())
//				.map(this::prepareData)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetFundsList,
							this::handleGetFundsListError);
		}
	}

//	private InvestmentProgramsViewModel prepareData(InvestmentProgramsViewModel model) {
//		fundsToAdd = InvestmentProgramExtended.extendInvestmentPrograms(model.getInvestmentPrograms());
//		return model;
//	}

	private void handleGetFundsList(FundsList response) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);
		getViewState().showEmptyList(false);
		getViewState().showBottomProgress(false);

		if (getFundsSubscription != null)
			getFundsSubscription.unsubscribe();

		fundsToAdd = response.getFunds();

		getViewState().setFundsCount(StringFormatUtil.formatAmount(response.getTotal(), 0, 0));

		if (fundsToAdd.size() == 0) {
			if (skip == 0)
				getViewState().showEmptyList(true);
			return;
		}

		if (skip == 0) {
			fundsList.clear();
			getViewState().setFunds(fundsToAdd);
		}
		else {
			getViewState().addFunds(fundsToAdd);
		}
		fundsList.addAll(fundsToAdd);
		skip += TAKE;
		filter.setTake(TAKE);
		filter.setSkip(skip);
	}

	private void handleGetFundsListError(Throwable error) {
		getFundsSubscription.unsubscribe();
		getViewState().showBottomProgress(false);

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		if (ApiErrorResolver.isNetworkError(error)) {
			if (fundsList.size() == 0) {
				getViewState().showEmptyList(false);
				getViewState().showNoInternet(true);
			}
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	private void setFavorite(UUID fundId, boolean favorite) {
		if (fundsManager != null) {
			if (favoriteSubscription != null)
				favoriteSubscription.unsubscribe();
			favoriteSubscription = fundsManager.setFundFavorite(fundId, favorite)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleFavoriteSuccess(fundId, favorite),
							throwable -> handleFavoriteError(throwable, fundId, favorite));
		}
	}

	private void handleFavoriteSuccess(UUID fundId, Boolean favorite) {
		favoriteSubscription.unsubscribe();

		EventBus.getDefault().post(new OnFundFavoriteChangedEvent(fundId, favorite));
	}

	private void handleFavoriteError(Throwable throwable, UUID programId, Boolean favorite) {
		favoriteSubscription.unsubscribe();
		getViewState().changeFundIsFavorite(programId, favorite);

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
	public void onEventMainThread(OnFundFavoriteChangedEvent event) {
		getViewState().changeFundIsFavorite(event.getFundId(), event.isFavorite());
	}

	@Subscribe
	public void onEventMainThread(OnListFundFavoriteClickedEvent event) {
		setFavorite(event.getFundId(), event.isFavorite());
	}
}
