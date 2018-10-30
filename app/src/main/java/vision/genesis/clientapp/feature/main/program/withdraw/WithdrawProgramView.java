package vision.genesis.clientapp.feature.main.program.withdraw;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.model.ProgramRequest;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

interface WithdrawProgramView extends MvpView
{
	void setAvailableToWithdraw(String availableToWithdrawText);

	void setAmount(String amountText);

	void setAmountBase(String amountBaseString);

	void setCurrency(String programCurrency);

	void setRemainingInvestment(String remainingInvestmentString);

	void setPayoutDate(String payoutDate);

	void setContinueButtonEnabled(boolean enabled);

	void showConfirmDialog(ProgramRequest programRequest);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
