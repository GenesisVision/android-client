package vision.genesis.clientapp.feature.main.follow.create;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Locale;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.public_info.PublicInfoFragment;
import vision.genesis.clientapp.feature.main.follow.create.settings.FollowSettingsFragment;
import vision.genesis.clientapp.model.FollowSettingsModel;
import vision.genesis.clientapp.model.PublicInfoModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/12/2019.
 */

public class CreateFollowPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private PublicInfoFragment publicInfoFragment;

	private FollowSettingsFragment followSettingsFragment;

	private ArrayList<Fragment> fragments;

	CreateFollowPagerAdapter(FragmentManager fm, Boolean needPublicInfo) {
		super(fm);

		int stepsCount = needPublicInfo ? 2 : 1;

		fragments = new ArrayList<>();

		if (needPublicInfo) {
			publicInfoFragment = PublicInfoFragment.with(new PublicInfoModel(true, "01",
					GenesisVisionApplication.INSTANCE.getString(R.string.public_info), false,
					true, String.format(Locale.getDefault(), "%s (1/%d)", GenesisVisionApplication.INSTANCE.getString(R.string.next), stepsCount),
					null, null, null));
			fragments.add(publicInfoFragment);
		}

		String followSettingsButtonText = GenesisVisionApplication.INSTANCE.getString(R.string.create_follow);
		followSettingsFragment = FollowSettingsFragment.with(new FollowSettingsModel(stepsCount > 1, needPublicInfo ? "02" : "01",
				GenesisVisionApplication.INSTANCE.getString(R.string.fees_settings), followSettingsButtonText, null, null));
		fragments.add(followSettingsFragment);
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	int getSettingsPosition() {
		int pos = 0;
		for (Fragment fragment : fragments) {
			if (fragment.equals(followSettingsFragment)) {
				break;
			}
			pos++;
		}
		return pos;
	}
}
