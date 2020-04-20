package vision.genesis.clientapp.feature.main.dashboard.old.investor.copytrading;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.ItemsViewModelCopyTradingAccountInfo;
import rx.Subscription;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.events.OnBrowseProgramsClickedEvent;
import vision.genesis.clientapp.model.events.OnDashboardProgramsUpdateEvent;
import vision.genesis.clientapp.model.events.SetDashboardSignalsCountEvent;
import vision.genesis.clientapp.model.filter.DashboardFilter;
import vision.genesis.clientapp.model.filter.UserFilter;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

@InjectViewState
public class DashboardCopytradingPresenter extends MvpPresenter<DashboardCopytradingView>
{
	@Inject
	public DashboardManager dashboardManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription getSignalProvidersSubscription;

	private DashboardFilter filter;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		createFilter();
	}

	@Override
	public void onDestroy() {
		if (getSignalProvidersSubscription != null) {
			getSignalProvidersSubscription.unsubscribe();
		}

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
		getSignalProviders();
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
		getSignalProviders();
	}

	private void getSignalProviders() {
//		if (filter != null)
//			getSignalProvidersSubscription = followManager.getSignalProviders(filter)
//					.subscribeOn(Schedulers.computation())
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribe(this::handleGetSignalsSuccess,
//							this::handleGetSignalsError);
	}

	private void handleGetSignalsSuccess(ItemsViewModelCopyTradingAccountInfo response) {
		getSignalProvidersSubscription.unsubscribe();

		getViewState().showProgressBar(false);

		getViewState().setSignals(response.getItems());
		EventBus.getDefault().post(new SetDashboardSignalsCountEvent(response.getTotal()));
	}

	private void handleGetSignalsError(Throwable throwable) {
		getSignalProvidersSubscription.unsubscribe();

		getViewState().showProgressBar(false);
		getViewState().showEmpty(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}
}
