package vision.genesis.clientapp.feature.main.trading_account.change_broker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import io.swagger.client.model.Broker;
import vision.genesis.clientapp.feature.main.trading_account.create.broker.SelectBrokerFragment;
import vision.genesis.clientapp.feature.main.trading_account.create.settings.BrokerSettingsFragment;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

public class ChangeBrokerPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private SelectBrokerFragment selectBrokerFragment;

	private BrokerSettingsFragment settingsFragment;


	private ArrayList<Fragment> fragments;

	ChangeBrokerPagerAdapter(FragmentManager fm, TradingAccountDetailsModel model) {
		super(fm);

		fragments = new ArrayList<>();

		selectBrokerFragment = SelectBrokerFragment.with(model.getAssetId(), model.getBrokerName(), false);
		fragments.add(selectBrokerFragment);

		settingsFragment = BrokerSettingsFragment.with(model.getAssetId(), false);
		fragments.add(settingsFragment);

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
			if (fragment.equals(settingsFragment)) {
				break;
			}
			pos++;
		}
		return pos;
	}

	public void setSelectedBroker(Broker selectedBroker) {
		if (settingsFragment != null) {
			settingsFragment.setSelectedBroker(selectedBroker);
		}
	}
}
