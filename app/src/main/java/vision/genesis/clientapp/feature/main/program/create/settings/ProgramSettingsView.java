package vision.genesis.clientapp.feature.main.program.create.settings;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

interface ProgramSettingsView extends MvpView
{
	void setPeriodLengthOptions(ArrayList<String> periodLengthOptions);

	void setPeriodLength(String periodLength, Integer position);

	void setInvestmentLimit(Double investmentLimit);

	void setTradesDelayOptions(ArrayList<String> tradesDelayOptions);

	void setTradesDelay(String tradesDelay, Integer position);

	void setStopOutLevel(Double stopOutLevel);

	void setManagementFee(Double entryFeeValue);

	void setSuccessFee(Double exitFeeValue);

	void showStopOutError(String message);

	void hideStopOutError();

	void updateManagementFeeDescription(Double managerMaxEntryFee);

	void updateSuccessFeeDescription(Double managerMaxExitFee);

	void showSnackbarMessage(String message);

	void setConfirmButtonEnabled(boolean enabled);
}