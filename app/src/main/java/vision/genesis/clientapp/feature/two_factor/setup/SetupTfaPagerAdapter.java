package vision.genesis.clientapp.feature.two_factor.setup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import vision.genesis.clientapp.feature.two_factor.setup.first.SetupTfaFirstStepFragment;
import vision.genesis.clientapp.feature.two_factor.setup.forth.SetupTfaForthStepFragment;
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

	private SetupTfaForthStepFragment forthStepFragment;

	public SetupTfaPagerAdapter(FragmentManager fm) {
		super(fm);

		createFragments();
	}

	private void createFragments() {
		firstStepFragment = new SetupTfaFirstStepFragment();
		secondStepFragment = new SetupTfaSecondStepFragment();
		thirdStepFragment = new SetupTfaThirdStepFragment();
		forthStepFragment = new SetupTfaForthStepFragment();
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
			case 3:
				return forthStepFragment;
			default:
				return firstStepFragment;
		}
	}

	@Override
	public int getCount() {
		return 4;
	}

	public void setKey(String sharedKey, String authenticatorUri) {
		secondStepFragment.onSetKey(sharedKey, authenticatorUri);
	}

	public void setCodes(ArrayList<String> codes) {
		forthStepFragment.setCodes(codes);
	}
}
