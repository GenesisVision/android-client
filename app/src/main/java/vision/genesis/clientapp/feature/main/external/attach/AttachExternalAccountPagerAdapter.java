package vision.genesis.clientapp.feature.main.external.attach;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import io.swagger.client.model.BrokerDetails;
import vision.genesis.clientapp.feature.main.external.attach.api_key.ExternalApiKeyFragment;
import vision.genesis.clientapp.feature.main.trading_account.create.broker.SelectBrokerFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/12/2019.
 */

public class AttachExternalAccountPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private ArrayList<Fragment> fragments;

	AttachExternalAccountPagerAdapter(FragmentManager fm, BrokerDetails selectedBroker) {
		super(fm);

		createFragments(selectedBroker);
	}

	private void createFragments(BrokerDetails selectedBroker) {
		fragments = new ArrayList<>();

		if (selectedBroker == null) {
			fragments.add(SelectBrokerFragment.with(null, null, true));
		}
		fragments.add(ExternalApiKeyFragment.with(selectedBroker));
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}
}
