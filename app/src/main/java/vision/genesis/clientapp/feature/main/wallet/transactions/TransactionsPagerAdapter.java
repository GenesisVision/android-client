package vision.genesis.clientapp.feature.main.wallet.transactions;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.swagger.client.model.TransactionsFilter;

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

	private TransactionsFragment transactionsFragment;

	public TransactionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				if (transactionsFragment == null)
					transactionsFragment = TransactionsFragment.with(TransactionsFilter.TypeEnum.ALL, null);
				return transactionsFragment;
			default:
				return null;
		}
	}

	public void setTransactionsFilterType(TransactionsFilter.TypeEnum type) {
		transactionsFragment.setTransactionsFilterType(type);
	}

	@Override
	public int getCount() {
		return 1;
	}

	public void destroy() {
		if (transactionsFragment != null)
			transactionsFragment.onDestroyView();
	}
}