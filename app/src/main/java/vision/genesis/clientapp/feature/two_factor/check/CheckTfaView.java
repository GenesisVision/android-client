package vision.genesis.clientapp.feature.two_factor.check;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

interface CheckTfaView extends MvpView
{
	void setCode(String code);

	void setConfirmButtonEnabled(boolean enabled);

	void setKeyboardKeysEnabled(boolean enabled);

	void showProgress(boolean show);

	void showToastMessage(String message);

	void finishActivity();
}
