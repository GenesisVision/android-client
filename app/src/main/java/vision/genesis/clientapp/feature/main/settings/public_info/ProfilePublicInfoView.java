package vision.genesis.clientapp.feature.main.settings.public_info;

import com.arellomobile.mvp.MvpView;

import java.io.File;

import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/12/2019.
 */

interface ProfilePublicInfoView extends MvpView
{
	void setUsername(String title);

	void setAbout(String description);

	void openCamera(ImageUtils imageUtils, File newLogoFile);

	void openGallery(ImageUtils imageUtils);

	void startImageCropActivity(String imageUri);

	void showLogoProgress(boolean show);

	void removeLogo();

	void updateLogo(String url);

	void updateLogoById(String logoId);

	void showSnackbarMessage(String message);

	void setConfirmButtonEnabled(boolean enabled);

	void showProgress(boolean show);

	void showButtonProgress(boolean show);

	void finishActivity();
}