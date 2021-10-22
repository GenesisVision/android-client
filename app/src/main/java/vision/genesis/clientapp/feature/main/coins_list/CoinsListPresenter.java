package vision.genesis.clientapp.feature.main.coins_list;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.CoinsAsset;
import io.swagger.client.model.CoinsAssetItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.CoinsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.events.OnCoinFavoriteChangedEvent;
import vision.genesis.clientapp.model.events.OnFundFavoriteChangedEvent;
import vision.genesis.clientapp.model.events.OnListCoinFavoriteClickedEvent;
import vision.genesis.clientapp.model.events.ProgramsListFiltersAppliedEvent;
import vision.genesis.clientapp.model.events.ProgramsListFiltersClearedEvent;
import vision.genesis.clientapp.model.filter.ProgramsFilter;
import vision.genesis.clientapp.model.filter.UserFilter;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/10/2021.
 */

@InjectViewState
public class CoinsListPresenter extends MvpPresenter<CoinsListView>
{
	private static int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public CoinsManager coinsManager;

	private Subscription baseCurrencySubscription;

	private Subscription getCoinsSubscription;

	private Subscription favoriteSubscription;

	private List<CoinsAsset> coinsList = new ArrayList<>();

	private int skip = 0;

	private ProgramsFilter filter;

	private List<CoinsAsset> coinsToAdd = new ArrayList<>();

	private Boolean isDataSet = false;

	private String location = "";

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH);

	private CurrencyEnum baseCurrency;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToBaseCurrency();
		getViewState().setRefreshing(true);
		getCoinsList(true);
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (getCoinsSubscription != null) {
			getCoinsSubscription.unsubscribe();
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
		if (!location.equals(CoinsListFragment.LOCATION_SEARCH)) {
			isDataSet = true;
			getCoinsList(true);
		}
	}

	void showSearchResults(CoinsAssetItemsViewModel result) {
		skip = 0;
		handleGetCoinsList(result);
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getCoinsList(true);
	}

	void onUpdateAll() {
		getViewState().showProgressBar(true);
		getCoinsList(true);
	}

	void onLastListItemVisible() {
		getViewState().showBottomProgress(true);
		getCoinsList(false);
	}

	void onFiltersClicked() {
		getViewState().showFiltersActivity(filter);
	}

	private void subscribeToBaseCurrency() {
		if (settingsManager != null) {
			baseCurrencySubscription = settingsManager.getBaseCurrency()
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::baseCurrencyChangedHandler);
		}
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;

		getCoinsList(true);
	}

	private void createFilter(ProgramsFilter filter) {
		if (filter == null) {
			filter = new ProgramsFilter();
		}
		this.filter = filter;
		this.filter.setSkip(0);
		this.filter.setTake(TAKE);
		if (this.filter.getDateRange() == null) {
			this.filter.setDateRange(dateRange);
		}
		this.filter.setSorting(SortingEnum.BYMARKETCAPDESC);
	}

	public void onFilterUpdated(UserFilter userFilter) {
		this.filter.updateWithUserFilter(userFilter);
		getViewState().setRefreshing(true);
		getCoinsList(true);
	}

	private void getCoinsList(boolean forceUpdate) {
		if (filter != null && coinsManager != null && isDataSet && baseCurrency != null) {
			if (forceUpdate) {
				skip = 0;
				filter.setSkip(skip);
			}

			if (getCoinsSubscription != null) {
				getCoinsSubscription.unsubscribe();
			}
			getCoinsSubscription = coinsManager.getCoinsList(filter)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetCoinsList,
							this::handleGetCoinsListError);
		}
	}

	private void handleGetCoinsList(CoinsAssetItemsViewModel response) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);
		getViewState().showEmptyList(false);
		getViewState().showBottomProgress(false);

		if (getCoinsSubscription != null) {
			getCoinsSubscription.unsubscribe();
		}

		coinsToAdd = response.getItems();

		if (coinsToAdd.size() == 0) {
			if (skip == 0) {
				getViewState().showEmptyList(true);
			}
			return;
		}

		if (skip == 0) {
			coinsList.clear();
			getViewState().setCoins(coinsToAdd);
		}
		else {
			getViewState().addCoins(coinsToAdd);
		}
		coinsList.addAll(coinsToAdd);
		skip += TAKE;
		filter.setTake(TAKE);
		filter.setSkip(skip);
	}

	private void handleGetCoinsListError(Throwable error) {
		getCoinsSubscription.unsubscribe();
		getViewState().showBottomProgress(false);

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		if (ApiErrorResolver.isNetworkError(error)) {
			if (coinsList.size() == 0) {
				getViewState().showEmptyList(false);
				getViewState().showNoInternet(true);
			}
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	private void setFavorite(UUID coinId, boolean favorite) {
		if (coinsManager != null) {
			if (favoriteSubscription != null) {
				favoriteSubscription.unsubscribe();
			}
			favoriteSubscription = coinsManager.setCoinFavorite(coinId, favorite)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleFavoriteSuccess(coinId, favorite),
							throwable -> handleFavoriteError(throwable, coinId, favorite));
		}
	}

	private void handleFavoriteSuccess(UUID fundId, Boolean favorite) {
		favoriteSubscription.unsubscribe();

		EventBus.getDefault().post(new OnFundFavoriteChangedEvent(fundId, favorite));
	}

	private void handleFavoriteError(Throwable throwable, UUID coinId, Boolean favorite) {
		favoriteSubscription.unsubscribe();
		getViewState().changeCoinIsFavorite(coinId, favorite);

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
	public void onEventMainThread(OnCoinFavoriteChangedEvent event) {
		getViewState().changeCoinIsFavorite(event.getCoinId(), event.isFavorite());
	}

	@Subscribe
	public void onEventMainThread(OnListCoinFavoriteClickedEvent event) {
		setFavorite(event.getCoinId(), event.isFavorite());
	}
}
