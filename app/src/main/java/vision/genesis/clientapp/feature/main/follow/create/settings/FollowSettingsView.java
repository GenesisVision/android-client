package vision.genesis.clientapp.feature.main.follow.create.settings;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 29/11/2019.
 */

interface FollowSettingsView extends MvpView
{
	void updateEntryFeeDescription(Double managerMaxEntryFee);

	void updateSuccessFeeDescription(Double managerMaxExitFee);

	void setEntryFee(Double entryFeeValue);

	void setSuccessFee(Double exitFeeValue);

	void showSnackbarMessage(String message);

	void setConfirmButtonEnabled(boolean enabled);
}