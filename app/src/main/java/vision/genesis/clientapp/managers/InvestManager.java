package vision.genesis.clientapp.managers;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestmentsFilter;
import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

public class InvestManager
{
	private InvestorApi investorApi;

	private ManagerApi managerApi;

	private Subscription getInvestmentProgramsSubscription;

	private BehaviorSubject<InvestmentProgramsViewModel> investmentProgramsBehaviorSubject = BehaviorSubject.create();

	public InvestManager(InvestorApi investorApi, ManagerApi managerApi) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;
	}

	public Observable<InvestmentProgramsViewModel> getTradersList(InvestmentsFilter filter, boolean forceUpdate) {
		if (getInvestmentProgramsSubscription != null)
			getInvestmentProgramsSubscription.unsubscribe();
		if (forceUpdate)
			investmentProgramsBehaviorSubject = BehaviorSubject.create();
		getInvestmentProgramsSubscription = investorApi.apiInvestorInvestmentsPost(filter)
				.observeOn(Schedulers.io())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleInvestmentProgramsListUpdate,
						this::handleInvestmentProgramsListUpdateError);
		return investmentProgramsBehaviorSubject;
	}

	private void handleInvestmentProgramsListUpdate(InvestmentProgramsViewModel investmentPrograms) {
		investmentProgramsBehaviorSubject.onNext(investmentPrograms);
	}

	private void handleInvestmentProgramsListUpdateError(Throwable error) {
		investmentProgramsBehaviorSubject.onError(error);
	}
}
