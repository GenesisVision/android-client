package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ProgramsList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.InvestorDashboardManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.events.OnBrowseProgramsClickedEvent;
import vision.genesis.clientapp.model.events.OnDashboardProgramsUpdateEvent;
import vision.genesis.clientapp.model.events.OnDashboardReinvestClickedEvent;
import vision.genesis.clientapp.model.events.OnProgramReinvestChangedEvent;
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

	@Inject
	public ProgramsManager programsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription dateRangeSubscription;

	private Subscription getProgramsSubscription;

	private Subscription reinvestSubscription;

	private DateRange dateRange;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToDateRange();
	}

	@Override
	public void onDestroy() {
		if (dateRangeSubscription != null)
			dateRangeSubscription.unsubscribe();
		if (getProgramsSubscription != null)
			getProgramsSubscription.unsubscribe();
		if (reinvestSubscription != null)
			reinvestSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onShow() {
		getPrograms();
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
		getPrograms();
	}

	private void getPrograms() {
		if (dateRange != null)
			getProgramsSubscription = dashboardManager.getPrograms(SortingEnum.BYPROFITDESC.toString(), dateRange, 0, 100)
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

	private void setReinvest(UUID programId, boolean reinvest) {
		if (programsManager != null) {
			if (reinvestSubscription != null)
				reinvestSubscription.unsubscribe();
			reinvestSubscription = programsManager.setReinvest(programId, reinvest)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleReinvestSuccess(programId, reinvest),
							throwable -> handleReinvestError(throwable, programId, reinvest));
		}
	}

	private void handleReinvestSuccess(UUID programId, Boolean reinvest) {
		reinvestSubscription.unsubscribe();

		EventBus.getDefault().post(new OnProgramReinvestChangedEvent(programId, reinvest));
	}

	private void handleReinvestError(Throwable throwable, UUID programId, Boolean reinvest) {
		reinvestSubscription.unsubscribe();
		getViewState().setProgramReinvest(programId, reinvest);

//		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnProgramReinvestChangedEvent event) {
		getViewState().setProgramReinvest(event.getProgramId(), event.getReinvest());
	}

	@Subscribe
	public void onEventMainThread(OnDashboardReinvestClickedEvent event) {
		setReinvest(event.getProgramId(), event.getReinvest());
	}
}
