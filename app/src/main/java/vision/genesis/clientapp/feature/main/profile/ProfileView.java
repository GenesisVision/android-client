package vision.genesis.clientapp.feature.main.profile;

import com.arellomobile.mvp.MvpView;

import java.io.File;

import io.swagger.client.model.ProfileFullViewModel;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

interface ProfileView extends MvpView
{
	void setEditMode(boolean editMode);

	void updateProfile(ProfileFullViewModel profileModel);

	void updateAvatar(String imageId);

	void openCamera(ImageUtils imageUtils, File newAvatarFile);

	void openGallery(ImageUtils imageUtils);

	void showAvatarProgress(boolean show);

	void showUpdateProgress(boolean show);

	void showSnackbarMessage(String message);
}
