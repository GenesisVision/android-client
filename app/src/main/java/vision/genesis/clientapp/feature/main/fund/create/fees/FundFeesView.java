package vision.genesis.clientapp.feature.main.fund.create.fees;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

interface FundFeesView extends MvpView
{
	void updateEntryFeeDescription(Double managerMaxEntryFee);

	void updateExitFeeDescription(Double managerMaxExitFee);

	void setEntryFee(Double entryFeeValue);

	void setExitFee(Double exitFeeValue);

	void showSnackbarMessage(String message);

	void setConfirmButtonEnabled(boolean enabled);
}