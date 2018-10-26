package vision.genesis.clientapp.feature.main.settings;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.ProfileFullViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

interface SettingsView extends MvpView
{
//	void showFingerprintOption();

	void updateProfile(ProfileFullViewModel profile);

//	void updateSettings(SettingsModel settingsModel);
//
//	void showDisablePin();
//
//	void showEnableFingerprint();
//
//	void showDisableFingerprint();

	void showDialogMessage(String message);

//	void changeThemeWithAnim(String newTheme);
}
