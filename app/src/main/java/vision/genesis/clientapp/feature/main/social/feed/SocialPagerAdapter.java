package vision.genesis.clientapp.feature.main.social.feed;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import io.swagger.client.model.UserFeedMode;
import vision.genesis.clientapp.feature.main.social.post.PostsListFragment;
import vision.genesis.clientapp.model.PostsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class SocialPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private PostsListFragment liveFragment;

	private PostsListFragment hotFragment;

	private PostsListFragment feedFragment;

	private TabLayout tabLayout;

	SocialPagerAdapter(FragmentManager fm, TabLayout tabLayout, boolean showEvents) {
		super(fm);
		this.tabLayout = tabLayout;

		PostsFilter liveFilter = new PostsFilter();
		liveFilter.setShowOnlyUserPosts(!showEvents);
		liveFragment = PostsListFragment.with(liveFilter);

		PostsFilter hotFilter = new PostsFilter();
		hotFilter.setShowTop(true);
		hotFilter.setShowOnlyUserPosts(!showEvents);
		hotFragment = PostsListFragment.with(hotFilter);

		PostsFilter feedFilter = new PostsFilter();
		feedFilter.setUserMode(UserFeedMode.FRIENDSPOSTS);
		feedFilter.setShowOnlyUserPosts(!showEvents);
		feedFragment = PostsListFragment.with(feedFilter);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "live":
				return liveFragment;
			case "hot":
				return hotFragment;
			case "feed":
				return feedFragment;
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return tabLayout.getTabCount();
	}

	public void destroy() {
	}

	public void sendUpdate() {
	}

	public void setShowEvents(boolean showEvents) {
		if (liveFragment != null) {
			liveFragment.setShowEvents(showEvents);
		}
		if (hotFragment != null) {
			hotFragment.setShowEvents(showEvents);
		}
		if (feedFragment != null) {
			feedFragment.setShowEvents(showEvents);
		}
	}
}