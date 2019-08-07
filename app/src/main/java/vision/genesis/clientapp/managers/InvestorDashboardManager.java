package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.model.DashboardPortfolioEvents;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.FundsList;
import io.swagger.client.model.ProgramRequests;
import io.swagger.client.model.ProgramsList;
import io.swagger.client.model.SignalsList;
import rx.Observable;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.filter.DashboardFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/07/2018.
 */

public class InvestorDashboardManager
{
	private InvestorApi investorApi;

	public InvestorDashboardManager(InvestorApi investorApi) {
		this.investorApi = investorApi;
	}

	public Observable<DashboardSummary> getDashboard(DateRange dateRange, String baseCurrency) {
		return investorApi.v10InvestorGet(AuthManager.token.getValue(), baseCurrency, dateRange.getFrom(), dateRange.getTo(),
				null, null, 10, 0, 100);
	}

	public Observable<ProgramsList> getPrograms(DashboardFilter filter) {
		return investorApi.v10InvestorProgramsGet(AuthManager.token.getValue(), filter.getSorting().getValue(),
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), filter.getCurrencySecondary() != null ? filter.getCurrencySecondary().getValue() : null,
				filter.getActionStatus(), filter.getDashboardActionStatus() != null ? filter.getDashboardActionStatus().toLowerCase() : null,
				filter.getSkip(), filter.getTake());
	}

	public Observable<FundsList> getFunds(DashboardFilter filter) {
		return investorApi.v10InvestorFundsGet(AuthManager.token.getValue(), filter.getSorting().getValue(),
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), filter.getCurrencySecondary() != null ? filter.getCurrencySecondary().getValue() : null,
				filter.getActionStatus(), filter.getDashboardActionStatus() != null ? filter.getDashboardActionStatus().toLowerCase() : null,
				filter.getSkip(), filter.getTake());
	}

	public Observable<SignalsList> getSignalProviders(DashboardFilter filter) {
		return investorApi.v10InvestorSignalsGet(AuthManager.token.getValue(), filter.getSorting().getValue(),
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), filter.getCurrencySecondary() != null ? filter.getCurrencySecondary().getValue() : null,
				filter.getActionStatus(), filter.getDashboardActionStatus() != null ? filter.getDashboardActionStatus().toLowerCase() : null,
				filter.getSkip(), filter.getTake());
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