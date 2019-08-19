package vision.genesis.clientapp.feature.two_factor.setup.first;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/06/2018.
 */
public class TfaTutorialPagerAdapter extends FragmentStatePagerAdapter
{
	private TfaTutorial0Fragment fragment0;

	private TfaTutorial1Fragment fragment1;

	private TfaTutorial2Fragment fragment2;

	private TfaTutorial3Fragment fragment3;

	private TfaTutorial4Fragment fragment4;

	public TfaTutorialPagerAdapter(FragmentManager fm) {
		super(fm);

		createFragments();
	}

	private void createFragments() {
		fragment0 = new TfaTutorial0Fragment();
		fragment1 = new TfaTutorial1Fragment();
		fragment2 = new TfaTutorial2Fragment();
		fragment3 = new TfaTutorial3Fragment();
		fragment4 = new TfaTutorial4Fragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return fragment0;
			case 1:
				return fragment1;
			case 2:
				return fragment2;
			case 3:
				return fragment3;
			case 4:
				return fragment4;
			default:
				return fragment1;
		}
	}

	@Override
	public int getCount() {
		return 5;
	}
}
