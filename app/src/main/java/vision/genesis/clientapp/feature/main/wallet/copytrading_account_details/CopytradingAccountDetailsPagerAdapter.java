package vision.genesis.clientapp.feature.main.wallet.copytrading_account_details;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import vision.genesis.clientapp.feature.main.copytrading.open_trades.CopytradingOpenTradesFragment;
import vision.genesis.clientapp.feature.main.copytrading.trades_history.CopytradingTradesHistoryFragment;
import vision.genesis.clientapp.feature.main.copytrading.trading_log.TradingLogFragment;

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

	private TradingLogFragment tradingLogFragment;

	private TabLayout tabLayout;

	CopytradingAccountDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, String accountCurrency) {
		super(fm);
		this.tabLayout = tabLayout;
//		openTradesFragment = CopytradingOpenTradesFragment.with(CopytradingOpenTradesFragment.LOCATION_COPYTRADING_ACCOUNT, accountCurrency);
//		tradesHistoryFragment = CopytradingTradesHistoryFragment.with(CopytradingTradesHistoryFragment.LOCATION_COPYTRADING_ACCOUNT, accountCurrency);
//		tradingLogFragment = TradingLogFragment.with(CopytradingTradesHistoryFragment.LOCATION_COPYTRADING_ACCOUNT, accountCurrency);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
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

	public void sendUpdate() {
		openTradesFragment.pagerShow();
		tradesHistoryFragment.pagerShow();
		tradingLogFragment.pagerShow();
	}

	public void sendSwipeRefresh() {
		openTradesFragment.pagerShow();
		tradesHistoryFragment.onSwipeRefresh();
		tradingLogFragment.onSwipeRefresh();
	}

	public void onOffsetChanged(int verticalOffset) {
		tradesHistoryFragment.onOffsetChanged(verticalOffset);
		tradingLogFragment.onOffsetChanged(verticalOffset);
	}
}