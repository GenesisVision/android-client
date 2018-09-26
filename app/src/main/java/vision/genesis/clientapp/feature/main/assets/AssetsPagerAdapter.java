package vision.genesis.clientapp.feature.main.assets;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vision.genesis.clientapp.feature.main.favorites.FavoritesFragment;
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

	private FavoritesFragment favoritesFragment;

	private ProgramsListFragment programsFragment;

	private ProgramsListFragment fundsFragment;

	private TabLayout tabLayout;

	AssetsPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		favoritesFragment = new FavoritesFragment();
		programsFragment = new ProgramsListFragment();
		fundsFragment = new ProgramsListFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "favorites":
				return favoritesFragment;
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
}