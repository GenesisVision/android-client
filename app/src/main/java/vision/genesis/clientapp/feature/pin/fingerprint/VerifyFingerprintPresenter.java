package vision.genesis.clientapp.feature.pin.fingerprint;

import android.content.Context;
import android.os.Build;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.utils.fingerprint.FingerprintHandler;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/06/2018.
 */

@InjectViewState
public class VerifyFingerprintPresenter extends MvpPresenter<VerifyFingerprintView> implements FingerprintHandler.FingerprintAuthListener
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public SettingsManager settingsManager;

	private int requestCode;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (authManager.generateFingerprintKey())
				authManager.startFingerprintAuth(new FingerprintHandler(this));
		}
		else {
			getViewState().finishActivity();
		}
	}

	@Override
	public void onAuthenticationSucceeded() {
		implementRequest();
		getViewState().finishActivity();
	}

	@Override
	public void onAuthenticationHelp() {

	}

	@Override
	public void onAuthenticationError(String message) {
		getViewState().disableFingerprint(message);
	}

	@Override
	public void onAuthenticationFailed() {
		getViewState().showToastMessage(context.getString(R.string.fingerprint_not_recognized));
		getViewState().shakeFingerprint();
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	private void implementRequest() {
		switch (requestCode) {
			case VerifyFingerprintActivity.ENABLE_FINGERPRINT_REQUEST_CODE:
				settingsManager.setFingerprintEnabled(true);
				break;
			case VerifyFingerprintActivity.DISABLE_FINGERPRINT_REQUEST_CODE:
				settingsManager.setFingerprintEnabled(false);
				break;
		}
	}
}
