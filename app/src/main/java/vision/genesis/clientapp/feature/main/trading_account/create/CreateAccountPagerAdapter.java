package vision.genesis.clientapp.feature.main.trading_account.create;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import io.swagger.client.model.Broker;
import io.swagger.client.model.NewTradingAccountRequest;
import vision.genesis.clientapp.feature.main.trading_account.create.broker.SelectBrokerFragment;
import vision.genesis.clientapp.feature.main.trading_account.create.deposit.CreateAccountDepositFragment;
import vision.genesis.clientapp.feature.main.trading_account.create.settings.BrokerSettingsFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

public class CreateAccountPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private SelectBrokerFragment brokerFragment;

	private BrokerSettingsFragment settingsFragment;

	private CreateAccountDepositFragment depositFragment;

	CreateAccountPagerAdapter(FragmentManager fm) {
		super(fm);

		createFragments();
	}

	private void createFragments() {
		brokerFragment = SelectBrokerFragment.with(null, null, false);
		settingsFragment = BrokerSettingsFragment.with(null);
		depositFragment = new CreateAccountDepositFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 1:
				return settingsFragment;
			case 2:
				return depositFragment;
			case 0:
			default:
				return brokerFragment;
		}
	}

	@Override
	public int getCount() {
		return 3;
	}

	void setRequest(NewTradingAccountRequest request) {
		depositFragment.setRequest(request);
	}

	public void setSelectedBroker(Broker selectedBroker) {
		settingsFragment.setSelectedBroker(selectedBroker);
	}

	public void setMinDepositAmount(Double minDepositAmount, String currency) {
		depositFragment.setMinDepositAmount(minDepositAmount, currency);
	}
}
