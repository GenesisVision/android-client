package vision.genesis.clientapp.feature.main.assets;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/05/2018.
 */

interface AssetsView extends MvpView
{
	void showSearch(boolean show);

	void showToast(String message);

	void showSnackbarMessage(String message);

	void showProgress(boolean show);

	void setFavoritesTabCount(int count);

	void setProgramsTabCount(int count);
}