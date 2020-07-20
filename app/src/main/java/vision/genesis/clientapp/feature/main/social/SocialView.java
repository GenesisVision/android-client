package vision.genesis.clientapp.feature.main.social;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

interface SocialView extends MvpView
{
	void updateMedia();

	void updateLive();

	void openMediaUrl(String url);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
