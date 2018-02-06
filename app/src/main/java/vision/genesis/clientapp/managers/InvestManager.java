package vision.genesis.clientapp.managers;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestmentsFilter;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.InvestmentProgram;
import vision.genesis.clientapp.utils.MockProfitChartDataUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

public class InvestManager
{
	public BehaviorSubject<InvestmentsFilter> filterSubject = BehaviorSubject.create();

	private InvestorApi investorApi;

	private ManagerApi managerApi;

	public InvestManager(InvestorApi investorApi, ManagerApi managerApi) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;

		filterSubject.onNext(new InvestmentsFilter());
	}

	public InvestmentsFilter getFilter() {
		return filterSubject.getValue();
	}

	public void setFilter(InvestmentsFilter filter) {
		filterSubject.onNext(filter);
	}

	public Observable<InvestmentProgramsViewModel> getTradersList(InvestmentsFilter filter) {
		return investorApi.apiInvestorInvestmentsPost(filter);
	}

	public List<InvestmentProgram> parseInvestmentProgramsModel(InvestmentProgramsViewModel model) {
		List<InvestmentProgram> investmentPrograms = new ArrayList<>();
		for (io.swagger.client.model.InvestmentProgram program : model.getInvestments()) {
			InvestmentProgram investmentProgram = new InvestmentProgram(program);
			investmentProgram.chartData = MockProfitChartDataUtil.getEntries();
			investmentPrograms.add(investmentProgram);
		}
		return investmentPrograms;
	}
}