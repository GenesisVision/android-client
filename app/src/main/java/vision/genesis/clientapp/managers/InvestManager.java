package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentProgramBuyToken;
import io.swagger.client.model.InvestmentProgramRequests;
import io.swagger.client.model.InvestmentProgramRequestsFilter;
import io.swagger.client.model.InvestmentProgramViewModel;
import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestorDashboard;
import io.swagger.client.model.TradesChartViewModel;
import io.swagger.client.model.TradesFilter;
import io.swagger.client.model.TradesViewModel;
import io.swagger.client.model.WalletsViewModel;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.ProgramRequest;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

public class InvestManager
{
	public BehaviorSubject<InvestmentProgramsFilter> filterSubject = BehaviorSubject.create();

	private InvestorApi investorApi;

	private ManagerApi managerApi;

	public InvestManager(InvestorApi investorApi, ManagerApi managerApi) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;

		filterSubject.onNext(new InvestmentProgramsFilter());
	}

	public InvestmentProgramsFilter getFilter() {
		return filterSubject.getValue();
	}

	public void setFilter(InvestmentProgramsFilter filter) {
		filterSubject.onNext(filter);
	}

	public Observable<InvestmentProgramsViewModel> getProgramsList(InvestmentProgramsFilter filter) {
		return investorApi.apiInvestorInvestmentProgramsPost(AuthManager.token.getValue(), filter);
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

	public Observable<InvestorDashboard> getInvestments(String sorting) {
		return investorApi.apiInvestorDashboardGet(AuthManager.token.getValue(), sorting, 10);
	}

	public Observable<InvestmentProgramViewModel> getInvestmentProgramDetails(UUID programId) {
		return investorApi.apiInvestorInvestmentProgramGet(programId, AuthManager.token.getValue());
	}

	public Observable<InvestmentProgramRequests> getInvestmentProgramRequests(InvestmentProgramRequestsFilter filter) {
		return investorApi.apiInvestorInvestmentProgramRequestsPost(AuthManager.token.getValue(), filter);
	}

	public Observable<TradesViewModel> getProgramTrades(TradesFilter filter) {
		return investorApi.apiInvestorInvestmentProgramTradesPost(filter);
	}

	public Observable<TradesChartViewModel> getEquityChart(UUID programId, String timeFrame) {
		return investorApi.apiInvestorInvestmentProgramEquityChartGet(programId, timeFrame);
	}

	public Observable<Void> cancelRequest(UUID requestId) {
		return investorApi.apiInvestorInvestmentProgramsCancelInvestmentRequestPost(requestId, AuthManager.token.getValue());
	}

	public Observable<Void> setProgramFavorite(UUID programId, boolean isFavorite) {
		return getSetProgramFavoriteApiObservable(programId, isFavorite);
	}

	private Observable<Void> getSetProgramFavoriteApiObservable(UUID programId, boolean isFavorite) {
		return isFavorite
				? investorApi.apiInvestorInvestmentProgramsFavoritesAddPost(programId, AuthManager.token.getValue())
				: investorApi.apiInvestorInvestmentProgramsFavoritesRemovePost(programId, AuthManager.token.getValue());
	}

	public Observable<InvestmentProgramBuyToken> getBuyTokensModel(UUID programId) {
		return investorApi.apiInvestorInvestmentProgramBuyTokensGet(programId, AuthManager.token.getValue());
	}
}