package vision.genesis.clientapp.managers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.ProfileFullViewModel;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.model.events.OnUnauthorizedResponseGetEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class ProfileManager
{
	private InvestorApi investorApi;

	private ManagerApi managerApi;

	private BehaviorSubject<ProfileFullViewModel> profileBehaviorSubject = BehaviorSubject.create();

	private Subscription getProfileSubscription;

	public ProfileManager(InvestorApi investorApi, ManagerApi managerApi) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;

		EventBus.getDefault().register(this);
	}

	public BehaviorSubject<ProfileFullViewModel> getProfileFull() {
		getProfileSubscription = getProfileFullApiObservable()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);
		return profileBehaviorSubject;
	}

	private void handleGetProfileSuccess(ProfileFullViewModel model) {
		getProfileSubscription.unsubscribe();
		profileBehaviorSubject.onNext(model);
	}

	private void handleGetProfileError(Throwable error) {
		getProfileSubscription.unsubscribe();
	}

	private Observable<ProfileFullViewModel> getProfileFullApiObservable() {
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorProfileFullGet(AuthManager.token.getValue())
				: managerApi.apiManagerProfileFullGet(AuthManager.token.getValue());
	}

	@Subscribe
	public void onEventMainThread(OnUnauthorizedResponseGetEvent event) {
		profileBehaviorSubject = BehaviorSubject.create();
	}
}
