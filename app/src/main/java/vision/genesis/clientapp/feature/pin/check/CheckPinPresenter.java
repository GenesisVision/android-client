package vision.genesis.clientapp.feature.pin.check;

import android.app.Activity;
import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.events.CheckPinSuccessEvent;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.VibrationUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/06/2018.
 */

@InjectViewState
public class CheckPinPresenter extends MvpPresenter<CheckPinView>
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	private String pin = "";

	private int wrongAttempts = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	public void onNumber(String number) {
		if (pin.length() < Constants.PIN_CODE_LENGTH) {
			pin += number;
			getViewState().setPin(pin.length());
		}
		if (pin.length() == Constants.PIN_CODE_LENGTH) {
			getViewState().setKeyboardKeysEnabled(false);
			checkPin();
		}
	}

	public void onBackspace() {
		if (pin.length() > 0) {
			pin = pin.substring(0, pin.length() - 1);
			getViewState().setPin(pin.length());
			getViewState().setKeyboardKeysEnabled(true);
		}
	}

	public void onLongBackspace() {
		pin = "";
		getViewState().setPin(0);
		getViewState().setKeyboardKeysEnabled(true);
	}


	private void checkPin() {
		if (!settingsManager.checkPin(pin)) {
			getViewState().setPinError(true);
			getViewState().setKeyboardKeysEnabled(true);
			pin = "";
			wrongAttempts++;
			if (wrongAttempts == Constants.PIN_MAX_WRONG_ATTEMPTS) {
				getViewState().setKeyboardKeysEnabled(false);
				getViewState().setErrorMessage(context.getString(R.string.error_pin_too_many_attempts));
				VibrationUtil.vibrateResetPin(context);
			}
			else {
				VibrationUtil.vibrateWrongPin(context);
			}
		}
		else {
			VibrationUtil.vibrateRightPin(context);
			EventBus.getDefault().post(new CheckPinSuccessEvent());
			getViewState().finishActivity(Activity.RESULT_OK);
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
}
