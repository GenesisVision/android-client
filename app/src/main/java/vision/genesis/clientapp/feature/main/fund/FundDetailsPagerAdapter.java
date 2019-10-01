package vision.genesis.clientapp.feature.main.fund;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

import vision.genesis.clientapp.feature.main.fund.balance.FundBalanceFragment;
import vision.genesis.clientapp.feature.main.fund.info.FundInfoFragment;
import vision.genesis.clientapp.feature.main.fund.profit.FundProfitFragment;
import vision.genesis.clientapp.feature.main.fund.reallocate_history.ReallocateHistoryFragment;
import vision.genesis.clientapp.feature.main.fund.structure.FundStructureFragment;
import vision.genesis.clientapp.feature.main.program.events.ProgramEventsFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

public class FundDetailsPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private FundInfoFragment fundInfoFragment;

	private FundStructureFragment fundStructureFragment;

	private ReallocateHistoryFragment reallocateHistoryFragment;

	private FundProfitFragment fundProfitFragment;

	private FundBalanceFragment fundBalanceFragment;

	private ProgramEventsFragment fundEventsFragment;

	private TabLayout tabLayout;

	FundDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, UUID fundId) {
		super(fm);
		this.tabLayout = tabLayout;
		fundInfoFragment = FundInfoFragment.with(fundId);
		fundStructureFragment = FundStructureFragment.with(fundId);
		reallocateHistoryFragment = ReallocateHistoryFragment.with(fundId);
		fundProfitFragment = FundProfitFragment.with(fundId);
		fundBalanceFragment = FundBalanceFragment.with(fundId);
		fundEventsFragment = ProgramEventsFragment.with(ProgramEventsFragment.LOCATION_FUND, fundId);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "info":
				return fundInfoFragment;
			case "structure":
				return fundStructureFragment;
			case "reallocate_history":
				return reallocateHistoryFragment;
			case "profit":
				return fundProfitFragment;
			case "balance":
				return fundBalanceFragment;
			case "events":
				return fundEventsFragment;
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
		fundInfoFragment.pagerShow();
		fundStructureFragment.pagerShow();
		reallocateHistoryFragment.pagerShow();
		fundProfitFragment.pagerShow();
		fundBalanceFragment.pagerShow();
		fundEventsFragment.pagerShow();
	}

	public void sendSwipeRefresh() {
		fundStructureFragment.onSwipeRefresh();
		reallocateHistoryFragment.onSwipeRefresh();
		fundEventsFragment.onSwipeRefresh();
	}

	public void onOffsetChanged(int verticalOffset) {
		reallocateHistoryFragment.onOffsetChanged(verticalOffset);
		fundProfitFragment.onOffsetChanged(verticalOffset);
		fundBalanceFragment.onOffsetChanged(verticalOffset);
		fundEventsFragment.onOffsetChanged(verticalOffset);
	}
}