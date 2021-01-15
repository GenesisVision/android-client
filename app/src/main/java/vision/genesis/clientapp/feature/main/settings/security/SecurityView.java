package vision.genesis.clientapp.feature.main.settings.security;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.model.SettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/10/2018.
 */

interface SecurityView extends MvpView
{
	void showFingerprintOption();

	void updateSettings(SettingsModel settingsModel);

	void showEnableFingerprint();

	void showDisableFingerprint();

	void showSetupTfaActivity();

	void showDisableTfaActivity();

	void showSetPinActivity();

	void showDisablePin();

	void showSnackbarMessage(String message);

	void showDialogMessage(String message);
}
