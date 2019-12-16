package vision.genesis.clientapp.feature.main.fund.change_settings;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/12/2019.
 */

public interface ChangeFundSettingsView extends MvpView
{
	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
