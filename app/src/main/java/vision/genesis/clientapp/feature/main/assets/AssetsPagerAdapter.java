package vision.genesis.clientapp.feature.main.assets;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

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
		programsFragment = new ProgramsListFragment();
		fundsFragment = new FundsListFragment();
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
}