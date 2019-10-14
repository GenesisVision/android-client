package vision.genesis.clientapp.feature.main.dashboard.investor.funds;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.FundsList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.events.OnBrowseFundsClickedEvent;
import vision.genesis.clientapp.model.events.OnDashboardFundFavoriteClickedEvent;
import vision.genesis.clientapp.model.events.OnFundFavoriteChangedEvent;
import vision.genesis.clientapp.model.events.SetDashboardFundsCountEvent;
import vision.genesis.clientapp.model.filter.DashboardFilter;
import vision.genesis.clientapp.model.filter.UserFilter;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

@InjectViewState
public class DashboardFundsPresenter extends MvpPresenter<DashboardFundsView>
{
	@Inject
	public DashboardManager dashboardManager;

	@Inject
	public FundsManager fundsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription getFundsSubscription;

	private Subscription favoriteSubscription;

	private DashboardFilter filter;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		createFilter();
		getFunds();
	}

	@Override
	public void onDestroy() {
		if (getFundsSubscription != null)
			getFundsSubscription.unsubscribe();
		if (favoriteSubscription != null)
			favoriteSubscription.unsubscribe();

		super.onDestroy();
	}

	private void createFilter() {
		if (filter == null)
			filter = new DashboardFilter();
		this.filter.setSkip(0);
		this.filter.setTake(1000);
		this.filter.setDateRange(DateRange.createFromEnum(DateRange.DateRangeEnum.MONTH));
		this.filter.setChartPointsCount(10);
		filter.setSorting(SortingEnum.BYTITLEASC);
	}

	void onShow() {
		if (getFundsSubscription != null && !getFundsSubscription.isUnsubscribed())
			return;
		getFunds();
	}

	void onBrowseFundsClicked() {
		EventBus.getDefault().post(new OnBrowseFundsClickedEvent());
	}

	void onFiltersClicked() {
		getViewState().showFiltersActivity(filter);
	}

	void onFilterUpdated(UserFilter userFilter) {
		this.filter.updateWithUserFilter(userFilter);
		getViewState().showProgressBar(true);
		getFunds();
	}

	private void getFunds() {
		if (filter != null)
			getFundsSubscription = dashboardManager.getFunds(filter)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetFundsSuccess,
							this::handleGetFundsError);
	}

	private void handleGetFundsSuccess(FundsList response) {
		getFundsSubscription.unsubscribe();

		getViewState().showProgressBar(false);

		getViewState().setFunds(response.getFunds());
		EventBus.getDefault().post(new SetDashboardFundsCountEvent(response.getTotal()));
	}

	private void handleGetFundsError(Throwable throwable) {
		getFundsSubscription.unsubscribe();

		getViewState().showProgressBar(false);
		getViewState().showEmpty(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
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
		getViewState().setFundFavorite(programId, favorite);

//		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnFundFavoriteChangedEvent event) {
		getViewState().setFundFavorite(event.getFundId(), event.isFavorite());
	}

	@Subscribe
	public void onEventMainThread(OnDashboardFundFavoriteClickedEvent event) {
		setFavorite(event.getFundId(), event.isFavorite());
	}
}
