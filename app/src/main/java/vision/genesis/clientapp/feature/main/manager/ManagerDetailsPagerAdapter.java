package vision.genesis.clientapp.feature.main.manager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

import vision.genesis.clientapp.feature.main.funds_list.FundsListFragment;
import vision.genesis.clientapp.feature.main.manager.info.ManagerInfoFragment;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListFragment;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

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

	private ProgramsListFragment managerProgramsFragment;

	private FundsListFragment managerFundsFragment;

	private TabLayout tabLayout;

	ManagerDetailsPagerAdapter(FragmentManager fm, TabLayout tabLayout, UUID managerId) {
		super(fm);
		this.tabLayout = tabLayout;

		managerInfoFragment = ManagerInfoFragment.with(managerId);

		ProgramsFilter programsFilter = new ProgramsFilter();
		programsFilter.setManagerId(managerId);
		Bundle data = new Bundle();
		data.putParcelable(ProgramsListFragment.EXTRA_FILTER, programsFilter);
		managerProgramsFragment = ProgramsListFragment.with(ProgramsListFragment.LOCATION_MANAGER, data);

		ProgramsFilter fundsFilter = new ProgramsFilter();
		fundsFilter.setManagerId(managerId);
		managerFundsFragment = FundsListFragment.with(FundsListFragment.LOCATION_MANAGER, fundsFilter);
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "info":
				return managerInfoFragment;
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
	}

	public void sendSwipeRefresh() {
	}

	public void onOffsetChanged(int verticalOffset) {
		managerProgramsFragment.onOffsetChanged(verticalOffset);
		managerFundsFragment.onOffsetChanged(verticalOffset);
	}
}