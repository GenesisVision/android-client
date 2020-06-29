package vision.genesis.clientapp.feature.main.copytrading.edit_subscription;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import vision.genesis.clientapp.feature.main.copytrading.subscription_settings.SubscriptionSettingsFragment;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */

public class EditSubscriptionPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private SubscriptionSettingsFragment subscriptionSettingsFragment;

	private ArrayList<Fragment> fragments;

	EditSubscriptionPagerAdapter(FragmentManager fm, SubscriptionSettingsModel model) {
		super(fm);

		fragments = new ArrayList<>();
		subscriptionSettingsFragment = SubscriptionSettingsFragment.with(model);
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
