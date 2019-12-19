package vision.genesis.clientapp.feature.main.copytrading.follow_trades;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.UUID;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.copytrading.select_account.SelectSubscriptionAccountFragment;
import vision.genesis.clientapp.feature.main.copytrading.subscription_settings.SubscriptionSettingsFragment;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */

public class FollowTradesPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private SelectSubscriptionAccountFragment selectAccountFragment;

	private SubscriptionSettingsFragment subscriptionSettingsFragment;

	private ArrayList<Fragment> fragments;

	FollowTradesPagerAdapter(FragmentManager fm, UUID followId) {
		super(fm);

		fragments = new ArrayList<>();

		selectAccountFragment = SelectSubscriptionAccountFragment.with(followId);
		fragments.add(selectAccountFragment);


		subscriptionSettingsFragment = SubscriptionSettingsFragment.with(new SubscriptionSettingsModel(true, "02",
				GenesisVisionApplication.INSTANCE.getString(R.string.subscription_settings),
				GenesisVisionApplication.INSTANCE.getString(R.string.subscribe),
				null, null, null, null, null));
		fragments.add(subscriptionSettingsFragment);
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
			if (fragment.equals(subscriptionSettingsFragment)) {
				break;
			}
			pos++;
		}
		return pos;
	}
}
