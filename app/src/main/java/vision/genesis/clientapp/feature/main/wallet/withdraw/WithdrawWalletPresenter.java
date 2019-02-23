package vision.genesis.clientapp.feature.main.wallet.withdraw;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.WalletWithdrawalInfo;
import io.swagger.client.model.WithdrawalSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.wallet.withdraw.confirm.ConfirmWalletWithdrawBottomSheetFragment;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.WalletModel;
import vision.genesis.clientapp.model.WithdrawalRequest;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ValidatorUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/27/18.
 */

@InjectViewState
public class WithdrawWalletPresenter extends MvpPresenter<WithdrawWalletView> implements ConfirmWalletWithdrawBottomSheetFragment.OnConfirmWalletWithdrawListener
{
	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	private Subscription getWithdrawalInfoSubscription;

	private WalletWithdrawalInfo walletInfo;

	private Double availableInWallet;

	private Double amount = 0.0;

	private Double finalAmount = 0.0;

	private WalletModel model;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);

		getWalletInfo();
	}

	@Override
	public void onDestroy() {
		if (getWithdrawalInfoSubscription != null)
			getWithdrawalInfoSubscription.unsubscribe();

		super.onDestroy();
	}

	public void setModel(WalletModel model) {
		this.model = model;
		getWalletInfo();
	}

	void onMaxClicked() {
		if (availableInWallet != null) {
			getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableInWallet, walletInfo.getCurrency().getValue()));
		}
	}

	void onContinueClicked(String address) {
		if (walletInfo == null) {
			return;
		}

		Boolean isAddressValid;
		switch (walletInfo.getCurrency()) {
			case GVT:
			case ETH:
				isAddressValid = ValidatorUtil.isEthAddressValid(address);
				break;
			default:
				isAddressValid = !address.trim().isEmpty();
				break;
		}
		if (!isAddressValid) {
			getViewState().showAddressNotValidError(true);
			return;
		}

		WithdrawalRequest request = new WithdrawalRequest();
		request.setAmount(amount);
		request.setCurrency(walletInfo.getCurrency().getValue());
		request.setAddress(address);
		request.setAmountText(String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.formatCurrencyAmount(amount, walletInfo.getCurrency().getValue()),
				walletInfo.getCurrency().getValue()));
		request.setFeeAmountText(getFeeAmountString());
		request.setEstimatedAmountText(getFinalAmountString());
		getViewState().showConfirmDialog(request);
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

			updateFeeAmount();
			updateFinalAmount();

			getViewState().setContinueButtonEnabled(finalAmount > 0 && amount <= availableInWallet);
		}
	}

	private void updateFeeAmount() {
		if (walletInfo != null) {
			getViewState().setFeeAmount(getFeeAmountString());
		}
	}

	private void updateFinalAmount() {
		if (walletInfo != null) {
			finalAmount = amount - walletInfo.getCommission();
			if (finalAmount < 0)
				finalAmount = 0.0;

			getViewState().setFinalAmountLabel(getFinalAmountLabelString());
			getViewState().setFinalAmount(getFinalAmountString());
		}
	}

	private String getFinalAmountLabelString() {
		return walletInfo.getCurrency().getValue().equals(CurrencyEnum.GVT.getValue())
				? context.getString(R.string.you_will_get)
				: context.getString(R.string.approximate_amount);
	}

	private String getFinalAmountString() {
		return String.format(Locale.getDefault(), "%s %s", StringFormatUtil.formatCurrencyAmount(finalAmount, walletInfo.getCurrency().getValue()), walletInfo.getCurrency().getValue());
	}

	private String getFeeAmountString() {
		return StringFormatUtil.getValueString(walletInfo.getCommission(), walletInfo.getCurrency().getValue());
	}

	private void getWalletInfo() {
		if (walletManager != null && model != null) {
			getViewState().showProgress(true);
			getWithdrawalInfoSubscription = walletManager.getWalletWithdrawInfo()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetWalletWithdrawInfoSuccess,
							this::handleGetWalletWithdrawInfoError);
		}
	}

	private void handleGetWalletWithdrawInfoSuccess(WithdrawalSummary response) {
		getWithdrawalInfoSubscription.unsubscribe();
		getViewState().showProgress(false);

		for (WalletWithdrawalInfo info : response.getWallets()) {
			if (info.getCurrency().getValue().equals(model.getCurrency())) {
				walletInfo = info;
				availableInWallet = walletInfo.getAvailableToWithdrawal();
				getViewState().setWalletInfo(walletInfo);
				updateFinalAmount();
				updateFeeAmount();
				break;
			}
		}
	}

	private void handleGetWalletWithdrawInfoError(Throwable throwable) {
		getWithdrawalInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onWithdrawSucceeded() {
		getViewState().finishActivity();
	}
}
