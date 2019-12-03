package vision.genesis.clientapp.feature.main.follow.create;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/12/2019.
 */

public interface CreateFollowView extends MvpView
{
	void initViewPager(Boolean needPublicInfo);

	void showSettings();

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
