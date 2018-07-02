package vision.genesis.clientapp.managers;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.model.InvestorDashboard;
import rx.Observable;

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

	public Observable<InvestorDashboard> getInvestments(String sorting) {
		return investorApi.apiInvestorDashboardGet(AuthManager.token.getValue(), sorting, 10);
	}
}