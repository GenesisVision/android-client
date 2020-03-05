package vision.genesis.clientapp.feature.main.funds_challenge;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import vision.genesis.clientapp.feature.main.funds_list.FundsListFragment;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.FacetModel;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/03/2020.
 */

public class FundsChallengePagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private FundsListFragment fundsFragment;

	FundsChallengePagerAdapter(FragmentManager fm, FacetModel model) {
		super(fm);

		ProgramsFilter filter = new ProgramsFilter();
		filter.setFacetId(model.getId());
		filter.setDateRange(DateRange.createFromString(model.getTimeFrame()));
		filter.setSorting(SortingEnum.fromValue(model.getSorting()));
		fundsFragment = FundsListFragment.with(FundsListFragment.LOCATION_FACET, filter);
	}

	@Override
	public Fragment getItem(int position) {
		return fundsFragment;
	}

	@Override
	public int getCount() {
		return 1;
	}

	public void sendSwipeRefresh() {
		if (fundsFragment != null) {
			fundsFragment.onSwipeRefresh();
		}
	}

	public void onOffsetChanged(int verticalOffset) {
		fundsFragment.onOffsetChanged(verticalOffset);
	}
}