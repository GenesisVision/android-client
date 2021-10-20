package vision.genesis.clientapp.feature.main.dashboard.investments.coins;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import vision.genesis.clientapp.feature.main.dashboard.investments.coins.history.CoinsHistoryFragment;
import vision.genesis.clientapp.feature.main.dashboard.investments.coins.portfolio.CoinsAssetsFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2020.
 */

public class CoinsPortfolioPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private CoinsAssetsFragment coinsAssetsFragment;

	private CoinsHistoryFragment historyFragment;


	private TabLayout tabLayout;

	CoinsPortfolioPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;

		coinsAssetsFragment = new CoinsAssetsFragment();
		historyFragment = new CoinsHistoryFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "portfolio":
				return coinsAssetsFragment;
			case "history":
				return historyFragment;
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return tabLayout.getTabCount();
	}

	public void sendSwipeRefresh() {
		if (coinsAssetsFragment != null) {
			coinsAssetsFragment.onSwipeRefresh();
		}
		if (historyFragment != null) {
			historyFragment.onSwipeRefresh();
		}
	}

	public void sendUpdate() {
		if (coinsAssetsFragment != null) {
			coinsAssetsFragment.onSwipeRefresh();
		}
	}
}