package vision.genesis.clientapp.managers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import io.swagger.client.api.WalletApi;
import io.swagger.client.model.CreateWithdrawalRequestModel;
import io.swagger.client.model.Currency;
import io.swagger.client.model.InternalTransferRequest;
import io.swagger.client.model.ItemsViewModelTransactionViewModel;
import io.swagger.client.model.WalletSummary;
import io.swagger.client.model.WithdrawalSummary;
import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.model.TransactionsFilter;
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

	private BehaviorSubject<WalletSummary> walletsSubject = BehaviorSubject.create();

	private Subscription getWalletsSubscription;

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

//	public Observable<WalletsInfo> getWalletAddress() {
//		return walletApi.v10WalletAddressesGet(AuthManager.token.getValue());
//	}

	public Observable<WithdrawalSummary> getWalletWithdrawInfo() {
		return walletApi.getUserWithdrawalSummary(AuthManager.token.getValue());
	}

	public Observable<Void> withdraw(WithdrawalRequest request) {
		CreateWithdrawalRequestModel requestModel = new CreateWithdrawalRequestModel();
		requestModel.setAmount(request.getAmount());
		requestModel.setCurrency(Currency.fromValue(request.getCurrency()));
		requestModel.setAddress(request.getAddress());
		requestModel.setTwoFactorCode(request.getTfaCode());
		return walletApi.createWithdrawalRequest(AuthManager.token.getValue(), requestModel);
	}

	public BehaviorSubject<WalletSummary> getWallets(String currency, Boolean forceUpdate) {
		if (forceUpdate) {
			walletsSubject = BehaviorSubject.create();
		}
		getWalletsSubscription = walletApi.getWalletSummary(currency, AuthManager.token.getValue())
				.observeOn(Schedulers.io())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetWalletsSuccess,
						this::handleGetWalletsError);

		return walletsSubject;
	}

	private void handleGetWalletsSuccess(WalletSummary response) {
		getWalletsSubscription.unsubscribe();
		walletsSubject.onNext(response);
	}

	private void handleGetWalletsError(Throwable error) {
		getWalletsSubscription.unsubscribe();
	}

	public Observable<ItemsViewModelTransactionViewModel> getTransactionsInternal(TransactionsFilter filter) {
		return walletApi.getTransactionsInternal(AuthManager.token.getValue(),
				filter.getType() == null ? null : filter.getType().getValue(),
				filter.getDateRange() == null ? null : filter.getDateRange().getFrom(),
				filter.getDateRange() == null ? null : filter.getDateRange().getTo(),
				filter.getWalletCurrency(),
				filter.getSkip(), filter.getTake());
	}

	public Observable<ItemsViewModelTransactionViewModel> getTransactionsExternal(TransactionsFilter filter) {
		return walletApi.getTransactionsExternal(AuthManager.token.getValue(),
				filter.getType() == null ? null : filter.getType().getValue(),
				filter.getDateRange() == null ? null : filter.getDateRange().getFrom(),
				filter.getDateRange() == null ? null : filter.getDateRange().getTo(),
				filter.getWalletCurrency(),
				filter.getSkip(), filter.getTake());
	}

//	public Observable<MultiWalletExternalTransactionsViewModel> getExternalTransactions(TransactionsFilter filter) {
//		return walletApi.get(AuthManager.token.getValue(),
//				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
//				null, filter.getWalletCurrency(),
//				filter.getSkip(), filter.getTake());
//	}

	@Subscribe
	public void onEventMainThread(OnUnauthorizedResponseGetEvent event) {
		balanceBehaviorSubject = BehaviorSubject.create();
	}

	public Observable<Void> transfer(InternalTransferRequest request) {
		return walletApi.transfer(AuthManager.token.getValue(), request);
	}

//	public Observable<TransactionDetails> getTransaction(UUID transactionId) {
//		return walletApi.v10WalletTransactionByIdGet(transactionId, AuthManager.token.getValue());
//	}

	public Observable<Void> cancelWithdrawRequest(UUID transactionId) {
		return walletApi.cancelWithdrawalRequest(transactionId, AuthManager.token.getValue());
	}

	public Observable<Void> resendConfirmationEmail(UUID transactionId) {
		return walletApi.resendWithdrawalRequestEmail(transactionId, AuthManager.token.getValue());
	}
}
