package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * GenesisVision
 * Created by Vitaly on 3/13/18.
 */

public class DashboardPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private DashboardProgramsFragment programsFragment;

	private DashboardProgramsFragment archivedProgramsFragment;

	public DashboardPagerAdapter(FragmentManager fm) {
		super(fm);
		programsFragment = DashboardProgramsFragment.with();
		archivedProgramsFragment = DashboardProgramsFragment.with();
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return programsFragment;
			case 1:
				return archivedProgramsFragment;
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return 2;
	}

	public void destroy() {
		if (programsFragment != null)
			programsFragment.onDestroyView();

		if (archivedProgramsFragment != null)
			archivedProgramsFragment.onDestroyView();
	}

	public void showProgressBar(boolean show) {
		programsFragment.showProgressBar(show);
		archivedProgramsFragment.showProgressBar(show);
	}

	public void showEmpty(boolean show) {
		programsFragment.showEmpty(show);
		archivedProgramsFragment.showEmpty(show);

	}
}