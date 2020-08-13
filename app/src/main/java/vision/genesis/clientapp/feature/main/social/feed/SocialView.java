package vision.genesis.clientapp.feature.main.social.feed;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.model.PostsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

interface SocialView extends MvpView
{
	void initViewPager(boolean showEvents);

	void setShowEventsChecked(boolean checked);

	void showTrendingBottomSheet();

	void showFilteredPosts(boolean show);

	void setFilter(PostsFilter filter);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
