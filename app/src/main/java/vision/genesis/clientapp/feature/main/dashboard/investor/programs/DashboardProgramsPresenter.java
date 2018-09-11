package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.ProgramsList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.InvestorDashboardManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.events.OnDashboardProgramsUpdateEvent;
import vision.genesis.clientapp.model.events.OnInvestButtonClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 3/13/18.
 */

@InjectViewState
public class DashboardProgramsPresenter extends MvpPresenter<DashboardProgramsView>
{
	@Inject
	public InvestorDashboardManager dashboardManager;

	private Subscription getProgramsSubscription;

	private DateRange dateRange = new DateRange();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

//		EventBus.getDefault().register(this);
		getPrograms();
	}

	@Override
	public void onDestroy() {
		if (getProgramsSubscription != null)
			getProgramsSubscription.unsubscribe();

//		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onShow() {
		getPrograms();
	}

	void onStartInvestingClicked() {
		EventBus.getDefault().post(new OnInvestButtonClickedEvent());
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		EventBus.getDefault().post(new OnDashboardProgramsUpdateEvent());
	}

	private void getPrograms() {
		getProgramsSubscription = dashboardManager.getPrograms(SortingEnum.BYPROFITDESC.toString(), dateRange, 0, 10)
				.subscribeOn(Schedulers.computation())
				.map(this::prepareData)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProgramsSuccess,
						this::handleGetProgramsError);
	}

	private ProgramsList prepareData(ProgramsList dashboard) {
//		List<InvestmentProgramDashboardInvestor> programs = dashboard.getInvestmentPrograms();
//		programs = new ArrayList<>();
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
//				programs.add(programExtended);
//			else
//				archivedPrograms.add(programExtended);
//		}

		return dashboard;
	}

	private void handleGetProgramsSuccess(ProgramsList response) {
		getProgramsSubscription.unsubscribe();

		getViewState().showProgressBar(false);

		getViewState().setPrograms(response.getPrograms());
//		getViewState().setTotalPortfolioValue(dashboard.getTotalPortfolioAmount());
	}

	private void handleGetProgramsError(Throwable throwable) {
		getProgramsSubscription.unsubscribe();

		getViewState().showProgressBar(false);
		getViewState().showEmpty(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
//			if (programs.size() == 0)
//			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}
}
