package vision.genesis.clientapp.managers;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.model.DashboardProgramsList;
import rx.Observable;
import vision.genesis.clientapp.model.DateRange;

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

	public Observable<DashboardProgramsList> getPrograms(String sorting, DateRange dateRange, Integer skip, Integer take) {
		return investorApi.v10InvestorDashboardProgramListGet(AuthManager.token.getValue(), sorting, dateRange.getFrom(), dateRange.getTo(), skip, take);
	}
}