package vision.genesis.clientapp.feature.main.program;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

import vision.genesis.clientapp.feature.main.program.balance.ProgramBalanceFragment;
import vision.genesis.clientapp.feature.main.program.events.ProgramEventsFragment;
import vision.genesis.clientapp.feature.main.program.info.ProgramInfoFragment;
import vision.genesis.clientapp.feature.main.program.open_positions.OpenPositionsFragment;
import vision.genesis.clientapp.feature.main.program.period_history.PeriodHistoryFragment;
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

	private PeriodHistoryFragment periodHistoryFragment;

	private ProgramEventsFragment programEventsFragment;

	private TabLayout tabLayout;

	ProgramDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, UUID programId, String programCurrency, Integer periodDurationDays) {
		super(fm);
		this.tabLayout = tabLayout;
		programInfoFragment = ProgramInfoFragment.with(programId);
		openPositionsFragment = OpenPositionsFragment.with(programId);
		programProfitFragment = ProgramProfitFragment.with(programId);
		programEquityFragment = ProgramBalanceFragment.with(programId);
		programTradesFragment = ProgramTradesFragment.with(programId);
		periodHistoryFragment = PeriodHistoryFragment.with(programId, programCurrency, periodDurationDays);
		programEventsFragment = ProgramEventsFragment.with(ProgramEventsFragment.LOCATION_PROGRAM, programId);
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
			case "period_history":
				return periodHistoryFragment;
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
		periodHistoryFragment.pagerShow();
		programEventsFragment.pagerShow();
	}

	public void sendSwipeRefresh() {
		openPositionsFragment.onSwipeRefresh();
		programTradesFragment.onSwipeRefresh();
		periodHistoryFragment.onSwipeRefresh();
		programEventsFragment.onSwipeRefresh();
	}

	public void onOffsetChanged(int verticalOffset) {
		programProfitFragment.onOffsetChanged(verticalOffset);
		programEquityFragment.onOffsetChanged(verticalOffset);
		programTradesFragment.onOffsetChanged(verticalOffset);
		periodHistoryFragment.onOffsetChanged(verticalOffset);
		programEventsFragment.onOffsetChanged(verticalOffset);
	}
}