package vision.genesis.clientapp.feature.main.rating;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.LevelInfo;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListFragment;
import vision.genesis.clientapp.model.FacetModel;
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

	private List<LevelInfo> levelData;

	private List<ProgramsListFragment> fragments = new ArrayList<>();

	ProgramsRatingPagerAdapter(FragmentManager fm, List<LevelInfo> levelData, FacetModel facetModel) {
		super(fm);

		this.levelData = levelData;

		addAllLevelsFragment(facetModel);

		for (LevelInfo data : levelData) {
			ProgramsFilter filter = new ProgramsFilter();
			filter.setLevelMin(data.getLevel());
			filter.setLevelMax(data.getLevel());
			filter.setFacetId(facetModel.getId());
			addFragment(filter);
		}
	}

	private void addAllLevelsFragment(FacetModel facetModel) {
		ProgramsFilter filter = new ProgramsFilter();
		filter.setFacetId(facetModel.getId());
		addFragment(filter);
	}

	private void addFragment(ProgramsFilter filter) {
		Bundle bundle = new Bundle();
		bundle.putParcelable(ProgramsListFragment.EXTRA_FILTER, filter);
		fragments.add(ProgramsListFragment.with(ProgramsListFragment.LOCATION_RATING, bundle));
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return levelData.size();
	}
}