package vision.genesis.clientapp.feature.main.search;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import io.swagger.client.model.SearchViewModel;
import vision.genesis.clientapp.feature.main.funds_list.FundsListFragment;
import vision.genesis.clientapp.feature.main.managers_list.ManagersListFragment;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListFragment;

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

	private ManagersListFragment managersFragment;

//	private FundsListFragment fundsFragment;

//	private ManagersListFragment managersFragment;

	private TabLayout tabLayout;

	SearchPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		programsFragment = ProgramsListFragment.with(ProgramsListFragment.LOCATION_SEARCH, null);
		fundsFragment = FundsListFragment.with(FundsListFragment.LOCATION_SEARCH, null);
		managersFragment = ManagersListFragment.with(ManagersListFragment.LOCATION_SEARCH);
//		fundsFragment = new FundsListFragment();
//		managersFragment = new ManagersListFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "programs":
				return programsFragment;
			case "funds":
				return fundsFragment;
			case "managers":
				return managersFragment;
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return tabLayout.getTabCount();
	}

	public void sendSearchResults(SearchViewModel model) {
		programsFragment.showSearchResults(model.getPrograms());
		fundsFragment.showSearchResults(model.getFunds());
		managersFragment.showSearchResults(model.getManagers());
	}
}