package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.CopytradingApi;
import io.swagger.client.api.DashboardApi;
import io.swagger.client.api.InvestmentsApi;
import io.swagger.client.model.DashboardInvestingDetails;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.DashboardTradingDetails;
import io.swagger.client.model.ItemsViewModelAssetInvestmentRequest;
import io.swagger.client.model.ItemsViewModelDashboardTradingAsset;
import io.swagger.client.model.ItemsViewModelFundInvestingDetailsList;
import io.swagger.client.model.ItemsViewModelProgramInvestingDetailsList;
import rx.Observable;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

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

	public Observable<DashboardSummary> getSummary(String currency) {
		return dashboardApi.getSummary(AuthManager.token.getValue(), currency);
	}

	public Observable<DashboardInvestingDetails> getInvestingDetails(String currency, int eventsTake) {
		return dashboardApi.getInvestingDetails(AuthManager.token.getValue(), currency, eventsTake);
	}

	public Observable<DashboardTradingDetails> getTradingDetails(String currency, int eventsTake) {
		return dashboardApi.getTradingDetails(AuthManager.token.getValue(), currency, eventsTake);
	}

	public Observable<ItemsViewModelAssetInvestmentRequest> getRequests(int skip, int take) {
		return investmentsApi.getRequests(skip, take, AuthManager.token.getValue());
	}

	public Observable<ItemsViewModelAssetInvestmentRequest> getRequestsByAsset(UUID assetId) {
		return investmentsApi.getRequestsByProgram(assetId, 0, 100, AuthManager.token.getValue());
	}

	public Observable<Void> cancelRequest(UUID requestId) {
		return investmentsApi.cancelRequest(requestId, AuthManager.token.getValue());
	}

	public Observable<ItemsViewModelDashboardTradingAsset> getPrivate(DateRange dateRange, String baseCurrency, int skip, int take) {
		return dashboardApi.getPrivateTradingAssets(AuthManager.token.getValue(),
				dateRange != null ? dateRange.getFrom() : null, dateRange != null ? dateRange.getTo() : null,
				10, baseCurrency, null,
				skip, take);
	}

	public Observable<ItemsViewModelDashboardTradingAsset> getPublic(DateRange dateRange, String baseCurrency, int skip, int take) {
		return dashboardApi.getPublicTradingAssets(AuthManager.token.getValue(),
				dateRange != null ? dateRange.getFrom() : null, dateRange != null ? dateRange.getTo() : null,
				10, baseCurrency, null,
				skip, take);
	}

	public Observable<ItemsViewModelProgramInvestingDetailsList> getPrograms(ProgramsFilter filter) {
		return dashboardApi.getInvestingPrograms(AuthManager.token.getValue(),
				filter.getSorting() != null ? filter.getSorting().getValue() : null,
				filter.getCurrency() != null ? filter.getCurrency().getValue() : null,
				filter.getStatus(),
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(),
				filter.getFacetId() == null ? null : filter.getFacetId().toString(),
				filter.getMask(), filter.getManagerId(), false,
				filter.getSkip(), filter.getTake());
	}

	public Observable<ItemsViewModelFundInvestingDetailsList> getFunds(ProgramsFilter filter) {
		return dashboardApi.getInvestingFunds(AuthManager.token.getValue(),
				filter.getSorting() != null ? filter.getSorting().getValue() : null,
				filter.getCurrency() != null ? filter.getCurrency().getValue() : null,
				filter.getStatus(),
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(),
				filter.getFacetId() == null ? null : filter.getFacetId().toString(),
				filter.getMask(), filter.getManagerId(), false,
				filter.getSkip(), filter.getTake());
	}
}