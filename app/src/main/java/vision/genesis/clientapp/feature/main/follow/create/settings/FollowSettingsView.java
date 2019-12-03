package vision.genesis.clientapp.feature.main.follow.create.settings;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 29/11/2019.
 */

interface FollowSettingsView extends MvpView
{
	void updateVolumeFeeDescription(Double minVolumeFee, Double maxVolumeFee);

	void updateSuccessFeeDescription(Double minSuccessFee, Double maxSuccessFee);

	void setVolumeFee(Double entryFeeValue);

	void setSuccessFee(Double exitFeeValue);

	void showSnackbarMessage(String message);

	void setConfirmButtonEnabled(boolean enabled);
}