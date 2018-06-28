package vision.genesis.clientapp.feature.pin.check;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.events.CheckPinSuccessEvent;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.VibrationUtil;
import vision.genesis.clientapp.utils.fingerprint.FingerprintHandler;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/06/2018.
 */

@InjectViewState
public class CheckPinPresenter extends MvpPresenter<CheckPinView> implements FingerprintHandler.FingerprintAuthListener
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public SettingsManager settingsManager;

	private String pin = "";

	private int wrongAttempts = 0;

	private boolean fingerprintEnabled;

	private boolean fingerprintChanged;

	private CancellationSignal cancellationSignal;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	void onStart() {
		initFingerprintAuthMaybe();
	}

	void onStop() {
		if (cancellationSignal != null)
			cancellationSignal.cancel();
	}

	void setFingerprintEnabled(boolean fingerprintEnabled) {
		this.fingerprintEnabled = fingerprintEnabled;
		initFingerprintAuthMaybe();
	}

	private void initFingerprintAuthMaybe() {
		if (fingerprintEnabled && authManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			cancellationSignal = authManager.startFingerprintAuth(new FingerprintHandler(this));
			if (cancellationSignal == null) {
				fingerprintChanged = true;
				getViewState().disableFingerprint(context.getString(R.string.error_fingerprint_changed));
				settingsManager.setFingerprintEnabled(false);
			}
		}
	}

	void onNumber(String number) {
		if (pin.length() < Constants.PIN_CODE_LENGTH) {
			pin += number;
			getViewState().setPin(pin.length());
		}
		if (pin.length() == Constants.PIN_CODE_LENGTH) {
			getViewState().setKeyboardKeysEnabled(false);
			checkPin();
		}
	}

	void onBackspace() {
		if (pin.length() > 0) {
			pin = pin.substring(0, pin.length() - 1);
			getViewState().setPin(pin.length());
			getViewState().setKeyboardKeysEnabled(true);
		}
	}

	void onLongBackspace() {
		pin = "";
		getViewState().setPin(0);
		getViewState().setKeyboardKeysEnabled(true);
	}


	private void checkPin() {
		if (!settingsManager.checkPin(pin)) {
			getViewState().setPinError(true);
			getViewState().setKeyboardKeysEnabled(false);
			pin = "";
			wrongAttempts++;
			if (wrongAttempts == Constants.PIN_MAX_WRONG_ATTEMPTS) {
				getViewState().setKeyboardKeysEnabled(false);
				getViewState().setErrorMessage(context.getString(R.string.error_pin_too_many_attempts));
				VibrationUtil.vibrateResetPin(context);
			}
			else {
				VibrationUtil.vibrateWrongPin(context);
				new Handler().postDelayed(() -> {
					getViewState().setPinError(false);
					getViewState().setKeyboardKeysEnabled(true);
				}, 400);
			}
		}
		else {
			VibrationUtil.vibrateRightPin(context);
			EventBus.getDefault().post(new CheckPinSuccessEvent());
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				if (fingerprintChanged && authManager.hasEnrolledFingerprints())
					getViewState().showVerifyFingerprintActivity();
			}
			finish();
		}
	}

	private void resetPin() {
		pin = "";
		wrongAttempts = 0;
		getViewState().setPin(0);
		getViewState().setPin(0);
		getViewState().setPinError(false);
		getViewState().setKeyboardKeysEnabled(true);
		VibrationUtil.vibrateResetPin(context);
	}

	private void finish() {
		getViewState().finishAnimations();
		new Handler().postDelayed(() -> getViewState().finishActivity(Activity.RESULT_OK), 200);
	}

	@Override
	public void onAuthenticationSucceeded() {
		EventBus.getDefault().post(new CheckPinSuccessEvent());
		finish();
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
}
