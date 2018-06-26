package vision.genesis.clientapp.feature.pin.fingerprint;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/06/2018.
 */

interface VerifyFingerprintView extends MvpView
{
	void shakeFingerprint();

	void disableFingerprint(String message);

	void showToastMessage(String message);

	void finishActivity();
}
