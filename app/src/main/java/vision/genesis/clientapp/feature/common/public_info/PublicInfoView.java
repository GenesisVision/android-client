package vision.genesis.clientapp.feature.common.public_info;

import com.arellomobile.mvp.MvpView;

import java.io.File;

import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

interface PublicInfoView extends MvpView
{
	void setTitle(String title);

	void setDescription(String description);

	void openCamera(ImageUtils imageUtils, File newLogoFile);

	void openGallery(ImageUtils imageUtils);

	void startImageCropActivity(String imageUri);

	void showLogoProgress(boolean show);

	void removeLogo();

	void updateLogo(String url);

	void showSnackbarMessage(String message);

	void setConfirmButtonEnabled(boolean enabled);

	void showTitleError(String errorText);

	void showDescriptionError(String errorText);

	void cleanTitleError();

	void cleanDescriptionError();
}