package vision.genesis.clientapp.feature.main.program.invest;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.model.ProgramRequest;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

interface InvestProgramView extends MvpView
{
	void setAvailableToInvest(String availableToInvest);

	void setWalletsFrom(List<WalletData> wallets);

	void setWalletFrom(WalletData selectedWalletFrom);

	void setMinInvestmentAmount(Double minInvestmentAmount);

	void setAmount(String amountText);

	void setAmountBase(String amountBaseString);

	void setManagementFee(String entryFeeText);

	void setGvCommission(String gvCommissionString);

	void setInvestmentAmount(String investmentAmountText);

	void setContinueButtonEnabled(boolean enabled);

	void showConfirmDialog(ProgramRequest programRequest);

	void showProgress(boolean show);

	void showAmountProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
