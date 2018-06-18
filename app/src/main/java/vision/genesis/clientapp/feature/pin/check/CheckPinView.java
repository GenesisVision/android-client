package vision.genesis.clientapp.feature.pin.check;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/06/2018.
 */

interface CheckPinView extends MvpView
{
	void setPin(int symbolsEntered);

	void setPinError(boolean error);

	void setKeyboardKeysEnabled(boolean enabled);

	void setErrorMessage(String message);

	void showToastMessage(String message);

	void finishActivity(int resultCode);
}
