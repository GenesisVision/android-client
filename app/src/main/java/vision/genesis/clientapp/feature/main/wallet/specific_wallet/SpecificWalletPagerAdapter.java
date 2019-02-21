package vision.genesis.clientapp.feature.main.wallet.specific_wallet;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vision.genesis.clientapp.feature.main.wallet.external_transactions.ExternalTransactionsFragment;
import vision.genesis.clientapp.feature.main.wallet.transactions.TransactionsFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/02/2019.
 */

public class SpecificWalletPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private TransactionsFragment transactionsFragment;

	private ExternalTransactionsFragment externalTransactionsFragment;

	private TabLayout tabLayout;

	SpecificWalletPagerAdapter(FragmentManager fm, TabLayout tabLayout, String walletCurrency) {
		super(fm);
		this.tabLayout = tabLayout;
		transactionsFragment = TransactionsFragment.with(walletCurrency);
		externalTransactionsFragment = ExternalTransactionsFragment.with(walletCurrency);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
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
		transactionsFragment.pagerShow();
		externalTransactionsFragment.pagerShow();
	}

	public void sendSwipeRefresh() {
		transactionsFragment.onSwipeRefresh();
		externalTransactionsFragment.onSwipeRefresh();
	}

	public void onOffsetChanged(int verticalOffset) {
		transactionsFragment.onOffsetChanged(verticalOffset);
		externalTransactionsFragment.onOffsetChanged(verticalOffset);
	}
}