package vision.genesis.clientapp.feature.main.fund;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.UUID;

import vision.genesis.clientapp.feature.main.fund.balance.FundBalanceFragment;
import vision.genesis.clientapp.feature.main.fund.info.FundInfoFragment;
import vision.genesis.clientapp.feature.main.fund.profit.FundProfitFragment;
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

	private FundProfitFragment fundProfitFragment;

	private FundBalanceFragment fundBalanceFragment;

	private ProgramEventsFragment fundEventsFragment;

	private TabLayout tabLayout;

	FundDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, UUID programId) {
		super(fm);
		this.tabLayout = tabLayout;
		fundInfoFragment = FundInfoFragment.with(programId);
		fundProfitFragment = FundProfitFragment.with(programId);
		fundBalanceFragment = FundBalanceFragment.with(programId);
		fundEventsFragment = ProgramEventsFragment.with(programId);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "info":
				return fundInfoFragment;
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
		fundProfitFragment.pagerShow();
		fundBalanceFragment.pagerShow();
		fundEventsFragment.pagerShow();
	}

	public void sendSwipeRefresh() {
		fundEventsFragment.onSwipeRefresh();
	}

	public void onOffsetChanged(int verticalOffset) {
		fundProfitFragment.onOffsetChanged(verticalOffset);
		fundBalanceFragment.onOffsetChanged(verticalOffset);
		fundEventsFragment.onOffsetChanged(verticalOffset);
	}
}