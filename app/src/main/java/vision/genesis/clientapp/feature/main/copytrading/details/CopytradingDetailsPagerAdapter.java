package vision.genesis.clientapp.feature.main.copytrading.details;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import vision.genesis.clientapp.feature.main.copytrading.subscriptions.CopytradingSubscriptionsFragment;
import vision.genesis.clientapp.feature.main.copytrading.trades_history.CopytradingTradesHistoryFragment;
import vision.genesis.clientapp.feature.main.copytrading.trading_log.TradingLogFragment;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/12/2019.
 */

public class CopytradingDetailsPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private CopytradingSubscriptionsFragment subscriptionsFragment;

//	private CopytradingOpenTradesFragment openTradesFragment;

	private CopytradingTradesHistoryFragment tradesHistoryFragment;

	private TradingLogFragment tradingLogFragment;


	private TabLayout tabLayout;

	CopytradingDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, TradingAccountDetailsModel model) {
		super(fm);
		this.tabLayout = tabLayout;

		subscriptionsFragment = CopytradingSubscriptionsFragment.with(model.getAccountId());
//		openTradesFragment = CopytradingOpenTradesFragment.with(model.getAccountId());
		tradesHistoryFragment = CopytradingTradesHistoryFragment.with(model);
		tradingLogFragment = TradingLogFragment.with(model.getAccountId());
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "subscriptions":
				return subscriptionsFragment;
//			case "open_trades":
//				return openTradesFragment;
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
		if (subscriptionsFragment != null) {
			subscriptionsFragment.pagerShow();
		}
//		if (openTradesFragment != null) {
//			openTradesFragment.pagerShow();
//		}
		if (tradesHistoryFragment != null) {
			tradesHistoryFragment.pagerShow();
		}
		if (tradingLogFragment != null) {
			tradingLogFragment.pagerShow();
		}
	}

	public void sendSwipeRefresh() {
		if (subscriptionsFragment != null) {
			subscriptionsFragment.onSwipeRefresh();
		}
//		if (openTradesFragment != null) {
//			openTradesFragment.pagerShow();
//		}
		if (tradesHistoryFragment != null) {
			tradesHistoryFragment.onSwipeRefresh();
		}
		if (tradingLogFragment != null) {
			tradingLogFragment.onSwipeRefresh();
		}
	}
}