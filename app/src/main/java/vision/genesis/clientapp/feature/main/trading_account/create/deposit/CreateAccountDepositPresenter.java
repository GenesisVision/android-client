package vision.genesis.clientapp.feature.main.trading_account.create.deposit;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.NewTradingAccountRequest;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletMultiSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.managers.RateManager;
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

	@Inject
	public RateManager rateManager;

	private Subscription walletsSubscription;

	private NewTradingAccountRequest request;

	private WalletData selectedWallet;

	private Double availableInWallet;

	private double amount = 0;

	private double amountBase = 0;

	private double minDepositAmount = 50;

	private String minDepositCurrency;

	private double minDepositSelectedCurrencyAmount = 50;

	private Double rate = 1.0;

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

	void setMinDeposit(Double minDepositAmount, String minDepositCurrency) {
		this.minDepositAmount = minDepositAmount;
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

			amountBase = amount * rate;

			getViewState().setAmountBase(getAmountBaseString());
			getViewState().setCreateButtonEnabled(amount >= minDepositSelectedCurrencyAmount && amount <= availableInWallet);
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

	void onCreateAccountClicked() {
		if (selectedWallet != null) {
			request.setDepositAmount(amount);
			request.setDepositWalletId(selectedWallet.getId());
			EventBus.getDefault().post(new OnCreateAccountCreateButtonClickedEvent());
		}
	}

	private String getAmountBaseString() {
		return String.format(Locale.getDefault(), "≈ %s",
				StringFormatUtil.getValueString(amountBase, minDepositCurrency));
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

	private void handleWalletUpdateSuccess(WalletMultiSummary response) {
		getViewState().showProgress(false);
		List<WalletData> wallets = response.getWallets();

		getViewState().setWallets(wallets);
		onWalletSelected(wallets.get(0));
	}

	private void handleWalletUpdateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void updateRate() {
		if (minDepositCurrency != null && selectedWallet != null && rateManager != null) {
			getViewState().showRateProgress(true);
			rateManager.getRate(selectedWallet.getCurrency().getValue(), minDepositCurrency)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetRateSuccess,
							this::handleGetRateError);
		}
	}

	private void handleGetRateSuccess(Double rate) {
		getViewState().showRateProgress(false);
		this.rate = rate;

		minDepositSelectedCurrencyAmount = minDepositAmount / rate;
		getViewState().setMinDepositWalletCurrencyAmount(minDepositSelectedCurrencyAmount, selectedWallet.getCurrency().getValue());
	}

	private void handleGetRateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onWalletSelected(WalletData wallet) {
		this.selectedWallet = wallet;
		availableInWallet = selectedWallet.getAvailable();
		getViewState().setWallet(selectedWallet);
		getViewState().setAmount("");

		updateRate();
	}
}
