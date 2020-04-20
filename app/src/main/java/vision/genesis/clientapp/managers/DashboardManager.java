package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.CopytradingApi;
import io.swagger.client.api.DashboardApi;
import io.swagger.client.api.InvestmentsApi;
import io.swagger.client.model.AssetInvestmentRequestItemsViewModel;
import io.swagger.client.model.Currency;
import io.swagger.client.model.DashboardAssetStatus;
import io.swagger.client.model.DashboardInvestingDetails;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.DashboardTradingAssetItemsViewModel;
import io.swagger.client.model.DashboardTradingDetails;
import io.swagger.client.model.FundInvestingDetailsListItemsViewModel;
import io.swagger.client.model.FundsFilterSorting;
import io.swagger.client.model.ProgramInvestingDetailsListItemsViewModel;
import io.swagger.client.model.ProgramsFilterSorting;
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
		return dashboardApi.getDashboardSummary(Currency.fromValue(currency));
	}

	public Observable<DashboardInvestingDetails> getInvestingDetails(String currency, int eventsTake) {
		return dashboardApi.getInvestingDetails(Currency.fromValue(currency), eventsTake);
	}

	public Observable<DashboardTradingDetails> getTradingDetails(String currency, int eventsTake) {
		return dashboardApi.getTradingDetails(Currency.fromValue(currency), eventsTake);
	}

	public Observable<AssetInvestmentRequestItemsViewModel> getRequests(int skip, int take) {
		return investmentsApi.getRequests(skip, take);
	}

	public Observable<AssetInvestmentRequestItemsViewModel> getRequestsByAsset(UUID assetId) {
		return investmentsApi.getRequestsByProgram(assetId, 0, 100);
	}

	public Observable<Void> cancelRequest(UUID requestId) {
		return investmentsApi.cancelRequest(requestId);
	}

	public Observable<DashboardTradingAssetItemsViewModel> getPrivate(DateRange dateRange, String baseCurrency, int skip, int take) {
		return dashboardApi.getPrivateTradingAssets(
				dateRange != null ? dateRange.getFrom() : null, dateRange != null ? dateRange.getTo() : null,
				10, Currency.fromValue(baseCurrency), null,
				skip, take);
	}

	public Observable<DashboardTradingAssetItemsViewModel> getPublic(DateRange dateRange, String baseCurrency, int skip, int take) {
		return dashboardApi.getPublicTradingAssets(
				dateRange != null ? dateRange.getFrom() : null, dateRange != null ? dateRange.getTo() : null,
				10, Currency.fromValue(baseCurrency), null,
				skip, take);
	}

	public Observable<ProgramInvestingDetailsListItemsViewModel> getPrograms(ProgramsFilter filter) {
		return dashboardApi.getInvestingPrograms(
				filter.getSorting() != null ? ProgramsFilterSorting.fromValue(filter.getSorting().getValue()) : null,
				filter.getShowIn() != null ? Currency.fromValue(filter.getShowIn().getValue()) : null,
				DashboardAssetStatus.fromValue(filter.getStatus()),
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(),
				filter.getFacetId() == null ? null : filter.getFacetId().toString(),
				filter.getMask(), filter.getManagerId(), false,
				filter.getSkip(), filter.getTake());
	}

	public Observable<FundInvestingDetailsListItemsViewModel> getFunds(ProgramsFilter filter) {
		return dashboardApi.getInvestingFunds(
				filter.getSorting() != null ? FundsFilterSorting.fromValue(filter.getSorting().getValue()) : null,
				filter.getShowIn() != null ? Currency.fromValue(filter.getShowIn().getValue()) : null,
				DashboardAssetStatus.fromValue(filter.getStatus()),
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(),
				filter.getFacetId() == null ? null : filter.getFacetId().toString(),
				filter.getMask(), filter.getManagerId(), false,
				filter.getSkip(), filter.getTake());
	}
}