package vision.genesis.clientapp.feature.main.dashboard.investor;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.DashboardPortfolioEvent;
import io.swagger.client.model.DashboardSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.InvestorDashboardManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class InvestorDashboardPresenter extends MvpPresenter<InvestorDashboardView>
{
	@Inject
	public Context context;

	@Inject
	public InvestorDashboardManager dashboardManager;

	private Subscription getEventsSubscription;

	private Subscription dashboardSubscription;

	private DateRange dateRange = new DateRange();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

//		EventBus.getDefault().register(this);
		getDashboard();
	}

	@Override
	public void onDestroy() {
		if (getEventsSubscription != null)
			getEventsSubscription.unsubscribe();
		if (dashboardSubscription != null)
			dashboardSubscription.unsubscribe();
//		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
//		getEvents();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getDashboard();
//		getEvents();
	}

	private void getDashboard() {
		if (dashboardSubscription != null)
			dashboardSubscription.unsubscribe();
		dashboardSubscription = dashboardManager.getDashboard(dateRange)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetDashboardSuccess,
						this::handleGetDashboardError);
	}

	private void handleGetDashboardSuccess(DashboardSummary response) {
		dashboardSubscription.unsubscribe();
		getViewState().setRefreshing(false);

		getViewState().setChartData(response.getChart());
		getViewState().setPortfolioEvents(response.getEvents().getEvents());
		getViewState().setAssetsCount(response.getProgramsCount(), response.getFundsCount());

	}

	private void handleGetDashboardError(Throwable throwable) {
		dashboardSubscription.unsubscribe();
		getViewState().setRefreshing(false);
	}

	private void getEvents() {
		getEventsSubscription = dashboardManager.getPortfolioEvents()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetEventsSuccess,
						this::handleGetEventsError);
	}

	private void handleGetEventsSuccess(List<DashboardPortfolioEvent> response) {
		List<DashboardPortfolioEvent> events = response.size() > 10 ? response.subList(0, 10) : response;
		getViewState().setPortfolioEvents(events);
	}

	private void handleGetEventsError(Throwable throwable) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
//			if (programs.size() == 0)
//				getViewState().showNoInternet(true);
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}
}
