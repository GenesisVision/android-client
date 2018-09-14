package vision.genesis.clientapp.feature.main.dashboard.investor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.swagger.client.model.DashboardChartValue;
import vision.genesis.clientapp.feature.main.dashboard.investor.header.InvestorDashboardHeaderPortfolioFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/08/2018.
 */
public class DashboardHeaderPagerAdapter extends FragmentStatePagerAdapter
{
	private InvestorDashboardHeaderPortfolioFragment portfolio;

	private InvestorDashboardHeaderPortfolioFragment profit;

	DashboardHeaderPagerAdapter(FragmentManager fm) {
		super(fm);

		createFragments();
	}

	private void createFragments() {
		portfolio = new InvestorDashboardHeaderPortfolioFragment();
		profit = new InvestorDashboardHeaderPortfolioFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return portfolio;
			case 1:
				return profit;
			default:
				return portfolio;
		}
	}

	@Override
	public int getCount() {
		return 2;
	}

	public void setPortfolioChart(DashboardChartValue chart) {
		if (portfolio != null)
			portfolio.setData(chart);
	}

	public void onDrag() {
		portfolio.onPagerDrag();
	}
}
