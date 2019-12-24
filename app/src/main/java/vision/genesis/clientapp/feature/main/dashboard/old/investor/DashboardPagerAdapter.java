package vision.genesis.clientapp.feature.main.dashboard.old.investor;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import vision.genesis.clientapp.feature.main.copytrading.open_trades.CopytradingOpenTradesFragment;
import vision.genesis.clientapp.feature.main.copytrading.trades_history.CopytradingTradesHistoryFragment;
import vision.genesis.clientapp.feature.main.copytrading.trading_log.TradingLogFragment;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.copytrading.DashboardCopytradingFragment;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.funds.DashboardFundsFragment;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.programs.DashboardProgramsFragment;

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

	private CopytradingOpenTradesFragment openTradesFragment;

	private CopytradingTradesHistoryFragment tradesHistoryFragment;

	private TradingLogFragment tradingLogFragment;

	private TabLayout tabLayout;

	DashboardPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		programsFragment = DashboardProgramsFragment.with();
		fundsFragment = DashboardFundsFragment.with();
		copytradingFragment = DashboardCopytradingFragment.with();
//		openTradesFragment = CopytradingOpenTradesFragment.with(CopytradingOpenTradesFragment.LOCATION_DASHBOARD, null);
//		tradesHistoryFragment = CopytradingTradesHistoryFragment.with(CopytradingTradesHistoryFragment.LOCATION_DASHBOARD, null);
//		tradingLogFragment = TradingLogFragment.with(CopytradingTradesHistoryFragment.LOCATION_DASHBOARD, null);
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
			case "open_trades":
				return openTradesFragment;
			case "trades_history":
				return tradesHistoryFragment;
			case "trading_log":
				return tradingLogFragment;
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
		openTradesFragment.showProgressBar(show);
		tradesHistoryFragment.showProgress(show);
		tradingLogFragment.showProgress(show);
	}

	public void onAssetsListsUpdate() {
		programsFragment.pagerShow();
		fundsFragment.pagerShow();
		copytradingFragment.pagerShow();
		openTradesFragment.pagerShow();
		tradesHistoryFragment.onSwipeRefresh();
		tradingLogFragment.onSwipeRefresh();
	}

	public void onOffsetChanged(int verticalOffset) {
		programsFragment.onOffsetChanged(verticalOffset);
		fundsFragment.onOffsetChanged(verticalOffset);
		copytradingFragment.onOffsetChanged(verticalOffset);
		tradesHistoryFragment.onDashboardOffsetChanged(verticalOffset);
		tradingLogFragment.onDashboardOffsetChanged(verticalOffset);
	}
}