package vision.genesis.clientapp.feature.main.program.create;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vision.genesis.clientapp.feature.main.program.create.first.CreateProgramFirstStepFragment;
import vision.genesis.clientapp.feature.main.program.create.second.CreateProgramSecondStepFragment;
import vision.genesis.clientapp.feature.main.program.create.third.CreateProgramThirdStepFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/07/2018.
 */

public class CreateProgramPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private CreateProgramFirstStepFragment firstStepFragment;

	private CreateProgramSecondStepFragment secondStepFragment;

	private CreateProgramThirdStepFragment thirdStepFragment;

	CreateProgramPagerAdapter(FragmentManager fm) {
		super(fm);

		createFragments();
	}

	private void createFragments() {
		firstStepFragment = new CreateProgramFirstStepFragment();
		secondStepFragment = new CreateProgramSecondStepFragment();
		thirdStepFragment = new CreateProgramThirdStepFragment();
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
}
