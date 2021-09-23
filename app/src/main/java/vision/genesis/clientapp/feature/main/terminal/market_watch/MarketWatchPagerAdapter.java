package vision.genesis.clientapp.feature.main.terminal.market_watch;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import vision.genesis.clientapp.feature.main.terminal.market_watch.list.FavoriteTickersListFragment;
import vision.genesis.clientapp.feature.main.terminal.market_watch.list.TickersListFragment;
import vision.genesis.clientapp.model.terminal.MarketWatchTickerModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

public class MarketWatchPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private FavoriteTickersListFragment favoritesFragment;

	private TickersListFragment btcFragment;

	private TickersListFragment bnbFragment;

	private TickersListFragment altsFragment;

	private TickersListFragment fiatsFragment;

	private TabLayout tabLayout;

	MarketWatchPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		favoritesFragment = new FavoriteTickersListFragment();
		btcFragment = new TickersListFragment();
		bnbFragment = new TickersListFragment();
		altsFragment = new TickersListFragment();
		fiatsFragment = new TickersListFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "favorites":
				return favoritesFragment;
			case "btc":
				return btcFragment;
			case "bnb":
				return bnbFragment;
			case "alts":
				return altsFragment;
			case "fiats":
				return fiatsFragment;
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

	public void setFavoriteTickers(List<MarketWatchTickerModel> tickers) {
		if (favoritesFragment != null) {
			favoritesFragment.setTickers(tickers);
		}
	}

	public void setBtcTickers(List<MarketWatchTickerModel> tickers) {
		if (btcFragment != null) {
			btcFragment.setTickers(tickers);
		}
	}

	public void setBnbTickers(List<MarketWatchTickerModel> tickers) {
		if (bnbFragment != null) {
			bnbFragment.setTickers(tickers);
		}
	}

	public void setAltsTickers(List<MarketWatchTickerModel> tickers) {
		if (altsFragment != null) {
			altsFragment.setTickers(tickers);
		}
	}

	public void setFiatsTickers(List<MarketWatchTickerModel> tickers) {
		if (fiatsFragment != null) {
			fiatsFragment.setTickers(tickers);
		}
	}

	public void showFavoriteTickersProgress() {
		if (favoritesFragment != null) {
			favoritesFragment.showProgress();
		}
	}
}