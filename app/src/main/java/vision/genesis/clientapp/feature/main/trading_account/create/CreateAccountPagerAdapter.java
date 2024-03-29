package vision.genesis.clientapp.feature.main.trading_account.create;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Map;

import io.swagger.client.model.Broker;
import io.swagger.client.model.ExchangeInfo;
import io.swagger.client.model.NewTradingAccountRequest;
import vision.genesis.clientapp.feature.main.trading_account.create.broker.SelectBrokerFragment;
import vision.genesis.clientapp.feature.main.trading_account.create.broker_settings.BrokerSettingsFragment;
import vision.genesis.clientapp.feature.main.trading_account.create.deposit.CreateAccountDepositFragment;
import vision.genesis.clientapp.feature.main.trading_account.create.exchange_settings.ExchangeSettingsFragment;
import vision.genesis.clientapp.model.CreateAccountModel;

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

	private BrokerSettingsFragment brokerSettingsFragment;

	private ExchangeSettingsFragment exchangeSettingsFragment;

	private CreateAccountDepositFragment depositFragment;

	private ArrayList<Fragment> fragments;

	CreateAccountPagerAdapter(FragmentManager fm, CreateAccountModel model) {
		super(fm);

		createFragments(model);
	}

	private void createFragments(CreateAccountModel model) {
		fragments = new ArrayList<>();

		if (model == null) {
			brokerFragment = SelectBrokerFragment.with(null, null, false);
			brokerSettingsFragment = BrokerSettingsFragment.with(null, false);
			exchangeSettingsFragment = ExchangeSettingsFragment.with(null, false);
			fragments.add(brokerFragment);
			fragments.add(brokerSettingsFragment);
			fragments.add(exchangeSettingsFragment);
		}
		depositFragment = CreateAccountDepositFragment.with(model);
		fragments.add(depositFragment);
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	void setRequest(NewTradingAccountRequest request) {
		if (depositFragment != null) {
			depositFragment.setRequest(request);
		}
	}

	public void setSelectedBroker(Broker selectedBroker) {
		if (brokerSettingsFragment != null) {
			brokerSettingsFragment.setSelectedBroker(selectedBroker);
		}
	}

	public void setSelectedExchange(ExchangeInfo selectedExchange) {
		if (exchangeSettingsFragment != null) {
			exchangeSettingsFragment.setSelectedExchange(selectedExchange);
		}
	}

	public void setMinDepositAmount(Map<String, Double> minDepositAmountInfo, String currency) {
		if (depositFragment != null) {
			depositFragment.setMinDepositAmount(minDepositAmountInfo, currency);
		}
	}

	public void setDepositStepNumber(String stepNumber) {
		if (depositFragment != null) {
			depositFragment.setStepNumber(stepNumber);
		}
	}
}
