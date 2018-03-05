package vision.genesis.clientapp.feature.main.wallet.transactions;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * GenesisVision
 * Created by Vitaly on 3/5/18.
 */

public class TransactionsPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private TransactionsFragment transactionsAllFragment;

	private TransactionsFragment transactionsInternalFragment;

	private TransactionsFragment transactionsExternalFragment;

	public TransactionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				if (transactionsAllFragment == null)
					transactionsAllFragment = new TransactionsFragment();
				return transactionsAllFragment;
			case 1:
				if (transactionsInternalFragment == null)
					transactionsInternalFragment = new TransactionsFragment();
				return transactionsInternalFragment;
			case 2:
				if (transactionsExternalFragment == null)
					transactionsExternalFragment = new TransactionsFragment();
				return transactionsExternalFragment;
			default:
				return transactionsAllFragment;
		}
	}

	@Override
	public int getCount() {
		return 3;
	}

	public void destroy() {
		if (transactionsAllFragment != null)
			transactionsAllFragment.onDestroyView();

		if (transactionsInternalFragment != null)
			transactionsInternalFragment.onDestroyView();

		if (transactionsExternalFragment != null)
			transactionsExternalFragment.onDestroyView();
	}
}