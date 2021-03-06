package vision.genesis.clientapp.feature.main.wallet;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

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
		transactionsFragment = TransactionsFragment.with(TransactionsFragment.LOCATION_WALLET, null);
		externalTransactionsFragment = ExternalTransactionsFragment.with(ExternalTransactionsFragment.LOCATION_WALLET, null);
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