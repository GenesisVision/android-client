package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ProgramsApi;
import io.swagger.client.model.DashboardPortfolioEvents;
import io.swagger.client.model.LevelUpSummary;
import io.swagger.client.model.ProgramBalanceChart;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramInvestInfo;
import io.swagger.client.model.ProgramPeriodsViewModel;
import io.swagger.client.model.ProgramProfitChart;
import io.swagger.client.model.ProgramWithdrawInfo;
import io.swagger.client.model.ProgramsList;
import io.swagger.client.model.TradesViewModel;
import rx.Observable;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

public class ProgramsManager
{
	private InvestorApi investorApi;

//	private ManagerApi managerApi;

	private ProgramsApi programsApi;

	//	public ProgramsManager(InvestorApi investorApi, ManagerApi managerApi, ProgramApi programsApi) {
	public ProgramsManager(InvestorApi investorApi, ProgramsApi programsApi) {
		this.investorApi = investorApi;
//		this.managerApi = managerApi;
		this.programsApi = programsApi;
	}

	public Observable<ProgramsList> getProgramsList(ProgramsFilter filter) {
		return programsApi.v10ProgramsGet(AuthManager.token.getValue(),
				filter.getLevelMin(), filter.getLevelMax(), null,
				filter.getProfitAvgMin(), filter.getProfitAvgMax(),
				filter.getSorting() != null ? filter.getSorting().getValue() : null,
				filter.getCurrency() != null ? filter.getCurrency().getValue() : null, null,
				filter.getLevelUpFrom(), filter.getTags(), null,
				filter.getDateRange() != null ? filter.getDateRange().getFrom() : null,
				filter.getDateRange() != null ? filter.getDateRange().getTo() : null,
				filter.getChartPointsCount(), filter.getMask(),
				filter.getFacetId() != null ? filter.getFacetId().toString() : null,
				filter.getIsFavorite(), filter.getIsEnabled(),
				null, null,
				filter.getIds(), null,
				filter.getManagerId() != null ? filter.getManagerId().toString() : null, null,
				null,
				filter.getSkip(), filter.getTake());
	}

	public Observable<Void> setProgramFavorite(UUID programId, boolean isFavorite) {
		return isFavorite ? programFavoritesAdd(programId) : programFavoritesRemove(programId);
	}

	private Observable<Void> programFavoritesAdd(UUID programId) {
		return programsApi.v10ProgramsByIdFavoriteAddPost(programId, AuthManager.token.getValue());
	}

	private Observable<Void> programFavoritesRemove(UUID programId) {
		return programsApi.v10ProgramsByIdFavoriteRemovePost(programId, AuthManager.token.getValue());
	}

	public Observable<ProgramDetailsFull> getProgramDetails(UUID programId, CurrencyEnum baseCurrency) {
		return programsApi.v10ProgramsByIdGet(programId.toString(), AuthManager.token.getValue(), baseCurrency.getValue());
	}

	public Observable<TradesViewModel> getProgramOpenPositions(UUID programId) {
		return programsApi.v10ProgramsByIdTradesOpenGet(programId, "ByDateDesc", null, null, null, 0, 1000);
	}

	public Observable<ProgramProfitChart> getProfitChart(UUID programId, DateRange dateRange, Integer maxPointCount) {
		return programsApi.v10ProgramsByIdChartsProfitGet(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, null);
	}

	public Observable<ProgramBalanceChart> getBalanceChart(UUID programId, DateRange dateRange, Integer maxPointCount) {
		return programsApi.v10ProgramsByIdChartsBalanceGet(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, null);
	}

	public Observable<TradesViewModel> getProgramTrades(UUID programId, DateRange dateRange, Integer skip, Integer take) {
		return programsApi.v10ProgramsByIdTradesGet(programId, dateRange.getFrom(), dateRange.getTo(), null, null, null, null, skip, take);
	}

	public Observable<DashboardPortfolioEvents> getProgramHistory(UUID programId, DateRange dateRange, Integer skip, Integer take) {
		return investorApi.v10InvestorPortfolioEventsGet(AuthManager.token.getValue(), programId, dateRange.getFrom(), dateRange.getTo(), null, null, skip, take);
	}

	public Observable<ProgramInvestInfo> getInvestInfo(UUID programId, String baseCurrency) {
		return investorApi.v10InvestorProgramsByIdInvestInfoByCurrencyGet(programId, baseCurrency, AuthManager.token.getValue());
	}

	public Observable<ProgramWithdrawInfo> getWithdrawInfo(UUID programId, CurrencyEnum baseCurrency) {
		return investorApi.v10InvestorProgramsByIdWithdrawInfoByCurrencyGet(programId, baseCurrency.getValue(), AuthManager.token.getValue());
	}

	public Observable<Void> invest(ProgramRequest investRequest) {
		return investorApi.v10InvestorProgramsByIdInvestByAmountPost(investRequest.getProgramId(), investRequest.getAmount(), AuthManager.token.getValue(), investRequest.getWalletCurrency());
	}

	public Observable<Void> withdraw(ProgramRequest withdrawRequest) {
		return investorApi.v10InvestorProgramsByIdWithdrawMultiByAmountPost(
				withdrawRequest.getProgramId(),
				withdrawRequest.getAmount(),
				AuthManager.token.getValue(),
				withdrawRequest.isWithdrawAll());
	}

	public Observable<Void> setReinvest(UUID programId, boolean reinvest) {
		return reinvest
				? investorApi.v10InvestorProgramsByIdReinvestOnPost(programId, AuthManager.token.getValue())
				: investorApi.v10InvestorProgramsByIdReinvestOffPost(programId, AuthManager.token.getValue());
	}

	public Observable<LevelUpSummary> getRatingInfo() {
		return programsApi.v10ProgramsLevelupSummaryGet(AuthManager.token.getValue());
	}

	public Observable<ProgramPeriodsViewModel> getPeriodHistory(UUID programId, DateRange dateRange, int skip, int take) {
		return programsApi.v10ProgramsByIdPeriodsGet(programId.toString(), AuthManager.token.getValue(),
				dateRange.getFrom(), dateRange.getTo(),
				null, null, null,
				skip, take);
	}
}