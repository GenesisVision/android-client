package vision.genesis.clientapp.feature.main.wallet.transfer_wallet;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.InternalTransferRequest;
import io.swagger.client.model.TransferRequestType;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletMultiSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.WalletModel;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/02/2019.
 */

@InjectViewState
public class TransferWalletPresenter extends MvpPresenter<TransferWalletView> implements SelectWalletBottomSheetFragment.OnWalletSelectedListener
{
	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	@Inject
	public RateManager rateManager;

	private Subscription walletsSubscription;

	private WalletData walletInfo;

	private Double availableInWallet;

	private Double amount = 0.0;

	private Double finalAmount = 0.0;

	private WalletModel model;

	private List<WalletData> wallets;

	private WalletData selectedWalletTo;

	private Double rate;

	private Subscription transferSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);

		subscribeToWallets();
	}

	@Override
	public void onDestroy() {
		if (walletsSubscription != null) {
			walletsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	public void setModel(WalletModel model) {
		this.model = model;
		subscribeToWallets();
	}

	void onMaxClicked() {
		if (availableInWallet != null) {
			getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableInWallet, walletInfo.getCurrency().getValue()));
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

		if (availableInWallet != null && walletInfo != null) {
			if (amount > availableInWallet) {
				getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableInWallet, walletInfo.getCurrency().getValue()));
				return;
			}

			updateRate();
			updateFinalAmount();

			getViewState().setConfirmButtonEnabled(finalAmount > 0 && amount <= availableInWallet);
		}
	}

	private void updateFinalAmount() {
		if (walletInfo != null && rate != null) {
			finalAmount = amount * rate;

			getViewState().setFinalAmount(getFinalAmountString());
		}
	}

	private String getFinalAmountString() {
		return String.format(Locale.getDefault(), "%s%s",
				StringFormatUtil.getApproxSymbolIfNeeded(finalAmount),
				StringFormatUtil.getValueString(finalAmount, selectedWalletTo.getCurrency().getValue()));
	}

	private String getRateString(Double rate, String wallet1Currency, String wallet2Currency) {
		return String.format(Locale.getDefault(), "1 %s = %s", wallet1Currency, StringFormatUtil.getValueString(rate, wallet2Currency));
	}

	private void subscribeToWallets() {
		if (walletManager != null && model != null) {
			if (walletsSubscription != null) {
				walletsSubscription.unsubscribe();
			}
			walletsSubscription = walletManager.getWallets(model.getCurrency(), false)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWalletUpdateSuccess,
							this::handleWalletUpdateError);
		}
	}

	private void handleWalletUpdateSuccess(WalletMultiSummary response) {
		getViewState().showProgress(false);

		wallets = response.getWallets();

		for (WalletData info : response.getWallets()) {
			if (info.getCurrency().getValue().equals(model.getCurrency())) {
				availableInWallet = info.getAvailable();
				this.walletInfo = info;
				getViewState().setWalletInfo(info);
				break;
			}
		}
		setWalletsTo();
	}

	private void handleWalletUpdateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void setWalletsTo() {
		List<WalletData> walletsTo = new ArrayList<>();

		for (WalletData wallet : wallets) {
			if (wallet.getCurrency().getValue().equals(model.getCurrency())) {
				continue;
			}
			walletsTo.add(wallet);
		}
		getViewState().setWalletsTo(walletsTo);
		onWalletSelected(walletsTo.get(0));
	}

	private void updateRate() {
		if (walletInfo != null && selectedWalletTo != null && rateManager != null) {
			getViewState().showRateProgress(true);
			rateManager.getRate(walletInfo.getCurrency().getValue(), selectedWalletTo.getCurrency().getValue())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetRateSuccess,
							this::handleGetRateError);
		}
	}

	private void handleGetRateSuccess(Double rate) {
		getViewState().showRateProgress(false);
		this.rate = rate;
		getViewState().setRate(getRateString(rate, walletInfo.getCurrency().getValue(), selectedWalletTo.getCurrency().getValue()));
		updateFinalAmount();
	}

	private void handleGetRateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void sendTransferRequest() {
		InternalTransferRequest request = new InternalTransferRequest();
		request.setAmount(amount);
		request.setSourceId(walletInfo.getId());
		request.setDestinationId(selectedWalletTo.getId());
		request.setSourceType(TransferRequestType.WALLET);
		request.setDestinationType(TransferRequestType.WALLET);
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
		this.selectedWalletTo = wallet;
		getViewState().setWalletTo(selectedWalletTo);
		updateRate();
	}
}
