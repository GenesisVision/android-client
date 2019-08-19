package vision.genesis.clientapp.feature.main.assets;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import io.swagger.client.model.FundFacet;
import io.swagger.client.model.ProgramFacet;
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

	private TabLayout tabLayout;

	AssetsPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		programsFragment = ProgramsListFragment.with(ProgramsListFragment.LOCATION_ASSETS, null);
		fundsFragment = FundsListFragment.with(FundsListFragment.LOCATION_ASSETS, null);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "programs":
				return programsFragment;
			case "funds":
				return fundsFragment;
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

	public void setProgramsFacets(List<ProgramFacet> programsFacets) {
		programsFragment.setFacets(programsFacets);
	}

	public void setFundsFacets(List<FundFacet> fundsFacets) {
		fundsFragment.setFacets(fundsFacets);
	}

	public void onOffsetChanged(int verticalOffset) {
		programsFragment.onOffsetChanged(verticalOffset);
		fundsFragment.onOffsetChanged(verticalOffset);
	}
}