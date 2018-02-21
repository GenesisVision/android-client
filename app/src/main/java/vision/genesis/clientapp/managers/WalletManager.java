package vision.genesis.clientapp.managers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.ProfileShortViewModel;
import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.WalletTransactionsViewModel;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.model.events.OnUnauthorizedResponseGetEvent;
import vision.genesis.clientapp.utils.MockWalletTransactionUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class WalletManager
{
	public static final int MAX_DECIMAL_POINT_DIGITS = 8;

	private InvestorApi investorApi;

	private ManagerApi managerApi;

	private BehaviorSubject<Double> balanceBehaviorSubject = BehaviorSubject.create();

	public WalletManager(InvestorApi investorApi, ManagerApi managerApi) {
		this.investorApi = investorApi;
		this.managerApi = managerApi;

		EventBus.getDefault().register(this);
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

	public Observable<WalletTransactionsViewModel> getTransactions(TransactionsFilter filter) {
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorWalletTransactionsPost(AuthManager.token.getValue(), filter)
				: managerApi.apiManagerWalletTransactionsPost(AuthManager.token.getValue(), filter);
	}

	public Observable<WalletTransactionsViewModel> getMockTransactions(TransactionsFilter filter) {
		return Observable.just(MockWalletTransactionUtil.getTransactionsModel(filter)).delay(500, TimeUnit.MILLISECONDS);
	}

	@Subscribe
	public void onEventMainThread(OnUnauthorizedResponseGetEvent event) {
		balanceBehaviorSubject = BehaviorSubject.create();
	}
}
