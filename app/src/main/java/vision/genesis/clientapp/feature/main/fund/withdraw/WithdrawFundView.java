package vision.genesis.clientapp.feature.main.fund.withdraw;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.model.FundRequest;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/10/2018.
 */

interface WithdrawFundView extends MvpView
{
	void setAvailableToWithdraw(String availableToWithdrawString);

	void setWalletsTo(List<WalletData> wallets);

	void setWalletTo(WalletData selectedWalletTo);

	void setMinWithdrawalAmount(Double minWithdrawalAmount);

	void setAmount(String amountText);

	void setEstimatedAmount(String estimatedAmountString);

	void setExitFee(String exitFeeString);

	void setWithdrawalAmount(String withdrawalAmountString);

	void setContinueButtonEnabled(boolean enabled);

	void showConfirmDialog(FundRequest fundRequest);

	void showProgress(boolean show);

	void showAmountProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
