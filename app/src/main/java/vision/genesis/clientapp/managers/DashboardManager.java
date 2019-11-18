package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.CopytradingApi;
import io.swagger.client.api.DashboardApi;
import io.swagger.client.api.InvestmentsApi;
import io.swagger.client.model.DashboardInvestingDetails;
import io.swagger.client.model.DashboardSummary;
import io.swagger.client.model.DashboardTradingDetails;
import io.swagger.client.model.ItemsViewModelAssetInvestmentRequest;
import rx.Observable;

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

	public Observable<ItemsViewModelAssetInvestmentRequest> getRequests(UUID assetId) {
		return investmentsApi.getRequestsByProgram(assetId, 0, 100, AuthManager.token.getValue());
	}

//	public Observable<ItemsViewModelCopyTradingAccountInfo> getSignalProviders(DashboardFilter filter) {
//		return copytradingApi.getSignalAssets(AuthManager.token.getValue(), null,
//				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
//				filter.getChartPointsCount(), null,
//				null, null,
//				filter.getSkip(), filter.getTake());
//	}
}