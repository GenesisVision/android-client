package vision.genesis.clientapp.feature.main.dashboard.investor;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.DashboardProgramsList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.InvestorDashboardManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.InvestmentProgramDashboardExtended;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.events.OnDashboardProgramsUpdateEvent;
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

	private Subscription getInvestmentsSubscription;

	private List<InvestmentProgramDashboardExtended> activePrograms = new ArrayList<>();

	private List<InvestmentProgramDashboardExtended> archivedPrograms = new ArrayList<>();

	private DateRange dateRange = new DateRange();

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
		getInvestmentsSubscription = dashboardManager.getPrograms(SortingEnum.BYPROFITDESC.toString(), dateRange, 0, 1000)
				.subscribeOn(Schedulers.computation())
				.map(this::prepareData)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetInvestmentsSuccess,
						this::handleGetInvestmentsError);
	}

	private DashboardProgramsList prepareData(DashboardProgramsList dashboard) {
//		List<InvestmentProgramDashboardInvestor> programs = dashboard.getInvestmentPrograms();
//		activePrograms = new ArrayList<>();
//		archivedPrograms = new ArrayList<>();
//
//		for (InvestmentProgramDashboardInvestor program : programs) {
//			InvestmentProgramDashboardExtended programExtended = new InvestmentProgramDashboardExtended(program);
//
//			programExtended.setTokens(StringFormatUtil.formatAmount(program.getInvestedTokens(), 0,
//					Constants.TOKENS_MAX_DECIMAL_POINT_DIGITS));
//			double tokensFiatValue = program.getInvestedTokens() * program.getToken().getInitialPrice();
//			programExtended.setTokensFiat(String.format(Locale.getDefault(), "($%.2f)", tokensFiatValue));
//
//			programExtended.setProfitShort(StringFormatUtil.formatAmount(program.getProfitFromProgram(), 0, 2));
//			programExtended.setProfitFull(StringFormatUtil.formatAmount(program.getProfitFromProgram(), 2,
//					StringFormatUtil.getCurrencyMaxFraction(CurrencyEnum.GVT.toString())));
//
//			programExtended.setEquityChart(ProfitSmallChartView.getPreparedEquityChart(program.getEquityChart()));
//
//			if (!program.isIsArchived())
//				activePrograms.add(programExtended);
//			else
//				archivedPrograms.add(programExtended);
//		}

		return dashboard;
	}

	private void handleGetInvestmentsSuccess(DashboardProgramsList dashboard) {
		getInvestmentsSubscription.unsubscribe();

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);

		getViewState().setActivePrograms(activePrograms);
		getViewState().setArchivedPrograms(archivedPrograms);
//		getViewState().setTotalPortfolioValue(dashboard.getTotalPortfolioAmount());
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
