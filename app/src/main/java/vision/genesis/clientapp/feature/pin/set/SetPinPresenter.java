package vision.genesis.clientapp.feature.pin.set;

import android.content.Context;
import android.os.Handler;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.VibrationUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/06/2018.
 */

@InjectViewState
public class SetPinPresenter extends MvpPresenter<SetPinView>
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	private String pin = "";

	private String repeatPin = "";

	private int wrongAttempts = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	public void onNumber(String number) {
		if (pin.length() != Constants.PIN_CODE_LENGTH)
			addToPin(number);
		else
			addToRepeatPin(number);
	}

	private void addToPin(String number) {
		if (pin.length() < Constants.PIN_CODE_LENGTH) {
			pin += number;
			getViewState().setPin(pin.length());
		}
		if (pin.length() == Constants.PIN_CODE_LENGTH) {
			getViewState().showRepeatPin(true);
			VibrationUtil.vibrateRightPin(context);
		}
	}

	private void addToRepeatPin(String number) {
		if (repeatPin.length() < Constants.PIN_CODE_LENGTH) {
			repeatPin += number;
			getViewState().setRepeatPin(repeatPin.length());
		}
		if (repeatPin.length() == Constants.PIN_CODE_LENGTH) {
			getViewState().setKeyboardKeysEnabled(false);
			checkPin();
		}
	}

	public void onBackspace() {
		if (pin.length() != Constants.PIN_CODE_LENGTH)
			backspacePin();
		else
			backspaceRepeatPin();
	}

	private void backspacePin() {
		if (pin.length() > 0) {
			pin = pin.substring(0, pin.length() - 1);
			getViewState().setPin(pin.length());
			getViewState().setKeyboardKeysEnabled(true);
		}
	}

	private void backspaceRepeatPin() {
		if (repeatPin.length() > 0) {
			repeatPin = repeatPin.substring(0, repeatPin.length() - 1);
			getViewState().setRepeatPin(repeatPin.length());
			getViewState().setKeyboardKeysEnabled(true);
		}
	}

	public void onLongBackspace() {
		if (pin.length() != Constants.PIN_CODE_LENGTH)
			longBackspacePin();
		else
			longBackspaceRepeatPin();
	}

	private void longBackspacePin() {
		pin = "";
		getViewState().setPin(0);
		getViewState().setKeyboardKeysEnabled(true);
	}

	private void longBackspaceRepeatPin() {
		repeatPin = "";
		getViewState().setRepeatPin(0);
		getViewState().setKeyboardKeysEnabled(true);
	}

	private void checkPin() {
		if (!pin.equals(repeatPin)) {
			getViewState().setRepeatPinError(true);
			getViewState().setKeyboardKeysEnabled(false);
			repeatPin = "";
			wrongAttempts++;
			if (wrongAttempts == Constants.PIN_MAX_WRONG_ATTEMPTS) {
				resetPin();
			}
			else {
				VibrationUtil.vibrateWrongPin(context);
				new Handler().postDelayed(() -> {
					getViewState().setRepeatPinError(false);
					getViewState().setKeyboardKeysEnabled(true);
				}, 400);
			}
		}
		else {
			VibrationUtil.vibrateRightPin(context);
			settingsManager.setPinCodeEnabled(true);
			if (settingsManager.setPin(pin)) {
				finish();
			}
			else {
				getViewState().showToastMessage(context.getString(R.string.error_set_pin));
				resetPin();
			}
		}
	}

	private void resetPin() {
		pin = "";
		repeatPin = "";
		wrongAttempts = 0;
		getViewState().setPin(0);
		getViewState().setRepeatPin(0);
		getViewState().setRepeatPinError(false);
		getViewState().showRepeatPin(false);
		getViewState().setKeyboardKeysEnabled(true);
		VibrationUtil.vibrateResetPin(context);
	}

	private void finish() {
		getViewState().finishAnimations();
		new Handler().postDelayed(() -> getViewState().finishActivity(), 200);
	}
}
