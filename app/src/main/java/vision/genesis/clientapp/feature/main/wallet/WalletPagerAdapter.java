package vision.genesis.clientapp.feature.main.wallet;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vision.genesis.clientapp.feature.main.wallet.external_transactions.ExternalTransactionsFragment;
import vision.genesis.clientapp.feature.main.wallet.my_wallets.MyWalletsFragment;
import vision.genesis.clientapp.feature.main.wallet.transactions.TransactionsFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

public class WalletPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private MyWalletsFragment myWalletsFragment;

	private TransactionsFragment transactionsFragment;

	private ExternalTransactionsFragment externalTransactionsFragment;

	private TabLayout tabLayout;

	WalletPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;
		myWalletsFragment = new MyWalletsFragment();
		transactionsFragment = TransactionsFragment.with(null);
		externalTransactionsFragment = ExternalTransactionsFragment.with(null);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "my_wallets":
				return myWalletsFragment;
			case "transactions":
				return transactionsFragment;
			case "deposits_withdrawals":
				return externalTransactionsFragment;
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
		myWalletsFragment.pagerShow();
		transactionsFragment.pagerShow();
		externalTransactionsFragment.pagerShow();
	}

	public void sendSwipeRefresh() {
		myWalletsFragment.onSwipeRefresh();
		transactionsFragment.onSwipeRefresh();
		externalTransactionsFragment.onSwipeRefresh();
	}

	public void onOffsetChanged(int verticalOffset) {
		transactionsFragment.onOffsetChanged(verticalOffset);
		externalTransactionsFragment.onOffsetChanged(verticalOffset);
	}
}