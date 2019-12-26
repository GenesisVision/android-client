package vision.genesis.clientapp.feature.main.settings.referral_program;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import vision.genesis.clientapp.feature.main.settings.referral_program.history.ReferralHistoryFragment;
import vision.genesis.clientapp.feature.main.settings.referral_program.info.ReferralInfoFragment;
import vision.genesis.clientapp.feature.main.settings.referral_program.referral_friends.ReferralFriendsFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

public class ReferralProgramPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private ReferralInfoFragment infoFragment;

	private ReferralFriendsFragment referralFriendsFragment;

	private ReferralHistoryFragment historyFragment;


	private TabLayout tabLayout;

	ReferralProgramPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
		super(fm);
		this.tabLayout = tabLayout;

		infoFragment = new ReferralInfoFragment();
		referralFriendsFragment = new ReferralFriendsFragment();
		historyFragment = new ReferralHistoryFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (tabLayout.getTabAt(position).getTag().toString()) {
			case "info":
				return infoFragment;
			case "referral_friends":
				return referralFriendsFragment;
			case "history":
				return historyFragment;
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return tabLayout.getTabCount();
	}

	public void destroy() {
	}

	public void sendUpdate() {
		if (infoFragment != null) {
			infoFragment.pagerShow();
		}
		if (referralFriendsFragment != null) {
			referralFriendsFragment.pagerShow();
		}
		if (historyFragment != null) {
			historyFragment.pagerShow();
		}
	}

	public void sendSwipeRefresh() {
		if (infoFragment != null) {
			infoFragment.onSwipeRefresh();
		}
		if (referralFriendsFragment != null) {
			referralFriendsFragment.pagerShow();
		}
		if (historyFragment != null) {
			historyFragment.onSwipeRefresh();
		}
	}
}