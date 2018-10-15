package vision.genesis.clientapp.managers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.swagger.client.api.WalletApi;
import io.swagger.client.model.CreateWithdrawalRequestModel;
import io.swagger.client.model.WalletSummary;
import io.swagger.client.model.WalletTransactionsViewModel;
import io.swagger.client.model.WalletsInfo;
import io.swagger.client.model.WithdrawalSummary;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.WithdrawalRequest;
import vision.genesis.clientapp.model.events.OnUnauthorizedResponseGetEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class WalletManager
{
	private WalletApi walletApi;

	private BehaviorSubject<Double> balanceBehaviorSubject = BehaviorSubject.create();

	//	public WalletManager(InvestorApi investorApi, ManagerApi managerApi) {
	public WalletManager(WalletApi walletApi) {
		this.walletApi = walletApi;

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

	public Observable<WalletsInfo> getWalletAddress() {
		return walletApi.v10WalletAddressesGet(AuthManager.token.getValue());
	}

	public Observable<WithdrawalSummary> getWalletWithdrawInfo() {
		return walletApi.v10WalletWithdrawInfoGet(AuthManager.token.getValue());
	}

	public Observable<Void> withdraw(WithdrawalRequest request) {
		CreateWithdrawalRequestModel requestModel = new CreateWithdrawalRequestModel();
		requestModel.setAmount(request.getAmount());
		requestModel.setCurrency(CreateWithdrawalRequestModel.CurrencyEnum.fromValue(request.getCurrency()));
		requestModel.setAddress(request.getAddress());
		return walletApi.v10WalletWithdrawRequestNewPost(AuthManager.token.getValue(), requestModel);
	}

	public Observable<WalletSummary> getWallet(CurrencyEnum currency) {
		return walletApi.v10WalletByCurrencyGet(currency.getValue(), AuthManager.token.getValue());
	}

	public Observable<WalletTransactionsViewModel> getTransactions(DateRange dateRange, Integer skip, Integer take) {
		return walletApi.v10WalletTransactionsGet(AuthManager.token.getValue(), null,
				dateRange.getFrom(), dateRange.getTo(),
				null, null,
				skip, take);
	}

	@Subscribe
	public void onEventMainThread(OnUnauthorizedResponseGetEvent event) {
		balanceBehaviorSubject = BehaviorSubject.create();
	}
}
