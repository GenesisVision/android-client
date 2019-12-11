package vision.genesis.clientapp.feature.main.program.create;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Locale;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.public_info.PublicInfoFragment;
import vision.genesis.clientapp.feature.main.program.create.deposit.CreateProgramDepositFragment;
import vision.genesis.clientapp.feature.main.program.create.settings.ProgramSettingsFragment;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.ProgramSettingsModel;
import vision.genesis.clientapp.model.PublicInfoModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public class CreateProgramPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private PublicInfoFragment publicInfoFragment;

	private ProgramSettingsFragment programSettingsFragment;

	private CreateProgramDepositFragment depositFragment;

	private ArrayList<Fragment> fragments;

	CreateProgramPagerAdapter(FragmentManager fm, Boolean needPublicInfo, Boolean needDeposit, CreateProgramModel model) {
		super(fm);

		int stepsCount = 1;
		if (needPublicInfo) {
			stepsCount++;
		}
		if (needDeposit) {
			stepsCount++;
		}

		fragments = new ArrayList<>();

		if (needPublicInfo) {
			publicInfoFragment = PublicInfoFragment.with(new PublicInfoModel(true, "01",
					GenesisVisionApplication.INSTANCE.getString(R.string.public_info), false,
					String.format(Locale.getDefault(), "%s (1/%d)", GenesisVisionApplication.INSTANCE.getString(R.string.next), stepsCount),
					null, null, null));
			fragments.add(publicInfoFragment);
		}

		String programSettingsButtonText = !needDeposit ? GenesisVisionApplication.INSTANCE.getString(R.string.create_program)
				: String.format(Locale.getDefault(), "%s (%d/%d)",
				GenesisVisionApplication.INSTANCE.getString(R.string.next),
				needPublicInfo ? 2 : 1,
				stepsCount);
		programSettingsFragment = ProgramSettingsFragment.with(new ProgramSettingsModel(stepsCount > 1, needPublicInfo ? "02" : "01",
				GenesisVisionApplication.INSTANCE.getString(R.string.main_settings), false,
				programSettingsButtonText, model.getCurrency(),
				null, null, null, null, null, null, true));
		fragments.add(programSettingsFragment);

		if (needDeposit) {
			depositFragment = CreateProgramDepositFragment.with(needPublicInfo ? "03" : "02", model);
			fragments.add(depositFragment);
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

	int getSettingsPosition() {
		int pos = 0;
		for (Fragment fragment : fragments) {
			if (fragment.equals(programSettingsFragment)) {
				break;
			}
			pos++;
		}
		return pos;
	}

	int getDepositPosition() {
		int pos = 0;
		for (Fragment fragment : fragments) {
			if (fragment.equals(depositFragment)) {
				break;
			}
			pos++;
		}
		return pos;
	}
}
