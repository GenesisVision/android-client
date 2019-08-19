package vision.genesis.clientapp.feature.main.dashboard.investor;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import io.swagger.client.model.DashboardChartValue;
import vision.genesis.clientapp.feature.main.dashboard.investor.header.InvestorDashboardHeaderPortfolioFragment;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/08/2018.
 */
public class DashboardHeaderPagerAdapter extends FragmentStatePagerAdapter
{
	private InvestorDashboardHeaderPortfolioFragment portfolioFragment;

//	private InvestorDashboardHeaderPortfolioFragment profitFragment;

	private TabLayout tabLayout;

	DashboardHeaderPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		portfolioFragment = new InvestorDashboardHeaderPortfolioFragment();
//		profitFragment = new InvestorDashboardHeaderPortfolioFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "portfolio":
				return portfolioFragment;
//			case "profit":
//				return profitFragment;
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return tabLayout.getTabCount();
	}

	public void setPortfolioChart(DashboardChartValue chart, DateRange dateRange) {
		if (portfolioFragment != null)
			portfolioFragment.setData(chart, dateRange);
	}

	public void setInRequests(Double totalValue, Double rate) {
		portfolioFragment.setInRequestsData(totalValue, rate);
	}

	public void chartViewModeTurnOff() {
		portfolioFragment.chartViewModeTurnOff();
	}
}
