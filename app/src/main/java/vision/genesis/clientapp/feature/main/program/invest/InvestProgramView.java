package vision.genesis.clientapp.feature.main.program.invest;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.model.ProgramRequest;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

interface InvestProgramView extends MvpView
{
	void setAvailableToInvest(Double availableToInvest);

	void setAmount(String amountText);

	void setAmountBase(String amountBaseString);

	void setEntryFee(String entryFeeText);

	void setGvCommission(String gvCommissionString);

	void setInvestmentAmount(String investmentAmountText);

	void setContinueButtonEnabled(boolean enabled);

	void showConfirmDialog(ProgramRequest programRequest);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
