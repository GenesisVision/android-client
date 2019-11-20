package vision.genesis.clientapp.feature.main.fund.create.deposit;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletMultiSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.events.OnCreateFundCreateButtonClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

@InjectViewState
public class CreateFundDepositPresenter extends MvpPresenter<CreateFundDepositView> implements
		SelectWalletBottomSheetFragment.OnWalletSelectedListener
{
	@Inject
	public WalletManager walletManager;

	private Subscription walletsSubscription;

	private NewFundRequest request;

	private WalletData selectedWallet;

	private Double availableInWallet;

	private double amount = 0;

	private double amountBase = 0;

	private double minDepositGvtAmount = 50;

	private double minDepositSelectedCurrencyAmount = 50;

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

		super.onDestroy();
	}

	void setRequest(NewFundRequest request) {
		this.request = request;
	}

	void setMinDepositAmount(Double minDepositAmount) {
		this.minDepositGvtAmount = minDepositAmount;
	}

	void onAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			amount = 0.0;
		}

		if (selectedWallet != null) {
			if (amount > availableInWallet) {
				getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(availableInWallet));
				return;
			}

			amountBase = amount / selectedWallet.getRateToGVT();

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

	void onCreateFundClicked() {
		if (selectedWallet != null) {
			request.setDepositAmount(amount);
			request.setDepositWalletId(selectedWallet.getId());
			EventBus.getDefault().post(new OnCreateFundCreateButtonClickedEvent());
		}
	}

	private String getAmountBaseString() {
		return String.format(Locale.getDefault(), "≈ %s",
				StringFormatUtil.getGvtValueString(amountBase));
	}

	private void subscribeToWallets() {
		if (walletManager != null) {
			if (walletsSubscription != null) {
				walletsSubscription.unsubscribe();
			}
			walletsSubscription = walletManager.getWallets(CurrencyEnum.GVT.getValue(), true)
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

	@Override
	public void onWalletSelected(WalletData wallet) {
		this.selectedWallet = wallet;
		availableInWallet = selectedWallet.getAvailable();
		minDepositSelectedCurrencyAmount = minDepositGvtAmount * selectedWallet.getRateToGVT();
		getViewState().setWallet(selectedWallet);
		getViewState().setMinDepositWalletCurrencyAmount(minDepositSelectedCurrencyAmount);
		getViewState().setAmount("");
	}
}
