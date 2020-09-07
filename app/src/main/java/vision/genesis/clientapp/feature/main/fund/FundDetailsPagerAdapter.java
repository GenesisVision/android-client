package vision.genesis.clientapp.feature.main.fund;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.FundAssetInfo;
import io.swagger.client.model.FundDetailsFull;
import vision.genesis.clientapp.feature.main.fund.balance.FundBalanceFragment;
import vision.genesis.clientapp.feature.main.fund.info.FundInfoFragment;
import vision.genesis.clientapp.feature.main.fund.info.owner.FundOwnerInfoFragment;
import vision.genesis.clientapp.feature.main.fund.profit.FundProfitFragment;
import vision.genesis.clientapp.feature.main.fund.reallocate_history.FundHistoryFragment;
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

	private FundOwnerInfoFragment fundOwnerInfoFragment;

	private FundStructureFragment fundStructureFragment;

	private FundHistoryFragment fundHistoryFragment;

	private FundProfitFragment fundProfitFragment;

	private FundBalanceFragment fundBalanceFragment;

	private ProgramEventsFragment fundEventsFragment;

	private TabLayout tabLayout;

	FundDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, FundDetailsFull fundDetails) {
		super(fm);
		this.tabLayout = tabLayout;

		UUID fundId = fundDetails.getId();

		if (fundDetails.getPersonalDetails() != null && fundDetails.getPublicInfo().isIsOwnAsset()) {
			fundOwnerInfoFragment = FundOwnerInfoFragment.with(fundDetails);
		}
		else {
			fundInfoFragment = FundInfoFragment.with(fundId);
		}
		fundStructureFragment = FundStructureFragment.with();
		fundHistoryFragment = FundHistoryFragment.with(fundId);
		fundProfitFragment = FundProfitFragment.with(fundId);
		fundBalanceFragment = FundBalanceFragment.with(fundId);
		fundEventsFragment = ProgramEventsFragment.with(ProgramEventsFragment.LOCATION_FUND, fundId);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "info":
				return fundInfoFragment;
			case "owner_info":
				return fundOwnerInfoFragment;
			case "structure":
				return fundStructureFragment;
			case "reallocate_history":
				return fundHistoryFragment;
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

	public void setAssets(List<FundAssetInfo> assets) {
		if (fundStructureFragment != null) {
			fundStructureFragment.setAssets(assets);
		}
	}

	public void sendUpdate() {
		if (fundInfoFragment != null) {
			fundInfoFragment.pagerShow();
		}
		if (fundHistoryFragment != null) {
			fundHistoryFragment.pagerShow();
		}
		if (fundProfitFragment != null) {
			fundProfitFragment.pagerShow();
		}
		if (fundBalanceFragment != null) {
			fundBalanceFragment.pagerShow();
		}
		if (fundEventsFragment != null) {
			fundEventsFragment.pagerShow();
		}
	}

	public void sendSwipeRefresh() {
		if (fundHistoryFragment != null) {
			fundHistoryFragment.onSwipeRefresh();
		}
		if (fundEventsFragment != null) {
			fundEventsFragment.onSwipeRefresh();
		}
	}

	public void onOffsetChanged(int verticalOffset) {
		if (fundHistoryFragment != null) {
			fundHistoryFragment.onOffsetChanged(verticalOffset);
		}
		if (fundProfitFragment != null) {
			fundProfitFragment.onOffsetChanged(verticalOffset);
		}
		if (fundBalanceFragment != null) {
			fundBalanceFragment.onOffsetChanged(verticalOffset);
		}
		if (fundEventsFragment != null) {
			fundEventsFragment.onOffsetChanged(verticalOffset);
		}
	}

	void updateOwnerInfo(FundDetailsFull fundDetails) {
		if (fundOwnerInfoFragment != null) {
			fundOwnerInfoFragment.updateInfo(fundDetails);
		}
	}
}