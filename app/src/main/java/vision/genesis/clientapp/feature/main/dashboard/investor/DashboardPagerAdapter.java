package vision.genesis.clientapp.feature.main.dashboard.investor;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vision.genesis.clientapp.feature.main.dashboard.investor.copytrading.DashboardCopytradingFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.funds.DashboardFundsFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.programs.DashboardProgramsFragment;

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

	private DashboardFundsFragment fundsFragment;

	private DashboardCopytradingFragment copytradingFragment;

	private TabLayout tabLayout;

	DashboardPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		programsFragment = DashboardProgramsFragment.with();
		fundsFragment = DashboardFundsFragment.with();
		copytradingFragment = DashboardCopytradingFragment.with();
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "programs":
				return programsFragment;
			case "funds":
				return fundsFragment;
			case "copytrading":
				return copytradingFragment;
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

	public void showProgressBar(boolean show) {
		programsFragment.showProgressBar(show);
		fundsFragment.showProgressBar(show);
		copytradingFragment.showProgressBar(show);
	}

	public void showEmpty(boolean show) {
		programsFragment.showEmpty(show);
		fundsFragment.showEmpty(show);
		copytradingFragment.showEmpty(show);

	}

	public void onAssetsListsUpdate() {
		programsFragment.pagerShow();
		fundsFragment.pagerShow();
		copytradingFragment.pagerShow();
	}
}