package vision.genesis.clientapp.managers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.swagger.client.api.InvestorApi;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.events.OnUnauthorizedResponseGetEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class WalletManager
{
	private InvestorApi investorApi;

//	private ManagerApi managerApi;

	private BehaviorSubject<Double> balanceBehaviorSubject = BehaviorSubject.create();

	//	public WalletManager(InvestorApi investorApi, ManagerApi managerApi) {
	public WalletManager(InvestorApi investorApi) {
		this.investorApi = investorApi;
//		this.managerApi = managerApi;

		EventBus.getDefault().register(this);
	}

	public BehaviorSubject<Double> getBalance() {
		updateBalance();
		return balanceBehaviorSubject;
	}

	private void updateBalance() {
//		getWalletsApiObservable()
//				.observeOn(Schedulers.io())
//				.subscribeOn(Schedulers.io())
//				.subscribe(this::handleGetProfileShortResponse,
//						this::handleGetProfileShortError);
	}

//	private void handleGetProfileShortResponse(WalletsViewModel model) {
//		balanceBehaviorSubject.onNext(model.getWallets().get(0).getAmount());
//	}
//
//	private void handleGetProfileShortError(Throwable error) {
//		balanceBehaviorSubject.onError(error);
//	}
//
//	private Observable<WalletsViewModel> getWalletsApiObservable() {
//		return Constants.IS_INVESTOR
//				? investorApi.apiInvestorWalletGet(AuthManager.token.getValue())
//				: managerApi.apiManagerWalletGet(AuthManager.token.getValue());
//	}
//
//	public Observable<WalletTransactionsViewModel> getTransactions(TransactionsFilter filter) {
//		return Constants.IS_INVESTOR
//				? investorApi.apiInvestorWalletTransactionsPost(AuthManager.token.getValue(), filter)
//				: managerApi.apiManagerWalletTransactionsPost(AuthManager.token.getValue(), filter);
//	}
//
//	public Observable<WalletAddressViewModel> getWalletAddress() {
//		return Constants.IS_INVESTOR
//				? investorApi.apiInvestorWalletAddressGet(AuthManager.token.getValue())
//				: managerApi.apiManagerWalletAddressGet(AuthManager.token.getValue());
//	}

	@Subscribe
	public void onEventMainThread(OnUnauthorizedResponseGetEvent event) {
		balanceBehaviorSubject = BehaviorSubject.create();
	}
}
