package vision.genesis.clientapp.feature.main.rating;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.LevelUpData;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListFragment;
import vision.genesis.clientapp.model.RatingInfo;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/04/2019.
 */

public class ProgramsRatingPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private List<ProgramsListFragment> fragments = new ArrayList<>();

	private TabLayout tabLayout;

	ProgramsRatingPagerAdapter(FragmentManager fm, TabLayout tabLayout, List<LevelUpData> levelUpData) {
		super(fm);
		this.tabLayout = tabLayout;

		for (LevelUpData data : levelUpData) {
			ProgramsFilter filter = new ProgramsFilter();
			filter.setLevelUpFrom(data.getLevel());
			Bundle bundle = new Bundle();
			bundle.putParcelable(ProgramsListFragment.EXTRA_FILTER, filter);

			RatingInfo ratingInfo = new RatingInfo(data.getLevel(), data.getTotal(), data.getQuota(), data.getTargetProfit());
			bundle.putParcelable(ProgramsListFragment.EXTRA_RATING_INFO, ratingInfo);
			fragments.add(ProgramsListFragment.with(ProgramsListFragment.LOCATION_RATING, bundle));
		}
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return tabLayout.getTabCount();
	}
}