package vision.genesis.clientapp.feature.main.settings;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.ProfileFullViewModel;
import vision.genesis.clientapp.model.SettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

interface SettingsView extends MvpView
{
	void updateProfile(ProfileFullViewModel profile);

	void updateSettings(SettingsModel settingsModel);

	void showDisablePin();
}
