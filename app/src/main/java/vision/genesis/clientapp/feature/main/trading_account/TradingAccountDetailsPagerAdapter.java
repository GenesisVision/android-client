package vision.genesis.clientapp.feature.main.trading_account;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

import io.swagger.client.model.PrivateTradingAccountFull;
import vision.genesis.clientapp.feature.main.trading_account.balance.TradingAccountBalanceFragment;
import vision.genesis.clientapp.feature.main.trading_account.info.TradingAccountInfoFragment;
import vision.genesis.clientapp.feature.main.trading_account.open_positions.TradingAccountOpenPositionsFragment;
import vision.genesis.clientapp.feature.main.trading_account.profit.TradingAccountProfitFragment;
import vision.genesis.clientapp.feature.main.trading_account.trades.TradingAccountTradesFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public class TradingAccountDetailsPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private TradingAccountInfoFragment infoFragment;

	private TradingAccountOpenPositionsFragment openPositionsFragment;

	private TradingAccountProfitFragment profitFragment;

	private TradingAccountBalanceFragment equityFragment;

	private TradingAccountTradesFragment tradesFragment;

	private TabLayout tabLayout;

	TradingAccountDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, PrivateTradingAccountFull accountDetails) {
		super(fm);
		this.tabLayout = tabLayout;

		UUID assetId = accountDetails.getId();

		infoFragment = TradingAccountInfoFragment.with(accountDetails);
		openPositionsFragment = TradingAccountOpenPositionsFragment.with(assetId);
		profitFragment = TradingAccountProfitFragment.with(accountDetails);
		equityFragment = TradingAccountBalanceFragment.with(accountDetails);
		tradesFragment = TradingAccountTradesFragment.with(assetId);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "info":
				return infoFragment;
			case "open_positions":
				return openPositionsFragment;
			case "profit":
				return profitFragment;
			case "equity":
				return equityFragment;
			case "trades":
				return tradesFragment;
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
		if (infoFragment != null) {
			infoFragment.pagerShow();
		}
		if (openPositionsFragment != null) {
			openPositionsFragment.pagerShow();
		}
		if (profitFragment != null) {
			profitFragment.pagerShow();
		}
		if (equityFragment != null) {
			equityFragment.pagerShow();
		}
		if (tradesFragment != null) {
			tradesFragment.pagerShow();
		}
	}

	public void sendSwipeRefresh() {
		if (openPositionsFragment != null) {
			openPositionsFragment.onSwipeRefresh();
		}
		if (tradesFragment != null) {
			tradesFragment.onSwipeRefresh();
		}
	}
}