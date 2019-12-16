package vision.genesis.clientapp.feature.main.assets;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import io.swagger.client.model.AssetFacet;
import vision.genesis.clientapp.feature.main.follows_list.FollowsListFragment;
import vision.genesis.clientapp.feature.main.funds_list.FundsListFragment;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

public class AssetsPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private ProgramsListFragment programsFragment;

	private FundsListFragment fundsFragment;

	private FollowsListFragment followsFragment;

	private TabLayout tabLayout;

	AssetsPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		programsFragment = ProgramsListFragment.with(ProgramsListFragment.LOCATION_ASSETS, null);
		fundsFragment = FundsListFragment.with(FundsListFragment.LOCATION_ASSETS, null);
		followsFragment = FollowsListFragment.with(FollowsListFragment.LOCATION_ASSETS, null);
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

	public void setProgramsFacets(List<AssetFacet> programsFacets) {
		programsFragment.setFacets(programsFacets);
	}

	public void setFundsFacets(List<AssetFacet> fundsFacets) {
		fundsFragment.setFacets(fundsFacets);
	}

	public void setFollowsFacets(List<AssetFacet> followsFacets) {
		followsFragment.setFacets(followsFacets);
	}

	public void onOffsetChanged(int verticalOffset) {
		programsFragment.onOffsetChanged(verticalOffset);
		fundsFragment.onOffsetChanged(verticalOffset);
		followsFragment.onOffsetChanged(verticalOffset);
	}
}