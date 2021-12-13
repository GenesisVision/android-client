package vision.genesis.clientapp.feature.pin.check;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.DateTimeUtil;
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

	private int timerSeconds = 0;

	private Subscription timerSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		checkTimer();
	}

	void onStart() {
		initFingerprintAuthMaybe();
	}

	void onStop() {
		if (cancellationSignal != null) {
			cancellationSignal.cancel();
		}
	}

	@Override
	public void onDestroy() {
		if (timerSubscription != null) {
			timerSubscription.unsubscribe();
		}

		super.onDestroy();
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
				getViewState().setErrorMessage(context.getString(R.string.error_fingerprint_changed));
				getViewState().disableFingerprint(false);
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
				if (cancellationSignal != null) {
					cancellationSignal.cancel();
				}
				getViewState().disableFingerprint(false);

				settingsManager.setPinErrorTimestamp(System.currentTimeMillis());
				startTimer(Constants.PIN_TIMER_COOLDOWN_SECONDS);
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
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				if (fingerprintChanged && authManager.hasEnrolledFingerprints()) {
					getViewState().showVerifyFingerprintActivity();
				}
			}
			finish();
		}
	}

	private void checkTimer() {
		long secondsLeft = Constants.PIN_TIMER_COOLDOWN_SECONDS - (System.currentTimeMillis() - settingsManager.getPinErrorTimestamp()) / 1000;
		if (secondsLeft > 0) {
			getViewState().setKeyboardKeysEnabled(false);
			getViewState().setErrorMessage(context.getString(R.string.error_pin_too_many_attempts));
			getViewState().disableFingerprint(false);
			startTimer((int) secondsLeft);
		}
	}

	private void startTimer(int seconds) {
		timerSeconds = seconds;
		getViewState().showTimer(true);
		getViewState().setTimer(DateTimeUtil.formatTimer(timerSeconds));
		timerSubscription = Observable.interval(1L, TimeUnit.SECONDS)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(aLong -> {
					timerSeconds--;
					getViewState().setTimer(DateTimeUtil.formatTimer(timerSeconds));
					if (timerSeconds <= 0) {
						wrongAttempts = 0;
						getViewState().showTimer(false);
						getViewState().setPinError(false);
						getViewState().setKeyboardKeysEnabled(true);
						getViewState().initStartText();
						timerSubscription.unsubscribe();
					}
				}, error -> {
				});
	}

	private void finish() {
		getViewState().finishAnimations();
		new Handler().postDelayed(() -> getViewState().finishActivity(Activity.RESULT_OK), 200);
	}

	@Override
	public void onAuthenticationSucceeded() {
		finish();
	}

	@Override
	public void onAuthenticationHelp() {

	}

	@Override
	public void onAuthenticationError(String message) {
		getViewState().disableFingerprint(true);
	}

	@Override
	public void onAuthenticationFailed() {
		getViewState().showToastMessage(context.getString(R.string.fingerprint_not_recognized));
		getViewState().shakeFingerprint();
	}
}
