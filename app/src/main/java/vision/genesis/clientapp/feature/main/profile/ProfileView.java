package vision.genesis.clientapp.feature.main.profile;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.ProfileFullViewModel;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface ProfileView extends MvpView
{
	void updateProfile(ProfileFullViewModel profileModel);
}
