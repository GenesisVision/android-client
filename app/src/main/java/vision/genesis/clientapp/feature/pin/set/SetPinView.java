package vision.genesis.clientapp.feature.pin.set;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/06/2018.
 */

interface SetPinView extends MvpView
{
	void setPin(int symbolsEntered);

	void setPinError(boolean error);

	void setRepeatPin(int symbolsEntered);

	void setRepeatPinError(boolean error);

	void showRepeatPin(boolean show);

	void setKeyboardKeysEnabled(boolean enabled);

	void setErrorMessage(String message);

	void showToastMessage(String message);

	void finishAnimations();

	void finishActivity();
}
