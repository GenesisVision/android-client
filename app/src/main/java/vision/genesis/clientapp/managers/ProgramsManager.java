package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.api.ProgramApi;
import io.swagger.client.model.BrokersFilter;
import io.swagger.client.model.BrokersViewModel;
import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentProgramBuyToken;
import io.swagger.client.model.InvestmentProgramRequests;
import io.swagger.client.model.InvestmentProgramRequestsFilter;
import io.swagger.client.model.InvestmentProgramViewModel;
import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.NewInvestmentRequest;
import io.swagger.client.model.TradesChartViewModel;
import io.swagger.client.model.TradesFilter;
import io.swagger.client.model.TradesViewModel;
import io.swagger.client.model.WalletsViewModel;
import rx.Observable;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.utils.Constants;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

public class ProgramsManager
{
	private InvestorApi investorApi;

	private ManagerApi managerApi;

	private ProgramApi programApi;

	public ProgramsManager(InvestorApi investorApi, ManagerApi managerApi, ProgramApi programApi) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;
		this.programApi = programApi;
	}

	public Observable<InvestmentProgramsViewModel> getProgramsList(InvestmentProgramsFilter filter) {
		return Constants.IS_INVESTOR
				? investorApi.apiInvestorInvestmentProgramsPost(AuthManager.token.getValue(), filter)
				: managerApi.apiManagerInvestmentProgramsPost(AuthManager.token.getValue(), filter);
	}

	public Observable<Void> setProgramFavorite(UUID programId, boolean isFavorite) {
		return isFavorite ? programFavoritesAdd(programId) : programFavoritesRemove(programId);
	}

	private Observable<Void> programFavoritesAdd(UUID programId) {
		return Constants.IS_INVESTOR
				? investorApi.apiInvestorInvestmentProgramsFavoritesAddPost(programId, AuthManager.token.getValue())
				: managerApi.apiManagerInvestmentProgramsFavoritesAddPost(programId, AuthManager.token.getValue());
	}

	private Observable<Void> programFavoritesRemove(UUID programId) {
		return Constants.IS_INVESTOR
				? investorApi.apiInvestorInvestmentProgramsFavoritesRemovePost(programId, AuthManager.token.getValue())
				: managerApi.apiManagerInvestmentProgramsFavoritesRemovePost(programId, AuthManager.token.getValue());
	}

	public Observable<InvestmentProgramViewModel> getInvestmentProgramDetails(UUID programId) {
		return Constants.IS_INVESTOR
				? investorApi.apiInvestorInvestmentProgramGet(programId, AuthManager.token.getValue())
				: managerApi.apiManagerInvestmentProgramGet(programId, AuthManager.token.getValue());
	}

	public Observable<TradesViewModel> getProgramTrades(TradesFilter filter) {
		return Constants.IS_INVESTOR
				? investorApi.apiInvestorInvestmentProgramTradesPost(filter)
				: managerApi.apiManagerInvestmentProgramTradesPost(filter);
	}

	public Observable<TradesChartViewModel> getEquityChart(UUID programId, String timeFrame) {
		return Constants.IS_INVESTOR
				? investorApi.apiInvestorInvestmentProgramEquityChartGet(programId, timeFrame)
				: managerApi.apiManagerInvestmentProgramEquityChartGet(programId, timeFrame);
	}

	public Observable<InvestmentProgramBuyToken> getBuyTokensModel(UUID programId) {
		return investorApi.apiInvestorInvestmentProgramBuyTokensGet(programId, AuthManager.token.getValue());
	}

	public Observable<WalletsViewModel> invest(ProgramRequest investRequest) {
		Invest model = new Invest();
		model.setInvestmentProgramId(investRequest.programId);
		model.setAmount(investRequest.amount);
		return investorApi.apiInvestorInvestmentProgramsInvestPost(AuthManager.token.getValue(), model);
	}

	public Observable<Void> withdraw(ProgramRequest withdrawalRequest) {
		Invest model = new Invest();
		model.setInvestmentProgramId(withdrawalRequest.programId);
		model.setAmount(withdrawalRequest.amount);
		return investorApi.apiInvestorInvestmentProgramsWithdrawPost(AuthManager.token.getValue(), model);
	}

	public Observable<Void> cancelRequest(UUID requestId) {
		return investorApi.apiInvestorInvestmentProgramsCancelInvestmentRequestPost(requestId, AuthManager.token.getValue());
	}

	public Observable<InvestmentProgramRequests> getInvestmentProgramRequests(InvestmentProgramRequestsFilter filter) {
		return investorApi.apiInvestorInvestmentProgramRequestsPost(AuthManager.token.getValue(), filter);
	}

	public Observable<BrokersViewModel> getDataToCreateProgram() {
		return managerApi.apiManagerBrokersPost(new BrokersFilter());
	}

	public Observable<UUID> sendCreateProgramRequest(NewInvestmentRequest request) {
		return managerApi.apiManagerAccountNewInvestmentRequestPost(AuthManager.token.getValue(), request);
	}
}