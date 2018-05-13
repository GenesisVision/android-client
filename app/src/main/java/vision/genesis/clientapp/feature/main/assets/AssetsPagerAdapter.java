package vision.genesis.clientapp.feature.main.assets;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vision.genesis.clientapp.feature.main.favorites.FavoritesFragment;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListFragment;
import vision.genesis.clientapp.feature.tournament.TournamentFragment;

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

	private TournamentFragment tournamentFragment;

	private TabLayout tabLayout;

	AssetsPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		favoritesFragment = new FavoritesFragment();
		programsFragment = new ProgramsListFragment();
		tournamentFragment = new TournamentFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "favorites":
				return favoritesFragment;
			case "programs":
				return programsFragment;
			case "tournament":
				return tournamentFragment;
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

	public void setTournamentData(Integer tournamentCurrentRound, Integer tournamentTotalRounds) {
		tournamentFragment.setTournamentData(tournamentCurrentRound, tournamentTotalRounds);
	}
}