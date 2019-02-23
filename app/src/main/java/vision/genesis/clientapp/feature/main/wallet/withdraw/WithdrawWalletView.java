package vision.genesis.clientapp.feature.main.wallet.withdraw;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.WalletWithdrawalInfo;
import vision.genesis.clientapp.model.WithdrawalRequest;

/**
 * GenesisVision
 * Created by Vitaly on 2/27/18.
 */

interface WithdrawWalletView extends MvpView
{
	void setWalletInfo(WalletWithdrawalInfo selectedWallet);

	void setAmount(String amountString);

	void setFeeAmount(String feeAmountString);

	void setFinalAmountLabel(String finalAmountLabelString);

	void setFinalAmount(String finalAmountString);

	void setContinueButtonEnabled(boolean enabled);

	void showConfirmDialog(WithdrawalRequest withdrawalRequest);

	void showAddressNotValidError(boolean show);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
