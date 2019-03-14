package vision.genesis.clientapp.feature.main.fund.withdraw;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.FundWithdrawInfo;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletMultiSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.withdraw.confirm.ConfirmFundWithdrawBottomSheetFragment;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

@InjectViewState
public class WithdrawFundPresenter extends MvpPresenter<WithdrawFundView> implements
		ConfirmFundWithdrawBottomSheetFragment.OnConfirmFundWithdrawListener,
		SelectWalletBottomSheetFragment.OnWalletSelectedListener
{
	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	@Inject
	public FundsManager fundsManager;

	private Subscription walletsSubscription;

	private Subscription withdrawInfoSubscription;

	private FundRequest fundRequest;

	private Double amount;

	private FundWithdrawInfo withdrawInfo;

	private Double availableToWithdraw;

	private Double exitFee;

	private Double withdrawalAmount;

	private WalletData selectedWalletTo;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToWallets();
		getWithdrawInfo();
	}

	@Override
	public void onDestroy() {
		if (walletsSubscription != null)
			walletsSubscription.unsubscribe();
		if (withdrawInfoSubscription != null)
			withdrawInfoSubscription.unsubscribe();

		super.onDestroy();
	}

	void setFundRequest(FundRequest fundRequest) {
		this.fundRequest = fundRequest;
		subscribeToWallets();
	}

	void onAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			amount = 0.0;
		}

		if (withdrawInfo != null) {
			if (amount > 100) {
				getViewState().setAmount("100");
				return;
			}

			updateAmounts();
		}
	}

	private void updateAmounts() {
		getViewState().setEstimatedAmount(getEstimatedAmountString());
		getViewState().setExitFee(getExitFeeString());
		getViewState().setWithdrawalAmount(getWithdrawalAmountString());
		checkContinueButtonAvailability();
	}

	private void checkContinueButtonAvailability() {
		getViewState().setContinueButtonEnabled(amount > 0 && amount <= 100);
	}

	private Double getEstimatedAmount() {
		return withdrawInfo.getAvailableToWithdraw() * withdrawInfo.getRate() * amount / 100;
	}

	private Double getEstimatedExitFee() {
		return getEstimatedAmount() * withdrawInfo.getExitFee() / 100;
	}

	private Double getWithdrawalAmount() {
		return getEstimatedAmount() - getEstimatedExitFee();
	}

	private String getEstimatedAmountString() {
		return String.format(Locale.getDefault(), "≈ %s %s",
				StringFormatUtil.formatCurrencyAmount(getEstimatedAmount(), selectedWalletTo.getCurrency().getValue()),
				selectedWalletTo.getCurrency().getValue());
	}

	private String getAmountToWithdrawString() {
		return String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(amount, 0, 4), getEstimatedAmountString());
	}

	private String getExitFeeString() {
		return String.format(Locale.getDefault(), "%s%% (≈ %s %s)",
				StringFormatUtil.formatAmount(withdrawInfo.getExitFee(), 0, 4),
				StringFormatUtil.formatCurrencyAmount(getEstimatedExitFee(), selectedWalletTo.getCurrency().getValue()),
				selectedWalletTo.getCurrency().getValue());
	}

	private String getWithdrawalAmountString() {
		return String.format(Locale.getDefault(), "≈ %s %s",
				StringFormatUtil.formatCurrencyAmount(getWithdrawalAmount(), selectedWalletTo.getCurrency().getValue()),
				selectedWalletTo.getCurrency().getValue());
	}

	void onMaxClicked() {
		getViewState().setAmount("100");
	}

	void onContinueClicked() {
		fundRequest.setAmount(amount);
		fundRequest.setWalletCurrency(selectedWalletTo.getCurrency().getValue());
		fundRequest.setAmountTopText(getAmountToWithdrawString());
		fundRequest.setInfoMiddleText(getExitFeeString());
		fundRequest.setAmountBottomText(getWithdrawalAmountString());
		getViewState().showConfirmDialog(fundRequest);
	}

	private void subscribeToWallets() {
		if (walletManager != null && fundRequest != null) {
			if (walletsSubscription != null)
				walletsSubscription.unsubscribe();
			walletsSubscription = walletManager.getWallets(CurrencyEnum.GVT.getValue(), false)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWalletUpdateSuccess,
							this::handleWalletUpdateError);
		}
	}

	private void handleWalletUpdateSuccess(WalletMultiSummary response) {
		List<WalletData> wallets = response.getWallets();

		getViewState().setWalletsTo(wallets);
		onWalletSelected(wallets.get(0));
	}

	private void handleWalletUpdateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getWithdrawInfo() {
		if (fundsManager != null && fundRequest != null && selectedWalletTo != null) {
			getViewState().showAmountProgress(true);
			getViewState().setContinueButtonEnabled(false);
			withdrawInfoSubscription = fundsManager.getWithdrawInfo(fundRequest.getFundId(), selectedWalletTo.getCurrency().getValue())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWithdrawInfoResponse,
							this::handleWithdrawInfoError);
		}
	}

	private void handleWithdrawInfoResponse(FundWithdrawInfo response) {
		withdrawInfoSubscription.unsubscribe();

		getViewState().showProgress(false);
		getViewState().showAmountProgress(false);

		withdrawInfo = response;

		getViewState().setAvailableToWithdraw(StringFormatUtil.getValueString(
				withdrawInfo.getAvailableToWithdraw() * withdrawInfo.getRate(),
				selectedWalletTo.getCurrency().getValue()));
		updateAmounts();
	}

	private void handleWithdrawInfoError(Throwable throwable) {
		withdrawInfoSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onWithdrawSucceeded() {
		getViewState().finishActivity();
	}


	@Override
	public void onWalletSelected(WalletData wallet) {
		this.selectedWalletTo = wallet;
		getViewState().setWalletTo(selectedWalletTo);
		getWithdrawInfo();
	}
}
