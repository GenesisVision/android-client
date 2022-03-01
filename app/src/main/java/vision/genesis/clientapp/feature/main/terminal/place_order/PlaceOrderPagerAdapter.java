package vision.genesis.clientapp.feature.main.terminal.place_order;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

import io.swagger.client.model.TradingAccountPermission;
import io.swagger.client.model.TradingPlatformBinanceOrdersMode;
import vision.genesis.clientapp.feature.main.terminal.open_orders.OpenOrdersFragment;
import vision.genesis.clientapp.feature.main.terminal.order_history.OrderHistoryFragment;
import vision.genesis.clientapp.feature.main.terminal.positions.PositionsFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */

public class PlaceOrderPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private PositionsFragment positionsFragment;

	private OpenOrdersFragment openOrdersFragment;

	private OrderHistoryFragment orderHistoryFragment;

	private OrderHistoryFragment tradesHistoryFragment;

	private TabLayout tabLayout;

	PlaceOrderPagerAdapter(FragmentManager fm, TabLayout tabLayout, UUID accountId, String symbol, TradingAccountPermission currentMarket) {
		super(fm);
		this.tabLayout = tabLayout;

		if (currentMarket.equals(TradingAccountPermission.FUTURES)) {
			positionsFragment = PositionsFragment.with(accountId, symbol);
		}
		openOrdersFragment = OpenOrdersFragment.with(accountId);
		orderHistoryFragment = OrderHistoryFragment.with(accountId, symbol, TradingPlatformBinanceOrdersMode.ORDERHISTORY);
		tradesHistoryFragment = OrderHistoryFragment.with(accountId, symbol, TradingPlatformBinanceOrdersMode.TRADEHISTORY);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "positions":
				return positionsFragment;
			case "open_orders":
				return openOrdersFragment;
			case "order_history":
				return orderHistoryFragment;
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

	public void onOffsetChanged(int verticalOffset) {
		if (orderHistoryFragment != null) {
			orderHistoryFragment.onOffsetChanged(verticalOffset);
		}
		if (tradesHistoryFragment != null) {
			tradesHistoryFragment.onOffsetChanged(verticalOffset);
		}
	}

	public void destroy() {
	}
}