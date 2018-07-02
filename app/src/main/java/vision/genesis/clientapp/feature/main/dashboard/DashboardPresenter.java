package vision.genesis.clientapp.feature.main.dashboard;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramDashboardInvestor;
import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.InvestorDashboard;
import io.swagger.client.model.WalletTransaction;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.InvestorDashboardManager;
import vision.genesis.clientapp.model.InvestmentProgramDashboardExtended;
import vision.genesis.clientapp.model.events.OnDashboardProgramsUpdateEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.StringFormatUtil;

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
	public InvestorDashboardManager dashboardManager;

	private Subscription getInvestmentsSubscription;

	private List<InvestmentProgramDashboardExtended> activePrograms = new ArrayList<>();

	private List<InvestmentProgramDashboardExtended> archivedPrograms = new ArrayList<>();

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
		getInvestmentsSubscription = dashboardManager.getInvestments(InvestmentProgramsFilter.SortingEnum.BYPROFITDESC.toString())
				.subscribeOn(Schedulers.computation())
				.map(this::prepareData)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetInvestmentsSuccess,
						this::handleGetInvestmentsError);
	}

	private InvestorDashboard prepareData(InvestorDashboard dashboard) {
		List<InvestmentProgramDashboardInvestor> programs = dashboard.getInvestmentPrograms();
		activePrograms = new ArrayList<>();
		archivedPrograms = new ArrayList<>();

		for (InvestmentProgramDashboardInvestor program : programs) {
			InvestmentProgramDashboardExtended programExtended = new InvestmentProgramDashboardExtended(program);

			programExtended.setTokens(StringFormatUtil.formatAmount(program.getInvestedTokens(), 0,
					Constants.TOKENS_MAX_DECIMAL_POINT_DIGITS));
			double tokensFiatValue = program.getInvestedTokens() * program.getToken().getInitialPrice();
			programExtended.setTokensFiat(String.format(Locale.getDefault(), "($%.2f)", tokensFiatValue));

			programExtended.setProfitShort(StringFormatUtil.formatAmount(program.getProfitFromProgram(), 0, 2));
			programExtended.setProfitFull(StringFormatUtil.formatAmount(program.getProfitFromProgram(), 2,
					StringFormatUtil.getCurrencyMaxFraction(WalletTransaction.CurrencyEnum.GVT.toString())));

			programExtended.setEquityChart(ProfitSmallChartView.getPreparedEquityChart(program.getEquityChart()));

			if (!program.isIsArchived())
				activePrograms.add(programExtended);
			else
				archivedPrograms.add(programExtended);
		}

		return dashboard;
	}

	private void handleGetInvestmentsSuccess(InvestorDashboard dashboard) {
		getInvestmentsSubscription.unsubscribe();

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);

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
