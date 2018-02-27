package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentProgramViewModel;
import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestorDashboard;
import io.swagger.client.model.WalletsViewModel;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

public class InvestManager
{
	public BehaviorSubject<InvestmentProgramsFilter> filterSubject = BehaviorSubject.create();

	private InvestorApi investorApi;

	private ManagerApi managerApi;

	public InvestManager(InvestorApi investorApi, ManagerApi managerApi) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;

		filterSubject.onNext(new InvestmentProgramsFilter());
	}

	public InvestmentProgramsFilter getFilter() {
		return filterSubject.getValue();
	}

	public void setFilter(InvestmentProgramsFilter filter) {
		filterSubject.onNext(filter);
	}

	public Observable<InvestmentProgramsViewModel> getTradersList(InvestmentProgramsFilter filter) {
		return investorApi.apiInvestorInvestmentProgramsPost(filter);
	}

//	public List<InvestmentProgram> parseInvestmentProgramsModel(InvestmentProgramsViewModel model) {
//		List<InvestmentProgram> investmentPrograms = new ArrayList<>();
//		for (io.swagger.client.model.InvestmentProgram program : model.getInvestments()) {
//			InvestmentProgram investmentProgram = new InvestmentProgram(program);
//			investmentProgram.chartData = MockProfitChartDataUtil.getEntries();
//			investmentPrograms.add(investmentProgram);
//		}
//		return investmentPrograms;
//	}

	public Observable<WalletsViewModel> invest(UUID programId, double amount) {
		Invest model = new Invest();
		model.setInvestmentProgramId(programId);
		model.setAmount(amount);
		return investorApi.apiInvestorInvestmentProgramsInvestPost(AuthManager.token.getValue(), model);
	}

	public Observable<InvestorDashboard> getInvestments() {
		return investorApi.apiInvestorDashboardGet(AuthManager.token.getValue());
	}

	public Observable<InvestmentProgramViewModel> getInvestmentProgramDetails(UUID programId) {
		return investorApi.apiInvestorInvestmentProgramGet(programId);
	}
}