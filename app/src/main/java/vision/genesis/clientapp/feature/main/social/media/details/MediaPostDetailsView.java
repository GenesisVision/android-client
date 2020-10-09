package vision.genesis.clientapp.feature.main.social.media.details;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.MediaPost;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/10/2020.
 */

interface MediaPostDetailsView extends MvpView
{
	void updateView(MediaPost post);

	void showProgressBar(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
