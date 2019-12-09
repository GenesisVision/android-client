package vision.genesis.clientapp.feature.main.follow.edit;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/12/2019.
 */

public interface EditFollowSettingsView extends MvpView
{
	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
