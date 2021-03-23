package vision.genesis.clientapp.feature.main.program.create.deposit;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.swagger.client.model.AssetType;
import io.swagger.client.model.Currency;
import io.swagger.client.model.InternalTransferRequest;
import io.swagger.client.model.InternalTransferRequestType;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.events.OnCreateProgramCreateButtonClickedEvent;
import vision.genesis.clientapp.model.events.OnProgramDepositConfirmEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 29/11/2019.
 */

@InjectViewState
public class CreateProgramDepositPresenter extends MvpPresenter<CreateProgramDepositView> implements
		SelectWalletBottomSheetFragment.OnWalletSelectedListener
{
	@Inject
	public WalletManager walletManager;

	private Subscription walletsSubscription;

	private Subscription depositSubscription;

	private WalletData selectedWallet;

	private Double availableInWallet;

	private double amount = 0;

	private CreateProgramModel model;

	private double minDepositSelectedCurrencyAmount;

	private String minDepositCurrency;

	private Map<String, Double> minDepositInfo;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToWallets();
	}

	@Override
	public void onDestroy() {
		if (walletsSubscription != null) {
			walletsSubscription.unsubscribe();
		}
		if (depositSubscription != null) {
			depositSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setModel(CreateProgramModel model) {
		this.model = model;
		this.minDepositCurrency = model.getCurrency();
		subscribeToWallets();
	}

	void setMinDeposit(Map<String, Double> minDepositInfo, Currency accountCurrency) {
		this.minDepositInfo = minDepositInfo;
		this.minDepositCurrency = accountCurrency.getValue();
		subscribeToWallets();
	}

	void onAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			amount = 0.0;
		}

		if (selectedWallet != null && model != null && minDepositCurrency != null) {
			if (amount > availableInWallet) {
				getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(availableInWallet));
				return;
			}

			getViewState().setConfirmButtonEnabled(amount >= minDepositSelectedCurrencyAmount && amount <= availableInWallet);
		}
	}

	void onMinClicked() {
		if (selectedWallet != null) {
			double minAmount = selectedWallet.getAvailable() > minDepositSelectedCurrencyAmount
					? minDepositSelectedCurrencyAmount
					: selectedWallet.getAvailable();
			getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(minAmount));
		}
	}

	void onMaxClicked() {
		if (selectedWallet != null) {
			getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(availableInWallet));
		}
	}

	void onConfirmClicked() {
		if (selectedWallet != null) {
			if (model != null && model.getAssetId() != null) {
				depositFunds();
			}
			else if (minDepositCurrency != null) {
				EventBus.getDefault().post(new OnProgramDepositConfirmEvent(amount, selectedWallet.getId()));
			}
		}
	}

	private void subscribeToWallets() {
		if (walletManager != null && minDepositCurrency != null) {
			if (walletsSubscription != null) {
				walletsSubscription.unsubscribe();
			}
			walletsSubscription = walletManager.getWallets(minDepositCurrency, true)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWalletUpdateSuccess,
							this::handleWalletUpdateError);
		}
	}

	private void handleWalletUpdateSuccess(WalletSummary response) {
		getViewState().showProgress(false);
		List<WalletData> wallets = new ArrayList<>();
		for (WalletData wallet : response.getWallets()) {
			if (minDepositInfo.containsKey(wallet.getCurrency().getValue())) {
				wallets.add(wallet);
			}
		}
		getViewState().setWallets(wallets);
		onWalletSelected(wallets.get(0));
	}

	private void handleWalletUpdateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void depositFunds() {
		if (walletManager != null && selectedWallet != null && amount > 0) {
			getViewState().showButtonProgress(true);

			InternalTransferRequest request = new InternalTransferRequest();
			request.setAmount(amount);
			request.setSourceId(selectedWallet.getId());
			request.setSourceType(InternalTransferRequestType.WALLET);
			request.setDestinationId(model.getAssetId());
			request.setDestinationType(model.getAssetType().equals(AssetType.NONE)
					? InternalTransferRequestType.PRIVATETRADINGACCOUNT
					: InternalTransferRequestType.PUBLICTRADINGACCOUNT);

			depositSubscription = walletManager.transfer(request)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleDepositSuccess, this::handleDepositError);
		}
	}

	private void handleDepositSuccess(Void response) {
		depositSubscription.unsubscribe();
		getViewState().showButtonProgress(false);

		EventBus.getDefault().post(new OnCreateProgramCreateButtonClickedEvent());
	}

	private void handleDepositError(Throwable throwable) {
		depositSubscription.unsubscribe();
		getViewState().showButtonProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onWalletSelected(WalletData wallet) {
		this.selectedWallet = wallet;
		availableInWallet = selectedWallet.getAvailable();

		updateMinDeposit();
		getViewState().setWallet(selectedWallet);
		getViewState().setAmount("");
	}

	private void updateMinDeposit() {
		if (selectedWallet != null && minDepositInfo != null) {
			this.minDepositCurrency = selectedWallet.getCurrency().getValue();
			minDepositSelectedCurrencyAmount = minDepositInfo.get(minDepositCurrency);
			getViewState().setMinDepositWalletCurrencyAmount(minDepositSelectedCurrencyAmount, selectedWallet.getCurrency().getValue());
		}
	}
}
