package vision.genesis.clientapp.feature.main.wallet.transfer_funds;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.InternalTransferRequest;
import io.swagger.client.model.InternalTransferRequestType;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.TransferFundsModel;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/02/2019.
 */

@InjectViewState
public class TransferFundsPresenter extends MvpPresenter<TransferWalletView> implements SelectWalletBottomSheetFragment.OnWalletSelectedListener
{
	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	@Inject
	public RateManager rateManager;

	private Subscription walletsSubscription;

	private Double available;

	private Double amount = 0.0;

	private Double finalAmount = 0.0;

	private TransferFundsModel model;

	private List<WalletData> wallets;

	private WalletData selectedWallet;

	private Double rate;

	private Subscription transferSubscription;

	private String firstRateCurrency;

	private String secondRateCurrency;

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

	public void setModel(TransferFundsModel model) {
		this.model = model;
		if (model.getTransferDirection().equals(TransferFundsModel.TransferDirection.WITHDRAW)) {
			available = model.getAvailable();
		}
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

		if (available != null && model != null) {
			if (amount > available) {
				getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(available));
				return;
			}

			updateFinalAmount();

			getViewState().setConfirmButtonEnabled(finalAmount > 0 && amount <= available);
		}
	}

	private void updateFinalAmount() {
		if (rate != null) {
			finalAmount = amount * rate;

			getViewState().setFinalAmount(getFinalAmountString());
		}
	}

	private String getFinalAmountString() {
		return String.format(Locale.getDefault(), "%s%s",
				model.getCurrency().equals(selectedWallet.getCurrency().getValue()) ? "" : StringFormatUtil.getApproxSymbolIfNeeded(finalAmount),
				StringFormatUtil.getValueString(finalAmount, secondRateCurrency));
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

	private void handleWalletUpdateSuccess(WalletSummary response) {
		getViewState().showProgress(false);

		wallets = response.getWallets();

//		for (WalletData info : response.getWallets()) {
//			if (info.getCurrency().getValue().equals(model.getCurrency())) {
//				available = info.getAvailable();
//				this.walletInfo = info;
//				getViewState().setWalletInfo(info);
//				break;
//			}
//		}
		setWallets();
	}

	private void handleWalletUpdateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void setWallets() {
		List<WalletData> walletsToSecond = new ArrayList<>();

		for (WalletData wallet : wallets) {
			if (model.getAssetType().equals(InternalTransferRequestType.WALLET)
					&& wallet.getCurrency().getValue().equals(model.getCurrency())) {
				continue;
			}
			walletsToSecond.add(wallet);
		}
		getViewState().setWallets(walletsToSecond);
		onWalletSelected(walletsToSecond.get(0));
	}

	private void updateRate() {
		if (model != null && selectedWallet != null && rateManager != null) {
			getViewState().showRateProgress(true);

			updateRateCurrencies();
			rateManager.getRate(firstRateCurrency, secondRateCurrency)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetRateSuccess,
							this::handleGetRateError);
		}
	}

	private void updateRateCurrencies() {
		if (model.getTransferDirection().equals(TransferFundsModel.TransferDirection.WITHDRAW)) {
			firstRateCurrency = model.getCurrency();
			secondRateCurrency = selectedWallet.getCurrency().getValue();
		}
		else {
			firstRateCurrency = selectedWallet.getCurrency().getValue();
			secondRateCurrency = model.getCurrency();
		}
	}

	private void handleGetRateSuccess(Double rate) {
		getViewState().showRateProgress(false);
		this.rate = rate;
		getViewState().setRate(getRateString(rate, firstRateCurrency, secondRateCurrency));
		updateFinalAmount();
	}

	private void handleGetRateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void sendTransferRequest() {
		InternalTransferRequest request = new InternalTransferRequest();
		request.setAmount(amount);
		request.setTransferAll(false);

		if (model.getTransferDirection().equals(TransferFundsModel.TransferDirection.WITHDRAW)) {
			request.setSourceId(model.getId());
			request.setDestinationId(selectedWallet.getId());
			request.setSourceType(model.getAssetType());
			request.setDestinationType(InternalTransferRequestType.WALLET);
		}
		else {
			request.setSourceId(selectedWallet.getId());
			request.setDestinationId(model.getId());
			request.setSourceType(InternalTransferRequestType.WALLET);
			request.setDestinationType(model.getAssetType());
		}

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
		if (model.getTransferDirection().equals(TransferFundsModel.TransferDirection.DEPOSIT)) {
			available = selectedWallet.getAvailable();
			getViewState().setAmount("");
		}
		getViewState().setWallet(selectedWallet);
		updateRate();
	}
}
