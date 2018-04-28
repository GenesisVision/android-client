package vision.genesis.clientapp.feature.main.dashboard;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramDashboardInvestor;
import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.InvestorDashboard;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.events.OnDashboardProgramsUpdateEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class DashboardPresenter extends MvpPresenter<DashboardView>
{
	@Inject
	public Context context;

	@Inject
	public InvestManager investManager;

	private Subscription getInvestmentsSubscription;

	private List<InvestmentProgramDashboardInvestor> activePrograms = new ArrayList<>();

	private List<InvestmentProgramDashboardInvestor> archivedPrograms = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (getInvestmentsSubscription != null)
			getInvestmentsSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
		getInvestments();
	}

	private void getInvestments() {
		getViewState().showProgressBar(true);
		getInvestmentsSubscription = investManager.getInvestments(InvestmentProgramsFilter.SortingEnum.BYPROFITDESC.toString())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetInvestmentsSuccess,
						this::handleGetInvestmentsError);
	}

	private void handleGetInvestmentsSuccess(InvestorDashboard dashboard) {
		getInvestmentsSubscription.unsubscribe();

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);

		activePrograms = new ArrayList<>();
		archivedPrograms = new ArrayList<>();

		List<InvestmentProgramDashboardInvestor> programs = dashboard.getInvestmentPrograms();
		for (InvestmentProgramDashboardInvestor program : programs) {
			if (!program.isIsArchived())
				activePrograms.add(program);
			else
				archivedPrograms.add(program);
		}

		getViewState().setActivePrograms(activePrograms);
		getViewState().setArchivedPrograms(archivedPrograms);
		getViewState().setTotalPortfolioValue(dashboard.getTotalPortfolioAmount());
	}

	private void handleGetInvestmentsError(Throwable throwable) {
		getInvestmentsSubscription.unsubscribe();

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showEmpty(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (activePrograms.size() == 0 && archivedPrograms.size() == 0)
				getViewState().showNoInternet(true);
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	@Subscribe
	public void onEventMainThread(OnDashboardProgramsUpdateEvent event) {
		getInvestments();
	}
}
