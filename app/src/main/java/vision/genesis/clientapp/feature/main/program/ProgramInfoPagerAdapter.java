package vision.genesis.clientapp.feature.main.program;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.UUID;

import io.swagger.client.model.TransactionsFilter;
import vision.genesis.clientapp.feature.main.program.description.ProgramDescriptionFragment;
import vision.genesis.clientapp.feature.main.program.details.ProgramDetailsFragment;
import vision.genesis.clientapp.feature.main.program.trades.TradesFragment;
import vision.genesis.clientapp.feature.main.wallet.transactions.TransactionsFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

public class ProgramInfoPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private ProgramDetailsFragment programDetailsFragment;

	private ProgramDescriptionFragment programDescriptionFragment;

	private TransactionsFragment historyFragment;

	private TradesFragment tradesFragment;

	private TabLayout tabLayout;

	public ProgramInfoPagerAdapter(FragmentManager fm, TabLayout tabLayout, UUID programId) {
		super(fm);
		this.tabLayout = tabLayout;
		programDetailsFragment = ProgramDetailsFragment.with(programId);
		programDescriptionFragment = ProgramDescriptionFragment.with(programId);
		historyFragment = TransactionsFragment.with(TransactionsFilter.TypeEnum.ALL, programId);
		tradesFragment = TradesFragment.with(programId);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "details":
				return programDetailsFragment;
			case "description":
				return programDescriptionFragment;
			case "history":
				return historyFragment;
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
		programDetailsFragment.pagerShow();
		programDescriptionFragment.pagerShow();
	}
}