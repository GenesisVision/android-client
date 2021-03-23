package vision.genesis.clientapp.feature.main.trading_account.create.deposit;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.swagger.client.model.NewTradingAccountRequest;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.events.OnCreateAccountCreateButtonClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

@InjectViewState
public class CreateAccountDepositPresenter extends MvpPresenter<CreateAccountDepositView> implements
		SelectWalletBottomSheetFragment.OnWalletSelectedListener
{
	@Inject
	public WalletManager walletManager;

	private Subscription walletsSubscription;

	private NewTradingAccountRequest request;

	private WalletData selectedWallet;

	private Double availableInWallet;

	private double amount = 0;

	private Map<String, Double> minDepositInfo;

	private String minDepositCurrency;

	private double minDepositSelectedCurrencyAmount = -1;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (walletsSubscription != null) {
			walletsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setRequest(NewTradingAccountRequest request) {
		this.request = request;
	}

	void setMinDeposit(Map<String, Double> minDepositInfo, String minDepositCurrency) {
		this.minDepositInfo = minDepositInfo;
		this.minDepositCurrency = minDepositCurrency;

		subscribeToWallets();
	}

	void onAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			amount = 0.0;
		}

		if (selectedWallet != null && minDepositCurrency != null) {
			if (amount > availableInWallet) {
				getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(availableInWallet));
				return;
			}

			getViewState().setCreateButtonEnabled(amount >= minDepositSelectedCurrencyAmount && amount <= availableInWallet);
		}
	}

	void onMinClicked() {
		if (selectedWallet != null) {
			double minAmount = selectedWallet.getAvailable() > minDepositSelectedCurrencyAmount
					? minDepositSelectedCurrencyAmount
					: selectedWallet.getAvailable();
			getViewState().setAmount(StringFormatUtil.formatMinAmountWithoutGrouping(minAmount));
		}
	}

	void onMaxClicked() {
		if (selectedWallet != null) {
			getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(availableInWallet));
		}
	}

	void onCreateAccountClicked() {
		if (selectedWallet != null) {
			request.setDepositAmount(amount);
			request.setDepositWalletId(selectedWallet.getId());
			EventBus.getDefault().post(new OnCreateAccountCreateButtonClickedEvent());
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
