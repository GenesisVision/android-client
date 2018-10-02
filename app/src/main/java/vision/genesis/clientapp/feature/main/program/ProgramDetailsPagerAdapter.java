package vision.genesis.clientapp.feature.main.program;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.UUID;

import vision.genesis.clientapp.feature.main.program.info.ProgramInfoFragment;
import vision.genesis.clientapp.feature.main.program.profit.ProgramProfitFragment;
import vision.genesis.clientapp.feature.main.program.trades.TradesFragment;
import vision.genesis.clientapp.feature.main.wallet.transactions.TransactionsFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

public class ProgramDetailsPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private final ProgramProfitFragment programProfitFragment;

	private ProgramInfoFragment programInfoFragment;

	private TransactionsFragment historyFragment;

	private TradesFragment tradesFragment;

	private TabLayout tabLayout;

	ProgramDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, UUID programId) {
		super(fm);
		this.tabLayout = tabLayout;
		programInfoFragment = ProgramInfoFragment.with(programId);
		programProfitFragment = ProgramProfitFragment.with(programId);
		historyFragment = TransactionsFragment.with(programId);
		tradesFragment = TradesFragment.with(programId);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "info":
				return programInfoFragment;
			case "profit":
				return programProfitFragment;
			case "trades":
				return tradesFragment;
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

	public void destroy() {
	}

	public void sendUpdate() {
		programInfoFragment.pagerShow();
		programProfitFragment.pagerShow();
		tradesFragment.pagerShow();
	}

	public void sendSwipeRefresh() {
		tradesFragment.onSwipeRefresh();
	}
}