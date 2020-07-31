package vision.genesis.clientapp.feature.main.search;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import io.swagger.client.model.CommonPublicAssetsViewModel;
import vision.genesis.clientapp.feature.main.follows_list.FollowsListFragment;
import vision.genesis.clientapp.feature.main.funds_list.FundsListFragment;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListFragment;
import vision.genesis.clientapp.feature.main.users_list.fragment.UsersListFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/03/2019.
 */

public class SearchPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private ProgramsListFragment programsFragment;

	private FundsListFragment fundsFragment;

	private FollowsListFragment followsFragment;

	private UsersListFragment usersFragment;

	private TabLayout tabLayout;

	SearchPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		programsFragment = ProgramsListFragment.with(ProgramsListFragment.LOCATION_SEARCH, null);
		fundsFragment = FundsListFragment.with(FundsListFragment.LOCATION_SEARCH, null);
		followsFragment = FollowsListFragment.with(FollowsListFragment.LOCATION_SEARCH, null);
		usersFragment = UsersListFragment.with(UsersListFragment.LOCATION_SEARCH);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "programs":
				return programsFragment;
			case "funds":
				return fundsFragment;
			case "follows":
				return followsFragment;
			case "users":
				return usersFragment;
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return tabLayout.getTabCount();
	}

	public void sendSearchResults(CommonPublicAssetsViewModel model) {
		programsFragment.showSearchResults(model.getPrograms());
		fundsFragment.showSearchResults(model.getFunds());
		followsFragment.showSearchResults(model.getFollows());
		usersFragment.showSearchResults(model.getManagers());
	}
}