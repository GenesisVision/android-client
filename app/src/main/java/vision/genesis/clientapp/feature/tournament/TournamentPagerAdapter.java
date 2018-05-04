package vision.genesis.clientapp.feature.tournament;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import vision.genesis.clientapp.feature.tournament.round.TournamentProgramsFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/05/2018.
 */

public class TournamentPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private List<TournamentProgramsFragment> fragments = new ArrayList<>();

	TournamentPagerAdapter(FragmentManager fm, int maxRounds) {
		super(fm);

		for (int round = 1; round <= maxRounds; round++) {
			fragments.add(TournamentProgramsFragment.with(round));
		}

	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	public void destroy() {
	}
}