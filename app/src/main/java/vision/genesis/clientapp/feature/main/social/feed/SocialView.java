package vision.genesis.clientapp.feature.main.social.feed;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

interface SocialView extends MvpView
{
	void initViewPager(boolean showEvents);

	void setShowEventsChecked(boolean checked);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
