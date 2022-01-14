package vision.genesis.clientapp.managers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import io.swagger.client.api.WalletApi;
import io.swagger.client.model.CreateWithdrawalRequestModel;
import io.swagger.client.model.Currency;
import io.swagger.client.model.InternalMultiTransferRequest;
import io.swagger.client.model.InternalTransferRequest;
import io.swagger.client.model.TransactionExternalType;
import io.swagger.client.model.TransactionInternalType;
import io.swagger.client.model.TransactionViewModelItemsViewModel;
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
		return walletApi.getUserWithdrawalSummary();
	}

	public Observable<Void> withdraw(WithdrawalRequest request) {
		CreateWithdrawalRequestModel requestModel = new CreateWithdrawalRequestModel();
		requestModel.setAmount(request.getAmount());
		requestModel.setCurrency(Currency.fromValue(request.getCurrency()));
		requestModel.setAddress(request.getAddress());
		requestModel.setTwoFactorCode(request.getTfaCode());
		requestModel.setBlockchain(request.getBlockchain());
		return walletApi.createWithdrawalRequest(requestModel);
	}

	public BehaviorSubject<WalletSummary> getWallets(String currency, Boolean forceUpdate) {
		if (forceUpdate) {
			walletsSubject = BehaviorSubject.create();
		}
		getWalletsSubscription = walletApi.getWalletSummary(Currency.fromValue(currency))
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

	public Observable<TransactionViewModelItemsViewModel> getTransactionsInternal(TransactionsFilter filter, TransactionInternalType type) {
		return walletApi.getTransactionsInternal(
				type,
				filter.getDateRange() == null ? null : filter.getDateRange().getFrom(),
				filter.getDateRange() == null ? null : filter.getDateRange().getTo(),
				Currency.fromValue(filter.getWalletCurrency()),
				filter.getSkip(), filter.getTake());
	}

	public Observable<TransactionViewModelItemsViewModel> getTransactionsExternal(TransactionsFilter filter, TransactionExternalType type) {
		return walletApi.getTransactionsExternal(
				type,
				filter.getDateRange() == null ? null : filter.getDateRange().getFrom(),
				filter.getDateRange() == null ? null : filter.getDateRange().getTo(),
				Currency.fromValue(filter.getWalletCurrency()),
				filter.getSkip(), filter.getTake());
	}

//	public Observable<MultiWalletExternalTransactionsViewModel> getExternalTransactions(TransactionsFilter filter) {
//		return walletApi.get(
//				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
//				null, filter.getWalletCurrency(),
//				filter.getSkip(), filter.getTake());
//	}

	@Subscribe
	public void onEventMainThread(OnUnauthorizedResponseGetEvent event) {
		balanceBehaviorSubject = BehaviorSubject.create();
	}

	public Observable<Void> transfer(InternalTransferRequest request) {
		return walletApi.transfer(request);
	}

	public Observable<Void> transferMulti(InternalMultiTransferRequest request) {
		return walletApi.transferMultiCurrency(request);
	}

//	public Observable<TransactionDetails> getTransaction(UUID transactionId) {
//		return walletApi.v10WalletTransactionByIdGet(transactionId);
//	}

	public Observable<Void> cancelWithdrawRequest(UUID transactionId) {
		return walletApi.cancelWithdrawalRequest(transactionId);
	}

	public Observable<Void> resendConfirmationEmail(UUID transactionId) {
		return walletApi.resendWithdrawalRequestEmail(transactionId);
	}

//	public Observable<Void> setUsingGvtToPayFees(boolean on) {
//		return on
//				? walletApi.switchPayFeeInGvtOn(AuthManager.token.getValue())
//				: walletApi.switchPayFeeInGvtOff(AuthManager.token.getValue());
//	}
}
