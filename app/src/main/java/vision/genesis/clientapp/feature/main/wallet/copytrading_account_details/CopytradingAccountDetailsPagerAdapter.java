package vision.genesis.clientapp.feature.main.wallet.copytrading_account_details;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vision.genesis.clientapp.feature.main.copytrading.open_trades.CopytradingOpenTradesFragment;
import vision.genesis.clientapp.feature.main.copytrading.trades_history.CopytradingTradesHistoryFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/06/2019.
 */

public class CopytradingAccountDetailsPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private CopytradingOpenTradesFragment openTradesFragment;

	private CopytradingTradesHistoryFragment tradesHistoryFragment;

	private TabLayout tabLayout;

	CopytradingAccountDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, String accountCurrency) {
		super(fm);
		this.tabLayout = tabLayout;
		openTradesFragment = CopytradingOpenTradesFragment.with(CopytradingOpenTradesFragment.LOCATION_COPYTRADING_ACCOUNT, accountCurrency);
		tradesHistoryFragment = CopytradingTradesHistoryFragment.with(CopytradingTradesHistoryFragment.LOCATION_COPYTRADING_ACCOUNT, accountCurrency);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "open_trades":
				return openTradesFragment;
			case "trades_history":
				return tradesHistoryFragment;
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

	public void sendUpdate() {
		openTradesFragment.pagerShow();
		tradesHistoryFragment.pagerShow();
	}

	public void sendSwipeRefresh() {
		tradesHistoryFragment.onSwipeRefresh();
	}

	public void onOffsetChanged(int verticalOffset) {
//		openTradesFragment.onOffsetChanged(verticalOffset);
//		tradesHistoryFragment.onOffsetChanged(verticalOffset);
	}
}