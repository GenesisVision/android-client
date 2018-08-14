package vision.genesis.clientapp.feature.main.dashboard.investor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vision.genesis.clientapp.feature.main.dashboard.investor.header.InvestorDashboardHeaderFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/08/2018.
 */
public class DashboardHeaderPagerAdapter extends FragmentStatePagerAdapter
{
	private InvestorDashboardHeaderFragment value;

	private InvestorDashboardHeaderFragment profit;

	DashboardHeaderPagerAdapter(FragmentManager fm) {
		super(fm);

		createFragments();
	}

	private void createFragments() {
		value = InvestorDashboardHeaderFragment.with(InvestorDashboardHeaderFragment.TYPE_PORTFOLIO);
		profit = InvestorDashboardHeaderFragment.with(InvestorDashboardHeaderFragment.TYPE_PROFIT);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return value;
			case 1:
				return profit;
			default:
				return value;
		}
	}

	@Override
	public int getCount() {
		return 2;
	}
}
