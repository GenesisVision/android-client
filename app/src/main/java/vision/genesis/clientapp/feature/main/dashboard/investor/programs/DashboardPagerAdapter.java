package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

import android.support.design.widget.TabLayout;
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

	private DashboardProgramsFragment fundsFragment;

	private TabLayout tabLayout;

	public DashboardPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		programsFragment = DashboardProgramsFragment.with();
		fundsFragment = DashboardProgramsFragment.with();
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
		if (programsFragment != null)
			programsFragment.onDestroyView();

		if (fundsFragment != null)
			fundsFragment.onDestroyView();
	}

	public void showProgressBar(boolean show) {
		programsFragment.showProgressBar(show);
		fundsFragment.showProgressBar(show);
	}

	public void showEmpty(boolean show) {
		programsFragment.showEmpty(show);
		fundsFragment.showEmpty(show);

	}
}