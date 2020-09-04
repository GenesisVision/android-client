package vision.genesis.clientapp.feature.main.fund.add_asset;

import android.util.Pair;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.PlatformAsset;
import vision.genesis.clientapp.feature.main.fund.add_asset.fragment.AddAssetFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/03/2019.
 */

public class AddAssetPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private TabLayout tabLayout;

	private ArrayList<AddAssetFragment> fragments;

	AddAssetPagerAdapter(FragmentManager fm, TabLayout tabLayout, ArrayList<Pair<String, List<PlatformAsset>>> assets) {
		super(fm);
		this.tabLayout = tabLayout;

		fragments = new ArrayList<>();

		for (int i = 0; i < assets.size(); i++) {
			fragments.add(new AddAssetFragment());
		}
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	void sendSearchResults(ArrayList<Pair<String, List<PlatformAsset>>> model) {
		for (int i = 0; i < fragments.size(); i++) {
			fragments.get(i).setAssets(model.get(i).second);
		}
	}
}