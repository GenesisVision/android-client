package vision.genesis.clientapp.feature.main.program.create;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import io.swagger.client.model.Broker;
import io.swagger.client.model.Currency;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.public_info.PublicInfoFragment;
import vision.genesis.clientapp.feature.main.program.create.deposit.CreateProgramDepositFragment;
import vision.genesis.clientapp.feature.main.program.create.settings.ProgramSettingsFragment;
import vision.genesis.clientapp.feature.main.trading_account.create.broker.SelectBrokerFragment;
import vision.genesis.clientapp.feature.main.trading_account.create.settings.BrokerSettingsFragment;
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

	private SelectBrokerFragment brokerSelectFragment;

	private BrokerSettingsFragment accountSettingsFragment;

	private PublicInfoFragment publicInfoFragment;

	private ProgramSettingsFragment programSettingsFragment;

	private CreateProgramDepositFragment depositFragment;

	private ArrayList<Fragment> fragments;

	CreateProgramPagerAdapter(FragmentManager fm, Boolean needBrokerSelect, Boolean needPublicInfo, Boolean needDeposit, CreateProgramModel model) {
		super(fm);

		int stepNumber = 1;
		int stepsCount = 1;
		if (needBrokerSelect) {
			stepsCount += 2;
		}
		if (needPublicInfo) {
			stepsCount++;
		}
		if (needDeposit) {
			stepsCount++;
		}

		fragments = new ArrayList<>();

		if (needBrokerSelect) {
//			brokerSelectFragment = SelectBrokerFragment.with(new PublicInfoModel(true,
//					getStepNumberText(stepNumber),
//					GenesisVisionApplication.INSTANCE.getString(R.string.public_info), false,
//					true,
//					getButtonText(stepNumber, stepsCount),
//					null, null, null));
			brokerSelectFragment = SelectBrokerFragment.with(null, null, false);
			fragments.add(brokerSelectFragment);
			stepNumber++;

			accountSettingsFragment = BrokerSettingsFragment.with(null, true);
			fragments.add(accountSettingsFragment);
			stepNumber++;
		}
		if (needPublicInfo) {
			publicInfoFragment = PublicInfoFragment.with(new PublicInfoModel(true,
					getStepNumberText(stepNumber),
					GenesisVisionApplication.INSTANCE.getString(R.string.public_info), false,
					true,
					getButtonText(stepNumber, stepsCount),
					null, null, null));
			fragments.add(publicInfoFragment);
			stepNumber++;
		}

		programSettingsFragment = ProgramSettingsFragment.with(new ProgramSettingsModel(stepsCount > 1,
				getStepNumberText(stepNumber),
				GenesisVisionApplication.INSTANCE.getString(R.string.main_settings), false,
				getButtonText(stepNumber, stepsCount),
				model.getCurrency(),
				null, null, null, null, null, null,
				model.isExchange(), true, false, null));
		fragments.add(programSettingsFragment);
		stepNumber++;

		if (needDeposit) {
			depositFragment = CreateProgramDepositFragment.with(
					getStepNumberText(stepNumber),
					model);
			fragments.add(depositFragment);
		}
	}

	private String getStepNumberText(int stepNumber) {
		return String.format(Locale.getDefault(), "0%d", stepNumber);
	}

	private String getButtonText(int stepNumber, int stepsCount) {
		return stepNumber == stepsCount
				? GenesisVisionApplication.INSTANCE.getString(R.string.create_program)
				: String.format(Locale.getDefault(), "%s (%d/%d)", GenesisVisionApplication.INSTANCE.getString(R.string.next), stepNumber, stepsCount);
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	void setIsExchangeProgram(Boolean isExchangeProgram) {
		int stepsCount = 5;
		int currentStep = 3;
		if (isExchangeProgram) {
			stepsCount--;
			currentStep--;
		}

		if (accountSettingsFragment != null) {
			accountSettingsFragment.setSteps(getStepNumberText(2), getButtonText(2, 5));
		}

		if (publicInfoFragment != null) {
			publicInfoFragment.setSteps(getStepNumberText(currentStep), getButtonText(currentStep, stepsCount));
		}
		currentStep++;
		if (programSettingsFragment != null) {
			programSettingsFragment.setIsExchangeProgram(isExchangeProgram);
			programSettingsFragment.setSteps(getStepNumberText(currentStep), getButtonText(currentStep, stepsCount));
		}
		currentStep++;
		if (depositFragment != null) {
			depositFragment.setSteps(getStepNumberText(currentStep), getButtonText(currentStep, stepsCount));
		}
	}

	public void setSelectedBroker(Broker selectedBroker) {
		if (accountSettingsFragment != null) {
			accountSettingsFragment.setSelectedBroker(selectedBroker);
		}
	}

	public void setMinDeposit(Map<String, Double> minDepositInfo, Currency accountCurrency) {
		if (programSettingsFragment != null) {
			programSettingsFragment.setCurrency(accountCurrency);
		}
		if (depositFragment != null) {
			depositFragment.setMinDeposit(minDepositInfo, accountCurrency);
		}
	}

	int getAccountSettingsPosition() {
		return getFragmentPosition(accountSettingsFragment);
	}

	int getPublicInfoPosition() {
		return getFragmentPosition(publicInfoFragment);
	}

	int getSettingsPosition() {
		return getFragmentPosition(programSettingsFragment);
	}

	int getDepositPosition() {
		return getFragmentPosition(depositFragment);
	}

	private int getFragmentPosition(Fragment fragmentToFind) {
		int pos = 0;
		for (Fragment fragment : fragments) {
			if (fragment.equals(fragmentToFind)) {
				break;
			}
			pos++;
		}
		return pos;
	}
}
