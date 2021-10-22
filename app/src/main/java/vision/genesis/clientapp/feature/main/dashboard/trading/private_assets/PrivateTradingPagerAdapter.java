package vision.genesis.clientapp.feature.main.dashboard.trading.private_assets;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import io.swagger.client.model.DashboardTradingAsset;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2020.
 */

public class PrivateTradingPagerAdapter extends FragmentStatePagerAdapter
{
	private PrivateTradingListFragment accountsFragment;

	private PrivateTradingListFragment fundsFragment;


	private TabLayout tabLayout;

	PrivateTradingPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;

		accountsFragment = new PrivateTradingListFragment();
		fundsFragment = new PrivateTradingListFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "accounts":
				return accountsFragment;
			case "funds":
				return fundsFragment;
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return tabLayout.getTabCount();
	}

	public void setAccounts(List<DashboardTradingAsset> accounts, String baseCurrency) {
		if (accountsFragment != null) {
			accountsFragment.setItems(accounts, baseCurrency);
		}
	}

	public void setFunds(List<DashboardTradingAsset> funds, String baseCurrency) {
		if (fundsFragment != null) {
			fundsFragment.setItems(funds, baseCurrency);
		}
	}
}