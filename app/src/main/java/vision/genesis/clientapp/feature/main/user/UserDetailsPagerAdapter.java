package vision.genesis.clientapp.feature.main.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

import io.swagger.client.model.UserFeedMode;
import vision.genesis.clientapp.feature.main.follows_list.FollowsListFragment;
import vision.genesis.clientapp.feature.main.funds_list.FundsListFragment;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListFragment;
import vision.genesis.clientapp.feature.main.social.post.PostsListFragment;
import vision.genesis.clientapp.feature.main.user.info.UserInfoFragment;
import vision.genesis.clientapp.model.PostsFilter;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

public class UserDetailsPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private UserInfoFragment userInfoFragment;

	private PostsListFragment feedFragment;

	private ProgramsListFragment managerProgramsFragment;

	private FundsListFragment managerFundsFragment;

	private FollowsListFragment managerFollowsFragment;

	private TabLayout tabLayout;

	UserDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, UUID userId, boolean isOwnFeed) {
		super(fm);
		this.tabLayout = tabLayout;

		userInfoFragment = UserInfoFragment.with(userId);

		PostsFilter feedFilter = new PostsFilter();
		feedFilter.setUserMode(UserFeedMode.PROFILEPOSTS);
		feedFilter.setUserId(userId);
		feedFilter.setIsOwnFeed(isOwnFeed);
		feedFragment = PostsListFragment.with(feedFilter);

		ProgramsFilter programsFilter = new ProgramsFilter();
		programsFilter.setManagerId(userId);
		Bundle dataPrograms = new Bundle();
		dataPrograms.putParcelable(ProgramsListFragment.EXTRA_FILTER, programsFilter);
		managerProgramsFragment = ProgramsListFragment.with(ProgramsListFragment.LOCATION_MANAGER, dataPrograms);

		ProgramsFilter fundsFilter = new ProgramsFilter();
		fundsFilter.setManagerId(userId);
		managerFundsFragment = FundsListFragment.with(FundsListFragment.LOCATION_MANAGER, fundsFilter);

		ProgramsFilter followsFilter = new ProgramsFilter();
		followsFilter.setManagerId(userId);
		Bundle dataFollows = new Bundle();
		dataFollows.putParcelable(ProgramsListFragment.EXTRA_FILTER, programsFilter);
		managerFollowsFragment = FollowsListFragment.with(FollowsListFragment.LOCATION_MANAGER, dataFollows);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "info":
				return userInfoFragment;
			case "feed":
				return feedFragment;
			case "programs":
				return managerProgramsFragment;
			case "funds":
				return managerFundsFragment;
			case "follows":
				return managerFollowsFragment;
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
		userInfoFragment.pagerShow();
	}

	public void sendSwipeRefresh() {
		if (feedFragment != null) {
			feedFragment.onSwipeRefresh();
		}
	}

	public void onOffsetChanged(int verticalOffset) {
		managerProgramsFragment.onOffsetChanged(verticalOffset);
		managerFundsFragment.onOffsetChanged(verticalOffset);
		managerFollowsFragment.onOffsetChanged(verticalOffset);
	}
}