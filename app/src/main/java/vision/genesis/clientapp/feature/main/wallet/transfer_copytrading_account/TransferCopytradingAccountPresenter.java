package vision.genesis.clientapp.feature.main.wallet.transfer_copytrading_account;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.InternalTransferRequest;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletMultiSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CopytradingAccountModel;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/02/2019.
 */

@InjectViewState
public class TransferCopytradingAccountPresenter extends MvpPresenter<TransferCopytradingAccountView> implements SelectWalletBottomSheetFragment.OnWalletSelectedListener
{
	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	@Inject
	public RateManager rateManager;

	private Subscription walletsSubscription;

//	private WalletData walletInfo;

	private Double available;

	private Double amount = 0.0;

	private Double finalAmount = 0.0;

	private CopytradingAccountModel account;

	private String operation;

	private List<WalletData> wallets;

	private WalletData selectedWallet;

	private Double rate;

	private Subscription transferSubscription;

	private String sourceCurrency;

	private String destinationCurrency;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);

		subscribeToWallets();
	}

	@Override
	public void onDestroy() {
		if (walletsSubscription != null)
			walletsSubscription.unsubscribe();

		super.onDestroy();
	}

	public void setData(CopytradingAccountModel model, String operation) {
		this.account = model;
		this.operation = operation;
		if (operation.equals(TransferCopytradingAccountActivity.OPERATION_DEPOSIT))
			this.destinationCurrency = account.getCurrency();
		if (operation.equals(TransferCopytradingAccountActivity.OPERATION_WITHDRAW))
			this.sourceCurrency = account.getCurrency();
		subscribeToWallets();
	}

	void onMaxClicked() {
		if (available != null) {
			getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(available));
		}
	}

	void onConfirmClicked() {
		sendTransferRequest();
	}

	void onAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			amount = 0.0;
		}

		if (available != null && account != null) {
			if (amount > available) {
				getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(available));
				return;
			}

			updateRate();
			updateFinalAmount();

			getViewState().setConfirmButtonEnabled(finalAmount > 0 && amount <= available);
		}
	}

	private void updateFinalAmount() {
		if (account != null && rate != null) {
			finalAmount = amount * rate;

			getViewState().setFinalAmount(getFinalAmountString());
		}
	}

	private String getFinalAmountString() {
		return String.format(Locale.getDefault(), "%s%s",
				sourceCurrency.equals(destinationCurrency) ? "" : StringFormatUtil.getApproxSymbolIfNeeded(finalAmount),
				StringFormatUtil.getValueString(finalAmount, destinationCurrency));
	}

	private String getRateString(Double rate, String wallet1Currency, String wallet2Currency) {
		return String.format(Locale.getDefault(), "1 %s = %s", wallet1Currency, StringFormatUtil.getValueString(rate, wallet2Currency));
	}

	private void subscribeToWallets() {
		if (walletManager != null && account != null) {
			if (walletsSubscription != null)
				walletsSubscription.unsubscribe();
			walletsSubscription = walletManager.getWallets(account.getCurrency(), false)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWalletUpdateSuccess,
							this::handleWalletUpdateError);
		}
	}

	private void handleWalletUpdateSuccess(WalletMultiSummary response) {
		getViewState().showProgress(false);

		wallets = response.getWallets();

		setWallets();
	}

	private void handleWalletUpdateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void setWallets() {
		getViewState().setWallets(wallets);
		onWalletSelected(wallets.get(0));
	}

	private void updateRate() {
		if (sourceCurrency != null && destinationCurrency != null && rateManager != null) {
			getViewState().showRateProgress(true);
			rateManager.getRate(sourceCurrency, destinationCurrency)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetRateSuccess,
							this::handleGetRateError);
		}
	}

	private void handleGetRateSuccess(Double rate) {
		getViewState().showRateProgress(false);
		this.rate = rate;
		getViewState().setRate(getRateString(rate, sourceCurrency, destinationCurrency));
		updateFinalAmount();
	}

	private void handleGetRateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void sendTransferRequest() {
		InternalTransferRequest request = new InternalTransferRequest();
		request.setAmount(amount);
		switch (operation) {
			case TransferCopytradingAccountActivity.OPERATION_DEPOSIT:
				request.setSourceId(selectedWallet.getId());
				request.setDestinationId(account.getId());
				request.setSourceType(InternalTransferRequest.SourceTypeEnum.WALLET);
				request.setDestinationType(InternalTransferRequest.DestinationTypeEnum.COPYTRADINGACCOUNT);
				break;
			case TransferCopytradingAccountActivity.OPERATION_WITHDRAW:
				request.setSourceId(account.getId());
				request.setDestinationId(selectedWallet.getId());
				request.setSourceType(InternalTransferRequest.SourceTypeEnum.COPYTRADINGACCOUNT);
				request.setDestinationType(InternalTransferRequest.DestinationTypeEnum.WALLET);
				break;
		}
		request.setTransferAll(false);

		getViewState().showButtonProgress(true);

		transferSubscription = walletManager.transfer(request)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleTransferSuccess,
						this::handleTransferError);
	}

	private void handleTransferSuccess(Void response) {
		transferSubscription.unsubscribe();

		getViewState().finishActivity();
	}

	private void handleTransferError(Throwable throwable) {
		transferSubscription.unsubscribe();
		getViewState().showButtonProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onWalletSelected(WalletData wallet) {
		this.selectedWallet = wallet;
		available = operation.equals(TransferCopytradingAccountActivity.OPERATION_DEPOSIT)
				? selectedWallet.getAvailable()
				: account.getAvailable();
		if (operation.equals(TransferCopytradingAccountActivity.OPERATION_WITHDRAW))
			this.destinationCurrency = selectedWallet.getCurrency().getValue();
		if (operation.equals(TransferCopytradingAccountActivity.OPERATION_DEPOSIT))
			this.sourceCurrency = selectedWallet.getCurrency().getValue();
		getViewState().setSelectedWallet(selectedWallet);
		getViewState().setAmount("");
		updateRate();
	}
}
