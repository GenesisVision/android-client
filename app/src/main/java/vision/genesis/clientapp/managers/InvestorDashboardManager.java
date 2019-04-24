package vision.genesis.clientapp.managers;

import java.util.List;
import java.util.UUID;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.model.DashboardPortfolioEvent;
import io.swagger.client.model.DashboardPortfolioEvents;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.FundsList;
import io.swagger.client.model.ProgramRequests;
import io.swagger.client.model.ProgramsList;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/07/2018.
 */

public class InvestorDashboardManager
{
	private InvestorApi investorApi;

	private BehaviorSubject<List<DashboardPortfolioEvent>> portfolioEventsSubject = BehaviorSubject.create();

	public InvestorDashboardManager(InvestorApi investorApi) {
		this.investorApi = investorApi;
	}

	public Observable<DashboardSummary> getDashboard(DateRange dateRange, String baseCurrency) {
		return investorApi.v10InvestorGet(AuthManager.token.getValue(), baseCurrency, dateRange.getFrom(), dateRange.getTo(),
				null, null, 10, 0, 100);
	}

	public Observable<ProgramsList> getPrograms(String sorting, DateRange dateRange, Integer skip, Integer take) {
		return investorApi.v10InvestorProgramsGet(AuthManager.token.getValue(), sorting, dateRange.getFrom(), dateRange.getTo(), 10, null, null, skip, take);
	}

	public Observable<FundsList> getFunds(String sorting, DateRange dateRange, Integer skip, Integer take) {
		return investorApi.v10InvestorFundsGet(AuthManager.token.getValue(), sorting, dateRange.getFrom(), dateRange.getTo(), 10, null, null, skip, take);
	}

	public Observable<DashboardPortfolioEvents> getPortfolioEvents(DateRange dateRange, Integer skip, Integer take) {
		return investorApi.v10InvestorPortfolioEventsGet(AuthManager.token.getValue(), null, dateRange.getFrom(), dateRange.getTo(), null, null, skip, take);
	}

	public Observable<Void> cancelRequest(UUID requestId) {
		return investorApi.v10InvestorProgramsRequestsByIdCancelPost(requestId, AuthManager.token.getValue());
	}

	public Observable<ProgramRequests> getRequests(UUID assetId) {
		return investorApi.v10InvestorProgramsByIdRequestsBySkipByTakeGet(assetId, 0, 100, AuthManager.token.getValue());
	}
}