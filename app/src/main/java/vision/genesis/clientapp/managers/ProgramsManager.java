package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ProgramsApi;
import io.swagger.client.model.ProgramChart;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramsList;
import rx.Observable;
import vision.genesis.clientapp.model.DateRange;
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
				filter.getStatisticDateFrom(), filter.getStatisticDateTo(), filter.getSorting().getValue(),
				filter.getMask(), filter.getFacetId(), filter.getIsFavorite(),
				filter.getCurrency().getValue(), filter.getIds(), filter.getSkip(), filter.getTake());
//		return Constants.IS_INVESTOR
//				? investorApi.apiInvestorInvestmentProgramsPost(AuthManager.token.getValue(), filter)
//				: managerApi.apiManagerInvestmentProgramsPost(AuthManager.token.getValue(), filter);
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

	public Observable<ProgramDetailsFull> getProgramDetails(UUID programId) {
		return programsApi.v10ProgramsByIdGet(programId, AuthManager.token.getValue());
	}

//	public Observable<TradesViewModel> getProgramTrades(TradesFilter filter) {
//		return programsApi.v10ProgramByIdTradesGet()
//	}

	public Observable<ProgramChart> getChart(UUID programId, DateRange dateRange, Integer maxPointCount) {
		return programsApi.v10ProgramsByIdChartGet(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount);
	}

//	public Observable<InvestmentProgramBuyToken> getBuyTokensModel(UUID programId) {
//		return investorApi.apiInvestorInvestmentProgramBuyTokensGet(programId, AuthManager.token.getValue());
//	}
//
//	public Observable<WalletsViewModel> invest(ProgramRequest investRequest) {
//		Invest model = new Invest();
//		model.setInvestmentProgramId(investRequest.programId);
//		model.setAmount(investRequest.amount);
//		return investorApi.apiInvestorInvestmentProgramsInvestPost(AuthManager.token.getValue(), model);
//	}
//
//	public Observable<Void> withdraw(ProgramRequest withdrawalRequest) {
//		Invest model = new Invest();
//		model.setInvestmentProgramId(withdrawalRequest.programId);
//		model.setAmount(withdrawalRequest.amount);
//		return investorApi.apiInvestorInvestmentProgramsWithdrawPost(AuthManager.token.getValue(), model);
//	}
//
//	public Observable<Void> cancelRequest(UUID requestId) {
//		return investorApi.apiInvestorInvestmentProgramsCancelInvestmentRequestPost(requestId, AuthManager.token.getValue());
//	}
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