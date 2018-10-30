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
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.InvestorDashboardManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.events.OnBrowseFundsClickedEvent;
import vision.genesis.clientapp.model.events.OnDashboardFundFavoriteClickedEvent;
import vision.genesis.clientapp.model.events.OnFundFavoriteChangedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

@InjectViewState
public class DashboardFundsPresenter extends MvpPresenter<DashboardFundsView>
{
	@Inject
	public InvestorDashboardManager dashboardManager;

	@Inject
	public FundsManager fundsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription dateRangeSubscription;

	private Subscription getFundsSubscription;

	private Subscription favoriteSubscription;

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
		if (getFundsSubscription != null)
			getFundsSubscription.unsubscribe();
		if (favoriteSubscription != null)
			favoriteSubscription.unsubscribe();

		super.onDestroy();
	}

	void onShow() {
		getFunds();
	}

	void onBrowseFundsClicked() {
		EventBus.getDefault().post(new OnBrowseFundsClickedEvent());
	}

	private void subscribeToDateRange() {
		dateRangeSubscription = settingsManager.getDateRange()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::dateRangeChangedHandler);
	}

	private void dateRangeChangedHandler(DateRange dateRange) {
		this.dateRange = dateRange;
		getFunds();
	}

	private void getFunds() {
		if (dateRange != null)
			getFundsSubscription = dashboardManager.getFunds(SortingEnum.BYPROFITDESC.toString(), dateRange, 0, 100)
					.subscribeOn(Schedulers.computation())
					.map(this::prepareData)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetFundsSuccess,
							this::handleGetFundsError);
	}

	private FundsList prepareData(FundsList dashboard) {
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

	private void handleGetFundsSuccess(FundsList response) {
		getFundsSubscription.unsubscribe();

		getViewState().showProgressBar(false);

		getViewState().setFunds(response.getFunds());
//		getViewState().setTotalPortfolioValue(dashboard.getTotalPortfolioAmount());
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
