package vision.genesis.clientapp.feature.main.manager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.UUID;

import vision.genesis.clientapp.feature.main.funds_list.FundsListFragment;
import vision.genesis.clientapp.feature.main.manager.info.ManagerInfoFragment;
import vision.genesis.clientapp.feature.main.manager.profit.ManagerProfitFragment;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

public class ManagerDetailsPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private ManagerInfoFragment managerInfoFragment;

	private ManagerProfitFragment managerProfitFragment;

	private ProgramsListFragment managerProgramsFragment;

	private FundsListFragment managerFundsFragment;

	private TabLayout tabLayout;

	ManagerDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, UUID managerId) {
		super(fm);
		this.tabLayout = tabLayout;
		managerInfoFragment = ManagerInfoFragment.with(managerId);
		managerProfitFragment = ManagerProfitFragment.with(managerId);
		managerProgramsFragment = ProgramsListFragment.with(ProgramsListFragment.LOCATION_MANAGER, managerId);
		managerFundsFragment = FundsListFragment.with(FundsListFragment.LOCATION_MANAGER, managerId);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "info":
				return managerInfoFragment;
			case "profit":
				return managerProfitFragment;
			case "programs":
				return managerProgramsFragment;
			case "funds":
				return managerFundsFragment;
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
		managerInfoFragment.pagerShow();
		managerProfitFragment.pagerShow();
//		programBalanceFragment.pagerShow();
//		managerProgramsFragment.pagerShow();
//		managerFundsFragment.pagerShow();
	}

	public void sendSwipeRefresh() {
//		managerProgramsFragment.onAssetsListsUpdate();
//		managerFundsFragment.onAssetsListsUpdate();
	}

	public void onOffsetChanged(int verticalOffset) {
		managerProfitFragment.onOffsetChanged(verticalOffset);
//		managerProgramsFragment.onOffsetChanged(verticalOffset);
//		managerFundsFragment.onOffsetChanged(verticalOffset);
	}
}