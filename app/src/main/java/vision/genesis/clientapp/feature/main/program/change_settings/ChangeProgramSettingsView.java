package vision.genesis.clientapp.feature.main.program.change_settings;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

public interface ChangeProgramSettingsView extends MvpView
{
	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
