package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ProgramsApi;
import io.swagger.client.model.DashboardPortfolioEvents;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramInvestInfo;
import io.swagger.client.model.ProgramProfitChart;
import io.swagger.client.model.ProgramsList;
import io.swagger.client.model.TradesViewModel;
import rx.Observable;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.ProgramsFilter;

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
				filter.getLevelMin(), filter.getLevelMax(), filter.getProfitAvgMin(), filter.getProfitAvgMax(),
				filter.getSorting().getValue(), filter.getCurrency() != null ? filter.getCurrency().getValue() : null, null,
				filter.getStatisticDateFrom(), filter.getStatisticDateTo(), 10,
				filter.getMask(), filter.getFacetId() != null ? filter.getFacetId().toString() : null, filter.getIsFavorite(),
				filter.getIds(), filter.getSkip(), filter.getTake());
	}

	public Observable<DashboardPortfolioEvents> getPortfolioEvents(DateRange dateRange, Integer skip, Integer take) {
		return investorApi.v10InvestorPortfolioEventsGet(AuthManager.token.getValue(), null, dateRange.getFrom(), dateRange.getTo(), null, null, skip, take);
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

	public Observable<ProgramProfitChart> getProfitChart(UUID programId, DateRange dateRange, Integer maxPointCount) {
		return programsApi.v10ProgramsByIdChartsProfitGet(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount);
	}

	public Observable<TradesViewModel> getProgramTrades(UUID programId, DateRange dateRange, Integer skip, Integer take) {
		return programsApi.v10ProgramsByIdTradesGet(programId, dateRange.getFrom(), dateRange.getTo(), null, null, skip, take);
	}

	public Observable<DashboardPortfolioEvents> getProgramHistory(UUID programId, DateRange dateRange, Integer skip, Integer take) {
		return investorApi.v10InvestorPortfolioEventsGet(AuthManager.token.getValue(), programId, dateRange.getFrom(), dateRange.getTo(), null, null, skip, take);
	}

	public Observable<ProgramInvestInfo> getInvestInfo(UUID programId, CurrencyEnum baseCurrency) {
		return investorApi.v10InvestorProgramsByIdInvestInfoByCurrencyGet(programId, baseCurrency.getValue(), AuthManager.token.getValue());
	}

	public Observable<Void> invest(ProgramRequest investRequest) {
		return investorApi.v10InvestorProgramsByIdInvestByAmountPost(investRequest.getProgramId(), investRequest.getAmount(), AuthManager.token.getValue());
	}

	//	public Observable<Void> withdraw(ProgramRequest withdrawalRequest) {
//		Invest model = new Invest();
//		model.setInvestmentProgramId(withdrawalRequest.programId);
//		model.setAmount(withdrawalRequest.amount);
//		return investorApi.apiInvestorInvestmentProgramsWithdrawPost(AuthManager.token.getValue(), model);
//	}
//
	public Observable<Void> cancelRequest(UUID requestId) {
		return investorApi.v10InvestorProgramsRequestsByIdCancelPost(requestId, AuthManager.token.getValue());
	}
//
//	public Observable<InvestmentProgramRequests> getInvestmentProgramRequests(InvestmentProgramRequestsFilter filter) {
//		return investorApi.apiInvestorInvestmentProgramRequestsPost(AuthManager.token.getValue(), filter);
//	}
//
//	public Observable<BrokersViewModel> getDataToCreateProgram() {
//		return managerApi.apiManagerBrokersPost(new BrokersFilter());
//	}
//
//	public Observable<UUID> sendCreateProgramRequest(NewInvestmentRequest request) {
//		return managerApi.apiManagerAccountNewInvestmentRequestPost(AuthManager.token.getValue(), request);
//	}
}