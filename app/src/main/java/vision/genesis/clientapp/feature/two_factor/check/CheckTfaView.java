package vision.genesis.clientapp.feature.two_factor.check;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

interface CheckTfaView extends MvpView
{
	void setCode(String code);

	void setKeyboardKeysEnabled(boolean enabled);

	void showProgress(boolean show);

	void showToastMessage(String message);

	void finishActivity();
}
