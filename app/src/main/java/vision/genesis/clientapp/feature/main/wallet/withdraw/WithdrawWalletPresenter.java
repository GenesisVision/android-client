package vision.genesis.clientapp.feature.main.wallet.withdraw;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.WalletWithdrawalInfo;
import io.swagger.client.model.WithdrawalSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.main.wallet.withdraw.confirm.ConfirmWalletWithdrawBottomSheetFragment;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.WithdrawalRequest;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ValidatorUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/27/18.
 */

@InjectViewState
public class WithdrawWalletPresenter extends MvpPresenter<WithdrawWalletView> implements SelectOptionBottomSheetFragment.OnOptionSelectedListener, ConfirmWalletWithdrawBottomSheetFragment.OnConfirmWalletWithdrawListener
{
	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	private Subscription getWithdrawalInfoSubscription;

	private List<WalletWithdrawalInfo> wallets;

	private WalletWithdrawalInfo selectedWallet;

	private Double amount = 0.0;

	private Double availableInWallet;

	private double feeAmount;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getWalletAddresses();
	}

	@Override
	public void onDestroy() {
		if (getWithdrawalInfoSubscription != null)
			getWithdrawalInfoSubscription.unsubscribe();

		super.onDestroy();
	}

	void onAvailableClicked() {
		if (availableInWallet != null) {
			getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableInWallet, CurrencyEnum.GVT.getValue()));
		}
	}

	void onMaxClicked() {
		if (availableInWallet != null && selectedWallet != null) {
			double maxAmount = availableInWallet / (1 + selectedWallet.getCommission() / 100);
			getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(maxAmount, CurrencyEnum.GVT.getValue()));
		}
	}

	void onContinueClicked(String address) {
		if (selectedWallet == null) {
			return;
		}

		Boolean isAddressValid;
		switch (selectedWallet.getCurrency()) {
			case GVT:
			case ETH:
				isAddressValid = ValidatorUtil.isEthAddressValid(address);
				break;
			default:
				isAddressValid = true;
				break;
		}
		if (!isAddressValid) {
			getViewState().showAddressNotValidError(true);
			return;
		}

		WithdrawalRequest request = new WithdrawalRequest();
		request.setAmount(amount);
		request.setCurrency(selectedWallet.getCurrency().getValue());
		request.setAddress(address);
		request.setAmountText(String.format(Locale.getDefault(), "%s GVT",
				StringFormatUtil.formatCurrencyAmount(amount, CurrencyEnum.GVT.getValue())));
		request.setEstimatedAmountText(String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.formatCurrencyAmount(amount, selectedWallet.getCurrency().getValue()),
				selectedWallet.getCurrency().getValue()));
		request.setFeeAmountText(getFeeAmountString());
		getViewState().showConfirmDialog(request);
	}

	void onAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			amount = 0.0;
		}

		if (availableInWallet != null && selectedWallet != null) {
			if (amount > availableInWallet) {
				getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableInWallet, CurrencyEnum.GVT.getValue()));
				return;
			}

			updateAmountBase();
			updateFeeAmount();

			getViewState().setContinueButtonEnabled(amount > 0
					&& amount + feeAmount <= availableInWallet);
		}
	}

	private void updateAmountBase() {
		if (selectedWallet != null)
			getViewState().setAmountBase(getAmountBaseString());
	}

	private void updateFeeAmount() {
		if (selectedWallet != null) {
			feeAmount = amount * (selectedWallet.getCommission() / 100);

			getViewState().setFeeAmount(getFeeAmountString());
		}
	}

	private String getAmountBaseString() {
		return String.format(Locale.getDefault(), "= %s %s",
				StringFormatUtil.formatCurrencyAmount(amount * selectedWallet.getRateToGvt(), selectedWallet.getCurrency().getValue()),
				selectedWallet.getCurrency().getValue());
	}

	private String getFeeAmountString() {
		return String.format(Locale.getDefault(), "%s%% (%s GVT)",
				StringFormatUtil.formatAmount(selectedWallet.getCommission(), 0, 4),
				StringFormatUtil.formatAmount(feeAmount, 0, 8));
	}

	private void getWalletAddresses() {
		getViewState().showProgress(true);
		getWithdrawalInfoSubscription = walletManager.getWalletWithdrawInfo()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetWalletAddressesSuccess,
						this::handleGetWalletAddressesError);
	}

	private void handleGetWalletAddressesSuccess(WithdrawalSummary response) {
		getWithdrawalInfoSubscription.unsubscribe();
		getViewState().showProgress(false);

		availableInWallet = response.getAvailableToWithdrawal();
		getViewState().setAvailableInWallet(availableInWallet);

		wallets = response.getWallets();

		ArrayList<String> walletsOptions = new ArrayList<>();
		for (WalletWithdrawalInfo wallet : wallets) {
			walletsOptions.add(String.format(Locale.getDefault(), "%s (%s)",
					wallet.getDescription(), wallet.getCurrency().getValue()));
		}
		getViewState().setWalletsOptions(walletsOptions);
		onOptionSelected(0, walletsOptions.get(0));
		onAmountChanged("0");
	}

	private void handleGetWalletAddressesError(Throwable throwable) {
		getWithdrawalInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onOptionSelected(Integer position, String text) {
		this.selectedWallet = wallets.get(position);
		getViewState().setWalletInfo(selectedWallet, text, position);
		updateAmountBase();
		updateFeeAmount();
	}

	@Override
	public void onWithdrawSucceeded() {
		getViewState().finishActivity();
	}
}
