package vision.genesis.clientapp.feature.main.program;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.UUID;

import vision.genesis.clientapp.feature.main.program.balance.ProgramBalanceFragment;
import vision.genesis.clientapp.feature.main.program.events.ProgramEventsFragment;
import vision.genesis.clientapp.feature.main.program.info.ProgramInfoFragment;
import vision.genesis.clientapp.feature.main.program.open_positions.OpenPositionsFragment;
import vision.genesis.clientapp.feature.main.program.profit.ProgramProfitFragment;
import vision.genesis.clientapp.feature.main.program.trades.ProgramTradesFragment;

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

	private ProgramInfoFragment programInfoFragment;

	private OpenPositionsFragment openPositionsFragment;

	private ProgramProfitFragment programProfitFragment;

	private ProgramBalanceFragment programEquityFragment;

	private ProgramTradesFragment programTradesFragment;

	private ProgramEventsFragment programEventsFragment;

	private TabLayout tabLayout;

	ProgramDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, UUID programId) {
		super(fm);
		this.tabLayout = tabLayout;
		programInfoFragment = ProgramInfoFragment.with(programId);
		openPositionsFragment = OpenPositionsFragment.with(programId);
		programProfitFragment = ProgramProfitFragment.with(programId);
		programEquityFragment = ProgramBalanceFragment.with(programId);
		programEventsFragment = ProgramEventsFragment.with(ProgramEventsFragment.LOCATION_PROGRAM, programId);
		programTradesFragment = ProgramTradesFragment.with(programId);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "info":
				return programInfoFragment;
			case "open_positions":
				return openPositionsFragment;
			case "profit":
				return programProfitFragment;
			case "equity":
				return programEquityFragment;
			case "trades":
				return programTradesFragment;
			case "events":
				return programEventsFragment;
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
		openPositionsFragment.pagerShow();
		programProfitFragment.pagerShow();
		programEquityFragment.pagerShow();
		programTradesFragment.pagerShow();
		programEventsFragment.pagerShow();
	}

	public void sendSwipeRefresh() {
		openPositionsFragment.onSwipeRefresh();
		programTradesFragment.onSwipeRefresh();
		programEventsFragment.onSwipeRefresh();
	}

	public void onOffsetChanged(int verticalOffset) {
		programProfitFragment.onOffsetChanged(verticalOffset);
		programEquityFragment.onOffsetChanged(verticalOffset);
		programTradesFragment.onOffsetChanged(verticalOffset);
		programEventsFragment.onOffsetChanged(verticalOffset);
	}
}