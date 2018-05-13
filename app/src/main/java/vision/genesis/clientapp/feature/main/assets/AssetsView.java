package vision.genesis.clientapp.feature.main.assets;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.PlatformStatus;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/05/2018.
 */

interface AssetsView extends MvpView
{
	void showToast(String message);

	void showSnackbarMessage(String message);

	void showProgress(boolean show);

	void setFavoritesTabCount(int count);

	void setProgramsTabCount(int count);

	void onPlatformStatusUpdated(PlatformStatus platformStatus);
}