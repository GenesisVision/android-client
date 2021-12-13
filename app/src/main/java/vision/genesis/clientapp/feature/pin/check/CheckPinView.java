package vision.genesis.clientapp.feature.pin.check;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/06/2018.
 */

interface CheckPinView extends MvpView
{
	void initStartText();

	void setPin(int symbolsEntered);

	void setPinError(boolean error);

	void setKeyboardKeysEnabled(boolean enabled);

	void setErrorMessage(String message);

	void showTimer(boolean show);

	void setTimer(String timerString);

	void showToastMessage(String message);

	void showVerifyFingerprintActivity();

	void shakeFingerprint();

	void disableFingerprint(boolean changeText);

	void finishAnimations();

	void finishActivity(int resultCode);
}
