package vision.genesis.clientapp.feature.main.fund.withdraw;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.model.FundRequest;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/10/2018.
 */

interface WithdrawFundView extends MvpView
{
	void setAvailableToWithdraw(Double availableToWithdraw);

	void setAmount(String amountText);

	void setExitFee(String exitFeeString);

	void setContinueButtonEnabled(boolean enabled);

	void showConfirmDialog(FundRequest fundRequest);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
