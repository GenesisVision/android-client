package vision.genesis.clientapp.feature.main.external.attach;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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

	private SelectBrokerFragment brokerFragment;

	private ExternalApiKeyFragment apiKeyFragment;

	AttachExternalAccountPagerAdapter(FragmentManager fm) {
		super(fm);

		createFragments();
	}

	private void createFragments() {
		brokerFragment = SelectBrokerFragment.with(null, null, true);
		apiKeyFragment = new ExternalApiKeyFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 1:
				return apiKeyFragment;
			case 0:
			default:
				return brokerFragment;
		}
	}

	@Override
	public int getCount() {
		return 2;
	}
}
