package vision.genesis.clientapp.managers;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.ProfileShortViewModel;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.BuildConfig;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class ProfileManager
{
	private InvestorApi investorApi;

	private ManagerApi managerApi;

	private BehaviorSubject<Double> balanceBehaviorSubject = BehaviorSubject.create();

	public ProfileManager(InvestorApi investorApi, ManagerApi managerApi) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;
	}

	public BehaviorSubject<Double> getBalance() {
		updateBalance();
		return balanceBehaviorSubject;
	}

	private void updateBalance() {
		getProfileShortApiObservable()
				.observeOn(Schedulers.io())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetProfileShortResponse,
						this::handleGetProfileShortError);
	}

	private void handleGetProfileShortResponse(ProfileShortViewModel model) {
		balanceBehaviorSubject.onNext(model.getBalance());
	}

	private void handleGetProfileShortError(Throwable error) {
//		balanceBehaviorSubject.onError(error);
	}

	private Observable<ProfileShortViewModel> getProfileShortApiObservable() {
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorProfileGet(AuthManager.token.getValue())
				: managerApi.apiManagerProfileGet(AuthManager.token.getValue());
	}
}
