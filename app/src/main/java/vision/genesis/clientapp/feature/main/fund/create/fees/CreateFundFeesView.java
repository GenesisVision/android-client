package vision.genesis.clientapp.feature.main.fund.create.fees;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

interface CreateFundFeesView extends MvpView
{
	void updateEntryFeeDescription(Double managerMaxEntryFee);

	void updateExitFeeDescription(Double managerMaxExitFee);

	void setEntryFeeText(Double entryFeeValue);

	void setExitFeeText(Double exitFeeValue);

	void showSnackbarMessage(String message);

	void setNextButtonEnabled(boolean enabled);
}