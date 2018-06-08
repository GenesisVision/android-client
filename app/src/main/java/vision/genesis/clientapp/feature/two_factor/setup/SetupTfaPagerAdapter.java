package vision.genesis.clientapp.feature.two_factor.setup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vision.genesis.clientapp.feature.two_factor.setup.first.SetupTfaFirstStepFragment;
import vision.genesis.clientapp.feature.two_factor.setup.second.SetupTfaSecondStepFragment;
import vision.genesis.clientapp.feature.two_factor.setup.third.SetupTfaThirdStepFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */
public class SetupTfaPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private SetupTfaFirstStepFragment firstStepFragment;

	private SetupTfaSecondStepFragment secondStepFragment;

	private SetupTfaThirdStepFragment thirdStepFragment;

	public SetupTfaPagerAdapter(FragmentManager fm) {
		super(fm);

		createFragments();
	}

	private void createFragments() {
		firstStepFragment = new SetupTfaFirstStepFragment();
		secondStepFragment = new SetupTfaSecondStepFragment();
		thirdStepFragment = new SetupTfaThirdStepFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return firstStepFragment;
			case 1:
				return secondStepFragment;
			case 2:
				return thirdStepFragment;
			default:
				return firstStepFragment;
		}
	}

	@Override
	public int getCount() {
		return 3;
	}

	public void setKey(String sharedKey, String authenticatorUri) {
		secondStepFragment.onSetKey(sharedKey, authenticatorUri);
	}
}
