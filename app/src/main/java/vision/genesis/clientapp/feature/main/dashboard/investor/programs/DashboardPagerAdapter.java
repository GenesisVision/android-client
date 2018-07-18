package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import vision.genesis.clientapp.model.InvestmentProgramDashboardExtended;

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

	private DashboardProgramsFragment activeProgramsFragment;

	private DashboardProgramsFragment archivedProgramsFragment;

	public DashboardPagerAdapter(FragmentManager fm) {
		super(fm);
		activeProgramsFragment = DashboardProgramsFragment.with();
		archivedProgramsFragment = DashboardProgramsFragment.with();
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return activeProgramsFragment;
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

	public void setActivePrograms(List<InvestmentProgramDashboardExtended> programs) {
		activeProgramsFragment.setPrograms(programs);
	}

	public void setArchivedPrograms(List<InvestmentProgramDashboardExtended> programs) {
		archivedProgramsFragment.setPrograms(programs);
	}

	public void destroy() {
		if (activeProgramsFragment != null)
			activeProgramsFragment.onDestroyView();

		if (archivedProgramsFragment != null)
			archivedProgramsFragment.onDestroyView();
	}

	public void showProgressBar(boolean show) {
		activeProgramsFragment.showProgressBar(show);
		archivedProgramsFragment.showProgressBar(show);
	}

	public void showNoInternet(boolean show) {
		activeProgramsFragment.showNoInternet(show);
		archivedProgramsFragment.showNoInternet(show);
	}

	public void showEmpty(boolean show) {
		activeProgramsFragment.showEmpty(show);
		archivedProgramsFragment.showEmpty(show);

	}

	public void setRefreshing(boolean show) {
		activeProgramsFragment.setRefreshing(show);
		archivedProgramsFragment.setRefreshing(show);

	}
}