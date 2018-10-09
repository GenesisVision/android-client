package vision.genesis.clientapp.feature.main.dashboard.investor;

import android.support.design.widget.TabLayout;
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
	private InvestorDashboardHeaderPortfolioFragment portfolioFragment;

	private InvestorDashboardHeaderPortfolioFragment profitFragment;

	private TabLayout tabLayout;

	DashboardHeaderPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		portfolioFragment = new InvestorDashboardHeaderPortfolioFragment();
		profitFragment = new InvestorDashboardHeaderPortfolioFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "portfolio":
				return portfolioFragment;
			case "profit":
				return profitFragment;
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return tabLayout.getTabCount();
	}

	public void setPortfolioChart(DashboardChartValue chart) {
		if (portfolioFragment != null)
			portfolioFragment.setData(chart);
	}

	public void setInRequests(Double totalValue, Double rate) {
		portfolioFragment.setInRequestsData(totalValue, rate);
	}

	public void chartViewModeTurnOff() {
		portfolioFragment.chartViewModeTurnOff();
	}
}
