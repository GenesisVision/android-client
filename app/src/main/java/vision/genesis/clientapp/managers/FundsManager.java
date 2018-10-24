package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.FundsApi;
import io.swagger.client.api.InvestorApi;
import io.swagger.client.model.DashboardPortfolioEvents;
import io.swagger.client.model.FundBalanceChart;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.FundProfitChart;
import io.swagger.client.model.FundsList;
import io.swagger.client.model.ProgramInvestInfo;
import io.swagger.client.model.ProgramWithdrawInfo;
import rx.Observable;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

public class FundsManager
{
	private InvestorApi investorApi;

//	private ManagerApi managerApi;

	private FundsApi fundsApi;

	//	public FundsManager(InvestorApi investorApi, ManagerApi managerApi, ProgramApi programsApi) {
	public FundsManager(InvestorApi investorApi, FundsApi fundsApi) {
		this.investorApi = investorApi;
//		this.managerApi = managerApi;
		this.fundsApi = fundsApi;
	}

	public Observable<FundsList> getFundsList(ProgramsFilter filter) {
		return fundsApi.v10FundsGet(AuthManager.token.getValue(),
				filter.getSorting().getValue(), filter.getCurrency() != null ? filter.getCurrency().getValue() : null,
				filter.getStatisticDateFrom(), filter.getStatisticDateTo(), 10,
				filter.getMask(), filter.getFacetId() != null ? filter.getFacetId().toString() : null, filter.getIsFavorite(),
				filter.getIds(), filter.getManagerId() != null ? filter.getManagerId().toString() : null, null, filter.getSkip(), filter.getTake());
	}

	public Observable<Void> setFundFavorite(UUID programId, boolean isFavorite) {
		return isFavorite ? fundFavoritesAdd(programId) : fundFavoritesRemove(programId);
	}

	private Observable<Void> fundFavoritesAdd(UUID fundId) {
		return fundsApi.v10FundsByIdFavoriteAddPost(fundId, AuthManager.token.getValue());
	}

	private Observable<Void> fundFavoritesRemove(UUID fundId) {
		return fundsApi.v10FundsByIdFavoriteRemovePost(fundId, AuthManager.token.getValue());
	}

	public Observable<FundDetailsFull> getFundDetails(UUID fundId, CurrencyEnum baseCurrency) {
		return fundsApi.v10FundsByIdGet(fundId.toString(), AuthManager.token.getValue(), baseCurrency.getValue());
	}

	public Observable<FundProfitChart> getProfitChart(UUID fundId, DateRange dateRange, Integer maxPointCount) {
		return fundsApi.v10FundsByIdChartsProfitGet(fundId, dateRange.getFrom(), dateRange.getTo(), maxPointCount);
	}

	public Observable<FundBalanceChart> getBalanceChart(UUID fundId, DateRange dateRange, Integer maxPointCount) {
		return fundsApi.v10FundsByIdChartsBalanceGet(fundId, dateRange.getFrom(), dateRange.getTo(), maxPointCount);
	}

	public Observable<DashboardPortfolioEvents> getFundHistory(UUID fundId, DateRange dateRange, Integer skip, Integer take) {
		return investorApi.v10InvestorPortfolioEventsGet(AuthManager.token.getValue(), fundId, dateRange.getFrom(), dateRange.getTo(), null, null, skip, take);
	}

	public Observable<ProgramInvestInfo> getInvestInfo(UUID programId, CurrencyEnum baseCurrency) {
		return investorApi.v10InvestorProgramsByIdInvestInfoByCurrencyGet(programId, baseCurrency.getValue(), AuthManager.token.getValue());
	}

	public Observable<ProgramWithdrawInfo> getWithdrawInfo(UUID programId, CurrencyEnum baseCurrency) {
		return investorApi.v10InvestorProgramsByIdWithdrawInfoByCurrencyGet(programId, baseCurrency.getValue(), AuthManager.token.getValue());
	}

	public Observable<Void> invest(ProgramRequest investRequest) {
		return investorApi.v10InvestorFundsByIdInvestByAmountPost(investRequest.getProgramId(), investRequest.getAmountDue(), AuthManager.token.getValue());
	}

	public Observable<Void> withdraw(ProgramRequest withdrawRequest) {
		return investorApi.v10InvestorFundsByIdWithdrawByPercentPost(withdrawRequest.getProgramId(), withdrawRequest.getAmountDue(), AuthManager.token.getValue());
	}

	//	public Observable<Void> withdraw(ProgramRequest withdrawalRequest) {
//		Invest model = new Invest();
//		model.setInvestmentProgramId(withdrawalRequest.programId);
//		model.setAmount(withdrawalRequest.amount);
//		return investorApi.apiInvestorInvestmentProgramsWithdrawPost(AuthManager.token.getValue(), model);
//	}
//
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