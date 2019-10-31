package vision.genesis.clientapp.feature.main.fund.create.main;

import com.arellomobile.mvp.MvpView;

import java.io.File;

import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

interface CreateFundMainView extends MvpView
{
	void openCamera(ImageUtils imageUtils, File newLogoFile);

	void openGallery(ImageUtils imageUtils);

	void startImageCropActivity(String imageUri);

	void showLogoProgress(boolean show);

	void removeLogo();

	void updateLogo(String url);

	void showSnackbarMessage(String message);

	void setNextButtonEnabled(boolean enabled);
}