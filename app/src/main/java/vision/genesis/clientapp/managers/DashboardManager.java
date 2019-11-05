package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.CopytradingApi;
import io.swagger.client.api.DashboardApi;
import io.swagger.client.api.InvestmentsApi;
import io.swagger.client.model.ItemsViewModelAssetInvestmentRequest;
import io.swagger.client.model.ItemsViewModelCopyTradingAccountInfo;
import rx.Observable;
import vision.genesis.clientapp.model.filter.DashboardFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/07/2018.
 */

public class DashboardManager
{
	private final InvestmentsApi investmentsApi;

	private final CopytradingApi copytradingApi;

	private DashboardApi dashboardApi;

	public DashboardManager(DashboardApi dashboardApi, InvestmentsApi investmentsApi, CopytradingApi copytradingApi) {
		this.dashboardApi = dashboardApi;
		this.investmentsApi = investmentsApi;
		this.copytradingApi = copytradingApi;
	}

	//	public Observable<DashboardSummary> getDashboard(DateRange dateRange, String baseCurrency) {
//		return dashboardApi.v10InvestorGet(AuthManager.token.getValue(), baseCurrency, dateRange.getFrom(), dateRange.getTo(),
//				null, null, 10, 0, 100);
//	}
//
//	public Observable<ProgramsList> getPrograms(DashboardFilter filter) {
//		return dashboardApi.v10InvestorProgramsGet(AuthManager.token.getValue(), filter.getSorting() != null ? filter.getSorting().getValue() : null,
//				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
//				filter.getChartPointsCount(), filter.getCurrencySecondary() != null ? filter.getCurrencySecondary().getValue() : null,
//				filter.getActionStatus(), filter.getDashboardActionStatus() != null ? filter.getDashboardActionStatus().toLowerCase() : null,
//				filter.getSkip(), filter.getTake());
//	}
//
//	public Observable<FundsList> getFunds(DashboardFilter filter) {
//		return dashboardApi.v10InvestorFundsGet(AuthManager.token.getValue(), filter.getSorting() != null ? filter.getSorting().getValue() : null,
//				null,
//				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
//				filter.getChartPointsCount(), filter.getCurrencySecondary() != null ? filter.getCurrencySecondary().getValue() : null,
//				filter.getActionStatus(), filter.getDashboardActionStatus() != null ? filter.getDashboardActionStatus().toLowerCase() : null,
//				filter.getSkip(), filter.getTake());
//	}
//
	public Observable<ItemsViewModelCopyTradingAccountInfo> getSignalProviders(DashboardFilter filter) {
		return copytradingApi.getSignalAssets(AuthManager.token.getValue(), null,
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), null,
				null, null,
				filter.getSkip(), filter.getTake());
	}
//
//	public Observable<DashboardPortfolioEvents> getPortfolioEvents(DateRange dateRange, Integer skip, Integer take) {
//		return dashboardApi.v10InvestorPortfolioEventsGet(AuthManager.token.getValue(), null, dateRange.getFrom(), dateRange.getTo(), null, null, skip, take);
//	}
//
//	public Observable<Void> cancelRequest(UUID requestId) {
//		return dashboardApi.v10InvestorProgramsRequestsByIdCancelPost(requestId, AuthManager.token.getValue());
//	}

	public Observable<ItemsViewModelAssetInvestmentRequest> getRequests(UUID assetId) {
		return investmentsApi.getRequestsByProgram(assetId, 0, 100, AuthManager.token.getValue());
	}

//	public Observable<InvestmentEventViewModels> getEvents(String eventLocation, UUID assetId, DateRange dateRange, String eventType, String assetType, Integer skip, Integer take) {
//		return dashboardApi.v10InvestorInvestmentsEventsGet(AuthManager.token.getValue(), eventLocation, assetId,
//				dateRange.getFrom(), dateRange.getTo(),
//				eventType, assetType,
//				skip, take);
//	}
}