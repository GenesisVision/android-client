package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.DashboardApi;
import io.swagger.client.model.DashboardPortfolioEvents;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.FundsList;
import io.swagger.client.model.InvestmentEventViewModels;
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

public class DashboardManager
{
	private DashboardApi dashboardApi;

	public DashboardManager(DashboardApi dashboardApi) {
		this.dashboardApi = dashboardApi;
	}

	public Observable<DashboardSummary> getDashboard(DateRange dateRange, String baseCurrency) {
		return dashboardApi.v10InvestorGet(AuthManager.token.getValue(), baseCurrency, dateRange.getFrom(), dateRange.getTo(),
				null, null, 10, 0, 100);
	}

	public Observable<ProgramsList> getPrograms(DashboardFilter filter) {
		return dashboardApi.v10InvestorProgramsGet(AuthManager.token.getValue(), filter.getSorting() != null ? filter.getSorting().getValue() : null,
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), filter.getCurrencySecondary() != null ? filter.getCurrencySecondary().getValue() : null,
				filter.getActionStatus(), filter.getDashboardActionStatus() != null ? filter.getDashboardActionStatus().toLowerCase() : null,
				filter.getSkip(), filter.getTake());
	}

	public Observable<FundsList> getFunds(DashboardFilter filter) {
		return dashboardApi.v10InvestorFundsGet(AuthManager.token.getValue(), filter.getSorting() != null ? filter.getSorting().getValue() : null,
				null,
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), filter.getCurrencySecondary() != null ? filter.getCurrencySecondary().getValue() : null,
				filter.getActionStatus(), filter.getDashboardActionStatus() != null ? filter.getDashboardActionStatus().toLowerCase() : null,
				filter.getSkip(), filter.getTake());
	}

	public Observable<SignalsList> getSignalProviders(DashboardFilter filter) {
		return dashboardApi.v10InvestorSignalsGet(AuthManager.token.getValue(), filter.getSorting() != null ? filter.getSorting().getValue() : null,
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), filter.getCurrencySecondary() != null ? filter.getCurrencySecondary().getValue() : null,
				filter.getActionStatus(), filter.getDashboardActionStatus() != null ? filter.getDashboardActionStatus().toLowerCase() : null,
				filter.getSkip(), filter.getTake());
	}

	public Observable<DashboardPortfolioEvents> getPortfolioEvents(DateRange dateRange, Integer skip, Integer take) {
		return dashboardApi.v10InvestorPortfolioEventsGet(AuthManager.token.getValue(), null, dateRange.getFrom(), dateRange.getTo(), null, null, skip, take);
	}

	public Observable<Void> cancelRequest(UUID requestId) {
		return dashboardApi.v10InvestorProgramsRequestsByIdCancelPost(requestId, AuthManager.token.getValue());
	}

	public Observable<ProgramRequests> getRequests(UUID assetId) {
		return dashboardApi.v10InvestorProgramsByIdRequestsBySkipByTakeGet(assetId, 0, 100, AuthManager.token.getValue());
	}

	public Observable<InvestmentEventViewModels> getEvents(String eventLocation, UUID assetId, DateRange dateRange, String eventType, String assetType, Integer skip, Integer take) {
		return dashboardApi.v10InvestorInvestmentsEventsGet(AuthManager.token.getValue(), eventLocation, assetId,
				dateRange.getFrom(), dateRange.getTo(),
				eventType, assetType,
				skip, take);
	}
}