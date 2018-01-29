package vision.genesis.clientapp.managers;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestmentsFilter;
import rx.Observable;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

public class InvestManager
{
	private InvestorApi investorApi;

	private ManagerApi managerApi;

	public InvestManager(InvestorApi investorApi, ManagerApi managerApi) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;
	}

	public Observable<InvestmentProgramsViewModel> getTradersList(InvestmentsFilter filter) {
		return investorApi.apiInvestorInvestmentsPost(filter);
	}
}