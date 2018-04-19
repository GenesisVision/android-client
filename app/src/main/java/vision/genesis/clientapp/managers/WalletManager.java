package vision.genesis.clientapp.managers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.swagger.client.api.InvestorApi;
import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.WalletAddressViewModel;
import io.swagger.client.model.WalletTransactionsViewModel;
import io.swagger.client.model.WalletsViewModel;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.model.events.OnUnauthorizedResponseGetEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class WalletManager
{
	public static final int GVT_MAX_DECIMAL_POINT_DIGITS = 4;

	public static final int TOKENS_MAX_DECIMAL_POINT_DIGITS = 2;

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
		getWalletsApiObservable()
				.observeOn(Schedulers.io())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetProfileShortResponse,
						this::handleGetProfileShortError);
	}

	private void handleGetProfileShortResponse(WalletsViewModel model) {
		balanceBehaviorSubject.onNext(model.getWallets().get(0).getAmount());
	}

	private void handleGetProfileShortError(Throwable error) {
//		balanceBehaviorSubject.onError(error);
	}

	private Observable<WalletsViewModel> getWalletsApiObservable() {
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorWalletGet(AuthManager.token.getValue())
				: managerApi.apiManagerWalletGet(AuthManager.token.getValue());
	}

	public Observable<WalletTransactionsViewModel> getTransactions(TransactionsFilter filter) {
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorWalletTransactionsPost(AuthManager.token.getValue(), filter)
				: managerApi.apiManagerWalletTransactionsPost(AuthManager.token.getValue(), filter);
	}

	public Observable<WalletAddressViewModel> getWalletAddress() {
		return BuildConfig.FLAVOR.equals("investor")
				? investorApi.apiInvestorWalletAddressGet(AuthManager.token.getValue())
				: managerApi.apiManagerWalletAddressGet(AuthManager.token.getValue());
	}

	@Subscribe
	public void onEventMainThread(OnUnauthorizedResponseGetEvent event) {
		balanceBehaviorSubject = BehaviorSubject.create();
	}
}
