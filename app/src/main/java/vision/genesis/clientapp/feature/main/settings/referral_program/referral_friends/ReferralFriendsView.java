package vision.genesis.clientapp.feature.main.settings.referral_program.referral_friends;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.ReferralFriend;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

interface ReferralFriendsView extends MvpView
{
	void setReferrals(List<ReferralFriend> newReferrals);

	void addReferrals(List<ReferralFriend> newReferrals);

	void showProgress(boolean show);

	void showEmpty(boolean show);

	void showSnackbarMessage(String message);
}
