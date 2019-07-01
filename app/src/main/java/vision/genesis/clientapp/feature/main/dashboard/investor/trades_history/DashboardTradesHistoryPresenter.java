package vision.genesis.clientapp.feature.main.dashboard.investor.trades_history;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.SignalsList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.dashboard.investor.DashboardActionStatus;
import vision.genesis.clientapp.managers.InvestorDashboardManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.events.OnBrowseProgramsClickedEvent;
import vision.genesis.clientapp.model.events.OnDashboardProgramsUpdateEvent;
import vision.genesis.clientapp.model.events.SetDashboardSignalsCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

@InjectViewState
public class DashboardTradesHistoryPresenter extends MvpPresenter<DashboardTradesHistoryView>
{
	@Inject
	public InvestorDashboardManager dashboardManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription dateRangeSubscription;

	private Subscription getSignalProvidersSubscription;

	private DateRange dateRange;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToDateRange();
	}

	@Override
	public void onDestroy() {
		if (dateRangeSubscription != null)
			dateRangeSubscription.unsubscribe();
		if (getSignalProvidersSubscription != null)
			getSignalProvidersSubscription.unsubscribe();

		super.onDestroy();
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

	private void subscribeToDateRange() {
		dateRangeSubscription = settingsManager.getDateRange()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::dateRangeChangedHandler);
	}

	private void dateRangeChangedHandler(DateRange dateRange) {
		this.dateRange = dateRange;
		getSignalProviders();
	}

	private void getSignalProviders() {
		if (dateRange != null)
			getSignalProvidersSubscription = dashboardManager.getSignalProviders(DashboardActionStatus.ALL.getValue(),
					SortingEnum.BYPROFITDESC.toString(), dateRange, 0, 100)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetSignalsSuccess,
							this::handleGetSignalsError);
	}

	private void handleGetSignalsSuccess(SignalsList response) {
		getSignalProvidersSubscription.unsubscribe();

		getViewState().showProgressBar(false);

		getViewState().setSignals(response.getPrograms());
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
